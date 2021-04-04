package com.example.smartcontrol;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class splashAcitivty extends AppCompatActivity {
    ImageView splashfundo, imageView,imageView2;
    LottieAnimationView lottieAnimationView;
    ConstraintLayout layout;
private FirebaseDatabase database;
private ValueEventListener valueEventListener;
    int codigoiroff;
    int codigoiron;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_acitivty);
layout = (ConstraintLayout)findViewById(R.id.layout);
        AnimationDrawable animationDrawable = (AnimationDrawable)layout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(3000);
        animationDrawable.start();
database = FirebaseDatabase.getInstance();
       ouvinte1();

    }

    private void ouvinte1(){
        DatabaseReference reference = database.getReference().child("Aparelho");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                 codigoiroff = snapshot.child("CodigoIRoff").getValue(int.class);
                codigoiron = snapshot.child("CodigoIRon").getValue(int.class);
                 if(codigoiroff != 0 && codigoiron !=0){
                     Handler handle = new Handler();
                     handle.postDelayed(new Runnable() {
                         @Override public void run() {
                             Proximatela();
                         }
                     }, 4000);
                 }else if(codigoiroff == 0 && codigoiron ==0){
                    GetIr();
                 }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private void Proximatela() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }
    private void GetIr(){
        Intent intent = new Intent(this, BuscaIRActivity.class);
        startActivity(intent);

    }
    }

