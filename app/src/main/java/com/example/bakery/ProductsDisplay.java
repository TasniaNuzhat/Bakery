package com.example.bakery;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;

public class ProductsDisplay extends AppCompatActivity {
    private ListView listViewProducts;
    private DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_display);

        listViewProducts = findViewById(R.id.list_view_User_Page_products);

        databaseHelper = new DatabaseHelper(this);

        displayProducts();

    }


    @Override
    protected void onResume() {
        super.onResume();
        // Refresh the displayed products
        displayProducts();
    }


    private void displayProducts() {
        Cursor cursor = databaseHelper.getAllProducts();
        ProductAdapter adapter = new ProductAdapter(this, cursor, 0);
        listViewProducts.setAdapter(adapter);
    }

}


