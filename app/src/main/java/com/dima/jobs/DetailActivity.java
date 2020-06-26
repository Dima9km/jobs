package com.dima.jobs;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ImageView companyLogo = findViewById(R.id.companyLogo);
        TextView company = findViewById(R.id.company);
        TextView title = findViewById(R.id.title);
        TextView location = findViewById(R.id.location);
        TextView type = findViewById(R.id.type);
        TextView description = findViewById(R.id.description);
        TextView createdAt = findViewById(R.id.created_at);
        TextView howToApply = findViewById(R.id.how_to_apply);
        TextView url = findViewById(R.id.url);
        final TextView companyUrl = findViewById(R.id.company_url);
//for nt

        final Job job = (Job) getIntent().getExtras().getSerializable("job");
        Picasso.with(companyLogo.getContext()).load(job.getCompanyLogo()).into(companyLogo);
        company.setText(job.getCompany());
        title.setText(job.getTitle());
        location.setText(job.getLocation());
        type.setText(job.getType());
        description.setText(Html.fromHtml(job.getDescription()));

        String inputPattern = "EEE MMM dd HH:mm:ss z yyyy"; //Wed Jun 03 19:36:58 UTC 2020
        String outputPattern = "dd.MMM.yyyy";
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

        createdAt.setText(str);


        howToApply.setText(Html.fromHtml(job.getHowToApply()));
        url.setText(Html.fromHtml(job.getUrl()));
        companyUrl.setText(job.getCompanyUrl());
        companyUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(job.getCompanyUrl()));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setPackage(null);
                companyUrl.getContext().startActivity(intent);
            }
        });

        howToApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(job.getCompanyUrl()));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setPackage(null);
                companyUrl.getContext().startActivity(intent);
            }
        });

        url.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(job.getCompanyUrl()));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setPackage(null);
                companyUrl.getContext().startActivity(intent);
            }
        });

    }


}
