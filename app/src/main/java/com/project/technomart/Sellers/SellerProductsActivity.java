package com.project.technomart.Sellers;

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
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.project.technomart.Model.SellerProducts;
import com.project.technomart.Prevalent.PrevalentSeller;
import com.project.technomart.R;
import com.project.technomart.ViewHolder.SellerProductViewHolder;
import com.squareup.picasso.Picasso;

public class SellerProductsActivity extends AppCompatActivity {

    private TextView SellerName;
    private DatabaseReference ProductsRef;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_products);

        SellerName = (TextView) findViewById(R.id.seller_products_name_title);
        ProductsRef = FirebaseDatabase.getInstance().getReference().child("Seller Products");
        recyclerView = findViewById(R.id.seller_products_list);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        SellerName.setText("Product of "+ PrevalentSeller.currentonlineSeller.getName());

    }

    @Override
    protected void onStart()
    {
        super.onStart();
        FirebaseRecyclerOptions<SellerProducts> options = new FirebaseRecyclerOptions.Builder<SellerProducts>().
                setQuery(ProductsRef.orderByChild("sellerPhone").equalTo(PrevalentSeller.currentonlineSeller.getPhone()), SellerProducts.class).build();

        FirebaseRecyclerAdapter<SellerProducts, SellerProductViewHolder> adapter = new
                FirebaseRecyclerAdapter<SellerProducts, SellerProductViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull SellerProductViewHolder holder, int position, @NonNull final SellerProducts model) {
                        holder.sellertxtProductName.setText(model.getPname());
                        holder.sellertxtProductDescription.setText(model.getDescription());
                        holder.sellertxtProductPrice.setText("Price:" + model.getPrice() + " BDT");
                        holder.sellertxtName.setText("Seller Name: " + model.getSellerName());
                        holder.sellertxtPhone.setText("Seller Phone: " + model.getSellerPhone());
                        holder.sellretxtAddress.setText("Seller Address: " + model.getSellerAddress());

                        Picasso.get().load(model.getImage()).into(holder.sellerimageView);

                        holder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                final String productID = model.getPid();
                                CharSequence options[] = new CharSequence[]
                                        {
                                                "Yes",
                                                "No"
                                        };
                                AlertDialog.Builder builder = new AlertDialog.Builder(SellerProductsActivity.this);
                                builder.setTitle("Do you want to Update this Product?");
                                builder.setItems(options, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int position) {
                                        if (position == 0) {

                                            Intent intent = new Intent(SellerProductsActivity.this, SellerMaintainProductsActivity.class);
                                            intent.putExtra("pid", model.getPid());
                                            startActivity(intent);
                                        }
                                        if (position == 1)
                                        {

                                        }
                                    }
                                });
                                builder.show();

                            }

                        });

                    }

                    @NonNull
                    @Override
                    public SellerProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.seller_product_items_layout, parent, false);

                        SellerProductViewHolder holder = new SellerProductViewHolder(view);
                        return holder;
                    }
                };


        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }

}

