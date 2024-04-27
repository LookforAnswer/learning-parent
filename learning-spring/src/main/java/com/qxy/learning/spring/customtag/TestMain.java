package com.qxy.learning.spring.customtag;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestMain {

    public static void main(String[] args) {
        // 加载解析xml配置，基于  BeanFactory 包装了 Context
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("bean-config/customtag.xml");
        // bean 的加载
        User testBean = (User) classPathXmlApplicationContext.getBean("testbean");

        System.out.println(testBean.getUserName() + "," + testBean.getEmail());

    }
}
