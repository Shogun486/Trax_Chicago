package com.example.trackcta;

import android.util.Log;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/*
    Fetches train arrival times for a unique stop_id
*/

public class ArrivalsAPI
{
    private static final String API_KEY = "a64c36c7f3174612a48134c15b3568a5",
            PRE_KEY_URL = "http://lapi.transitchicago.com/api/1.0/ttarrivals.aspx?key=",
            POST_KEY_URL1 = "&stpid=",
            POST_KEY_URL2 = "&outputType=JSON";
    private static String URL;
    private static RequestQueue queue;
    public static String name = "DEFAULT", destination = "", serviceDesc = "";
    private static ArrivalsActivity arrivalsActivity;



    public static void call(ArrivalsActivity arrivalsActivity, int id)
    {
        ArrivalsAPI.arrivalsActivity = arrivalsActivity;
        queue = Volley.newRequestQueue(ArrivalsAPI.arrivalsActivity);

        Response.Listener <JSONObject> listener = new Response.Listener<JSONObject>()
        {
            @Override
            public void onResponse(JSONObject response)
            {
                arrivalsActivity.alt.clear();
                try
                {

                    JSONObject initialQuery = new JSONObject(response.toString());
                    JSONObject ctatt = (JSONObject) initialQuery.getJSONObject("ctatt");
                    JSONArray eta = (JSONArray) ctatt.getJSONArray("eta");
                    JSONObject etaInfo = (JSONObject) eta.get(0);
                    Log.d("etaInfo", etaInfo.toString());
                    name = etaInfo.getString("staNm");
                    destination = etaInfo.getString("destNm");
                    serviceDesc = etaInfo.getString("stpDe");
                    ArrivalsActivity.updateName(); // Logging

                    ArrivalsInfo info = new ArrivalsInfo(destination, serviceDesc);
                    arrivalsActivity.alt.add(info);
                    ArrivalsAdapter ap = new ArrivalsAdapter(arrivalsActivity, arrivalsActivity.alt);
                    arrivalsActivity.recyclerView.setAdapter(ap);
                    ap.notifyDataSetChanged();

                } catch (JSONException e)
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
                Log.d("ERROR", error.toString());
            }
        };
        URL = PRE_KEY_URL + API_KEY + POST_KEY_URL1 + id + POST_KEY_URL2;
        Log.d("ARV", URL);
        queue.add(new JsonObjectRequest(Request.Method.GET, URL, null, listener, error));
    }
}