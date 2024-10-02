package com.faisaldev.workflow_engine.errors;

import com.faisaldev.workflow_engine.dtos.CompletedWorkflowDto;
import com.faisaldev.workflow_engine.dtos.WorkflowDto;
import lombok.Getter;
import lombok.Setter;

@Getter
public class WorkflowActivation extends RuntimeException {

    private final CompletedWorkflowDto completedWorkflowDto;
    private final String topic;

    public WorkflowActivation(CompletedWorkflowDto completedWorkflowDto, String topic, String message) {
        super(message);
        this.completedWorkflowDto = completedWorkflowDto;
        this.topic = topic;
    }
}
