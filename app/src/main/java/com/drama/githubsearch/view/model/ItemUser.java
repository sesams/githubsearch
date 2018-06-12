package com.drama.githubsearch.view.model;

import com.drama.githubsearch.R;

import tellh.com.stickyheaderview_rv.adapter.DataBean;
import tellh.com.stickyheaderview_rv.adapter.StickyHeaderViewAdapter;

public class ItemUser extends DataBean {

    private String name;
    private String avatarUrl;
    private boolean isFavorite;
    private boolean shouldSticky;

    public ItemUser() {
    }

    public ItemUser(String name, String avatarUrl, boolean isFavorite) {
        this.name = name;
        this.avatarUrl = avatarUrl;
        this.isFavorite = isFavorite;
    }

    @Override
    public int getItemLayoutId(StickyHeaderViewAdapter adapter) {
        return R.layout.item_user;
    }

    @Override
    public boolean shouldSticky() {
        return shouldSticky;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public boolean isShouldSticky() {
        return shouldSticky;
    }

    public void setShouldSticky(boolean shouldSticky) {
        this.shouldSticky = shouldSticky;
    }
}