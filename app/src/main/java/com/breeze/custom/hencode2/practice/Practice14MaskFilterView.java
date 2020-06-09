package com.breeze.custom.hencode2.practice;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.breeze.custom.hencode2.R;


public class Practice14MaskFilterView extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Bitmap bitmap;
    private BlurMaskFilter mNormal;
    private BlurMaskFilter mInner;
    private BlurMaskFilter mOuter;
    private BlurMaskFilter mSolid;

    public Practice14MaskFilterView(Context context) {
        super(context);
    }

    public Practice14MaskFilterView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice14MaskFilterView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        setLayerType(LAYER_TYPE_SOFTWARE, null);
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.what_the_fuck);
        //radius 参数是模糊的范围， style 是模糊的类型
        //1.内外都模糊绘制
        mNormal = new BlurMaskFilter(50, BlurMaskFilter.Blur.NORMAL);
        //2.内部模糊，外部不绘制
        mInner = new BlurMaskFilter(50, BlurMaskFilter.Blur.INNER);
        //3.内部不绘制，外部模糊
        mOuter = new BlurMaskFilter(50, BlurMaskFilter.Blur.OUTER);
        //4.内部正常绘制，外部模糊
        mSolid = new BlurMaskFilter(50, BlurMaskFilter.Blur.SOLID);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 用 Paint.setMaskFilter 来设置不同的 BlurMaskFilter      MaskFilter可以设置两种效果
        // 1.浮雕效果的 EmbossMaskFilter   2.模糊效果的 BlurMaskFilter
        // 1.浮雕效果的 EmbossMaskFilter
        // 构造方法 EmbossMaskFilter(float[] direction, float ambient, float specular, float blurRadius)
        // direction 是一个 3 个元素的数组，指定了光源的方向； ambient 是环境光的强度，数值范围是 0 到 1； specular 是炫光的系数； blurRadius 是应用光线的范围

        paint.setMaskFilter(mNormal);
        // 第一个：NORMAL        内外都模糊绘制
        canvas.drawBitmap(bitmap, 100, 50, paint);

        paint.setMaskFilter(mInner);
        // 第二个：INNER         内部模糊，外部不绘制
        canvas.drawBitmap(bitmap, bitmap.getWidth() + 200, 50, paint);

        paint.setMaskFilter(mOuter);
        // 第三个：OUTER        内部不绘制，外部模糊
        canvas.drawBitmap(bitmap, 100, bitmap.getHeight() + 100, paint);

        paint.setMaskFilter(mSolid);
        // 第四个：SOLID         内部正常绘制，外部模糊
        canvas.drawBitmap(bitmap, bitmap.getWidth() + 200, bitmap.getHeight() + 100, paint);
    }
}
