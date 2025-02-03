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

public class StopsAPI
{
    private static final String STOPS_URL = "https://data.cityofchicago.org/resource/8pix-ypme.json";
    private static RequestQueue queue;
    private static MainActivity mainActivity;
    private static String [] stops;

    public static void call(MainActivity mainActivity)
    {
        StopsAPI.mainActivity = mainActivity;
        queue = Volley.newRequestQueue(StopsAPI.mainActivity);

        Response.Listener <JSONArray> listener = new Response.Listener<JSONArray>()
        {
            @Override
            public void onResponse(JSONArray response)
            {
                try
                {
                    JSONArray arr = new JSONArray(response.toString());
                    int len = arr.length();
                    stops = new String[len];
                    for(int i = 0; i < len; i++)
                    {
                        JSONObject info = arr.getJSONObject(i);
                        String stop_name = info.getString("stop_name");
                        Log.d("stop_name", stop_name);

                        stops[i] = stop_name;
                    }
                    Log.d("Length", String.valueOf(stops.length));

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
                Log.d("an error occurred", error.toString());
            }
        };
        queue.add(new JsonArrayRequest(Request.Method.GET, STOPS_URL, null, listener, error));
    }

    public static String [] getStops() { return stops; }

}
