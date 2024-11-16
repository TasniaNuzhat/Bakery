package com.example.bakery;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class InsertProductActivity extends AppCompatActivity {
    private static final int REQUEST_IMAGE_PICK = 1;
    private EditText productNameEditText;
    private EditText productPriceEditText;

    private ImageView selectedImageView;
    private Button selectImageButton;
    private Button insertProductButton;
    private DatabaseHelper databaseHelper;
    private byte[] imageByteArray;
    private Bitmap selectedImageBitmap;

    private ActivityResultLauncher<Intent> imagePickerLauncher;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_product);

        productNameEditText = findViewById(R.id.et_product_name);
        productPriceEditText = findViewById(R.id.et_product_price);
        selectedImageView = findViewById(R.id.iv_selected_image);
        selectImageButton = findViewById(R.id.btn_select_image);
        insertProductButton = findViewById(R.id.btn_insert_product);




        databaseHelper = new DatabaseHelper(this);




        imagePickerLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                Uri imageUri = result.getData().getData();
                try {
                    selectedImageBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                    selectedImageView.setImageBitmap(selectedImageBitmap);
                    imageByteArray = bitmapToByteArray(selectedImageBitmap); // Convert the bitmap to byte array
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


        selectImageButton.setOnClickListener(view -> showImageSelectionDialog());

        insertProductButton.setOnClickListener(view -> insertProduct());
    }

    private void showImageSelectionDialog() {
        Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        pickIntent.setType("image/*");
        imagePickerLauncher.launch(pickIntent);
    }

    private byte[] bitmapToByteArray(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    private void insertProduct() {
        String name = productNameEditText.getText().toString();
        String priceString = productPriceEditText.getText().toString();


        if (name.isEmpty() || priceString.isEmpty() ||imageByteArray == null) {
            Toast.makeText(this, "Please fill all the fields and select an image", Toast.LENGTH_SHORT).show();
            return;
        }


        double price = Double.parseDouble(priceString);



        boolean isInsertedProduct = databaseHelper.insertProduct(name, price,imageByteArray);
       if (isInsertedProduct){
           Toast.makeText(this, "Product inserted successfully!", Toast.LENGTH_SHORT).show();
       }
       else{
           Toast.makeText(this, "Product not inserted!", Toast.LENGTH_SHORT).show();


       }


    }
}
