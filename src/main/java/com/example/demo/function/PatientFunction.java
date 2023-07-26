package com.example.demo.function;

import com.example.demo.model.PatientDetails;
import com.example.demo.model.PatientKey;
import com.example.demo.model.ResponseDTO;
import com.example.demo.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.function.web.util.HeaderUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

//https://github.com/springdoc/springdoc-openapi-demos/blob/master/springdoc-openapi-spring-cloud-function/spring-cloud-function-webmvc/src/main/java/org/springdoc/demo/services/functions/SampleApplication.java
@Configuration
public class PatientFunction {

    @Autowired
    PatientService patientService;

    @Bean("patientSave")
    @RouterOperation(method = RequestMethod.POST,
            operation = @Operation(description = "Save patient details", operationId = "patientSave", tags = "modifies",
                    responses = @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = ResponseDTO.class)))))
    public Function<PatientDetails, ResponseDTO> save() {
        return user -> patientService.save(user);
    }

    @Bean("patientUpdate")
    @RouterOperation(method = RequestMethod.POST,
            operation = @Operation(description = "Update patient details for supplied patient keys", operationId = "patientUpdate", tags = "modifies",
                    responses = @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = ResponseDTO.class)))))
    public Function<PatientDetails, ResponseDTO> update() {
        return user -> patientService.update(user);
    }

    @Bean("patientGet")
    @RouterOperation(method = RequestMethod.GET,
            operation = @Operation(description = "Fetch patient details for supplied patient keys", operationId = "patientGet", tags = "fetches",
                    responses = @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = ResponseDTO.class)))))
    public Function<Message<String>, ResponseDTO> get() {

        return message -> {
            Map<String, String> httpParam = (Map<String, String>)message.getHeaders().get(HeaderUtils.HTTP_REQUEST_PARAM);
            if(Objects.nonNull(httpParam)) {
                PatientKey key = new PatientKey(toLong(httpParam.get("id")),
                        httpParam.getOrDefault("firstname", null),
                        httpParam.getOrDefault("lastname", null),
                        toLong(httpParam.get("phone_number")));

                return patientService.get(key);
            }
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setMessage("Patient Details found");
            responseDTO.setStatus(true);
            return responseDTO;
        };
    }

    public Long toLong(String value) {
        if (Objects.nonNull(value)) {
            try {
                return Long.valueOf(value);
            } catch (NumberFormatException nx) {
                return null;
            }
        }
        return null;
    }

//    TODO: Add authentication to avoid inadvertent delete by anyone
    @Bean("patientDelete")
    @RouterOperation(method = RequestMethod.POST,
            operation = @Operation(description = "Delete patient details", operationId = "patientDelete", tags = "modifies",
                    responses = @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = ResponseDTO.class)))))
    public Function<Long, ResponseDTO> delete() {
        return id -> patientService.delete(id);
    }

    @Bean("patientGetAll")
    @RouterOperation(method = RequestMethod.GET,
            operation = @Operation(description = "Fetch all patient details", operationId = "patientGetAll", tags = "fetches",
                    responses = @ApiResponse(responseCode = "200", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ResponseDTO.class))))))
    public Supplier<ResponseDTO> getAll() {
        return () -> patientService.getAll();
    }

    @Bean("patientProfile")
    @RouterOperation(method = RequestMethod.GET,
            operation = @Operation(description = "Fetch patient profile", operationId = "patientGet", tags = "profile",
                    responses = @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = String.class)))))
    public Function<String, ResponseDTO> userProfile() {
        return id -> patientService.userProfile(id);
    }
}
