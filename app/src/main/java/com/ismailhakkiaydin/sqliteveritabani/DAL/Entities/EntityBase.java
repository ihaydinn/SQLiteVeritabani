package com.ismailhakkiaydin.sqliteveritabani.DAL.Entities;

public class EntityBase {

    private int ID;

    public EntityBase(){

    }

    public EntityBase(int id){
        this();
        ID = id;
    }

    public int getID(){
        return ID;
    }

    public void setID(int ID){
        this.ID = ID;
    }

}
