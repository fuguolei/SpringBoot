package com.igalaxy.boot.util;

import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * Created by jinlong on 16/8/25.
 */
public class HttpClientUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpClientUtils.class);

    /**
     * post请求
     * @param url
     * @param formParams
     * @return
     */
    public static String doPost(String url, Map<String, String> formParams) {
        if (MapUtils.isEmpty(formParams)) {
            return doPost(url);
        }

        try {
            MultiValueMap<String, String> requestEntity = new LinkedMultiValueMap<>();
            formParams.keySet().stream().forEach(key -> requestEntity.add(key, MapUtils.getString(formParams, key, "")));
            return RestClient.getClient().postForObject(url, requestEntity, String.class);
        } catch (Exception e) {
            LOGGER.error("POST请求出错：{}", url, e);
        }

        return null;
    }

    /**
     * post请求
     * @param url
     * @return
     */
    public static String doPost(String url) {
        try {
            return RestClient.getClient().postForObject(url, HttpEntity.EMPTY, String.class);
        } catch (Exception e) {
            LOGGER.error("POST请求出错：{}", url, e);
        }

        return null;
    }

    /**
     * get请求
     * @param url
     * @return
     */
    public static String doGet(String url) {
        try {
            return RestClient.getClient().getForObject(url, String.class);
        } catch (Exception e) {
            LOGGER.error("GET请求出错：{}", url, e);
        }

        return null;
    }

    public static String doGet(String url, Object... urlVariables) {
        try {
            return RestClient.getClient().getForObject(url, String.class, urlVariables);
        } catch (Exception e) {
            LOGGER.error("GET请求出错：{}", url, e);
        }

        return null;
    }



    public static void main(String[] args) {
//        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
//        String time = df.format(new Date());
//        String sign = accountId + time + authToken;
//        Map<String, String> form = new HashMap<>();
//        form.put("sid", accountId);
//        form.put("appId", appId);
//        form.put("sign", DigestUtils.md5DigestAsHex(sign.getBytes()));
//        form.put("time", time);
//        form.put("templateId", templateId);
//        form.put("to", "13001996816");
//        form.put("param", "abc,123");
//        //String response = restTemplate.postForObject(server, form, String.class);
//        String response = doPost(server, form);
//        System.out.println(response);


    }

    static RestTemplate restTemplate = new RestTemplate();
    private static String server = "http://www.ucpaas.com/maap/sms/code";

    private  static String accountId = "f373ddb443d47e437783596516580445";

    private static String authToken = "1869e8d0ccda6a8a4ecbbe1377199efd";


    private static String appId = "6ab91a2206414e81b236352bbce36da8";

    private static String templateId = "15151";

    public static class Test {
        public Integer errcode;
        public String errmsg;
    }
}
