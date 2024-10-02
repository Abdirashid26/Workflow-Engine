package com.faisaldev.workflow_engine.dtos;


import com.faisaldev.workflow_engine.enums.WorkflowStatus;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompletedWorkflowDto {

    private UUID workflowItemId;

    private WorkflowStatus workflowStatus;


}
