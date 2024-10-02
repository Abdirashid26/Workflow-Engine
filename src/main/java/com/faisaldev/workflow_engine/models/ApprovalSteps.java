package com.faisaldev.workflow_engine.models;


import com.faisaldev.workflow_engine.enums.ApproverType;
import com.faisaldev.workflow_engine.enums.OrderType;
import com.faisaldev.workflow_engine.enums.WorkflowStatus;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table("tb_approval_steps")
public class ApprovalSteps {

    @Id
    private UUID id;

    @Column("item_id")
    private UUID itemId;

    @Column("workflow_id")
    private Long workflowId;

    @Column("order_number")
    private Long orderNumber;

    private String payload;

    @Column("workflow_status")
    private WorkflowStatus workflowStatus;

    @Column("completed_topic")
    private String completedTopic;

    @Column("approver_type")
    private ApproverType approverType;

    @Column("order_type")
    private OrderType orderType;



}
