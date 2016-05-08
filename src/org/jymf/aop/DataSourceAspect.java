package org.jymf.aop;

import java.math.BigDecimal;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.jymf.utils.DynamicDataSource;
import org.springframework.stereotype.Component;

@Aspect
@Component 
public class DataSourceAspect {

	@Before(value = "execution(* org.jymf.service.impl.*.*(..))") 
	public void beofre(JoinPoint jp) {
		Object[] args=jp.getArgs();
		BigDecimal workMode=new BigDecimal(-1);
		
		if(args.length>0){
			Object arg = args[args.length-1];
	        String name = arg.getClass().getName();
	        if(name.endsWith("BigDecimal")){
				try{
				    workMode = (BigDecimal)arg;
				}catch(Exception e){
				}
	        }
		}		
	    DynamicDataSource.setDataSource(workMode);

	  }
	
	@After(value = "execution(* org.jymf.service.impl.*.*(..))") 
	public void after(JoinPoint jp){
		DynamicDataSource.clearDataSource();
	}
}
