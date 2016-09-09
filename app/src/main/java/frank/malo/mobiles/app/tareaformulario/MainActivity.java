package frank.malo.mobiles.app.tareaformulario;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText et_nombre;
    EditText et_telefono;
    EditText et_mail;
    EditText et_descripcion;
    DatePicker dp_fecha_nacimiento;
    boolean editar = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //obtengo la referencia a los controles de usuario
        et_nombre = (EditText) findViewById(R.id.etNombreCompleto);
        et_telefono  = (EditText) findViewById(R.id.etTelefono);
        et_mail  = (EditText) findViewById(R.id.etEmail);
        et_descripcion  = (EditText) findViewById(R.id.etDescripcion);
        dp_fecha_nacimiento = (DatePicker) findViewById(R.id.dpFechaNacimiento);

        Bundle parametros = getIntent().getExtras();
        //valido un parametro para saber si está en modo de edición
        try{
            editar = parametros.getBoolean(getResources().getString(R.string.flag_editar));
        }catch (NullPointerException ex){
            editar = false;
        }

        //si es que estoy editando, seteo los valore recuperados en el Intent
        if (editar){
            String nombre = parametros.getString(getResources().getString(R.string.pnombre_completo));
            String telefono = parametros.getString(getResources().getString(R.string.ptelefono));
            String mail = parametros.getString(getResources().getString(R.string.pmail));
            String descripcion = parametros.getString(getResources().getString(R.string.pdescripcion));
            int anio = parametros.getInt(getResources().getString(R.string.panio));
            int mes = parametros.getInt(getResources().getString(R.string.pmes));
            int dia = parametros.getInt(getResources().getString(R.string.pdia));

            et_nombre.setText(nombre);
            et_telefono.setText(telefono);
            et_mail.setText(mail);
            et_descripcion.setText(descripcion);

            dp_fecha_nacimiento.updateDate(anio, mes, dia);
        }
    }

    //método que está en el evento onclick del botón para enviar los datos que el usuario ingresa a la siguiente actividad
    public void EnviarDatos(View view){
        String nombre = et_nombre.getText().toString();
        String telefono = et_telefono.getText().toString();
        String mail = et_mail.getText().toString();
        String descripcion = et_descripcion.getText().toString();
        int anio = dp_fecha_nacimiento.getYear();
        int mes = dp_fecha_nacimiento.getMonth();
        int dia = dp_fecha_nacimiento.getDayOfMonth();

        Intent intent = new Intent(MainActivity.this, DatosFormulario.class);
        intent.putExtra(getResources().getString(R.string.pnombre_completo), nombre);
        intent.putExtra(getResources().getString(R.string.ptelefono), telefono);
        intent.putExtra(getResources().getString(R.string.pmail), mail);
        intent.putExtra(getResources().getString(R.string.pdescripcion), descripcion);
        intent.putExtra(getResources().getString(R.string.panio), anio);
        intent.putExtra(getResources().getString(R.string.pmes), mes);
        intent.putExtra(getResources().getString(R.string.pdia), dia);

        startActivity(intent);
        finish();
    }

}
