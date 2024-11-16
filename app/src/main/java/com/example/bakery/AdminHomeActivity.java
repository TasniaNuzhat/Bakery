package com.example.bakery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class AdminHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        Button btnInsertProduct=findViewById(R.id.btn_insert_product);
        Button btnViewProduct=findViewById(R.id.btn_view_product);
        Button btnUpdateProduct=findViewById(R.id.btn_update_product);
        Button btnDeleteProduct=findViewById(R.id.btn_delete_product);

        btnInsertProduct.setOnClickListener(v->{
            Intent intent=new Intent(AdminHomeActivity.this, InsertProductActivity.class);
        startActivity(intent);
        });

        btnViewProduct.setOnClickListener(v->{
            Intent intent=new Intent(AdminHomeActivity.this, ViewProductActivity.class);
            startActivity(intent);
        });
        btnUpdateProduct.setOnClickListener(v->{
            Intent intent=new Intent(AdminHomeActivity.this, UpdateProductActivity.class);
            startActivity(intent);
        });
        btnDeleteProduct.setOnClickListener(v->{
            Intent intent=new Intent(AdminHomeActivity.this, DeleteProductActivity.class);
            startActivity(intent);
        });
    }
}