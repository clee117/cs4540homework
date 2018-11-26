package com.example.rkjc.news_app_2;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class JsonUtils {
    public static ArrayList<NewsItem> parseNews(String JSONString) {
        ArrayList<NewsItem> list = new ArrayList<NewsItem>();

        JSONObject object;
        JSONArray articles;
        JSONObject newsItem;
        try {
            object = new JSONObject(JSONString);
            articles = object.getJSONArray("articles");
            for(int i = 0; i < articles.length(); i++) {
                newsItem = articles.getJSONObject(i);
                list.add(new NewsItem(newsItem.getString("author"),
                        newsItem.getString("title"),
                        newsItem.getString("description"),
                        newsItem.getString("url"),
                        newsItem.getString("urlToImage"),
                        newsItem.getString("publishedAt")));
            }
        }catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
}


