package com.project.technomart.Buyers;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.project.technomart.Model.SellerProducts;
import com.project.technomart.R;
import com.squareup.picasso.Picasso;

public class SellerProductDetailsActivity extends AppCompatActivity {

    private  DatabaseReference productsRef;
    private Button callSellerButton;
    private ImageView sellerProductImage;
    private TextView sellerProductPrice, sellerProductDescription, sellerProductName, sellerName,sellerPhone,sellerAddress;
    private String sellerProductID = "",callNumber = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_product_details);

        productsRef = FirebaseDatabase.getInstance().getReference().child("Seller Products");

        sellerProductID = getIntent().getStringExtra("pid");

        callSellerButton = (Button) findViewById(R.id.call_seller_button);
        sellerProductImage = (ImageView) findViewById(R.id.seller_product_image_details);
        sellerProductName = (TextView) findViewById(R.id.seller_product_name_details);
        sellerProductDescription = (TextView) findViewById(R.id.seller_product_description_details);
        sellerProductPrice = (TextView) findViewById(R.id.seller_product_price_details);
        sellerName = (TextView) findViewById(R.id.seller_name_details);
        sellerPhone = (TextView) findViewById(R.id.seller_phone_details);
        sellerAddress = (TextView) findViewById(R.id.seller_address_details);

       getSellerProductDetails(sellerProductID);

       callSellerButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view)
           {
               Intent intent = new Intent(Intent.ACTION_DIAL);
               intent.setData(Uri.parse("tel:" + callNumber));
               startActivity(intent);
           }
       });

    }

    private void getSellerProductDetails(String sellerProductID)
    {
        productsRef.child(sellerProductID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {

                    SellerProducts products = dataSnapshot.getValue(SellerProducts.class);

                    callNumber = dataSnapshot.child("sellerPhone").getValue().toString();

                    sellerProductName.setText("Product Name: " + products.getPname());
                    sellerProductPrice.setText("Product Name: " + products.getPrice() +" BDT" );
                    sellerProductDescription.setText("Product Description: " + products.getDescription());
                    sellerName.setText("Seller Name: " + products.getSellerName());
                    sellerPhone.setText("Seller Phone Number: " + products.getSellerPhone());
                    sellerAddress.setText("Seller Address: " + products.getSellerAddress());

                    Picasso.get().load(products.getImage()).into(sellerProductImage);
                }

                else
                {
                    Toast.makeText(SellerProductDetailsActivity.this, "Connection Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
