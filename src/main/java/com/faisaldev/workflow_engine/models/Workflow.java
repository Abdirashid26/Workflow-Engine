package com.faisaldev.workflow_engine.models;


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
@Table("tb_workflows")
public class Workflow {

    @Id
    private Long id;

    private String name;

    private String remarks;

    private OrderType orderType;

    private WorkflowProcess process;

    private boolean active = true;

    @Column("soft_delete")
    private boolean softDelete = false;


}
