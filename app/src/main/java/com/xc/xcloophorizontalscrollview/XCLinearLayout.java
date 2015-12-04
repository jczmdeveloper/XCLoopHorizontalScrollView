package com.xc.xcloophorizontalscrollview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by caizhiming on 2015/12/4.
 */
public class XCLinearLayout extends LinearLayout {
    public XCLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public XCLinearLayout(Context context) {
        super(context);
        setChildrenDrawingOrderEnabled(true);
    }

    /**
     *
     * @param childCount
     * @param order
     * @return
     */
    @Override
    protected int getChildDrawingOrder(int childCount, int order) {
        if (order == 2) {
            return 1;
        } else if (order == 1) {
            return 2;
        } else
            return order;
    }
}
