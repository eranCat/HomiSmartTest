package com.erank.homismarttest.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import com.erank.homismarttest.R;
import com.erank.homismarttest.fragments.RecordsFragment;
import com.erank.homismarttest.models.Action;
import com.erank.homismarttest.models.User;
import com.erank.homismarttest.room.repositories.RecordsRepo;
import com.erank.homismarttest.room.repositories.UsersRepo;
import com.erank.homismarttest.utils.ShortStuff;
import com.erank.homismarttest.utils.UsersSpinner;

import static com.erank.homismarttest.utils.ShortStuff.alert;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();
    private static final int RC_REGISTER = 89;

    private UsersSpinner usersSpinner;
    private UsersRepo usersRepo;
    private RecordsRepo recordsRepo;

    private User selectedUser;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usersRepo = UsersRepo.getInstance(this);
        recordsRepo = RecordsRepo.getInstance(this);

        findViews();

        fetchUsers();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_REGISTER && resultCode == RESULT_OK) {
            fetchUsers();
            toast("Registered !");
        }
    }

    private void findViews() {
        usersSpinner = findViewById(R.id.spinner);
        usersSpinner.setOnUserSelectedCallback(user -> {
            selectedUser = user;
            getSupportFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                    .replace(R.id.container, RecordsFragment.newInstance(user.id))
                    .commit();
        });

        findViewById(R.id.outBtn).setOnClickListener(v -> saveAction(Action.OUT));

        findViewById(R.id.inBtn).setOnClickListener(v -> saveAction(Action.IN));

        findViewById(R.id.regBtn).setOnClickListener(v -> startRegister());

        findViewById(R.id.send_to_server_btn).setOnClickListener(v -> sendToServer());
    }

    private void sendToServer() {
        if (selectedUser == null) {
            toast(R.string.select_user);
            return;
        }

        recordsRepo.sendDataToServer(selectedUser, () -> {
            alert(this, "Upload task is done");
        });
    }

    private void fetchUsers() {
        usersRepo.loadUsers(users -> {

            usersSpinner.setUsers(users);

            if (!users.isEmpty()) return;

            startRegister();
        });
    }

    private void startRegister() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivityForResult(intent, RC_REGISTER);
    }

    private void saveAction(Action action) {
        if (selectedUser == null) {
            toast("Please select a user before making an action");
            return;
        }

        User.Record record = new User.Record(action, selectedUser.id);
        recordsRepo.insertAll(() -> {
            String string = getString(R.string.added_to_records, record);
            Log.d(TAG, String.format("saveAction: %s", string));
        }, record);
    }

    private void toast(String msg) {
        ShortStuff.toast(this, msg);
    }

    private void toast(@StringRes int msg) {
        ShortStuff.toast(this, msg);
    }
}