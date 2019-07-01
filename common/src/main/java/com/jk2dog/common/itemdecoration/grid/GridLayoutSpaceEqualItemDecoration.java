package com.jk2dog.common.itemdecoration.grid;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.jk2dog.common.itemdecoration.utils.ItemUtil;
import com.jk2dog.common.utils.ScreenUtil;

public class GridLayoutSpaceEqualItemDecoration extends RecyclerView.ItemDecoration {
    private static final int DEFAULT_HEIGHT = 5;
    private int mSpaceSize;


    private GridLayoutSpaceEqualItemDecoration(Builder builder) {
        mSpaceSize = builder.spaceSize;
    }


    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int itemPosition = parent.getChildAdapterPosition(view);
        int size = ScreenUtil.dp2px(parent.getContext(), mSpaceSize);
        outRect.set(0,
                ItemUtil.isFirstRaw(parent, itemPosition, view) ? size : 0, size, size);
    }


    public static class Builder {

        private int spaceSize = DEFAULT_HEIGHT;


        public Builder() {
        }


        public Builder setSpaceSize(int spaceSize) {
            this.spaceSize = spaceSize;
            return this;
        }


        public GridLayoutSpaceEqualItemDecoration build() {
            return new GridLayoutSpaceEqualItemDecoration(this);
        }
    }
}
