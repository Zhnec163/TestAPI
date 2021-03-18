package org.example.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

public class Store{

    @SerializedName("id")
    private int id;

    @SerializedName("petId")
    private int petId;

    @SerializedName("quantity")
    private int quantity;

    @SerializedName("shipDate")
    private Date shipDate;

    @SerializedName("status")
    private String status;

    @SerializedName("complete")
    private boolean complete;

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public void setPetId(int id){
        this.petId = id;
    }

    public int getPetId(){
        return petId;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    public int getQuantity(){
        return quantity;
    }

    public void setShipDate(Date shipDate){
        this.shipDate = shipDate;
    }

    public Date getShipDate(){
        return shipDate;
    }

    public void setStatus(String status){
        this.status = status;
    }

    public String getStatus(){
        return status;
    }

    public void setComplete(boolean complete){
        this.complete = complete;
    }

    public boolean getComplete(){
        return complete;
    }
}