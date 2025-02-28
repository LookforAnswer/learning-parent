package com.qxy.learning.spring.factorybean;

import org.springframework.beans.factory.FactoryBean;

public class CarFactoryBean implements FactoryBean<Car> {

    /**
     * 通过逗号分隔符一次性创建 Car 所需要的属性
     */
    private String carInfo;

    public String getCarInfo() {
        return carInfo;
    }

    public void setCarInfo(String car) {
        this.carInfo = car;
    }

    @Override
    public Car getObject() throws Exception {
        Car car = new Car();
        String[] infos = carInfo.split(",");
        car.setBrand(infos[0]);
        car.setMaxSpeed(Integer.valueOf(infos[1]));
        car.setPrice(Double.valueOf(infos[2]));
        return car;
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
