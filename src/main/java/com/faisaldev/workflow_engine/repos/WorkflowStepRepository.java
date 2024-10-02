package com.faisaldev.workflow_engine.repos;

import com.faisaldev.workflow_engine.models.WorkflowStep;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface WorkflowStepRepository extends ReactiveCrudRepository<WorkflowStep, Long> {

    Mono<WorkflowStep> findByWorkflowIdAndOrderNumber(Long workflowId, Long orderNumber);
    Flux<WorkflowStep> findByWorkflowIdOrderByOrderNumberAsc(Long workflowId);

}
