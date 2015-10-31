package com.github.lcokean.fastguidesample;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.github.lcokean.fastguide.GuidePage;
import com.github.lcokean.fastguide.listener.ViewLazyInitializeListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        final GuidePage guidePage = new GuidePage();
        TextView textView1 = new TextView(getApplicationContext());
        textView1.setText("1");
        textView1.setTextSize(50);
        textView1.setGravity(Gravity.CENTER);
        textView1.setBackgroundColor(Color.parseColor("#ff00ddff"));
        guidePage.addPage(textView1);
        TextView textView2 = new TextView(getApplicationContext());
        textView2.setText("2");
        textView2.setTextSize(50);
        textView2.setGravity(Gravity.CENTER);
        textView2.setBackgroundColor(Color.parseColor("#ff00ddff"));
        guidePage.addPage(textView2);
        guidePage.addPage(this, R.layout.activity_main);
        guidePage.setOnViewLazyInitializeListener(new ViewLazyInitializeListener() {
            @Override
            public void onViewLazyInitialize(View view, int position) {
                Toast.makeText(getApplicationContext(), "view in " + position + " is Initialized", Toast.LENGTH_SHORT).show();
            }
        });
        //guidePage.setOrientation(GuidePage.VERTICAL);  // defult is HORIZONTAL
        guidePage.setOnViewSelectedListener(new GuidePage.OnViewSelectedListener() {
            @Override
            public void onViewSelected(View view, int position) {
                if (position != 2) return;
                view.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getApplicationContext(), "onClick", Toast.LENGTH_SHORT).show();
                        guidePage.finish();
                    }
                });
            }
        });
        guidePage.show(this);
    }
}
