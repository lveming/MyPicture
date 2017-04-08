package com.lm.demo.mypicture.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.lm.demo.mypicture.R;

import java.util.List;

/**
 * Created by Administrator on 2017/4/8.
 */

public class MyAdapter extends RecyclerView.Adapter{

    private List<String> list;
    private Context mContext;
    public MyAdapter(List list, Context context) {
        this.list=list;
        this.mContext=context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_picture, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        Glide.with(mContext)
                .load(list.get(position))
                .error(R.drawable.error)
                .into(((MyViewHolder) holder).avatarImageView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public ImageView avatarImageView;
        public ImageView likeImageView;
        public ImageView dislikeImageView;
        public MyViewHolder(View itemView) {
            super(itemView);
            avatarImageView = (ImageView) itemView.findViewById(R.id.iv_avatar);
            likeImageView = (ImageView) itemView.findViewById(R.id.iv_like);
            dislikeImageView = (ImageView) itemView.findViewById(R.id.iv_dislike);
        }
    }
}
