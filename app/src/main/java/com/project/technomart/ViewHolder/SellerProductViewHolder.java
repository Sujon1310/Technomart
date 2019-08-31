package com.project.technomart.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.technomart.Interfaces.ItemClickListener;
import com.project.technomart.R;

public class SellerProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    public TextView sellertxtProductName,sellertxtProductDescription, sellertxtProductPrice,sellertxtName,sellertxtPhone,sellretxtAddress;
    public ImageView sellerimageView;
    public ItemClickListener sellerlistner;

    public SellerProductViewHolder(@NonNull View itemView)
    {

        super(itemView);

        sellerimageView = (ImageView) itemView.findViewById(R.id.seller_product_image_view);
        sellertxtProductName = (TextView) itemView.findViewById(R.id.seller_product_name_title);
        sellertxtProductDescription = (TextView) itemView.findViewById(R.id.seller_product_description);
        sellertxtProductPrice = (TextView) itemView.findViewById(R.id.seller_product_price );

        sellertxtName = (TextView) itemView.findViewById(R.id.seller_name_view );
        sellertxtPhone = (TextView) itemView.findViewById(R.id.seller_phone_view );
        sellretxtAddress = (TextView) itemView.findViewById(R.id.seller_address_view );
    }

    public void setItemClickListener(ItemClickListener listner)
    {
        this.sellerlistner = listner;
    }

    @Override
    public void onClick(View view)
    {
        sellerlistner.onClick(view, getAdapterPosition(),false);
    }
}
