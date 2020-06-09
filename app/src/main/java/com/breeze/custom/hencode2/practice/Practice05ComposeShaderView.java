package com.breeze.custom.hencode2.practice;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.BlendMode;
import android.graphics.Canvas;
import android.graphics.ComposeShader;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.breeze.custom.hencode2.R;

public class Practice05ComposeShaderView extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public Practice05ComposeShaderView(Context context) {
        super(context);
    }

    public Practice05ComposeShaderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice05ComposeShaderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        // 硬件加速下 ComposeShader 不能使用两个同类型的 Shader，关闭硬件加速
        setLayerType(LAYER_TYPE_SOFTWARE, null);


        Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), R.mipmap.batman);
        Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(), R.mipmap.batman_logo);

        Shader shader1 = new BitmapShader(bitmap1, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        BitmapShader shader2 = new BitmapShader(bitmap2, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        ComposeShader composeShader = new ComposeShader(shader1, shader2, PorterDuff.Mode.DST_IN);
        paint.setShader(composeShader);

        // 用 Paint.setShader(shader) 设置一个 ComposeShader
        // Shader 1: BitmapShader 图片：R.drawable.batman
        // Shader 2: BitmapShader 图片：R.drawable.batman_logo
        //构造方法：ComposeShader(Shader shaderA, Shader shaderB, PorterDuff.Mode mode)

//        233133
//                2331233
//                        213333
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawCircle(200, 200, 200, paint);
    }
}
