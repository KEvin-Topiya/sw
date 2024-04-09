package com.example.sweet_wave.adapter;


public class orderStructure {
    int Total;
    String id,Address,Order,Date,Pay,Status,Uname;

    public orderStructure(int Total,String id,String Address,String Order,String Date,String Pay,String Status,String Uname){
        this.Address =Address;
        this.id=id;
        this.Order =Order;
        this.Date =Date;
        this.Pay =Pay;
        this.Status =Status;
        this.Uname = Uname;
        this.Total=Total;
    }
}
