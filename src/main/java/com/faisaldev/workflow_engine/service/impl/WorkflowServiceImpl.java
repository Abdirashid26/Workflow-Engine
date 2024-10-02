package com.faisaldev.workflow_engine.service.impl;

import com.faisaldev.workflow_engine.dtos.WorkflowDto;
import com.faisaldev.workflow_engine.models.Workflow;
import com.faisaldev.workflow_engine.repos.WorkflowRepository;
import com.faisaldev.workflow_engine.service.WorkflowService;
import com.faisaldev.workflow_engine.utils.GlobalResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


@Service
@RequiredArgsConstructor
@Slf4j
public class WorkflowServiceImpl implements WorkflowService {


    private final WorkflowRepository workflowRepository;


    @Override
    public Mono<GlobalResponse> createWorkflow(WorkflowDto workflowDto) {
        Workflow workflow = Workflow.builder()
                .name(workflowDto.getName())
                .remarks(workflowDto.getRemarks())
                .process(workflowDto.getWorkflowProcess())
                .orderType(workflowDto.getOrderType())
                .build();
        return Mono.just(workflow)
                .flatMap(workflowRepository::save)
                .flatMap(res -> Mono.just(
                        GlobalResponse.builder()
                                .status("00")
                                .message("Workflow created")
                                .data(null)
                                .build()
                ));
    }



}
