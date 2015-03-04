package com.app.tarun.dc2.Data;

/**
 * Created by Tarun on 28-02-2015.
 *
 * Cost hasnt been incorporated yet neither in
 * constructor nor in getters
 */
public class MedicineDetails {

    public enum MedicineTypes{
        Tablets,Strips,Bottles
    }

    private String nameOfMedicine;
    private MedicineTypes medicineType;

    private int quantity;

    //TO DO : not included as of now the cost
    private float cost;

    public MedicineDetails(String name,MedicineTypes type,int quantity){
        nameOfMedicine = name;
        medicineType = type;
        this.quantity = quantity;
    }

    public String getMedicineName(){
        return nameOfMedicine;
    }

    public MedicineTypes getMedicineType(){
        return medicineType;
    }

    public int getQuantity(){
        return quantity;
    }


}
