package com.app.tarun.dc2.Fragments;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.app.tarun.dc2.Adpaters.MedicineCardAdapter;
import com.app.tarun.dc2.Adpaters.PrescriptionCardAdapter;
import com.app.tarun.dc2.OnFragmentInteractionListener;
import com.app.tarun.dc2.R;

public class CartFragment extends android.support.v4.app.Fragment {

    public static String TAG = "Cart";

    private OnFragmentInteractionListener mListener;

    private PrescriptionCardAdapter prescriptionCardAdapter;
    private MedicineCardAdapter medicineCardAdapter;

    // TODO: Rename and change types and number of parameters
    public static CartFragment newInstance() {
        CartFragment fragment = new CartFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public CartFragment() {
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
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        ImageButton addMoreButton = (ImageButton)view.findViewById(R.id.CartAddButton);
        ImageButton continueButton = (ImageButton)view.findViewById(R.id.CartContinueButton);

        //Setting up the current order fragment
        CurrentOrderFragment currentOrderFragment = CurrentOrderFragment.newInstance();

        currentOrderFragment.setPrescriptionCardAdapter(prescriptionCardAdapter);
        currentOrderFragment.setMedicineCardAdapter(medicineCardAdapter);

        getChildFragmentManager().beginTransaction().replace(R.id.cartContainer,currentOrderFragment,"CurrentOrder").commitAllowingStateLoss();


        //events on views
        addMoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.replaceFragment(R.id.CartAddButton,null);
            }
        });

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.replaceFragment(R.id.CartContinueButton,null);
            }
        });

        return view;
    }

    public void setPrescriptionCardAdapter(PrescriptionCardAdapter adapter){
        prescriptionCardAdapter = adapter;
    }

    public void setMedicineCardAdapter(MedicineCardAdapter adapter) {
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
