package com.project.technomart.Sellers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.project.technomart.R;

import java.util.HashMap;

public class SellerRegistrationActivity extends AppCompatActivity {
    private Button seller_Register_Btn;
    private TextView already_Account;
    private EditText nameInput, phoneNumber_Input,email_Input,password_Input,addressInput;
    private ProgressDialog loadingBar;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_seller_registration);

        loadingBar= new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();


        seller_Register_Btn = (Button) findViewById(R.id.seller_register_btn);
        already_Account = (TextView) findViewById(R.id.seller_already_link);
        nameInput = (EditText) findViewById(R.id.seller_name);
        phoneNumber_Input = (EditText) findViewById(R.id.seller_phone);
        email_Input = (EditText) findViewById(R.id.seller_email);
        password_Input = (EditText) findViewById(R.id.seller_password);
        addressInput = (EditText) findViewById(R.id.seller_address);

        seller_Register_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                registerSeller();
            }
        });



        already_Account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(SellerRegistrationActivity.this,SellerLoginActivity.class);
                startActivity(intent);
            }
        });

    }

    private void registerSeller()
    {
        final String name = nameInput.getText().toString();
        final String phone = phoneNumber_Input.getText().toString();
        final String email = email_Input.getText().toString();
        final String password = password_Input.getText().toString();
        final String address = addressInput.getText().toString();

        if(TextUtils.isEmpty(name)){
            Toast.makeText(this, "Please Enter Your Name..", Toast.LENGTH_SHORT).show();
        }

        else if(TextUtils.isEmpty(phone)){
            Toast.makeText(this, "Please Enter Your Phone Number..", Toast.LENGTH_SHORT).show();
        }

        else if(TextUtils.isEmpty(password)){
            Toast.makeText(this, "Please Enter Your Password..", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(email)){
            Toast.makeText(this, "Please Enter Your Email..", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(address)){
            Toast.makeText(this, "Please Enter Your Address.", Toast.LENGTH_SHORT).show();
        }

        else{

            loadingBar.setTitle("Create Account");
            loadingBar.setMessage("Please wait, while we are checking the Credentials.");
            loadingBar.setCanceledOnTouchOutside(false);//If user click on screen but dialog remain
            loadingBar.show();

            ValidatephoneNumber(name,phone,email,password,address);
        }


    }

    private void ValidatephoneNumber(final String name, final String phone, final String email, final String password, final String address)
    {
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(!(dataSnapshot.child("Sellers").child(phone).exists())){

                    HashMap<String, Object>sellerdataMap=new HashMap<>();
                    sellerdataMap.put("phone", phone);
                    sellerdataMap.put("password", password);
                    sellerdataMap.put("name", name);
                    sellerdataMap.put("email", email);
                    sellerdataMap.put("address", address);

                    RootRef.child("Sellers").child(phone).updateChildren(sellerdataMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if (task.isSuccessful())
                            {
                                Toast.makeText(SellerRegistrationActivity.this, "Congratulations! Your Account has been Created Successfully!", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                                Intent intent = new Intent(SellerRegistrationActivity.this, SellerLoginActivity.class);
                                intent.addFlags((Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));//Back Button will not work
                                startActivity(intent);
                                finish();
                            }

                            else
                            {
                                loadingBar.dismiss();
                                Toast.makeText(SellerRegistrationActivity.this, "Network Error: Please Try Again", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });


                }
                else {
                    Toast.makeText(SellerRegistrationActivity.this, "This" + phone + "Already Exists", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                    Toast.makeText(SellerRegistrationActivity.this, "Please Try with Another Phone Number", Toast.LENGTH_SHORT).show();


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
