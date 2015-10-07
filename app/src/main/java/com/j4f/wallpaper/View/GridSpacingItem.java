package com.j4f.wallpaper.View;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by pham on 7/24/2015.
 */
public class GridSpacingItem extends RecyclerView.ItemDecoration {
    public GridSpacingItem() {
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int ratio=parent.getWidth()-(view.getWidth()*3);
        int space= ratio/4;
        outRect.left=space;
        outRect.top=space;
    }
}
