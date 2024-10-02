package com.faisaldev.workflow_engine.controller.advice;

import com.faisaldev.workflow_engine.errors.WorkflowActivation;
import com.faisaldev.workflow_engine.kafka.PublishingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class ControllerAdvice {

    private final PublishingService publishingService;


    @ExceptionHandler(WorkflowActivation.class)
    public String handleWorkflowActivation(WorkflowActivation exception) {
        log.error(exception.getMessage());
        publishingService.sendCompletedWorkflowTopic(
                exception.getCompletedWorkflowDto(),
                exception.getTopic()
        );
        return exception.getMessage();
    }



}
