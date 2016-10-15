package dam.isi.frsf.utn.edu.ar.lab03c2016bis;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by Luciano on 13/10/2016.
 */
public class Adaptador extends BaseAdapter {


    private Activity activity;
    private ArrayList dataList;
    private static LayoutInflater inflater = null;
    public Resources res;
    Trabajo trabajo = null;
    int i = 0;

    public Adaptador(Activity context, ArrayList dataList, Resources resLocal){
        activity = context;
        this.dataList = dataList;
        res = resLocal;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        if(dataList.size()<=0)
            return 1;
        return dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View vista = convertView;
        Holder holder;

        if(convertView ==null){
            vista = inflater.inflate(R.layout.item, null);

            holder = new Holder();
            holder.txtProfesion = (TextView) vista.findViewById(R.id.txt_profesion);
            holder.txtProyecto= (TextView) vista.findViewById(R.id.txt_proyecto);
            holder.txtHoras = (TextView) vista.findViewById(R.id.txt_horas);
            holder.txtCobro = (TextView) vista.findViewById(R.id.txt_cobro);
            holder.imgBandera = (ImageView) vista.findViewById(R.id.img_bandera);
            holder.txtFecha = (TextView) vista.findViewById(R.id.txt_fecha);
            holder.checkBox = (CheckBox) vista.findViewById(R.id.check_box);

            vista.setTag(holder);
        }
        else
            holder = (Holder) vista.getTag();

        if(dataList.size() <= 0){
            holder.txtProfesion.setText("Sin información");
        } else {

            SimpleDateFormat dt = new SimpleDateFormat("dd/mm/yyyy");
            NumberFormat formatter = new DecimalFormat("#0.00");

            trabajo = (Trabajo) dataList.get(position);

            holder.txtProfesion.setText(trabajo.getCategoria().getDescripcion());
            holder.txtProyecto.setText("asd1");
            holder.txtHoras.setText(trabajo.getDescripcion());
            holder.txtCobro.setText(trabajo.getDescripcion());
            holder.txtFecha.setText("asd2" + dt.format(trabajo.getFechaEntrega()));

            String directorio = "ar.edu.utn.frsf.isi.dam.lab03c2016bis:drawable/";
            switch (trabajo.getMonedaPago()){
                case 1: directorio += "us";break;
                case 2: directorio += "eu";break;
                case 3: directorio += "ar";break;
                case 4: directorio += "uk";break;
                case 5: directorio += "br";break; // 1 US$ 2Euro 3 AR$- 4 Libra 5 R$
                default: break;
            }
            holder.imgBandera.setImageResource(res.getIdentifier(directorio , null, null));
            holder.checkBox.setChecked(trabajo.getRequiereIngles());

        }
        return vista;
    }

    public static class Holder{

        public TextView txtProfesion;
        public TextView txtProyecto;
        public TextView txtHoras;
        public TextView txtCobro;
        public ImageView imgBandera;
        public TextView txtFecha;
        public CheckBox checkBox;
    }
}
