package org.mossin.com.chemistryapp.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.LoginFilter;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Main extends Activity  {
  String simbolo,numeroAt,masita;
  Spinner elementos;
  ArrayAdapter<String> adElementos;
  Button btn1;
  CheckBox check1,check2,check3;
  TextView resultado,numAtom,masa;
  String arregloElementos [];/*Aqui se guarda la linea  leeida divida en 3*/
  ArrayList<String>nombres = new ArrayList<String>();/*Aqui se guarda los Nombres de los elementos */
  ArrayList<String>simbolos = new ArrayList<String>();/*Aqui se guardan Los simbolos */
  ArrayList<String>numerosAtomicos = new ArrayList<String>();
  ArrayList<String>masaAtomica = new ArrayList<String>();;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /* declaracion de valores */
        elementos = (Spinner)findViewById(R.id.spinner);
        btn1 = (Button)findViewById(R.id.button);
        resultado = (TextView)findViewById(R.id.textView2);
        numAtom = (TextView)findViewById(R.id.textView3);
       masa = (TextView)findViewById(R.id.textView4);
        btn1.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int posicion = elementos.getSelectedItemPosition();

              if(check1.isChecked()){
                 simbolo = simbolos.get(posicion).toString();
                  resultado.setText("Simbolo: "+simbolo);
              } if(check2.isChecked()){
                numeroAt = numerosAtomicos.get(posicion).toString();
                  numAtom.setText("N.Atómico: "+numeroAt);
              }if(check3.isChecked()){
                    masita = masaAtomica.get(posicion).toString();
                    masa.setText("M.Atómico: "+masita);
                }

             /* Bundle bolsa = new Bundle();
              Intent intecion = new Intent(getApplicationContext(),Main2Activity.class);
              bolsa.putString("SIMBOLO",simbolo);
              bolsa.putString("NUMEROATOMICO",numeroAt);
              intecion.putExtras(bolsa);
              startActivity(intecion);*/

            }
        });

    /* Leer Datos de un archivo  */
        InputStream flujoEntrada =  getResources().openRawResource(R.raw.nuevo);
        InputStreamReader lector = new InputStreamReader(flujoEntrada);
        BufferedReader buffer = new BufferedReader(lector,8192);/*8192 es el tamaño*/
        check1 = (CheckBox)findViewById(R.id.checkBox);
        check2 = (CheckBox)findViewById(R.id.checkBox2);
        check3 = (CheckBox)findViewById(R.id.checkBox3);


        try {
            String linea = buffer.readLine();
            while(linea!=null) {
                 arregloElementos = linea.split(",");
                linea = buffer.readLine();
                nombres.add(arregloElementos[0]);
                simbolos.add(arregloElementos[1]);
                numerosAtomicos.add(arregloElementos[2]);
                masaAtomica.add(arregloElementos[3]);
                adElementos = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, nombres);
                elementos.setAdapter(adElementos);
            }


        } catch (IOException e) {
            Toast.makeText(this,"Ocurrio un problema: " +e.getMessage(),Toast.LENGTH_SHORT).show();
        }
        finally {
            try {
                buffer.close();
                lector.close();
                flujoEntrada.close();

            } catch (IOException e) {
                Toast.makeText(this,"Ocurrio un problema: "+ e.getMessage(),Toast.LENGTH_SHORT).show();

            }

        }

    }

  //Este Metodo Crea un Menu Y lo Hace Visible
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    // Menu Para limpiar el contenido
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int  id = item.getItemId();
        if(id == R.id.eliminar){
          resultado.setText("");
          numAtom.setText("");
            masa.setText("");
            check1.setChecked(false);
            check2.setChecked(false);
            check3.setChecked(false);


        }
        return super.onOptionsItemSelected(item);
    }
}
