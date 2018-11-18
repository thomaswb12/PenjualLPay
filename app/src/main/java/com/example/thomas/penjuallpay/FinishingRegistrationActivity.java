package com.example.thomas.penjuallpay;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class FinishingRegistrationActivity extends AppCompatActivity {

    public ImageButton btnRegis2ChangeIcon;
    public ImageView imgRegis2StoreIcon;
    public TextView txtRegis2StoreName;
    public Button btnFinsihingDone;

    private static final int PICK_IMAGE = 100;
    Uri imageUri;
    private static long back_pressed ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finishing_registration);

        btnRegis2ChangeIcon = (ImageButton) findViewById(R.id.btnRegis2ChangeIcon);
        imgRegis2StoreIcon = (ImageView) findViewById(R.id.imgRegis2StoreIcon);
        txtRegis2StoreName = (TextView) findViewById(R.id.txtRegis2StoreName);
        btnFinsihingDone = (Button) findViewById(R.id.btnFinsihingDone);

        btnRegis2ChangeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        });
    }

    private void openGallery(){
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && requestCode == PICK_IMAGE){
            imageUri = data.getData();
            imgRegis2StoreIcon.setImageURI(imageUri);
        }
    }

    public void doneRegister(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        if (back_pressed + 2000 > System.currentTimeMillis()){
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.addFlags((Intent.FLAG_ACTIVITY_CLEAR_TOP));
            startActivity(intent);
            finish();
        } else{
            Toast.makeText(this, "Press once again to exit",Toast.LENGTH_SHORT).show();
        }
        back_pressed = System.currentTimeMillis();
    }
}
