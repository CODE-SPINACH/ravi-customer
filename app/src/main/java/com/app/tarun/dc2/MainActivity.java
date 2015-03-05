package com.app.tarun.dc2;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.app.tarun.dc2.Adpaters.MedicineCardAdapter;
import com.app.tarun.dc2.Adpaters.PrescriptionCardAdapter;
import com.app.tarun.dc2.Data.PrescriptionDetails;
import com.app.tarun.dc2.Dialogs.SendPrescriptionDialog;
import com.app.tarun.dc2.Fragments.AddressFragment;
import com.app.tarun.dc2.Fragments.BuyMedicineFragment;
import com.app.tarun.dc2.Fragments.CartFragment;
import com.app.tarun.dc2.Fragments.HomeScreenFragment;
import com.app.tarun.dc2.Fragments.MedicineEditFragment;
import com.app.tarun.dc2.Fragments.NotificationFragment;
import com.app.tarun.dc2.Fragments.TimeSlotFragment;
import com.app.tarun.dc2.Fragments.PrescriptionEditFragment;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;


public class MainActivity extends ActionBarActivity implements OnFragmentInteractionListener {

    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;

    private Uri fileUri;
    private DrawerLayout drawerLayout;
    private ListView drawerListView;

    private String[] drawerOptions = {"Home", "Orders", "Settings", "Profile", "About Us"};

    private ActionBarDrawerToggle drawerToggle;

    //The complete Order List is within these adapters, to be retained throughout the lifecycle of activity
    PrescriptionCardAdapter prescriptionCardAdapter;
    MedicineCardAdapter medicineCardAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_layout);

        prescriptionCardAdapter = new PrescriptionCardAdapter(this,R.layout.cards_prescription);
        medicineCardAdapter = new MedicineCardAdapter(this,R.layout.cards_medicine);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerListView = (ListView) findViewById(R.id.drawer_list);

        drawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

        drawerListView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, drawerOptions));
        drawerListView.setOnItemClickListener(new DrawerListItemClickListener());


        //TO DO : change content description
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout,
                R.string.drawer_open, R.string.drawer_closr) {

            //Change Title
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle("Home");
            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle("Drug Corner");
            }


        };
        drawerLayout.setDrawerListener(drawerToggle);

        if (savedInstanceState == null)
            selectItem(0);

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    //handles the drawer item selection
    private class DrawerListItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    private void selectItem(int position) {
        android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.container, HomeScreenFragment.newInstance(),HomeScreenFragment.TAG).commitAllowingStateLoss();
    }

    public void onFragmentInteraction(Uri uri) {
    }

    @Override
    public void onBackPressed() {
        getSupportFragmentManager().popBackStack();
    }

    public void takePhoto() {
        Calendar cal = Calendar.getInstance();
        File file = new File(Environment.getExternalStorageDirectory(),  (cal.getTimeInMillis()+".jpg"));
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }else{
            file.delete();
            try {
                file.createNewFile();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        fileUri = Uri.fromFile(file);
        Intent i = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        i.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
        startActivityForResult(i, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
            replaceFragment(CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE,null);
        }
    }

    public void addPrescriptionDetails(PrescriptionDetails details){

        //The Prescription details entered in the edit fragment are being added to the adpater
        //to prevent data loss


    }

    public void replaceFragment(int id,Object object){
        android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        switch (id){
            case R.id.buyMedicineButton:
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.replace(R.id.container, BuyMedicineFragment.newInstance(),BuyMedicineFragment.TAG);
                ft.addToBackStack("Home");
                ft.commitAllowingStateLoss();
                break;

            case R.id.callUsButton:
                break;

            case R.id.sendPrescriptionButton:
                SendPrescriptionDialog dialog = new SendPrescriptionDialog(this);
                dialog.show();
                break;

            case R.id.enterManuallyButton:
                MedicineEditFragment editFragment =
                        (MedicineEditFragment)getSupportFragmentManager().findFragmentByTag(MedicineEditFragment.TAG);

                if(editFragment == null)
                    editFragment = MedicineEditFragment.newInstance();

                editFragment.setMedicineCardAdapter(medicineCardAdapter);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.replace(R.id.container,editFragment,MedicineEditFragment.TAG);
                ft.addToBackStack(null);
                ft.commitAllowingStateLoss();
                break;

            case R.id.previousOrderButton:
                break;

            case  R.id.prescriptionContinue:
                //Adapter is attached to cartFragment to make it visible there

                CartFragment cartFragment = CartFragment.newInstance();

                if(object!=null)
                    prescriptionCardAdapter.add((PrescriptionDetails)object);

                cartFragment.setPrescriptionCardAdapter(prescriptionCardAdapter);
                cartFragment.setMedicineCardAdapter(medicineCardAdapter);

                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.replace(R.id.container, cartFragment,CartFragment.TAG);
                ft.addToBackStack("Cam");
                ft.commitAllowingStateLoss();
                break;

            case CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE:
                PrescriptionEditFragment fragment1 = PrescriptionEditFragment.newInstance();
                fragment1.fileUri = fileUri;
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.replace(R.id.container, fragment1,PrescriptionEditFragment.TAG);
                ft.addToBackStack("BuyMedicine");
                ft.commitAllowingStateLoss();
                break;

            case R.layout.cards_prescription:
                PrescriptionDetails prescriptionDetails = prescriptionCardAdapter.getItem((int)object);
                PrescriptionEditFragment fragment2 = (PrescriptionEditFragment)
                        getSupportFragmentManager().findFragmentByTag(PrescriptionEditFragment.TAG);

                fragment2.setPrescriptionDetails(prescriptionDetails);

                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.replace(R.id.container, fragment2);
                ft.addToBackStack("prescriptions");
                ft.commitAllowingStateLoss();
                break;

            case R.id.CartAddButton:
                Fragment orderFragment = getSupportFragmentManager().findFragmentByTag(BuyMedicineFragment.TAG);
                if(orderFragment == null)
                    break;
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.replace(R.id.container, orderFragment);
                ft.addToBackStack("order");
                ft.commitAllowingStateLoss();
                break;

            case R.id.medicineEditContinueButton:
                CartFragment orderFragment1 =
                        (CartFragment)getSupportFragmentManager().findFragmentByTag(CartFragment.TAG);
                if(orderFragment1 == null) {
                    orderFragment1 = CartFragment.newInstance();
                }
                orderFragment1.setMedicineCardAdapter(medicineCardAdapter);
                orderFragment1.setPrescriptionCardAdapter(prescriptionCardAdapter);

                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.replace(R.id.container, orderFragment1,CartFragment.TAG);
                ft.addToBackStack(null);
                ft.commitAllowingStateLoss();
                break;

            case R.id.contactInfoContinueButton:
                TimeSlotFragment timeSlotFragment =
                        (TimeSlotFragment)getSupportFragmentManager().findFragmentByTag(TimeSlotFragment.TAG);
                if(timeSlotFragment == null) {
                    timeSlotFragment = TimeSlotFragment.newInstance();
                }


                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.replace(R.id.container, timeSlotFragment, TimeSlotFragment.TAG);
                ft.addToBackStack(null);
                ft.commitAllowingStateLoss();
                break;

            case R.id.firstTimeSlot:
                NotificationFragment notificationFragment =
                        (NotificationFragment)getSupportFragmentManager().findFragmentByTag(NotificationFragment.TAG);
                if(notificationFragment == null) {
                    notificationFragment = NotificationFragment.newInstance();
                }
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.replace(R.id.container, notificationFragment, NotificationFragment.TAG);
                ft.addToBackStack(null);
                ft.commitAllowingStateLoss();
                break;

            case R.id.secondTimeSlot:
                NotificationFragment notificationFragment2 =
                        (NotificationFragment)getSupportFragmentManager().findFragmentByTag(NotificationFragment.TAG);
                if(notificationFragment2 == null) {
                    notificationFragment2 = NotificationFragment.newInstance();
                }
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.replace(R.id.container, notificationFragment2, NotificationFragment.TAG);
                ft.addToBackStack(null);
                ft.commitAllowingStateLoss();
                break;

            case R.id.thirdTimeSLot:
                NotificationFragment notificationFragment3 =
                        (NotificationFragment)getSupportFragmentManager().findFragmentByTag(NotificationFragment.TAG);
                if(notificationFragment3 == null) {
                    notificationFragment3 = NotificationFragment.newInstance();
                }
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.replace(R.id.container, notificationFragment3, NotificationFragment.TAG);
                ft.addToBackStack(null);
                ft.commitAllowingStateLoss();
                break;

            case R.id.fourthTimeSlot:
                NotificationFragment notificationFragment4 =
                        (NotificationFragment)getSupportFragmentManager().findFragmentByTag(NotificationFragment.TAG);
                if(notificationFragment4 == null) {
                    notificationFragment4 = NotificationFragment.newInstance();
                }
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.replace(R.id.container, notificationFragment4, NotificationFragment.TAG);
                ft.addToBackStack(null);
                ft.commitAllowingStateLoss();
                break;


            case R.id.CartContinueButton:
                AddressFragment addressFragment =
                        (AddressFragment)getSupportFragmentManager().findFragmentByTag(AddressFragment.TAG);
                if(addressFragment == null){
                    addressFragment = AddressFragment.newInstance();
                }
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.replace(R.id.container, addressFragment,AddressFragment.TAG);
                ft.addToBackStack(null);
                ft.commitAllowingStateLoss();
            default:
                break;
        }

    }



}
