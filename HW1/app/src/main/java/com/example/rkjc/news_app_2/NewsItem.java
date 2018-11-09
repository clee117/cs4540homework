package com.example.rkjc.news_app_2;

import java.util.Date;

public class NewsItem {
    private String author;
    private String title;
    private String description;
    private String url;
    private String urlToImage;

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public String getPublishedAtString() {
        return publishedAtString;
    }

    private String publishedAtString;

    public NewsItem(String author, String title, String description, String urlString, String urlToImageString, String publishedAtString) {
        this.author = author;
        this.title = title;
        this.description = description;
        this.url = urlString;
        this.urlToImage = urlToImageString;
        this.publishedAtString = publishedAtString;
    }

}
