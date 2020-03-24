package com.erank.homismarttest.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Entity
public class User implements Parcelable {

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
    @PrimaryKey
    @NonNull
    public String id;
    public String name;

    public User() {
    }

    public User(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
    }

    protected User(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
    }

    @NotNull
    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
    }

    @Entity
    public static class Record {

        @PrimaryKey(autoGenerate = true)
        public long id;

        public Date date;

        public Action action;

        @NonNull
        public String uid;//user's record id

        public Record() {
        }

        public Record(Action action, String uid) {
            this.date = Calendar.getInstance().getTime();
            this.action = action;
            this.uid = uid;
        }

        @Override
        public String toString() {
            return "Record{" +
                    "id=" + id +
                    ", date=" + date +
                    ", action=" + action +
                    ", uid='" + uid + '\'' +
                    '}';
        }
    }
}