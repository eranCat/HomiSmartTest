package com.erank.homismarttest.room.repositories;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.erank.homismarttest.models.JsonUser;
import com.erank.homismarttest.models.User;
import com.erank.homismarttest.room.AppDatabase;
import com.erank.homismarttest.room.RecordDao;
import com.erank.homismarttest.room.repositories.RecordsTasks.GetRecordsTask;
import com.erank.homismarttest.room.repositories.RecordsTasks.InsertRecordTask;
import com.erank.homismarttest.utils.CallAPITask;

import java.util.List;

public class RecordsRepo {

    private static final String TAG = RecordsRepo.class.getName();

    private static RecordsRepo instance = new RecordsRepo();

    private RecordDao recordDao;

    public static RecordsRepo getInstance(Context context) {
        instance.recordDao = AppDatabase.getInstance(context).recordDao();
        return instance;
    }

    public LiveData<List<User.Record>> getAllByUID(String uid) {
        return recordDao.getAllByUserIdSync(uid);
    }

    public void insertAll(InsertRecordTask.Delegate delegate, User.Record... records) {
        new InsertRecordTask(delegate, recordDao).execute(records);
    }

    public void sendDataToServer(User user, CallAPITask.Callback callback) {

        String urlStr = "some api url";
        new GetRecordsTask(records -> {
            CallAPITask callAPITask = new CallAPITask(callback, urlStr);
            callAPITask.execute(new JsonUser(user, records));

        }, recordDao).execute(user.id);
    }
}