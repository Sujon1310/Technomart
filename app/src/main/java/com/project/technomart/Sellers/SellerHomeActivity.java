package com.project.technomart.Sellers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.project.technomart.Buyers.MainActivity;
import com.project.technomart.Prevalent.PrevalentSeller;
import com.project.technomart.R;
import com.rey.material.widget.CheckBox;


public class SellerHomeActivity extends AppCompatActivity {

    private Button sellerAddProductBtn, sellerLogoutBtn, sellerProductsBtn;
    private TextView displayName;
    private CheckBox AcceptTermsCheckbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_home);

        sellerAddProductBtn = (Button) findViewById(R.id.seller_add_btn);
        sellerLogoutBtn = (Button) findViewById(R.id.seller_logout_btn);
        sellerProductsBtn = (Button) findViewById(R.id.my_products_btn);
        displayName = (TextView) findViewById(R.id.seller_name_txt);
        AcceptTermsCheckbox = findViewById(R.id.accept_terms_chkb);

        displayName.setText(PrevalentSeller.currentonlineSeller.getName()+ ",");

        AcceptTermsCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b)
            {
                if(AcceptTermsCheckbox.isChecked())
                {
                    sellerAddProductBtn.setVisibility(View.VISIBLE);
                }
                else
                {
                    sellerAddProductBtn.setVisibility(View.GONE);
                }
            }
        });


        sellerLogoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(SellerHomeActivity.this, MainActivity.class);
                intent.addFlags((Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));//Back Button will not work
                startActivity(intent);
                finish();

            }
        });

        sellerProductsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(SellerHomeActivity.this, SellerProductsActivity.class);

                startActivity(intent);

            }
        });

        sellerAddProductBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(SellerHomeActivity.this, SellerAddProductsActivity.class);
                startActivity(intent);

            }
        });

    }
}
