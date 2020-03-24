package com.erank.homismarttest.room.repositories.UserTasks;

import android.os.AsyncTask;

import com.erank.homismarttest.models.User;
import com.erank.homismarttest.room.UserDao;

import java.util.List;

public class GetUsersTask extends AsyncTask<Void,Void,List<User>> {

    private UserDao userDao;
    private Delegate delegate;

    public GetUsersTask(Delegate delegate, UserDao dao) {
        userDao = dao;
        this.delegate = delegate;
    }

    @Override
    protected List<User> doInBackground(Void... voids) {
        return userDao.getAll();
    }

    @Override
    protected void onPostExecute(List<User> users) {
        delegate.usersFetchDone(users);
    }

    public interface Delegate {
        void usersFetchDone(List<User> users);
    }
}
