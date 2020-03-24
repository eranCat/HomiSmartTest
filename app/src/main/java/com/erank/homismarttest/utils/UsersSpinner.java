package com.erank.homismarttest.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.appcompat.widget.AppCompatSpinner;

import com.erank.homismarttest.models.User;

import java.util.ArrayList;
import java.util.List;

public class UsersSpinner extends AppCompatSpinner {

    private UserSelectCallback callback;

    public UsersSpinner(Context context) {
        super(context);
    }

    public UsersSpinner(Context context, int mode) {
        super(context, mode);
    }

    public UsersSpinner(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public UsersSpinner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public UsersSpinner(Context context, AttributeSet attrs, int defStyleAttr, int mode) {
        super(context, attrs, defStyleAttr, mode);
    }

    public UsersSpinner(Context context, AttributeSet attrs, int defStyleAttr,
                        int mode, Resources.Theme popupTheme) {
        super(context, attrs, defStyleAttr, mode, popupTheme);
    }

    public void setUsers(List<User> users) {

        List<String> userNames = new ArrayList<>(users.size());
        for (User user : users) userNames.add(user.name);

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(
                getContext(),
                android.R.layout.simple_spinner_item,
                userNames);

        setAdapter(dataAdapter);

        setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View v, int i, long id) {
                if (callback != null) callback.onUserSelected(users.get(i));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                if (callback != null) callback.onUserSelected(null);
            }
        });
    }

    public void setOnUserSelectedCallback(UserSelectCallback callback) {
        this.callback = callback;
    }

    public interface UserSelectCallback {
        void onUserSelected(User user);
    }
}
