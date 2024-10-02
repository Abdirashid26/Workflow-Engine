package com.faisaldev.workflow_engine.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WorkflowItem {

    private UUID id = UUID.randomUUID();
    private UUID itemId;
    private Long workflowId;
    private String payload;
    private String workflowStatus;  // Adjust this to the appropriate type if WorkflowStatus is an enum
    private String completedTopic;


}
