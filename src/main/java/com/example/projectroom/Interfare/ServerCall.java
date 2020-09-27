package com.example.projectroom.Interfare;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import com.example.projectroom.Domain.MainDomain;
import com.example.projectroom.Room.RoomUpdate;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

public class ServerCall extends AsyncTask {
    ProgressDialog pd;
    AlertDialog alert;
    Context ctx;

    public ServerCall(Context ct) {
        ctx = ct;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd = new ProgressDialog(ctx);
        pd.setTitle("Running Process");
        pd.setMessage("Please Wait...");
        pd.show();
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        String r = (String)o;
        pd.dismiss();
        alert = new AlertDialog.Builder(ctx).create();
        alert.setTitle("- Task Result -");
        alert.setMessage(r);
        alert.show();
//        Toast.makeText(ctx,r,Toast.LENGTH_SHORT).show();
    }

    @Override
    protected Object doInBackground(Object[] obj) {

        String type = (String)obj[0];

        if(type.equals("addRoom")){
            try
            {   String Rno = (String)obj[1];
                String Deposit = (String)obj[2];
                String Maintenance = (String)obj[3];
                String Rent = (String)obj[4];

                String url = MainDomain.getDomain()+"addRoom.php";

                URL addURL = new URL(url);
                HttpURLConnection con = (HttpURLConnection) addURL.openConnection();
                con.setRequestMethod("POST");
                con.setDoInput(true);
                con.setDoOutput(true);

                OutputStream outputStream = con.getOutputStream();
                BufferedWriter bufWri = new BufferedWriter(new OutputStreamWriter
                        (outputStream,"UTF-8"));

                String post_data =
        URLEncoder.encode("rno","UTF-8")+"="+URLEncoder.encode(Rno,"UTF-8")+"&"+
        URLEncoder.encode("RoomDep","UTF-8")+"="+URLEncoder.encode(Deposit,"UTF-8")+"&"+
        URLEncoder.encode("RoomMaintain","UTF-8")+"="+URLEncoder.encode(Maintenance,"UTF-8")+"&"+
        URLEncoder.encode("RoomRent","UTF-8")+"="+URLEncoder.encode(Rent,"UTF-8");

                bufWri.write(post_data);
                bufWri.flush();
                bufWri.close();
                outputStream.close();

                InputStream instream = con.getInputStream();
                BufferedReader bufRead = new BufferedReader(new InputStreamReader
                        (instream,"iso-8859-1"));

                String result="", line="";
                while((line = bufRead.readLine()) != null) {
                    result += line;
                }
                bufRead.close();
                instream.close();
                con.disconnect();
//                if(result.startsWith("ï»¿ï»¿")) {
//                    result = result.substring(6);
//                }
                SubStrConvert scon = new SubStrConvert();
                result = scon.getSubString(result);
//
                return result;
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        if(type.equals("update")) {
            try
            {
                String Deposit = (String)obj[1];
                String Maintenance = (String)obj[2];
                String Rent = (String)obj[3];
                String Rno = (String)obj[4];

                String url = MainDomain.getDomain()+"updtRoom.php";

                URL addURL = new URL(url);
                HttpURLConnection con = (HttpURLConnection) addURL.openConnection();
                con.setRequestMethod("POST");
                con.setDoInput(true);
                con.setDoOutput(true);

                OutputStream outputStream = con.getOutputStream();
                BufferedWriter bufWri = new BufferedWriter(new OutputStreamWriter
                        (outputStream,"UTF-8"));

                String post_data =
                        URLEncoder.encode("RoomDep","UTF-8")+"="+
                        URLEncoder.encode(Deposit,"UTF-8")+"&"+
                        URLEncoder.encode("RoomMaintain","UTF-8")+"="+
                        URLEncoder.encode(Maintenance,"UTF-8")+"&"+
                        URLEncoder.encode("RoomRent","UTF-8")+"="+
                        URLEncoder.encode(Rent,"UTF-8")+"&"+
                        URLEncoder.encode("rno","UTF-8")+"="+
                        URLEncoder.encode(Rno,"UTF-8");

                bufWri.write(post_data);
                bufWri.flush();
                bufWri.close();
                outputStream.close();

                InputStream instream = con.getInputStream();
                BufferedReader bufRead = new BufferedReader(new InputStreamReader
                        (instream,"iso-8859-1"));

                String result="", line="";
                while((line = bufRead.readLine()) != null)
                {
                    result += line;
                }
                bufRead.close();
                instream.close();
                con.disconnect();
                if(result.startsWith("ï»¿ï»¿")) {
                    result = result.substring(6);
                }
                return result;
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        if(type.equals("delete")){
            try
            {
                String Rno = (String)obj[1];
                String url = MainDomain.getDomain()+"delRoom.php";

                URL addURL = new URL(url);
                HttpURLConnection con = (HttpURLConnection) addURL.openConnection();
                con.setRequestMethod("POST");
                con.setDoInput(true);
                con.setDoOutput(true);

                OutputStream outputStream = con.getOutputStream();
                BufferedWriter bufWri = new BufferedWriter(new OutputStreamWriter
                        (outputStream,"UTF-8"));

                String post_data = URLEncoder.encode("rno","UTF-8")+"="+
                                URLEncoder.encode(Rno,"UTF-8");
                bufWri.write(post_data);
                bufWri.flush();
                bufWri.close();
                outputStream.close();

                InputStream instream = con.getInputStream();
                BufferedReader bufRead = new BufferedReader(new InputStreamReader
                        (instream,"iso-8859-1"));

                String result="", line="";
                while((line = bufRead.readLine()) != null)
                {
                    result += line;
                }
                bufRead.close();
                instream.close();
                con.disconnect();
                if(result.startsWith("ï»¿ï»¿")) {
                    result = result.substring(6);
                }
                return result;
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;

        }
        return null;
    }
}
