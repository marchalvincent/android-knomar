package com.example.androidknomar.util;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

public class AbstractParser {

    private URI uri;

    public AbstractParser(String url) throws URISyntaxException {
        super();
        uri = new URI(url);
    }

    public InputStream getStream() throws Exception {
        HttpClient c = new DefaultHttpClient();
        HttpGet r = new HttpGet(uri);
        try {
            return c.execute(r).getEntity().getContent();
        } catch(IOException e) {
            e.printStackTrace();
            throw new Exception("Impossible to connect the http url :" + e.getMessage());
        }
    }


    public XmlPullParser initializer(InputStream inputStream) throws Exception
    {
        InputStreamReader reader = new InputStreamReader(inputStream);

        XmlPullParserFactory factory;
        XmlPullParser xpp;
        try {
            factory = XmlPullParserFactory.newInstance();
            xpp = factory.newPullParser();
            xpp.setInput(reader);
        } catch (XmlPullParserException e) {
            throw new Exception("Impossible to start the initalization : " + e.getMessage());
        }
        return xpp;
    }

    
}
