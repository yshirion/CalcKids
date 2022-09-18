package com.example.calackids;

import androidx.appcompat.app.AppCompatActivity;

public class CalcService extends AppCompatActivity {

    public static String setUserTitle(CalcKidsApplication app){
        String child;
        String parent = "";
        child = app.currentChildUser.getUser_name() + " id: " + app.currentChildUser.getId()
                + " Fid: " +app.currentChildUser.getFamily_id();
        if (app.currentParentUser != null) parent = ". by " + app.currentParentUser.getUser_name();

        return child + parent + ".";
    }
}
