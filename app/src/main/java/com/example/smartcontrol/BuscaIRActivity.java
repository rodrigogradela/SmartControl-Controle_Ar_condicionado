package com.example.smartcontrol;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.airbnb.lottie.Lottie;
import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class BuscaIRActivity extends AppCompatActivity {

    private FirebaseDatabase database;
    private ValueEventListener valueEventListener;
    int codigoiroff;
    int codigoiron;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busca_i_r);
        database = FirebaseDatabase.getInstance();
       ouvinte1();


    }
    private void ouvinte1(){

        DatabaseReference reference = database.getReference().child("Aparelho");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                codigoiron = snapshot.child("CodigoIRon").getValue(int.class);
                codigoiroff = snapshot.child("CodigoIRoff").getValue(int.class);
                if(codigoiron != 0 ){
                    LottieAnimationView lottieAnimationView = findViewById(R.id.lottie3);
                    lottieAnimationView.setVisibility(1);
                    lottieAnimationView.playAnimation();





                }
                if(codigoiroff != 0 ){
                    LottieAnimationView lottieAnimationView = findViewById(R.id.lottie4);
                    lottieAnimationView.setVisibility(1);
                    lottieAnimationView.playAnimation();
                    Handler handle = new Handler();
                    handle.postDelayed(new Runnable() {
                        @Override public void run() {
                            Proximatela();
                        }
                    }, 2000);



                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private void Proximatela() {
        Intent intent = new Intent(this, splashAcitivty.class);
        startActivity(intent);
        Runtime.getRuntime().exit(0);
    }
}
