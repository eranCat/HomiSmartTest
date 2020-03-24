package com.erank.homismarttest.room.repositories.RecordsTasks;

import android.os.AsyncTask;

import com.erank.homismarttest.models.User;
import com.erank.homismarttest.room.RecordDao;

public class InsertRecordTask extends AsyncTask<User.Record,Void, User.Record[]> {

    private RecordDao recordDao;
    private Delegate delegate;

    public InsertRecordTask(Delegate delegate, RecordDao dao) {
        recordDao = dao;
        this.delegate = delegate;
    }

    @Override
    protected User.Record[] doInBackground(User.Record... records) {
        recordDao.insertAll(records);
        return records;
    }

    @Override
    protected void onPostExecute(User.Record[] records) {
        delegate.recordInsertDone();
    }

    public interface Delegate {
        void recordInsertDone();
    }
}
