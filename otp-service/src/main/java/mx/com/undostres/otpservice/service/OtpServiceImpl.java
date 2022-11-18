package mx.com.undostres.otpservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.com.undostres.otpservice.dao.OtpDao;
import mx.com.undostres.otpservice.models.OtpOrder;
import mx.com.undostres.otpservice.models.Response;
import mx.com.undostres.otpservice.service.provider.OtpProvider;
import mx.com.undostres.otpservice.service.provider.TwilioOtp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Pattern;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class OtpServiceImpl implements OtpService{

    @Autowired
    private OtpDao otpDao;


    @Override
    public LinkedHashMap<String,String[]> getOtpOrder() throws Exception {

  LinkedHashMap<String,String[]> cof=new LinkedHashMap<>();
  cof.put("huawei",new String[]{"huawei", "requestHuaweiOtp", "verifyHuaweiOtp", "mobileforotp", "codigo", "pedir", "verificar"});
        cof.put("firebase",new String[]{"firebase","firebaseCallAx","firebaseCodeCall","mobileforotp","firebasecodigo","pedir","verificar"});
        cof.put("waas",new String[]{"waas", "requestWaasOtp", "verifyWaasOtp", "mobileforotp", "codigo", "pedir", "verificar"});
        cof.put("twilio",new String[]{"twillio","sendTwilioAjaxPaymentDesk","verifyTwilioAjaxPaymentDesk","mobileforotp","codigo","pedir","verificar"});
        LinkedHashMap<String,String[]> result=new LinkedHashMap<>();
        List<String> names= otpDao.getOtpOrder();
        for (String a:
             names) {
            result.put(a,cof.get(a));
        }
        return result;
    }

    @Override
    public ResponseEntity<Response<Object>> sendOtp(String mobile, String message, int login)  {
        try {

            //Twilio

            //Do logic here
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public ResponseEntity<Response<Object>> verifyOtp(String mobile, String code, int login) {
        try {

            //Twilio

            //Do logic here
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }


    @Override
    public ResponseEntity<Response<Object>> sendOtpGeneric(String mobileForOtp, OtpProvider otpProvider, int login, String responseType, boolean ignoreDbCheck) {
        return otpProvider.sendOtp(mobileForOtp,responseType,login);
    }

    @Override
    public ResponseEntity<Response<Object>> sendOtpGeneric(String mobileForOtp, OtpProvider otpProvider, int login) {
        return sendOtpGeneric(mobileForOtp, otpProvider, login, "respond" , false);
    }

    @Override
    public ResponseEntity<Response<Object>> verifyOtpGeneric(String code, int register, OtpProvider otpProvider, int login, String responseType, boolean ignoreDbCheck) {
        return otpProvider.verifyOtp(responseType,code,login);
    }

    @Override
    public ResponseEntity<Response<Object>> verifyOtpGeneric(String code, int register, OtpProvider otpProvider, int login) {
        return verifyOtpGeneric(code, register, otpProvider, login, "respond", false);
    }

}
