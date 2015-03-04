package com.app.tarun.dc2.Fragments;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.app.tarun.dc2.Adpaters.MedicineCardAdapter;
import com.app.tarun.dc2.OnFragmentInteractionListener;
import com.app.tarun.dc2.R;

public class MedicineListFragment extends android.support.v4.app.Fragment {

    public static String TAG = "MedicineList";


    private MedicineCardAdapter medicineCardAdapter;
    OnFragmentInteractionListener mListener;

    public static CharSequence getTitle(){
        return "Medicine";
    }

    public static MedicineListFragment newInstance() {
        MedicineListFragment fragment = new MedicineListFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    public MedicineListFragment() {
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
        View view = inflater.inflate(R.layout.fragment_medicine_list, container, false);

        ListView medicineListView = (ListView)view.findViewById(R.id.medicineListView);
        medicineListView.setAdapter(medicineCardAdapter);

        medicineListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        return view;
    }

    public void setMedicineCardAdapter(MedicineCardAdapter adapter) {
        medicineCardAdapter = adapter;
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
