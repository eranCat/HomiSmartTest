package com.erank.homismarttest.room.repositories.RecordsTasks;

import android.os.AsyncTask;

import com.erank.homismarttest.models.User;
import com.erank.homismarttest.room.RecordDao;

import java.util.List;

public class GetRecordsTask extends AsyncTask<String, Void, List<User.Record>> {

    private RecordDao recordDao;
    private Delegate delegate;

    public GetRecordsTask(Delegate delegate, RecordDao dao) {
        recordDao = dao;
        this.delegate = delegate;
    }

    @Override
    protected List<User.Record> doInBackground(String... uids) {
        return recordDao.getAllByUserId(uids[0]);
    }

    @Override
    protected void onPostExecute(List<User.Record> records) {
        delegate.recordsFetchDone(records);
    }

    public interface Delegate {
        void recordsFetchDone(List<User.Record> records);
    }
}
