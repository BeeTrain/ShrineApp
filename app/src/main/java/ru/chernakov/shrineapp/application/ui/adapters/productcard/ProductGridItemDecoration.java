package ru.chernakov.shrineapp.application.ui.adapters.productcard;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import ru.chernakov.shrineapp.application.ui.fragments.ProductsFragment;

/**
 * Custom item decoration for a vertical {@link ProductsFragment} {@link RecyclerView}. Adds a
 * small amount of padding to the left of grid items, and a large amount of padding to the right.
 */
public class ProductGridItemDecoration extends RecyclerView.ItemDecoration {
    private int largePadding;
    private int smallPadding;

    public ProductGridItemDecoration(int largePadding, int smallPadding) {
        this.largePadding = largePadding;
        this.smallPadding = smallPadding;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.left = smallPadding;
        outRect.right = smallPadding;
//        outRect.top = largePadding;
//        outRect.bottom = largePadding;
    }
}
