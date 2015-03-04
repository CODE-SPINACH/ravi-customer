package com.app.tarun.dc2.Adpaters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.app.tarun.dc2.Fragments.MedicineListFragment;
import com.app.tarun.dc2.Fragments.PrescriptionListFragment;

/**
 * Created by Tarun on 28-02-2015.
 */
public class MyPagerAdapter extends FragmentPagerAdapter {

    private PrescriptionCardAdapter prescriptionCardAdapter;
    private MedicineCardAdapter medicineCardAdapter;

    public MyPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int pos) {
        switch(pos) {
            case 0:
                PrescriptionListFragment prescriptionListFragment = PrescriptionListFragment.newInstance();
                prescriptionListFragment.setPrescriptionCardAdapter(prescriptionCardAdapter);
                return prescriptionListFragment;
            case 1:
                MedicineListFragment medicineListFragment = MedicineListFragment.newInstance();
                medicineListFragment.setMedicineCardAdapter(medicineCardAdapter);
                return medicineListFragment;
            default:
                PrescriptionListFragment prescriptionListFragment1 = PrescriptionListFragment.newInstance();
                prescriptionListFragment1.setPrescriptionCardAdapter(prescriptionCardAdapter);
                return prescriptionListFragment1;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch(position) {
            case 0: return PrescriptionListFragment.getTitle();
            case 1: return MedicineListFragment.getTitle();
            default: return PrescriptionListFragment.getTitle();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    public void setPrescriptionCardAdapter(PrescriptionCardAdapter adapter){
        prescriptionCardAdapter = adapter;
    }

    public void setMedicineCardAdapter(MedicineCardAdapter adapter) {
        medicineCardAdapter = adapter;
    }

}