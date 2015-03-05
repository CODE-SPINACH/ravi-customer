package com.app.tarun.dc2.Fragments;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import com.app.tarun.dc2.OnFragmentInteractionListener;
import com.app.tarun.dc2.R;

public class AddressFragment extends android.support.v4.app.Fragment {
    private OnFragmentInteractionListener mListener;

    public static String TAG = "Address";

    public static AddressFragment newInstance() {
        AddressFragment fragment = new AddressFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public AddressFragment() {
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
        View view = inflater.inflate(R.layout.fragment_address, container, false);
        ImageButton contactInfoEditButton = (ImageButton)view.findViewById(R.id.contactInfoEditButton);
        ImageButton contactInfoContinueButton = (ImageButton)view.findViewById(R.id.contactInfoContinueButton);
        final EditText firstName = (EditText)view.findViewById(R.id.firstName);
        final EditText lastName = (EditText)view.findViewById(R.id.lastName);
        final EditText phoneNumber = (EditText)view.findViewById(R.id.phoneNumber);
        final EditText address = (EditText)view.findViewById(R.id.address);
        final EditText address2 = (EditText)view.findViewById(R.id.address2);
        final EditText pincode = (EditText)view.findViewById(R.id.pincode);
        final EditText landmark = (EditText)view.findViewById(R.id.landmark);
        final EditText city = (EditText)view.findViewById(R.id.city);
        final EditText state = (EditText)view.findViewById(R.id.state);



        /**Setting all editText to false**/
        firstName.setFocusable(false);
        lastName.setFocusable(false);
        phoneNumber.setFocusable(false);
        address.setFocusable(false);
        address2.setFocusable(false);
        pincode.setFocusable(false);
        landmark.setFocusable(false);
        city.setFocusable(false);
        state.setFocusable(false);
        /**Setting all editText to false**/
        contactInfoEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // mListener.replaceFragment(R.id.addressContinueButton,null);
                firstName.setFocusable(true);
                lastName.setFocusable(true);
                phoneNumber.setFocusable(true);
                address.setFocusable(true);
                address2.setFocusable(true);
                pincode.setFocusable(true);
                landmark.setFocusable(true);
                city.setFocusable(true);
                state.setFocusable(true);

            }
        });

        contactInfoContinueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.replaceFragment(R.id.contactInfoContinueButton,null);
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
