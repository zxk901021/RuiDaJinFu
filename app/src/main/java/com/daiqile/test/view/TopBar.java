package com.daiqile.test.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.daiqile.test.R;
import com.daiqile.test.utils.DisplayUtil;


public class TopBar extends RelativeLayout {

    private ImageView mLeftImage, mRightImage;
    private TextView mTitleView;

    private LayoutParams mLeftParams, mTitleParams, mRightParams;

    private Drawable mLeftDrawable;

    private Drawable mRightDrawable;

    private float mTitleTextSize;
    private int mTitleTextColor;
    private String mTitle;

    private topbarClickListener mListener;

    public TopBar(Context context) {
        super(context);
    }

    public TopBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public TopBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        setBackgroundColor(0xFF1a1a26);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TopBar);
        mLeftDrawable = ta.getDrawable(R.styleable.TopBar_leftDrawable);

        mRightDrawable = ta.getDrawable(R.styleable.TopBar_rightDrawable);

        mTitleTextSize = ta.getDimension(R.styleable.TopBar_titleTextSize, 10);
        mTitleTextColor = ta.getColor(R.styleable.TopBar_titleTextColor, 0);
        mTitle = ta.getString(R.styleable.TopBar_title);

        ta.recycle();

        mLeftImage = new ImageView(context);
        mRightImage = new ImageView(context);
        mTitleView = new TextView(context);
        mTitleView.setMaxEms(6);
        mTitleView.setSingleLine();
        mTitleView.setEllipsize(TextUtils.TruncateAt.valueOf("END"));

        mLeftImage.setImageDrawable(mLeftDrawable);
        mLeftImage.setPadding(DisplayUtil.dip2px(context, 16), 0, DisplayUtil.dip2px(context, 16), 0);

        mRightImage.setImageDrawable(mRightDrawable);
        mRightImage.setPadding(DisplayUtil.dip2px(context, 16), 0, DisplayUtil.dip2px(context, 16), 0);

        mTitleView.setText(mTitle);
        mTitleView.setTextColor(mTitleTextColor);
        mTitleView.setTextSize(0, mTitleTextSize);
        mTitleView.setGravity(Gravity.CENTER);

        mLeftParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        mLeftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, TRUE);
        mLeftParams.addRule(RelativeLayout.CENTER_VERTICAL, TRUE);
        addView(mLeftImage, mLeftParams);

        mRightParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        mRightParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, TRUE);
        mRightParams.addRule(RelativeLayout.CENTER_VERTICAL, TRUE);
        addView(mRightImage, mRightParams);

        mTitleParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        mTitleParams.addRule(RelativeLayout.CENTER_IN_PARENT, TRUE);
        addView(mTitleView, mTitleParams);

        mRightImage.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.rightClick();
                }
            }
        });

        mLeftImage.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.leftClick();
                }
            }
        });

        setRightButton(false);
    }

    public void setOnTopbarClickListener(topbarClickListener mListener) {
        this.mListener = mListener;
    }


    public interface topbarClickListener {
        void leftClick();

        void rightClick();
    }

    public void setTitle(String title) {
        mTitleView.setText(title);
    }

    public void setButtonVisiable(int id, boolean flag) {
        if (flag) {
            if (id == 0) {
                mLeftImage.setVisibility(View.VISIBLE);
            } else {
                mRightImage.setVisibility(View.VISIBLE);
            }
        } else {
            if (id == 0) {
                mLeftImage.setVisibility(View.GONE);
            } else {
                mRightImage.setVisibility(View.GONE);
            }
        }
    }

    public void setRightButton(boolean flag) {
        if (flag) {

                mRightImage.setVisibility(View.VISIBLE);

        } else {

                mRightImage.setVisibility(View.GONE);

        }
    }

    public void setLeftButton(boolean flag) {
        if (flag) {

            mLeftImage.setVisibility(View.VISIBLE);

        } else {

            mLeftImage.setVisibility(View.GONE);

        }
    }

}
