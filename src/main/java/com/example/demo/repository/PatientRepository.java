package com.example.demo.repository;

import com.example.demo.model.PatientDetails;
import org.springframework.cloud.gcp.data.spanner.repository.SpannerRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.*;

@Repository
public interface PatientRepository extends SpannerRepository<PatientDetails, Long>, CrudRepository<PatientDetails, Long> {
}
