package com.example.ttuapp1;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hsalf.smilerating.SmileRating;

import static android.content.ContentValues.TAG;


public class RateApp extends Fragment {
    MainActivity mainActivity;
    FirebaseAuth firebaseAuth;
    Button buttonrate;
    String rateappNumber;
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.rate_app, container, false);
buttonrate = view.findViewById(R.id.buttonrateapp);

//toolbar
      Toolbar toolbar=getActivity().findViewById(R.id.toolbar);
      toolbar.setTitle("Rate App");



SmileRating smilerating = view.findViewById(R.id.smile_rating);
smilerating.setOnSmileySelectionListener(new SmileRating.OnSmileySelectionListener() {
    @Override
    public void onSmileySelected(int smiley, boolean reselected) {
        switch(smiley){
            case SmileRating.BAD:
                Log.i(TAG, "BAD");
                break;
            case SmileRating.GOOD:
                Log.i(TAG, "Good");
                break;
                case SmileRating.GREAT:
                Log.i(TAG, "Great");
                break;
                case SmileRating.OKAY:
                Log.i(TAG, "Okay");
                break;case SmileRating.TERRIBLE:
                Log.i(TAG, "Terrible");
                break;
        }
    }
});
smilerating.setOnRatingSelectedListener(new SmileRating.OnRatingSelectedListener() {
    @Override
    public void onRatingSelected(final int level, boolean reselected) {
        rateappNumber = Integer.toString(level);
    }
});

buttonrate.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        rateapp();
    }
});

    return view;

  }
    public void rateapp(){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("App Rating");
      // String email=firebaseAuth.getCurrentUser().getEmail();
        //String uid=firebaseAuth.getCurrentUser().getUid();

        RateAppModel rateAppModel = new RateAppModel(rateappNumber,"email","uid");
        databaseReference.setValue(rateAppModel);
        Toast.makeText(getContext(), "Thanks for rating our App.", Toast.LENGTH_SHORT).show();
    }
}