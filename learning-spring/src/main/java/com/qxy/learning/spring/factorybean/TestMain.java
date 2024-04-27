package com.qxy.learning.spring.factorybean;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestMain {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("bean-config/factorybean.xml");

        // 获取 工厂 bean 对应的 Car 实例
        Car testBean = (Car) classPathXmlApplicationContext.getBean("car");
        System.out.println(testBean.getBrand() + "," + testBean.getMaxSpeed()+ "," + testBean.getPrice());


        // 获取 CarFacotryBean 自身的实例，使用&，学过计算机的人dou
        CarFactoryBean carFactoryBean = (CarFactoryBean) classPathXmlApplicationContext.getBean("&car");
        System.out.println(carFactoryBean);
    }
}
