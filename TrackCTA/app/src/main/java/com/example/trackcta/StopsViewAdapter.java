package com.example.trackcta;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.ArrayList;

/*

    Adapter for ListView in StopsActivity

 */

public class StopsViewAdapter extends ArrayAdapter <StopDisplayInfo>
{

    public StopsViewAdapter(@NonNull Context context, ArrayList<StopDisplayInfo> arrayList)
    {
        super(context, 0, arrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View currentItemView = convertView;
        if (currentItemView == null)
            currentItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        StopDisplayInfo currentNumberPosition = getItem(position);
        assert currentNumberPosition != null;

        TextView textView1 = currentItemView.findViewById(R.id.textView1List);
        String text = currentNumberPosition.getStopName();
        text = StopsActivity.getProperStopDisplay(text);
        textView1.setText(text);

        int colorAmount = StopsAPI.stopIDtoColors.get(currentNumberPosition.getStopID()).size();
        for(int i = 0; i < colorAmount; i++)
        {
            if(i == 0)
            {
                TextView textViewColor1List = currentItemView.findViewById(R.id.textViewColor1List);
                textViewColor1List.setBackgroundColor(StationsAdapter.getColorInt(StopsAPI.stopIDtoColors.get(currentNumberPosition.getStopID()).get(0)));
                textViewColor1List.setText("                         ");
            }
            if(i == 1)
            {
                TextView textViewColor2List = currentItemView.findViewById(R.id.textViewColor2List);
                textViewColor2List.setBackgroundColor(StationsAdapter.getColorInt(StopsAPI.stopIDtoColors.get(currentNumberPosition.getStopID()).get(1)));
                textViewColor2List.setText("                         ");
                textViewColor2List.setVisibility(View.VISIBLE);
            }
            if(i == 2)
            {
                TextView textViewColor3List = currentItemView.findViewById(R.id.textViewColor3List);
                textViewColor3List.setBackgroundColor(StationsAdapter.getColorInt(StopsAPI.stopIDtoColors.get(currentNumberPosition.getStopID()).get(2)));
                textViewColor3List.setText("                         ");
                textViewColor3List.setVisibility(View.VISIBLE);
            }
            if(i == 3)
            {
                TextView textViewColor4List = currentItemView.findViewById(R.id.textViewColor4List);
                textViewColor4List.setBackgroundColor(StationsAdapter.getColorInt(StopsAPI.stopIDtoColors.get(currentNumberPosition.getStopID()).get(3)));
                textViewColor4List.setText("                         ");
                textViewColor4List.setVisibility(View.VISIBLE);
            }
        }

        return currentItemView;
    }

}
