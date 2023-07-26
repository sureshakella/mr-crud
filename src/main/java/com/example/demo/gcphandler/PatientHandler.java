package com.example.demo.gcphandler;

import com.example.demo.model.ResponseDTO;
import org.springframework.http.HttpMethod;

//public class PatientHandler extends GcpSpringBootRequestHandler<Object, ResponseDTO> {
//
//    @FunctionName("userSave")
//    public ResponseDTO save(
//            @HttpTrigger(name = "userSaveRequest", methods = {HttpMethod.POST}, authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<Optional<User>> request,
//            ExecutionContext context) {
//        return handleRequest(request.getBody().get(), context);
//    }
//
//    @FunctionName("userGet")
//    public ResponseDTO get(
//            @HttpTrigger(name = "userGetRequest", methods = {HttpMethod.GET}, authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage request,
//            @BindingName("id") Integer id, ExecutionContext context) {
//        return handleRequest(id, context);
//    }
//
//    @FunctionName("userDelete")
//    public ResponseDTO delete(
//            @HttpTrigger(name = "userDeleteRequest", methods = {HttpMethod.DELETE}, authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage request,
//            @BindingName("id") Integer id, ExecutionContext context) {
//        return handleRequest(id, context);
//    }
//
//    @FunctionName("userUpdate")
//    public ResponseDTO update(
//            @HttpTrigger(name = "userUpdateRequest", methods = {HttpMethod.PUT}, authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<Optional<User>> request,
//            ExecutionContext context) {
//        return handleRequest(request.getBody().get(), context);
//    }
//
//    @FunctionName("userProfile")
//    public ResponseDTO profile(
//            @HttpTrigger(name = "userProfileRequest", methods = {HttpMethod.GET}, route = "profile/{id}", authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage request,
//            @BindingName("id") String id, ExecutionContext context) {
//        return handleRequest(id, context);
//    }
//}