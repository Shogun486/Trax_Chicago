package com.example.trackcta;

import android.util.Log;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;

/*
    TO-IMPLEMENT
*/

public class LocationsAPI
{
    private static final String API_KEY = "a64c36c7f3174612a48134c15b3568a5",
            PRE_KEY_URL = "http://lapi.transitchicago.com/api/1.0/ttpositions.aspx?key=",
            POST_KEY_URL = "&rt=red&outputType=JSON";
    private static String URL = PRE_KEY_URL + API_KEY + POST_KEY_URL;
    private static RequestQueue queue;
    private static MainActivity mainActivity;


    public static void call(MainActivity mainActivity)
    {
        LocationsAPI.mainActivity = mainActivity;
        queue = Volley.newRequestQueue(LocationsAPI.mainActivity);

        Response.Listener <JSONObject> listener = new Response.Listener<JSONObject>()
        {
            @Override
            public void onResponse(JSONObject response)
            {
                try
                {
                    JSONObject initialQuery = new JSONObject(response.toString());
                    JSONObject first = initialQuery.getJSONObject("ctatt");
                    Log.d("tmst", first.getString("tmst"));
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
        Log.d("URL", URL);
        queue.add(new JsonObjectRequest(Request.Method.GET, URL, null, listener, error));
    }
}