package com.techmorphosis.sundaram.virajnewsamplework;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.github.barteksc.pdfviewer.PDFView;

public class OpenPdfActivity extends AppCompatActivity {

    private Button btn;
    private PDFView pdfView;
    private ImageView backbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_pdf);

        btn=findViewById(R.id.button);
        pdfView=findViewById(R.id.pdf);
        backbtn=findViewById(R.id.backbtn);


        btnClickMethod();
    }

    private void btnClickMethod() {

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn.setVisibility(View.INVISIBLE);
                pdfView.setVisibility(View.VISIBLE);
                backbtn.setVisibility(View.VISIBLE);

                pdfView.fromAsset("viraj.pdf")
                        .enableSwipe(true)
                        .load();


            }
        });


        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),OpenPdfActivity.class));
                btn.setVisibility(View.VISIBLE);
                pdfView.setVisibility(View.INVISIBLE);
                backbtn.setVisibility(View.INVISIBLE);


            }
        });
    }


}
