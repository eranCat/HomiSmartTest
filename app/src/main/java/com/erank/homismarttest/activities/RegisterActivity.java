package com.erank.homismarttest.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.erank.homismarttest.R;
import com.erank.homismarttest.models.User;
import com.erank.homismarttest.room.repositories.UsersRepo;

public class RegisterActivity extends AppCompatActivity {

    private EditText nameEt;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nameEt = findViewById(R.id.nameEt);

        progressBar = findViewById(R.id.reg_progressBar);

        Button saveBtn = findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(v -> saveUser());
    }

    private void saveUser() {
        final String name = nameEt.getText().toString();
        if (name.isEmpty()) {
            nameEt.setError("Can't be empty");
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        UsersRepo.getInstance(this).insertUser(() -> {
            setResult(RESULT_OK);
            finish();

        }, new User(name));
    }

}
