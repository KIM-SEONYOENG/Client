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

public class MyPageFragment extends Fragment {
    private View view;
    PreferenceAccess preferenceAccess;
    TextView textView;
    Button button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mypage, container, false);
        textView = view.findViewById(R.id.id);
        button = view.findViewById(R.id.fragmentregistmatebtn);
        preferenceAccess = new PreferenceAccess(getActivity().getApplicationContext());
        preferenceAccess.setUserName("123");
        String id = preferenceAccess.getUserName();
        textView.setText(id);
        return view;
    }
}

