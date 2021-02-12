package com.test1;
import com.dome1.*;
public class TestPhone{
    public static void main(String[] args){
        Phone ph = new Phone();
        ph.setBrand("Honor");
        ph.setPrice(2999);
        System.out.println("手机的型号是：" + ph.getBrand());
        System.out.println("手机的价格是：" + ph.getPrice());
        //ph.showInfo()
        /*ph.call("张三");
        ph.sendMessage("罗翔");*/
    }
}