package com.app.tarun.dc2.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.app.tarun.dc2.OnFragmentInteractionListener;
import com.app.tarun.dc2.R;


public class HomeScreenFragment extends android.support.v4.app.Fragment {

    public static String TAG = "Home";

    private OnFragmentInteractionListener mListener;

    public static HomeScreenFragment newInstance() {
        HomeScreenFragment fragment = new HomeScreenFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public HomeScreenFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_screen, container, false);

        ImageButton buyMedicineButton = (ImageButton)view.findViewById(R.id.buyMedicineButton);
        ImageButton callUsButton = (ImageButton)view.findViewById(R.id.callUsButton);

        TextView buyMedicine=(TextView)view.findViewById(R.id.BuyMedicine);
        TextView callUs=(TextView)view.findViewById(R.id.CallUs);

        //Applying custom font to the views
        Typeface typeFace=Typeface.createFromAsset(getActivity().getAssets(),"fonts/gothic.ttf");
        buyMedicine.setTypeface(typeFace);
        callUs.setTypeface(typeFace);

        //Events on Buttons
        buyMedicineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.replaceFragment(R.id.buyMedicineButton,null);
            }
        });

        callUsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:123456789"));
                startActivity(callIntent);
            }
        });


        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


}
