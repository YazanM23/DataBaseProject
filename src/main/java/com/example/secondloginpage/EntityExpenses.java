package com.example.secondloginpage;

public class EntityExpenses {
    private String Name;
    private String Type;
    private String Amount;
    private String Date;
    private String Reason;
    private String Project_ID ;
    private String ID;

    public EntityExpenses(String id, String date, String name, String type, String reason, String amount, String project_ID) {
        Name = name;
        Type = type;
        Amount = amount;
        Date = date;
        Reason = reason;
        this.Project_ID = project_ID;
        ID = id;
    }

    public void setID(String id){
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
public void setReason(String reason){
        Reason=reason;
}
public void setProject_ID(String id){
        Project_ID=id;
}

public String  getID(){
        return ID;
}
public String getProject_ID(){
        return Project_ID;
}

public String getReason(){
        return Reason;
}

public String getDate(){
        return Date;
}
    public String getName(){
        return Name;
    }
    public String getType(){
        return Type;
    }
    public String getAmount(){
        return Amount;
    }



}
