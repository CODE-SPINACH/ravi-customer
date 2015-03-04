package com.app.tarun.dc2.Adpaters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.app.tarun.dc2.Data.MedicineDetails;
import com.app.tarun.dc2.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tarun on 28-02-2015.
 */

public class MedicineCardAdapter extends ArrayAdapter<MedicineDetails> {

        public static class ViewHolder{
            public TextView sNo;
            public TextView nameOfMedicine;
            public TextView numberOfMedicine;
            public ImageButton closeButton;
        }

        List<MedicineDetails> medicineList = new ArrayList<MedicineDetails>();

        public MedicineCardAdapter(Context context,int cardViewResourceId){
            super(context,cardViewResourceId);
        }

        @Override
        public void add(MedicineDetails object) {
            medicineList.add(object);
            super.add(object);
        }

        @Override
        public int getCount() {
            return this.medicineList.size();
        }

        @Override
        public MedicineDetails getItem(int index) {
            return this.medicineList.get(index);
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View view = convertView;
            ViewHolder holder = new ViewHolder();
            if (view == null) {
                LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.cards_medicine, parent, false);

                holder.sNo = (TextView) view.findViewById(R.id.serialNoTextView1);
                holder.nameOfMedicine = (TextView) view.findViewById(R.id.nameOfMedicineTextView);
                holder.numberOfMedicine = (TextView) view.findViewById(R.id.numberOfMedicineTextView);
                holder.closeButton = (ImageButton)view.findViewById(R.id.medicineCardCloseButton);

                holder.closeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        medicineList.remove(position);
                        notifyDataSetChanged();
                    }
                });

                view.setTag(holder);
            } else {
                holder = (ViewHolder)view.getTag();
            }

            MedicineDetails medicineDetails = getItem(position);
            holder.sNo.setText((position + 1) +"");
            holder.nameOfMedicine.setText(medicineDetails.getMedicineName() + "");
            holder.numberOfMedicine.setText(medicineDetails.getQuantity() + "");
            return view;
        }
}