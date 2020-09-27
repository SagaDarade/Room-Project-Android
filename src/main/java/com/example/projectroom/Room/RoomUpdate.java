package com.example.projectroom.Room;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.projectroom.Interfare.ServerCall;
import com.example.projectroom.R;

public class RoomUpdate extends AppCompatActivity {

    EditText ed_rno,ed_totDeposit,ed_totMainten,ed_totRent;
    Button b_update, b_delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_update);

        ed_rno = findViewById(R.id.ed_rno);
        ed_totDeposit = findViewById(R.id.ed_totDeposit);
        ed_totMainten = findViewById(R.id.ed_totMainten);
        ed_totRent = findViewById(R.id.ed_totRent);

        b_update = findViewById(R.id.btn_update);
        b_delete = findViewById(R.id.btn_delete);

        Bundle ban = getIntent().getExtras();

        ed_rno.setText(ban.getString("s1"));
        ed_totDeposit.setText(ban.getString("s2"));
        ed_totMainten.setText(ban.getString("s3"));
        ed_totRent.setText(ban.getString("s4"));

        b_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onUpdate(v);
                System.out.println("UPDATE button clicked.");
            }
        });

        b_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDelete(v);
            }
        });
    }

    public void onUpdate(View v){
        String Rno = ed_rno.getText().toString().trim();
        String Deposit = ed_totDeposit.getText().toString().trim();
        String Maintenance = ed_totMainten.getText().toString().trim();
        String Rent = ed_totRent.getText().toString().trim();

        ServerCall scall = new ServerCall(RoomUpdate.this);
        scall.execute("update",Deposit, Maintenance, Rent,Rno);
        System.out.println("UPDATE Task performed.");
    }

    public void onDelete(View v){
        String Rno = ed_rno.getText().toString().trim();

        ServerCall sc = new ServerCall(RoomUpdate.this);
        sc.execute("delete",Rno);
    }

    public void clrText()
    {
        startActivity(new Intent(RoomUpdate.this,GetAllRooms.class));
    }
}
