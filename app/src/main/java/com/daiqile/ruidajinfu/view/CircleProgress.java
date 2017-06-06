package com.daiqile.ruidajinfu.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2016/11/10.
 */

public class CircleProgress extends View {

    private int mMeasureHeight;
    private int mMeasureWidth;

    private Paint mCirclePaint = new Paint();
    private RectF mCircleRectF;
    private float mCircleXY;

    private Paint mArcPaint = new Paint();
    private RectF mArcRectF;

    private RectF mArcRectFInner;

    private Paint mArcPaintSweep = new Paint();
    private float mSweepValue = 0;

    private Paint mTextPaint = new Paint();
    private String mShowText = "0%";

    private Paint mTextPaint2 = new Paint();

    private Paint mPaintDegree = new Paint();

    private float length = 0;

    private float scale;

    public CircleProgress(Context context) {
        super(context);
    }

    public CircleProgress(Context context, AttributeSet attrs) {
        super(context, attrs);
        scale = context.getResources().getDisplayMetrics().density;
    }

    public CircleProgress(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        mMeasureWidth = MeasureSpec.getSize(widthMeasureSpec);
        mMeasureHeight = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(mMeasureWidth, mMeasureHeight);
        initView();
    }

    private void initView() {
        if (mMeasureHeight >= mMeasureWidth) {
            length = mMeasureWidth;
        } else {
            length = mMeasureHeight;
        }

        mCircleXY = length / 2;
        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setColor(Color.WHITE);
        mCirclePaint.setStyle(Paint.Style.STROKE);
        mCirclePaint.setStrokeWidth(5);

        mCircleRectF = new RectF(
                (float) (10),
                (float) (10),
                (float) (length - 10),
                (float) (length - 10)
        );

        mArcRectFInner = new RectF(
                (float) (length * 0.2 + 2),
                (float) (length * 0.2 + 2),
                (float) (length * 0.8 - 2),
                (float) (length * 0.8 - 2)
        );

        mArcRectF = new RectF(
                (float) (length * 0.1),
                (float) (length * 0.1),
                (float) (length * 0.9),
                (float) (length * 0.9)
        );

//        mArcPaint = new Paint();
        mArcPaint.setAntiAlias(true);
        mArcPaint.setColor(Color.parseColor("#ffefbf"));
        mArcPaint.setStrokeWidth((float) (length * 0.1));
        mArcPaint.setStyle(Paint.Style.STROKE);

//        mArcPaintSweep = new Paint();
        mArcPaintSweep.setAntiAlias(true);
        mArcPaintSweep.setStrokeWidth((float) (length * 0.1));
        mArcPaintSweep.setColor(Color.parseColor("#fbd268"));
        mArcPaintSweep.setStyle(Paint.Style.STROKE);

//        mTextPaint = new TextPaint();
        mTextPaint.setColor(Color.WHITE);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        mTextPaint.setTextSize(80);

//        mTextPaint2 = new TextPaint();
        mTextPaint2.setColor(Color.WHITE);
        mTextPaint2.setTextAlign(Paint.Align.CENTER);
        mTextPaint2.setTextSize(50);

//        mPaintDegree = new Paint();
        mPaintDegree.setStrokeWidth(3);
        mPaintDegree.setColor(Color.WHITE);
        mPaintDegree.setAntiAlias(true);
        mPaintDegree.setStyle(Paint.Style.STROKE);
        mPaintDegree.setTextSize(25);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawArc(mArcRectFInner, 150, 240, false, mCirclePaint);
        canvas.drawArc(mCircleRectF, 150, 240, false, mCirclePaint);
        canvas.drawArc(mArcRectF, 150, 240, false, mArcPaint);
        canvas.drawArc(mArcRectF, 150, (float) (mSweepValue + 0.005), false, mArcPaintSweep);
        canvas.drawText(mShowText, 0, mShowText.length(), mCircleXY, mCircleXY, mTextPaint);
        float mRadius = (float)(mCircleXY - length * 0.2 - 2);
        canvas.translate(mCircleXY, mCircleXY);
        for (int i = 0; i < 12; i++) {
            if (i != 2 && i != 3 && i != 4) {
                canvas.drawLine(mRadius, 0, mRadius - 20, 0, mPaintDegree);
                Rect rect = new Rect();
                if (i == 1) {
                    mPaintDegree.getTextBounds("100", 0, 1, rect);
                    canvas.drawText("100", mRadius - 25 - mPaintDegree.measureText("100") , rect.height() / 2, mPaintDegree);
                }
                if (i == 5) {
                    mPaintDegree.getTextBounds("0", 0, 1, rect);
                    canvas.drawText("0", mRadius - 25 - mPaintDegree.measureText("0") , rect.height() / 2, mPaintDegree);
                }
            }
            canvas.rotate(30);
        }
        canvas.restore();
    }

    public void setmShowText(String text) {
        mShowText = text;
        this.invalidate();
    }

    public String getmShowText() {
        return mShowText;
    }

    public void setmSweepValue(float sweepValue) {
        mSweepValue = sweepValue;
        this.invalidate();
    }

    public float getmSweepValue() {
        return mSweepValue;
    }

}
