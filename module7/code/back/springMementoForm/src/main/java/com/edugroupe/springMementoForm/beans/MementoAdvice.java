package com.edugroupe.springMementoForm.beans;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;


public class MementoAdvice implements MethodInterceptor {

	private Map<Integer, Integer> cache;
	
	
	public MementoAdvice() {
		this.cache = new HashMap<Integer, Integer>();
	}
	
	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		System.out.println("appel de " + invocation.getMethod().getName());
		System.out.println("de la classe " + invocation.getThis().getClass().getName());
		System.out.println("avec les arguments " + Arrays.toString(invocation.getArguments()));
		// ais je déjà appelé fibonacci avec cette valeur? 
		if (cache.containsKey(invocation.getArguments()[0])) {
			// oui, renvoyer directement la valeur
			System.out.println("déjà en cache");
			return cache.get(invocation.getArguments()[0]);
		}
		else {
			System.out.println("non, on appele la méthode originale");
			Object returnValue =  invocation.proceed();
			cache.put((int)invocation.getArguments()[0], (int)returnValue);
			return returnValue;
		}
	}

}
