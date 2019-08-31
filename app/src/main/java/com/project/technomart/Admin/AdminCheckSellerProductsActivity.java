package com.project.technomart.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.project.technomart.Model.SellerProducts;
import com.project.technomart.R;

import com.project.technomart.ViewHolder.SellerProductViewHolder;
import com.squareup.picasso.Picasso;

public class AdminCheckSellerProductsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    private DatabaseReference unverifiedProductsRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_check_seller_products);

        unverifiedProductsRef = FirebaseDatabase.getInstance().getReference().child("Seller Products");

        recyclerView = findViewById(R.id.admin_seller_products_list);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    protected void onStart()
    {
        super.onStart();



        FirebaseRecyclerOptions<SellerProducts> options = new FirebaseRecyclerOptions.Builder<SellerProducts>().
                setQuery(unverifiedProductsRef.orderByChild("productState").equalTo("Not Approved"), SellerProducts.class).build();

        FirebaseRecyclerAdapter <SellerProducts, SellerProductViewHolder> adapter = new
                FirebaseRecyclerAdapter<SellerProducts, SellerProductViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull SellerProductViewHolder holder, int position, @NonNull final SellerProducts model)
            {
                holder.sellertxtProductName.setText(model.getPname());
                holder.sellertxtProductDescription.setText(model.getDescription());
                holder.sellertxtProductPrice.setText("Price:" + model.getPrice() + " BDT");
                holder.sellertxtName.setText("Seller Name: " + model.getSellerName());
                holder.sellertxtPhone.setText("Seller Phone: " + model.getSellerPhone());
                holder.sellretxtAddress.setText("Seller Address: " + model.getSellerAddress());

                Picasso.get().load(model.getImage()).into(holder.sellerimageView);

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view)
                    {
                        final String productID = model.getPid();
                        CharSequence options[] = new CharSequence[]
                                {
                                        "Yes",
                                        "No"
                                };
                        AlertDialog.Builder builder = new AlertDialog.Builder(AdminCheckSellerProductsActivity.this);
                        builder.setTitle("Do you want to Approved this Product?");
                        builder.setItems(options, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int position)
                            {
                                if(position == 0)
                                {
                                    ChangeSellerProductState(productID);
                                }
                                if(position == 1)
                                {
                                    CharSequence options[] = new CharSequence[]
                                            {
                                                    "Yes",
                                                    "No"
                                            };
                                    AlertDialog.Builder builder = new AlertDialog.Builder(AdminCheckSellerProductsActivity.this);
                                    builder.setTitle("Do you want to Delete this Product?");
                                    builder.setItems(options, new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int position)
                                        {
                                            if(position == 0)
                                            {
                                                DeleteSellerProductState(productID);
                                            }
                                            if(position == 1)
                                            {


                                            }
                                        }
                                    });
                                    builder.show();

                                }
                            }
                        });
                        builder.show();

                    }

                });

            }

            @NonNull
            @Override
            public SellerProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
            {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.seller_product_items_layout, parent, false);

                SellerProductViewHolder holder = new SellerProductViewHolder(view);
                return  holder;
            }
        };


        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }

    private void DeleteSellerProductState(String productID)
    {
        unverifiedProductsRef.child(productID).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task)
            {
                Intent intent = new Intent(AdminCheckSellerProductsActivity.this, AdminCheckSellerProductsActivity.class);
                startActivity(intent);
                finish();

                Toast.makeText(AdminCheckSellerProductsActivity.this, "The Product Is deleted successfully.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void ChangeSellerProductState(String productID)
    {
        unverifiedProductsRef.child(productID).child("productState").setValue("Approved").addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task)
            {
                Toast.makeText(AdminCheckSellerProductsActivity.this, "That Product has been Approved and available for sale.", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
