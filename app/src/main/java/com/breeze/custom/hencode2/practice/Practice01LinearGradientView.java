package com.breeze.custom.hencode2.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

public class Practice01LinearGradientView extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Shader mShader1;
    private Shader mShader2;
    private Shader mShader3;

    public Practice01LinearGradientView(Context context) {
        super(context);
        Log.d("TAG1", "Practice01LinearGradientView:1 ");
    }

    public Practice01LinearGradientView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        Log.d("TAG1", "Practice01LinearGradientView:2 ");
    }

    public Practice01LinearGradientView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Log.d("TAG1", "Practice01LinearGradientView:3 ");
    }

    {
        Log.d("TAG1", "{}: ");
        mShader1 = new LinearGradient(100, 100, 500, 500, Color.parseColor("#E91E63"),
                Color.parseColor("#2196F3"), Shader.TileMode.CLAMP);
        mShader2 = new LinearGradient(700, 100, 1000, 500, Color.parseColor("#E91E63"),
                Color.parseColor("#2196F3"), Shader.TileMode.MIRROR);
        mShader3 = new LinearGradient(100, 700, 500, 1000, Color.parseColor("#E91E63"),
                Color.parseColor("#2196F3"), Shader.TileMode.REPEAT);

        // 用 Paint.setShader(shader) 设置一个 LinearGradient
        // LinearGradient 的参数：坐标：(100, 100) 到 (500, 500) ；颜色：#E91E63 到 #2196F3
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d("TAG1", "onDraw: ");
        paint.setShader(mShader1);
        canvas.drawCircle(300, 300, 200, paint);

        paint.setShader(mShader2);
        canvas.drawCircle(900, 300, 200, paint);

        paint.setShader(mShader3);
        canvas.drawCircle(300, 900, 200, paint);
    }
}
