package com.drama.githubsearch.view.model;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.drama.githubsearch.R;

import tellh.com.stickyheaderview_rv.adapter.StickyHeaderViewAdapter;
import tellh.com.stickyheaderview_rv.adapter.ViewBinder;

/**
 * Created by tlh on 2017/1/22 :)
 */

public class ItemUserViewBinder extends ViewBinder<ItemUser, ItemUserViewBinder.ViewHolder> {

    private OnItemClickListener listener;

    @Override
    public ViewHolder provideViewHolder(View itemView) {
        return new ViewHolder(itemView);
    }

    @Override
    public void bindView(StickyHeaderViewAdapter adapter, ViewHolder holder, int position, ItemUser entity) {
        holder.user = entity;
        holder.name.setText(entity.getName());
        Glide.with(holder.ivAvatar.getContext()).load(entity.getAvatarUrl()).into(holder.ivAvatar);

        holder.ivStar.setVisibility(View.GONE);
        if (entity.isFavorite()) {
            holder.ivStar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemLayoutId(StickyHeaderViewAdapter adapter) {
        return R.layout.item_user;
    }

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    class ViewHolder extends ViewBinder.ViewHolder {
        public TextView name;
        public ImageView ivAvatar;
        public ImageView ivStar;
        public ItemUser user;

        public ViewHolder(View rootView) {
            super(rootView);
            this.name = rootView.findViewById(R.id.name);
            this.ivAvatar = rootView.findViewById(R.id.avatar);
            this.ivStar = rootView.findViewById(R.id.star);

            rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null && user != null) {
                        listener.onClickUser(user);
                    }
                }
            });
        }
    }

    interface OnItemClickListener {
        void onClickUser(ItemUser user);
    }
}
