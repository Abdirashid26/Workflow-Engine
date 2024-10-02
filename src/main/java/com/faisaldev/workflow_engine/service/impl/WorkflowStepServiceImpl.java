package com.faisaldev.workflow_engine.service.impl;

import com.faisaldev.workflow_engine.dtos.CompletedWorkflowDto;
import com.faisaldev.workflow_engine.dtos.WorkflowItem;
import com.faisaldev.workflow_engine.enums.OrderType;
import com.faisaldev.workflow_engine.enums.WorkflowStatus;
import com.faisaldev.workflow_engine.errors.WorkflowActivation;
import com.faisaldev.workflow_engine.errors.WorkflowNotFoundException;
import com.faisaldev.workflow_engine.kafka.PublishingService;
import com.faisaldev.workflow_engine.models.ApprovalSteps;
import com.faisaldev.workflow_engine.models.Workflow;
import com.faisaldev.workflow_engine.models.WorkflowStep;
import com.faisaldev.workflow_engine.repos.ApprovalStepsRepository;
import com.faisaldev.workflow_engine.repos.WorkflowRepository;
import com.faisaldev.workflow_engine.repos.WorkflowStepRepository;
import com.faisaldev.workflow_engine.service.WorkflowStepService;
import com.faisaldev.workflow_engine.utils.GlobalResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
@Slf4j
public class WorkflowStepServiceImpl implements WorkflowStepService {

    private final WorkflowStepRepository workflowStepRepository;
    private final WorkflowRepository workflowRepository;
    private final ApprovalStepsRepository approvalStepsRepository;
    private final PublishingService publishingService;

    @Override
    public Mono<GlobalResponse> addWorkflowStep(WorkflowStep workflowStep) {
        return workflowRepository.findById(workflowStep.getWorkflowId())
                .switchIfEmpty(Mono.error(new WorkflowNotFoundException("Workflow with ID " + workflowStep.getWorkflowId() + " not found")))
                .flatMap(workflow -> {
                    return workflowStepRepository.findByWorkflowIdAndOrderNumber(workflowStep.getWorkflowId(), workflowStep.getOrderNumber())
                            .flatMap(existingStep -> Mono.just(new GlobalResponse("01","Workflow Step with Order Number " + workflowStep.getOrderNumber() + " already exists",null)))
                            .switchIfEmpty(Mono.defer(() -> {
                                return workflowStepRepository.save(workflowStep)
                                        .doOnError(err -> log.error("Add WorkflowStep Error", err))
                                        .map(savedStep -> GlobalResponse.builder()
                                                .status("00")
                                                .message("Add WorkflowStep Success")
                                                .build());
                            }));
                });
    }

    @Override
    public void addWorkflowApprovalStep(WorkflowItem approvalStepsDto) {
        workflowRepository.findById(approvalStepsDto.getWorkflowId())
                // Publish to the complete if the workflow does not exist
                .switchIfEmpty(
                    Mono.error(
                            new WorkflowActivation(
                                    CompletedWorkflowDto.builder().workflowItemId(approvalStepsDto.getItemId()).workflowStatus(WorkflowStatus.APPROVED).build(),
                                    approvalStepsDto.getCompletedTopic(),
                                    "Workflow Approval Step Activated"
                            )
                    )
                )
                .zipWith(workflowStepRepository.findByWorkflowIdOrderByOrderNumberAsc(approvalStepsDto.getWorkflowId()).collectList())
                .publishOn(Schedulers.boundedElastic())
                .map(tuple -> {
                    Workflow workflow = tuple.getT1();
                    List<WorkflowStep> workflowSteps = tuple.getT2();

                    if (!workflow.isActive() || workflowSteps.isEmpty()) {
                        // approve the work item (publish to the topic directly)
                        publishingService.sendCompletedWorkflowTopic(
                                CompletedWorkflowDto.builder().workflowItemId(approvalStepsDto.getItemId()).workflowStatus(WorkflowStatus.APPROVED).build(),
                                approvalStepsDto.getCompletedTopic()
                                );
                    }else{

                        if(workflow.getOrderType().equals(OrderType.UNORDERERED)){
                            for (WorkflowStep workflowStep : workflowSteps) {
                                ApprovalSteps approvalSteps = ApprovalSteps.builder()
                                        .workflowId(workflow.getId())
                                        .orderNumber(workflowStep.getOrderNumber())
                                        .approverType(workflowStep.getApproverType())
                                        .workflowStatus(WorkflowStatus.PENDING)
                                        .itemId(approvalStepsDto.getItemId())
//                                        .id(approvalStepsDto.getId())
                                        .completedTopic(approvalStepsDto.getCompletedTopic())
                                        .orderType(workflow.getOrderType())
                                        .payload(approvalStepsDto.getPayload())
                                        .build();

                                approvalStepsRepository.save(approvalSteps)
                                        .doOnSuccess(res -> {
                                                // publish message to Topic to notify the Auth Service to send emails to
                                                //  people/users with that Role

                                        })
                                        .subscribe();

                            }
                        }else{
                            WorkflowStep workflowStep = workflowSteps.get(0);
                            ApprovalSteps approvalSteps = ApprovalSteps.builder()
                                    .workflowId(workflow.getId())
                                    .orderNumber(workflowStep.getOrderNumber())
                                    .approverType(workflowStep.getApproverType())
                                    .workflowStatus(WorkflowStatus.PENDING)
                                    .itemId(approvalStepsDto.getItemId())
//                                    .id(approvalStepsDto.getId())
                                    .completedTopic(approvalStepsDto.getCompletedTopic())
                                    .orderType(workflow.getOrderType())
                                    .payload(approvalStepsDto.getPayload())
                                    .build();
                            approvalStepsRepository.save(approvalSteps).subscribe();

                        }

                    }

                    return tuple;

                })
                .doOnError(err -> log.error("Add WorkFlow Item WorkflowApprovalStep Error", err))
                .subscribe();



    }


}
