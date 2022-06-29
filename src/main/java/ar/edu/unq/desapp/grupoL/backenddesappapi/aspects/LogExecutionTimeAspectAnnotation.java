package ar.edu.unq.desapp.grupoL.backenddesappapi.aspects;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.time.Instant;
@Aspect
@Slf4j
@Component
public class LogExecutionTimeAspectAnnotation {

	@Pointcut("within( ar.edu.unq.desapp.grupoL.backenddesappapi.webservice.*)")
	public void controller() { /* Comment added to avoid SonarCloud marking this empty method as a code smell */ }

	@Pointcut("execution(* *.*(..))")
	protected void allMethods() { /* Comment added to avoid SonarCloud marking this empty method as a code smell */ }

	@Around("controller() && allMethods()")
	public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
		Timestamp date = Timestamp.from(Instant.now());

		HttpServletRequest httpRequest = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		String httpMethod = httpRequest.getMethod();
		String requestURI = httpRequest.getRequestURI();

		Long startTime = System.currentTimeMillis();
		Object objectToReturn = joinPoint.proceed();
		Long executionTime = System.currentTimeMillis() - startTime;

		Signature signature = joinPoint.getSignature();
		String arguments = new ObjectMapper().writeValueAsString(joinPoint.getArgs());

		dataMethod(date, executionTime, httpMethod, requestURI, signature, arguments);

		return objectToReturn;
	}

	private void dataMethod(Timestamp timeStamp, Long executionTime,
							String httpMethod, String requestURI,
							Signature signature, String arguments) {
		log.info("Date: " + timeStamp + " Action: " + httpMethod + " " + requestURI) ;
		log.info("Method: "  + signature);
		log.info("Arguments: " + arguments + " | executed in " + executionTime + " ms");
	}
}