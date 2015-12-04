package com.xc.xcloophorizontalscrollview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.widget.HorizontalScrollView;

import java.util.List;


/**
 * Created by caizhiming on 2015/12/4.
 */
public class XCLoopHorizontalScrollView extends HorizontalScrollView {

    private XCLinearLayout mLinearLayout;
    private boolean mFirst = true;
    private int mWidth = 0;
    private int mLLWidth = 0;
    private int mChildWidth = 0;
    private int mMoveShore = 100;
    private long mLastScrollTime = -1L;
    private int mLastScrollX = -1;

    public XCLoopHorizontalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public boolean addItemViews(List<View> viewList) {
        if (viewList == null || viewList.size() < 3) {
            return false;
        }
        mLinearLayout.removeAllViews();
        for (int i = 0; i < viewList.size(); i++) {
            mLinearLayout.addView(viewList.get(i));
        }
        startAnimation(mLinearLayout.getChildAt(1));
        return true;
    }

    private void init() {
        mLinearLayout = new XCLinearLayout(getContext());
        mLinearLayout.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
        this.addView(mLinearLayout);

        if (mLinearLayout.getChildCount() < 3) {
            return;
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (mFirst) {
            mWidth = getMeasuredWidth();
            mLLWidth = mLinearLayout.getMeasuredWidth();
            mChildWidth = mLinearLayout.getChildAt(0).getMeasuredWidth();
            if (mWidth == 0 || mLLWidth == 0 || mChildWidth == 0) {
            }
            scrollToMiddle(true);
            mFirst = false;
        }
    }

    private void scrollToMiddle(boolean smoothly) {
        if (mWidth == 0 || mLLWidth == 0 || mChildWidth == 0) {
        }
        int width = (mLLWidth - mWidth) >> 1;
        if (smoothly) {
            smoothScrollTo(width, 0);
        } else
            scrollTo(width, 0);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_UP:
                this.post(new Runnable() {
                    @Override
                    public void run() {
                        scrollToMiddle(true);
                    }
                });
                break;
        }
        return super.onTouchEvent(ev);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        // scroll view move to left size
        int scrollX = getScrollX();
        if (scrollX == 0) {
            return;
        }
        if (mLastScrollTime != -1L && System.currentTimeMillis() - mLastScrollTime > 1300
                && mLastScrollX != -1 && Math.abs(scrollX - mLastScrollX) > 150) {
            return;
        }
        if (scrollX < 40) {
        }
        if (scrollX <= mMoveShore && scrollX > 10) {
            moveToLeftItem();
        }
        // scroll view move to right size
        else if (scrollX >= mLLWidth - mWidth - mMoveShore && scrollX < mLLWidth - mWidth - 10) {
            moveToRightItem();
        }
        mLastScrollTime = System.currentTimeMillis();
        mLastScrollX = scrollX;
    }

    private void startAnimation(View view) {
        ViewPropertyAnimator animator = view.animate().scaleX(1.2f).scaleY(1.2f)
                .setDuration(200);
        animator.start();
    }

    private void stopAnimation(View view) {
        ViewPropertyAnimator animator = view.animate().scaleX(1.0f).scaleY(1.0f)
                .setDuration(100);
        animator.start();
    }

    private void moveToLeftItem() {
        View toRemoved = mLinearLayout.getChildAt(2);
        mLinearLayout.removeView(toRemoved);
        mLinearLayout.addView(toRemoved, 0);
        scrollBy(mChildWidth, 0);
        invalidate();
        stopAnimation(mLinearLayout.getChildAt(2));
        startAnimation(mLinearLayout.getChildAt(1));
    }

    private void moveToRightItem() {
        View toRemoved = mLinearLayout.getChildAt(0);
        mLinearLayout.removeView(toRemoved);
        mLinearLayout.addView(toRemoved, 2);
        scrollBy(-mChildWidth, 0);
        invalidate();
        stopAnimation(mLinearLayout.getChildAt(0));
        startAnimation(mLinearLayout.getChildAt(1));
    }

}
