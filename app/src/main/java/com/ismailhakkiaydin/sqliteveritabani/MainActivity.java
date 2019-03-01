package com.ismailhakkiaydin.sqliteveritabani;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.ismailhakkiaydin.sqliteveritabani.DAL.Entities.EntityBase;
import com.ismailhakkiaydin.sqliteveritabani.DAL.Entities.HatirlatmaEntity;
import com.ismailhakkiaydin.sqliteveritabani.DAL.Entities.KategoriEntity;
import com.ismailhakkiaydin.sqliteveritabani.DAL.Repositories.IRepository;
import com.ismailhakkiaydin.sqliteveritabani.DAL.Repositories.RepositoryContainer;
import com.ismailhakkiaydin.sqliteveritabani.PL.KategoriAdapter;

public class MainActivity extends AppCompatActivity {

    private LinearLayout pnlMain;
    private Spinner spnCategory, spnID;
    private EditText txtText, txtDate;
    private RepositoryContainer repositoryContainer;
    private IRepository repository;
    private boolean ins_upd_flg = false;
    private int cat_id =0, sel_id = 0;

    private void init(){
        repositoryContainer = RepositoryContainer.create(this);

        pnlMain= findViewById(R.id.pnlMain);
        spnCategory = findViewById(R.id.spnCategory);
        spnID = findViewById(R.id.spnID);
        txtText = findViewById(R.id.txtText);
        txtDate = findViewById(R.id.txtDate);
    }

    private void BuildCategories(){
        repository = (IRepository) repositoryContainer.getRepository("CATEGORY");
        //kategoriler deployment e eklenmesi
        if (repository.GetCount()==0){
            repository.Add(new KategoriEntity(0, "Toplanti"));
            repository.Add(new KategoriEntity(0, "Eğitim"));
            repository.Add(new KategoriEntity(0, "Ders"));
            repository.Add(new KategoriEntity(0, "Özel Gün"));
        }
    }

    //kategoriler spinner a ekleniyor
    public void BindToCategorySpinner(){
        repository = (IRepository) repositoryContainer.getRepository("CATEGORY");
        KategoriAdapter adp = new KategoriAdapter(this,repository.GetResult());
        spnCategory.setAdapter(adp);
    }

    //hatırlatmalar spiner a ekleniyor
    public void BindToHatirlatmaSpinner(){
        repository = (IRepository) repositoryContainer.getRepository("TODO");
        KategoriAdapter adp = new KategoriAdapter(this,repository.GetResult());
        spnCategory.setAdapter(adp);
    }

    //hatırlatma secimi
    private void spnID_Selection(){
        spnID.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                TextView tID = (TextView) view;
                sel_id = Integer.parseInt(tID.getText().toString());
                repository = (IRepository) repositoryContainer.getRepository("TODO");
                DisplayEntity(repository.GetRecord(sel_id));
                ins_upd_flg = false;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    //kategori secimi
    private void spnCategory_Selection(){
        spnCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                LinearLayout p = (LinearLayout) view;
                TextView tID = (TextView) p.getChildAt(0);
                cat_id = Integer.parseInt(tID.getText().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void registerHandlers(){
        spnID_Selection();
        spnCategory_Selection();
    }

    //hatırlatma entity nesnesinin ekranda gösterilemsi
    private void DisplayEntity(EntityBase e){
        HatirlatmaEntity he = (HatirlatmaEntity) e;

        txtDate.setText(he.getTarih());
        txtText.setText(he.getMetin());
        //spinnerda secili hale getirlimesi
        for (int i =0; i<=spnCategory.getCount()-1; i++){
            KategoriEntity itm = (KategoriEntity) spnCategory.getItemAtPosition(i);
            if (he.getKategori() == itm.getID()){
                spnCategory.setSelection(i);
                break;
            }
        }
    }

    private EntityBase GetEntityFromScreen(int id){
        HatirlatmaEntity he = new HatirlatmaEntity(id, txtText.getText().toString(),cat_id,txtDate.getText().toString());
        return he;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            init();
            BuildCategories();
            BindToCategorySpinner();
            BindToHatirlatmaSpinner();
            registerHandlers();
        }catch (Exception exc){
            Log.e("Activity:onCreate", exc.getMessage());
        }

    }

    //silme işlemi
    public void mnuDelete_Click(MenuItem m){
        if (!ins_upd_flg){
            repository.Delete(sel_id);
            BindToHatirlatmaSpinner();
        }
    }

    //insert update
    public void  mnuSave_Click(MenuItem m){
        repository = (IRepository) repositoryContainer.getRepository("TODO");
        if (ins_upd_flg){
            repository.Add(GetEntityFromScreen(0));
            BindToHatirlatmaSpinner();
        }else{
            repository.Update(GetEntityFromScreen(sel_id));
            BindToHatirlatmaSpinner();
        }
    }

    //ekran temizleme
    public void mnuClear_Click(MenuItem m){
        ins_upd_flg = true;
        spnCategory.setSelection(0);
        for (int i =0; i<=pnlMain.getChildCount()-1; i++){
            View v = pnlMain.getChildAt(i);
            if (v instanceof EditText){
                ((EditText) v).setText("");
            }
        }
    }


    public boolean onCreateOptionsMenu(Menu m){
        this.getMenuInflater().inflate(R.menu.menu_main, m);
        return true;
    }

}
