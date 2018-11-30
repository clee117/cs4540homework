package com.example.rkjc.news_app_2;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.io.IOException;
import java.util.List;

public class Repository {

    private NewsItemDao dao;
    private LiveData<List<NewsItem>> allItems;

    public Repository(Application app) {
        NewsItemRoomDatabase db = NewsItemRoomDatabase.getDatabase(app);
        dao = db.dao();
        allItems = dao.loadAllNewsItems();
    }

    public LiveData<List<NewsItem>> getItems() {
        new getAllAsyncTask().execute();
        return allItems;
    }

    public void syncDataBase() {new syncDbAsyncTask().execute();}

    private class getAllAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            allItems = dao.loadAllNewsItems();
            return null;
        }
    }

    private class syncDbAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            dao.clearAll();
            try {
                String jsonString = NetworkUtils.getResponseFromHttpUrl(NetworkUtils.buildURL());
                List<NewsItem> items = JsonUtils.parseNews(jsonString);
                dao.insert(items);
            }catch(IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

}
