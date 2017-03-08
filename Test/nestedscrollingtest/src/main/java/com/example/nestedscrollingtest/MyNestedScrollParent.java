package com.example.nestedscrollingtest;

import android.content.Context;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.NestedScrollingParentHelper;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/2/14.
 */
public class MyNestedScrollParent extends LinearLayout implements NestedScrollingParent {
    private NestedScrollingParentHelper mParentHelper;
    private ImageView img;
    private TextView tv;
    private MyNestedScrollChild nsc;
    private int imgHeight;
    private int tvHeight;

    public MyNestedScrollParent(Context context) {
        super(context);
        init();
    }

    public MyNestedScrollParent(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mParentHelper = new NestedScrollingParentHelper(this);
    }

    @Override
    protected void onFinishInflate() {
        img = (ImageView) getChildAt(0);
        tv = (TextView) getChildAt(1);
        nsc = (MyNestedScrollChild) getChildAt(2);
        img.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if(imgHeight<=0){
                    imgHeight = img.getMeasuredHeight();
                }
            }
        });
        tv.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (tvHeight <= 0) {
                    tvHeight = tv.getMeasuredHeight();
                }
            }
        });
    }

    //在此可以判断参数target是哪一个子view以及滚动的方向，然后决定是否要配合其进行嵌套滚动
    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        if(target instanceof MyNestedScrollChild){
            return true;
        }
        return false;
    }

    @Override
    public void onNestedScrollAccepted(View child, View target, int axes) {
        mParentHelper.onNestedScrollAccepted(child, target, axes);
    }

    @Override
    public void onStopNestedScroll(View child) {
        mParentHelper.onStopNestedScroll(child);
    }

    @Override
    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
        if(showImg(dy)||hideImg(dy)){//如果需要显示或隐藏图片，即需要自己(parent)滚动
            scrollBy(0,-dy);
            consumed[1] = dy;
        }
    }

    //下拉的时候是否要向下滚动以显示图片
    public boolean showImg(int dy){
        if(dy >0){
            if(getScrollY()>0&&nsc.getScrollY()==0){
                return true;
            }
        }
        return false;
    }

    //上拉的时候，是否要向上滚动，隐藏图片
    public boolean hideImg(int dy) {
        if(dy < 0) {
            if(getScrollY()<imgHeight) {
                return true;
            }
        }
        return false;
    }

    //scrollBy内部会调用scrollTo
    //限制滚动范围
    @Override
    public void scrollTo(int x, int y) {
        if (y < 0) {
            y = 0;
        }
        if (y > imgHeight) {
            y = imgHeight;
        }

        super.scrollTo(x, y);
    }


}
