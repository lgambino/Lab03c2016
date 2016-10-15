package dam.isi.frsf.utn.edu.ar.lab03c2016bis;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Luciano on 14/10/2016.
 */
public class NuevaOferta extends AppCompatActivity {

    private Button guardar;
    private EditText txtOferta;
    private RadioGroup radioGroup;
    private Spinner spinner;
    private String oferta;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nueva_oferta);

        List<Categoria> categorias = Arrays.asList(Categoria.CATEGORIAS_MOCK);
        ArrayAdapter<Categoria> adaptadorSpinner = new ArrayAdapter<Categoria>(this, android.R.layout.simple_spinner_item, categorias);

        adaptadorSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner= (Spinner) findViewById(R.id.spinnerCategoria);
        spinner.setAdapter(adaptadorSpinner);

        txtOferta = (EditText) findViewById(R.id.TextOfertaIngresada);
        radioGroup = (RadioGroup) findViewById(R.id.rdGroup);
        guardar = (Button) findViewById(R.id.btnGuardar);
        //guardar.setOnClickListener(this);
    }


    public void onClick(View v) {
        oferta = txtOferta.getText().toString();

        Categoria categoria= (Categoria) spinner.getSelectedItem();

        if(oferta.equals("") || categoria==null ){
            Toast.makeText(v.getContext(), "Error. Complete los campos." , Toast.LENGTH_SHORT).show();
        }
        else {
            Trabajo trabajo = new Trabajo();
            trabajo.setCategoria(categoria);
            trabajo.setDescripcion(oferta);
            //1 US$ 2Euro 3 AR$- 4 Libra 5 R$
            switch (radioGroup.getCheckedRadioButtonId()) {
                case R.id.pesosAr:
                    trabajo.setMonedaPago(3);
                    break;
                case R.id.usd:
                    trabajo.setMonedaPago(1);
                    break;
                case R.id.euros:
                    trabajo.setMonedaPago(2);
                    break;
                case R.id.libras:
                    trabajo.setMonedaPago(4);
                    break;
                case R.id.reales:
                    trabajo.setMonedaPago(5);
                    break;
            }

            Intent i = getIntent();
            i.putExtra("resultado", trabajo);
            setResult(RESULT_OK, i);
            finish();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("oferta",txtOferta.getText().toString());
        outState.putInt("spinner",spinner.getSelectedItemPosition());
        outState.putInt("rdChecked", radioGroup.getCheckedRadioButtonId());
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        txtOferta.setText(savedInstanceState.getString("oferta"));
        spinner.setSelection(savedInstanceState.getInt("spinner"));

        RadioButton radioButton = (RadioButton) findViewById(savedInstanceState.getInt("rdChecked"));
        radioButton.setSelected(true);
    }
}
