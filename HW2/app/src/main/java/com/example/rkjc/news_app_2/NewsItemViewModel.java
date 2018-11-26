package com.example.rkjc.news_app_2;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

public class NewsItemViewModel extends AndroidViewModel {
    private Repository repo;
    private LiveData<List<NewsItem>> allItems;

    public NewsItemViewModel(Application app) {
        super(app);
        repo = new Repository(app);
        allItems = repo.getItems();
    }

    public LiveData<List<NewsItem>> getItems() {
        return allItems;
    }
    public void syncData() {
        repo.syncDataBase();
        allItems = repo.getItems();
    }

}
