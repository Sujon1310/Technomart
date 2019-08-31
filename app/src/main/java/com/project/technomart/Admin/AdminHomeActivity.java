package com.project.technomart.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.project.technomart.Buyers.HomeActivity;
import com.project.technomart.Buyers.MainActivity;
import com.project.technomart.R;

public class AdminHomeActivity extends AppCompatActivity {

    private ImageView Ctg_Aurdinos, Ctg_Boards, Ctg_Batteries, Ctg_Capacitors;
    private ImageView Ctg_Controllers, Ctg_Displays, Ctg_Ics, Ctg_Modules;
    private ImageView Ctg_Raspberry, Ctg_Sensors, Ctg_Wires, Ctg_Others;

    private Button LogOutBtn,CheckOrdersBtn, ChkSellerProductsBtn,maintainProductsBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        LogOutBtn = (Button) findViewById(R.id.admin_logout_btn);
        CheckOrdersBtn = (Button) findViewById(R.id.check_orders_btn);
        maintainProductsBtn = (Button) findViewById(R.id.maintain_btn);
        ChkSellerProductsBtn = (Button) findViewById(R.id.check_seller_products_btn);

        Ctg_Aurdinos = (ImageView) findViewById(R.id.ctg_aurdinos);
        Ctg_Boards = (ImageView) findViewById(R.id.ctg_boards);
        Ctg_Batteries = (ImageView) findViewById(R.id.ctg_batteries);
        Ctg_Capacitors = (ImageView) findViewById(R.id.ctg_capacitors);

        Ctg_Controllers = (ImageView) findViewById(R.id.ctg_controllers);
        Ctg_Displays = (ImageView) findViewById(R.id.ctg_displays);
        Ctg_Ics = (ImageView) findViewById(R.id.ctg_ics);
        Ctg_Modules = (ImageView) findViewById(R.id.ctg_modules);

        Ctg_Raspberry = (ImageView) findViewById(R.id.ctg_raspberrypi);
        Ctg_Sensors = (ImageView) findViewById(R.id.ctg_sensors);
        Ctg_Wires = (ImageView) findViewById(R.id.ctg_wires);
        Ctg_Others = (ImageView) findViewById(R.id.ctg_others);


        LogOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(AdminHomeActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });

        CheckOrdersBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(AdminHomeActivity.this, AdminNewOrdersActivity.class);
                startActivity(intent);
            }
        });

        maintainProductsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(AdminHomeActivity.this, HomeActivity.class);
                intent.putExtra("Admin", "Admin");
                startActivity(intent);
            }
        });

        ChkSellerProductsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminHomeActivity.this, AdminCheckSellerProductsActivity.class);
                startActivity(intent);
            }
        });


        Ctg_Aurdinos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(AdminHomeActivity.this, AdminAddProductsActivity.class);
                intent.putExtra("category", "Aurdinos");
                startActivity(intent);
            }
        });


        Ctg_Boards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(AdminHomeActivity.this, AdminAddProductsActivity.class);
                intent.putExtra("category", "Boards");
                startActivity(intent);
            }
        });


        Ctg_Batteries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(AdminHomeActivity.this, AdminAddProductsActivity.class);
                intent.putExtra("category", "Batteries");
                startActivity(intent);
            }
        });


        Ctg_Capacitors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(AdminHomeActivity.this, AdminAddProductsActivity.class);
                intent.putExtra("category", "Capacitors");
                startActivity(intent);
            }
        });


        Ctg_Controllers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(AdminHomeActivity.this, AdminAddProductsActivity.class);
                intent.putExtra("category", "Controllers");
                startActivity(intent);
            }
        });


        Ctg_Displays.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(AdminHomeActivity.this, AdminAddProductsActivity.class);
                intent.putExtra("category", "Displays");
                startActivity(intent);
            }
        });



        Ctg_Ics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(AdminHomeActivity.this, AdminAddProductsActivity.class);
                intent.putExtra("category", "ICs");
                startActivity(intent);
            }
        });


        Ctg_Modules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(AdminHomeActivity.this, AdminAddProductsActivity.class);
                intent.putExtra("category", "Modules");
                startActivity(intent);
            }
        });



        Ctg_Raspberry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(AdminHomeActivity.this, AdminAddProductsActivity.class);
                intent.putExtra("category", "Raspberrypi");
                startActivity(intent);
            }
        });


        Ctg_Sensors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(AdminHomeActivity.this, AdminAddProductsActivity.class);
                intent.putExtra("category", "Sensors");
                startActivity(intent);
            }
        });


        Ctg_Wires.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(AdminHomeActivity.this, AdminAddProductsActivity.class);
                intent.putExtra("category", "Wires");
                startActivity(intent);
            }
        });


        Ctg_Others.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(AdminHomeActivity.this, AdminAddProductsActivity.class);
                intent.putExtra("category", "Others");
                startActivity(intent);
            }
        });
    }
}
