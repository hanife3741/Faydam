package com.esogu.hanife.faydam;

import com.google.gson.Gson;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

/**
 * Created by ASUS on 23.12.2014.
 */
public class WebservisCallerImpl implements WebServisCaller {
    private static final String NAMESPACE="http://www.faydam.com";
    private static final String SERVIS_URL="http://193.109.135.67:92/Service1.asmx";
    private static final String DOGRULA_METHOD="KulaniciVarMi";
    private static final String SOAP_DOGRULA_ACTİON=NAMESPACE+"KullaniciVarMi";
    public boolean KullaniciVarMi(KullaniciVarMiInput input){
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
            androidHttpTransport.call(SOAP_DOGRULA_ACTİON,envelope);
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

