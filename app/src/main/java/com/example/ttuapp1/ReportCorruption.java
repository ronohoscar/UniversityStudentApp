package com.example.ttuapp1;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;


public class ReportCorruption extends Fragment {
    MainActivity mainActivity;
    EditText subject,message;
    Button btnreport;
    String subjects,messages;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.report_corruption, container, false);

        //toolbar
        Toolbar toolbar=getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle("Report Corruption");

//find elements from xml
subject = view.findViewById(R.id.EditTextSubject);
message = view.findViewById(R.id.EditTextMessage);
btnreport = view.findViewById(R.id.btnReport);

        subjects=subject.getText().toString();
        messages=message.getText().toString();

message.addTextChangedListener(new TextWatcher() {
    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        if (charSequence.toString().trim().length() > 0) {
            btnreport.setEnabled(true);

        } else {
           btnreport.setEnabled(false);
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {
    }
});


btnreport.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        addReportMessage();
    }
});

        return view;
    }

    public void addReportMessage(){
        String anonymous_user = UUID.randomUUID().toString();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("Repoting Corruption").child(anonymous_user);
        ReportCorruptionHolder reportCorruptionConstructor = new ReportCorruptionHolder(anonymous_user,subject.getText().toString(),message.getText().toString());
        databaseReference.setValue(reportCorruptionConstructor);

    }
}