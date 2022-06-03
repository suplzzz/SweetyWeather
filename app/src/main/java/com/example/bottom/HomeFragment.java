package com.example.bottom;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import org.json.JSONException;

public class HomeFragment extends Fragment {

    SwipeRefreshLayout refreshLayout;
    TextView info, wear;
    ImageView image1, image2, image3, image4;

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        OpenWeather openWeather = new OpenWeather();
        refreshLayout = view.findViewById(R.id.refreshLayout);
        info = view.findViewById(R.id.info);
        image1 = view.findViewById(R.id.image1);
        image2 = view.findViewById(R.id.image2);
        image3 = view.findViewById(R.id.image3);
        image4 = view.findViewById(R.id.image4);
        wear = view.findViewById(R.id.wear);



        new Thread(() -> {
            try {
                double temperature = Double.parseDouble(openWeather.initialize());
                if (temperature > 23) {
                    requireActivity().runOnUiThread(() ->image1.setImageResource(R.drawable.cap));
                    requireActivity().runOnUiThread(() ->image2.setImageResource(R.drawable.tshirt));
                    requireActivity().runOnUiThread(() ->image3.setImageResource(R.drawable.shorts));
                    requireActivity().runOnUiThread(() ->image4.setImageResource(R.drawable.shoes));

                }

                if (temperature < 23 && temperature > 15) {
                    requireActivity().runOnUiThread(() ->image1.setImageResource(R.drawable.cap));
                    requireActivity().runOnUiThread(() ->image2.setImageResource(R.drawable.sweater));
                    requireActivity().runOnUiThread(() ->image3.setImageResource(R.drawable.jeans));
                    requireActivity().runOnUiThread(() ->image4.setImageResource(R.drawable.shoes));
                }

                if (temperature < 15 && temperature > 0) {

                }

                if (temperature < 0) {

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            String temp = null;
            try {
                temp = openWeather.initialize();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String finalTemp = temp;
            if(Double.parseDouble( finalTemp) > 0){
                String finalTemp1 = "+" + finalTemp;
                requireActivity().runOnUiThread(() -> info.setText(finalTemp1 + " °C"));
            }
            else {
                requireActivity().runOnUiThread(() -> info.setText(finalTemp + " °C"));
            }

        }).start();





        refreshLayout.setOnRefreshListener(() -> {
            new Thread(() -> {
                String temp = null;
                try {
                    temp = openWeather.initialize();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String finalTemp = temp;
                if(Double.parseDouble( finalTemp) > 0){
                    String finalTemp1 = "+" + finalTemp;
                    requireActivity().runOnUiThread(() -> info.setText(finalTemp1 + " °C"));
                }
                else {
                    requireActivity().runOnUiThread(() -> info.setText(finalTemp + " °C"));
                }
            }).start();


            refreshLayout.setRefreshing(false);
        });
        return view;
    }

}