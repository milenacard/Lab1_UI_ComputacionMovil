package co.edu.udea.compumovil.gr3.lab1ui;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

/**
 * Created by Andrés Huertas on 15/08/2016.
 */
public class DateDialog extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    EditText textoFecha;
    public DateDialog(View view){
        textoFecha = (EditText) view;
    }

    public Dialog onCreateDialog(Bundle savedInstanceState){
        final Calendar calendario = Calendar.getInstance();
        int año = calendario.get(Calendar.YEAR);
        int mes = calendario.get(Calendar.MONTH);
        int dia = calendario.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(), this,año, mes, dia);
    }

    public void onDateSet(DatePicker view, int año, int mes, int dia){
        String fecha = dia + "/"+(mes+1)+"/"+año;
        textoFecha.setText(fecha);
        }


}
