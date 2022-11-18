package mx.com.undostres.otpservice.service.provider;

import mx.com.undostres.otpservice.models.Response;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


public abstract class OtpProvider {
   public abstract ResponseEntity<Response<Object>>  sendOtp(String mobile, String message, int login) ;
   public abstract ResponseEntity<Response<Object>> verifyOtp(String code, String errorMessage, int login);
    public abstract int getProviderCode();
}
