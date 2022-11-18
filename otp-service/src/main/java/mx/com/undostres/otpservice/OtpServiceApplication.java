package mx.com.undostres.otpservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
//@SpringBootApplication(scanBasePackages={"mx.com.undostres","mx.com.undostres"})
//@ComponentScan({"mx.com.undostres.otpservice", "mx.com.undostres.otpservice.service.provider"})
@ComponentScan({"mx.com.undostres.otpservice","mx.com.undostres.otpservice.service.provider"})
public class OtpServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OtpServiceApplication.class, args);
	}

}
