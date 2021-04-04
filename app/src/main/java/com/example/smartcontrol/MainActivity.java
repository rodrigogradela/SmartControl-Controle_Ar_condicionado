package com.example.smartcontrol;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Objects;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {
    private FirebaseDatabase database;
    private ValueEventListener valueEventListener;
    private FirebaseDatabase database2;
    private ValueEventListener valueEventListener2;

int tempambiente;
int tempcontrole;
int tempcontrolemenos;
int novatempcontrole;
int humidadeambiente;
String tempconvertida;
String humidadeconvertida;
String tempcontroletexto;
String tempmenostexto;
    Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Brazil/East"));
    int dia;
    int minuto;
    int segundo;
    long tempoInicial;
    long tempoFinal;
    long tempoemmili;
    float tempodeuso;
    float contadiaria;
    float contadorkwh;
    String converthwh;
     float numero1 =3600000;
     float numero2 =30;
     float pegakwdia;
    float min;

private boolean switchon = false ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        database = FirebaseDatabase.getInstance();

        LottieAnimationView btnconfig = findViewById(R.id.lottiegear);
        DatabaseReference reference = database.getReference().child("Aparelho");


        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                tempambiente = snapshot.child("TempAmbiente").getValue(int.class);
                humidadeambiente = snapshot.child("Humidade").getValue(int.class);
                tempcontrole = snapshot.child("TempControle").getValue(int.class);
                float pegatempodeuso = snapshot.child("Tempodeuso").getValue(float.class);
                float pegavalor = snapshot.child("Valorapagar").getValue(float.class);

                LottieAnimationView btnon = findViewById(R.id.lottieon);
                LottieAnimationView animagraf = findViewById(R.id.lottiechart);
                LottieAnimationView ar = findViewById(R.id.lottiear);
                LottieAnimationView temp = findViewById(R.id.lottietempo);
                TextView btnmais = findViewById(R.id.btn_mais);
                TextView btnmenos = findViewById(R.id.btn_menos);
                TextView textView2 = findViewById(R.id.texthumidade);
                TextView textView = findViewById(R.id.texttemperatura);
                TextView tempcontroltexto = findViewById(R.id.texttemperatura2);
                TextView textvalor = findViewById(R.id.textvalor);
                TextView textkwvalor = findViewById(R.id.textkwhvalor);
                TextView tempo = findViewById(R.id.texttempo);

                humidadeconvertida = String.valueOf(humidadeambiente);
                textView2.setText(humidadeconvertida);
                tempconvertida= String.valueOf(tempambiente);
                textView.setText(tempconvertida);
                tempcontroletexto = String.valueOf(tempcontrole);
                tempcontroltexto.setText(tempcontroletexto);
                DecimalFormat df = new DecimalFormat("0.000");
                DecimalFormat df3 = new DecimalFormat("0.00");
                DecimalFormat df2 = new DecimalFormat("0");
                String convertempouso = df2.format(pegatempodeuso);
                tempo.setText(convertempouso + " Min") ;
                String convertvalor = df3.format(pegavalor);
                textvalor.setText(convertvalor) ;
                animagraf.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Proximatela();
                    }
                });
                btnon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //botao ta off
                        if(switchon){
                            btnon.setMinAndMaxProgress(0.5f,1.0f);
                            btnon.playAnimation();
                            switchon = false;
                            temp.setMinAndMaxProgress(0.0f,0.0f);
                            temp.playAnimation();
                            reference.child("Estado").setValue(0);
                            ar.setMinAndMaxProgress(0.0f,0.0f);
                            ar.playAnimation();
                            tempoFinal = System.currentTimeMillis();
                            tempoemmili = (tempoFinal-tempoInicial);
                            tempodeuso = tempoemmili/numero1;
                            contadiaria = (float) ((tempodeuso*1*17.1*0.85))/numero2;

                             min = tempodeuso *60;
                            String convertempouso = df2.format(pegatempodeuso);
                            tempo.setText(convertempouso + " Min") ;

                            contadorkwh = (float) (tempodeuso*0.57);
                            converthwh = df.format(pegakwdia);
                            float pegavalor = snapshot.child("Valorapagar").getValue(float.class);
                            reference.child("Valorapagar").setValue(contadiaria + pegavalor);
                            String convertvalor = df3.format(pegavalor);
                            textvalor.setText(convertvalor ) ;


                            reference.child("Tempodeuso").setValue(min + pegatempodeuso );
                            System.out.println("testando --------"+df.format(tempodeuso));
                            System.out.println("testando --------"+df.format(contadiaria));
                            System.out.println("testando --------"+df.format(contadorkwh));

                            textkwvalor.setText(converthwh);

                            grafico();




                        }else{//botao ta on
                            btnon.setMinAndMaxProgress(0.0f,0.5f);
                            btnon.playAnimation();
                            switchon = true;
                          reference.child("Estado").setValue(1);
                            ar.setMinAndMaxProgress(0.0f,0.5f);
                          ar.playAnimation();
                            temp.setMinAndMaxProgress(0.0f,1.0f);
                            temp.playAnimation();
                             dia = calendar.get(Calendar.DAY_OF_MONTH);
                             minuto = calendar.get(Calendar.MINUTE);
                             segundo = calendar.get(Calendar.SECOND);
                            tempoInicial = System.currentTimeMillis();
                            converthwh = df.format(pegakwdia);
                            textkwvalor.setText(converthwh);

                            float pegatempodeuso = snapshot.child("Tempodeuso").getValue(float.class);
                            reference.child("Tempodeuso").setValue(min + +pegatempodeuso);
                            String convertempouso = df2.format(pegatempodeuso);
                            tempo.setText(convertempouso + " Min") ;
                            float pegavalor = snapshot.child("Valorapagar").getValue(float.class);
                            reference.child("Valorapagar").setValue(contadiaria + pegavalor);
                            String convertvalor = df3.format(pegavalor);
                            textvalor.setText(convertvalor ) ;
                            grafico();
                        }

                    }

                });
                btnmais.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        tempcontrole = snapshot.child("TempControle").getValue(int.class);
                        novatempcontrole = tempcontrole+1;
                        tempcontroletexto = String.valueOf(novatempcontrole);
                        tempcontroltexto.setText(tempcontroletexto);
                        reference.child("TempControle").setValue(novatempcontrole);
                    }
                });
                btnmenos.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        tempcontrolemenos = snapshot.child("TempControle").getValue(int.class);
                        novatempcontrole = tempcontrole-1;
                        tempmenostexto = String.valueOf(novatempcontrole);
                        tempcontroltexto.setText(tempmenostexto);
                        reference.child("TempControle").setValue(novatempcontrole);
                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
btnconfig.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        getconfig();
    }
});

    }
    private void Proximatela() {
        Intent intent = new Intent(this, grafAcitivty.class);
        startActivity(intent);

    }

    private void getconfig() {
        Intent intent = new Intent(this, configActivity.class);
        startActivity(intent);

    }
    private void grafico(){
        database2 = FirebaseDatabase.getInstance();
        DatabaseReference reference2 = database2.getReference().child("ConsumoDias");


        reference2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                String convertdia =Integer.toString(dia);
                pegakwdia=snapshot.child(convertdia).getValue(float.class);
                reference2.child(convertdia).setValue(contadorkwh + snapshot.child(convertdia).getValue(float.class) );
                System.out.println("testando --------"+ dia);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}
