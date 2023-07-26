package com.example.demo.service;

import com.example.demo.model.PatientDetails;
import com.example.demo.model.PatientKey;
import com.example.demo.model.ResponseDTO;
import com.example.demo.repository.PatientRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PatientService {
    private static List<PatientDetails> patients = new ArrayList<>();

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private RestTemplate restTemplate;

    @PostConstruct
    public void init() {
        PatientDetails patient = new PatientDetails();
        patient.setId(1L);
        patient.setFirstName("Lorem");
        patient.setLastName("Ipsum");
        patient.setDob("31-12-2020");
        patient.setGender("Male");
        patient.setPhoneNumber(1234567890L);
        patient.setAddress("Flat#456, Test Street, Fake City, Bogus State");
        patient.setPinCode(987654);
        patient.setEmailId("test@gmail.com");
        patientRepository.save(patient);
    }

//    private List<PatientDetails> generatePatientDetails() {
//
//        return new ArrayList<>();
//    }

    public ResponseDTO save(PatientDetails patient) {
        patientRepository.save(patient);
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage("Success");
        responseDTO.setStatus(true);
        responseDTO.setData(patient);
        return responseDTO;
    }

    public ResponseDTO update(PatientDetails patient) {
        ResponseDTO responseDTO = new ResponseDTO();
        if (Objects.nonNull(patient.getId())) {
            Optional<PatientDetails> oldPatient = patientRepository.findById(patient.getId());
            if (oldPatient.isPresent()) {
                patientRepository.save(patient);
                responseDTO.setData(patient);
                responseDTO.setStatus(true);
                responseDTO.setMessage("Patient Details Update Success ");
            }
            else {
                responseDTO.setMessage("Patient Details not found");
                responseDTO.setStatus(false);
            }
        } else {
            responseDTO.setMessage("No valid patient key provided");
            responseDTO.setStatus(false);
        }
        return responseDTO;
    }

    public ResponseDTO get(PatientKey key) {
        ResponseDTO responseDTO = new ResponseDTO();
        if (Objects.nonNull(key)) {
            Optional<PatientDetails> patient = patients.stream()
                    .filter(it -> Objects.equals(key.getId(), it.getId()) ||
                            Objects.equals(key.getPhoneNumber(), it.getPhoneNumber()) ||
                            (Objects.equals(key.getFirstName(), it.getFirstName()) && Objects.equals(key.getLastName(), it.getLastName())) ||
                            Objects.equals(key.getFirstName(), it.getFirstName()) || Objects.equals(key.getLastName(), it.getLastName()))
                    .findFirst();

            if(patient.isPresent()) {
                responseDTO.setMessage("Patient Details found");
                responseDTO.setData(patient.get());
                responseDTO.setStatus(true);
            }
            else {
                responseDTO.setMessage("Patient details not found");
                responseDTO.setStatus(false);
            }
        } else {
            responseDTO.setMessage("No valid patient search keys provided. " +
                    "Please provide patientId or Phone number or Full name to retrieve patient details");
            responseDTO.setStatus(false);
        }
        return responseDTO;
    }

    public ResponseDTO delete(Long idOrPhoneNumber) {
        ResponseDTO responseDTO = new ResponseDTO();
        boolean success = Objects.nonNull(idOrPhoneNumber) &&
                patients.removeIf(it -> it.getId().equals(idOrPhoneNumber) || it.getPhoneNumber().equals(idOrPhoneNumber));
        if (success) {
            responseDTO.setMessage("Record is deleted successfully");
            responseDTO.setStatus(true);
        }
        else {
            responseDTO.setMessage("Record Id/Phone number is not found");
            responseDTO.setStatus(false);
        }
        return responseDTO;
    }

    public ResponseDTO getAll() {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage("All records fetched");
        responseDTO.setStatus(true);
        responseDTO.setData(patientRepository.findAll());
        return responseDTO;
    }

    public ResponseDTO userProfile(String id) {
        PatientDetails user = restTemplate.getForEntity("https://jsonplaceholder.typicode.com/todos/" + id, PatientDetails.class).getBody();
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setData(user);
        return responseDTO;
    }
}
