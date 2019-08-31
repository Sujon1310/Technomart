package com.project.technomart.Buyers.Categories;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.project.technomart.Admin.AdminAddProductsActivity;
import com.project.technomart.R;

public class CategoriesHomeActivity extends AppCompatActivity {

    private ImageView Ctg_Aurdinos, Ctg_Boards, Ctg_Batteries, Ctg_Capacitors;
    private ImageView Ctg_Controllers, Ctg_Displays, Ctg_Ics, Ctg_Modules;
    private ImageView Ctg_Raspberry, Ctg_Sensors, Ctg_Wires, Ctg_Others;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories_home);

        Ctg_Aurdinos = (ImageView) findViewById(R.id.category_aurdinos);
        Ctg_Boards = (ImageView) findViewById(R.id.category_boards);
        Ctg_Batteries = (ImageView) findViewById(R.id.category_batteries);
        Ctg_Capacitors = (ImageView) findViewById(R.id.category_capacitors);

        Ctg_Controllers = (ImageView) findViewById(R.id.category_controllers);
        Ctg_Displays = (ImageView) findViewById(R.id.category_displays);
        Ctg_Ics = (ImageView) findViewById(R.id.category_ics);
        Ctg_Modules = (ImageView) findViewById(R.id.category_modules);

        Ctg_Raspberry = (ImageView) findViewById(R.id.category_raspberrypi);
        Ctg_Sensors = (ImageView) findViewById(R.id.category_sensors);
        Ctg_Wires = (ImageView) findViewById(R.id.category_wires);
        Ctg_Others = (ImageView) findViewById(R.id.category_others);

        Ctg_Aurdinos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(CategoriesHomeActivity.this, AurdinoCategoryActivity.class);
                intent.putExtra("category", "Aurdinos");
                startActivity(intent);
            }
        });


        Ctg_Boards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(CategoriesHomeActivity.this, BoardsCategoryActivity.class);
                intent.putExtra("category", "Boards");
                startActivity(intent);
            }
        });


        Ctg_Batteries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(CategoriesHomeActivity.this, BatteriesCategoryActivity.class);
                intent.putExtra("category", "Batteries");
                startActivity(intent);
            }
        });


        Ctg_Capacitors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(CategoriesHomeActivity.this, CapacitorsCategoryActivity.class);
                intent.putExtra("category", "Capacitors");
                startActivity(intent);
            }
        });


        Ctg_Controllers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(CategoriesHomeActivity.this, ControllerCategoryActivity.class);
                intent.putExtra("category", "Controllers");
                startActivity(intent);
            }
        });


        Ctg_Displays.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(CategoriesHomeActivity.this, DisplayCategoryActivity.class);
                intent.putExtra("category", "Displays");
                startActivity(intent);
            }
        });



        Ctg_Ics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(CategoriesHomeActivity.this, ICCategoryActivity.class);
                intent.putExtra("category", "ICs");
                startActivity(intent);
            }
        });


        Ctg_Modules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(CategoriesHomeActivity.this, ModuleCategoryActivity.class);
                intent.putExtra("category", "Modules");
                startActivity(intent);
            }
        });



        Ctg_Raspberry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(CategoriesHomeActivity.this, RaspberryPiCategoryActivity.class);
                intent.putExtra("category", "Raspberrypi");
                startActivity(intent);
            }
        });


        Ctg_Sensors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(CategoriesHomeActivity.this, SensorsCategoryActivity.class);
                intent.putExtra("category", "Sensors");
                startActivity(intent);
            }
        });


        Ctg_Wires.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(CategoriesHomeActivity.this, WireCategoryActivity.class);
                intent.putExtra("category", "Wires");
                startActivity(intent);
            }
        });


        Ctg_Others.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(CategoriesHomeActivity.this, OthersCategoryActivity.class);
                intent.putExtra("category", "Others");
                startActivity(intent);
            }
        });
    }
}
