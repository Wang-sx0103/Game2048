package com.dome1;
//import java.lang.System.Logger

public class Phone{
    private String brand;
    private int price;
    //private String test
    public Phone(){}
    public Phone(String brand,int price){
        this.brand = brand;
        this.price = price;
    }
    public void setBrand(String brand){
        this.brand = brand;
    }
    public void setPrice(int price){
        this.price = price;
    }
    public String getBrand(){
        return brand;
    }
    public int getPrice(){
        return price;
    }
    public void showInfo(){
        System.out.println("手机的价格是："+this.price);
        System.out.println("手机的型号是："+this.brand);
    }
    public void call(String name){
        
        System.out.println(name);

    }
    public void sendMessage(String name){
        System.out.println(name);
    }
    
}