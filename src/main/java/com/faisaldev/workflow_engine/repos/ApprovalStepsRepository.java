package com.faisaldev.workflow_engine.repos;

import com.faisaldev.workflow_engine.models.ApprovalSteps;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import java.util.UUID;

public interface ApprovalStepsRepository extends ReactiveCrudRepository<ApprovalSteps, UUID> {
}
