package com.jk2dog.common.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.AttributeSet;

import com.jk2dog.common.R;


public class SeparatedTextview extends VectorCompatTextView {
    private String mSecondText;
    private float mSecondTextSize = 12;
    private int mSecondTextColor =Color.parseColor("#525253");
    private float mRightOffset;
    private Paint mTextPaint;


    public SeparatedTextview(Context context) {
        super(context);
        initPaint();
    }


    public SeparatedTextview(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }


    public SeparatedTextview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }




    private void init(Context context, AttributeSet attrs) {
        TypedArray attributes = context.obtainStyledAttributes(attrs,
            R.styleable.SeparatedTextview);
        mSecondText = attributes.getString(R.styleable.SeparatedTextview_secondText);
        mSecondTextSize = attributes.getDimension(R.styleable.SeparatedTextview_secondTextSize, 12);
        mSecondTextColor = attributes.getColor(R.styleable.SeparatedTextview_secondTextColor,mSecondTextColor);
        mRightOffset = attributes.getDimension(R.styleable.SeparatedTextview_rightOffset, 0);
        attributes.recycle();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!TextUtils.isEmpty(mSecondText)) {
            drawSecondText(canvas);
        }
    }

    private void initPaint() {
        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextSize(mSecondTextSize);
        mTextPaint.setColor(mSecondTextColor);
    }


    private void drawSecondText(Canvas canvas) {
        initPaint();
        int width = (int) (getWidth() - getCompoundDrawablePadding() - getPaddingRight() -
            mRightOffset);
        if (mDrawableRight != null) {
            width -= mDrawableRight.getIntrinsicWidth();
        }
        int height = getHeight();

        Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
        int baseline = ((height - (int) (fontMetrics.bottom - fontMetrics.top)) >> 1) -
            (int) fontMetrics.top;
        Rect rect = new Rect();
        mTextPaint.getTextBounds(mSecondText, 0, mSecondText.length(), rect);
        int left = width - rect.width();
        canvas.drawText(mSecondText, left, baseline, mTextPaint);
    }


    public void setSecondText(String text) {
        mSecondText = text;
        invalidate();
    }

    public String getSecondText(){
        return mSecondText;
    }


    public void setSecondTextSize(int size) {
        mSecondTextSize = size;
        invalidate();
    }

    public void setSecondTextColor(int color) {
        mSecondTextColor = color;
        invalidate();
    }
}
