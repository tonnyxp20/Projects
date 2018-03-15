package com.developer.tonny.suma;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button suma;
    EditText num1, num2, res;
    // TextView res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        suma = (Button) findViewById(R.id.btnSuma);
        num1 = (EditText) findViewById(R.id.edtNum1);
        num2 = (EditText) findViewById(R.id.edtNum2);
        // res = (TextView) findViewById(R.id.tvRes);
        res = (EditText) findViewById(R.id.edtRes);
        res.setEnabled(false);
        suma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int n1 = Integer.parseInt(num1.getText().toString());
                int n2 = Integer.parseInt(num2.getText().toString());

                int sum = n1 + n2;
                String result = String.valueOf(sum);
                res.setText(result);
            }
        });
    }
}
