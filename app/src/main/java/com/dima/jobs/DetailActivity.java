package com.dima.jobs;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DetailActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //getting elements from xml

        Toolbar toolbarDetail = findViewById(R.id.toolbar_detail);
        ImageView companyLogo = findViewById(R.id.companyLogo);
        //    TextView company = findViewById(R.id.company);
        TextView title = findViewById(R.id.title);
        TextView location = findViewById(R.id.location);
        TextView type = findViewById(R.id.type);
        TextView description = findViewById(R.id.description);
        TextView createdAt = findViewById(R.id.created_at);
        TextView howToApply = findViewById(R.id.how_to_apply);
        TextView url = findViewById(R.id.url);
        final TextView companyUrl = findViewById(R.id.company_url);

        //gettin the element of job from parcel
        final Job job = (Job) getIntent().getExtras().getSerializable("job");

        //setting properties to views
        Picasso.with(companyLogo.getContext()).load(job.getCompanyLogo()).into(companyLogo);
        //     company.setText(job.getCompany());
        title.setText(job.getTitle());
        location.setText(job.getLocation());
        type.setText(job.getType());
        description.setText(Html.fromHtml(job.getDescription()));

        // converting date output of createdAt
        String inputPattern = "EEE MMM dd HH:mm:ss z yyyy"; //Wed Jun 03 19:36:58 UTC 2020
        String outputPattern = "dd.MM.yyyy";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern, Locale.getDefault());
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern, Locale.getDefault());
        Date date = null;
        String str = null;
        try {
            date = inputFormat.parse(job.getCreatedAt());
            str = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        createdAt.setText("Created at: " + str);

        toolbarDetail.setSubtitle(job.getTitle());
        toolbarDetail.setTitle(job.getCompany());
        toolbarDetail.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);
        toolbarDetail.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        howToApply.setText(Html.fromHtml(job.getHowToApply()));
        url.setText("See more: " + Html.fromHtml(job.getUrl()));
        companyUrl.setText("Website: " + job.getCompanyUrl());
        companyUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(job.getCompanyUrl()));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setPackage(null);
                startActivity(intent);
            }
        });

        howToApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(job.getCompanyUrl()));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setPackage(null);
                startActivity(intent);
            }
        });

        url.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(job.getCompanyUrl()));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setPackage(null);
                startActivity(intent);
            }
        });

    }


}
