package com.developer.tonny.myroutine.Activities;

import android.content.DialogInterface;
import android.os.StrictMode;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.developer.tonny.myroutine.Adapters.Encriptado;
import com.developer.tonny.myroutine.Conexion;
import com.developer.tonny.myroutine.R;

public class SettingsActivity extends AppCompatActivity {

    public Encriptado encode = new Encriptado();
    public Conexion cn = new Conexion();
    private Button btnCambiar;
    private EditText edtPass;
    private TextView txtCuenta;
    private AlertDialog.Builder dialogo;
    public String nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        setTitle("Ajustes");
        setStrictMode();

        txtCuenta = (TextView) findViewById(R.id.textViewCuenta);
        //txtCuenta.setText("Bienvenido " + nombre);

        edtPass = (EditText) findViewById(R.id.editTextPass);
        btnCambiar = (Button) findViewById(R.id.buttonCambiar);
        btnCambiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDialog();
            }
        });
        nombre = getIntent().getStringExtra("name");

    }

    private void setDialog() {
        dialogo = new AlertDialog.Builder(this);
        dialogo.setTitle("Aviso");
        dialogo.setMessage("¿ Está seguro que desea guardar los cambios ?");
        dialogo.setCancelable(false);
        dialogo.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                actualizar();
            }
        });
        dialogo.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                dialogo1.dismiss();
            }
        });
        dialogo.show();
    }

    public void actualizar() {
        String pwd = encode.shaEncryption(edtPass.getText().toString());

        try {

            if (pwd.equals("")) {
                showToast("Complete los campos.");
            } else {
                String respuesta = cn.postUsuario("http://tonnyxp20.x10host.com/Podometro/actualizarUsuario.php", encode.shaEncryption(nombre), pwd, null);

                showToast(respuesta);
            }
            edtPass.setText("");

        } catch (Exception x) {
            Toast.makeText(this, "Acceso denegado.", Toast.LENGTH_SHORT).show();
        }
    }

    private void setStrictMode() {
        StrictMode.ThreadPolicy policy = new StrictMode
                .ThreadPolicy.Builder()
                .permitAll()
                .build();

        StrictMode.setThreadPolicy(policy);
    }

    public void showToast(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

}
