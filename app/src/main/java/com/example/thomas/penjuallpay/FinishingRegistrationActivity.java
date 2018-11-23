package com.example.thomas.penjuallpay;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thomas.penjuallpay.Model.AuthUser;
import com.example.thomas.penjuallpay.Model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

import id.zelory.compressor.Compressor;


public class FinishingRegistrationActivity extends AppCompatActivity {

    public ImageButton btnRegis2ChangeIcon;
    public ImageView imgRegis2StoreIcon;
    public TextView txtRegis2StoreName;
    public Button btnFinsihingDone;

    private ProgressDialog progressDialog;

    private Uri file;
    private FirebaseAuth mAuth;
    private FirebaseUser curUser;
    private StorageReference mStorageRef;

    private  int PICK_IMAGE = 100;
    Uri imageUri;
    byte[] gambar;
    private static long back_pressed ;

    FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finishing_registration);

        mAuth = FirebaseAuth.getInstance();
        curUser = mAuth.getCurrentUser();

        progressDialog = new ProgressDialog(this);

        btnRegis2ChangeIcon = (ImageButton) findViewById(R.id.btnRegis2ChangeIcon);
        imgRegis2StoreIcon = (ImageView) findViewById(R.id.imgRegis2StoreIcon);
        txtRegis2StoreName = (TextView) findViewById(R.id.txtRegis2StoreName);
        btnFinsihingDone = (Button) findViewById(R.id.btnFinsihingDone);

        if(curUser != null){
            txtRegis2StoreName.setText(curUser.getDisplayName());
            //mStorageRef = FirebaseStorage.getInstance().getReference("profilepics/"+curUser.getPhoneNumber()+".jpg");


        }
        btnRegis2ChangeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        });
        btnFinsihingDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.setMessage("Please Wait");
                progressDialog.show();
                uploadImageToFirebaseStorage();
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
            gambar = convertImageToByte(imageUri);
            Bitmap bitmap = BitmapFactory.decodeByteArray(gambar, 0, gambar.length);
            imgRegis2StoreIcon.setImageBitmap(bitmap);
        }
    }

    public byte[] convertImageToByte(Uri uri){
        byte[] data = null;
        try {
            ContentResolver cr = getBaseContext().getContentResolver();
            InputStream inputStream = cr.openInputStream(uri);
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 50, baos);
            data = baos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return data;
    }

    private void uploadImageToFirebaseStorage() {
        StorageReference profile = FirebaseStorage.getInstance().getReference("/profilepics/"+currentFirebaseUser.getUid()+".jpg");
        profile.putBytes(gambar).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle unsuccessful uploads
                Toast.makeText(FinishingRegistrationActivity.this,exception.getMessage(), Toast.LENGTH_LONG).show();
                progressDialog.dismiss();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
                // ...
                Toast.makeText(FinishingRegistrationActivity.this,"Success Upload Image", Toast.LENGTH_LONG).show();
                UserProfileChangeRequest profile = new UserProfileChangeRequest.Builder()
                        .setPhotoUri(imageUri)
                        .build();
                curUser.updateProfile(profile)
                        .addOnCompleteListener(new OnCompleteListener<Void>(){

                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    progressDialog.dismiss();
                                    Toast.makeText(FinishingRegistrationActivity.this,"Profile Updated",Toast.LENGTH_SHORT).show();
//
                                    Intent intent = new Intent(FinishingRegistrationActivity.this, MainActivity.class);
                                    startActivity(intent);
                                }
                                else {
                                    progressDialog.dismiss();
                                    Toast.makeText(FinishingRegistrationActivity.this,"Error",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                //progressDialog.dismiss();
            }
        });
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
