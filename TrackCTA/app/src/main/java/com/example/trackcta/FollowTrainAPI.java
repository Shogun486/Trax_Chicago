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

public class FollowTrainAPI
{

    private static final String API_KEY = "a64c36c7f3174612a48134c15b3568a5",
            PRE_KEY_URL = "http://lapi.transitchicago.com/api/1.0/ttfollow.aspx?key=",
            POST_KEY_URL1 = "&runnumber=",
            POST_KEY_URL2 = "&outputType=JSON";
    private static String URL;
    private static RequestQueue queue;
    private static VisualizerActivity visualizerActivity;


    public static void call(VisualizerActivity visualizerActivity, int run)
    {
        FollowTrainAPI.visualizerActivity = visualizerActivity;
        queue = Volley.newRequestQueue(FollowTrainAPI.visualizerActivity);

        Response.Listener <JSONObject> listener = new Response.Listener<JSONObject>()
        {
            @Override
            public void onResponse(JSONObject response)
            {
                visualizerActivity.alv.clear();

                try
                {
                    JSONObject initialQuery = new JSONObject(response.toString());
                    JSONObject ctatt = (JSONObject) initialQuery.get("ctatt");
                    JSONArray eta = (JSONArray) ctatt.getJSONArray("eta");
                    int len = eta.length();
                    String stationName = "", arrivalTime = "";
                    for(int i = 0; i < len; i++) {
                        JSONObject etaInfo = (JSONObject) eta.get(i);
                        stationName = etaInfo.getString("staNm");
                        arrivalTime = etaInfo.getString("arrT");

                        VisualizerInfo info = new VisualizerInfo(stationName, arrivalTime);
                        visualizerActivity.alv.add(info);
                    }
                    VisualizerAdapter va = new VisualizerAdapter(visualizerActivity, visualizerActivity.alv);
                    visualizerActivity.recyclerViewVisualizer.setAdapter(va);
                    va.notifyDataSetChanged();

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
        URL = PRE_KEY_URL + API_KEY + POST_KEY_URL1 + run + POST_KEY_URL2;
        Log.d("URL", URL);
        queue.add(new JsonObjectRequest(Request.Method.GET, URL, null, listener, error));

    }


}
