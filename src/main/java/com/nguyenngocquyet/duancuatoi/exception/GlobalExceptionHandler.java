package com.nguyenngocquyet.duancuatoi.exception;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.nguyenngocquyet.duancuatoi.dto.respon.ApiResponse;
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    ResponseEntity<ApiResponse> handRuntime(Exception exception) {
        ApiResponse apiRespon = new ApiResponse();
        apiRespon.setCode(ErrorCode.UNCATEGORIZED_EXCEPTION.getCode());
        apiRespon.setMessage(ErrorCode.UNCATEGORIZED_EXCEPTION.getMessage());

        return ResponseEntity.badRequest().body(apiRespon);
    }

//    @ExceptionHandler(value = RuntimeException.class)
//    ResponseEntity<ApiRespon> handleRuntimeException(RuntimeException exception) {
//        String message = exception.getMessage();
//
//        ApiRespon apiRespon = new ApiRespon();
//        apiRespon.setCode(1001);
//        apiRespon.setMessage(message);
//
//        return ResponseEntity.badRequest().body(apiRespon);
//    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<ApiResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {

        String enumKey = exception.getFieldError().getDefaultMessage();


        ErrorCode errorCode = ErrorCode.INVALID_KEY;
        try {
            errorCode = ErrorCode.valueOf(enumKey);
        }catch (IllegalArgumentException e) {

        }

        ApiResponse apiRespon = new ApiResponse();
        apiRespon.setCode(errorCode.getCode());
        apiRespon.setMessage(errorCode.getMessage());

        return ResponseEntity.badRequest().body(apiRespon);
    }

    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ApiResponse> handleAppException(AppException exception) {
        ErrorCode errorCode = exception.getErrorCode();
        ApiResponse apiRespon = new ApiResponse();
        apiRespon.setCode(errorCode.getCode());
        apiRespon.setMessage(errorCode.getMessage());

        return ResponseEntity.badRequest().body(apiRespon);

    }
}
