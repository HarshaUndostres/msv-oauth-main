package mx.com.undostres.otpservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.twilio.Twilio;
import lombok.extern.slf4j.Slf4j;
import mx.com.undostres.otpservice.config.TwilioConfig;
import mx.com.undostres.otpservice.models.Response;
import mx.com.undostres.otpservice.service.OtpServiceImpl;
import mx.com.undostres.otpservice.service.provider.*;
import mx.com.undostres.otpservice.service.OtpService;
import mx.com.undostres.otpservice.utils.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.twilio.rest.verify.v2.service.Verification;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.LinkedHashMap;

@Slf4j
@RestController
@RequestMapping("api/v1/otp/")
public class OtpController {

    @Autowired
    private OtpService otpService;


    @GetMapping(value = "otpOrder")
    public ResponseEntity<Response<Object>> otpOrder(){
        try {
            LinkedHashMap<String,String[]> otpOrder= otpService.getOtpOrder();
            if (otpOrder==null)
                return HttpUtils.createResponseEntity("Empty response", false, null, HttpStatus.NOT_FOUND);
            return HttpUtils.createResponseEntity("Success", true, otpOrder, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping(value = "sendOtp")
    public ResponseEntity<Response<Object>>  sendOtp(@RequestParam("phone") String phone, @RequestParam("message") String message, @RequestParam("login") int login){
        try {
            ResponseEntity<Response<Object>> otpServices= otpService.sendOtp(phone, message, login);
            if (otpServices.getStatusCode().is2xxSuccessful())
                return HttpUtils.createResponseEntity("Success", true, otpServices, HttpStatus.OK);
            return HttpUtils.createResponseEntity("Empty response", false, null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping(value = "verifyOtp")
    public ResponseEntity<Response<Object>>  verifyOtp(@RequestParam("phone") String phone, @RequestParam("message") String message, @RequestParam("login") int login){
        try {
            ResponseEntity<Response<Object>> otpServices= otpService.verifyOtp(phone, message, login);
            if (otpServices.getStatusCode().is2xxSuccessful())
                return HttpUtils.createResponseEntity("Success", true, otpServices, HttpStatus.OK);
            return HttpUtils.createResponseEntity("Empty response", false, null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("sendFirebaseOtp")
    public ResponseEntity<Response<Object>> sendFirebaseOtp(@RequestParam("phone") String phone, @RequestParam("message") String message, @RequestParam("login") int login)
    {
        FirebaseOtp firebaseOtp = new FirebaseOtp();
        OtpServiceImpl otpService1 = new OtpServiceImpl();
        return otpService1.sendOtpGeneric(phone,firebaseOtp,login);
    }

    @PostMapping("verifyFirebaseOtp")
    public ResponseEntity<Response<Object>> verifyFirebaseOtp(@RequestParam("phone") String phone, @RequestParam("code") String otp, @RequestParam("login") int login, @RequestParam("register") int register)
    {
        FirebaseOtp firebaseOtp = new FirebaseOtp();
        OtpServiceImpl otpService1 = new OtpServiceImpl();
        return otpService1.verifyOtpGeneric(otp,register,firebaseOtp,login);
    }



    @GetMapping("sendHuaweiOtp")
    public ResponseEntity<Response<Object>> sendHuaweiOtp(@RequestParam("phone") String phone, @RequestParam("message") String message, @RequestParam("login") int login)
    {
        HuaweiOtp huaweiOtp = new HuaweiOtp();
        OtpServiceImpl otpService1 = new OtpServiceImpl();
        return otpService1.sendOtpGeneric(phone,huaweiOtp,login,message,false);
    }
    @PostMapping("verifyHuaweiOtp")
    public  ResponseEntity<Response<Object>>  verifyHuaweiOtp(@RequestParam("phone") String phone, @RequestParam("code") String otp, @RequestParam("login") int login, @RequestParam("register") int register)
    {
        HuaweiOtp huaweiOtp = new HuaweiOtp();
        OtpServiceImpl otpService1 = new OtpServiceImpl();
        return otpService1.verifyOtpGeneric(otp,register,huaweiOtp,login);
    }


    @PostMapping(value = "sendTwilioOtp")
    public ResponseEntity<Response<Object>> sendTwilioOtp(@RequestParam("phone") String phone, @RequestParam("message") String message, @RequestParam("login") int login)
    {
        TwilioOtp twilioOtp = new TwilioOtp();
        OtpServiceImpl otpService1 = new OtpServiceImpl();
        return otpService1.sendOtpGeneric(phone,twilioOtp,login,message,false);
    }

    @PostMapping("verifyTwilioOtp")
    public ResponseEntity<Response<Object>> verifyTwilioOtp(@RequestParam("phone") String phone, @RequestParam("code") String otp, @RequestParam("login") int login, @RequestParam("register") int register)
    {
        TwilioOtp twilioOtp = new TwilioOtp();
        OtpServiceImpl otpService1 = new OtpServiceImpl();
        return otpService1.verifyOtpGeneric(otp,register,twilioOtp,login,phone,false);
    }



    @PostMapping("sendWaasOtp")
    public ResponseEntity<Response<Object>> sendWaasOtp(@RequestParam("phone") String phone, @RequestParam("message") String message, @RequestParam("login") int login)
    {
        WaasOtp waasOtp = new WaasOtp();
        OtpServiceImpl otpService1 = new OtpServiceImpl();
        return otpService1.sendOtpGeneric(phone,waasOtp,login);
    }

    @PostMapping("verifyWaasOtp")
    public ResponseEntity<Response<Object>> verifyWaasOtp(@RequestParam("phone") String phone, @RequestParam("code") String otp, @RequestParam("login") int login, @RequestParam("register") int register)
    {
        WaasOtp waasOtp = new WaasOtp();
        OtpServiceImpl otpService1 = new OtpServiceImpl();
        return otpService1.verifyOtpGeneric(otp,register,waasOtp,login);
    }


}


