package com.keizyi.smartqq.kit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Alex_
 * Date: 2018/11/6
 * Time: 10:38
 */
public class HttpRequestKit {
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpRequestKit.class);

    public static String sendGet(String url) {
        return sendGet(url, null);
    }

    public static String sendGet(String url, String param) {
        BufferedReader in;
        String result = "";
        String urlString;
        if (null == param) {
            urlString = url;
        } else {
            urlString = url + "?" + param;
        }
        in = new BufferedReader(new InputStreamReader(doGet(urlString ,null)));
        String line;
        try {
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    ;
                }
            }
        }
        return null;
    }

    public static InputStream doGet(String urlNameString , String referer) {
        try {
            URLConnection connection = connectGet(urlNameString, referer, HttpType.GET);
            Map<String, List<String>> map = connection.getHeaderFields();
            //LOGGER DEBUG WRITER
            map.forEach((k, v) -> {
                LOGGER.debug("key={} ; value={}", k, v.toString());
            });
            return connection.getInputStream();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static URLConnection connectGet(String url, String referer, HttpType type) throws IOException {
        URL realUrl = new URL(url);
        URLConnection connection = realUrl.openConnection();
        connection.setRequestProperty("accept", "*/*");
        connection.setRequestProperty("connection", "Keep-Alive");
        connection.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36");
        if (null != referer){
            connection.setRequestProperty("referer" ,referer);
        }
        //connect
        if (type == HttpType.POST) {
            //
        }
        connection.connect();
        return connection;
    }

    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            URLConnection conn = realUrl.openConnection();
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setDoOutput(true);
            conn.setDoInput(true);

            out = new PrintWriter(conn.getOutputStream());
            out.print(param);
            out.flush();

            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != out) {
                    out.close();
                }
                if (null != in) {
                    in.close();
                }
            } catch (IOException ex) {
                ;
            }
        }
        return null;
    }

    public enum HttpType {
        GET,
        POST;
    }
}
