package com.example.trnhxunnam.pshopmyclone.AdapterTouchHelperInGH;

import android.graphics.Canvas;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.example.trnhxunnam.pshopmyclone.Adapter.AdapterRecyclerStandard;

public class RecyclerItemTouchHelper extends ItemTouchHelper.SimpleCallback {

    private RecyclerItemTouchHelperListener listener;

    public RecyclerItemTouchHelper(int dragDirs, int swipeDirs,RecyclerItemTouchHelperListener listener) {
        super(dragDirs, swipeDirs);
        this.listener =  listener;
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
        return true;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        if(listener != null){
            listener.onSwiper(viewHolder,i,viewHolder.getAdapterPosition());
        }
    }

    @Override
    public void clearView(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        View view = ((AdapterRecyclerStandard.ViewHolder01) viewHolder).linearLayout_touch_item;
        getDefaultUIUtil().clearView(view);
    }

    @Override
    public int convertToAbsoluteDirection(int flags, int layoutDirection) {
        return super.convertToAbsoluteDirection(flags, layoutDirection);
    }

    @Override
    public void onSelectedChanged(@Nullable RecyclerView.ViewHolder viewHolder, int actionState) {
        if(viewHolder != null){
            View view = ((AdapterRecyclerStandard.ViewHolder01) viewHolder).relativeLayout;
            getDefaultUIUtil().onSelected(view);
        }
    }


    // su dung hieuj ung lam mo khi vuot remove
    @Override
    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        View view = ((AdapterRecyclerStandard.ViewHolder01) viewHolder).linearLayout_touch_item;
        getDefaultUIUtil().onDraw(c,recyclerView,view,dX,dY,actionState,isCurrentlyActive);
    }

    @Override
    public void onChildDrawOver(@NonNull Canvas c, @NonNull RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        View view = ((AdapterRecyclerStandard.ViewHolder01) viewHolder).linearLayout_touch_item;
        getDefaultUIUtil().onDrawOver(c,recyclerView,view,dX,dY,actionState,isCurrentlyActive);
    }

    /*
    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {

        final int dragFlags = 0;
        final int swipeFlags;
        swipeFlags = ([OK to swipe this view])
            ? ItemTouchHelper.START | ItemTouchHelper.END
            : 0;
        return makeMovementFlags(dragFlags, swipeFlags);
    }
    */

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {

        //int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN|ItemTouchHelper.START | ItemTouchHelper.END;
        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        //int dragFlags = 0;
        int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
        //int swipeFlags = ItemTouchHelper.START;
        //int swipeFlags = 0;

        return makeMovementFlags(dragFlags, swipeFlags);
        //return 0; // vo hieu hoa swipe
    }


    @Override
    public boolean isLongPressDragEnabled() {  // cho phep keo len hay keo xuong ? = viec sap xep lai item = cach keo len keo xuong
        return true;
    }

    @Override
    public boolean isItemViewSwipeEnabled() {   // cho phep keo trai hay keo phai ?
        return true;
    }


}

