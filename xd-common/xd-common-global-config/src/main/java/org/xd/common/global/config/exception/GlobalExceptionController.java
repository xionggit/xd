package org.xd.common.global.config.exception;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MultipartException;
import org.xd.common.global.config.i18n.CoreMessageSource;

import lombok.extern.slf4j.Slf4j;

/** 
 * @Description: 统一异常处理
 * @author: xiong  
 * @GitConfig name: xionggit  email: shao200815@163.com
 * @date: 2017-10-30 10:56:34 
 */
@RestController
@ControllerAdvice
@Slf4j
public class GlobalExceptionController {

    @Autowired
    private CoreMessageSource messageSource;
    
    @ExceptionHandler(IOException.class)
    public ResponseEntity<String> handleIOException(IOException e) {
        log.info("服务器io异常: " + e.getMessage(),e);
        return new ResponseEntity<String>(messageSource.getMessage("ioException"),HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleServiceException(RuntimeException e) {
        
        log.info("服务器RuntimeException: " + e.getMessage(),e);

        return new ResponseEntity<String>(messageSource.getMessage("runTimeException"),HttpStatus.INTERNAL_SERVER_ERROR);

    }
    
    @ExceptionHandler(RuntimeServiceException.class)
    public ResponseEntity<String> handleServiceException(RuntimeServiceException e) {
        
        log.info("服务器RuntimeException: " + e.getMessage(),e);
        
        return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        
        log.info("服务器Exception: " + e.getMessage(),e);

        return new ResponseEntity<String>(messageSource.getMessage("exception"),HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<String> handleException(MissingServletRequestParameterException e) {
        
        log.info("服务器MissingServletRequestParameterException: " + e.getMessage(),e);

        return new ResponseEntity<String>(messageSource.getMessage("badRequestException"),HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> handleException(MethodArgumentTypeMismatchException e) {
        
        log.info("服务器MethodArgumentTypeMismatchException: " + e.getMessage(),e);
        
        return new ResponseEntity<String>(messageSource.getMessage("badRequestException"),HttpStatus.BAD_REQUEST);
    }

    /**  
     * hibernate 参数检查异常
     * @Title processValidationError  
     * @param e
     * @return Result<String>   
     */  
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> processValidationError(MethodArgumentNotValidException e) {
        
        BindingResult bindingResult = e.getBindingResult();
        String errorMessage = bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage)
                .reduce((msg, msg2) -> msg.concat(";").concat(msg2)).get();
        log.info("服务器MethodArgumentNotValidException: " 
                + e.getMessage(), e);
        return new ResponseEntity<String>(errorMessage, HttpStatus.BAD_REQUEST);
    }
    
    /**
    * 上传文件异常，例如超过上传文件大小限制。 
    * @Title handleMultipartException  
    * @param e
    * @return Result<String>
     */
    @ExceptionHandler(value = {MultipartException.class})
    public ResponseEntity<String> handleMultipartException(MultipartException e) {
        
        log.info("服务器MultipartException: " 
                + e.getMessage(), e );
        
        return new ResponseEntity<String>(messageSource.getMessage("uploadException"),HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
