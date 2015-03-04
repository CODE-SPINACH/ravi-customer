package com.app.tarun.dc2.Data;

import android.net.Uri;

/**
 * Created by Tarun on 28-02-2015.
 */
public class PrescriptionDetails {

    //The path to the image of the subscription
    private Uri imageUri;

    //Notes associated with the prescription
    private String notes;

    //Days of medicine : if explicitly specified
    private int days;

    //if daysTextView of medicine is meant to be as per given in the prescription
    public static String defaultMessage = "as\nspecified";

    public PrescriptionDetails(){
        imageUri = null;
        this.notes = "";
        days = 0;
    }


    public PrescriptionDetails(Uri imageUri, String notes) {
        this.imageUri = imageUri;
        this.notes = notes;
        days = 0;
    }


    public PrescriptionDetails(Uri imageUri, String notes, int days) {
        this.imageUri = imageUri;
        this.notes = notes;
        this.days = days;
    }

    public Uri getImageUri(){
        return imageUri;
    }

    public String getNotes(){
        return notes;
    }

    public int getDays(){
        return days;
    }

    public void setDays(int days){
        this.days = days;
    }

    public void setNotes(String notes){
        this.notes = notes;
    }
}
