package com.app.tarun.dc2.Dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TextView;

import com.app.tarun.dc2.OnFragmentInteractionListener;
import com.app.tarun.dc2.R;

/**
 * Created by Tarun on 27-02-2015.
 */
public class SendPrescriptionDialog extends Dialog {

    Activity activity;
    ImageButton cameraButton;
    ImageButton galleryButton;
    TextView cameraTextView;
    TextView galleryTextView;

    OnFragmentInteractionListener callBack;

    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
    private Uri fileUri;


    public SendPrescriptionDialog(Activity a,int style){
        super(a,style);
        activity = a;
    }

    public SendPrescriptionDialog(Activity a){
        super(a);
        activity = a;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        callBack = (OnFragmentInteractionListener)activity;

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.send_prescription_dialog_layout);

        galleryTextView = (TextView)findViewById((R.id.galleryTextView));
        cameraTextView = (TextView)findViewById((R.id.cameraTextView));

        cameraButton = (ImageButton)findViewById(R.id.cameraButton);
        galleryButton = (ImageButton)findViewById(R.id.galleryButton);

        //Assign custom fonts to TextView
        Typeface typeFace=Typeface.createFromAsset(activity.getAssets(),"fonts/gothic.ttf");
        galleryTextView.setTypeface(typeFace);
        cameraTextView.setTypeface(typeFace);


        //Events of Buttons
        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                callBack.takePhoto();
                dismiss();
            }
        });

        galleryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

}
