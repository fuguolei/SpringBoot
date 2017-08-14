package com.igalaxy.boot.conf;

import com.github.wxpay.sdk.WXPayConfig;

import java.io.*;

/**
 * Created by fuguolei on 2017/8/12.
 */
public class MyWXPayConfig implements WXPayConfig {
    private byte[] certData;

    public MyWXPayConfig() {
//        String certPath = "/path/to/apiclient_cert.p12";
//        File file = new File(certPath);
//        InputStream certStream = null;
//        try {
//            certStream = new FileInputStream(file);
//            this.certData = new byte[(int) file.length()];
//            certStream.read(this.certData);
//            certStream.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public String getAppID() {
        return "wx855e27a3d08ed53f";
    }

    @Override
    public String getMchID() {
        return "1370242202";
    }

    @Override
    public String getKey() {
        return "66ff2be57bd12f21b72f96566b754a26";
    }

    @Override
    public InputStream getCertStream() {
        ByteArrayInputStream certBis = new ByteArrayInputStream(this.certData);
        return certBis;
    }

    @Override
    public int getHttpConnectTimeoutMs() {
        return 8000;
    }

    @Override
    public int getHttpReadTimeoutMs() {
        return 10000;
    }
}
