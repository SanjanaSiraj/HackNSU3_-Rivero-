package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Model.Area;
import com.example.myapplication.adapters.CountriesAdapter;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import pl.droidsonroids.gif.GifImageView;

public class MainActivity extends AppCompatActivity {
    private GifImageView gifImageView;
    private TextView textView,textView2;
    private RecyclerView recyclerView;
    private ImageView button;
    private FirebaseFirestore db;
    private ImageView imageView;
    //private PreferenceManager preferenceManager;
    public static final String TAG="MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        loadUserDetails();
        getAllSavedAreas();
        setListeners();
    }

    private void setListeners() {
        button.setOnClickListener(v->{
            startActivity(new Intent(MainActivity.this,MapActivity.class));
        });
        imageView.setOnClickListener(v->signOut());
    }

    private void signOut() {
        showToast("Signing out...");
        //preferenceManager.clear();
        Intent intent=new Intent(getApplicationContext(),SignInActivity.class);
        startActivity(intent);
        finish();
    }
    private void loadUserDetails(){
       // textView2.setText(preferenceManager.getString(Constants.KEY_NAME));
    }

    private void showToast(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
    private void getAllSavedAreas() {
        db.collection("areas").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if(error!=null){
                    Log.d(TAG, "onEvent: "+error.toString());
                }else{
                    if(value!=null){
                        List<Area> areaList=new ArrayList<>();
                        for(QueryDocumentSnapshot documentSnapshot:value){
                            Area area=documentSnapshot.toObject(Area.class);
                            area.setDocumentId(documentSnapshot.getId());
                            areaList.add(area);
                        }

                        CountriesAdapter adapter=new CountriesAdapter(areaList,getApplicationContext());
                        recyclerView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }
                }
            }
        });

    }

    private void init() {
        //gifImageView=findViewById(R.id.earthGif);
       // textView=findViewById(R.id.savedcountry);
        textView2=findViewById(R.id.username);
        recyclerView=findViewById(R.id.recyclerview);
        button=findViewById(R.id.addRegion);
        imageView=findViewById(R.id.log_out);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        db=FirebaseFirestore.getInstance();
    }
}