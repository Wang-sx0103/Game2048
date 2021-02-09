package com.dome1;

public class TestPhone{
    public static void main(String[] args){
        Phone ph = new Phone();
        ph.brand = "Honor";
        ph.price = 2999;
        System.out.println(ph.brand+"-----"+ph.price);
        ph.call("张三");
        ph.sendMessage("罗翔");
    }
}