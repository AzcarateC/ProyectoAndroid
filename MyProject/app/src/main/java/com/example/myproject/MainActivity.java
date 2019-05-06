package com.example.myproject;

import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import java.security.cert.PolicyNode;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import android.os.Bundle;
import android.support.v7.widget.ViewUtils;
import android.util.Pair;
import android.view.DragEvent;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private int ArrayImage[] = {
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

    private int[][] matrizImage = new int[8][8];

    private GridLayout randomLayout() {
        final GridLayout g_layout = (GridLayout) findViewById(R.id.mygridl);
        for (int fila = 0; fila < 8; fila++) {
            for (int colum = 0; colum < 8; colum++) {
                int numRandom = (int) (Math.random()*6);
                Resources res = getResources();
                ImageView img = new ImageView(this);
                img.setImageDrawable(res.getDrawable(ArrayImage[numRandom]));
                img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        v.setSelected(!v.isSelected());
                        if (v.isSelected()) {
                            setSingleEvent(g_layout);
                        }
                    }
                });
                g_layout.addView(img, 30, 30);
                matrizImage[fila][colum] = numRandom;
            }
        }
        return g_layout;
    }

    private void setSingleEvent(final GridLayout g_layout) {
        for (int i = 0; i < g_layout.getChildCount(); i++) {
            ImageView img = (ImageView) g_layout.getChildAt(i);
            final int fin = i;
            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setSinlgeEvent2(g_layout, fin);
                }
            });
        }
    }

    private void setSinlgeEvent2(final GridLayout g_layout, final int f) {
        for (int i = 0; i < g_layout.getChildCount(); i++) {
            ImageView im2 = (ImageView) g_layout.getChildAt(i);
            final int fin = i;
            im2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    
                    Toast.makeText(MainActivity.this, "Primer elemento" + f + " segundo:" + fin, Toast.LENGTH_SHORT).show();
                    setSingleEvent(g_layout);
                    

                }
            });
        }

    }

}