package com.example.marugameserver.ViewHolder;


import android.view.ContextMenu;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.marugameserver.Interface.ItemClickListener;
import com.example.marugameserver.R;

public class OrderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnCreateContextMenuListener {

    public TextView txtOrderId, txtOrderStatus,txtOrderPhone,txtOrderAddress;

    private ItemClickListener itemClickListener;

    public OrderViewHolder(View itemView) {
        super(itemView);

        txtOrderAddress=(TextView) itemView.findViewById(R.id.order_address);
        txtOrderId=(TextView) itemView.findViewById(R.id.order_id);
        txtOrderStatus=(TextView) itemView.findViewById(R.id.order_status);
        txtOrderPhone=(TextView) itemView.findViewById(R.id.order_phone);

        itemView.setOnClickListener(this);
        itemView.setOnCreateContextMenuListener(this);

    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view,getAdapterPosition(),false);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.setHeaderTitle("PILIH AKSI");

        menu.add(0,0, getAdapterPosition(), "Update");
        menu.add(0,1, getAdapterPosition(), "Delete");
    }
}

