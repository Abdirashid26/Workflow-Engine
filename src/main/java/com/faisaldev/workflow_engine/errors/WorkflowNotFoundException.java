package com.faisaldev.workflow_engine.errors;

public class WorkflowNotFoundException extends RuntimeException {
    public WorkflowNotFoundException(String message) {
        super(message);
    }
}
