package com.xmpy.demo.controller.advice;


import com.xmpy.demo.exeption.ProductException;
import com.xmpy.demo.exeption.UserException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


// 컨트롤러에서 예외가 발생했을 때, 예외처리기 만들기
// 컨트롤러에서 예외 발생 -> catch X -> DispatchServlet이 낚아챔 -> @RestController클래스 찾아서 -> 핸들러 실행
@RestControllerAdvice
public class GlobarExceptionHandler {

    @ExceptionHandler(UserException.class)
    public ResponseEntity<?> handleUserException(UserException e){
        return ResponseEntity
                .status(e.getStatus())
                .body(e.getMessage());
    }

    @ExceptionHandler(ProductException.class)
    public ResponseEntity<?> handleProductException(ProductException e){
        return ResponseEntity
                .status(e.getStatus())
                .body(e.getMessage());
    }


}

