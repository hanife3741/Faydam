package com.esogu.hanife.faydam;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
    EditText KullaniciAdi;
    EditText Sifre;
    Button Giris;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        KullaniciAdi = (EditText) findViewById(R.id.kullaniciAdi);
        Sifre = (EditText) findViewById(R.id.sifre);
        Giris = (Button) findViewById(R.id.giris);
        Giris.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final KullaniciVarMiInput KvMInput = new KullaniciVarMiInput();
                KvMInput.setKullaniciAdi(KullaniciAdi.getText().toString());
                KvMInput.setSifre(Sifre.getText().toString());
                class KullaniciAsyncTask extends AsyncTask<String, Void, String> {
                    public KullaniciVarMiInput input;
                    public boolean sonuc = false;

                    protected void onPostExecute(String result) {
                        Toast.makeText(getApplicationContext(), "Dogrulama sonucu : " + sonuc, Toast.LENGTH_LONG).show();
                    }
                    protected String doInBackground(String... params) {
                        WebServisCaller i = new WebservisCallerImpl();
                        sonuc = i.KullaniciVarMi(input);
                        return "";
                    }
                }
                KullaniciAsyncTask lt = new KullaniciAsyncTask();
                lt.input = KvMInput;
                lt.execute("");
            }
        });

    }
}
