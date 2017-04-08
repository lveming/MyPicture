package com.lm.demo.mypicture.ui.frament;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.lm.demo.mypicture.R;
import com.lm.demo.mypicture.adapter.MyAdapter;
import com.lm.demo.mypicture.util.HttpUtil;
import com.lm.demo.mypicture.util.PictureBean;
import com.lm.demo.mypicture.util.PictureUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import me.yuqirong.cardswipelayout.CardConfig;
import me.yuqirong.cardswipelayout.CardItemTouchHelperCallback;
import me.yuqirong.cardswipelayout.CardLayoutManager;
import me.yuqirong.cardswipelayout.OnSwipeListener;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/4/6.
 */

public class PictureFragment extends Fragment {

    private RecyclerView recyclerView;
    private MyAdapter adapter;
    private List<String> list=new ArrayList<>();
    private static final String TAG = "PictureFragment";
    private String url;

    public static PictureFragment newInstance(String param1) {
        PictureFragment fragment = new PictureFragment();
        Bundle args = new Bundle();
        args.putString("args1", param1);
        fragment.setArguments(args);
        return fragment;
    }


    public PictureFragment() {
        super();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.priture_fragment, container, false);
        Bundle bundle = getArguments();
        String args1 = bundle.getString("args1");
        getActivity().setTitle(args1);
        recyclerView= (RecyclerView) view.findViewById(R.id.id_priture_RecyclerView);
        adapter=new MyAdapter(list,getActivity());
        recyclerView.setAdapter(adapter);
        initData();
        initRecycler();

        return view;
    }

    private void initRecycler() {

        CardItemTouchHelperCallback cardCallback = new CardItemTouchHelperCallback(recyclerView.getAdapter(), list);
        cardCallback.setOnSwipedListener(new OnSwipeListener() {
            @Override
            public void onSwiping(RecyclerView.ViewHolder viewHolder, float ratio, int direction) {
                MyAdapter.MyViewHolder myHolder = (MyAdapter.MyViewHolder) viewHolder;
                viewHolder.itemView.setAlpha(1 - Math.abs(ratio) * 0.2f);
                if (direction == CardConfig.SWIPING_LEFT) {
                    myHolder.dislikeImageView.setAlpha(Math.abs(ratio));
                } else if (direction == CardConfig.SWIPING_RIGHT) {
                    myHolder.likeImageView.setAlpha(Math.abs(ratio));
                } else {
                    myHolder.dislikeImageView.setAlpha(0f);
                    myHolder.likeImageView.setAlpha(0f);
                }
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, Object o, int direction) {
                MyAdapter.MyViewHolder myHolder = (MyAdapter.MyViewHolder) viewHolder;
                viewHolder.itemView.setAlpha(1f);
                myHolder.dislikeImageView.setAlpha(0f);
                myHolder.likeImageView.setAlpha(0f);
//                Toast.makeText(MainActivity.this, direction == CardConfig.SWIPED_LEFT ? "不感兴趣" : "已加入购物车", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onSwipedClear() {
                Toast.makeText(getActivity(), "没有数据啦", Toast.LENGTH_SHORT).show();
                recyclerView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
//                        initData();
//                        recyclerView.getAdapter().notifyDataSetChanged();
                    }
                }, 3000L);
            }
        });

        final ItemTouchHelper touchHelper = new ItemTouchHelper(cardCallback);
        final CardLayoutManager cardLayoutManager = new CardLayoutManager(recyclerView, touchHelper);
        recyclerView.setLayoutManager(cardLayoutManager);
        touchHelper.attachToRecyclerView(recyclerView);
    }
    private void initData() {

        url="http://gank.io/api/data/福利/100/1";
        HttpUtil.sentOkHttpRequest(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: 解析错误" + e );
               // Toast.makeText(getActivity(),"图片地址解析错误",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e(TAG, "onResponse: 解析成功" );
                String responseText=response.body().string();
                final String info=new String(responseText.getBytes(),"UTF-8");
//                Log.i(TAG, "onResponse: info"+info);
                PictureBean pictureBean= PictureUtil.getData(info);
                List<PictureBean.ResultsBean> listResults= pictureBean.getResults();
                //String results=listResults.get(0).getUrl();
                for (int i = 0; i < listResults.size(); i++) {
                    list.add(listResults.get(i).getUrl());
                }
                if (getActivity()==null){
                    Log.e(TAG, "onResponse: getActivity()为空-----------------------------------------------------------------" );
                    return;
                }
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        recyclerView.getAdapter().notifyDataSetChanged();
                    }
                });
            }
        });
    }
}
