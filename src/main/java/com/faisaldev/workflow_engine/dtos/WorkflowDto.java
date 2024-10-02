package com.faisaldev.workflow_engine.dtos;


import com.faisaldev.workflow_engine.enums.OrderType;
import com.faisaldev.workflow_engine.enums.WorkflowProcess;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WorkflowDto {

    @NotEmpty(message = "Name cannot be empty")
    @NonNull
    private String name;
    @NotEmpty(message = "Remarks cannot be empty")
    @NonNull
    private String remarks;
    @NotEmpty(message = "Workflow process cannot be empty")
    @NonNull
    private WorkflowProcess workflowProcess;
    @NotEmpty(message = "Order Type cannot be empty")
    @NonNull
    private OrderType orderType;


}
