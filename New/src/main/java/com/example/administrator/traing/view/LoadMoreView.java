package com.example.administrator.traing.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;

/**
 * Created by Administrator on 2017/8/20.
 */

public class LoadMoreView extends ScrollView {


    private int index = 0;
    private boolean isLoadMore = false;
    private OnRefreshListener onRefreshListener;

    public LoadMoreView(Context context) {
        this(context, null);
    }

    public LoadMoreView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadMoreView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);


        setOnTouchListener(new MyTouchEvent());

    }


    public interface OnRefreshListener {
        public void onLoadMore();

    }


    public void setOnRefreshListener(OnRefreshListener l) {
        this.onRefreshListener = l;
    }


    private class MyTouchEvent implements OnTouchListener {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {

                case MotionEvent.ACTION_DOWN:
                    break;
                case MotionEvent.ACTION_MOVE:

                    index++;

                    break;
                default:
                    break;
            }

            if (event.getAction() == MotionEvent.ACTION_UP && index > 0) {
                index = 0;
                View view = ((ScrollView) v).getChildAt(0);
                if (view.getMeasuredHeight() <= v.getScrollY() + v.getHeight()) {

                    isLoadMore = true;
                    if (onRefreshListener != null) {
                        onRefreshListener.onLoadMore();
                    }
                }
            }

            return false;
        }
    }
}
