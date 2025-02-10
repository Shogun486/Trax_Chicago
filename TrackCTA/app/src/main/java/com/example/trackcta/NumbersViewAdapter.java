package com.example.trackcta;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;

import androidx.annotation.Nullable;

import com.example.trackcta.R;
import com.example.trackcta.NumbersView;

import java.util.ArrayList;

public class NumbersViewAdapter extends ArrayAdapter<NumbersView>
{

    // invoke the suitable constructor of the ArrayAdapter class
    public NumbersViewAdapter(@NonNull Context context, ArrayList<NumbersView> arrayList) {

        // pass the context and arrayList for the super
        // constructor of the ArrayAdapter class
        super(context, 0, arrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // convertView which is recyclable view
        View currentItemView = convertView;

        // of the recyclable view is null then inflate the custom layout for the same
        if (currentItemView == null) {
            currentItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        // get the position of the view from the ArrayAdapter
        NumbersView currentNumberPosition = getItem(position);

        // then according to the position of the view assign the desired image for the same
        //ImageView numbersImage = currentItemView.findViewById(R.id.imageViewList);
        assert currentNumberPosition != null;
        //numbersImage.setImageResource(currentNumberPosition.getNumbersImageId());

        // then according to the position of the view assign the desired TextView 1 for the same
        TextView textView1 = currentItemView.findViewById(R.id.textView1List);
        textView1.setText(StopsActivity.parseDestinationBound(currentNumberPosition.getNumberInDigit()));
        //textView1.setBackgroundColor(Color.rgb(84,84,84));
        //textView1.setBackgroundColor(StationsAdapter.getColorInt(StopsAPI.stopToColors.get(currentNumberPosition.getNumberInDigit()).get(0)));

        int colorAmount = (StopsAPI.stopToColors.get(currentNumberPosition.getNumberInDigit()).size());
        for(int i = 0; i < colorAmount; i++)
        {
            if(i == 0)
            {
                TextView textViewColor1List = currentItemView.findViewById(R.id.textViewColor1List);
                textViewColor1List.setBackgroundColor(StationsAdapter.getColorInt(StopsAPI.stopToColors.get(currentNumberPosition.getNumberInDigit()).get(0)));
                textViewColor1List.setText(StopsAPI.stopToColors.get(currentNumberPosition.getNumberInDigit()).get(0));
                textViewColor1List.setVisibility(View.VISIBLE);
            }
            if(i == 1)
            {
                TextView textViewColor2List = currentItemView.findViewById(R.id.textViewColor2List);
                textViewColor2List.setBackgroundColor(StationsAdapter.getColorInt(StopsAPI.stopToColors.get(currentNumberPosition.getNumberInDigit()).get(1)));
                textViewColor2List.setText(StopsAPI.stopToColors.get(currentNumberPosition.getNumberInDigit()).get(1));
                textViewColor2List.setVisibility(View.VISIBLE);

            }
            if(i == 2)
            {
                TextView textViewColor3List = currentItemView.findViewById(R.id.textViewColor3List);
                textViewColor3List.setBackgroundColor(StationsAdapter.getColorInt(StopsAPI.stopToColors.get(currentNumberPosition.getNumberInDigit()).get(2)));
                textViewColor3List.setText(StopsAPI.stopToColors.get(currentNumberPosition.getNumberInDigit()).get(2));
                textViewColor3List.setVisibility(View.VISIBLE);

            }
            if(i == 3)
            {
                TextView textViewColor4List = currentItemView.findViewById(R.id.textViewColor4List);
                textViewColor4List.setBackgroundColor(StationsAdapter.getColorInt(StopsAPI.stopToColors.get(currentNumberPosition.getNumberInDigit()).get(3)));
                textViewColor4List.setText(StopsAPI.stopToColors.get(currentNumberPosition.getNumberInDigit()).get(3));
                textViewColor4List.setVisibility(View.VISIBLE);

            }
        }


        //Log.d("backColor",StopsAPI.stopToColors.get(currentNumberPosition.getNumberInDigit()).toString());
        // then according to the position of the view assign the desired TextView 2 for the same
        //TextView textView2 = currentItemView.findViewById(R.id.textView2List);
        //textView2.setText(currentNumberPosition.getNumbersInText());

        // then return the recyclable view
        return currentItemView;
    }

}
