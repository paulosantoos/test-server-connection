package main.java.com.paulosantos;

import org.apache.http.HttpHost;
import org.apache.http.NameValuePair;
import org.apache.http.ProtocolVersion;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static main.java.com.paulosantos.constants.ApplicationConstant.CLIENT_ID;
import static main.java.com.paulosantos.constants.ApplicationConstant.CLIENT_SECRET;
import static main.java.com.paulosantos.constants.ApplicationConstant.GRANT_TYPE;
import static main.java.com.paulosantos.constants.ApplicationConstant.PASSWORD;
import static main.java.com.paulosantos.constants.ApplicationConstant.PROXY_PORT;
import static main.java.com.paulosantos.constants.ApplicationConstant.PROXY_SCHEME;
import static main.java.com.paulosantos.constants.ApplicationConstant.PROXY_URL;
import static main.java.com.paulosantos.constants.ApplicationConstant.REQUEST_MAJOR_VERSION;
import static main.java.com.paulosantos.constants.ApplicationConstant.REQUEST_MINOR_VERSION;
import static main.java.com.paulosantos.constants.ApplicationConstant.REQUEST_PROTOCOL;
import static main.java.com.paulosantos.constants.ApplicationConstant.REQUEST_URL;
import static main.java.com.paulosantos.constants.ApplicationConstant.USERNAME;
import static main.java.com.paulosantos.constants.ApplicationConstant.USE_PROXY;


/**
 * @author Paulo Santos
 * @version 1.1
 * @apiNote Check server connection with or without proxy
 * @since 2018-06-05
 */
public class Main {

    public static void main(String[] args) {

        final CloseableHttpClient httpclient = HttpClients.createDefault();
        final HttpPost post = new HttpPost(REQUEST_URL);

        /* if necessary, set protocol version to HTTP/1.0 */
        post.setProtocolVersion(new ProtocolVersion(REQUEST_PROTOCOL, REQUEST_MAJOR_VERSION, REQUEST_MINOR_VERSION));

        if (USE_PROXY) {
            HttpHost proxy = new HttpHost(PROXY_URL, PROXY_PORT, PROXY_SCHEME);
            RequestConfig config = RequestConfig.custom()
                    .setProxy(proxy)
                    .build();
            post.setConfig(config);
        }

        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("client_id", CLIENT_ID));
        params.add(new BasicNameValuePair("client_secret", CLIENT_SECRET));
        params.add(new BasicNameValuePair("grant_type", GRANT_TYPE));
        params.add(new BasicNameValuePair("username", USERNAME));
        params.add(new BasicNameValuePair("password", PASSWORD));

        CloseableHttpResponse response = null;
        String body = "";

        try {
            /* print configs that will be used in request */
            printConfig(params, args, post);

            post.setEntity(new UrlEncodedFormEntity(params));
            response = httpclient.execute(post);
            body = EntityUtils.toString(response.getEntity(), "UTF-8");
            System.out.println("Response: " + body + " Code:" + response.getStatusLine().getStatusCode());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                assert response != null;
                response.close();
            } catch (final IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void printConfig(final List<NameValuePair> params, final String[] args, final HttpPost post) {
        System.out.println("Request URL: " + REQUEST_URL);
        System.out.println("Proxy Enable?: " + USE_PROXY);
        System.out.println("HTTP Protocol: " + post.getProtocolVersion());

        if (USE_PROXY) {
            System.out.println("Setting Proxy: " + PROXY_SCHEME + "://" + PROXY_URL + ":" + PROXY_PORT);
        }

        System.out.println("OAuth2 Credentials");
        for (NameValuePair valuePair : params) {
            System.out.println("Key: " + valuePair.getName() + ": " + valuePair.getValue());
        }
    }
}
