package com.ck.naverclone;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnFocusChangeListener, AdapterView.OnItemSelectedListener{

    //TextView 선언
    TextView tv_id_email;
    TextView alert; // 경고문 선언용
    TextView alert_id;
    TextView alert_password;
    TextView alert_password_check;
    TextView alert_name;
    TextView alert_birthday;
    TextView alert_gender;
    TextView alert_email;
    TextView alert_access;

    //ImageView 선언
    ImageView iv_password_lock;
    ImageView iv_password_lock_check;

    //EditText 선언
    EditText et_id;
    EditText et_password;
    EditText et_password_check;
    EditText et_name;
    EditText et_year;
    EditText et_date;
    EditText et_email;
    EditText et_access;

    //Button 선언
    Button register;

    //Layout 선언
    LinearLayout l_id;
    LinearLayout l_password;
    LinearLayout l_password_check;

    Drawable drawable;

    String month[] = {"월", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
    String gender[] = {"성별", "남자", "여자", "선택 안함"};
    String country[] = {"가나 +233", "독일 +49", "대한민국 +82", "미국/캐나다 +1", "일본 +81"};

    int accCount;

    ArrayAdapter<String> adapter;
    SharedPreferences pref;
    SharedPreferences.Editor editor;

    ViewGroup.LayoutParams layoutParams;

    Spinner spinner;
    Spinner spinner2;
    Spinner spinner3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = findViewById(R.id.spinner);
        spinner2 = findViewById(R.id.spinner2);
        spinner3 = findViewById(R.id.spinner3);

        //textView 할당
        tv_id_email = findViewById(R.id.textView_id_mail);
        tv_id_email.setOnClickListener(this);

        alert_id = findViewById(R.id.textView2);
        alert_password = findViewById(R.id.textView4);
        alert_password_check = findViewById(R.id.textView6);
        alert_name = findViewById(R.id.textView8);
        alert_birthday = findViewById(R.id.textView10);
        alert_gender = findViewById(R.id.textView12);
        alert_email = findViewById(R.id.textView31);
        alert_access = findViewById(R.id.textView15);

        getInit();

        //imageView 할당
        iv_password_lock = findViewById(R.id.imageView2);
        iv_password_lock.setOnClickListener(this);

        iv_password_lock_check = findViewById(R.id.imageView3);
        iv_password_lock_check.setOnClickListener(this);

        //editText 할당
        et_id = findViewById(R.id.editText_id);
        clickAndFocus(et_id);

        et_password = findViewById(R.id.editText_password);
        clickAndFocus(et_password);

        et_password_check = findViewById(R.id.editText_password_check);
        clickAndFocus(et_password_check);

        et_name = findViewById(R.id.editText_name);
        clickAndFocus(et_name);

        et_year = findViewById(R.id.editText_year);
        clickAndFocus(et_year);

        et_date = findViewById(R.id.editText_date);
        clickAndFocus(et_date);

        et_email = findViewById(R.id.editText_email);
        clickAndFocus(et_email);

        et_access = findViewById(R.id.editText_access_code);
        clickAndFocus(et_access);

        //button 할당
        register = findViewById(R.id.button2);
        register.setOnClickListener(this);

        //layout 할당
        l_id = findViewById(R.id.layout_id);
        l_password = findViewById(R.id.layout_password);
        l_password_check = findViewById(R.id.layout_password_check);

        //month spinner
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, month);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setSelection(0, false);
        spinner.setOnItemSelectedListener(this);

        //gender spinner
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, gender);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter);
        spinner2.setSelection(0, false);
        spinner2.setOnItemSelectedListener(this);

        //country spinner
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, country);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(adapter);
        spinner3.setSelection(0, false);
        spinner3.setOnItemSelectedListener(this);

        pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);
        editor = pref.edit();

    }

    private void clickAndFocus(View view){
        view.setOnClickListener(this);
        view.setOnFocusChangeListener(this);
    }

    private void getInit(){
        TextView[] alerts = {alert_id, alert_password, alert_password_check, alert_name, alert_birthday, alert_gender, alert_access};

        for(TextView alert : alerts){
            alertInit(alert);
        }
    }

    private void alertInit(TextView alert){
        ViewGroup.LayoutParams layoutParams = alert.getLayoutParams();

        layoutParams.width = 0;
        layoutParams.height = 0;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.textView_id_mail) {
            et_id.requestFocus();
        } else if (view.getId() == R.id.imageView2) {
            et_password.requestFocus();
        } else if (view.getId() == R.id.imageView3) {
            et_password_check.requestFocus();
        } else if (view.getId() == R.id.button2) {
            String regId = et_id.getText().toString();
            String regPassword = et_password.getText().toString();
            accCount = pref.getInt("accCount", 1);

                Log.d("acc", accCount +"");

                if(regId.isEmpty() || regPassword.isEmpty()){

                }
                else {
                    editor.putString("regId" + accCount, regId);
                    editor.putString("regPassword" + accCount, regPassword);
                    editor.putInt("accCount", accCount + 1);
                    editor.apply();

                    Log.d("acc", "ID: " + pref.getString("regId" + accCount, regId) + " PW: " + pref.getString("regPassword"+accCount,regPassword));

                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                    finish();
                }

        }
    }

    @Override
    public void onFocusChange(View view, boolean b) {

        if(b) {
            setDrawable(R.drawable.input_textline_focus);
        }
        else{
            setDrawable(R.drawable.input_textline_blur);
            if(view instanceof EditText) {
                String text = ((EditText) view).getText().toString();
                alertText(view, text);
            }
        }

        if (view.getId() == R.id.editText_id) {
            l_id.setBackground(drawable);
        } else if (view.getId() == R.id.editText_password) {
            l_password.setBackground(drawable);
        } else if (view.getId() == R.id.editText_password_check) {
            l_password_check.setBackground(drawable);
        }
    }

    private void setDrawable(int xmlUrl){ //Drawable
        drawable = getResources().getDrawable(xmlUrl, null);
    }


    private void alertText(View view, String text) {
        alert = null;

        int viewId = view.getId(); // viewId 변수를 선언하고 view.getId() 값을 저장합니다.

        if (viewId == R.id.editText_id) {
            alert = alert_id;
        } else if (viewId == R.id.editText_password) {
            alert = alert_password;
        } else if (viewId == R.id.editText_password_check) {
            alert = alert_password_check;
        } else if (viewId == R.id.editText_name) {
            alert = alert_name;
        } else if (viewId == R.id.editText_year || viewId == R.id.editText_date) {
            alert = alert_birthday;
        } else if (viewId == R.id.editText_email) {
            alert = alert_email;
        } else if (viewId == R.id.editText_access_code) {
            alert = alert_access;
        } else {
            Log.d("Error", "Error in alertText");
        }
    

        if (text.isEmpty()) {

            if(alert == alert_birthday) {
                getAlertNullText(alert, spinner);
            }
            else if(alert == alert_email){
                // X
            }
            else{
                getAlertNullText(alert);
            }

        }
        else {
            if(alert == alert_birthday) {
                getAlertNullText(alert, spinner);
            }
            else {
                getAlertText(alert);
            }
        }
    }

    private void alertNullLayoutParams(TextView view){
        layoutParams = view.getLayoutParams();

        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
    }

    private void alertLayoutParams(TextView view){
        layoutParams = view.getLayoutParams();

        layoutParams.width = 0;
        layoutParams.height = 0;

        view.setText("");
    }

    private void getAlertNullText(TextView view){
        alertNullLayoutParams(view);

        view.setText("필수 정보입니다.");
    }

    private void getAlertNullText(TextView view, Spinner spinner){
        alertNullLayoutParams(view);
        String year = et_year.getText().toString();
        String month = spinner.getSelectedItem().toString();
        String date = et_date.getText().toString();
        if(view == alert_birthday){
            if(year.length() != 4){
                view.setText("태어난 년도 4자리를 정확하게 입력하세요.");
            }
            else{
                if(month.equals("월")){
                    view.setText("태어난 월을 선택하세요.");
                }
                else{
                    if(date.isEmpty()) {
                        view.setText("태어난 일(날짜) 2자리를 정확하게 입력하세요.");
                    }
                    else{
                        Pattern yearPattern = Pattern.compile("\\d{4}");
                        Pattern datePattern = Pattern.compile("\\d{1,2}");
                        Matcher yearMatcher = yearPattern.matcher(year);
                        Matcher dateMatcher = datePattern.matcher(date);

                        if(yearMatcher.find() && dateMatcher.find()){
                            String bth = year + "-" + month + "-" + date;
                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

                            try {

                                String dth = dateFormat.format(new Date());
                                Date now = dateFormat.parse(dth);

                                Date birth = dateFormat.parse(bth);

                                long compare = now.getTime() - birth.getTime();

                                long compareDate = TimeUnit.MILLISECONDS.toDays(compare);
                                Toast.makeText(this.getApplicationContext(),""+compareDate, Toast.LENGTH_SHORT).show();
                                if(compareDate >= 36525){
                                    view.setText("정말이세요?");
                                }
                                else if(compareDate  < 5110){
                                    view.setText("만 14세 미만의 어린이는 보호자 동의가 필요합니다.");
                                }
                                else if(compareDate < 0){
                                    view.setText("미래에서 오셨군요. ^^");
                                }
                                else{
                                    alertLayoutParams(view);
                                }

                            } catch (ParseException e) {
                                throw new RuntimeException(e);
                            }


                        }
                        else{
                            view.setText("생년월일을 다시 확인해주세요.");
                        }
                    }
                }
            }
        }
    }

    private void getAlertText(TextView view){
        alertLayoutParams(view);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                    getAlertNullText(alert_birthday, spinner);

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}