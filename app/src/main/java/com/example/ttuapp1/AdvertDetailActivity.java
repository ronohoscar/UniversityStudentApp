package com.example.ttuapp1;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class AdvertDetailActivity extends AppCompatActivity {
    TextView title , description;
    ImageView imageView;
    private DatabaseReference advert;
    String advert_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advert_detail);

        title=findViewById(R.id.texttitlead);
        description=findViewById(R.id.textdescad);
        imageView=findViewById(R.id.imageViewad);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("ADVERT VIEW");

        //set back button
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        advert= FirebaseDatabase.getInstance().getReference().child("data");

        //get data from intent
        advert_id=getIntent().getStringExtra("advert_id");

        String stitle = getIntent().getStringExtra("dtitleto");

        //set attributes
        title.setText(stitle);


        //call for image retrieval
        retrieveAdvertDetails();
        //load image into view

    }

    private void retrieveAdvertDetails() {
        advert.child(advert_id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists() && (dataSnapshot.hasChild("image"))){
                    String sdescription = dataSnapshot.child("description").getValue().toString();
                    description.setText(sdescription);
                    String simage=dataSnapshot.child("image").getValue().toString();
                    Picasso.get().load(simage).resize(405,0).into(imageView);


                }else {
                    //TODO
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public boolean onSupportNavigateUp(){
        onBackPressed();
        return true;
}
}
