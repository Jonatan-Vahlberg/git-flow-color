package com.jonatan_vahlberg.myapplication;


import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.Shape;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

        private TextView textView;
        private Button btnAdd, btnTake, btnGrow, btnShrink, btnHide, btnReset, btnCrazy;
        private LinearLayout linearLayout;
        private ViewGroup viewGroup;

        private boolean startAnimation = true;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            linearLayout = findViewById(R.id.linear_layout);
            viewGroup = findViewById(R.id.linear_layout);

            textView = findViewById(R.id.txt_value);

            btnAdd = findViewById(R.id.btn_add);
            btnAdd.setOnClickListener(this);

            btnTake = findViewById(R.id.btn_take);
            btnTake.setOnClickListener(this);

            btnGrow = findViewById(R.id.btn_grow);
            btnGrow.setOnClickListener(this);

            btnShrink = findViewById(R.id.btn_shrink);
            btnShrink.setOnClickListener(this);

            btnHide = findViewById(R.id.btn_hide);
            btnHide.setOnClickListener(this);

            btnReset= findViewById(R.id.btn_reset);
            btnReset.setOnClickListener(this);

            btnCrazy = findViewById(R.id.btn_crazy);
            btnCrazy.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {

            int i = Integer.parseInt(textView.getText().toString());
            switch (v.getId()){


                case R.id.btn_add:
                    i++;
                    textView.setText((i) +"");
                    break;

                case R.id.btn_take:
                    i--;
                    textView.setText((i) +"");
                    break;

                case R.id.btn_grow:
                    textView.setTextScaleX(textView.getTextScaleX()+1);
                    break;

                case R.id.btn_shrink:
                    textView.setTextScaleX(textView.getTextScaleX()-1);
                    break;

                case R.id.btn_hide:
                    textView.setVisibility(View.GONE);
                    break;

                case R.id.btn_reset:
                    textView.setVisibility(View.VISIBLE);
                    break;
                case R.id.btn_crazy:
                    Random selector = new Random();
                    int randomInt = selector.nextInt(6);
                    switch (randomInt){
                        case 0:
                            randomColor(selector);
                            break;

                        case 1:
                            randomScale(selector, true );
                            break;
                        case 2:
                            randomScale(selector, false);
                        case 3:
                            randomColor(selector,linearLayout);
                            break;

                        case 4:
                            int color1 = getRandomColor(selector);
                            int color2;
                            if(color1 > 500000){
                                color2 = color1 - 5000;
                            }
                            else{
                                color2 = color1 + 5000;
                            }

                            int randomColor1 = Color.parseColor("#"+color1);
                            int randomColor2 = Color.parseColor("#"+color2);
                            GradientDrawable gradientDrawable2 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT,
                                    new int[] {randomColor1,randomColor2});
                            linearLayout.setBackground(gradientDrawable2);
                            break;
                        case 5:
                            toggleAnimation(textView, selector);
                    }
                    break;

                default:
                    break;
            }
        }

        void randomColor(Random selector){
            int color = selector.nextInt(899999)+100000;
            textView.setTextColor(Color.parseColor("#"+color));
        }

        void randomColor(Random selector, View v){
            int color = selector.nextInt(899999)+100000;
            v.setBackgroundColor(Color.parseColor("#"+color));
        }
        int getRandomColor(Random selector){
            return selector.nextInt(899999)+100000;
        }
        void randomScale(Random selector,boolean x){

            int scale = selector.nextInt(16)+2;
            if(x){
                textView.setTextScaleX(scale);
            }
            else{
                textView.setRotationY( textView.getRotationY() + scale);
            }

        }
        void toggleAnimation(View v, Random selector) {
            if(startAnimation){

                TransitionManager.beginDelayedTransition(viewGroup);
                v.setAlpha(selector.nextFloat());
                v.setScaleY(selector.nextFloat());

            }
        }

    class CustomTextView extends android.support.v7.widget.AppCompatTextView{
            //DECRAPTEATED
            String initalColor = "#000000";
            String initalValue = "";
            int initalScaleX = 0;
            int initalYrot = 0;


        public CustomTextView(){

            super(MainActivity.this);
        }
        public CustomTextView(String initalValue){

            super(MainActivity.this);
            this.initalValue = initalValue;
        }

        public void initzalize(){
            this.setTextColor(Color.parseColor(initalColor));
            this.setText(initalValue);
            this.setScaleX(initalScaleX);
            this.setRotationY(initalYrot);
            this.setVisibility(VISIBLE);
        }
    }
    }



