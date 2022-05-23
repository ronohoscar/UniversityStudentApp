package com.example.ttuapp1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class adverts extends Fragment {
    MainActivity mainActivity;
    RecyclerView recyclerView;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    ProgressBar AprogressBar;
    TextView textViewAdvertLoading;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.adverts_fragment,container,false);
        recyclerView=view.findViewById(R.id.adverts_recycle);

        AprogressBar=view.findViewById(R.id.progressBarAdverts);
        textViewAdvertLoading=view.findViewById(R.id.textViewNotificationLoading);

        databaseReference=firebaseDatabase.getInstance().getReference("data");

        LinearLayoutManager  linearLayoutManager= new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        //toolbar
        Toolbar toolbar=getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle("Adverts");

        return view;
    }
    @Override
    public void onStart() {

        super.onStart();
       /* ConnectivityManager connectivityManager= (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {*/
        FirebaseRecyclerOptions<AdvertModell> options= new FirebaseRecyclerOptions.Builder<AdvertModell>().setQuery(databaseReference,AdvertModell.class).build();
        FirebaseRecyclerAdapter firebaseRecyclerAdapter= new FirebaseRecyclerAdapter<AdvertModell, notificationsViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final notificationsViewHolder notificationsViewHolder, final int i, @NonNull AdvertModell advertModell) {
                notificationsViewHolder.setDetails(getActivity().getApplicationContext(),advertModell.getTitle(),advertModell.getImage(),advertModell.getDescription());
                notificationsViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        TextView dtitle = v.findViewById(R.id.texttitle);

                        String dtitleto = dtitle.getText().toString();



                        String advert_id = getRef(i).getKey();


                        //pass data to detail activity
                       /* Intent intent = new Intent(getActivity(), AdvertDetailActivity.class);
                        intent.putExtra("advert_id", advert_id);
                        intent.putExtra("dtitleto", dtitleto);
                        startActivity(intent); */

                    }
                });
            }

            @NonNull
            @Override
            public notificationsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
                View view = layoutInflater.inflate(R.layout.row_elements, parent, false);

                AprogressBar.setVisibility(View.GONE);
                textViewAdvertLoading.setVisibility(View.GONE);
                return new notificationsViewHolder(view);
            }

        };
        firebaseRecyclerAdapter.startListening();
        recyclerView.setAdapter(firebaseRecyclerAdapter);

        /*}else{
            Intent intent=new Intent(getActivity(),NoInternetActivity.class);
            startActivity(intent);
            getActivity().finish();
                        } */
    }
}
