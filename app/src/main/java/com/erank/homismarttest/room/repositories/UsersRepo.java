package com.erank.homismarttest.room.repositories;

import android.content.Context;

import com.erank.homismarttest.models.User;
import com.erank.homismarttest.room.AppDatabase;
import com.erank.homismarttest.room.UserDao;
import com.erank.homismarttest.room.repositories.UserTasks.GetUsersTask;
import com.erank.homismarttest.room.repositories.UserTasks.InsertUserTask;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class UsersRepo {

    private static UsersRepo instance = new UsersRepo();

    private UserDao userDao;

    private UsersRepo() {
    }

    public static UsersRepo getInstance(Context context) {
        instance.userDao = AppDatabase.getInstance(context).userDao();
        return instance;
    }

    public void insertUser(InsertUserTask.Callback callback, @NotNull User... users) {
        new InsertUserTask(callback, userDao).execute(users);

    }

    public void loadUsers(GetUsersTask.Delegate delegate) {
        new GetUsersTask(delegate, userDao).execute();
    }

}

