package com.example.smartcontrol;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class configActivity extends AppCompatActivity {
    private FirebaseDatabase database;
    private ValueEventListener valueEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);
        database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference().child("Aparelho");
        Button button = findViewById(R.id.buttoset);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        reference.child("reset").setValue(true);
                        reference.child("CodigoIRoff").setValue(0);
                        reference.child("CodigoIRon").setValue(0);
                        reference.child("Estado").setValue(0);
                        reference.child("Tempodeuso").setValue(0);
                        reference.child("Valorapagar").setValue(0);
                        reference.child("kwh").setValue(0);
                        reference.child("Taxaenergia").setValue(0);
                        Handler handle = new Handler();
                        handle.postDelayed(new Runnable() {
                            @Override public void run() {
                                Proximatela();
                            }
                        }, 2000);

                    }

                });

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