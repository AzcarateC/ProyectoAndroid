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
                Resources res = getResources(); //Por cada elemento de la matriz esta levantando las imagenes, podria hacerse una unica vez antes de iterar
                ImageView img = new ImageView(this);
                img.setImageDrawable(res.getDrawable(ArrayImage[numRandom]));
                img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        v.setSelected(!v.isSelected());
                        if (v.isSelected()) {
							//Estoy dandole comportamiento onClick al ImageView "img"
							//Cuando clickeo img corre el siguiente codigo
                            setSingleEvent(g_layout); 
							//A todas las imagenes les agrega comportamiento, para que si la clickea se evaluen las 2
                        }
                    }
                });
                g_layout.addView(img, 100, 100);
                matrizImage[fila][colum] = numRandom;
            }
        }
        return g_layout;
    }

    private void setSingleEvent(final GridLayout g_layout) {
        for (int i = 0; i < g_layout.getChildCount(); i++) { //Para todas las ImageView
            ImageView img = (ImageView) g_layout.getChildAt(i); 
            final int fin = i; //Me guardo su id
            img.setOnClickListener(new View.OnClickListener() { //A cada ImageView le agrego comportamiento onClick
                @Override
                public void onClick(View v) {
					//Cada image view al momento en que se clicke(y despues de haber clickeado alguna imagen antes) corre:
                    setSinlgeEvent2(g_layout, fin); //Recibe la ID de la imagen clickeada
                }
            });
        }
    }

    private void setSinlgeEvent2(final GridLayout g_layout, final int idImg1) {
        for (int i = 0; i < g_layout.getChildCount(); i++) { //Para todas las imagenes
            ImageView im2 = (ImageView) g_layout.getChildAt(i); //Levanto la imagen
            final int idImg2 = i; //Guardo la id de la imagen
            im2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Agrego comportamiento onClick para la segunda imagen
					//Muestro en pantalla las id de las dos imagenes!
                    Toast.makeText(MainActivity.this, "Primer elemento" + idImg1 + " segundo:" + idImg2, Toast.LENGTH_SHORT).show();
					
					/* Asi lo seguiria yo, me entusiasme y lo desglose abajo
					
					int exito;
					intentarIntercambiar(idImg1,idImg2,exito); 
					if (!exito){
						Toast.makeText(MainActivity.this, "Movimiento no valido" , Toast.LENGTH_SHORT).show();
					}
					
					*/
					
					//<INICIO PRUEBAS PARA CAMBIAR IMAGEN>
					
					ponerEnVerde(g_layout,idImg1,idImg2);
					
					//<FIN PRUEBAS PARA CAMBIAR IMAGEN>
					
					//Devuelvo el comportamiento onclick para seleccionar una imagen
                    setSingleEvent(g_layout);
                    

                }
            });
        }

    }
	
	
	
	//PRUEBA PARA CAMBIAR IMAGEN
	
	private void ponerEnVerde(GridLayout g_layout,int idImg1,int idImg2){
		//Toma 2 id de imagenes y las reemplaza por una gema verde
						Resources res = getResources();
						ImageView img1 = (ImageView) g_layout.getChildAt(idImg1);
						ImageView img2 = (ImageView) g_layout.getChildAt(idImg2);
						img1.setImageDrawable(res.getDrawable(ArrayImage[1])); //Cambio los dos dibujos clickeados por algo verde
						img2.setImageDrawable(res.getDrawable(ArrayImage[1]));
						
					}
	
	//FIN PRUEBA PARA CAMBIAR IMAGEN
	
	
	/*
	private void intentarIntercambiar(int idImg1, int idImg2, int exito){
		
		//La posicion en el vector la voy a usar para varias cosas asique es lo primero que quiero saber
		Pair coordenadasImg1 := getCoordenadas(idImg1); //Falta hacer la funcion getCoordenadas
		Pair coordenadasImg2 := getCoordenadas(idImg2);
		
		
		//Tengo que asegurarme de que sean vecinos, sino el movimiento no es valido
		if (sonVecinos(coordenadasImg1,coordenadasImg2)){ //Falta hacer sonVecinos
		
			//Genero una matriz temporal para probar el cambio
			int[][] matrizParaProbar = new int[8][8];
			copiarMatriz(matrizImage,matrizParaProbar); //Falta hacer copiarMatriz
			
			//Reemplazo 
			intercambiar(matrizParaProbar,coordenadasImg1,coordenadasImg2) //Falta hacer intercambiar
			
			//Obtengo los puntos que matchearon
			List<Pair> puntosQueDieronCoincidencia = new ArrayList<Pair>();
			buscarCoincidencias(matrizParaProbar,puntosQueDieronCoincidencia); //Falta hacer buscarCoincidencias
			
			if (puntosQueDieronCoincidencia.isEmpty()) {
				exito = 0;
			} else {
				exito = 1;
				limpiarCoincidencias(matrizParaProbar,puntosQueDieronCoincidencia); //Falta hacer limpiarCoincidencias
				matrizImage = matrizParaProbar; 
				
				// !!!! ESTO ES IMPORTANTE !!!!
				//yo no se si hacer este cambio en la matriz haga que se actualize
				//el layout, llegue hasta aca confiando en eso pero estaria bueno verificar que eso sea asi
				//antes de seguir con esta solucion
				
				// Para probar se podria hacer alguna accion que cambie la id del primer elemento de la matriz por otro color
				// y comprobar que se vea el cambio, validado eso me parece una buena solucion esta
				
			}
		
	
	}
	
	private Pair getCoordenadas(int unaId){
		//Recibe una id y devuelve la fila y columna donde esta ubicada en la matriz
	}
	
	private boolean sonVecinos(Pair cor1, Pair cor2){
		//Recibe cordenadas (x,y) y mira en la matriz si cor2 esta a la izquierda, derecha, arriba o abajo de cor1 y devuelve true o false
	}
	
	private void copiarMatriz(int[][] matrizOrigen, int[][] matrizCopia){
		//Recorre toda la matriz original copiando los valores en la matriz copia
	}
	
	private void intercambiar(int[][] unaMatriz,Pair cor1,Pair cor2){
		//Recibe una matriz y 2 coordenadas, intercambia los valores que hay en las coordenadas
	}
	
	private void buscarCoincidencias(int[][] unaMatriz, List<Pair> puntosQueDieronCoincidencia){
		//Recorre toda la matriz, por cada punto revisa si tiene tres o mas iguales a su derecha y hacia abajo, si hay coincidencias las agrega a la lista
		//Nota: No importa que se guarden pares duplicados

	}
	
	private void limpiarCoincidencias(int[][] unaMatriz, List<Pair> puntosQueDieronCoincidencia){
		//Recorre la lista de coordenadas y los borra, debe colocar una id nula para cada posicion en la lista
	}
	
	*/
	

}