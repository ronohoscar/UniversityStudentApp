package com.example.ttuapp1;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;


public class portal extends Fragment {
    MainActivity mainActivity;
    WebView portalttu;
    TextView textView;
    ImageView imageView;
    String ShowOrHideWebViewInitialUse = "show";
    ConstraintLayout constraintLayout;
    public static int Splash_Time=5000;





    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

       View v=inflater.inflate(R.layout.portal_fragment,container,false);

        //toolbar
        Toolbar toolbar=getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle("Portal");


       portalttu=v.findViewById(R.id.webView);
      imageView=v.findViewById(R.id.imageView);
      textView=v.findViewById(R.id.textViewLoad);

        portalttu.setWebViewClient(new CustomWebViewClient());
        portalttu.loadUrl("http://portal.ttuc.ac.ke");
          WebSettings webSettings=portalttu.getSettings();
        webSettings.setUserAgentString("Mozilla/5.0 (X11; U; Linux i686; en-US; rv:1.9.0.4) Gecko/20100101 Firefox/4.0");
        webSettings.setJavaScriptEnabled(true);

        return v;

    }
    private class CustomWebViewClient extends WebViewClient {

        @Override

        public void onPageStarted(WebView webview, String url, Bitmap favicon) {

            // only make it invisible the FIRST time the app is run
            if (ShowOrHideWebViewInitialUse.equals("show")) {
                webview.setVisibility(portalttu.INVISIBLE);

            }
        }

        @Override



        public void onPageFinished(final WebView view, final String url) {

        new Handler().postDelayed(new Runnable() {


@Override
public void run() {
            ShowOrHideWebViewInitialUse = "hide";
            imageView.setVisibility(View.GONE);
            textView.setVisibility(View.GONE);
            view.setVisibility(portalttu.VISIBLE);

}
        },Splash_Time);
            super.onPageFinished(view, url);
        }
}
}