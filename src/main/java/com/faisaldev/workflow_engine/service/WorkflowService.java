package com.faisaldev.workflow_engine.service;

import com.faisaldev.workflow_engine.dtos.WorkflowDto;
import com.faisaldev.workflow_engine.models.Workflow;
import com.faisaldev.workflow_engine.utils.GlobalResponse;
import reactor.core.publisher.Mono;

public interface WorkflowService {


    Mono<GlobalResponse> createWorkflow(WorkflowDto workflowDto);


}
