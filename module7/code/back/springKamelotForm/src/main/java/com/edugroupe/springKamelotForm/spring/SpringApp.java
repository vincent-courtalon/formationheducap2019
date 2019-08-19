package com.edugroupe.springKamelotForm.spring;

import java.util.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.edugroupe.springKamelotForm.beans.Chevalier;
import com.edugroupe.springKamelotForm.beans.ChevalierTableRonde;
import com.edugroupe.springKamelotForm.beans.Quete;


public class SpringApp {

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		
        Scanner input = new Scanner(System.in);
        

        input.nextLine();
        System.out.println("--------------------------------------");
        
       //Quete qa = ctx.getBean("qb1", Quete.class);
       // System.out.println(qa);
        
        Chevalier c1 = ctx.getBean("lancelot", Chevalier.class);
        c1.partirEnQuete();
        //System.out.println(((ChevalierTableRonde)c1).getMonture());
        
        input.nextLine();
		System.out.println("--------------------------------------");
		
		Chevalier c2 = ctx.getBean("caradoc", Chevalier.class);
        c2.partirEnQuete();
        //System.out.println(((ChevalierTableRonde)c2).getMonture());
		
		

		System.out.println("done...");
	}





}
