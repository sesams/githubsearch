package com.drama.githubsearch.view.model;

import com.drama.githubsearch.R;

import tellh.com.stickyheaderview_rv.adapter.DataBean;
import tellh.com.stickyheaderview_rv.adapter.StickyHeaderViewAdapter;

/**
 * Created by tlh on 2017/1/22 :)
 */

public class ItemHeader extends DataBean {

    private String prefix;

    public String getPrefix() {
        return prefix;
    }

    public ItemHeader(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public int getItemLayoutId(StickyHeaderViewAdapter adapter) {
        return R.layout.item_header;
    }

    @Override
    public boolean shouldSticky() {
        return true;
    }
}
