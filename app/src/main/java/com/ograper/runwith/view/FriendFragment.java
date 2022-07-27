package com.ograper.runwith.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.ograper.runwith.R;

public class FriendFragment extends Fragment {
    PreferenceAccess preferenceAccess;
    Button btnSendMsg, helper, mate_helper;
    Button registMate;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //메이트가 있는 경우
        preferenceAccess = new PreferenceAccess(getContext());
        View view = inflater.inflate(R.layout.fragment_friend, container, false);
        btnSendMsg = view.findViewById(R.id.btnSendMsg);
        helper = view.findViewById(R.id.button_helper);
        mate_helper = view.findViewById(R.id.mate_helper);


        //메이트가 없는 경우
        if(preferenceAccess.getUserFriend().equals("")) {
            view = inflater.inflate(R.layout.fragment_regist, container, false);
            //RegistActivity는 따로 필요가 없는것인가요?
            registMate = view.findViewById(R.id.registMate);
            registMate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    FragmentTransaction tr = getActivity().getSupportFragmentManager().beginTransaction();
                    RegistMateFragment registMate = new RegistMateFragment();
                    tr.replace(R.id.frameLayout, registMate);
                    tr.commit();
                }
            });
            //요청이 들어온 경우의 조건문 여쭤보기
        }

        return view;
    }
}
