package com.ograper.runwith.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.ograper.runwith.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TeamFragment extends Fragment implements View.OnClickListener {
    private TextView tvDate;
    private Button registMate;
    private  View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_regist, container, false);
        tvDate = (TextView) view.findViewById(R.id.tvDate);
        tvDate.setText(getDate());
        registMate = (Button) view.findViewById(R.id.registMate);

        return view;
    }

    public String getDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        return dateFormat.format(new Date());
    }

    @Override
    public void onClick(View v) {

    }
}
