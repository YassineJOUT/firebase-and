package com.oumaima.firebasetest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         final TextView txt =  findViewById(R.id.temp);
         final TextView txt1 =  findViewById(R.id.hum);
        FirebaseDatabase firebase = FirebaseDatabase.getInstance();

        DatabaseReference firebaseRootRef = firebase.getReference();
        DatabaseReference itemRef= firebaseRootRef.child("data");

        final ArrayList itemList=new ArrayList<>();

        ValueEventListener valueEventListener =new ValueEventListener()
        {
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                Log.d("TAG","inside ... ");

                for(DataSnapshot ds : dataSnapshot.getChildren())
                {

                    String itemName=ds.getValue(String.class);
                    itemList.add(itemName);
                    Log.d("Vlue",itemName);
                    if(ds.getKey().equals("humidity"))
                        txt1.setText(itemName);
                    if(ds.getKey().equals("temp"))
                        txt.setText(itemName);

                }
              //  Log.d("values",dataSnapshot.getValue(HashMap.class));
                Log.d("TAG",itemList.toString());
            }
            public void onCancelled(DatabaseError databaseError)
            {
                Log.d("TAG",databaseError.getMessage());
            }

        };
         itemRef.addValueEventListener(valueEventListener);
        Log.d("TAG","after attaching the listener ");

    }
}


