package com.lm.demo.mypicture.ui.activity.HomeActivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.RelativeLayout;

import com.lm.demo.mypicture.R;

public class InformationActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RelativeLayout headRelativeLayout;
    private RelativeLayout nameRelativeLayout;
    private RelativeLayout sexRelativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myself_information);
        initView();
    }

    private void initView() {
        toolbar= (Toolbar) findViewById(R.id.id_tooblar);
        headRelativeLayout= (RelativeLayout) findViewById(R.id.id_information_head_RelativeLayout);
        nameRelativeLayout= (RelativeLayout) findViewById(R.id.id_information_name_RelativeLayout);
        sexRelativeLayout= (RelativeLayout) findViewById(R.id.id_information_sex_RelativeLayout);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("修改个人资料");

    }
}
