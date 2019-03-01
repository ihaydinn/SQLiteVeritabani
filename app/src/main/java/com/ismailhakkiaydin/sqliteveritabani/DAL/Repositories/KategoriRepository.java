package com.ismailhakkiaydin.sqliteveritabani.DAL.Repositories;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ismailhakkiaydin.sqliteveritabani.DAL.Entities.BlankEntity;
import com.ismailhakkiaydin.sqliteveritabani.DAL.Entities.EntityBase;
import com.ismailhakkiaydin.sqliteveritabani.DAL.Entities.KategoriEntity;

import java.lang.annotation.Target;
import java.util.Vector;

public class KategoriRepository extends IRepository {

    public static final String TABLE_NAME = " Kategoriler ";
    public static final String ID = "No";
    public static final String ACIKLAMA = "Aciklama";

    public KategoriRepository(Context context){
        super(context);
    }

    @Override
    public long GetCount(){
        //Kayıt sayıları alınır
        SQLiteDatabase db = super.dbg.getReadableDatabase();
        Cursor cur = db.rawQuery("Select * from " + TABLE_NAME, null);
        long r = cur.getCount();

        return r;
    }

    @Override
    public boolean Add(EntityBase e){
        //Kayıt ekleme
        KategoriEntity he = (KategoriEntity) e;
        SQLiteDatabase db = super.dbg.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(ACIKLAMA, he.getAciklama());
        long r = db.insert(TABLE_NAME,null,cv);
        db.close();
        if (r>0){
            return true;
        }else
            return false;
    }

    @Override
    public boolean Update(EntityBase e){
        //Güncelleme
        KategoriEntity he = (KategoriEntity) e;
        SQLiteDatabase db = super.dbg.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(ACIKLAMA, he.getAciklama());
        long r = db.update(TABLE_NAME, cv, ID + " = ?", new String[]{String.valueOf(he.getID())});
        db.close();
        if (r>0){
            return true;
        }else
            return false;
    }

    @Override
    public boolean Delete(int id){
        //Silme
        SQLiteDatabase db = super.dbg.getWritableDatabase();
        long r = db.delete(TABLE_NAME, ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
        if (r>0){
            return true;
        }else
            return false;
    }

    @Override
    public EntityBase GetRecord(int id){
        //Belirli bir kaydın alınması
        EntityBase entity = null;
        SQLiteDatabase db = dbg.getReadableDatabase();
        Cursor cur = db.rawQuery("Select * from " + TABLE_NAME + "where"+ ID + "= ?", new String[]{String.valueOf(id)});

        if (cur.moveToNext()){
            entity = new KategoriEntity(id, cur.getString(cur.getColumnIndex(ACIKLAMA)));
        }else {
            //Kayıt bulunamazsa
            entity = new BlankEntity();
        }
        return entity;

    }

    @Override
    public Vector<EntityBase> GetResult(){
        //Tüm kayıtların alınıp vektöre doldurularak döndürülmesi
        EntityBase entity = null;
        SQLiteDatabase db = dbg.getReadableDatabase();
        Cursor cur = db.query(TABLE_NAME, new String[]{ID, ACIKLAMA}," ",null," "," "," ");
        Vector<EntityBase> records = new Vector<EntityBase>(cur.getCount());
        while (cur.moveToNext()){
            entity = new KategoriEntity(cur.getInt(0),cur.getString(cur.getColumnIndex(ACIKLAMA)));
            records.add(entity);
        }
        return records;
    }

}
