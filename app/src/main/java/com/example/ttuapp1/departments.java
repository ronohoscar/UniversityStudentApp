package com.example.ttuapp1;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;



public class departments extends Fragment {
  MainActivity mainActivity;
  Button mi,mi2,mi3,mi4,mi5,mi6;
  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View v= inflater.inflate(R.layout.departments_fragment,container,false);

    //toolbar
    Toolbar toolbar=getActivity().findViewById(R.id.toolbar);
    toolbar.setTitle("Departments");

    mi=v.findViewById(R.id.btnmi);
    mi2=v.findViewById(R.id.btnmi2);
    mi3=v.findViewById(R.id.btnmi3);
    mi4=v.findViewById(R.id.btnmi4);
    mi5=v.findViewById(R.id.btnmi5);
    mi6=v.findViewById(R.id.btnmi6);

    clickmi();
    clickmi2();
    clickmi3();
    clickmi4();
    clickmi5();
    clickmi6();
    return v;
  }
  public void clickmi(){
    mi.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        showMessage("Mathematics and Statistics Department","Contact Information\nP. O. BOX 635-80300\nVoi, Kenya\n+254 721 113302\n+254 774 222 064\ninfo@ttu.ac.ke\nDr.Justin Maghanga - Dean SSI\nMr.Abdalla Kombo - Lecturer\nDr. Nicholas Muthama - Lecturer\n");
      }
    });
  }
  public void clickmi2(){
    mi2.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        showMessage("Informatics and Computing Department","Contact Information\nP. O. BOX 635-80300\nVoi, Kenya\n+254 721 113302\n+254 774 222 064\ninfo@ttu.ac.ke\nList of Courses\nMIT - Msc in IT\nMGI - Msc in Geo-Informatics\nMCS - Msc in Computer Systems");
      }
    });
  }
  public void clickmi3(){
    mi3.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        showMessage("Mining and Mineral Process Engineering","Contact Information\nP. O. BOX 635-80300\nVoi, Kenya\n+254 721 113302\n+254 774 222 064\ninfo@ttu.ac.ke\nList of staff\nProf. Kiptanui Too - Proffesor\nMr.Arthur Ndegwa - Senior Lecturer\nDr. Nelifa Ondiaka - Lecturer\n");
      }
    });
  }
  public void clickmi4(){
    mi4.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        showMessage("Business Department","Contact Information\nP. O. BOX 635-80300\nVoi, Kenya\n+254 721 113302\n+254 774 222 064\ninfo@ttu.ac.ke");
      }
    });
  }
  public void clickmi5(){
    mi5.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        showMessage("Education Department"," Contact Information\nP. O. BOX 635-80300\nVoi, Kenya\n+254 721 113302\n+254 774 222 064\ninfo@ttu.ac.ke\nList of Staff in the Department\nProf. Fred Simiyu\nDavid Chikati - Lecturer\nDr. Reuben Nguyo - Lecturer");
      }
    });
  }
  public void clickmi6(){
    mi6.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        showMessage("Agriculture Department","Contact Information\nP. O. BOX 635-80300\nVoi, Kenya\n+254 721 113302\n+254 774 222 064\ninfo@ttu.ac.ke");
      }
    });
  }
  public void showMessage(String departmentName,String departmentDetails){
    AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
    builder.setCancelable(true);
    builder.setTitle(departmentName);
    builder.setMessage(departmentDetails);
    builder.show();

  }
}
