package com.xc.xcloophorizontalscrollview;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private XCLoopHorizontalScrollView mLoopHsView;
    private List<View> mItemViewList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLoopHsView = (XCLoopHorizontalScrollView) findViewById(R.id.loop_hs_view);
        int[] resIds = new int[3];
        resIds[0] = R.drawable.img1;
        resIds[1] = R.drawable.img2;
        resIds[2] = R.drawable.img3;
        for (int i = 0; i < 3; i++) {
            View itemView = LayoutInflater.from(this).
                    inflate(R.layout.layout_loop_hs_view_item, null);
            RelativeLayout content = (RelativeLayout) itemView.findViewById(R.id.rl_content);
            TextView textView = (TextView) itemView.findViewById(R.id.tv_content);
            textView.setText("Item View "+(i+1));
            content.setBackgroundResource(resIds[i]);
            mItemViewList.add(itemView);
        }
        mLoopHsView.addItemViews(mItemViewList);
    }

}
