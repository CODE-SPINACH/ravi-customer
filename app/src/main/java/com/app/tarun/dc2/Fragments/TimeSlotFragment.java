package com.app.tarun.dc2.Fragments;

import android.app.Activity;
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


public class TimeSlotFragment extends android.support.v4.app.Fragment {
    public static String TAG = "TimeSlot";
    private OnFragmentInteractionListener mListener;
    public static TimeSlotFragment newInstance() {
        TimeSlotFragment fragment = new TimeSlotFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    public TimeSlotFragment() {
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
        View view = inflater.inflate(R.layout.fragment_timeslot, container, false);

        TextView firstTimeSlotText = (TextView)view.findViewById(R.id.firstTimeSlotText);
        TextView secondTimeSlotText = (TextView)view.findViewById(R.id.secondTimeSlotText);
        TextView thirdTimeSLotText = (TextView)view.findViewById(R.id.thirdTimeSLotText);
        TextView fourthTimeSlotText = (TextView)view.findViewById(R.id.fourthTimeSlotText);

        ImageButton firstTimeSlot = (ImageButton)view.findViewById(R.id.firstTimeSlot);
        ImageButton secondTimeSlot = (ImageButton)view.findViewById(R.id.secondTimeSlot);
        ImageButton thirdTimeSLot = (ImageButton)view.findViewById(R.id.thirdTimeSLot);
        ImageButton fourthTimeSlot = (ImageButton)view.findViewById(R.id.fourthTimeSlot);


        //Assign custom fonts
        Typeface typeFace=Typeface.createFromAsset(getActivity().getAssets(),"fonts/gothic.ttf");
        firstTimeSlotText.setTypeface(typeFace);
        secondTimeSlotText.setTypeface(typeFace);
        thirdTimeSLotText.setTypeface(typeFace);
        fourthTimeSlotText.setTypeface(typeFace);

        // Inflate the layout for this fragment

        //All Events Start Here
        firstTimeSlot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.replaceFragment(R.id.firstTimeSlot,null);
            }
        });
        secondTimeSlot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.replaceFragment(R.id.secondTimeSlot,null);
            }
        });
        thirdTimeSLot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.replaceFragment(R.id.thirdTimeSLot,null);
            }
        });
        fourthTimeSlot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.replaceFragment(R.id.fourthTimeSlot,null);
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
