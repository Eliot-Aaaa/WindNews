package com.eliot.news.mynews.splash;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

import com.eliot.news.mynews.R;

import java.util.jar.Attributes;

/**
 * @ProjectName: WindNews
 * @Package: com.eliot.news.mynews.splash
 * @ClassName: TimeView
 * @Description: 类描述
 * @Author: 作者
 * @CreateDate: 2020/7/9 9:35
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/7/9 9:35
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class TimeView extends View {
    TextPaint mTextPaint;

    Paint circleP;
    Paint outerP;
    String content = "跳过";

    int pading = 5;
    int inner;
    int all;
    int degree;
    RectF outerRect;

    OnTimeClickListener mListener;

    public TimeView(Context context)
    {
        super(context);
    }

    public TimeView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.TimeView);
        int innerColor = array.getColor(R.styleable.TimeView_innerColor, Color.BLUE);
        int outerColor = array.getColor(R.styleable.TimeView_ringColor, Color.GREEN);
        mTextPaint = new TextPaint();
        mTextPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextSize(20);
        mTextPaint.setColor(Color.WHITE);

        circleP = new Paint();
        circleP.setFlags(Paint.ANTI_ALIAS_FLAG);
        circleP.setColor(innerColor);

        outerP = new Paint();
        outerP.setFlags(Paint.ANTI_ALIAS_FLAG);
        outerP.setColor(outerColor);
        outerP.setStyle(Paint.Style.STROKE);
        outerP.setStrokeWidth(pading);

        float text_Width = mTextPaint.measureText(content);
        inner = (int)text_Width + 2 * pading;
        all = inner + 2 * pading;

        outerRect = new RectF(pading / 2, pading / 2, all - pading / 2, all - pading / 2);

        array.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(all, all);
    }

    public void setD(int d)
    {
        this.degree = d;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawCircle(all / 2, all / 2, inner / 2, circleP);
        canvas.save();
        canvas.rotate(-90,  all / 2, all / 2);
        canvas.drawArc(outerRect, 0f, degree, false, outerP);
        canvas.restore();

        float y = (canvas.getHeight()/2);
        float de = mTextPaint.descent();//+
        float a = mTextPaint.ascent();//-
        canvas.drawText(content, pading*2, y-((de+a)/2), mTextPaint);
    }

    public void setProgress(int total, int now)
    {
        int space = 360/total;
        degree = space*now;
        invalidate();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                setAlpha(0.3f);
                break;
            case MotionEvent.ACTION_UP:
                setAlpha(1.0f);
                if (mListener != null)
                    mListener.onClickTime(this);
                break;
        }
        return true;
    }

    public void setListener(OnTimeClickListener listener)
    {
        this.mListener = listener;
    }
}
