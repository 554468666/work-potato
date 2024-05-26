package com.gm.wj.util;

import cn.hutool.json.JSONObject;
import com.gm.wj.result.Result;
import org.springframework.stereotype.Component;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.NoSuchAlgorithmException;

@Component
public class HttpPostUtils {
    public Result Post(String strUrl,String args) {
        try {

            ignoreSSL();
            // 设置请求的URL
            URL url = new URL(strUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // 设置请求方式为POST
            connection.setRequestMethod("POST");

            // 设置请求头
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);




            // 获取输出流并写入请求体
            try (DataOutputStream os = new DataOutputStream(connection.getOutputStream())) {
                byte[] inputBytes = args.getBytes("utf-8");
                os.write(inputBytes, 0, inputBytes.length);
            }
            StringBuilder response =null;
            // 获取并读取响应
            try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                response = new StringBuilder();
                String responseLine;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                System.out.println(response.toString());
            }
            return new Result(String.valueOf(00),response.toString(),"");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(String.valueOf(999),e.getMessage(),"");
        }
    }

    /**
     * 忽略SSL验证
     */
    public  void ignoreSSL()  {

        try {
            SSLContext sslContext = SSLContext.getInstance("TLS");
            TrustManager[] trustManagers = new TrustManager[]{new X509TrustManager() {
                @Override
                public void checkClientTrusted(java.security.cert.X509Certificate[] x509Certificates, String s) {}

                @Override
                public void checkServerTrusted(java.security.cert.X509Certificate[] x509Certificates, String s) {}

                @Override
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
            }};
            sslContext.init(null, trustManagers, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
