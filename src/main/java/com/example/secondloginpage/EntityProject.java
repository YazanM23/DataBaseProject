package com.example.secondloginpage;

public class EntityProject {
    public EntityProject(){

    }
private String ID;
private String Name;
private String Type;
private String Owner;
private String Location;
private String Year;
private String Capacity;
private String Manufactoring_Date;

public void setID(String id){
    ID=id;
}
public  void setName(String name){
    Name=name;
}
public void setType(String type){
    Type=type;
}
public void setOwner(String owner){
    Owner=owner;
}
    public void setLocation(String location){
    Location=location;
    }
    public void setManufactoring_Date(String date){
    Manufactoring_Date=date;
    }
    public void setYear(String year){
    Year=year;
    }
    public void setCapacity(String capacity){
    Capacity=capacity;
    }

    public String getID(){
    return ID;
    }
    public String getName(){
    return Name;
    }
    public String getType(){
    return Type;
    }
    public String getManufactoring_Date(){
    return Manufactoring_Date;
    }
    public String getOwner(){
    return Owner;
    }
    public String getLocation(){
    return Location;
    }
    public String getYear(){
    return Year;
    }
    public String getCapacity(){
    return Capacity;
    }

    public EntityProject(String ID, String capacity, String location, String year, String type, String manufactoring_Date, String name, String owner) {
        this.ID = ID;
        Name = name;
        Type = type;
        Owner = owner;
        Location = location;
        Year = year;
        Capacity = capacity;
        Manufactoring_Date = manufactoring_Date;
    }
}
