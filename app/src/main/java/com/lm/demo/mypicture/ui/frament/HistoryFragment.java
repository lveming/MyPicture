package com.lm.demo.mypicture.ui.frament;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lm.demo.mypicture.R;

/**
 * Created by Administrator on 2017/4/6.
 */

public class HistoryFragment extends Fragment {

    public static HistoryFragment newInstance(String param1) {
        HistoryFragment fragment = new HistoryFragment();
        Bundle args = new Bundle();
        args.putString("args1", param1);
        fragment.setArguments(args);
        return fragment;
    }


    public HistoryFragment() {
        super();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.history_fragment, container, false);
        Bundle bundle = getArguments();
        String args1 = bundle.getString("args1");
        getActivity().setTitle(args1);
        return view;
    }
}
