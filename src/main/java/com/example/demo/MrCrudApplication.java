package com.example.demo;

import com.example.demo.model.PatientKey;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gcp.data.spanner.repository.config.EnableSpannerRepositories;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.function.Function;

@EnableSpannerRepositories
@SpringBootApplication
public class MrCrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(MrCrudApplication.class, args);
	}

	@Bean
	public Function<String, String> reverseString() {
		return value -> new StringBuilder(value).reverse().toString();
	}

	@Bean
	public Function<PatientKey, PatientKey> get() {
		return key -> key;
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
