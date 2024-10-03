package com.faisaldev.workflow_engine.kafka;


import com.faisaldev.workflow_engine.dtos.ApproveWorkflowStep;
import com.faisaldev.workflow_engine.dtos.WorkflowItem;
import com.faisaldev.workflow_engine.service.WorkflowService;
import com.faisaldev.workflow_engine.service.WorkflowStepService;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class SubscribingService {

    private final Gson gson;
    private final WorkflowStepService workflowStepService;

    /**
     * Create Item for Workflow
     * @return
     */
    @Bean
    public Consumer<String> createItem(){
        return payload -> {
            WorkflowItem workflowItem = gson.fromJson(payload, WorkflowItem.class);
            workflowStepService.addWorkflowApprovalStep(workflowItem);
        };
    }


    /**
     * Approve/Reject Workflow Step
     */
    @Bean
    public Consumer<String> approveWorkflowStep(){
        return payload -> {
            ApproveWorkflowStep approveWorkflowStep = gson.fromJson(payload, ApproveWorkflowStep.class);
            workflowStepService.approveWorkflowStep(approveWorkflowStep);
        };
    }



}
