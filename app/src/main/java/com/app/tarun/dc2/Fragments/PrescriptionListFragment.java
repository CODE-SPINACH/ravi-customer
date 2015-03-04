package com.app.tarun.dc2.Fragments;


import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.app.tarun.dc2.Adpaters.PrescriptionCardAdapter;
import com.app.tarun.dc2.OnFragmentInteractionListener;
import com.app.tarun.dc2.R;

public class PrescriptionListFragment extends android.support.v4.app.Fragment{

    public static String TAG = "PrescriptionList";


    private PrescriptionCardAdapter prescriptionCardAdapter;

    OnFragmentInteractionListener mListener;

    public static CharSequence getTitle(){
        return "Prescription";
    }

    public static PrescriptionListFragment newInstance() {
        PrescriptionListFragment fragment = new PrescriptionListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public PrescriptionListFragment() {
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
        View view = inflater.inflate(R.layout.fragment_prescription_list, container, false);
        ListView prescriptionListView = (ListView)view.findViewById(R.id.prescriptionListView);

        prescriptionListView.setAdapter(prescriptionCardAdapter);

        prescriptionListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mListener.replaceFragment(R.layout.cards_prescription,(Object)position);
            }
        });

        return view;
    }

    public void setPrescriptionCardAdapter(PrescriptionCardAdapter adapter){
        prescriptionCardAdapter = adapter;
    }

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
