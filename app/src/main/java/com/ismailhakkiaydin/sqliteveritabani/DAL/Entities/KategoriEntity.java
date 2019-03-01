package com.ismailhakkiaydin.sqliteveritabani.DAL.Entities;

public class KategoriEntity extends EntityBase{

    private String Aciklama;

    public KategoriEntity(int id, String aciklama){
        super(id);
        Aciklama = aciklama;
    }

    public String getAciklama(){
        return Aciklama;
    }

    public void setAciklama(String aciklama){
        Aciklama = aciklama;
    }

}
