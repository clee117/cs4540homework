package com.example.rkjc.news_app_2;

import android.content.Context;
import android.os.AsyncTask;
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



public class MainActivity extends AppCompatActivity {
    private String jsonString = null;
    MenuItem item = null;
    RecyclerView recyclerView;
    NewsRecyclerViewAdapter adapter;
    ArrayList<NewsItem> items = new ArrayList<NewsItem>();

    // Recyclerview intentionally left blank to show the Refresh menu item works
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.news_recyclerview);
        adapter = new NewsRecyclerViewAdapter(this, items);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
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
                NewsQueryTask task = new NewsQueryTask();
                task.execute();
                break;
            default:
                System.out.println("A menu item with no assigned action has been pressed");
                return false;
        }
        return true;
    }

    public void startAsyncTask() {
        NewsQueryTask task = new NewsQueryTask();
        task.execute();
    }

    class NewsQueryTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                jsonString = NetworkUtils.getResponseFromHttpUrl(NetworkUtils.buildURL());
                adapter.mItems = JsonUtils.parseNews(jsonString);
            }catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(jsonString);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            adapter.notifyDataSetChanged();
        }
    }
}
