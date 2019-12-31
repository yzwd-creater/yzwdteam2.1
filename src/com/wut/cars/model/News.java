package com.wut.cars.model;

import java.io.File;
import java.security.PrivateKey;
import java.util.Date;
import java.util.Objects;

public class News {
    /** serialVersionUID. */
    private static final long serialVersionUID = 1L;

    private Integer newsId;
    private String author;
    private String time;
    private String local;
    private String title;
    private Integer readCount=0;
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getNewsId() {
        return newsId;
    }

    public void setNewsId(Integer newsId) {
        this.newsId = newsId;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getReadCount() {
        return readCount;
    }

    public void setReadCount(Integer readCount) {
        this.readCount = readCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        News news = (News) o;
        return Objects.equals(newsId, news.newsId) &&
                Objects.equals(author, news.author) &&
                Objects.equals(time, news.time) &&
                Objects.equals(local, news.local) &&
                Objects.equals(title, news.title) &&
                Objects.equals(readCount, news.readCount) &&
                Objects.equals(content, news.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(newsId, author, time, local, title, readCount, content);
    }
}
