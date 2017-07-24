package com.example.aaa.photoedittools;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.graphics.Color;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.example.aaa.photoedittools.Main.ImageFilterMain;


public class phototools extends Activity {
    GestureDetector mGestureDetector;
    LinearLayout layFirst;
    LinearLayout laySecond;
    LinearLayout layContain;
    //当前显示的layout
    LinearLayout layCur;
    //左边的layout
    LinearLayout layLeft;
    //右边的layout
    LinearLayout layRight;

    int screenWidth;
    TextView roll_dot1,roll_dot2;
    private OnTouchListener myTouch=new OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            return mGestureDetector.onTouchEvent(event);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.usertable);
        //设置button1的监视器和页面跳转
        Button button=(Button)findViewById(R.id.button1);
        button.setOnClickListener(new Button.OnClickListener(){
            @Override
         public void onClick(View v){
                Intent intent=new Intent();
                intent.setClass(phototools.this,ImageFilterMain.class);
                startActivity(intent);
                phototools.this.finish();
            }
        });

        layContain=(LinearLayout)this.findViewById(R.id.layContain);
        layFirst=(LinearLayout)this.findViewById(R.id.layFirst);
        laySecond=(LinearLayout)this.findViewById(R.id.laySecond);

        roll_dot1=(TextView)findViewById(R.id.roll_dot1);
        roll_dot2=(TextView)findViewById(R.id.roll_dot2);

        layCur=layFirst;
        layLeft=null;
        layRight=laySecond;

        layFirst.setOnTouchListener(myTouch);
        laySecond.setOnTouchListener(myTouch);
        screenWidth=getWindowManager().getDefaultDisplay().getWidth();
        layFirst.getLayoutParams().width=screenWidth;
        laySecond.getLayoutParams().width=screenWidth;

        mGestureDetector=new GestureDetector(this,new OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent e) {
                return true;
            }
         @Override
         public boolean onFling(MotionEvent e1,MotionEvent e2,float velocityX,float velocityY) {
             int x = (int) (e2.getX() - e1.getX());
             //判断滑动方向
             boolean dir = x>0;//如果大于0，为true，说明向右移动，直接将其前一个视图的marginleft设置成0，如果是向左移动，则直接将maringleft试着称宽度的负数
             if (dir) {
                 if (layLeft == null) return false;
                 LinearLayout.LayoutParams llp = (LayoutParams) layLeft.getLayoutParams();
                 TranslateAnimation anim1 = new TranslateAnimation(llp.leftMargin, 0, 0, 0);
                 anim1.setDuration(250);
                 layLeft.startAnimation(anim1);
                 llp.setMargins(0, 0, 0, 0);
                 layLeft.setLayoutParams(llp);
                 if (layLeft == layFirst) {
                     layLeft = null;
                     layCur = layFirst;
                     layRight = laySecond;
                     //设置屏幕下方的小点随着页面的切换而改变
                     roll_dot2.setTextColor(Color.BLACK);
                     roll_dot1.setTextColor(Color.WHITE);
                 }
             }
                 else {
                 if (layRight == null) return false;
                 LinearLayout.LayoutParams llp = (LayoutParams) layCur.getLayoutParams();
                 int width = layCur.getWidth();
                 TranslateAnimation anim = new TranslateAnimation(width, 0, 0, 0);
                 anim.setDuration(250);
                 layRight.startAnimation(anim);
                 llp.setMargins(-width, 0, 0, 0);
                 layCur.setLayoutParams(llp);
                 if (layCur == layFirst) {
                     layLeft = layFirst;
                     layCur = laySecond;
                     layRight = null;
                     roll_dot1.setTextColor(Color.BLACK);
                     roll_dot2.setTextColor(Color.WHITE);
                 }

             }
                 return true;
     }






             @Override
             public void onLongPress(MotionEvent e) {
                 // TODO Auto-generated method stub

             }
             @Override
             public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {

                 return false;
             }
             @Override
             public void onShowPress(MotionEvent e) {


             }
             @Override
             public boolean onSingleTapUp(MotionEvent e) {

                 return false;
             }

         });
    }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_phototools, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();

            //noinspection SimplifiableIfStatement
            if (id == R.id.action_settings) {
                return true;
            }

            return super.onOptionsItemSelected(item);
        }
    }