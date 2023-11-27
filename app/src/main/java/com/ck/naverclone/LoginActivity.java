package com.ck.naverclone;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, View.OnFocusChangeListener, AdapterView.OnItemSelectedListener{

    private final String ID_IS_NULL = "ID IS NULL";
    private final String PASSWORD_IS_NULL = "PASSWORD IS NULL";
    EditText loginId;
    EditText loginPassword;

    Button loginButton;

    String language[] = {"한국어", "English", "中文(简体)", "中文(台灣)"};
    String regId, regPassword;

    ArrayAdapter<String> adapter;

    SharedPreferences pref;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginId = findViewById(R.id.login_id);
        clickAndFocus(loginId);

        loginPassword = findViewById(R.id.login_pw);
        clickAndFocus(loginPassword);

        loginButton = findViewById(R.id.button6);
        clickAndFocus(loginButton);

        Spinner languageSpinner = findViewById(R.id.spinner5);

        adapter = new ArrayAdapter<String>(this, R.layout.spinner_language, language);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        languageSpinner.setAdapter(adapter);
        languageSpinner.setOnItemSelectedListener(this);
        languageSpinner.setSelection(0, false);

        pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);
        editor = pref.edit();
    }

    public void clickAndFocus(View view){
        view.setOnClickListener(this);
        view.setOnFocusChangeListener(this);
    }

    @Override
    public void onClick(View view) {
        int viewId = view.getId();

        if (viewId == R.id.button6) {
            String myId = loginId.getText().toString();
            String myPassword = loginPassword.getText().toString();

            int accCount = 1;
            if (myId.isEmpty() || myPassword.isEmpty()) {

                }
                else{
                    while(true){
                        regId = pref.getString("regId" + accCount, ID_IS_NULL);
                        regPassword = pref.getString("regPassword" + accCount, PASSWORD_IS_NULL);

                        if(regId.equals(ID_IS_NULL) || regPassword.equals(PASSWORD_IS_NULL)){
                            break;
                        }
                        else{
                            if(myId.equals(regId) && myPassword.equals(regPassword)){
                                Intent intent = new Intent(getApplicationContext(), LastActivity.class);
                                intent.putExtra("loginedId", regId);
                                intent.putExtra("loginedPassword", regPassword);
                                startActivity(intent);
                                finish();
                                break;
                            }
                        }

                        accCount++;
                    }
                }
        }
    }

    @Override
    public void onFocusChange(View view, boolean b) {

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

}