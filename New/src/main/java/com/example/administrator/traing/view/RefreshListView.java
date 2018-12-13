package com.example.administrator.traing.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import com.example.administrator.traing.R;

/**
 * Created by Administrator on 2017/8/29.
 */

public class RefreshListView extends ListView {

    private View footView;
    private int footViewMeasuredHeight;
    private boolean isMore = false;

    public RefreshListView(Context context) {
        this(context, null);
    }

    public RefreshListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RefreshListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initFooterView(context);
    }


    public interface OnRefresh {

        void onLoadMore();
    }

    private OnRefresh onRefresh;

    public void setOnRefresh(OnRefresh onRefresh) {
        this.onRefresh = onRefresh;
    }

    private void initFooterView(Context context) {
        footView = LayoutInflater.from(context).inflate(R.layout.listview_footer, null);
        footView.measure(0, 0);
        footViewMeasuredHeight = footView.getMeasuredHeight();
        footView.setPadding(0, -footViewMeasuredHeight, 0, 0);

        addFooterView(footView);

        setOnScrollListener(new MyOnScrollListener());
    }


    private class MyOnScrollListener implements OnScrollListener {
        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {
            if (scrollState == OnScrollListener.SCROLL_STATE_IDLE || scrollState == OnScrollListener.SCROLL_STATE_FLING) {
                if (getLastVisiblePosition() == getCount() - 1) {

                    footView.setPadding(10, 10, 10, 10);

                    isMore = true;

                    if (onRefresh != null) {
                        onRefresh.onLoadMore();
                    }

                }

            }


        }

        @Override
        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

        }
    }
}
