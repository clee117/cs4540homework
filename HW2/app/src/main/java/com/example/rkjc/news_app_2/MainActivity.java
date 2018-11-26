package com.example.rkjc.news_app_2;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private String jsonString = null;
    MenuItem item = null;
    RecyclerView recyclerView;
    NewsRecyclerViewAdapter adapter;
    ArrayList<NewsItem> items = new ArrayList<NewsItem>();
    NewsItemViewModel viewModel;

    // Recyclerview intentionally left blank to show the Refresh menu item works
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.news_recyclerview);
        adapter = new NewsRecyclerViewAdapter(this, items);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        viewModel = ViewModelProviders.of(this).get(NewsItemViewModel.class);
        viewModel.getItems().observe(this, new Observer<List<NewsItem>>() {
            @Override
            public void onChanged(@Nullable List<NewsItem> newsItems) {
                adapter.mItems = (ArrayList<NewsItem>) newsItems;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch(id) { // Uses switch instead of an if statement
            case R.id.action_search:
                viewModel.syncData();
                adapter.notifyDataSetChanged();
                //NewsQueryTask task = new NewsQueryTask();
                //task.execute();
                break;
            default:
                System.out.println("A menu item with no assigned action has been pressed");
                return false;
        }
        return true;
    }

}
