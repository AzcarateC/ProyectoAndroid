package com.example.bejeweled21;


import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.*;


public class MainActivity extends AppCompatActivity {

    final int life = 99;
    final int[] limitRight = {7, 15, 23, 31, 39, 47, 55, 63};
    final int[] limitLeft = {0, 8, 16, 24, 32, 40, 48, 56};
    private GridLayout gridLayout;
    private int[] ArrayImage = {R.drawable.blue, R.drawable.green, R.drawable.orange, R.drawable.purple, R.drawable.red, R.drawable.yellow, R.drawable.backgroun};
    private Integer array[] = new Integer[64];
    private int pressA = 0;
    private int pressB = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.createTable();
        setSingleEvent(gridLayout);

    }


    //See if the movement is valid (up, down, left or right)
    private boolean validateMovement(final int first, final int second/*int btnA, int btnB*/) {
        int up = first - 8;
        int down = first + 8;
        int left = first - 1;
        int right = first + 1;

        if ((up == second) || (down == second) || (right == second) || (left == second))
            return true;
        else return false;
    }


    private void createTable() {
        gridLayout = findViewById(R.id.gridlayout);
        for (int i = 0; i < 64; i++) {
            int numberRandom = (int) (Math.random() * 6);
            Resources resources = getResources();
            ImageView image = new ImageView(this);
            image.setImageDrawable(resources.getDrawable(ArrayImage[numberRandom]));
            gridLayout.addView(image);
            // I keep the number that refers to the image I saved.
            array[i] = numberRandom;
        }
    }


    private void setSingleEvent(final GridLayout mainGrid) {
        for (int i = 0; i < mainGrid.getChildCount(); i++) {
            ImageView image = (ImageView) mainGrid.getChildAt(i);
            final int finalI = i;
            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (pressA < life) {
                        pressA += 1;
                        setSingleEvent2(mainGrid, finalI);
                    }
                }
            });
        }
    }


    private void setSingleEvent2(final GridLayout mainGrid, final int j) {
        for (int i = 0; i < mainGrid.getChildCount(); i++) {
            ImageView image = (ImageView) mainGrid.getChildAt(i);
            final int finalI = i;
            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (pressB < life) {                                                          // take out later
                        pressB += 1;                                                              // take out later
                        if (validateMovement(j, finalI)) {
                            move(j, finalI, mainGrid);
                            List l1 = checkHorizontal(finalI);
                            List l2 = checkHorizontal(j);
                            List l3 = checkVertical(finalI);
                            List l4 = checkVertical(j);
                            if ((l2.size() > 2) || (l1.size() > 2) || (l3.size() > 2) || (l4.size() > 2)) {
                                removeGrid(l1, mainGrid);
                                removeGrid(l2, mainGrid);
                                removeGrid(l3, mainGrid);
                                removeGrid(l4, mainGrid);
                            } else {
                                Toast.makeText(MainActivity.this, "No changes. Try another grid", Toast.LENGTH_SHORT).show();
                                move(j, finalI, mainGrid);
                            }
                        }
                        setSingleEvent(gridLayout);
                    }
                }
            });
        }
    }


    private void removeGrid(List l1, GridLayout gridL) {
        //Recorro todx el arreglo
        for (Integer i = 0; i < 64; i++) {
            //Recorro toda la lista 1
            for (int x = 0; x < l1.size(); x++) {
                if (i == l1.get(x)) {
                    gridL.removeViewAt(i);
                    Resources resources = getResources();
                    ImageView image = new ImageView(this);
                    image.setImageDrawable(resources.getDrawable(ArrayImage[6]));
                    gridL.addView(image, i);
                    // I keep the number that refers to the image I saved.
                    array[i] = -1;
                }
            }
        }
    }


    private void move(int fClick, int sClick, GridLayout gL) {
        if ((array[fClick] == -1) || (array[sClick] == -1)) {
            Toast.makeText(MainActivity.this, "Momentarily invalid movement", Toast.LENGTH_LONG).show();
            return;
        }
        int aux = array[fClick];
        array[fClick] = array[sClick];
        array[sClick] = aux;
        for (int i = 0; i < 64; i++) {
            if (fClick == i) {
                gL.removeViewAt(i);
                Resources resources = getResources();
                ImageView image = new ImageView(this);
                image.setImageDrawable(resources.getDrawable(ArrayImage[array[fClick]]));
                gL.addView(image, i);
            } else if (sClick == i) {
                gL.removeViewAt(i);
                Resources resources = getResources();
                ImageView image2 = new ImageView(this);
                image2.setImageDrawable(resources.getDrawable(ArrayImage[array[sClick]]));
                gL.addView(image2, i);
            }
        }
    }


    private boolean inLimitRight(int value) {
        boolean rtn = false;
        for (int i = 0; i < limitRight.length; i++) {
            if (limitRight[i] == value) rtn = true;
        }
        return rtn;
    }


    private boolean inLimitLeft(int value) {
        boolean rtn = false;
        for (int i = 0; i < limitLeft.length; i++) {
            if (limitLeft[i] == value) {
                rtn = true;
            }
        }
        return rtn;
    }


    private List<Integer> checkHorizontal(int cord1) {
        List<Integer> listPos = new ArrayList();
        int firstClick = array[cord1];
        listPos.add(cord1);
        // if isnt a right limit moves right
        if (!inLimitRight(cord1)) {
            int right = cord1 + 1;
            int broR = array[right];
            while (firstClick == broR) {
                listPos.add(right);
                //if "right" is a right limit break
                if (inLimitRight(right)) break;
                right += 1;
                broR = array[right];
            }
        }
        //If isnt a left limit moves left
        if (!inLimitLeft(cord1)) {
            int left = cord1 - 1;
            int broL = array[left];
            while (firstClick == broL) {
                listPos.add(left);
                //if "left" is a left limit, then break
                if (inLimitLeft(left)) break;
                left -= 1;
                broL = array[left];
            }
        }
        // I save the size of the list to test in toast // take out later
        int size = listPos.size();
        if (size > 2) {
            return listPos;
        } else {
            listPos.clear();
            return listPos;
        }

    }


    private List<Integer> checkVertical(int cord1) {
        List<Integer> listPos = new ArrayList<Integer>();
        int firstClick = array[cord1];
        listPos.add(cord1);
        //If it isnt an upper limit it moves up
        if (cord1 >= 8) {
            int up = cord1 - 8;
            int broU = array[up];
            while (firstClick == broU) {
                listPos.add(up);
                //If "up" is an upper limit, then break
                up -= 8;
                if (up < 0) break;
                broU = array[up];
            }
        }
        //if it isnt a lower limit then it moves down
        if (cord1 <= 55) {
            int down = cord1 + 8;
            int broD = array[down];
            while (firstClick == broD) {
                listPos.add(down);
                down += 8;
                //If the left is a limit to the left then break
                if (down > 63) break;
                broD = array[down];
            }
        }
        //Save the size of the list to try on toast // take out later
        int size = listPos.size();
        if (size > 2) {
            return listPos;
        } else {
            listPos.clear();
            return listPos;
        }
    }
}