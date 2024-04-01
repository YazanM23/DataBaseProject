package com.example.secondloginpage;

public class EntityWareHouse {
    private String ID;
    private String Name;
    private String Amount;
    private String Date;
    private String Quantity;
    private String Type;
    public void  setID(String id){
        ID=id;
    }
    public void setName(String name){
        Name=name;
    }
    public void setType(String type){
        Type=type;
    }
    public void setAmount(String amount){
        Amount=amount;
    }
    public void setDate(String date){
        Date=date;
    }
    public void setQuantity(String quantity){
        Quantity= quantity;
    }
   public  String getName(){
        return Name;
   }
   public String getAmount(){
        return Amount;
   }
   public String getType(){
        return Type;
   }
   public String getDate(){
        return Date;
   }
   public String getQuantity(){
        return Quantity;
   }
    public String getID(){
        return ID;
    }
public EntityWareHouse(String id,String name,String type,String amount,String quantity,String date){
ID=id;
Name=name;
Amount=amount;
Quantity=quantity;
Date=date;
Type=type;
}



}
