package com.lm.demo.mypicture.ui.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.FrameLayout;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.lm.demo.mypicture.R;
import com.lm.demo.mypicture.ui.frament.HistoryFragment;
import com.lm.demo.mypicture.ui.frament.LabelFragment;
import com.lm.demo.mypicture.ui.frament.MyselfFragment;
import com.lm.demo.mypicture.ui.frament.PictureFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener {

    private Toolbar toolbar;
    private BottomNavigationBar bottomNavigationBar;
    private FrameLayout frameLayout;

    int lastSelectedPosition = 0;
    private static final String TAG = "MainActivity";
    private ArrayList<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.id_tooblar);
        bottomNavigationBar= (BottomNavigationBar) findViewById(R.id.id_BottomNavigationBar);
        frameLayout= (FrameLayout) findViewById(R.id.id_frameLayout);

        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.mipmap.picture, "图库").setActiveColor(R.color.black))
                .addItem(new BottomNavigationItem(R.mipmap.add, "标签").setActiveColor(R.color.black))
                .addItem(new BottomNavigationItem(R.mipmap.history, "历史").setActiveColor(R.color.black))
                .addItem(new BottomNavigationItem(R.mipmap.home, "我的").setActiveColor(R.color.black))
                .setFirstSelectedPosition(lastSelectedPosition )
                .initialise();

        bottomNavigationBar.setTabSelectedListener(this);

        fragments = getFragments();
        setDefaultFragment();
    }

    /**
     * 设置默认的
     */
    private void setDefaultFragment() {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.id_frameLayout, fragments.get(0));
        transaction.commit();
    }

    private ArrayList<Fragment> getFragments() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(PictureFragment.newInstance("Picture"));
        fragments.add(LabelFragment.newInstance("Label"));
        fragments.add(HistoryFragment.newInstance("History"));
        fragments.add(MyselfFragment.newInstance("Home"));
        return fragments;
    }

    @Override
    public void onTabSelected(int position) {//未选中 -> 选中
        Log.d(TAG, "onTabSelected() called with: " + "position = [" + position + "]");
        if (fragments != null) {
            if (position < fragments.size()) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                Fragment fragment = fragments.get(position);
                if (fragment.isAdded()) {
                    ft.replace(R.id.id_frameLayout, fragment);
                } else {
                    ft.add(R.id.id_frameLayout, fragment);
                }
                ft.commitAllowingStateLoss();
            }
        }

    }

    @Override
    public void onTabUnselected(int position) {//选中 -> 未选中
        Log.d(TAG, "onTabUnselected() called with: " + "position = [" + position + "]");
        if (fragments != null) {
            if (position < fragments.size()) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                Fragment fragment = fragments.get(position);
                ft.remove(fragment);
                ft.commitAllowingStateLoss();
            }
        }
    }

    @Override
    public void onTabReselected(int position) {//选中 -> 选中

    }
}
