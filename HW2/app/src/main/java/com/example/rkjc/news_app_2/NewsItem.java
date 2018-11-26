package com.example.rkjc.news_app_2;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName="news_item")
public class NewsItem {

    @ColumnInfo(name="id")
    @PrimaryKey(autoGenerate=true)
    @NonNull
    private int id;

    @ColumnInfo(name="author")
    private String author;
    @ColumnInfo(name="title")
    private String title;
    @ColumnInfo(name="description")
    private String description;
    @ColumnInfo(name="url")
    private String url;
    @ColumnInfo(name="urlToImage")
    private String urlToImage;
    @ColumnInfo(name="publishedAtString")
    private String publishedAtString;

    public int getId() { return this.id; }

    public String getAuthor() {
        return this.author;
    }

    public String getTitle() {
        return this.title;
    }

    public String getDescription() { return this.description; }

    public String getUrl() {
        return this.url;
    }

    public String getUrlToImage() {
        return this.urlToImage;
    }

    public String getPublishedAtString() {
        return this.publishedAtString;
    }

    public NewsItem(int id, String author, String title, String description, String url, String urlToImage, String publishedAtString) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.description = description;
        this.url = url;
        this.urlToImage = urlToImage;
        this.publishedAtString = publishedAtString;
    }

    @Ignore
    public NewsItem(String author, String title, String description, String urlString, String urlToImageString, String publishedAtString) {
        this.author = author;
        this.title = title;
        this.description = description;
        this.url = urlString;
        this.urlToImage = urlToImageString;
        this.publishedAtString = publishedAtString;
    }
}
