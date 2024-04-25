package com.qxy.learning.spring.customtag;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestMain {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("test/test.xml");
        User testBean = (User) classPathXmlApplicationContext.getBean("testbean");
        System.out.println(testBean.getUserName() + "," + testBean.getEmail());

    }
}
