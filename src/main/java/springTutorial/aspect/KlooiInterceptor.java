package springTutorial.aspect;

import org.aspectj.lang.ProceedingJoinPoint;

public class KlooiInterceptor {
	private static int nr = 0;
	
	public Object klooi(ProceedingJoinPoint pjp) throws Throwable {
		Object retVal = null;
		if (pjp.getArgs()[0].equals("Piet") && nr == 0) {
			System.out.println("Piet moeten we niet!");
			Object[] args = pjp.getArgs();
			args[0] = "Arie";
			retVal = pjp.proceed(args);
			nr++;
		} else {
			System.out.println("Klooi voor " + pjp.getArgs()[0]);
			retVal = pjp.proceed();
		}

		return retVal;
	}
}
