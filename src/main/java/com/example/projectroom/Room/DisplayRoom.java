package com.example.projectroom.Room;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.projectroom.Customer.GetAllCustomers;
import com.example.projectroom.Customer.UpdateCustomer;
import com.example.projectroom.Interfare.CustomerServer;
import com.example.projectroom.Process.AsyncResponse;
import com.example.projectroom.Process.AvailRooms;
import com.example.projectroom.Process.GetRoomProcess;
import com.example.projectroom.Process.RoomPOJO;
import com.example.projectroom.R;

import java.util.Calendar;
import java.util.List;

public class DisplayRoom extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    EditText ed_rno,ed_totDeposit,ed_totMainten,ed_totRent;
    EditText ed_name,ed_addrs,ed_birth,ed_contact,ed_contact2,ed_email,ed_adhaarCard,ed_PANCard,
            ed_initDate,ed_endDate,ed_mtrReading;
    Spinner spin;
    Button b_Load;
    List<RoomPOJO> productList;

    DatePickerDialog datePickerDialog;
    int year;
    int month;
    int dayOfMonth;
    Calendar calendar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_room);

        spin = findViewById(R.id.spin);
        spin.setOnItemSelectedListener(this);
        ed_rno = findViewById(R.id.ed_rno);
        ed_totDeposit = findViewById(R.id.ed_totDeposit);
        ed_totMainten = findViewById(R.id.ed_totMainten);
        ed_totRent = findViewById(R.id.ed_totRent);

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


        ed_birth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(DisplayRoom.this,
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
                datePickerDialog = new DatePickerDialog(DisplayRoom.this,
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
                datePickerDialog = new DatePickerDialog(DisplayRoom.this,
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


//        Bundle ban = getIntent().getExtras();
//
//        ed_rno.setText(ban.getString("s1"));
//        ed_totDeposit.setText(ban.getString("s2"));
//        ed_totMainten.setText(ban.getString("s3"));
//        ed_totRent.setText(ban.getString("s4"));

        AvailRooms AvailRooms = new AvailRooms(new AsyncResponse() {
            @Override
            public void processFinish(Object output) {
                String result = (String) output;
                System.out.println("MainAct: "+result);
                GetRoomProcess getRoomProcess = new GetRoomProcess(result);
                productList = getRoomProcess.getPlist();
                System.out.println("will add in Spinner: "+productList);
                ArrayAdapter adapter = new ArrayAdapter
                        (DisplayRoom.this,android.R.layout.simple_list_item_1,
                                productList);
                spin.setAdapter(adapter);
            }
        });
        AvailRooms.execute();

        b_Load = findViewById(R.id.b_load);
        b_Load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AvailRooms AvailRooms = new AvailRooms(new AsyncResponse() {
                    @Override
                    public void processFinish(Object output) {
                        String result = (String) output;
                        System.out.println("MainAct: "+result);
                        GetRoomProcess getRoomProcess = new GetRoomProcess(result);
                        productList = getRoomProcess.getPlist();
                        System.out.println("will add in Spinner: "+productList);
                        ArrayAdapter adapter = new ArrayAdapter
                                (DisplayRoom.this,android.R.layout.simple_list_item_1,
                                        productList);
                        spin.setAdapter(adapter);
                    }
                });
                AvailRooms.execute();
            }
        });
    }

    public void allCustomer(View v){
        startActivity(new Intent(this, GetAllCustomers.class));
    }

    public void onClear(View v){
        ed_name.setText("");
        ed_addrs.setText("");
        ed_birth.setText("");
        ed_contact.setText("");
        ed_contact2.setText("");
        ed_email.setText("");
        ed_adhaarCard.setText("");
        ed_PANCard.setText("");
        ed_initDate.setText("");
        ed_endDate.setText("");
        ed_mtrReading.setText("");
        ed_name.requestFocus();
    }

    public void custSave(View v){
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
        String args="addCust";

        new CustomerServer(DisplayRoom.this).execute(args,name,addrs,Bdate,Ph1,Ph2,Email,
                Adhaar,PAN,JoinDate,LeaveDate,Meter,RoomNum);
    }


    //SPINNER WORK
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//        Toast.makeText(this,""+productList.get(position).getRno(),Toast.LENGTH_SHORT).show();
        ed_rno.setText(productList.get(position).getRno());
        ed_totDeposit.setText(productList.get(position).getRdep());
        ed_totMainten.setText(productList.get(position).getRmain());
        ed_totRent.setText(productList.get(position).getRent());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
