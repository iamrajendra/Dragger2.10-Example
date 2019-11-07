package com.iamrajendra.dragger2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.iamrajendra.dragger2.component.DaggerRComponent;
import com.iamrajendra.dragger2.component.RComponent;
import com.iamrajendra.dragger2.dragger2.module.SharedPrefModule;

import javax.inject.Inject;



public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText editTextUsername,editTextNumber;
    private Button buttonSave,buttonGet;
    private RComponent RComponent;
@Inject
    SharedPrefModule sharedPrefModule;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        RComponent = DaggerRComponent.builder().sharedPrefModule(new SharedPrefModule(this)).build();
        RComponent.inject(this);

    }

    private void initViews() {
        buttonSave = findViewById(R.id.btnSave);
        buttonGet = findViewById(R.id.btnGet);
        buttonGet.setOnClickListener(this);
        buttonSave.setOnClickListener(this);
        editTextUsername = findViewById(R.id.inUsername);
        editTextNumber = findViewById(R.id.inNumber);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btnGet:
                editTextUsername.setText(sharedPrefModule.provideSharedPreferences(getApplicationContext()).getString("username","default"));
                editTextNumber.setText(sharedPrefModule.provideSharedPreferences(getApplicationContext()).getString("number", "12345"));
                break;
            case R.id.btnSave:
                SharedPreferences.Editor editor = sharedPrefModule.provideSharedPreferences(getApplicationContext()).edit();
                editor.putString("username", editTextUsername.getText().toString().trim());
                editor.putString("number", editTextNumber.getText().toString().trim());
                editor.apply();
                break;

        }
    }
}
