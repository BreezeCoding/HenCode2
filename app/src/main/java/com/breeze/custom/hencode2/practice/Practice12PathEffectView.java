package com.breeze.custom.hencode2.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ComposePathEffect;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.DiscretePathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.SumPathEffect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class Practice12PathEffectView extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Path path = new Path();
    private CornerPathEffect mCornerPathEffect;
    private DiscretePathEffect mDiscretePathEffect;
    private DashPathEffect mDashPathEffect;
    private PathDashPathEffect mPathDashPathEffect;
    private SumPathEffect mSumPathEffect;
    private ComposePathEffect mComposePathEffect;

    public Practice12PathEffectView(Context context) {
        super(context);
    }

    public Practice12PathEffectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice12PathEffectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        paint.setStyle(Paint.Style.STROKE);

        path.moveTo(50, 100);
        path.rLineTo(50, 100);
        path.rLineTo(80, -150);
        path.rLineTo(100, 100);
        path.rLineTo(70, -120);
        path.rLineTo(150, 80);

        //1.将拐角全部变成圆角
        mCornerPathEffect = new CornerPathEffect(5);
        //2.把线条随机偏移
        mDiscretePathEffect = new DiscretePathEffect(20, 5);
        //3.画虚线，20,5,5,5   画20像素空5像素   画5像素空5像素   0是偏移量
        mDashPathEffect = new DashPathEffect(new float[]{20, 5, 5, 5}, 0);
        //4使用path来画虚线,path是个圆点
        Path path1 = new Path();
        path1.addCircle(30, 30, 15, Path.Direction.CW);
        mPathDashPathEffect = new PathDashPathEffect(path1, 40, 0, PathDashPathEffect.Style.TRANSLATE);
        // shape 参数是用来绘制的 Path ； advance 是两个相邻的 shape 段之间的间隔，不过注意，这个间隔是两个 shape 段的起点的间隔，而不是前一个的终点和后一个的起点的距离；
        // phase 和 DashPathEffect 中一样，是虚线的偏移；最后一个参数 style，是用来指定拐弯改变的时候 shape 的转换方式。
        // style 的类型为 PathDashPathEffect.Style ，是一个 enum ，具体有三个值：
        // TRANSLATE：位移
        //ROTATE：旋转
        // MORPH：变体
        //5.组合效果类的 PathEffect,分别按照两种 PathEffect 分别对目标进行绘制
        mSumPathEffect = new SumPathEffect(mDashPathEffect, mDiscretePathEffect);
        //6.组合效果
        mComposePathEffect = new ComposePathEffect(mDashPathEffect, mDiscretePathEffect);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 使用 Paint.setPathEffect() 来设置不同的 PathEffect

        paint.setPathEffect(mCornerPathEffect);
        // 第一处：CornerPathEffect
        // 把所有拐角变成圆角
        canvas.drawPath(path, paint);

        canvas.save();
        canvas.translate(500, 0);
        paint.setPathEffect(mDiscretePathEffect);
        // 第二处：DiscretePathEffect
        // 把线条进行随机的偏离，让轮廓变得乱七八糟。乱七八糟的方式和程度由参数决定
        canvas.drawPath(path, paint);
        canvas.restore();

        canvas.save();
        canvas.translate(0, 200);
        paint.setPathEffect(mDashPathEffect);
        // 第三处：DashPathEffect
        //使用虚线来绘制线条
        canvas.drawPath(path, paint);
        canvas.restore();

        canvas.save();
        canvas.translate(500, 200);
        paint.setPathEffect(mPathDashPathEffect);
        // 第四处：PathDashPathEffect
        // 这个方法比 DashPathEffect 多一个前缀 Path ，所以顾名思义，它是使用一个 Path 来绘制「虚线」
        canvas.drawPath(path, paint);
        canvas.restore();

        canvas.save();
        canvas.translate(0, 400);
        paint.setPathEffect(mSumPathEffect);
        // 第五处：SumPathEffect
        // 一个组合效果类的 PathEffect     分别按照两种 PathEffect 分别对目标进行绘制
        canvas.drawPath(path, paint);
        canvas.restore();

        canvas.save();
        canvas.translate(500, 400);
        paint.setPathEffect(mComposePathEffect);
        // 第六处：ComposePathEffect
        // 一个组合效果类的 PathEffect     不过它是先对目标 Path 使用一个 PathEffect，然后再对这个改变后的 Path 使用另一个 PathEffect
        canvas.drawPath(path, paint);
        canvas.restore();
    }
}
