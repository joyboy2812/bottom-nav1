package com.example.th2_1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.th2_1.dal.SQLiteHelper;
import com.example.th2_1.model.Item;

import java.util.Calendar;

public class AddActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText eTitle, eContent, eDate;
    private RadioGroup rgOption1, rgOption2;
    private RadioButton rb1, rb2, rb3, rb4, rb5;
    private Button btUpdate, btCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        initView();
        btCancel.setOnClickListener(this);
        btUpdate.setOnClickListener(this);
        eDate.setOnClickListener(this);
    }

    private void initView(){
        eTitle = findViewById(R.id.eTitle);
        eContent = findViewById(R.id.eContent);
        eDate = findViewById(R.id.eDate);
        btCancel = findViewById(R.id.btCancel);
        btUpdate = findViewById(R.id.btUpdate);
        rgOption1 = findViewById(R.id.radio_group1);
        rgOption2 = findViewById(R.id.radio_group2);
        rb1 = findViewById(R.id.option1);
        rb2 = findViewById(R.id.option2);
        rb3 = findViewById(R.id.option3);
        rb4 = findViewById(R.id.option4);
        rb5 = findViewById(R.id.option5);
    }

    @Override
    public void onClick(View view) {
        if(view==eDate ){
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dialog = new DatePickerDialog(AddActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    String date = "";
                    if(month > 8 ){
                        date = dayOfMonth + "/" + (month+1) + "/" + year;
                    }else{
                        date = dayOfMonth + "/0" + (month+1) + "/" + year;

                    }
                    eDate.setText(date);
                }
            }, year, month, day);
            dialog.show();
        }
        if(view == btCancel){
            finish();
        }
        if(view == btUpdate){
            String t = eTitle.getText().toString();
            String c = eContent.getText().toString();
            String d = eDate.getText().toString();
            String s = "";
            if (rb1.isChecked()){
                s = rb1.getText().toString();
            }else if (rb2.isChecked()){
                s = rb2.getText().toString();
            }else if (rb3.isChecked()){
                s = rb3.getText().toString();
            }
            boolean collab = false;
            if (rb4.isChecked()){
                collab = false;
            }else if (rb5.isChecked()){
                collab = true;
            }
            if(!t.isEmpty()){
                Item i = new Item(t,c,d,s,collab);
                SQLiteHelper db = new SQLiteHelper(this);
                db.insertItem(i);
                finish();
            }
        }
    }
}