package com.example.maciej.bootcampbeer;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


public class AddBeerActivity extends ActionBarActivity {

    private EditText addBeerName;
    private EditText addBeerDescription;
    private ImageView addBeerPhoto;
    private Button addBeerButton;
    private String picturePath;
    private static int RESULT_LOAD_IMAGE = 1;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.add_beer);

        addBeerName = (EditText) findViewById(R.id.new_beer_name);
        addBeerDescription = (EditText) findViewById(R.id.new_beer_description);
        addBeerPhoto = (ImageView) findViewById(R.id.new_beer_photo);
        addBeerButton = (Button) findViewById(R.id.new_beer_button);
        addBeerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };
            Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            picturePath = cursor.getString(columnIndex);
            cursor.close();
            addBeerPhoto.setImageBitmap(BitmapFactory.decodeFile(picturePath));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_beer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.add_beer) {
            saveBeer();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void saveBeer() {
        String addBeerNameString = addBeerName.getText().toString();
        String addBeerDescriptionString = addBeerDescription.getText().toString();

        if (!addBeerNameString.isEmpty() && !addBeerDescriptionString.isEmpty()) {
            Beer beer = new Beer(addBeerNameString, addBeerDescriptionString, picturePath);

            BeerDatabase beerDb = new BeerDatabase(this);
            beerDb.addBeer(beer);

            Toast.makeText(this, "Piwo " + beer.getName() + " zapisane", Toast.LENGTH_SHORT).show();

            finish();
        }
        else Toast.makeText(this, "Pola muszą być wypełnione", Toast.LENGTH_SHORT).show();
    }
}
