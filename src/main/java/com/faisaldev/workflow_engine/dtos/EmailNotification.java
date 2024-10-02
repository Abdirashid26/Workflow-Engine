package com.faisaldev.workflow_engine.dtos;


import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmailNotification {



    private String title;

    private String message;

    private String email;


}
