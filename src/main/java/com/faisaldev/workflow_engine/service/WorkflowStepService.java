package com.faisaldev.workflow_engine.service;

import com.faisaldev.workflow_engine.dtos.ApproveWorkflowStep;
import com.faisaldev.workflow_engine.dtos.WorkflowItem;
import com.faisaldev.workflow_engine.models.WorkflowStep;
import com.faisaldev.workflow_engine.utils.GlobalResponse;
import reactor.core.publisher.Mono;

public interface WorkflowStepService {


    Mono<GlobalResponse> addWorkflowStep(WorkflowStep workflowStep);

    void addWorkflowApprovalStep(WorkflowItem approvalStepsDto);

    void approveWorkflowStep(ApproveWorkflowStep approveWorkflowStep);


}
