package mx.com.undostres.otpservice.config;

import com.twilio.Twilio;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;

public class TwilioConfig {
    @Value("${twilio.account-sid}")
    private String twilioAccountSid;

    @Value("${twilio.auth-token}")
    private String twilioAuthToken;

    @Value("${twilio.api-key}")
    private String serviceId;

    @PostConstruct
    public void init() { //You have to ask Juan Manuel for a username and password for the account or else create a new one
       Twilio.init(
               "ACc48f6b9c06543ec9ab9d82946e35af18",//username
               "9f291f183b0ea44695768a0941e8494d",//pasw
               "VA2147c395e20f174d8722f4753c1a06a5"//service_id
        );

    }

    public String getTwilioAccountSid() {
        return twilioAccountSid;
    }

    public void setTwilioAccountSid(String twilioAccountSid) {
        this.twilioAccountSid = twilioAccountSid;
    }

    public String getTwilioAuthToken() {
        return twilioAuthToken;
    }

    public void setTwilioAuthToken(String twilioAuthToken) {
        this.twilioAuthToken = twilioAuthToken;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }
}
