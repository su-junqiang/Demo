package com.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class ProductList extends Observable {
    private List<String> productList = null;//产品列表
    private static ProductList instance;//类唯一实例

    private ProductList() {
    }//构建方法私有化

    //取得唯一实例
    public static ProductList getInstance() {
        if (instance == null) {
            instance = new ProductList();
            instance.productList = new ArrayList<String>();
        }
        return instance;
    }

    //观察者电商接口
    private void addProductListObserver(Observer observer) {
        this.addObserver(observer);
    }

    //新增产品
    public  void addProduct(String newProduct) {
        productList.add(newProduct);
        System.out.println("产品列表新增了产品：" + newProduct);
        this.setChanged();//设置被观察对象发生变化
        this.notifyObservers(newProduct);//通知观察者并传递新对象
    }

    //京东电商接口
    public static class JdObserver implements Observer {

        @Override
        public void update(Observable o, Object product) {
            String newProduction = (String) product;
            System.out.println("发送新产品【" + newProduction + "】到京东");

        }
    }

    //淘宝电商接口
    public static class TaobaoObserver implements Observer {

        @Override
        public void update(Observable o, Object product) {
            String newProduction = (String) product;
            System.out.println("发送新产品【" + newProduction + "】到淘宝");

        }
    }
    public static void main(String[] args) {
        ProductList observable = ProductList.getInstance();
        TaobaoObserver taobaoObserver = new TaobaoObserver();
        JdObserver jdObserver = new JdObserver();
        observable.addObserver(jdObserver);
        observable.addObserver(taobaoObserver);
        observable.addProduct("sas");

    }
}
