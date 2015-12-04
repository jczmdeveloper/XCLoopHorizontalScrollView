# XCLoopHorizontalScrollView
自定义横向无限循环ScrollView-XCLoopHorizontalScrollView，最少支持3个Item View。
效果图如下：


![iamge](https://raw.githubusercontent.com/jczmdeveloper/XCLoopHorizontalScrollView/master/ScreenShots/01.gif)

使用方法如下：

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
