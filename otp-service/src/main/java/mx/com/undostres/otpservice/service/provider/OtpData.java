package mx.com.undostres.otpservice.service.provider;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.data.jpa.repository.Query;

public class OtpData {
    public final int ADMIN_PROVIDER = -1;
    public final int  FIREBASE_PROVIDER = 1;
    public final int  TWILIO_PROVIDER = 2;
    public final int  WAAS_PROVIDER = 3;
    public final int  HUAWEI_PROVIDER = 4;

    // Possible status values defined in tbl_dictionary
    public final int  OTP_SEND_FAILED = 1;
    public final int  OTP_SENT = 2;
    public final int  OTP_VERIFY_FAILED = 3;
    public final int  OTP_VERIFIED = 4;
}
