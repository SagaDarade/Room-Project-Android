package com.example.projectroom.Customer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.projectroom.Interfare.CustomerServer;
import com.example.projectroom.R;

import java.util.Calendar;

public class UpdateCustomer extends AppCompatActivity {

    EditText ed_rno, ed_name,ed_addrs,ed_birth,ed_contact,ed_contact2,ed_email,ed_adhaarCard,
            ed_PANCard,ed_initDate,ed_endDate,ed_mtrReading;
    int cid;
    String id=null;
    DatePickerDialog datePickerDialog;
    int year;
    int month;
    int dayOfMonth;
    Calendar calendar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_customer);

        ed_rno = findViewById(R.id.ed_rno);

        ed_name = findViewById(R.id.ed_name);
        ed_addrs = findViewById(R.id.ed_addrs);
        ed_birth = findViewById(R.id.ed_birth);
        ed_contact = findViewById(R.id.ed_contact);
        ed_contact2 = findViewById(R.id.ed_contact2);
        ed_email = findViewById(R.id.ed_email);
        ed_adhaarCard = findViewById(R.id.ed_adhaarCard);
        ed_PANCard = findViewById(R.id.ed_PANCard);
        ed_initDate = findViewById(R.id.ed_initDate);
        ed_endDate = findViewById(R.id.ed_endDate);
        ed_mtrReading = findViewById(R.id.ed_mtrReading);

        Bundle ban = getIntent().getExtras();
//        cid = ban.getInt("c00cid");
        id = ban.getString("c00cid");
        ed_name.setText(ban.getString("c01Name"));
        ed_addrs.setText(ban.getString("c02Addr"));
        ed_birth.setText(ban.getString("c03BDate"));
        ed_contact.setText(ban.getString("c04Ph1"));
        ed_contact2.setText(ban.getString("c05Ph2"));
        ed_email.setText(ban.getString("c06Email"));
        ed_adhaarCard.setText(ban.getString("c07Adhar"));
        ed_PANCard.setText(ban.getString("c08PAN"));
        ed_initDate.setText(ban.getString("c09Jd"));
        ed_endDate.setText(ban.getString("c10Ld"));
        ed_mtrReading.setText(ban.getString("c11Mtr"));
        ed_rno.setText(ban.getString("c12Rno"));

        ed_birth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(UpdateCustomer.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                ed_birth.setText(day + "/" + (month + 1) + "/" + year);
                            }
                        }, year, month, dayOfMonth);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });
        ed_initDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(UpdateCustomer.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                ed_initDate.setText(day + "/" + (month + 1) + "/" + year);
                            }
                        }, year, month, dayOfMonth);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog.show();

            }
        });

        ed_endDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(UpdateCustomer.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                ed_endDate.setText(day + "/" + (month + 1) + "/" + year);
                            }
                        }, year, month, dayOfMonth);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });


    }

    public void custUpdate(View v){
        String name = ed_name.getText().toString().toUpperCase().trim();
        String addrs = ed_addrs.getText().toString().toUpperCase().trim();
        String Bdate = ed_birth.getText().toString().toUpperCase().trim();
        String Ph1 = ed_contact.getText().toString().toUpperCase().trim();
        String Ph2 = ed_contact2.getText().toString().toUpperCase().trim();
        String Email = ed_email.getText().toString().trim();
        String Adhaar = ed_adhaarCard.getText().toString().trim();
        String PAN = ed_PANCard.getText().toString().toUpperCase().trim();
        String JoinDate = ed_initDate.getText().toString().trim();
        String LeaveDate = ed_endDate.getText().toString().trim();
        String Meter = ed_mtrReading.getText().toString().trim();
        String RoomNum = ed_rno.getText().toString().trim();

        CustomerServer cs = new CustomerServer(this);
        cs.execute("updtCust",name,addrs,Bdate,Ph1,Ph2,Email,Adhaar,PAN,JoinDate,LeaveDate,
                Meter,RoomNum,id);
    }

    public void custDel(View v){
        String RoomNum = ed_rno.getText().toString().trim();
        CustomerServer cs = new CustomerServer(this);
        cs.execute("delCust",RoomNum,id);
    }
}