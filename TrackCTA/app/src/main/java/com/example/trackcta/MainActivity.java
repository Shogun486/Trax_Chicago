package com.example.trackcta;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

public class MainActivity extends AppCompatActivity
{
    private EditText userStation, output;
    private Button stationChoiceBtn;
    private ListView listView;
    private String [] stops = {"red", "blue"}; // testing sample data
    private ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // testing output based on user input
        userStation = findViewById(R.id.stationChoice);
        userStation.setOnClickListener((view) -> { userStation.setText(""); });
        output = findViewById(R.id.output);
        stationChoiceBtn = findViewById(R.id.stationChoiceBtn);
        stationChoiceBtn.setOnClickListener((view) ->
        {
            String s = userStation.getText().toString();
            output.setText(s);
        });

        // testing user selection from menu
        listView = findViewById(R.id.listview);
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, stops)
        {
            @Override
            public View getView(int position, View convertView, ViewGroup parent)
            {
                TextView textView = (TextView) super.getView(position, convertView, parent);
                if(textView.getText().toString().equals("red"))
                {
                    textView.setBackgroundColor(getResources().getColor(R.color.black));
                }
                else
                {
                    textView.setBackgroundColor(getResources().getColor(R.color.purple_200));
                }
                textView.setTextColor(getResources().getColor(R.color.teal_200));
                return textView;
            }
        };
        listView.setAdapter(arrayAdapter);

        StopsAPI.call(this);
        LocationsAPI.call(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Search stations");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener()
        {
            @Override
            public boolean onQueryTextSubmit(String query)
            {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText)
            {
                arrayAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}
