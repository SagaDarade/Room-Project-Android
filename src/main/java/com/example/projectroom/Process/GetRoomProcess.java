package com.example.projectroom.Process;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GetRoomProcess {
    List<RoomPOJO> plist;

    public GetRoomProcess(String json) {
        plist = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(json);
            System.out.println("Process: "+json);

            for (int i = 0; i < jsonArray.length(); i++){
                JSONObject jsonObject =jsonArray.getJSONObject(i);
                String s1 =jsonObject.getString("roomNo");
                String s2 =jsonObject.getString("deposite");
                String s3 =jsonObject.getString("maintenance");
                String s4 =jsonObject.getString("rent");
                int flg =jsonObject.getInt("flag");
                RoomPOJO pojo = new RoomPOJO(s1,s2,s3,s4,flg);
                plist.add(pojo);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public List<RoomPOJO> getPlist(){
        System.out.println("Size: "+plist.size());
        for(RoomPOJO p:plist)
            System.out.println(p);
        return plist;
    }
}
