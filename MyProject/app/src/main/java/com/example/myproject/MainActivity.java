package com.example.myproject;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import java.util.Random;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;


public class MainActivity extends AppCompatActivity {

    private int ArrayImage[]={
            R.drawable.blue,
            R.drawable.green,
            R.drawable.orange,
            R.drawable.purple,
            R.drawable.red,
            R.drawable.yellow
    };


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.randomLayout();

    }
    private GridLayout randomLayout(){
        GridLayout g_layout=(GridLayout)findViewById(R.id.mygridl);
        for (int i=0;i<64;i++){
            int numRandom=(int)(Math.random()*6);
            Resources res=getResources();
            ImageView img = new ImageView(this);
            img.setImageDrawable(res.getDrawable(ArrayImage[numRandom]));
            g_layout.addView(img,135,135);
        }
        return g_layout;
    }
}

/*  public ImageView imgB= (ImageView)findViewById(R.id.imageView);
    public ImageView imgG= (ImageView)findViewById(R.id.imageView2);
    public ImageView imgR= (ImageView)findViewById(R.id.imageView6);
    public ImageView imgY= (ImageView)findViewById(R.id.imageView5);
    public ImageView imgP= (ImageView)findViewById(R.id.imageView4);
    public ImageView imgO= (ImageView)findViewById(R.id.imageView3);



    VECTOR DE ID DE FIGURAS

    private int ArrayImage[]={
           R.drawable.blue,
           R.drawable.green,
           R.drawable.orange,
           R.drawable.purple,
           R.drawable.red,
           R.drawable.yellow
    };


    GENERAR LA FIGURA DE MANERA RANDOM?

    private int numRandom=(int)(Math.random()*6)+1;
    public ImageView img1=(ImageView)findViewById(R.id.imageView);
    public ImageView img2=(ImageView)findViewById(R.id.imageView2);
    public ImageView img3=(ImageView)findViewById(R.id.imageView3);
    public ImageView img4=(ImageView)findViewById(R.id.imageView4);
    public ImageView img5=(ImageView)findViewById(R.id.imageView5);
    public ImageView img6=(ImageView)findViewById(R.id.imageView6);
    public ImageView img7=(ImageView)findViewById(R.id.imageView7);
    public ImageView img8=(ImageView)findViewById(R.id.imageView8);


    COMO LLENAR UN GRIDLAYOUT?

     for(int f=0;f<9;f++){
                Drawable d= getResources().getDrawable(ArrayImage[numRandom]);
                img1.setImageDrawable(d);
                Drawable d2= getResources().getDrawable(ArrayImage[numRandom]);
                img2.setImageDrawable(d2);
                Drawable d3= getResources().getDrawable(ArrayImage[numRandom]);
                img3.setImageDrawable(d3);
                Drawable d4= getResources().getDrawable(ArrayImage[numRandom]);
                img4.setImageDrawable(d4);
                Drawable d5= getResources().getDrawable(ArrayImage[numRandom]);
                img5.setImageDrawable(d5);
                Drawable d6= getResources().getDrawable(ArrayImage[numRandom]);
                img6.setImageDrawable(d6);
                Drawable d7= getResources().getDrawable(ArrayImage[numRandom]);
                img7.setImageDrawable(d7);
                Drawable d8= getResources().getDrawable(ArrayImage[numRandom]);
                img8.setImageDrawable(d8);
   */
