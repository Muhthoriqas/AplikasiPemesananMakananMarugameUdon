package com.example.marugameserver.ViewHolder;


import android.view.ContextMenu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.example.marugameserver.Common.Common;
import com.example.marugameserver.Interface.ItemClickListener;
import com.example.marugameserver.R;


public class MenuViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,
        View.OnLongClickListener,
        View.OnCreateContextMenuListener{

    public TextView txtMenuName;
    public ImageView imageView;

    private ItemClickListener itemClickListener;
    public MenuViewHolder(View itemView) {
        super(itemView);

        txtMenuName =(TextView) itemView.findViewById(R.id.menu_name);
        imageView = (ImageView) itemView.findViewById(R.id.menu_image);

        itemView.setOnCreateContextMenuListener(this);
        itemView.setOnLongClickListener(this);
        itemView.setOnClickListener(this);

    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view,getAdapterPosition(),false);
    }

    @Override
    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
        contextMenu.setHeaderTitle("Select the action");

        contextMenu.add(0,0,getAdapterPosition(), Common.UPDATE);
        contextMenu.add(0,1,getAdapterPosition(), Common.DELETE);
    }

    @Override
    public boolean onLongClick(View view) {
        return false;
    }

//    @Override
//    public boolean onLongClick(View view) {
//        itemClickListener.onClick(view,getAdapterPosition(),true);
//        return true;
//    }
}
