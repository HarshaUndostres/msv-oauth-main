package mx.com.undostres.otpservice.service.provider;

import mx.com.undostres.otpservice.models.Response;
import mx.com.undostres.otpservice.utils.HttpReqRespUtils;
import mx.com.undostres.otpservice.utils.HttpUtils;
import mx.com.undostres.otpservice.utils.StreamGobbler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class HuaweiOtp extends OtpProvider{
    @Override
    public ResponseEntity<Response<Object>>  sendOtp(String mobile, String message, int login) {
        try {
            String countryCode = (HttpReqRespUtils.geoip_country_code_by_name().equals("IN")) ? "+91" : (HttpReqRespUtils.geoip_country_code_by_name().equals("MX"))? "+52":"+53";
            String homeDirectory = System.getProperty("user.home");
            String cmd = "node Desktop/udt/msv-oauth-main/otp-service/src/main/resources/static/js/main.js "+countryCode+mobile+" send";
            System.out.println(cmd);
            System.out.println(homeDirectory);
//            ProcessBuilder pb = new ProcessBuilder("pwd");
//            Process process = pb.start();
//            System.out.println(process);
//            StringBuilder output = new StringBuilder();
//            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
//            String line = "";
//            while ((line = reader.readLine()) != null) {
//                output.append(line);
//                System.out.println(line);
//            }
//            int exitVal = process.waitFor();
//            if (exitVal == 0) {
//                System.out.println("**************************** The Output is ******************************");
//                System.out.println(output);
//                System.exit(0);
//            }
//            StreamGobbler streamGobbler = new StreamGobbler(process.getInputStream(), System.out::println);
//            Future<?> future = Executors.newSingleThreadExecutor().submit(streamGobbler);
//
//            int exitCode = process.waitFor();
//            assert exitCode == 0;
//
//            future.get();
            ScriptEngineManager sem = new ScriptEngineManager();
            List<ScriptEngineFactory> factories = sem.getEngineFactories();
            for (ScriptEngineFactory factory : factories)
                System.out.println(factory.getEngineName() + " " + factory.getEngineVersion() + " " + factory.getNames());
            if (factories.isEmpty())
                System.out.println("No Script Engines found");
//
//            ScriptEngine ee = new ScriptEngineManager().getEngineByName("Nashorn");
//            ee.eval(new FileReader(homeDirectory+"/Desktop/udt/msv-oauth-main/otp-service/src/main/resources/static/js/main.js"));
//            Invocable invocable = (Invocable)ee;
//            invocable.invokeFunction("otpfunction",countryCode,mobile,"send");
            return HttpUtils.createResponseEntity(message, true, null, HttpStatus.OK);
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }

    @Override
    public ResponseEntity<Response<Object>> verifyOtp(String code, String errorMessage, int login) {
        return null;
    }

    @Override
    public int getProviderCode() {
        OtpData otpData = new OtpData();
        return otpData.HUAWEI_PROVIDER;
    }
}
