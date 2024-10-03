package com.faisaldev.workflow_engine.dtos;


import com.faisaldev.workflow_engine.enums.ApproverType;
import com.faisaldev.workflow_engine.enums.WorkflowStatus;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ApproveWorkflowStep {


    String workflowItemId;
    WorkflowStatus workflowStatus;
    ApproverType approverType;


}
