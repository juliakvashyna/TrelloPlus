package com.assigment.trelloplus.controller;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.X509TrustManager;

/**
 * Created by Juli on 29.09.2015.
 */
public class HttpsTrustManager implements X509TrustManager {

    private static HttpsTrustManager[] httpsTrustManagers;
    private static final X509Certificate[] _AcceptedIssuers = new X509Certificate[]{};

    @Override
    public void checkClientTrusted(
            java.security.cert.X509Certificate[] x509Certificates, String s)
            throws java.security.cert.CertificateException {

    }

    @Override
    public void checkServerTrusted(
            java.security.cert.X509Certificate[] x509Certificates, String s)
            throws java.security.cert.CertificateException {

    }

    public boolean isClientTrusted(X509Certificate[] chain) {
        return true;
    }

    public boolean isServerTrusted(X509Certificate[] chain) {
        return true;
    }

    @Override
    public X509Certificate[] getAcceptedIssuers() {
        return _AcceptedIssuers;
    }

    public static void allowAllSSL() {
        HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {

            @Override
            public boolean verify(String arg0, SSLSession arg1) {
                return true;
            }

        });

        SSLContext context = null;
        if (httpsTrustManagers == null) {
            httpsTrustManagers = new HttpsTrustManager[]{new HttpsTrustManager()};
        }

        try {
            context = SSLContext.getInstance("TLS");
            context.init(null, httpsTrustManagers, new SecureRandom());
        } catch (KeyManagementException e){
            e.printStackTrace();
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        if (context != null)
            HttpsURLConnection.setDefaultSSLSocketFactory(context
                    .getSocketFactory());
    }

}



