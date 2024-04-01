package com.example.secondloginpage;

public class dateConverter {
    public dateConverter(){



    }
    public String Convert(String date){
        if(date.equals("1")){
          date ="jan";
        }
        if(date.equals("2")){
            date ="feb";
        }
        if(date.equals("3")){
            date ="mar";
        }
        if(date.equals("4")){
            date ="apr";
        }
        if(date.equals("5")){
            date ="may";
        }
        if(date.equals("6")){
            date ="jun";
        }
        if(date.equals("7")){
            date ="jul";
        }
        if(date.equals("8")){
            date ="aug";
        }
        if(date.equals("9")){
            date ="sep";
        }
        if(date.equals("10")){
            date ="oct";
        }
        if(date.equals("11")){
            date ="nov";
        }
        if(date.equals("12")){
            date ="dec";
        }
        return date;
    }
}
