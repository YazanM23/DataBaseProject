package com.example.secondloginpage;

public class EntityEmployee {
public EntityEmployee(){

}
    private String SSN;
private String FName;
    private String LName;
    private String PNumber;
    private String SDate;
    private String EDate;
    private String Type;
    private String age;
    private String salary;
    private String Blod;
    private String City;
    private String Gender;

    public EntityEmployee(String fname,String lname,String SSN,String phone,String startDate,String endDate,String type,String age,String city,String bloodType,String salary,String gender) {
        this.SSN = SSN;
        FName = fname;
        LName = lname;
PNumber=phone;
EDate=endDate;
SDate=startDate;
Type = type;
        this.age = age;
        this.salary = salary;
        Blod = bloodType;
        Gender=gender;
        City=city;
    }

    public String getSSN() {
        return SSN;
    }
    public String getFName(){
        return FName;
    }

 public String getLName(){
        return LName;
 }

    public String getPNumber() {
        return PNumber;
    }

    public String getSDate() {
        return SDate;
    }

    public String getEDate() {
        return EDate;
    }

    public String getType() {
        return Type;
    }

    public String  getAge() {
        return age;
    }

    public String  getSalary() {
        return salary;
    }

    public String getBlod() {
        return Blod;
    }

public String getCity(){
        return City;
}
public String getGender(){
        return Gender;
}
public EntityEmployee(String fname){
        FName=fname;
}

}
