package com.example.liuhuan20181008;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DrawFilter;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by 。。。。 on 2018/10/8.
 */

public class WaveView extends View {

    private Paint paint1;
    private Paint paint2;
    private float fai = 0;
    private Path path1;
    private Path path2;
    private DrawFilter drawFilter;

    public WaveView(Context context) {
        this(context, null);
    }

    public WaveView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WaveView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public interface OnWaveChangeListener {
        void Onchanged(float y);
    }

    private OnWaveChangeListener listener;

    public void setOnWaveChangeListener(OnWaveChangeListener listener) {
        this.listener = listener;
    }

    private void init() {
        //制作画笔
        paint1 = new Paint();
        paint1.setColor(Color.WHITE);
        //锯齿
        paint1.setAntiAlias(true);
        paint1.setStyle(Paint.Style.FILL);
        paint1.setStrokeWidth(5);


        paint2 = new Paint();
        //颜色
        paint2.setColor(Color.WHITE);
        paint2.setAntiAlias(true);
        paint2.setStyle(Paint.Style.FILL);
        paint2.setStrokeWidth(5);
        //透明度
        paint2.setAlpha(60);

        path1 = new Path();
        path2 = new Path();
        drawFilter = new PaintFlagsDrawFilter(0, Paint.FILTER_BITMAP_FLAG | Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //方程式
        double Ω = 2 * Math.PI / getMeasuredWidth();
        canvas.setDrawFilter(drawFilter);
        fai -= 0.1f;
        int A = getMeasuredHeight() / 2;
        path1.reset();
        path2.reset();
        path1.moveTo(getLeft(), getBottom());
        path2.moveTo(getLeft(), getBottom());
        for (int x = 0; x <= getMeasuredWidth(); x += 20) {
            float y1 = A * (float) Math.sin(Ω * x + fai) + A;
            float y2 = -A * (float) Math.sin(Ω * x + fai) + A;
            if (x > getMeasuredWidth() / 2 - 20 && x < getMeasuredWidth() / 2 + 20) {
                listener.Onchanged(y2);
            }
            path1.lineTo(x, y1);
            path2.lineTo(x, y2);
        }
        //进行划线
        path1.lineTo(getWidth(), getBottom());
        path2.lineTo(getWidth(), getBottom());
        canvas.drawPath(path1, paint1);
        canvas.drawPath(path2, paint2);
        postInvalidateDelayed(50);
    }
}
