package com.example.projectroom.Room;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.projectroom.Domain.MainDomain;
import com.example.projectroom.Process.AsyncResponse;
import com.example.projectroom.Process.GetRoomProcess;
import com.example.projectroom.Process.GetRooms;
import com.example.projectroom.Process.RoomAdapter;
import com.example.projectroom.Process.RoomPOJO;
import com.example.projectroom.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class GetAvailRooms extends AppCompatActivity {

    Button btn;
    String result;

    RecyclerView recycler;
    RoomAdapter adapter;
    List<RoomPOJO> productList;
    List<RoomPOJO> availabel;
    Context ct;
    String arg = "available";
    int flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_avail_rooms);

/*        Bundle ban = getIntent().getExtras();
        flag = ban.getInt("flag");*/

        btn = findViewById(R.id.btnFetch);
        productList = new ArrayList<>();
        availabel=new ArrayList<>();
        recycler = findViewById(R.id.recView);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        GetRooms getRooms = new GetRooms(GetAvailRooms.this,arg,new AsyncResponse() {
            @Override
            public void processFinish(Object output) {
                result = (String) output;
                System.out.println("MainAct: "+result);
                GetRoomProcess getRoomProcess = new GetRoomProcess(result.trim());
                productList = getRoomProcess.getPlist();
                adapter = new RoomAdapter(getApplicationContext(),1, productList);
                recycler.setHasFixedSize(true);
                recycler.setAdapter(adapter);
            }
        });
        getRooms.execute();


//        RoomListDemo();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetRooms getRooms = new GetRooms(GetAvailRooms.this,arg,new AsyncResponse() {
                    @Override
                    public void processFinish(Object output) {
                        result = (String) output;
                        System.out.println("MainAct: "+result);
                        GetRoomProcess getRoomProcess = new GetRoomProcess(result.trim());
                        productList = getRoomProcess.getPlist();
                        adapter = new RoomAdapter(getApplicationContext(),1, productList);
                        recycler.setHasFixedSize(true);
                        recycler.setAdapter(adapter);
                    }
                });
                getRooms.execute();
            }
        });
    }

    public void onBook(View v) {
        startActivity(new Intent(GetAvailRooms.this, DisplayRoom.class));
    }
}
