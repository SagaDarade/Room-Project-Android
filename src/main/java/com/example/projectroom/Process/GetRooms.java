package com.example.projectroom.Process;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.example.projectroom.Domain.MainDomain;
import com.example.projectroom.Interfare.SubStrConvert;


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

public class GetRooms extends AsyncTask {
    Context c;

    ProgressDialog progressDialog;
    List<RoomPOJO> plist;
    String result;
    String key;
    public AsyncResponse delegate = null;       //Call back interface

    public GetRooms(Context ct,String key,AsyncResponse asyncResponse) {
        c = ct;
        this.key = key;
        delegate = asyncResponse;
        plist = new ArrayList<>();
    }

    public List<RoomPOJO>getPlist(){
        return plist;
    }

    public String getResult(){return result;}

    private void progress() {
        progressDialog = new ProgressDialog(c);
        progressDialog.setTitle("Syncing...");
        progressDialog.setMessage("Please wait !");
        progressDialog.show();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progress();
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        //All Rooms list query will fire and all rooms will display.
        if(key.equals("all")) {
            String myurl = MainDomain.getDomain() + "viewAllRooms.php";
            System.out.println("2nd: " + myurl);
            try {
                URL url = new URL(myurl);
                HttpURLConnection hcon = (HttpURLConnection) url.openConnection();
                hcon.setRequestMethod("POST");
                hcon.setDoOutput(true);
                hcon.setDoInput(true);

                OutputStream ostream = hcon.getOutputStream();
                BufferedWriter writer = new BufferedWriter
                        (new OutputStreamWriter(ostream, "UTF-8"));
                String postdata = "";
                writer.write(postdata);
                writer.flush();
                writer.close();
                ostream.close();

                InputStream inputStream = hcon.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader
                        (inputStream, "iso-8859-1"));
//                    (new InputStreamReader(inputStream));
                result = "";
                String line = "";
                while ((line = reader.readLine()) != null) {
                    result += line;
                }
                reader.close();
                inputStream.close();
                hcon.disconnect();
                System.out.println("Result: " + result);
                SubStrConvert sc = new SubStrConvert();
                result = sc.getSubString(result);
                return result;

            } catch (ProtocolException e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
                ;
            } catch (MalformedURLException e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
                ;
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
                ;
            }
        }

        //Available Rooms list query will fire and available rooms will display.
        if(key.equals("available")) {
            String myurl = MainDomain.getDomain() + "viewAvailRooms.php";
            System.out.println("2nd: " + myurl);
            try {
                URL url = new URL(myurl);
                HttpURLConnection hcon = (HttpURLConnection) url.openConnection();
                hcon.setRequestMethod("POST");
                hcon.setDoOutput(true);
                hcon.setDoInput(true);

                OutputStream ostream = hcon.getOutputStream();
                BufferedWriter writer = new BufferedWriter
                        (new OutputStreamWriter(ostream, "UTF-8"));
                String postdata = "";
                writer.write(postdata);
                writer.flush();
                writer.close();
                ostream.close();

                InputStream inputStream = hcon.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader
                        (inputStream, "iso-8859-1"));
//                    (new InputStreamReader(inputStream));
                result = "";
                String line = "";
                while ((line = reader.readLine()) != null) {
                    result += line;
                }
                reader.close();
                inputStream.close();
                hcon.disconnect();
                System.out.println("Result: " + result);
                SubStrConvert sc = new SubStrConvert();
                result = sc.getSubString(result);
                return result;

            } catch (ProtocolException e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
                ;
            } catch (MalformedURLException e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
        }
        return null;

    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        progressDialog.dismiss();
        delegate.processFinish(o);
        System.out.println("Room Objects: "+o);
    }
}
