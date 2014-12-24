package com.esogu.hanife.faydam;

import com.google.gson.Gson;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

/**
 * Created by ASUS on 23.12.2014.
 */
public class WebservisCallerImpl implements WebServisCaller {






    private static  String NAMESPACE=null;
    private static  String SERVIS_URL=null;
    private static  String DOGRULA_METHOD=null;
    private static  String SOAP_DOGRULA_ACTION=null;
    public boolean KullaniciVarMi(KullaniciVarMiInput input){


        //properties nesnesini oluştur
        Properties prop = new Properties();
        //properties değerlerini /src/main/assets/app.properties dosyasından stream e oku
        InputStream inputStream = WebservisCallerImpl.class.getClassLoader().getResourceAsStream("assets/app.properties");
        try {//streamden properties nesnesine değerleri yükle
            prop.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        NAMESPACE =  prop.getProperty("NAMESPACE");
        SERVIS_URL =  prop.getProperty("SERVIS_URL");
        DOGRULA_METHOD =  prop.getProperty("DOGRULA_METHOD");
        NAMESPACE =  prop.getProperty("NAMESPACE");
        SOAP_DOGRULA_ACTION=NAMESPACE+DOGRULA_METHOD;

        SoapObject request = new SoapObject(NAMESPACE, DOGRULA_METHOD);
        request.addProperty("KullaniciAdi", input.getKullaniciAdi());
        request.addProperty("Sifre",input.getSifre());
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER12);
        envelope.dotNet=true;
        //envelope.avoidExceptionForUnknownProperty = true;
        envelope.encodingStyle=SoapEnvelope.ENC;
        envelope.setAddAdornments(false);
        envelope.implicitTypes=false;
        envelope.setOutputSoapObject(request);
        HttpTransportSE androidHttpTransport=new HttpTransportSE(SERVIS_URL);
        try {
            androidHttpTransport.call(SOAP_DOGRULA_ACTION,envelope);
            if(envelope.bodyIn instanceof SoapObject){
                SoapObject soapObject=(SoapObject) envelope.bodyIn;
                KullaniciVarMiInput kullaniciVarMiResult=new Gson().fromJson((String) soapObject.getProperty(0),KullaniciVarMiInput.class);

                return (kullaniciVarMiResult.getKullaniciAdi().toString() != "");
            }else if(envelope.bodyIn instanceof SoapFault){
                SoapFault soapFault = (SoapFault) envelope.bodyIn;
                throw new Exception(soapFault.getMessage());
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }
}

