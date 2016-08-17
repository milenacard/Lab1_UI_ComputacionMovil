package co.edu.udea.compumovil.gr3.lab1ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

/** Clase que contiene el control de la actividad principal
 *  @author Andrés Huertas
 *          Milena Cardenas
 */
public class Lab1UI extends AppCompatActivity implements View.OnClickListener{

    private EditText nombre;
    private EditText apellido;
    private EditText fechaNacimiento;
    private AutoCompleteTextView pais;
    private EditText telefono;
    private EditText direccion;
    private EditText email;
    private Spinner hobbies;
    private RadioGroup sexo;
    private RadioButton masculino;
    private RadioButton femenino;
    private CheckBox favorito;
    private String sexoStr;
    private String favoritoStr;

    private TextView tituloInfoRecolectada;
    private TextView infoResultado;

    private String [] listaPaises;
    private String [] listaHobbies;
    private ArrayAdapter<String> adaptadorPais;
    private ArrayAdapter<String> adaptadorHobbies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab1_ui);

        /** Identificación de componentes*/
        pais = (AutoCompleteTextView) findViewById(R.id.pais);
        hobbies = (Spinner) findViewById(R.id.hobbies_array);

        /** Sección de autocompletar paises */
        listaPaises = getResources().getStringArray(R.array.latinoAmerica_array);
        adaptadorPais = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listaPaises);
        pais.setAdapter(adaptadorPais);

        /** Sección de spinner hobbies*/
        listaHobbies = getResources().getStringArray(R.array.hobbies_array);
        adaptadorHobbies = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listaHobbies);
        hobbies.setAdapter(adaptadorHobbies);

        /** Seccion Sexo*/
        sexo = (RadioGroup) findViewById(R.id.radioGroupSexo);
        masculino = (RadioButton) findViewById(R.id.masculino);
        femenino = (RadioButton)findViewById(R.id.femenino);
        sexo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.masculino){
                    sexoStr = getResources().getString(R.string.masculino);
                } else {
                    sexoStr = getResources().getString(R.string.femenino);
                }
            }
        });

        /**Seccion Favorito*/
        favorito = (CheckBox)findViewById(R.id.favorito);
        favorito.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if (((CheckBox)v).isChecked()) {
                    favoritoStr = "Si";
                } else{
                    favoritoStr = "No";
                }
            }
        });

    }

    /**Sección del datePicker*/
    protected void onStart() {
        super.onStart();
        fechaNacimiento = (EditText) findViewById(R.id.fechaNacimiento_datePicker);
        fechaNacimiento.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    DateDialog dialogoFecha = new DateDialog(v);
                    android.app.FragmentTransaction fragmentTrans = getFragmentManager().beginTransaction();
                    dialogoFecha.show(fragmentTrans,"DatePicker");
                }
            }
        });
    }

    public void clickValidarMostrar(View view){
        boolean validacion = true;

        /** Identificación de componentes*/
        nombre = (EditText)findViewById(R.id.nombre);
        apellido = (EditText)findViewById(R.id.apellido);
        fechaNacimiento = (EditText) findViewById(R.id.fechaNacimiento_datePicker);
        pais = (AutoCompleteTextView) findViewById(R.id.pais);
        telefono = (EditText)findViewById(R.id.telefono);
        direccion = (EditText)findViewById(R.id.direccion);
        email = (EditText)findViewById(R.id.email);
        tituloInfoRecolectada = (TextView)findViewById(R.id.tituloInfoRecolectada);
        infoResultado = (TextView) findViewById(R.id.infoResultado);


        /** Validaciones*/
        if( nombre.getText().toString().length() == 0 ) {
            nombre.setError(getResources().getString(R.string.nombreObligatorio));
            tituloInfoRecolectada.setVisibility(View.INVISIBLE);
            infoResultado.setVisibility(View.INVISIBLE);
            validacion = false;
        }
        if( fechaNacimiento.getText().toString().length() == 0 ){
            fechaNacimiento.setError(getResources().getString(R.string.fechaNacimientoObligatoria));
            tituloInfoRecolectada.setVisibility(View.INVISIBLE);
            infoResultado.setVisibility(View.INVISIBLE);
            validacion = false;
        }
        if( pais.getText().toString().length() == 0 ){
            pais.setError(getResources().getString(R.string.paisObligatorio));
            tituloInfoRecolectada.setVisibility(View.INVISIBLE);
            infoResultado.setVisibility(View.INVISIBLE);
            validacion = false;
        }
        if( telefono.getText().toString().length() == 0 ){
            telefono.setError(getResources().getString(R.string.telefonoObligatorio));
            tituloInfoRecolectada.setVisibility(View.INVISIBLE);
            infoResultado.setVisibility(View.INVISIBLE);
            validacion = false;
        }
        if( email.getText().toString().length() == 0 ){
            email.setError(getResources().getString(R.string.emailObligatorio));
            tituloInfoRecolectada.setVisibility(View.INVISIBLE);
            infoResultado.setVisibility(View.INVISIBLE);
            validacion = false;
        }
        if (validacion) {
            StringBuffer resultadoBuffer = new StringBuffer();
            resultadoBuffer.append(getResources().getString(R.string.nombre) + ": " + nombre.getText()+"\n");
            resultadoBuffer.append(getResources().getString(R.string.apellido) + ": " + apellido.getText()+"\n");
            resultadoBuffer.append(getResources().getString(R.string.sexo) + " "+sexoStr+"\n");
            resultadoBuffer.append(getResources().getString(R.string.fecha_nacimiento) + " " + fechaNacimiento.getText()+"\n");
            resultadoBuffer.append(getResources().getString(R.string.pais) + ": " + pais.getText()+"\n");
            resultadoBuffer.append(getResources().getString(R.string.telefono) + ": " + telefono.getText()+"\n");
            resultadoBuffer.append(getResources().getString(R.string.direccion) + ": " + direccion.getText()+"\n");
            resultadoBuffer.append(getResources().getString(R.string.email) + ": " + email.getText()+"\n");
            resultadoBuffer.append(getResources().getString(R.string.hobbie) + " "+hobbies.getSelectedItem().toString()+"\n");
            resultadoBuffer.append(getResources().getString(R.string.favorito) + ": "+favoritoStr+"\n");

            infoResultado.setText(resultadoBuffer.toString());
            tituloInfoRecolectada.setVisibility(View.VISIBLE);
            infoResultado.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
    }

}
