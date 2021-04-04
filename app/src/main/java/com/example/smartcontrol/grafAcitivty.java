package com.example.smartcontrol;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.airbnb.lottie.LottieAnimationView;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class grafAcitivty extends AppCompatActivity  {
    private static final String TAG = "grafAcitivty";
    private LineChart mChart;
    private FirebaseDatabase database;
    private ValueEventListener valueEventListener;

    Float n1;
    Float n2;
    Float n3;
    Float n4;
    Float n5;
    Float n6;
    Float n7;
    Float n8;
    Float n9;
    Float n10;
    Float n11;
    Float n12;
    Float n13;
    Float n14;
    Float n15;
    Float n16;
    Float n17;
    Float n18;
    Float n19;
    Float n20;
    Float n21;
    Float n22;
    Float n23;
    Float n24;
    Float n25;
    Float n26;
    Float n27;
    Float n28;
    Float n29;
    Float n30;
    Float n31;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graf_acitivty);
        database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference().child("ConsumoDias");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                n1 = snapshot.child("1").getValue(float.class);
                n2 = snapshot.child("2").getValue(float.class);
                n3 = snapshot.child("3").getValue(float.class);
                n4 = snapshot.child("4").getValue(float.class);
                n5 = snapshot.child("5").getValue(float.class);
                n6 = snapshot.child("6").getValue(float.class);
                n7 = snapshot.child("7").getValue(float.class);
                n8 = snapshot.child("8").getValue(float.class);
                n9 = snapshot.child("9").getValue(float.class);
                n10 = snapshot.child("10").getValue(float.class);
                n11 = snapshot.child("11").getValue(float.class);
                n12 = snapshot.child("12").getValue(float.class);
                n13 = snapshot.child("13").getValue(float.class);
                n14 = snapshot.child("14").getValue(float.class);
                n15 = snapshot.child("15").getValue(float.class);
                n16 = snapshot.child("16").getValue(float.class);
                n17 = snapshot.child("17").getValue(float.class);
                n18 = snapshot.child("18").getValue(float.class);
                n19 = snapshot.child("19").getValue(float.class);
                n20 = snapshot.child("20").getValue(float.class);
                n21 = snapshot.child("21").getValue(float.class);
                n22 = snapshot.child("22").getValue(float.class);
                n23 = snapshot.child("23").getValue(float.class);
                n24 = snapshot.child("24").getValue(float.class);
                n25 = snapshot.child("25").getValue(float.class);
                n26 = snapshot.child("26").getValue(float.class);
                n27 = snapshot.child("27").getValue(float.class);
                n28 = snapshot.child("28").getValue(float.class);
                n29 = snapshot.child("29").getValue(float.class);
                n30 = snapshot.child("30").getValue(float.class);
                n31 = snapshot.child("31").getValue(float.class);
                mChart = (LineChart)findViewById(R.id.linechart);
                // mChart.setOnChartGestureListener(grafAcitivty.this);
                //mChart.setOnChartValueSelectedListener(grafAcitivty.this);



                mChart.setDragEnabled(true);
                mChart.setScaleEnabled(false);
                ArrayList<Entry> yValues = new ArrayList<>();
                yValues.add(new Entry(1,n1));
                yValues.add(new Entry(2,n2));
                yValues.add(new Entry(3,n3));
                yValues.add(new Entry(4,n4));
                yValues.add(new Entry(5,n5));
                yValues.add(new Entry(6,n6));
                yValues.add(new Entry(7,n7));
                yValues.add(new Entry(8,n8));
                yValues.add(new Entry(9,n9));
                yValues.add(new Entry(10,n10));
                yValues.add(new Entry(11,n11));
                yValues.add(new Entry(12,n12));
                yValues.add(new Entry(13,n13));
                yValues.add(new Entry(14,n14));
                yValues.add(new Entry(15,n15));
                yValues.add(new Entry(16,n16));
                yValues.add(new Entry(17,n17));
                yValues.add(new Entry(18,n18));
                yValues.add(new Entry(19,n19));
                yValues.add(new Entry(20,n20));
                yValues.add(new Entry(21,n21));
                yValues.add(new Entry(22,n22));
                yValues.add(new Entry(23,n23));
                yValues.add(new Entry(24,n24));
                yValues.add(new Entry(25,n25));
                yValues.add(new Entry(26,n26));
                yValues.add(new Entry(27,n27));
                yValues.add(new Entry(28,n28));
                yValues.add(new Entry(29,n29));
                yValues.add(new Entry(30,n30));
                yValues.add(new Entry(31,n31));
                LineDataSet set1 = new LineDataSet(yValues, "CONSUMO DIARIO");
                set1.setFillAlpha(1000);
                set1.setColor(Color.RED);
                set1.setLineWidth(3f);
                set1.setValueTextSize(20f);
                set1.setValueTextColor(Color.BLUE);
                ArrayList<ILineDataSet> dataSets = new ArrayList<>();
                dataSets.add(set1);
                LineData data = new LineData(dataSets);
                mChart.setData(data);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



}


}
