package com.app.tarun.dc2.Fragments;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.app.tarun.dc2.Data.PrescriptionDetails;
import com.app.tarun.dc2.OnFragmentInteractionListener;
import com.app.tarun.dc2.R;

public class PrescriptionEditFragment extends android.support.v4.app.Fragment {

    public static String TAG = "PrescriptionEdit";

    private PrescriptionDetails prescriptionDetails;

    //determines if the prescription is being edited or added for the first time
    boolean isBeingEdited = false;

    //The image of the prescription
    private Bitmap bitmap = null;

    //Its file path
    public Uri fileUri;

    private OnFragmentInteractionListener mListener;

    public static PrescriptionEditFragment newInstance() {
        PrescriptionEditFragment fragment = new PrescriptionEditFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public PrescriptionEditFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_prescription, container, false);

        final NumberPicker numberPicker = (NumberPicker)view.findViewById(R.id.numberPicker);
        ImageButton imageButton = (ImageButton)view.findViewById(R.id.prescriptionImageButton);
        ImageButton continueButton = (ImageButton)view.findViewById(R.id.prescriptionContinue);
        final EditText notesEditText = (EditText)view.findViewById(R.id.notesEditText);

        final ImageView imageView = (ImageView)view.findViewById(R.id.prescriptionImageZoom);
        final TextView textView = (TextView)view.findViewById(R.id.daysTextView);


        numberPicker.setMaxValue(30);
        numberPicker.setMinValue(0);

        //setting the previous values if the prescription details are being edited
        if(isBeingEdited){
            fileUri = prescriptionDetails.getImageUri();
            numberPicker.setValue(prescriptionDetails.getDays());
            notesEditText.setText(prescriptionDetails.getNotes());
        }

        //fetching the image file from the File Location
        try {
            bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), fileUri);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        imageButton.setImageBitmap(bitmap);


        //Events on buttons
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setVisibility(View.VISIBLE);
                imageView.setImageBitmap(bitmap);
            }
        });


        //TO DO Manage image zoom
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setVisibility(View.INVISIBLE);
            }
        });

        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                if(newVal == 0)
                    textView.setText("as specified in the prescription");
                else
                    textView.setText(newVal + " days");
            }
        });

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //To hide the virtual keyboard if open
                View view = getActivity().getCurrentFocus();
                if (view != null) {
                    InputMethodManager inputManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                }

                if(isBeingEdited){
                    prescriptionDetails.setDays(numberPicker.getValue());
                    prescriptionDetails.setNotes(notesEditText.getText().toString());

                    mListener.replaceFragment(R.id.prescriptionContinue,null);
                }
                else {
                    prescriptionDetails = (new PrescriptionDetails
                            (fileUri, notesEditText.getText().toString(), numberPicker.getValue()));
                    mListener.replaceFragment(R.id.prescriptionContinue, prescriptionDetails);
                }
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

    //Set the prescription details
    public void setPrescriptionDetails(PrescriptionDetails details){
        isBeingEdited = true;
        prescriptionDetails = details;
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
