package com.breeze.custom.hencode2.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class Practice16TextPathView extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Paint pathPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Path textPath = new Path();
    String text = "Hello HenCoder";

    public Practice16TextPathView(Context context) {
        super(context);
    }

    public Practice16TextPathView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice16TextPathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {

        //  getTextPath(String text, int start, int end, float x, float y, Path path)
        //  getTextPath(char[] text, int index, int count, float x, float y, Path path)
        //  text  绘制的文字      start,end  文字绘制开始结束的位置       x  y 实际文字将要绘制的位置   path  保存到path中
        paint.setTextSize(120);

        // 使用 Paint.getTextPath() 来获取文字的 Path

        pathPaint.setStyle(Paint.Style.STROKE);
        paint.getTextPath(text, 0, text.length(), 50, 400, textPath);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawText(text, 50, 200, paint);

        canvas.drawPath(textPath, pathPaint);
    }
}
