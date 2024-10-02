package com.faisaldev.workflow_engine.controller;


import com.faisaldev.workflow_engine.dtos.WorkflowDto;
import com.faisaldev.workflow_engine.models.WorkflowStep;
import com.faisaldev.workflow_engine.service.WorkflowService;
import com.faisaldev.workflow_engine.service.WorkflowStepService;
import com.faisaldev.workflow_engine.utils.GlobalResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/api/v1/workflow-engine/")
@RequiredArgsConstructor
public class WorkflowController {

    private final WorkflowService workflowService;
    private final WorkflowStepService workflowStepService;


    @PostMapping("create-workflow")
    public Mono<ResponseEntity<GlobalResponse>> createWorkflow(
            @RequestBody WorkflowDto workflowDto
            ){

        return workflowService.createWorkflow(workflowDto)
                .map(ResponseEntity::ok);


    }

    @PostMapping("add-workflow-step")
    public Mono<ResponseEntity<GlobalResponse>> addWorkflowStep(
            @RequestBody WorkflowStep workflowStep
    ){

        return workflowStepService.addWorkflowStep(workflowStep)
                .map(ResponseEntity::ok);


    }



}
