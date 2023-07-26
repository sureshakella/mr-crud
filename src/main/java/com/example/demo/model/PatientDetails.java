package com.example.demo.model;

import lombok.*;

import java.io.Serializable;
import org.springframework.cloud.gcp.data.spanner.core.mapping.*;


@Getter @Setter @NoArgsConstructor
@ToString()
@Table(name="test-project.PATIENT_DETAILS")
@Data
public class PatientDetails implements Serializable {

    @PrimaryKey()
    @Column(name="ID")
    private Long id; // will be set when persisting
    @Column(name="FIRST_NAME")
    private String firstName;
    @Column(name="LAST_NAME")
    private String lastName;
    @Column(name="DOB")
    private String dob;
    @Column(name="GENDER")
    private String gender;
    @Column(name="PHONE_NUMBER")
    private Long phoneNumber;
    @Column(name="ADDRESS")
    private String address;
    @Column(name="PIN_CODE")
    private Integer pinCode;
    @Column(name="EMAIL_ID")
    private String emailId;
}