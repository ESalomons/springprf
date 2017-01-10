package springTutorial.aspect;

import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;

public class MethodTimer  {

	public Object timeMethod(ProceedingJoinPoint pjp) throws Throwable{
		System.out.println("==============================");
		System.out.println(pjp.toShortString());
		System.out.print("args:");
		for(Object o: pjp.getArgs()){
			System.out.println("\t" + o);
		}
		
		Date before = new Date();
		Object retValue = pjp.proceed();
		Date after = new Date();
		
		long time = after.getTime() - before.getTime();
		System.out.println("Execution time: " + time);
		System.out.println("==============================");
		return retValue;
	}

}
