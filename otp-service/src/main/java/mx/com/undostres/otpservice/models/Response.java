package mx.com.undostres.otpservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Response<T> {
    private String msg;
    private boolean success;
    private T data;
}
