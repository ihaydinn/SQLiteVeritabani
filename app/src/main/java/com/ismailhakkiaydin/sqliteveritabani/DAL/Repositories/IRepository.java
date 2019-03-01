package com.ismailhakkiaydin.sqliteveritabani.DAL.Repositories;

import android.content.Context;

import com.ismailhakkiaydin.sqliteveritabani.DAL.DbGateway;
import com.ismailhakkiaydin.sqliteveritabani.DAL.Entities.EntityBase;

import java.util.Vector;

public abstract class IRepository {

    protected Context context;
    protected DbGateway dbg;

    public IRepository(Context context){
        this.context = context;
        dbg = new DbGateway(context, "todo.db", null, 1);
    }

    //CRUD eylemleri

    public abstract long GetCount();
    public abstract boolean Add(EntityBase e);
    public abstract boolean Update(EntityBase e);
    public abstract boolean Delete(int id);
    public abstract EntityBase GetRecord(int id);
    public abstract Vector<EntityBase> GetResult();

}
