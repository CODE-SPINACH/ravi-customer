package com.app.tarun.dc2.Fragments;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.app.tarun.dc2.Adpaters.MedicineCardAdapter;
import com.app.tarun.dc2.Data.MedicineDetails;
import com.app.tarun.dc2.OnFragmentInteractionListener;
import com.app.tarun.dc2.R;

public class MedicineEditFragment extends android.support.v4.app.Fragment {

    public static String TAG = "MedicineEdit";
    private OnFragmentInteractionListener mListener;

    private MedicineCardAdapter medicineCardAdapter;

    public static MedicineEditFragment newInstance() {
        MedicineEditFragment fragment = new MedicineEditFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public MedicineEditFragment() {
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
        View view = inflater.inflate(R.layout.fragment_medicine_edit, container, false);

        ImageButton addButton = (ImageButton)view.findViewById(R.id.medicineEditAddButton);
        ImageButton continueButton = (ImageButton)view.findViewById(R.id.medicineEditContinueButton);

        final EditText editText = (EditText)view.findViewById(R.id.medicineEditText);
        final NumberPicker numberPicker = (NumberPicker)view.findViewById(R.id.medicineEditNumberPicker);

        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(100);

        MedicineListFragment medicineListFragment = MedicineListFragment.newInstance();

        medicineListFragment.setMedicineCardAdapter(medicineCardAdapter);

        getChildFragmentManager().beginTransaction()
                .replace(R.id.medicineEditContainer, medicineListFragment, MedicineListFragment.TAG).commitAllowingStateLoss();

        //Events of views
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //To hide the virtual keyboard if open
                View view = getActivity().getCurrentFocus();
                if (view != null) {
                    InputMethodManager inputManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                }

                if(editText.getText().toString().matches("")){
                    Toast.makeText(getActivity(),"Please enter a medicine name",Toast.LENGTH_SHORT).show();
                    return;
                }

                medicineCardAdapter.add(new MedicineDetails
                        (editText.getText().toString(), MedicineDetails.MedicineTypes.Bottles,numberPicker.getValue()));
                numberPicker.setValue(1);
                editText.getText().clear();
            }
        });

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.replaceFragment(R.id.medicineEditContinueButton,null);
            }
        });

        TextView medicineEditText = (TextView)view.findViewById(R.id.medicineEditText);
        //Assign custom fonts
        Typeface typeFace=Typeface.createFromAsset(getActivity().getAssets(),"fonts/gothic.ttf");
        medicineEditText.setTypeface(typeFace);

        return view;
    }

    public void setMedicineCardAdapter(MedicineCardAdapter adapter){
        medicineCardAdapter = adapter;
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
