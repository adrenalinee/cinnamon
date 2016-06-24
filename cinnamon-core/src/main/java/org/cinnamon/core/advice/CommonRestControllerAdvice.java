package org.cinnamon.core.advice;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.cinnamon.core.advice.vo.BadRequestInfo;
import org.cinnamon.core.advice.vo.InternalServerErrorInfo;
import org.cinnamon.core.advice.vo.InvalidParameterInfo;
import org.cinnamon.core.advice.vo.NotFoundInfo;
import org.cinnamon.core.advice.vo.UnauthorizedInfo;
import org.cinnamon.core.advice.vo.Violation;
import org.cinnamon.core.exception.BadRequestException;
import org.cinnamon.core.exception.NotFoundException;
import org.cinnamon.core.exception.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

/**
 * rest api 들에 대해서
 * response status 별로 에러 처리
 * 
 * @author 동성
 * @since 2014. 12. 15.
 */
@ControllerAdvice(annotations=RestController.class)
public class CommonRestControllerAdvice {
	Logger logger = LoggerFactory.getLogger(getClass());
	
	
	/**
	 * 401 에러 처리
	 * 
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler(UnauthorizedException.class)
	public ResponseEntity<UnauthorizedInfo> handleUnauthorizedException(UnauthorizedException ex, HttpServletRequest request) {
		logger.info("start");
		
		logger.error("요청 처리중 에러 발생", ex);
		
		UnauthorizedInfo unauthorizedInfo = new UnauthorizedInfo();
		unauthorizedInfo.setStatus(HttpStatus.UNAUTHORIZED.value());
		unauthorizedInfo.setError(HttpStatus.UNAUTHORIZED.getReasonPhrase());
		unauthorizedInfo.setTimestamp(System.currentTimeMillis());
		unauthorizedInfo.setException(ex.getClass().getName());
		unauthorizedInfo.setMessage(ex.getMessage());
		unauthorizedInfo.setPath(request.getRequestURI());
		unauthorizedInfo.setMethod(request.getMethod());
		
		
		ResponseEntity<UnauthorizedInfo> response = new ResponseEntity<UnauthorizedInfo>(unauthorizedInfo, HttpStatus.UNAUTHORIZED);
		return response;
	}
	
	
	/**
	 * 
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<BadRequestInfo> handler(BadRequestException ex, HttpServletRequest request) {
		logger.info("start");
		
		logger.error("요청 처리중 에러 발생", ex);
		
		BadRequestInfo badRequestInfo = new BadRequestInfo();
		badRequestInfo.setStatus(HttpStatus.BAD_REQUEST.value());
		badRequestInfo.setError(HttpStatus.BAD_REQUEST.getReasonPhrase());
		badRequestInfo.setTimestamp(System.currentTimeMillis());
		badRequestInfo.setException(ex.getClass().getName());
		badRequestInfo.setMessage(ex.getMessage());
		badRequestInfo.setPath(request.getRequestURI());
		badRequestInfo.setMethod(request.getMethod());
		
		
		ResponseEntity<BadRequestInfo> response = new ResponseEntity<BadRequestInfo>(badRequestInfo, HttpStatus.BAD_REQUEST);
		return response;
	}
	
	@ExceptionHandler(BindException.class)
	public ResponseEntity<List<ObjectError>> exceptionHandler(BindException ex, HttpServletRequest request) {
		logger.info("start");
		
		logger.error("요청 처리중 에러 발생", ex);
		
		
		BindingResult bindingResult = ex.getBindingResult();
		
		List<ObjectError> objectErrors =  bindingResult.getAllErrors();
		
		ResponseEntity<List<ObjectError>> response = new ResponseEntity<List<ObjectError>>(objectErrors, HttpStatus.BAD_REQUEST);
		return response;
	}
	
	
	/**
	 * 404 에러중 유효하지 않은 파라미터 처리
	 * 
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<InvalidParameterInfo> handler(MethodArgumentNotValidException ex, HttpServletRequest request) {
		logger.info("start");
		
		logger.error("요청 처리중 에러 발생", ex);
		
		InvalidParameterInfo invalidParameterInfo = new InvalidParameterInfo();
		invalidParameterInfo.setStatus(HttpStatus.BAD_REQUEST.value());
		invalidParameterInfo.setError(HttpStatus.BAD_REQUEST.getReasonPhrase());
		invalidParameterInfo.setTimestamp(System.currentTimeMillis());
		invalidParameterInfo.setException(ex.getClass().getName());
		invalidParameterInfo.setMessage(ex.getMessage());
		invalidParameterInfo.setPath(request.getRequestURI());
		invalidParameterInfo.setMethod(request.getMethod());
		
		
		List<ObjectError> errorList = ex.getBindingResult().getAllErrors();
		List<Violation> violations = new LinkedList<>();
		if (errorList != null) {
			for (ObjectError error: errorList) {
				String fieldName;
				
				if (error instanceof FieldError) {
					System.out.println("field");
					FieldError fieldError = (FieldError) error;
					fieldName = fieldError.getField();
				} else {
					fieldName = error.getCode();
				}
				
				String message = error.getDefaultMessage(); //TODO 향후 다국어 지원되도록 수정
				
				Violation violation = new Violation();
				violation.setParamName(fieldName);
				violation.setMessage(message);
				
				violations.add(violation);
			}
			
			invalidParameterInfo.setViolations(violations);
		}
		
		
		
		ResponseEntity<InvalidParameterInfo> response = new ResponseEntity<InvalidParameterInfo>(invalidParameterInfo, HttpStatus.BAD_REQUEST);
		return response;
	}
	
	
	/**
	 * 500 에러 처리
	 * 
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<InternalServerErrorInfo> handler(Exception ex, HttpServletRequest request) {
		logger.info("start");
		
		logger.error("요청 처리중 에러 발생", ex);
		
		InternalServerErrorInfo internalServerErrorInfo = new InternalServerErrorInfo();
		internalServerErrorInfo.setException(ex.getClass().getName());
		internalServerErrorInfo.setMessage(ex.getMessage());
		internalServerErrorInfo.setPath(request.getRequestURI());
		internalServerErrorInfo.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		internalServerErrorInfo.setError(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
		internalServerErrorInfo.setTimestamp(System.currentTimeMillis());
		internalServerErrorInfo.setMethod(request.getMethod());
		
		ByteArrayOutputStream bo = new ByteArrayOutputStream();
		PrintWriter pw = new PrintWriter(bo, true);
		ex.printStackTrace(pw);
		internalServerErrorInfo.setTrace(bo.toString());
		
		
		ResponseEntity<InternalServerErrorInfo> response = new ResponseEntity<InternalServerErrorInfo>(internalServerErrorInfo, HttpStatus.INTERNAL_SERVER_ERROR);
		return response;
	}
	
	
	/**
	 * 404 에러 처리
	 * 
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<NotFoundInfo> handler(NotFoundException ex, HttpServletRequest request) {
		logger.info("start");
		
		logger.error("요청 처리중 에러 발생", ex);
		
		NotFoundInfo notFoundInfo = new NotFoundInfo();
		notFoundInfo.setException(ex.getClass().getName());
		notFoundInfo.setMessage(ex.getMessage());
		notFoundInfo.setPath(request.getRequestURI());
		notFoundInfo.setStatus(HttpStatus.NOT_FOUND.value());
		notFoundInfo.setError(HttpStatus.NOT_FOUND.getReasonPhrase());
		notFoundInfo.setTimestamp(System.currentTimeMillis());
		notFoundInfo.setMethod(request.getMethod());
		
		
		ResponseEntity<NotFoundInfo> response = new ResponseEntity<NotFoundInfo>(notFoundInfo, HttpStatus.NOT_FOUND);
		return response;
	}
	
	
}
