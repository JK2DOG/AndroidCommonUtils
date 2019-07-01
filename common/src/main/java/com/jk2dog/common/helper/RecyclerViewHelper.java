package com.jk2dog.common.helper;

import android.content.Context;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jk2dog.common.itemdecoration.grid.GridLayoutDividerItemDecoration;
import com.jk2dog.common.itemdecoration.grid.GridLayoutSpaceEqualItemDecoration;
import com.jk2dog.common.itemdecoration.grid.GridLayoutSpaceItemDecoration;
import com.jk2dog.common.itemdecoration.linear.LinearLayoutSpaceItemDecoration;
import com.jk2dog.common.itemdecoration.linear.LinearLayoutSpaceItemDecoration_;

/**
 * Created by long on 2016/3/30.
 * 视图帮助类
 */
public class RecyclerViewHelper {

    private RecyclerViewHelper() {
        throw new RuntimeException("RecyclerViewHelper cannot be initialized!");
    }


    /**
     * 网格布局 space 10
     */
    public static void initRecyclerViewGridSpace(Context context, RecyclerView view, RecyclerView.Adapter adapter, int column, int space) {
        GridLayoutManager layoutManager = new GridLayoutManager(context, column,
                RecyclerView.VERTICAL, false);
        view.setLayoutManager(layoutManager);
        view.setItemAnimator(new DefaultItemAnimator());
        view.addItemDecoration(
                new GridLayoutSpaceItemDecoration.Builder().setSpaceSize(space).build());
        view.setAdapter(adapter);
    }


    /**
     * 等分 网格布局 space 10  rv左边自己设置padding
     */
    public static void initRecyclerViewEqualGridSpace(Context context, RecyclerView view, RecyclerView.Adapter adapter, int column, int space) {
        GridLayoutManager layoutManager = new GridLayoutManager(context, column,
                RecyclerView.VERTICAL, false);
        view.setLayoutManager(layoutManager);
        view.setItemAnimator(new DefaultItemAnimator());
        view.addItemDecoration(
                new GridLayoutSpaceEqualItemDecoration.Builder().setSpaceSize(space).build());
        view.setAdapter(adapter);
    }


    /**
     * 网格线
     */
    public static void initRecyclerViewGridDivider(Context context, RecyclerView view, RecyclerView.Adapter adapter, int column, int color, int dividerHeight) {
        GridLayoutManager layoutManager = new GridLayoutManager(context, column,
                RecyclerView.VERTICAL, false);
        view.setLayoutManager(layoutManager);
        view.setItemAnimator(new DefaultItemAnimator());
        view.addItemDecoration(
                new GridLayoutDividerItemDecoration.Builder().setDividerHeight(dividerHeight).setDividerColor(color).build());
        view.setAdapter(adapter);
    }


    /**
     * 垂直方向Space上下左右无背景间隔第一个没有itemdecoration
     */
    public static void initRecyclerViewSpaceVNoFirst(Context context, RecyclerView view, RecyclerView.Adapter adapter, int space) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        view.setLayoutManager(layoutManager);
        view.setItemAnimator(new DefaultItemAnimator());
        view.addItemDecoration(
                new LinearLayoutSpaceItemDecoration.Builder().setSpaceSize(space).build());
        view.setAdapter(adapter);
    }


    /**
     * 垂直方向Space上下左右无背景、间隔都有
     */
    public static void initRecyclerViewSpaceV(Context context, RecyclerView view, RecyclerView.Adapter adapter, int space) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        view.setLayoutManager(layoutManager);
        view.setItemAnimator(new DefaultItemAnimator());
        view.addItemDecoration(
                new LinearLayoutSpaceItemDecoration_.Builder().setSpaceSize(space).build());
        view.setAdapter(adapter);
    }


    /**
     * 水平方向Space上下左右无背景、间隔都有
     */
    public static void initRecyclerViewSpaceH(Context context, RecyclerView view, RecyclerView.Adapter adapter, int space) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        view.setLayoutManager(layoutManager);
        view.setItemAnimator(new DefaultItemAnimator());
        view.addItemDecoration(
                new LinearLayoutSpaceItemDecoration_.Builder().setSpaceSize(space)
                        .setOrientation(LinearLayoutManager.HORIZONTAL)
                        .build());
        view.setAdapter(adapter);
    }


    /**
     * 水平方向Space上下左右无背景间隔第一个没有itemdecoration
     */
    public static void initRecyclerViewSpaceHNoFirst(Context context, RecyclerView view, RecyclerView.Adapter adapter, int space) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        view.setLayoutManager(layoutManager);
        view.setItemAnimator(new DefaultItemAnimator());
        view.addItemDecoration(
                new LinearLayoutSpaceItemDecoration.Builder().setSpaceSize(space)
                        .setOrientation(LinearLayoutManager.HORIZONTAL).build());
        view.setAdapter(adapter);
    }


    public static void initRecyclerViewSpaceVCheckLayoutManager(Context context, RecyclerView view, RecyclerView.Adapter adapter, int space) {
        if (view.getLayoutManager() == null) {
            LinearLayoutManager layoutManager = new LinearLayoutManager(context);
            layoutManager.setOrientation(RecyclerView.VERTICAL);
            view.setLayoutManager(layoutManager);
            view.setItemAnimator(new DefaultItemAnimator());
            view.addItemDecoration(
                    new LinearLayoutSpaceItemDecoration_.Builder().setSpaceSize(space).build());
        }
        view.setAdapter(adapter);
    }

    public static void initRecyclerViewSpaceHCheckLayoutManager(Context context, RecyclerView view, RecyclerView.Adapter adapter, int space) {
        if (view.getLayoutManager() == null) {
            LinearLayoutManager layoutManager = new LinearLayoutManager(context);
            layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            view.setLayoutManager(layoutManager);
            view.setItemAnimator(new DefaultItemAnimator());
            view.addItemDecoration(
                    new LinearLayoutSpaceItemDecoration_.Builder().setSpaceSize(space)
                            .setOrientation(LinearLayoutManager.HORIZONTAL)
                            .build());
        }
        view.setAdapter(adapter);
    }

}
