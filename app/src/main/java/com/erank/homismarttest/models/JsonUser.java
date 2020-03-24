package com.erank.homismarttest.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class JsonUser {
    private User user;

    @SerializedName("history")
    private List<User.Record> records;

    public JsonUser(User user, List<User.Record> records) {
        this.user = user;
        this.records = records;
    }

    public JsonUser() {
    }

    @Override
    public String toString() {
        return "JsonUser{" +
                "user=" + user +
                ", records=" + records +
                '}';
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<User.Record> getRecords() {
        return records;
    }

    public void setRecords(List<User.Record> records) {
        this.records = records;
    }
}
