package com.erank.homismarttest.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.erank.homismarttest.R;
import com.erank.homismarttest.models.User;
import com.erank.homismarttest.room.repositories.RecordsRepo;
import com.erank.homismarttest.utils.RecordsAdapter;

import java.util.List;

public class RecordsFragment extends Fragment {
    private static final String USER_ID = "userID";
    private static String TAG = RecordsFragment.class.getName();
    private RecyclerView recordsRecycler;
    private RecordsAdapter adapter;

    public static RecordsFragment newInstance(String userID) {

        Bundle args = new Bundle();
        args.putString(USER_ID, userID);
        RecordsFragment fragment = new RecordsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_records, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findViews(view);

        adapter = new RecordsAdapter();
        recordsRecycler.setAdapter(adapter);

        Bundle arguments = getArguments();
        if (arguments == null) return;

        String uid = arguments.getString(USER_ID);

        RecordsRepo repo = RecordsRepo.getInstance(getContext());
        repo.getAllByUID(uid).observe(this, adapter::setRecords);
    }

    private void findViews(View view) {
        recordsRecycler = view.findViewById(R.id.recordsList);
    }
}
