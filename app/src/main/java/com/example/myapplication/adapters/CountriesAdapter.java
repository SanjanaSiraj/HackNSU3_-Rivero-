package com.example.myapplication.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Model.Area;
import com.example.myapplication.R;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class CountriesAdapter extends RecyclerView.Adapter<CountriesAdapter.CountriesViewHolder>{
    private List<Area> areaList;
    public static final String TAG="AllFriendsAdapter";
    private Context context;
    private FirebaseFirestore db;
    //private PreferenceManager preferenceManager;

    public CountriesAdapter(List<Area> areaList, Context context) {
        this.areaList = areaList;
        this.context = context;
        db=FirebaseFirestore.getInstance();
       // preferenceManager=new PreferenceManager(context);
    }

    @NonNull
    @Override
    public CountriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyler_main, parent, false);
        return new CountriesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CountriesViewHolder holder, int position) {
        Area area= areaList.get(position);
        holder.textView1.setText(area.getArea_name());
        holder.textView2.setText(area.getPopulation());
        holder.textView3.setText(area.getErosionRate());
        holder.textView4.setText(area.getLoss());
        holder.textView5.setText(area.getDeadline());
        holder.imageView.setOnClickListener(v->{
            db.collection("areas").document(area.getDocumentId()).delete().addOnSuccessListener(unused -> Toast.makeText(context, "Deleted successfully", Toast.LENGTH_SHORT).show()).addOnFailureListener(e -> {
                Toast.makeText(context, "Unable to delete", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onFailure: "+e.getMessage());
            });
        });
    }

    @Override
    public int getItemCount() {
        return areaList.size();
    }

    class  CountriesViewHolder extends RecyclerView.ViewHolder
    {
        private TextView textView1,textView2,textView3,textView4,textView5;
        private ImageView imageView;

        public CountriesViewHolder(@NonNull View itemView)
        {
            super(itemView);
            imageView=itemView.findViewById(R.id.delete_main);
            textView1= itemView.findViewById(R.id.area_name);
            textView2 = itemView.findViewById(R.id.population_value);
            textView3= itemView.findViewById(R.id.erosion_Rate_value);
            textView4 = itemView.findViewById(R.id.loss_value);
            textView5= itemView.findViewById(R.id.deadline_value);
        }
    }

}
