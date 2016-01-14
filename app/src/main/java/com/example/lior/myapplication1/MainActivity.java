package com.example.lior.myapplication1;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.PaintDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class MainActivity extends Activity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View view = new MyView(this);

        LinearLayout v = (LinearLayout) findViewById(R.id.lineralayout);
        v.addView(view);
        RotateAnimation rotate = new RotateAnimation(0f, 360f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

        rotate.setDuration(1000);

        rotate.setInterpolator(new LinearInterpolator());
        rotate.setRepeatCount(Animation.INFINITE);

        rotate.setRepeatMode(Animation.INFINITE);

// prevents View from restoring to original direction.
        rotate.setFillAfter(true);
        view.startAnimation(rotate);
    }
}


class MyView extends View {

    Paint BackPaint = new Paint();
    Context MyContext;
    final int _BackgroundColor=Color.CYAN;
    final int _ForegroundColor = Color.WHITE;

    public MyView(Context context) {
        super(context);
        init(context);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MyView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context ctx){
        MyContext = ctx;
        BackPaint.setStyle(Paint.Style.FILL);
        BackPaint.setColor(_BackgroundColor);
    }


    /**
     * @param widthMeasureSpec width of the phone model in pixles
     * @param heightMeasureSpec height of the phone model in pixles
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int w = MeasureSpec.getSize(widthMeasureSpec);
        int h = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(w, h);
    }

    /**
     * @param canvas the canvas of the view
     */
    @Override
    protected void onDraw(Canvas canvas) {

        /*Does the arrangement before drawing the circles*/
        //region Arrangement
        float w, h, cx, cy, radius;
        w = getWidth();
        h = getHeight();
        cx = w/2;
        cy = h/2;

        if(w > h){
            radius = h/4;
        }else{
            radius = w/4;
        }

        //endregion



        Paint MyPaint = new Paint();
        MyPaint.setStyle(Paint.Style.FILL);

        float shaderCx = cx;
        float shaderCy = cy;
        int shaderColor0 = _BackgroundColor;
        int shaderColor1 =_ForegroundColor;


        //privdes color changes in the circle
        SweepGradient gardient = new SweepGradient(
                shaderCx,
                shaderCy,
                shaderColor0,
                shaderColor1);


        MyPaint.setShader(gardient);

        setBackground(new PaintDrawable(_BackgroundColor));
        canvas.drawCircle(cx, cy, radius, MyPaint);

        Paint gardientPainter = new Paint();




        RadialGradient cernterGardient = new RadialGradient(cx,cy,radius-5,new int[]{shaderColor0,Color.parseColor("#66CCFF")},null, Shader.TileMode.CLAMP);

        gardientPainter.setShader(cernterGardient);
        canvas.drawCircle(cx, cy,radius-5 , gardientPainter);
    };


}
