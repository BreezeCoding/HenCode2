package com.breeze.custom.hencode2.practice;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.breeze.custom.hencode2.R;


public class Practice07ColorMatrixColorFilterView extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Bitmap bitmap;

    public Practice07ColorMatrixColorFilterView(Context context) {
        super(context);
    }

    public Practice07ColorMatrixColorFilterView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice07ColorMatrixColorFilterView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.batman);
        float[] filter =
                {
                        0.6279345635605994F, 0.3202183420819367F, -0.03965408211312453F, 0, 9.651285835294123F,
                        0.02578397704808868F, 0.6441188644374771F, 0.03259127616149294F, 0, 7.462829176470591F,
                        0.0466055556782719F, -0.0851232987247891F, 0.5241648018700465F, 0, 5.159190588235296F,
                        0, 0, 0, 1, 0};
        ColorMatrixColorFilter colorMatrixColorFilter = new ColorMatrixColorFilter(filter);
        paint.setColorFilter(colorMatrixColorFilter);
        // 使用 setColorFilter() 设置一个 ColorMatrixColorFilter
        // 用 ColorMatrixColorFilter.setSaturation() 把饱和度去掉
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(bitmap, 0, 0, paint);
    }
}
