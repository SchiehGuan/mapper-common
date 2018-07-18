package com.hfocean.common.mapper.web.controller;

import com.hfocean.common.mapper.exception.BaseBusinessError;
import com.hfocean.common.mapper.exception.BaseBusinessException;
import com.hfocean.common.mapper.web.WebResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;


/*
 * 通用异常处理抽象类
 * 具体业务继承该类并@ControllerAdvice
 */
public abstract class BaseControllerAdvice extends ResponseEntityExceptionHandler {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
    @ExceptionHandler(BaseBusinessException.class)
    @ResponseBody
    public ResponseEntity<?> handleControllerException(HttpServletRequest request, Throwable ex) {
        BaseBusinessException exception = (BaseBusinessException)ex;
        WebResponse webResponse = new WebResponse(exception);
        return new ResponseEntity<>(webResponse, HttpStatus.OK);
    }

    @ExceptionHandler({IllegalArgumentException.class})
    @ResponseBody
    public ResponseEntity<?> handleIllegalArgumentException(HttpServletRequest request, Throwable ex) {
        HttpStatus status = getStatus(request);
        IllegalArgumentException exception = (IllegalArgumentException)ex;
        WebResponse webResponse = new WebResponse(BaseBusinessError.PARAMETER_ERROR.getCode(), exception.getMessage());
        return new ResponseEntity<>(webResponse, status);
    }
    
    @ExceptionHandler({RuntimeException .class})
    @ResponseBody
    public ResponseEntity<?> handleRuntimeException(HttpServletRequest request, Throwable ex) {
		logger.error("runtime error", ex);
		HttpStatus status = getStatus(request);
		WebResponse webResponse = new WebResponse(BaseBusinessError.INTER_ERROR.getCode(),BaseBusinessError.INTER_ERROR.getMessage());
        return new ResponseEntity<>(webResponse, status);
    }
    
    @ExceptionHandler({Exception .class})
    @ResponseBody
    public ResponseEntity<?> handleException(HttpServletRequest request, Throwable ex) {
		logger.error("runtime error", ex);
		HttpStatus status = getStatus(request);
		String name = ex.getClass().getName();
		WebResponse webResponse = null;
		if(name!=null&&name.contains("HSFServiceAddressNotFoundException")){
			webResponse = new WebResponse(BaseBusinessError.SERVICE_ERROR.getCode(),BaseBusinessError.SERVICE_ERROR.getMessage());
		}else{
		 webResponse = new WebResponse(BaseBusinessError.INTER_ERROR.getCode(),BaseBusinessError.INTER_ERROR.getMessage());
		}
        return new ResponseEntity<>(webResponse, status);
    }
    
	@Override	
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,HttpHeaders headers,HttpStatus status,WebRequest request)  {
		logger.error("argument not valid error", ex);
		Collection<FieldError> errors = ex.getBindingResult().getFieldErrors();
		//显示第一个返回对象字段的错误
		WebResponse webresponse = new WebResponse(BaseBusinessError.PARAMETER_ERROR.getCode(),errors.iterator().next().getDefaultMessage());
		return  new ResponseEntity<Object>(webresponse,headers,status);
    }
	
	@Override	
	protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex,HttpHeaders headers,HttpStatus status,WebRequest request)  {
		logger.error("missing path error", ex);
		WebResponse webresponse = new WebResponse(BaseBusinessError.PARAMETER_ERROR.getCode(),"missing path variable " + ex.getVariableName());
		return  new ResponseEntity<Object>(webresponse,headers,status);
    }
	
	@Override	
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request){
		logger.error("", ex);
		//显示第一个返回对象字段的错误
		WebResponse webresponse = new WebResponse(String.valueOf(status.value()),ex.getMessage());
		return  new ResponseEntity<Object>(webresponse,headers,status);
	}

    protected HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }

}