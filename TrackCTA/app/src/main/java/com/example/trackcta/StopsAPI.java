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
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;

/*
    FETCHES STATION DATA: stop names, 'L' color, lat/long, etc.
*/

public class StopsAPI
{
    private final String STOPS_URL = "https://data.cityofchicago.org/resource/8pix-ypme.json";
    private RequestQueue queue;
    private StationsActivity mainActivity;
    protected static Map <String, Set<String>> lineColors = new HashMap<>();
    //private static Map <String, List<String>> stationInfo = new HashMap<>();
    protected static Map<String, ArrayList<String>> stationToStopIDs = new HashMap<>();
    protected static Map<String, List<String>> stopIDtoColors = new HashMap<>();
    protected static Map<String, String> stopIDtoName = new HashMap<>();




    public void call(StationsActivity mainActivity)
    {
        //stationInfo.clear();
        //stationToStops.clear();

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
                        String descName = info.getString("station_descriptive_name");

                        //stopsColors
                        if(!stationToStopIDs.containsKey(stationName)) {
                            stationToStopIDs.put(stationName, new ArrayList<>());

                            mainActivity.al.add(stationName);

                        }

                        stationToStopIDs.get(stationName).add(stopID);
                        //stopIDs.add(stopID);


                        if(!stopIDtoColors.containsKey(stopID))
                        {
                            stopIDtoName.put(stopID, stopName);
                            stopIDtoColors.put(stopID, new ArrayList<>());

                            if(stopName.equals("Sheridan (Loop-bound)") || stopName.equals("Sheridan (Howard-Linden-bound)"))
                                stopIDtoColors.get(stopID).add("Red");

                            if(info.getBoolean("red") == true)
                            {
                                stopIDtoColors.get(stopID).add("Red");
                            }
                            if(info.getBoolean("blue") == true)
                            {
                                stopIDtoColors.get(stopID).add("Blue");


                            }
                            if(info.getBoolean("g") == true)
                            {
                                stopIDtoColors.get(stopID).add("Green");


                            }
                            if(info.getBoolean("brn") == true)
                            {
                                stopIDtoColors.get(stopID).add("Brown");


                            }
                            if(info.getBoolean("p") == true || info.getBoolean("pexp") == true)
                            {
                                stopIDtoColors.get(stopID).add("Purple");


                            }
                            if(info.getBoolean("y") == true)
                            {
                                stopIDtoColors.get(stopID).add("Yellow");


                            }
                            if(info.getBoolean("pnk") == true)
                            {
                                stopIDtoColors.get(stopID).add("Pink");


                            }
                            if(info.getBoolean("o") == true)
                            {
                                stopIDtoColors.get(stopID).add("Orange");
                            }
                        }



                        //stopColors

                        if(!lineColors.containsKey(stationName))
                            lineColors.put(stationName, new HashSet<>());

                        String color = "unknown";
                        if(info.getBoolean("red") == true)
                        {
                            color = "Red";
                            lineColors.get(stationName).add(color);
                        }
                        if(info.getBoolean("blue") == true)
                        {
                            color = "Blue";
                            lineColors.get(stationName).add(color);

                        }
                        if(info.getBoolean("g") == true)
                        {
                            color = "Green";
                            lineColors.get(stationName).add(color);

                        }
                        if(info.getBoolean("brn") == true)
                        {
                            color = "Brown";
                            lineColors.get(stationName).add(color);

                        }
                        if(info.getBoolean("p") == true || info.getBoolean("pexp") == true)
                        {
                            color = "Purple";
                            lineColors.get(stationName).add(color);

                        }
                        if(info.getBoolean("y") == true)
                        {
                            color = "Yellow";
                            lineColors.get(stationName).add(color);

                        }
                        if(info.getBoolean("pnk") == true)
                        {
                            color = "Pink";
                            lineColors.get(stationName).add(color);

                        }
                        if(info.getBoolean("o") == true)
                        {
                            color = "Orange";
                            lineColors.get(stationName).add(color);
                        }

/*
                        if(!stationInfo.containsKey(stationName))
                        {
                            stationInfo.put(stationName, new ArrayList<>());
                        }
                        stationInfo.get(stationName).add(stopName);
                        stationInfo.get(stationName).add(stopID);

 */
                    }

                    Log.d("newData", stationToStopIDs.toString());
                    Log.d("newData", stopIDtoColors.toString());

                    StationsAdapter sa = new StationsAdapter(mainActivity, mainActivity.al);
                    mainActivity.recyclerViewMain.setAdapter(sa);
                    sa.notifyDataSetChanged();

                    Log.d("URL", STOPS_URL);
                    //Log.d("stationInfo", stationInfo.toString());
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


    public static List <String> getInfo(String stationName) { return stationToStopIDs.get(stationName); }
    public static ArrayList <String> getStops(String stationName) { return stationToStopIDs.get(stationName); }

}
