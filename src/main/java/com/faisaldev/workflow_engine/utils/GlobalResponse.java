package com.faisaldev.workflow_engine.utils;


import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GlobalResponse {

    private String status;
    private String message;
    private Object data;

}
