package mx.com.undostres.otpservice.models;


import java.sql.Timestamp;
import java.util.UUID;
import javax.persistence.*;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.hibernate.annotations.Type;

@Entity
@Table(name="otp")
@Data
@AllArgsConstructor(onConstructor = @__(@Autowired))
@NoArgsConstructor
@Setter
@Getter
public class Otp {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @Column(name = "session_id")
    private String  session_id;	//varchar(50)
    @Column(name = "mobile")
    private String  mobile;	//varchar(10)
    @Column(name = "provider")
    private Integer  provider;	//int(11)
    @Column(name = "created_at")
    private Timestamp  created_at;	//timestamp
    @Column(name = "updated_at")
    private Timestamp updated_at;	//timestamp
    @Column(name = "otp_send_response")
    private String   otp_send_response;	//mediumtext
    @Column(name = "otp_verify_response")
    private String  otp_verify_response;	//mediumtext
    @Column(name = "status")
    private Integer  status;	//int(11)
}
