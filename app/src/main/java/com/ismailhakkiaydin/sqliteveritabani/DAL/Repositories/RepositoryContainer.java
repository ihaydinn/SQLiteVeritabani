package com.ismailhakkiaydin.sqliteveritabani.DAL.Repositories;

import android.content.Context;

import java.util.ArrayList;

public class RepositoryContainer {

    private static RepositoryContainer rc = null;
    private Context context;

    private ArrayList<IRepository> repositories ;

    private RepositoryContainer(Context context){
        repositories = new ArrayList<IRepository>();
        this.context = context;
    }

    public static RepositoryContainer create(Context context){
        if(rc == null){
            rc = new RepositoryContainer(context);
        }
        return rc;
    }

    //repository adına göre nesne döndüren factory
    public IRepository getRepository(int repName){
        IRepository ir = null;
        switch (repName){
            case RepositoryNames.TODO:
                ir = new HatirlatmaRepository(context);
                break;
            case RepositoryNames.CATEGORY:
                ir = new KategoriRepository(context);
                break;
        }
        return ir;
    }

    public Object getRepository(String category) {
        return category;
    }
}
