package com.ograper.runwith.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.ograper.runwith.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HomeFragment extends Fragment {
    private TextView tvStep;
    private TextView tvDate;

    PreferenceAccess preferenceAccess;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        preferenceAccess = new PreferenceAccess(getContext());

        tvStep = view.findViewById(R.id.tvStep);
        tvStep.setText(String.valueOf(preferenceAccess.getStep()));
        tvDate = view.findViewById(R.id.tvDate);
        tvDate.setText(getDate());

        return view;
    }

    public String getDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(getString(R.string.dateFormate));
        return dateFormat.format(new Date());
    }
}
