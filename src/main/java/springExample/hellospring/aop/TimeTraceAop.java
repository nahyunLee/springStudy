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
    //프록시가 생성되는걸 보고싶으면 .getClass해서 보면됨
    //EnharncerBySpringCGLIB --> 가 찍히면
    //CGLIB는 복제를 해서 코드를 조작하는 기술
}
