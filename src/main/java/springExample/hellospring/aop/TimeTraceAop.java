package springExample.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component //compoent스캔되도록--> 아니면 spring config에서 직접 생성
public class TimeTraceAop {

    @Around("execution(* springExample.hellospring..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint);
        try {
            return joinPoint.proceed();//다음 메소드로 진행이됨
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: " + joinPoint.toShortString() + "" + timeMs + "ms");
        }
    }
}
