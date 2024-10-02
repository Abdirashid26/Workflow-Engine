package com.faisaldev.workflow_engine.kafka;


import com.faisaldev.workflow_engine.dtos.ApprovalEmailDto;
import com.faisaldev.workflow_engine.dtos.CompletedWorkflowDto;
import com.faisaldev.workflow_engine.dtos.EmailNotification;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class PublishingService {


    private final Gson gson ;
    private final StreamBridge streamBridge ;



    public void sendCompletedWorkflowTopic(CompletedWorkflowDto completedWorkflowDto, String topic ) {
        Mono.fromRunnable(() -> {
                    streamBridge.send(topic, gson.toJson(completedWorkflowDto));
                })
                .subscribeOn(Schedulers.boundedElastic())
                .subscribe();

    }


    public void sendWorkflowApprovalInfo(ApprovalEmailDto approvalEmailDto ) {
        Mono.fromRunnable(() -> {
            streamBridge.send("agent-connect-send-email-to-approvers-topic", gson.toJson(approvalEmailDto));
        })
                .subscribeOn(Schedulers.boundedElastic())
                .subscribe();
    }




}
