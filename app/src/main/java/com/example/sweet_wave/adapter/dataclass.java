package com.example.sweet_wave.adapter;

public class dataclass
{
    public  dataclass(){

    }
    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDec() {
        return dec;
    }

    public void setDec(String dec) {
        this.dec = dec;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    String img,Name,dec;
    int price;
    public  dataclass(String img,String Name,String dec,int price){
        this.img=img;
        this.Name=Name;
        this.dec=dec;
        this.price=price;
    }
}
