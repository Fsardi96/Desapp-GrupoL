package ar.edu.unq.desapp.grupoL.backenddesappapi.Helpers;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CurrentDateTime {

    private CurrentDateTime() {
        //Empty Constructor
    }

    public static Date getNewDate(){
        return new Date();
        }

    public static String getNewDateString(){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return formatter.format(getNewDate());
    }
}


