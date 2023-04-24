package com.example.th2_1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.th2_1.dal.SQLiteHelper;
import com.example.th2_1.model.Item;

import java.util.Calendar;

public class UpdateDeleteActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText eTitle, eContent, eDate;
    private RadioGroup rgOption1, rgOption2;
    private RadioButton rb1, rb2, rb3, rb4, rb5;
    private Button btUpdate, btCancel, btDelete;
    private Item item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);
        initView();
        btCancel.setOnClickListener(this);
        btDelete.setOnClickListener(this);
        btUpdate.setOnClickListener(this);
        eDate.setOnClickListener(this);
        Intent intent = getIntent();
        item = (Item)intent.getSerializableExtra("item");
        eTitle.setText(item.getTitle());
        eContent.setText(item.getContent());
        eDate.setText(item.getDate());
        if (item.getStatus().equalsIgnoreCase("chưa thực hiện")){
            rb1.setChecked(true);
        } else if (item.getStatus().equalsIgnoreCase("đang thực hiện")) {
            rb2.setChecked(true);
        } else if (item.getStatus().equalsIgnoreCase("hoàn thành")) {
            rb3.setChecked(true);
        }
        if (item.isCollaborate()){
            rb5.setChecked(true);
        } else if (!item.isCollaborate()) {
            rb4.setChecked(true);
        }
    }

    private void initView() {
        eTitle = findViewById(R.id.eTitle);
        eContent = findViewById(R.id.eContent);
        eDate = findViewById(R.id.eDate);
        btCancel = findViewById(R.id.btCancel);
        btUpdate = findViewById(R.id.btUpdate);
        btDelete = findViewById(R.id.btDelete);
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
        SQLiteHelper db = new SQLiteHelper(this);
        if(view==eDate ){
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dialog = new DatePickerDialog(UpdateDeleteActivity.this, new DatePickerDialog.OnDateSetListener() {
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
        if(view == btDelete){
            int id = item.getId();
            AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
            builder.setTitle("Thông báo");
            builder.setMessage("Bạn có chắc muốn xóa " + item.getTitle()+" không?");
            builder.setIcon(R.drawable.remove);
            builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    db.deleteItem(id);
                    finish();
                }

            });
            builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();
        }
        if(view == btUpdate){
            int id = item.getId();
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
                Item i = new Item(id,t,c,d,s,collab);
                db.updateItem(i);
                finish();
            }
        }
    }
}