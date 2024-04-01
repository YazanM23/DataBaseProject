package com.example.secondloginpage;

public class EntityPurchases {

private String Name;
private String Type;
private String Quantity;
private String Date;
private String Amount;

    public EntityPurchases(String name, String amount, String quantity, String date, String type) {
        Name = name;
        Type = type;
        Quantity = quantity;
        Date = date;
        Amount = amount;
    }

    public void setName(String name){
    Name=name;
}
    public void setType(String type){
       Type=type;
    }
    public void setQuantity(String quantity){
     Quantity=quantity;
    }
    public void setDate(String date){
        Date=date;
    }



    public String getName(){
    return Name;
    }
    public String getType(){
    return Type;
    }
    public String getQuantity(){
    return Quantity;
    }
    public  String getDate(){
    return Date;
    }
    public  String getAmount(){return Amount;}
}
