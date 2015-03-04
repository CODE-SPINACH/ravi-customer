package com.app.tarun.dc2.Adpaters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.app.tarun.dc2.Data.PrescriptionDetails;
import com.app.tarun.dc2.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tarun on 28-02-2015.
 */
public class PrescriptionCardAdapter extends ArrayAdapter<PrescriptionDetails> {


    public static class ViewHolder{
        public TextView sNoTextView;
        public TextView daysTextView;
        public ImageButton closeButton;
    }

    List<PrescriptionDetails> prescriptionList = new ArrayList<PrescriptionDetails>();

    public PrescriptionCardAdapter(Context context,int cardViewResourceId){
        super(context,cardViewResourceId);
    }

    @Override
    public void add(PrescriptionDetails object) {
        prescriptionList.add(object);
        super.add(object);
    }

    @Override
    public int getCount() {
        return this.prescriptionList.size();
    }

    @Override
    public PrescriptionDetails getItem(int index) {
        return this.prescriptionList.get(index);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;

        ViewHolder holder = new ViewHolder();

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.cards_prescription, parent, false);

            holder.closeButton = (ImageButton) view.findViewById(R.id.prescriptionCardCloseButton);
            holder.sNoTextView = (TextView) view.findViewById(R.id.serialNoTextView);
            holder.daysTextView = (TextView) view.findViewById(R.id.daysTextView);

            holder.closeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    prescriptionList.remove(position);
                    notifyDataSetInvalidated();
                }
            });

            view.setTag(holder);
        } else {
            holder = (ViewHolder)view.getTag();
        }

        PrescriptionDetails prescriptionDetails = getItem(position);
        holder.sNoTextView.setText((position + 1) + "");
        holder.daysTextView.setText(prescriptionDetails.getDays() + "");
        return view;
    }
}
