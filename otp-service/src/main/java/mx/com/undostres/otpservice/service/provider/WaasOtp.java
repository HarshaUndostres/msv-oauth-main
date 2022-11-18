package mx.com.undostres.otpservice.service.provider;


import mx.com.undostres.otpservice.models.Response;
import org.springframework.http.ResponseEntity;

public class WaasOtp extends OtpProvider{


    @Override
    public ResponseEntity<Response<Object>>  sendOtp(String mobile, String message, int login) {
        return null;
    }

    @Override
    public ResponseEntity<Response<Object>> verifyOtp(String code, String errorMessage, int login) {
        return null;
    }

    @Override
    public int getProviderCode() {
        OtpData otpData = new OtpData();
        return otpData.WAAS_PROVIDER;
    }
}
