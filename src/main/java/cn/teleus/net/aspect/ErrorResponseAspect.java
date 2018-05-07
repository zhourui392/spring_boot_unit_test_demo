package cn.teleus.net.aspect;

import cn.teleus.common.Root;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

/**
 * 切面检测参数验证错误，检测到错误后直接返回
 * @Author zhourui
 */
@Aspect
@Component
public class ErrorResponseAspect {
    private static final Logger logger = LoggerFactory.getLogger(ErrorResponseAspect.class);

    @Pointcut("execution(public * cn.teleus.net.controller..*.*(..))")
    public void paramsError(){}

    @Around("paramsError()")
    public Object doAroundAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        Object[] objects = proceedingJoinPoint.getArgs();
        if (objects != null){
            for (Object object : objects){
                if (object instanceof BindingResult){
                    BindingResult bindingResult = (BindingResult)object;
                    if (bindingResult.hasErrors()) {
                        return Root.getRootParamsError(bindingResult.getFieldError().getDefaultMessage()).toJsonString();
                    }
                }
            }
        }
        return proceedingJoinPoint.proceed();
    }
}