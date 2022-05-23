package com.example.ttuapp1;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.Calendar;

import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;

public class AboutApp extends Fragment {
  MainActivity mainActivity;


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {

Toolbar toolbar = getActivity().findViewById(R.id.toolbar);
toolbar.setTitle("About App");

    Element adsElement = new Element();
    adsElement.setTitle("Taita Taveta University");
    return new AboutPage(getContext())
            .isRTL(false)
            .addItem(new Element().setTitle("Version 1.0"))
            .setDescription(getString(R.string.about))
            .addItem(adsElement)
            .addGroup("Connect with us")
            .addEmail("info@ttu.ac.ke")
            .addWebsite("https://ttu.ac.ke/")
            .addFacebook("Theonlymininguniversityinkenya")
            .addTwitter("taitatavetauni")
            .addInstagram("taitatavetauniversity")
            .addItem(getCopyRightsElement())
            .create();

  }

  Element getCopyRightsElement() {
    Element copyRightsElement = new Element();
    final String copyrights = String.format(getString(R.string.copy_right), Calendar.getInstance().get(Calendar.YEAR));
    copyRightsElement.setTitle(copyrights);
    copyRightsElement.setIconDrawable(R.drawable.about_icon_copy_right);
    copyRightsElement.setAutoApplyIconTint(true);
    copyRightsElement.setIconNightTint(android.R.color.white);
    copyRightsElement.setGravity(Gravity.CENTER);
    copyRightsElement.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Toast.makeText(getContext(), copyrights, Toast.LENGTH_SHORT).show();
      }
    });
    return copyRightsElement;
  }
}