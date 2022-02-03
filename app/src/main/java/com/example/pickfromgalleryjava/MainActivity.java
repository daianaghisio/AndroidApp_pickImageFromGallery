package com.example.pickfromgalleryjava;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //UI Views
    private ImageView profileImage;
    private Button intentButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    //init UI Views (better to use ViewBinding instead of findViewById)
        profileImage = findViewById(R.id.profileImage);
        intentButton = findViewById(R.id.intentButton);

    //handle click, launch intent to pick image from gallery
   intentButton.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v) {
           //intent to pick image from gallery
           Intent intent = new Intent(Intent.ACTION_PICK);
           //set type
           intent.setType("image/*");
           galleryActivityResultLauncher.launch(intent);
       }
   });

    }

    private ActivityResultLauncher<Intent> galleryActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
            @Override
                public void onActivityResult(ActivityResult result){
                //here we will handle the result of our intent
                if (result.getResultCode()== Activity.RESULT_OK){
                    //image picked
                    //get uri of image
                    Intent data = result.getData();
                    Uri imageUri = data.getData();

                    profileImage.setImageURI(imageUri);
                } else {
                    //cancelled
                    Toast.makeText(MainActivity.this, "cancelled...", Toast.LENGTH_SHORT).show();
                }
            }
            }
    );
}