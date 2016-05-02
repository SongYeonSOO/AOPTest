package com.estsoft.aoptest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component // bean 생성이 목적
@Aspect // proxy bean 생성이 목적
public class MyAspect {

	// advice정의
	// 어떤 ftn이 실행되기 전에 할 것인지 ftn의 위치를 직접 적어준다
	@Before("execution( public ProductVo com.estsoft.aoptest.ProductService.findProduct(String) )")
	public void before() {
		System.out.println("call [Before Advice]");
	}

	// 모든 return type과 모든 package에서 시작해서 class가 ProductService고, 그밑에 모든 method들
	@After("execution(* *..aoptest.ProductService.*(..) )")
	public void after() {
		System.out.println("call [After Advice]");
	}

	// before+after
	@Around("execution(* *..aoptest.ProductService.*(..) )")
	public ProductVo around(ProceedingJoinPoint pjp) throws Throwable {
		//before
		System.out.println("call [around.before Advice]");
		
		//App.java에서 service call을 하게 된 code부분 실행함
		ProductVo vo = (ProductVo) pjp.proceed();
		
		// after
		System.out.println("call [around.after Advice]");
		return vo;
	}

		//afterReturning ; 받을 변수 이름을 returning으로 쓴다.
		@AfterReturning(value="execution(* *..aoptest.ProductService.*(..) )", returning="vo")
		public void afterReturning(ProductVo vo) {
			System.out.println("call [afterReturning advice]"+vo);
		}
		
		//exception 발생 시,
		@AfterThrowing( value="execution(* *..aoptest.ProductService.*(..) )", throwing="ex" )
		public void afterThrowing( Throwable ex ) {
	//		System.out.println( "call [afterThrowing] : " + ex.toString() );
			System.out.println( "call [afterThrowing] : " + ex );
		}

		
}
