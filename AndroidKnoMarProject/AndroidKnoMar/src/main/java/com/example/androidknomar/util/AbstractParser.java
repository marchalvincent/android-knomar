package com.example.androidknomar.util;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by marchal.vincent on 25/01/14.
 */
public abstract class AbstractParser {

    private URI uri;

    public AbstractParser(URI uri) throws URISyntaxException {
        super();
        uri = uri;
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

    protected XmlPullParser initializer(InputStream is) throws Exception {
        InputStreamReader reader = new InputStreamReader(is);

        XmlPullParserFactory factory;
        XmlPullParser xpp;
        try {
            factory = XmlPullParserFactory.newInstance();
            xpp = factory.newPullParser();
            xpp.setInput(reader);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Impossible to initialize the parsing : " + e.getMessage());
        }
        return xpp;
    }

    abstract void parseSource(XmlPullParser xmlPullParser) throws Exception;
}
