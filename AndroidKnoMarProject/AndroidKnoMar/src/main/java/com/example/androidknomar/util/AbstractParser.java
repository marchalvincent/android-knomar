package com.example.androidknomar.util;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by marchal.vincent on 25/01/14.
 */
public class AbstractParser {

    private URI uri;

    public AbstractParser(String url) throws URISyntaxException {
        super();
        uri = new URI(url);
    }

    protected InputStream getStream() throws Exception {
        HttpClient c = new DefaultHttpClient();
        HttpGet r = new HttpGet(uri);
        try {
            return c.execute(r).getEntity().getContent();
        } catch(IOException e) {
            e.printStackTrace();
            throw new Exception("Impossible to connect the http url :" + e.getMessage());
        }
    }


}
