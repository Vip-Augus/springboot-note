package cn.sevenyuan.demo.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author JingQ at 2019-08-07
 */
@Aspect
@Component
public class BookAspect {

    /**
     * pointcut，指明拦截的路径
     * 第一个星号表示任意返回值
     * 第二个星号表示 aop 路径下任意类
     * 第三个星号表示类中的任意方法
     * (..) 表示任意参数
     */
    @Pointcut("execution(* cn.sevenyuan.demo.aop.BookService.*(..))")
    public void pc1() {
    }

    @Before(value = "pc1()")
    public void before(JoinPoint jp) {
        String name = jp.getSignature().getName();
        System.out.println(name + "方法开始执行");
    }

    @After(value = "pc1()")
    public void after(JoinPoint jp) {
        String name = jp.getSignature().getName();
        System.out.println(name + "方法结束执行");
    }

    /**
     * 返回值是 Object 类型，表示可以处理任意类型的返回值
     * 如果 result 类型是 Long，表示只能处理目标方法返回值是 Long 类型的情况
     * @param jp        连接点
     * @param result    结果
     */
    @AfterReturning(value = "pc1()", returning = "result")
    public void afterReturning(JoinPoint jp, Object result) {
        String name = jp.getSignature().getName();
        System.out.println(name + "方法返回值是 " + result);
    }

    @AfterThrowing(value = "pc1()", throwing = "e")
    public void afterThrowing(JoinPoint jp, Exception e) {
        String name = jp.getSignature().getName();
        System.out.println(name + "方法抛出异常，ex 是 " + e.getMessage());
    }

    @Around(value = "pc1()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("环绕前置执行");
        Object result = pjp.proceed();
        System.out.println("环绕后置执行");
        return result;
    }

}
