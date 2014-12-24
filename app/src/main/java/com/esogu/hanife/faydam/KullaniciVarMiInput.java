package com.esogu.hanife.faydam;

/**
 * Created by ASUS on 23.12.2014.
 */
public class KullaniciVarMiInput {
    public class KullaniciAdiResult {
        public boolean KullaniciVarMiResult;
    }
    private String KullaniciAdi;
    private String Sifre;
    public String getKullaniciAdi() {
        return KullaniciAdi;
    }
    public String getSifre() {
        return Sifre;
    }
    public void setKullaniciAdi(String kullaniciAdi){
        this.KullaniciAdi=kullaniciAdi;
    }
    public void setSifre(String sifre) {
        this.Sifre=sifre;
    }
}
