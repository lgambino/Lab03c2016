package dam.isi.frsf.utn.edu.ar.lab03c2016bis;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MainActivity extends AppCompatActivity{

    private Toolbar toolbar;

    private ListView listView;
    private Adaptador adapter;
    private ArrayList<Trabajo> listTrabajos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listTrabajos = new ArrayList<Trabajo>();
        listTrabajos.addAll(Arrays.asList(Trabajo.TRABAJOS_MOCK));

        listView = (ListView) findViewById(R.id.listado);
        adapter = new Adaptador(this, listTrabajos, getResources());
        listView.setAdapter(adapter);
        //listView.setOnItemLongClickListener(this);

        registerForContextMenu(listView);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        super.onCreateContextMenu(menu,v,menuInfo);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_contextual,menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuNuevaOferta: {

                Intent i = new Intent(MainActivity.this, NuevaOferta.class);

                startActivityForResult(i, 0);
                return true;
            }
            default:{
                return super.onOptionsItemSelected(item);
            }
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.postularse: {
                //Toast.makeText(MainActivity.this, getString(R.string.label_postularse), Toast.LENGTH_SHORT).show();
                return true;
            }
            case R.id.compartir: {

                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

                Trabajo trabajo = (Trabajo) listView.getItemAtPosition(info.position);

                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                //intent.setPackage("com.whatsapp");
                //intent.putExtra(Intent.EXTRA_TEXT, trabajo.getDescripcionTrabajo(this));
                try {
                    startActivity(intent);
                } catch (android.content.ActivityNotFoundException ex) {
                   // Toast.makeText(MainActivity.this, getString(R.string.label_mensaje_no_instalado), Toast.LENGTH_SHORT).show();
                }
                return true;
            }
            default:{
                return super.onContextItemSelected(item);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Trabajo resultado = (Trabajo) data.getSerializableExtra("resultado");
        listTrabajos.add(resultado);
        adapter.notifyDataSetChanged();
        Toast.makeText(MainActivity.this, "Trabajo agregado.", Toast.LENGTH_SHORT).show();


    }
}