package com.faisaldev.workflow_engine.repos;

import com.faisaldev.workflow_engine.enums.ApproverType;
import com.faisaldev.workflow_engine.enums.WorkflowStatus;
import com.faisaldev.workflow_engine.models.ApprovalSteps;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface ApprovalStepsRepository extends ReactiveCrudRepository<ApprovalSteps, UUID> {


    Mono<ApprovalSteps> findByItemIdAndWorkflowStatusAndApproverType(UUID itemId, WorkflowStatus workflowStatus, ApproverType approverType);

    Flux<ApprovalSteps> findByItemId(UUID itemId);

    Flux<ApprovalSteps> findAllByItemIdAndWorkflowStatus(UUID itemId, WorkflowStatus workflowStatus);

}
