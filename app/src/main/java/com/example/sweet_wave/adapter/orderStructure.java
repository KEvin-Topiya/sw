package com.example.sweet_wave.adapter;


public class orderStructure {
    int Total;
    String id,Address,Order,Date,Pay,Status,Uname,phone;

    public orderStructure(int Total,String id,String Address,String Order,String Date,String Pay,String Status,String Uname,String phone){
        this.Address =Address;
        this.id=id;
        this.Order =Order;
        this.Date =Date;
        this.Pay =Pay;
        this.Status =Status;
        this.phone =phone;
        this.Uname = Uname;
        this.Total=Total;
    }
}
