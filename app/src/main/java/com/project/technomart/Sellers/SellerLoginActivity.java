package com.project.technomart.Sellers;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.project.technomart.Model.Seller;
import com.project.technomart.Prevalent.PrevalentSeller;
import com.project.technomart.R;

public class SellerLoginActivity extends AppCompatActivity {

    private EditText inputPhone, inputPassword;
    private Button loginButton;
    private ProgressDialog loadingBar;
    

    private String parentDBName = "Sellers";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_login);

        loginButton=(Button) findViewById(R.id.seller_login_btn);
        inputPhone=(EditText) findViewById(R.id.seller_phone);
        inputPassword=(EditText) findViewById(R.id.seller_password);
        loadingBar= new ProgressDialog(this);
        
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) 
            {
                Loginseller();
                
            }
        });
        
    }

    private void Loginseller() 
    {
        String phone = inputPhone.getText().toString();
        String password = inputPassword.getText().toString();

        if(TextUtils.isEmpty(phone)){
            Toast.makeText(this, "Please Enter Your Phone Number..", Toast.LENGTH_SHORT).show();
        }

        else if(TextUtils.isEmpty(password)){
            Toast.makeText(this, "Please Enter Your Password..", Toast.LENGTH_SHORT).show();
        }

        else{

            loadingBar.setTitle("Login Account");
            loadingBar.setMessage("Please wait, while we are checking the Credentials.");
            loadingBar.setCanceledOnTouchOutside(false);//If user click on screen but dialog remain
            loadingBar.show();

            AllowAccesstoAccount(phone,password);
        }
    }

    private void AllowAccesstoAccount(final String phone, final String password)
    {
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();
        
        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) 
            {

                if(dataSnapshot.child(parentDBName).child(phone).exists())
                {
                    Seller sellersData = dataSnapshot.child(parentDBName).child(phone).getValue(Seller.class);

                    if(sellersData.getPhone().equals(phone))
                {
                    if(sellersData.getPassword().equals(password))
                    {
                        Toast.makeText(SellerLoginActivity.this, "Logged in Successfully!", Toast.LENGTH_SHORT).show();
                        loadingBar.dismiss();

                        Intent intent = new Intent(SellerLoginActivity.this, SellerHomeActivity.class);
                        intent.addFlags((Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));//Back Button will not work
                        PrevalentSeller.currentonlineSeller =sellersData;
                        startActivity(intent);
                        finish();

                    }
                    else
                    {
                        loadingBar.dismiss();
                        Toast.makeText(SellerLoginActivity.this, "Wrong Password!", Toast.LENGTH_SHORT).show();

                    }
                }


                }
                else
                {
                    Toast.makeText(SellerLoginActivity.this, "Account with this "+phone+" number does not exits!", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                    //Toast.makeText(SellerLoginActivity.this, "Please Create an Account!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
