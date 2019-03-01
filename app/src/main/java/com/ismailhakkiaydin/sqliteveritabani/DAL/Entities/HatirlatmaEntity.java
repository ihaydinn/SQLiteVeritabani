package com.ismailhakkiaydin.sqliteveritabani.DAL.Entities;


public class HatirlatmaEntity extends EntityBase{

    private String Metin;
    private int Kategori;
    private String Tarih;

    public HatirlatmaEntity(int id, String metin, int kategori, String tarih){
        super(id);
        Metin = metin;
        Kategori = kategori;
        Tarih = tarih;
    }

    public String getMetin(){
        return Metin;
    }

    public void setMetin(String metin){
        Metin = metin;
    }

    public int getKategori(){
        return Kategori;
    }

    public void setKategori(int kategori){
        Kategori = kategori;
    }

    public String getTarih(){
        return Tarih;
    }

    public void setTarih(String tarih){
        Tarih = tarih;
    }

}
