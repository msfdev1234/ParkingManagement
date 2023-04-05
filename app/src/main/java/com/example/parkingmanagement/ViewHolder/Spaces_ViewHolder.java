package com.example.parkingmanagement.ViewHolder;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.parkingmanagement.Interface.ItemClickListener;
import com.example.parkingmanagement.R;

public class Spaces_ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView parkingName_TxtVw, ownerName_TxtVw, address_TxtVw, phone_TxtVw;

    public RelativeLayout view_Whole;
    private ItemClickListener itemClickListener;

    public Spaces_ViewHolder(@NonNull View itemView) {
        super(itemView);

        parkingName_TxtVw = itemView.findViewById(R.id.spaceName_TxtVw);
        ownerName_TxtVw = itemView.findViewById(R.id.ownerName_TxtVw_Spaces);
        address_TxtVw = itemView.findViewById(R.id.address_TxtVw_Spaces);
        phone_TxtVw = itemView.findViewById(R.id.phone_TxtVw_Spaces);
        view_Whole = itemView.findViewById(R.id.view_Whole_Spaces);


    }

    @Override
    public void onClick(View view) {

    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
}
