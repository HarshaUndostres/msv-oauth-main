package mx.com.undostres.otpservice.utils;


import mx.com.undostres.otpservice.models.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class HttpUtils {
    public static <T> ResponseEntity<Response<T>> createResponseEntity(String msg, boolean success, T data, HttpStatus status) {
        return new ResponseEntity<Response<T>>(new Response<T>(msg, success, data), status);
    }
}
