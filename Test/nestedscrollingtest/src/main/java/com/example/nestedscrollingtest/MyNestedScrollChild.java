package com.example.nestedscrollingtest;

import android.content.Context;
import android.support.v4.view.NestedScrollingChild;
import android.support.v4.view.NestedScrollingChildHelper;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by Administrator on 2017/2/14.
 */
public class MyNestedScrollChild extends LinearLayout implements NestedScrollingChild{

    private int showHeight;
    private int lastY;
    private final int[] offset = new int[2];
    private final int[] consumed = new int[2];

    private NestedScrollingChildHelper mScrollingChildHelper;

    public MyNestedScrollChild(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyNestedScrollChild(Context context) {
        super(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //第一次测量，因为布局文件中高度是wrap_content，因此测量模式为ATMOST，即高度不能超过父控件的剩余空间
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        showHeight = getMeasuredHeight();  //获得这个高度有什么用？？
        //第二次测量，对高度没有任何限制，那么测量出来的就是完全展示内容所需要的高度
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(0,MeasureSpec.UNSPECIFIED);//重新确定高度
        super.onMeasure(widthMeasureSpec,heightMeasureSpec);//绘制view
    }

    @Override
    public void scrollTo(int x, int y) {   //scrollBy 内部会调用scrollTo 限制滑动的范围
        int maxY = getMeasuredHeight() - showHeight ;
        if (y > maxY) {
            y = maxY;
        } if (y < 0) {
            y = 0;
        }
        super.scrollTo(x,y); //相当于 重新设定了x 和 y
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                lastY = (int)event.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                //下面的代码在 http://blog.csdn.net/al4fun/article/details/53889075
                int y = (int)event.getRawY();
                int dy = y - lastY;
                lastY = y;

                if(startNestedScroll(ViewCompat.SCROLL_AXIS_HORIZONTAL) &&  //为毛是横坐标
                        dispatchNestedPreScroll(0,dy,consumed,offset)){//通知父类进行一部分的滚动
                    int remain = dy - consumed[1];
                    if(remain != 0) {
                        scrollBy(0,-remain); //因为是向上滑
                    }
                } else {   //否则就子类滑动
                    scrollBy(0,-dy);
                }
        }
        return true;
    }

    @Override
    public boolean startNestedScroll(int axes) {
        return getScrollingChildHelper().startNestedScroll(axes);
    }

    @Override
    public void setNestedScrollingEnabled(boolean enabled) {
        getScrollingChildHelper().setNestedScrollingEnabled(enabled);
    }

    @Override
    public boolean isNestedScrollingEnabled() {
        return getScrollingChildHelper().isNestedScrollingEnabled();
    }

    @Override
    public void stopNestedScroll() {
        getScrollingChildHelper().stopNestedScroll();
    }

    @Override
    public boolean hasNestedScrollingParent() {
        return getScrollingChildHelper().hasNestedScrollingParent();
    }

    @Override
    public boolean dispatchNestedScroll(int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int[] offsetInWindow) {
        return getScrollingChildHelper().dispatchNestedScroll(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, offsetInWindow);
    }

    @Override   //子view 在滑动前要执行的动作
    public boolean dispatchNestedPreScroll(int dx, int dy, int[] consumed, int[] offsetInWindow) {
        return getScrollingChildHelper().dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow);
    }

    @Override
    public boolean dispatchNestedFling(float velocityX, float velocityY, boolean consumed) {
        return getScrollingChildHelper().dispatchNestedFling(velocityX, velocityY, consumed);
    }

    @Override
    public boolean dispatchNestedPreFling(float velocityX, float velocityY) {
        return getScrollingChildHelper().dispatchNestedPreFling(velocityX, velocityY);
    }

    private NestedScrollingChildHelper getScrollingChildHelper() {
        if (mScrollingChildHelper == null) {
            mScrollingChildHelper = new NestedScrollingChildHelper(this);
            mScrollingChildHelper.setNestedScrollingEnabled(true);
        }
        return mScrollingChildHelper;
    }

}
