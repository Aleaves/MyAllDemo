package com.app.liulongbing.myalldemo.one;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by liulongbing on 16/11/22.
 */

public class OneView extends View{

    public OneView(Context context) {
        super(context);
    }

    public OneView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();
        paint.setStrokeWidth(2);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.RED);

        canvas.save();
        canvas.translate(50,50);
        canvas.drawCircle(getWidth()/2,getHeight()/2,150,paint);
        canvas.restore();

        paint.setColor(Color.BLUE);
        canvas.drawCircle(getWidth()/2,getHeight()/2,150,paint);

    }

}
