package com.faisaldev.workflow_engine.models;


import com.faisaldev.workflow_engine.enums.ApproverType;
import com.faisaldev.workflow_engine.enums.OrderType;
import com.faisaldev.workflow_engine.enums.WorkflowProcess;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table("tb_workflow_steps")
public class WorkflowStep {

    @Id
    private Long id;

    private String name;

    private String remarks;

    @Column("workflow_id")
    private Long workflowId;

    @Column("order_number")
    private Long orderNumber;

    @Column("approver_type")
    private ApproverType approverType;



}
