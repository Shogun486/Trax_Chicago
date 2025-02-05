package com.example.trackcta;

import android.util.Log;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;


/*
    FETCHES STATION DATA: stop names, 'L' color, lat/long, etc.
*/
public class StopsAPI
{
    private final String STOPS_URL = "https://data.cityofchicago.org/resource/8pix-ypme.json";
    private RequestQueue queue;
    private MainActivity mainActivity;

    // To-implement accessor methods
    public static Map <String, String> line = new HashMap<>();
    public static Map <String, List<String>> stationInfo = new HashMap<>();



    public void call(MainActivity mainActivity)
    {
        stationInfo.clear();
        this.mainActivity = mainActivity;
        queue = Volley.newRequestQueue(this.mainActivity);

        Response.Listener <JSONArray> listener = new Response.Listener<JSONArray>()
        {
            @Override
            public void onResponse(JSONArray response)
            {
                try
                {
                    JSONArray arr = new JSONArray(response.toString());
                    int len = arr.length();
                    for(int i = 0; i < len; i++)
                    {
                        JSONObject info = arr.getJSONObject(i);
                        String stationName = info.getString("station_name");
                        String stopName = info.getString("stop_name");
                        String stopID = info.getString("stop_id");

                        if(!stationInfo.containsKey(stationName))
                            stationInfo.put(stationName, new ArrayList<>());
                        stationInfo.get(stationName).add(stopName);
                        stationInfo.get(stationName).add(stopID);

                        String color = "unknown";
                        if(!line.containsKey(stationName))
                        {
                            if(info.getBoolean("red") == true)
                            {
                                color = "red";
                            }
                            else if(info.getBoolean("blue") == true)
                            {
                                color = "blue";
                            }
                            else if(info.getBoolean("g") == true)
                            {
                                color = "green";
                            }
                            else if(info.getBoolean("brn") == true)
                            {
                                color = "brown";
                            }
                            else if(info.getBoolean("p") == true)
                            {
                                color = "purple";
                            }
                            else if(info.getBoolean("y") == true)
                            {
                                color = "yellow";
                            }
                            else if(info.getBoolean("pnk") == true)
                            {
                                color = "pink";
                            }
                            else if(info.getBoolean("o") == true)
                            {
                                color = "orange";
                            }
                            line.put(stationName, color);
                        }
                    }
                    MainActivity.updateStops();
                    Log.d("STP", STOPS_URL);
                    Log.d("stationInfo", stationInfo.toString());
                }
                catch (JSONException e)
                {
                    throw new RuntimeException(e);
                }
            }
        };
        Response.ErrorListener error = new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Log.d("ERROR:", error.toString());
            }
        };
        queue.add(new JsonArrayRequest(Request.Method.GET, STOPS_URL, null, listener, error));
    }
}
