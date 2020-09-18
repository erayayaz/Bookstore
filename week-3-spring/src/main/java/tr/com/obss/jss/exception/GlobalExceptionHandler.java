package tr.com.obss.jss.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import tr.com.obss.jss.controller.UserController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler   {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<?> handleRuntimeException(HttpServletRequest req,ArithmeticException t){
        LOGGER.error(t.getMessage(),t);
        Map<String,String> hashmap = new HashMap<>();
            hashmap.put("error","Aritmetik bir hata oluştu");
        return new ResponseEntity<>(hashmap, HttpStatus.INTERNAL_SERVER_ERROR);

    }
    @ExceptionHandler(Throwable.class)
    public ResponseEntity<?> handleRuntimeException(HttpServletRequest req,Throwable t){
        LOGGER.error(t.getMessage(),t);
        Map<String,String> hashmap = new HashMap<>();
        hashmap.put("error","Bilinmeyen bir hata oluştu");
        return new ResponseEntity<>(hashmap, HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
