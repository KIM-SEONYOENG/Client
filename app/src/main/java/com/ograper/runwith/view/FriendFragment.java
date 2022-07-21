package com.ograper.runwith.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.ograper.runwith.R;

public class FriendFragment extends Fragment {
    PreferenceAccess preferenceAccess;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        preferenceAccess = new PreferenceAccess(getContext());
        View view = inflater.inflate(R.layout.fragment_friend, container, false);
        if(preferenceAccess.getUserFriend().equals("")) {
            view = inflater.inflate(R.layout.fragment_invite, container, false);
        }
        return view;
    }
}
