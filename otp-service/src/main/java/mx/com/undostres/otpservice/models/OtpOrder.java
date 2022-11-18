package mx.com.undostres.otpservice.models;

import lombok.*;
import org.hibernate.annotations.Type;
import org.springframework.beans.factory.annotation.Autowired;
import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name="otp_order")
@Data
@AllArgsConstructor(onConstructor = @__(@Autowired))
@NoArgsConstructor
@Setter
@Getter
public class OtpOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String  name;	//varchar(16)
}
