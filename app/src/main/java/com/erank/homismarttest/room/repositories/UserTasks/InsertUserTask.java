package com.erank.homismarttest.room.repositories.UserTasks;

import android.os.AsyncTask;

import com.erank.homismarttest.models.User;
import com.erank.homismarttest.room.UserDao;

public class InsertUserTask extends AsyncTask<User,Void,Void> {

    private UserDao userDao;
    private Callback callback;

    public InsertUserTask(Callback callback, UserDao dao) {
        this.userDao = dao;
        this.callback = callback;
    }

    @Override
    protected Void doInBackground(User... users) {
        userDao.insertAll(users);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        callback.userInsertDone();
    }

    public interface Callback {
        void userInsertDone();
    }
}
