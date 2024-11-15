package org.example;

import org.example.facade.BookingFacade;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        BookingFacade bookingFacade = context.getBean(BookingFacade.class);

        System.out.println("Application running!!!!");
    }
}