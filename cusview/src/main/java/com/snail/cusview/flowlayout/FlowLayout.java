package com.snail.cusview.flowlayout;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Snail
 * @Since 2021/6/3
 * 自定义流式布局
 */
public class FlowLayout extends ViewGroup {
    //水平间隔
    private float mHorizontalSpace = dp2px(16f);
    //竖直间隔
    private float mVerticalSpace = dp2px(8f);
    //记录每一行的子View，用于Layout
    private List<List<View>> allViews = new ArrayList<>();
    //记录每一行的高，用于Layout
    private List<Integer> lineHeights = new ArrayList<>();

    public FlowLayout(Context context) {
        super(context);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int childCount = getChildCount();
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();

        int selfWidth = MeasureSpec.getSize(widthMeasureSpec);//ViewGroup解析父View给的宽度
        int selfHeight = MeasureSpec.getSize(heightMeasureSpec);//ViewGroup解析父View给的宽度
        //保存一行中所有使用的View
        List<View> lineViews = new ArrayList<>();
        //一行中已经使用的宽度
        int lineUsedWidth = 0;
        //一行的高度
        int lineHeight = 0;

        //测量过程中，子View要求父View的宽高
        int parentNeededWidth = 0;
        int parentNeededHeight = 0;

        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            //将childView转换成MeasuresSpec
            LayoutParams layoutParams = childView.getLayoutParams();
            if (childView.VISIBLE != View.GONE) {
                int childWidthMeasureSpec = getChildMeasureSpec(widthMeasureSpec,
                        paddingLeft + paddingRight, layoutParams.width);
                int childHeightMeasureSpec = getChildMeasureSpec(heightMeasureSpec,
                        paddingTop + paddingBottom, layoutParams.height);
                childView.measure(childWidthMeasureSpec, childHeightMeasureSpec);

                //获取子View的宽高
                int childViewWidth = childView.getMeasuredWidth();
                int childViewHeight = childView.getMeasuredHeight();
                if (lineUsedWidth + childViewWidth + mHorizontalSpace > selfWidth) {
                    //换行，就可以知道当前ViewGroup所需要的宽高了
                    allViews.add(lineViews);
                    lineHeights.add(lineHeight);

                    parentNeededHeight = (int) (parentNeededHeight + lineHeight + mVerticalSpace);
                    parentNeededWidth = (int) Math.max(parentNeededWidth, lineUsedWidth + mHorizontalSpace);

                    lineViews = new ArrayList<>();
                    lineUsedWidth = 0;
                    lineHeight = 0;
                }
                //记录每一行的View
                lineViews.add(childView);
                //每行都有自己的宽高
                lineUsedWidth = (int) (lineUsedWidth + childViewWidth + mHorizontalSpace);
                lineHeight = Math.max(lineHeight, childViewHeight);

                //处理最后一行数据
                if (i == childCount - 1) {
                    allViews.add(lineViews);
                    lineHeights.add(lineHeight);
                    parentNeededWidth = (int) Math.max(parentNeededWidth, lineUsedWidth + mHorizontalSpace);
                    parentNeededHeight = (int) (parentNeededHeight + lineHeight + mVerticalSpace);
                }
            }
        }

        //再度量自己，保存
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int realWidth = (widthMode == MeasureSpec.EXACTLY) ? selfWidth : parentNeededWidth;
        int realHeight = (heightMode == MeasureSpec.EXACTLY) ? selfHeight : parentNeededHeight;
        setMeasuredDimension(realWidth, realHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int lineCount = allViews.size();

        int curL = getPaddingLeft();
        int curT = getPaddingTop();

        for (int i = 0; i < lineCount; i++) {
            List<View> lineViews = allViews.get(i);

            int lineHeight = lineHeights.get(i);
            for (int j = 0; j < lineViews.size(); j++) {
                View view = lineViews.get(j);
                int left = curL;
                int top = curT;

//                int right = left + view.getWidth();
//                int bottom = top + view.getHeight();

                int right = left + view.getMeasuredWidth();
                int bottom = top + view.getMeasuredHeight();
                view.layout(left, top, right, bottom);
                curL = (int) (right + mHorizontalSpace);
            }
            curT = (int) (curT + lineHeight + mVerticalSpace);
            curL = getPaddingLeft();
        }
    }

    private static float dp2px(Float value) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, Resources.getSystem().getDisplayMetrics());
    }
}
