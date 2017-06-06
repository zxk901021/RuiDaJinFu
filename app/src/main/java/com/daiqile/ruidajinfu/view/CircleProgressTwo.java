package com.daiqile.ruidajinfu.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2016/11/23.
 */

public class CircleProgressTwo extends View {

    private int mMeasureHeight;
    private int mMeasureWidth;

    private float length;
    private float radius;
    private float smallRadius;

    private Paint mCirclePaint;
    private float mCircleXY;

    private Paint mArcPaint;
    private RectF mArcRectF;

    private Paint mTextPaint;

    private float mSweepAngle;

    private int progress;

    private Paint mSmallCirclePaint;

    private float scale;

    private Rect rect1;

    public CircleProgressTwo(Context context) {
        super(context);
    }

    public CircleProgressTwo(Context context, AttributeSet attrs) {
        super(context, attrs);
        scale = context.getResources().getDisplayMetrics().density;
    }

    public CircleProgressTwo(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        mMeasureHeight = MeasureSpec.getSize(heightMeasureSpec);
        mMeasureWidth = MeasureSpec.getSize(widthMeasureSpec);
        setMeasuredDimension(mMeasureWidth, mMeasureHeight);
        initView();
    }

    private void initView() {
        if (mMeasureWidth >= mMeasureHeight) {
            length = mMeasureHeight;
        } else {
            length = mMeasureWidth;
        }

        mCircleXY = length / 2;
        radius = (float) (length * 0.4);
        smallRadius = (float) (length * 0.08);
        mCirclePaint = new Paint();
        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setColor(Color.WHITE);
        mCirclePaint.setStrokeWidth((float) (length * 0.03));
        mCirclePaint.setStyle(Paint.Style.STROKE);

        mArcPaint = new Paint();
        mArcPaint.setAntiAlias(true);
        mArcPaint.setStyle(Paint.Style.STROKE);
        mArcPaint.setColor(Color.parseColor("#fbd268"));
        mArcPaint.setStrokeWidth((float) (length * 0.03));
        mArcPaint.setAntiAlias(true);

        mArcRectF = new RectF(
                (float) (length * 0.1),
                (float) (length * 0.1),
                (float)(length * 0.9),
                (float)(length * 0.9)
        );

        mSmallCirclePaint = new Paint();
        mSmallCirclePaint.setAntiAlias(true);
        mSmallCirclePaint.setStyle(Paint.Style.FILL);
        mSmallCirclePaint.setColor(Color.parseColor("#fbd268"));

        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setColor(Color.BLACK);
        mTextPaint.setTextSize(35);

        rect1 = new Rect();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(mCircleXY, mCircleXY, radius, mCirclePaint);
        canvas.drawArc(mArcRectF, -90, mSweepAngle, false, mArcPaint);
        canvas.translate(mCircleXY, mCircleXY);
        canvas.drawCircle((float) (radius * Math.sin(Math.toRadians((double)mSweepAngle))), -(float)(radius * Math.cos(Math.toRadians((double)(mSweepAngle)))), smallRadius, mSmallCirclePaint);
        mTextPaint.getTextBounds(String.valueOf(progress), 0, String.valueOf(progress).length(), rect1);
        canvas.drawText(String.valueOf(progress), (float) (radius * Math.sin(Math.toRadians((double)mSweepAngle)) - rect1.width() / 2), rect1.height() / 2 - (float)(radius * Math.cos(Math.toRadians((double)(mSweepAngle)))), mTextPaint);
    }

    public void setProgress(int progress) {
        this.progress = progress;
        mSweepAngle = progress * 360 / 100;
        invalidate();
    }

}
