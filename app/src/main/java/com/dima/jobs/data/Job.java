package com.dima.jobs.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "favorites")
public class Job implements Serializable {

    @PrimaryKey
    private long databaseId;
    private boolean isFavorite;
    private String id;
    private String type;
    private String url;
    private String createdAt;
    private String company;
    private String companyUrl;
    private String location;
    private String title;
    private String description;
    private String howToApply;
    private String companyLogo;

    public Job(String id, String type, String url, String createdAt, String company, String companyUrl, String location, String title, String description, String howToApply, String companyLogo) {

        this.isFavorite = false;
        this.id = id;
        this.type = type;
        this.url = url;
        this.createdAt = createdAt;
        this.company = company;
        this.companyUrl = companyUrl;
        this.location = location;
        this.title = title;
        this.description = description;
        this.howToApply = howToApply;
        this.companyLogo = companyLogo;
    }

    public String getId() {
        return id;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public long getDatabaseId() {
        return databaseId;
    }

    public String getType() {
        return type;
    }

    public String getUrl() {
        return url;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getCompany() {
        return company;
    }

    public String getCompanyUrl() {
        return companyUrl;
    }

    public String getLocation() {
        return location;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getHowToApply() {
        return howToApply;
    }

    public String getCompanyLogo() {
        return companyLogo;
    }

    public void setDatabaseId(long databaseId) {
        this.databaseId = databaseId;
    }
}
