package com.app.tarun.dc2;

import android.net.Uri;

import com.app.tarun.dc2.Data.PrescriptionDetails;

/**
 * Created by Tarun on 27-02-2015.
 */
public interface OnFragmentInteractionListener {
    public void onFragmentInteraction(Uri uri);
    public void replaceFragment(int id,Object object);
    public void takePhoto();
    public void addPrescriptionDetails(PrescriptionDetails details);
}
