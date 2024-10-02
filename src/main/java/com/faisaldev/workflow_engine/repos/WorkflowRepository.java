package com.faisaldev.workflow_engine.repos;

import com.faisaldev.workflow_engine.models.Workflow;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface WorkflowRepository extends ReactiveCrudRepository<Workflow, Long> {


}
