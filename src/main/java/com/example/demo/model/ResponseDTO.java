package com.example.demo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @NoArgsConstructor
@ToString(includeFieldNames=true)
public class ResponseDTO {
    private String message;
    private boolean status;
    private Object data;
}
