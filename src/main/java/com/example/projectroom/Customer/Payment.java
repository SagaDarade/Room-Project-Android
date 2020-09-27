
package com.example.projectroom.Customer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectroom.R;

import java.util.Calendar;

public class Payment extends AppCompatActivity implements AdapterView.OnItemSelectedListener,
        View.OnClickListener {

    EditText ed_cName,ed_cRoomNo,ed_OldMtr,ed_NewMtr,ed_TotMtr,ed_unitRate,ed_rTotbill,ed_billAmt,
            ed_remAmt,ed_paidAmt,ed_inwards,ed_otherEx,ed_rTotRent;
    RadioButton rBtn_active,rBtn_deActive;

    int cid;

    TextView tv_date;
    DatePickerDialog.OnDateSetListener dateListener;

    TextWatcher txtTotMtr=null, tw_unitRate=null, tw_otherEx=null, tw_paid=null;

    Spinner spinner;
    RadioButton rb_act, rb_deact;
    String sitem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        spinner = findViewById(R.id.spinner);
        rb_act = findViewById(R.id.rBtn_active);
        rb_deact = findViewById(R.id.rBtn_deActive);

        ed_cName = findViewById(R.id.ed_cName);
        ed_cRoomNo = findViewById(R.id.ed_cRoomNo);
        ed_otherEx = findViewById(R.id.ed_otherEx);
        ed_OldMtr = findViewById(R.id.ed_OldMtr);
        ed_NewMtr = findViewById(R.id.ed_NewMtr);
        ed_TotMtr = findViewById(R.id.ed_TotMtr);
        ed_unitRate = findViewById(R.id.ed_unitRate);
        ed_rTotbill = findViewById(R.id.ed_rTotbill);
        ed_billAmt = findViewById(R.id.ed_billAmt);
        ed_remAmt = findViewById(R.id.ed_remAmt);
        ed_paidAmt = findViewById(R.id.ed_paidAmt);
        ed_inwards = findViewById(R.id.ed_inwards);;
        ed_rTotRent = findViewById(R.id.ed_rTotRent);;

        Bundle ban = getIntent().getExtras();
        cid = Integer.parseInt(ban.getString("c00cid"));
        ed_cName.setText(ban.getString("c01Name"));
        ed_OldMtr.setText(ban.getString("c11Mtr"));
        ed_cRoomNo.setText(ban.getString("c12Rno"));

        tv_date = findViewById(R.id.tvDate);
//        Calendar cal = Calendar.getInstance();
//        String time = ""+cal.get(Calendar.DAY_OF_MONTH)+"/"+(cal.get(Calendar.MONTH)+1)+"/"+
//                cal.get(Calendar.YEAR);
//        tv_date.setText(time);
        tv_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dp = new DatePickerDialog(
                        Payment.this,android.R.style.Theme,
                        dateListener,year,month,day);

                dp.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dp.show();
            }
        });

        dateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                month=+1;
                String date = day+"/"+month+"/"+year;
                tv_date.setText(date);
                Toast.makeText(getApplicationContext(),date,Toast.LENGTH_LONG).show();
            }
        };



        ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource(this,R.array.rSpin,
                        android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        txtTotMtr = new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                long newMtr = (long) Double.parseDouble(String.valueOf(ed_NewMtr.getText()));
                long oldMtr = (long) Double.parseDouble(String.valueOf(ed_OldMtr.getText()));
                long total = newMtr - oldMtr;
                ed_TotMtr.setText(""+total);
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        };

        tw_unitRate = new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                long totMtr = (long) Double.parseDouble(String.valueOf(ed_TotMtr.getText()));
                long rate = (long) Double.parseDouble(String.valueOf(ed_unitRate.getText()));
                long totBill = totMtr * rate;
                ed_rTotbill.setText(""+totBill);
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        };

        tw_otherEx = new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                long a,b,c;
                a = (long) Double.parseDouble(String.valueOf(ed_rTotbill.getText()));
                c = (long) Double.parseDouble(String.valueOf(ed_otherEx.getText()));
                b = (long) Double.parseDouble(String.valueOf(ed_rTotRent.getText()));
                ed_billAmt.setText(""+(a+b+c));
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        };

        tw_paid = new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                long a,b,c;
                a = (long) Double.parseDouble(String.valueOf(ed_billAmt.getText()));
                b = (long) Double.parseDouble(String.valueOf(ed_paidAmt.getText()));
                ed_remAmt.setText(""+(a-b));
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        };


        ed_otherEx.addTextChangedListener(tw_otherEx);
        ed_paidAmt.addTextChangedListener(tw_paid);
        ed_NewMtr.addTextChangedListener(txtTotMtr);
        ed_unitRate.addTextChangedListener(tw_unitRate);

        rb_act.setOnClickListener(this);
        rb_deact.setOnClickListener(this);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        if(pos==1){
            sitem = "RENT";    //RENT
            Toast.makeText(this,spinner.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();
        } else if(pos==2){
            sitem = "DEPOSIT";
            Toast.makeText(this,spinner.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();

        } else{
            sitem = "";
            Toast.makeText(this,"SELECT PAYMENT",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {
        if(findViewById(R.id.rBtn_deActive)==rb_deact || findViewById(R.id.rBtn_active)==rb_act) {
            if (rb_act.isChecked())
                Toast.makeText(this, "Status is ACTIVE", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this, "Status is DEACTIVE", Toast.LENGTH_SHORT).show();
        }
    }
}
