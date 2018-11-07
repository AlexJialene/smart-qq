package com.keizyi.smartqq.kit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
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
        return sendGet(url, null, null);
    }

    public static String sendGet(String url, Map<String, String> param) {
        return sendGet(url, param, null);
    }

    public static String sendGet(String url, Map<String, String> param, Map<String, String> headers) {
        return result(getConn(url, param, headers));
    }

    public static URLConnection getConn(String url, Map<String, String> param, Map<String, String> headers) {
        Piece piece = new Piece();
        if (null != param) {
            param.forEach((k, v) -> {
                piece.piece(k, v);
            });
        }
        return connection(url + piece.getUrlString(), HttpType.GET, null, headers);
    }

    public static URLConnection getConn(String url, Map<String, String> headers) {
        return getConn(url, null, headers);
    }

    public static URLConnection getConn(String url) {
        return getConn(url, null);
    }

    private static URLConnection connection(String url, HttpType type, Object postParam, Map<String, String> headers) {
        try {
            URL realUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36");
            if (null != headers) {
                headers.forEach((k, v) -> {
                    connection.setRequestProperty(k, v);
                });
            }
            if (type == HttpType.POST) {
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setDoInput(true);
                PrintWriter out = new PrintWriter(connection.getOutputStream());
                out.print(postParam);
                out.flush();
            } else {
                connection.setInstanceFollowRedirects(false);
                connection.connect();
            }
            Map<String, List<String>> map = connection.getHeaderFields();

            //LOGGER DEBUG WRITER
            map.forEach((k, v) -> {
                LOGGER.debug("key={} ; value={}", k, v.toString());
            });

            return connection;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String sendPost(String url, String postParam, Map<String, String> headers) {
        return result(connection(url, HttpType.POST, postParam, headers));

    }

    private static String result(URLConnection connection) {
        BufferedReader in = null;
        String result = "";
        try {
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
            return result;
        } catch (Exception e) {

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
        POST
    }

    private static class Piece {
        private String urlString = "?";

        public void piece(String k, String v) {
            urlString += k + "=" + v + "&";

        }

        public String getUrlString() {
            return urlString.length() == 1 ? "" : urlString;
        }
    }
}
