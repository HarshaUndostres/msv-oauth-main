package mx.com.undostres.otpservice.service.provider;

import mx.com.undostres.otpservice.models.Response;
import mx.com.undostres.otpservice.service.OtpService;
import mx.com.undostres.otpservice.utils.HttpUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FirebaseOtp extends OtpProvider{

    @Autowired
    private OtpService otpService;
    @Override
    public ResponseEntity<Response<Object>>  sendOtp(String mobile, String message, int login) {
        return HttpUtils.createResponseEntity(message, true, null, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Response<Object>> verifyOtp(String code, String errorMessage, int login) {
        return null;
    }

    @Override
    public int getProviderCode() {
        OtpData otpData = new OtpData();
        return otpData.FIREBASE_PROVIDER;
    }



    public ResponseEntity<Response<Object>> Firebaseverificationid(String otp_id,String verificationId){
        //If otp_id arrives null, it must be taken from the session
        return null;
    }

    public JSONObject firebasevid(){
        return null;
    }

}
