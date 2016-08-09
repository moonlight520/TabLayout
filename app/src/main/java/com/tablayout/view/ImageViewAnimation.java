package com.tablayout.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * Created by moon on 2016/8/3.
 */
public class ImageViewAnimation extends View {
    private Paint mPaint ,mPaint1;
/*
    private int height, width;
    private int mCount;*/
    private float mSweepAnglePer;
    private float mSweepAngle;
    BarAnimation anim;
    private float mColorWheelRadius;
    private float circleStrokeWidth;
    private float pressExtraStrokeWidth;
    private RectF mColorWheelRectangle = new RectF();
    public ImageViewAnimation(Context context) {

        super(context);
        init();
    }

    public ImageViewAnimation(Context context, AttributeSet attrs) {
        super(context,attrs);
        init();
    }

    public ImageViewAnimation(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();

    }
    public void init(){
        mPaint = new Paint();
        mPaint.setAntiAlias(false);
        mPaint.setColor(Color.GRAY);
        mPaint.setStrokeWidth((float) 10.0);
        mPaint.setStyle(Paint.Style.STROKE);
        mSweepAngle = 0;
        mPaint1 = new Paint();
        mPaint1.setAntiAlias(false);
        mPaint1.setColor(Color.WHITE);
        mPaint1.setStrokeWidth((float) 10.0);
        mPaint1.setStyle(Paint.Style.STROKE);

    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //canvas.drawArc(mColorWheelRectangle, -90, 360, false, mPaint);
        canvas.drawArc(mColorWheelRectangle, -90, mSweepAnglePer, false, mPaint);
        //canvas.drawArc(mColorWheelRectangle,-90,mSweepAnglePer,false,mPaint1);
        anim = new BarAnimation();
        anim.setDuration(2000);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int height = getDefaultSize(getSuggestedMinimumHeight(),
                heightMeasureSpec);
        int width = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        int min = Math.min(width/2, height/2);
        setMeasuredDimension(min, min);
        mColorWheelRadius = min - circleStrokeWidth - pressExtraStrokeWidth-40;
        mColorWheelRectangle.set(circleStrokeWidth + pressExtraStrokeWidth, circleStrokeWidth + pressExtraStrokeWidth,
                mColorWheelRadius, mColorWheelRadius);
    }

    @Override
    public void setPressed(boolean pressed) {
        super.setPressed(pressed);
        if(pressed){
            Log.e("==1","--");
            mPaint.setStrokeWidth(20);
            mPaint.setColor(Color.GRAY);
            mPaint.setStrokeWidth(circleStrokeWidth+pressExtraStrokeWidth);
            mSweepAnglePer=-mSweepAnglePer;
        }else{
            Log.e("no1","--");
            mPaint.setStrokeWidth(20);
            mPaint.setColor(Color.BLUE);
            mPaint.setStrokeWidth(circleStrokeWidth);
        }

    }

    public class BarAnimation extends Animation {

        public BarAnimation() {
        }

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            super.applyTransformation(interpolatedTime, t);

            if (interpolatedTime < 1.0f) {
                mSweepAnglePer = interpolatedTime * mSweepAngle;
                // mCount = (int)(interpolatedTime * Float.parseFloat(mText));
            } else {
                mSweepAnglePer = mSweepAngle;
                //mCount = Integer.parseInt(mText);
            }
            postInvalidate();
        }
    }
    public void startCustomAnimation(){
        this.startAnimation(anim);
    }
    public void setSweepAngle(float sweepAngle){
        mSweepAngle = sweepAngle;

    }


}
