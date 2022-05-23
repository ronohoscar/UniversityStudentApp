package com.example.ttuapp1;


import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

public class notificationsViewHolder extends RecyclerView.ViewHolder {
    TextView vtittle,vdescription;
    ImageView vimage;
    View view;
    TextView mName;
    //TextView mLink;
    Button mDownload;
    public notificationsViewHolder(@NonNull View itemView) {
        super(itemView);
        view=itemView;
        mName=itemView.findViewById(R.id.name); //no need to display download link
        // mLink=itemView.findViewById(R.id.link);
        mDownload=itemView.findViewById(R.id.down);



    }
    public void setDetails(Context cont, String tittle, String image, String description){
        vtittle=view.findViewById(R.id.texttitle);
        //vdescription=view.findViewById(R.id.textdesc);
        vimage=view.findViewById(R.id.imageView);
        vtittle.setText(tittle);
        // vdescription.setText(description);
        Picasso.get().load(image).resize(405,0).into(vimage);
    }
}
