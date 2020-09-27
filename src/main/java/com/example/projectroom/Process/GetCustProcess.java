package com.example.projectroom.Process;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GetCustProcess {
    List<CustPOJO> clist;

    public GetCustProcess(String json) {
        System.out.println("Before Cust Process: "+json);
        clist = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(json);
            System.out.println("All Cust Process: "+json);

            for (int i = 0; i < jsonArray.length(); i++){
                JSONObject jsonObject =jsonArray.getJSONObject(i);
                int cid =jsonObject.getInt("cid");
//                String cid =jsonObject.getString("cid");
                String cname =jsonObject.getString("cName");
                String caddr =jsonObject.getString("cAddress");
                String cBdate =jsonObject.getString("cBirthDate");
                String cont1 =jsonObject.getString("contact1");
                String cont2 =jsonObject.getString("contact2");
                String cmail =jsonObject.getString("cEmail");
                String cAdhar =jsonObject.getString("cAdhaar");
                String cPAN =jsonObject.getString("cPAN");
                String cJoining =jsonObject.getString("cJoining");
                String cLeaving =jsonObject.getString("cLeaving");
                int meter =jsonObject.getInt("mtrReading");
//                String meter =jsonObject.getString("mtrReading");
                String cRno =jsonObject.getString("roomNo");

                CustPOJO pojo = new CustPOJO(cid,cname,caddr,cBdate,cont1,cont2,cmail,cAdhar,cPAN,cJoining,cLeaving,meter,cRno);
                clist.add(pojo);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public List<CustPOJO> getclist(){
        System.out.println("Size: "+clist.size());
        for(CustPOJO c:clist)
            System.out.println(c);
        return clist;
    }
}