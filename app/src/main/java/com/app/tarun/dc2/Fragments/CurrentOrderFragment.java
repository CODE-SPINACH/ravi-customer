package com.app.tarun.dc2.Fragments;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.tarun.dc2.Adpaters.MedicineCardAdapter;
import com.app.tarun.dc2.Adpaters.MyPagerAdapter;
import com.app.tarun.dc2.Adpaters.PrescriptionCardAdapter;
import com.app.tarun.dc2.R;
import com.viewpagerindicator.TabPageIndicator;

public class CurrentOrderFragment extends android.support.v4.app.Fragment {

    public static String TAG = "CurrentOrder";

    private ViewPager viewPager;
    private TabPageIndicator tabPageIndicator;
    private PrescriptionCardAdapter prescriptionCardAdapter;
    private MedicineCardAdapter medicineCardAdapter;

    public static CurrentOrderFragment newInstance() {
        CurrentOrderFragment fragment = new CurrentOrderFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public CurrentOrderFragment() {
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
        View view = inflater.inflate(R.layout.fragment_current_order, container, false);

        tabPageIndicator = (TabPageIndicator)view.findViewById(R.id.titles);
        viewPager = (ViewPager)view.findViewById((R.id.viewPager));

        MyPagerAdapter pagerAdapter = new MyPagerAdapter(getChildFragmentManager());

        pagerAdapter.setPrescriptionCardAdapter(prescriptionCardAdapter);
        pagerAdapter.setMedicineCardAdapter(medicineCardAdapter);

        viewPager.setAdapter(pagerAdapter);

        tabPageIndicator.setViewPager(viewPager,0);


        return view;
    }

    public void setPrescriptionCardAdapter(PrescriptionCardAdapter adapter){
        prescriptionCardAdapter = adapter;
    }

    public void setMedicineCardAdapter(MedicineCardAdapter adapter) {
        medicineCardAdapter = adapter;
    }
}
