package com.faisaldev.workflow_engine.dtos;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ApprovalEmailDto {

    private String profile;
    private String message;

}
