package com.app.tarun.dc2.Fragments;

import android.app.Activity;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

import com.app.tarun.dc2.Dialogs.SendPrescriptionDialog;
import com.app.tarun.dc2.OnFragmentInteractionListener;
import com.app.tarun.dc2.R;

public class BuyMedicineFragment extends android.support.v4.app.Fragment {
    private OnFragmentInteractionListener mListener;

    public static String TAG = "BuyMedicine";

    // TODO: Rename and change types and number of parameters
    public static BuyMedicineFragment newInstance() {
        BuyMedicineFragment fragment = new BuyMedicineFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public BuyMedicineFragment() {
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
        View view = inflater.inflate(R.layout.fragment_buy_medicine, container, false);

        ImageButton sendPrescriptionButton = (ImageButton)view.findViewById(R.id.sendPrescriptionButton);
        ImageButton enterManuallyButton = (ImageButton)view.findViewById(R.id.enterManuallyButton);
        ImageButton previousOrderButton = (ImageButton)view.findViewById(R.id.previousOrderButton);

        TextView enterManuallyTextView = (TextView)view.findViewById(R.id.EnterManually);
        TextView sendPrescriptionTextView = (TextView)view.findViewById(R.id.SendPrescription);
        TextView previousOrderTextView = (TextView)view.findViewById(R.id.PreviousOrder);

        //Assign custom fonts
        Typeface typeFace=Typeface.createFromAsset(getActivity().getAssets(),"fonts/gothic.ttf");
        enterManuallyTextView.setTypeface(typeFace);
        sendPrescriptionTextView.setTypeface(typeFace);
        previousOrderTextView.setTypeface(typeFace);


        //Events on buttons
        sendPrescriptionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendPrescriptionDialog dialog = new SendPrescriptionDialog(getActivity());
                dialog.show();
                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                Window window = dialog.getWindow();
                lp.copyFrom(window.getAttributes());
                //This makes the dialog take up the full width
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                window.setAttributes(lp);
            }
        });

        enterManuallyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.replaceFragment(R.id.enterManuallyButton,null);
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
