package mx.com.undostres.otpservice.service;


import mx.com.undostres.otpservice.models.Response;
import mx.com.undostres.otpservice.service.provider.OtpProvider;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public interface OtpService {
    LinkedHashMap<String,String[]> getOtpOrder() throws Exception;
    ResponseEntity<Response<Object>> sendOtp(String mobile, String message, int login);
    ResponseEntity<Response<Object>> verifyOtp(String mobile,String code, int login);


    ResponseEntity<Response<Object>> sendOtpGeneric(String mobileForOtp,OtpProvider otpProvider,int login, String responseType,boolean ignoreDbCheck);
    ResponseEntity<Response<Object>> sendOtpGeneric(String mobileForOtp,OtpProvider otpProvider,int login);
    ResponseEntity<Response<Object>> verifyOtpGeneric(String code, int register, OtpProvider otpProvider, int login, String responseType, boolean ignoreDbCheck);
    ResponseEntity<Response<Object>> verifyOtpGeneric(String code, int register, OtpProvider otpProvider, int login);
}
