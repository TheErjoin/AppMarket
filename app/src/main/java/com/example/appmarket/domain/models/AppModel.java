package com.example.appmarket.domain.models;

import com.example.appmarket.common.utils.Status;

public class AppModel {

    private String link;
    private String version;
    private String type;
    private String logo50Link;
    private String logo200Link;
    private String title;
    private String description;
    private Status status = Status.canInstalled;

    public AppModel(
            String link,
            String version,
            String type,
            String logo50Link,
            String logo200Link,
            String title,
            String description,
            Status status
    ) {
        this.link = link;
        this.version = version;
        this.type = type;
        this.logo50Link = logo50Link;
        this.logo200Link = logo200Link;
        this.title = title;
        this.description = description;
        this.status = status;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLogo50Link() {
        return logo50Link;
    }

    public void setLogo50Link(String logo50Link) {
        this.logo50Link = logo50Link;
    }

    public String getLogo200Link() {
        return logo200Link;
    }

    public void setLogo200Link(String logo200Link) {
        this.logo200Link = logo200Link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
