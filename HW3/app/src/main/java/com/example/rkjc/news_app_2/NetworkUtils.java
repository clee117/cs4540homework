package com.example.rkjc.news_app_2;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtils {
    public final static String BASE_URL = "https://newsapi.org/v1/articles";
    public final static String SOURCE = "the-next-web";
    public final static String SORTBY = "latest";
    public final static String APIKEY = "";

    public static URL buildURL() {
        Uri uri = Uri.parse(BASE_URL).buildUpon()
                .appendQueryParameter("source", SOURCE)
                .appendQueryParameter("sortBy", SORTBY)
                .appendQueryParameter("apiKey", APIKEY).build();
        URL url = null;
        try {
            url = new URL(uri.toString());
        }catch (MalformedURLException e){
            e.printStackTrace();
        };
        return url;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            }else {
                return null;
            }
        }finally {
            urlConnection.disconnect();
        }
    }
}
