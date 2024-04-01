package com.example.secondloginpage;

public class Validation {
    public Validation() {
    }

    public boolean validationNumber(String number){
        char []r=number.toCharArray();
        for(int i=0;i<number.length();i++){
            if(!Character.isDigit(r[i])){
                return false;
            }
        }
        return true;
    }

    public boolean validationName(String name){
        char []r=name.toCharArray();
        for (int i=0;i<name.length();i++){
            if(Character.isDigit(r[i])){
                return false;
            }
        }
        return true;
    }
    public boolean validationSSN(String ssn){
        if(ssn.length()!=9){
            return false;
        }
       if(!validationNumber(ssn)) {
           return false;
       }
        return true;
    }
public boolean validationBlod(String blod){
        if(!(blod.equalsIgnoreCase("A+")||blod.equalsIgnoreCase("AB+")||blod.equalsIgnoreCase("A-")||blod.equalsIgnoreCase("B+")||blod.equalsIgnoreCase("AB-")||blod.equalsIgnoreCase("B-")||blod.equalsIgnoreCase("O-")||blod.equalsIgnoreCase("O+"))){
            return false;
        }
        return true;
}
public boolean validationPhoneNumber(String phonenumber){

        if(phonenumber.length()!=10){
            return false;
        }
    if(!validationNumber(phonenumber)) {
        return false;
    }
        return true;

}
public boolean validationGender(String gender){
        if(!(gender.equalsIgnoreCase("Male")||gender.equalsIgnoreCase("Female")||gender.equalsIgnoreCase("M")||gender.equalsIgnoreCase("F"))){
            return false;
    }
        return true;
}


}
