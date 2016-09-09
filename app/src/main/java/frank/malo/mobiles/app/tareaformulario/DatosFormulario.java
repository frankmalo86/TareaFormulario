package frank.malo.mobiles.app.tareaformulario;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DatosFormulario extends AppCompatActivity {

    TextView txNombreCompleto;
    TextView txTelefono;
    TextView txEmail;
    TextView txDescripcion;
    TextView txFechaNacimiento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_formulario);

        Bundle extras = getIntent().getExtras();
        String nombre_completo = extras.getString(getResources().getString(R.string.pnombre_completo));
        String telefono = extras.getString(getResources().getString(R.string.ptelefono));
        String email = extras.getString(getResources().getString(R.string.pmail));
        String descripcion = extras.getString(getResources().getString(R.string.pdescripcion));
        int anio = extras.getInt(getResources().getString(R.string.panio));
        int mes = extras.getInt(getResources().getString(R.string.pmes));
        int dia = extras.getInt(getResources().getString(R.string.pdia));

        txNombreCompleto = (TextView)findViewById(R.id.tx_nombre_completo);
        txTelefono = (TextView)findViewById(R.id.tx_telefono);
        txDescripcion = (TextView)findViewById(R.id.tx_descripcion);
        txFechaNacimiento = (TextView)findViewById(R.id.tx_fecha_nacimiento);
        txEmail = (TextView)findViewById(R.id.tx_email);

        txNombreCompleto.setText(nombre_completo);
        txTelefono.setText(telefono);
        txDescripcion.setText(descripcion);
        txFechaNacimiento.setText(dia+"/"+(mes+1)+"/"+anio);
        txEmail.setText(email);

        Button btn_editar = (Button)findViewById(R.id.btnEditar);
        btn_editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EnviarDatos();
            }
        });

    }


    private void EnviarDatos(){
        String nombre_completo = txNombreCompleto.getText().toString();
        String telefono = txTelefono.getText().toString();
        String descripcion = txDescripcion.getText().toString();
        String email = txEmail.getText().toString();
        String fecha_nacimiento = txFechaNacimiento.getText().toString();
        boolean editar = true;

        Intent intent = new Intent(DatosFormulario.this, MainActivity.class);
        intent.putExtra(getResources().getString(R.string.pnombre_completo), nombre_completo);
        intent.putExtra(getResources().getString(R.string.ptelefono), telefono);
        intent.putExtra(getResources().getString(R.string.pdescripcion), descripcion);
        intent.putExtra(getResources().getString(R.string.pmail), email);
        intent.putExtra(getResources().getString(R.string.flag_editar), true);

        try {
            Calendar fecha = stringToCalendar(fecha_nacimiento);
            intent.putExtra(getResources().getString(R.string.panio), fecha.get(Calendar.YEAR));
            intent.putExtra(getResources().getString(R.string.pmes), fecha.get(Calendar.MONTH));
            intent.putExtra(getResources().getString(R.string.pdia), fecha.get(Calendar.DAY_OF_MONTH));
        } catch (Exception e) {
            e.printStackTrace();
            Calendar now = Calendar.getInstance();
            intent.putExtra(getResources().getString(R.string.panio), Calendar.getInstance().get(Calendar.YEAR));
            intent.putExtra(getResources().getString(R.string.pmes), Calendar.getInstance().get(Calendar.MONTH));
            intent.putExtra(getResources().getString(R.string.pdia), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        }

        startActivity(intent);
        finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            Intent intent = new Intent(DatosFormulario.this, MainActivity.class);
            startActivity(intent);
        }
        return super.onKeyDown(keyCode, event);
    }

    private Calendar stringToCalendar (String fecha) throws ParseException {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        cal.setTime(sdf.parse(fecha));
        return cal;
    }

}
