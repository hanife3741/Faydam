package com.esogu.hanife.faydam;

/**
 * Created by ASUS on 23.12.2014.
 */
public class KullaniciVarMiInput {
    public class KullaniciAdiResult {
        public boolean KullaniciVarMiResult;
    }
    private String kullaniciAd;
    private String sifre;
    public String getKullaniciAdi() {
        return kullaniciAd;
    }
    public String getSifre() {
        return sifre;
    }
    public void setKullaniciAdi(String kullaniciAdi){
        this.kullaniciAd=kullaniciAdi;
    }
    public void setSifre(String sifre) {
        this.sifre=sifre;
    }
}
