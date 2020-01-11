package com.test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class Apple {
     private Integer price;

//    public Apple(Integer price) {
//        this.price = price;
//    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public static void main(String[] args) throws Exception {
        // 正常的调用
        Apple apple = new Apple();
        apple.setPrice(15);
        System.out.println(apple.getPrice());
        // 使用反射调用
        // 获取Class对象实例
        Class clz = Class.forName("com.test.Apple");
        // 根据Class对象实例获取构造函数（Contructor）对象
        Constructor appleConstructor = clz.getConstructor();
        //获取方法的Method 对象
        Method setPriceMethod = clz.getMethod("setPrice", Integer.class);
        // 使用Constructor 对象的newInstance方法获取反射类对象
        Object appleObj = appleConstructor.newInstance();
        //利用invoke方法调用方法
        setPriceMethod.invoke(appleObj, 55);
        Method getPriceMethod = clz.getMethod("getPrice");
        System.out.println(getPriceMethod.invoke(appleObj));
    }

}
