package com.example.demo.model;

import lombok.*;

@Getter @Setter @NoArgsConstructor
@AllArgsConstructor
@ToString(includeFieldNames=true)
public class PatientKey {
    private Long id;
    private String firstName;
    private String lastName;
    private Long phoneNumber;
}
