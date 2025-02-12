package com.example.trackcta;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;

import androidx.annotation.Nullable;

import java.util.ArrayList;

//harlem & western stations have stops dup
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
        String text = currentNumberPosition.getNumberInDigit();
        StringBuilder sb = new StringBuilder(text);
        String bound = "", park="";
        for(int i = 0; i < sb.length(); i++)
        {
            park += sb.charAt(i);
            Log.d("park", park);
            if(park.contains("Pk"))
            {
                sb.replace(i-2,i+1," Park");
                park = "";
            }

            bound += sb.charAt(i);
            if(bound.contains("bound")) {
                sb.delete(i - 5, i + 1);
                sb.deleteCharAt(sb.length() - 1);
                break;
            }

            if(i+1 != sb.length())
            {
                if (sb.charAt(i + 1) == '(')
                {

                    sb.setCharAt(i, 'X');
                    sb.deleteCharAt(i + 1);

                }
                else if(sb.charAt(i + 1) == ')')
                {
                    sb.deleteCharAt(i+1);
                }
            }


        }
        /*
        for(int i = 0; i < sb.length(); i++)
        {

            if(i + 3 < sb.length() && sb.substring(i, i + 3).contains("  "))
                break;
            text += sb.charAt(i);
        }*/

        text = "";
        String j = sb.toString();
        String [] arr = j.split("X");
        for(int c  = 0; c < arr.length; c++)
        {
            if(c == arr.length - 1 && arr.length != 2)
                text += "\nTo ";
            if(c != 0)
                text += arr[c];


        }
        //text = sb.toString();
        textView1.setText(text);



        //textView1.setBackgroundColor(Color.rgb(84,84,84));
        //textView1.setBackgroundColor(StationsAdapter.getColorInt(StopsAPI.stopToColors.get(currentNumberPosition.getNumberInDigit()).get(0)));

        int colorAmount = StopsAPI.stopIDtoColors.get(currentNumberPosition.getNumbersInText()).size();
       //og int colorAmount = (StopsAPI.stopToColors.get(currentNumberPosition.getNumbersInText()).size());
        for(int i = 0; i < colorAmount; i++)
        {
            if(i == 0)
            {
                TextView textViewColor1List = currentItemView.findViewById(R.id.textViewColor1List);
                textViewColor1List.setBackgroundColor(StationsAdapter.getColorInt(StopsAPI.stopIDtoColors.get(currentNumberPosition.getNumbersInText()).get(0)));
                textViewColor1List.setText("                         ");


            }
            if(i == 1)
            {
                TextView textViewColor2List = currentItemView.findViewById(R.id.textViewColor2List);
                textViewColor2List.setBackgroundColor(StationsAdapter.getColorInt(StopsAPI.stopIDtoColors.get(currentNumberPosition.getNumbersInText()).get(1)));
                textViewColor2List.setText("                         ");
                textViewColor2List.setVisibility(View.VISIBLE);

            }

            if(i == 2)
            {
                TextView textViewColor3List = currentItemView.findViewById(R.id.textViewColor3List);
                textViewColor3List.setBackgroundColor(StationsAdapter.getColorInt(StopsAPI.stopIDtoColors.get(currentNumberPosition.getNumbersInText()).get(2)));
                textViewColor3List.setText("                         ");
                textViewColor3List.setVisibility(View.VISIBLE);

            }
            if(i == 3)
            {
                TextView textViewColor4List = currentItemView.findViewById(R.id.textViewColor4List);
                textViewColor4List.setBackgroundColor(StationsAdapter.getColorInt(StopsAPI.stopIDtoColors.get(currentNumberPosition.getNumbersInText()).get(3)));
                textViewColor4List.setText("                         ");
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
