package com.example.ttuapp1;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    FirebaseAuth firebaseAuth;
    TextView headerEmail;
    TextView headerUsername;
    ImageView imageViewHeader;
    NavigationView navigationView;

    public String username,headerImageUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseAuth = FirebaseAuth.getInstance();

        //headerview call
        navigationView = findViewById(R.id.navView);
        View headerView = navigationView.getHeaderView(0);
        headerEmail = headerView.findViewById(R.id.headerViewEmail);
        headerUsername = headerView.findViewById(R.id.headerViewUsername);
        imageViewHeader = headerView.findViewById(R.id.imageProfileHeader);

        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout=findViewById(R.id.drawer_layout);

        //display user profile in header
        profileData();

        FirebaseUser user1 = firebaseAuth.getCurrentUser();
        if(user1==null){
            Intent intent = new Intent(MainActivity.this, Login.class);
            MainActivity.this.startActivity(intent);
            finish();
        }

        NavigationView navigationView=findViewById(R.id.navView);
        navigationView.setNavigationItemSelectedListener(this);


        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.colorWhite));
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState==null){
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,new home()).commit();

            navigationView.setCheckedItem(R.id.home);
        }

    }

    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }
    //display user profile in header
    public void profileData(){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference().child(firebaseAuth.getCurrentUser().getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                assert user != null;
                username=user.getEmail().toUpperCase();
                headerEmail.setText(user.getEmail());
                headerUsername.setText(user.getUsername());
                headerImageUrl =user.getImageUrl();
               if (!headerImageUrl.isEmpty()){
                    Picasso.get().load(headerImageUrl).into(imageViewHeader);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MainActivity.this,databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
       int id=item.getItemId();
       if (id==R.id.logout){
           firebaseAuth.getInstance().signOut();
           Toast.makeText(this, "Successfully logged out", Toast.LENGTH_SHORT).show();
           Intent intent=new Intent(MainActivity.this,Login.class);
           startActivity(intent);
           finish();
       }
       return true;
    }
    //side menu items
    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch(menuItem.getItemId()){
            case R.id.home:
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,new home()).commit();
                break;
            case R.id.portal:
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,new portal()).commit();
                break;
            case R.id.departments:
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,new departments()).commit();
                break;
            case R.id.notifications:
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,new notifications()).commit();
                break;
            case R.id.chatroom:
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,new chatroom()).commit();
                break;
            case R.id.ads:
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,new adverts()).commit();
                break;
            case R.id.report_corruption:
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,new ReportCorruption()).commit();
                break;
            case R.id.profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,new Profile()).commit();
                break;
            case R.id.about_app:
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,new AboutApp()).commit();
                break;
            case R.id.rate_app:
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,new RateApp()).commit();
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + menuItem.getItemId());
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
