package com.helpee.helpee.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.helpee.helpee.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class FaceBookStepActivity extends AppCompatActivity {

    Button btnConfirmRegister;
    CircleImageView imgProfile;
    RelativeLayout rvProfileImage;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_face_book_step);

        btnConfirmRegister = findViewById(R.id.btnConfirmRegister);
        imgProfile = findViewById(R.id.imgProfile);
        rvProfileImage = findViewById(R.id.rvProfileImage);


        rvProfileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });


        btnConfirmRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO Submit Register To Backend

                Intent intent = new Intent(FaceBookStepActivity.this, NationalIdActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imgProfile.setImageBitmap(imageBitmap);
        }
    }
}
