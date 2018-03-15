package com.developer.tonny.database.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.developer.tonny.database.R;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        setTitle("Menu");
    }

    public void OnViewClicked(View view) {
        Intent i = new Intent(this, ViewActivity.class);
        startActivity(i);
        finish();
    }

    public void OnInsertClicked(View view) {
        Intent i = new Intent(this, InsertActivity.class);
        startActivity(i);
    }

    public void OnDeleteClicked(View view) {
        Intent i = new Intent(this, DeleteActivity.class);
        startActivity(i);
    }

    public void OnUpdateClicked(View view) {
        Intent i = new Intent(this, UpdateActivity.class);
        startActivity(i);
    }

    public void OnRegistryClicked(View view) {
        Intent i = new Intent(this, RegistryActivity.class);
        startActivity(i);
    }

    public void OnLogoutClicked(View view) {
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
        finish();
    }

}
