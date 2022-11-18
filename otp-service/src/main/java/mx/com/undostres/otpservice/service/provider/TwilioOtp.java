package mx.com.undostres.otpservice.service.provider;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.twilio.Twilio;
import com.twilio.exception.ApiException;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.ValidationRequest;
import com.twilio.rest.verify.v2.Service;
import com.twilio.rest.verify.v2.service.Verification;
import com.twilio.rest.verify.v2.service.VerificationCheck;
import com.twilio.type.PhoneNumber;
import mx.com.undostres.otpservice.config.TwilioConfig;
import mx.com.undostres.otpservice.models.Response;
import mx.com.undostres.otpservice.utils.HttpReqRespUtils;
import mx.com.undostres.otpservice.utils.HttpUtils;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.transaction.Transactional;
import java.net.InetAddress;
import java.time.LocalDateTime;
import java.util.Dictionary;
import java.util.Hashtable;

@Configuration
@PropertySource("classpath:application.properties")
public class TwilioOtp extends OtpProvider implements EnvironmentAware{
    static Environment environment;

    @Override
    public void setEnvironment(Environment environment) {
        TwilioOtp.environment = environment;
    }
    private final TwilioConfig twilioproperties;

  public TwilioOtp(TwilioConfig twilioproperties) {
        this.twilioproperties=twilioproperties;
    }

    public TwilioOtp() {
        this.twilioproperties=new TwilioConfig();
        twilioproperties.init();
    }


    //method to send to otp
    @Override
    public ResponseEntity<Response<Object>>  sendOtp(String mobile, String message, int login) {
        try {
            String serviceId = environment.getProperty("twilio.api-key");
            String countryCode = (HttpReqRespUtils.geoip_country_code_by_name().equals("IN")) ? "+91" : (HttpReqRespUtils.geoip_country_code_by_name().equals("MX"))? "+52":"+53";
            Verification verification = Verification.creator(serviceId,countryCode + mobile,"sms").create();
            System.out.println(verification.getStatus());
            if(verification.getStatus().equals("pending")){
                return HttpUtils.createResponseEntity(message, true, null, HttpStatus.OK);
            }
            return HttpUtils.createResponseEntity("Something went wrong",false,null,HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResponseEntity<Response<Object>> verifyOtp( String mobile,String code, int login) {
        try{
            String serviceId = environment.getProperty("twilio.api-key");
            String countryCode = (HttpReqRespUtils.geoip_country_code_by_name().equals("IN")) ? "+91" : (HttpReqRespUtils.geoip_country_code_by_name().equals("MX"))? "+52":"+53";
            VerificationCheck verificationCheck = VerificationCheck.creator(serviceId).setTo(countryCode+mobile).setCode(code).create();
            System.out.println(verificationCheck.getStatus());
            if(verificationCheck.getStatus().equals("approved")){
                return HttpUtils.createResponseEntity("SUCCESS", true,verificationCheck.getStatus(), HttpStatus.OK);
            }
            return HttpUtils.createResponseEntity("Something went wrong",false,null,HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getProviderCode() {
        OtpData otpData = new OtpData();
        return otpData.TWILIO_PROVIDER;
    }

}
