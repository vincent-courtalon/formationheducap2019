package com.edugroupe.springjdbcForm.beans;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import com.edugroupe.springjdbcForm.metier.Produit;

public class CacheAdvice implements MethodInterceptor {

	private Map<Integer, Produit> cacheProduit;

	private List<Produit> cacheList;
	private LocalDateTime lastListTime;
	
	public CacheAdvice() {
		cacheProduit = new HashMap<>();
		cacheList = null;
		lastListTime = null;
	}
	
	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		System.out.println("appel de " + invocation.getMethod().getName());
		System.out.println("avec arguments " + Arrays.toString(invocation.getArguments()));
		
		if (invocation.getMethod().getName().equals("findById")) {
			if (cacheProduit.containsKey(invocation.getArguments()[0])) {
				System.out.println("déjà en cache");
				return cacheProduit.get(invocation.getArguments()[0]);
			}
			else {
				System.out.println("pas encore en cache");
				Object returnValue = invocation.proceed();
				cacheProduit.put((int)invocation.getArguments()[0], (Produit)returnValue);
				return returnValue;
			}
		}
		else {
			if (	cacheList != null 
				&& lastListTime.until(LocalDateTime.now(), ChronoUnit.SECONDS) < 5) {
				System.out.println("liste en cahce depuis moins de 5 secondes");
				return cacheList;
			}
			else {
				System.out.println("pas de liste en cache ou expiré (+ de 5 secondes)");
				cacheList = (List<Produit>)invocation.proceed();
				lastListTime = LocalDateTime.now();
				return cacheList;
			}
			
		}
		
		
	}

}
