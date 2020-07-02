package com.dima.jobs;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DetailActivity extends AppCompatActivity {

    Job job;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        job = (Job) getIntent().getExtras().getSerializable("job");    //Getting job data from parcel
        setContentView(R.layout.activity_detail);
        initToolbar();
        initUI();
    }

    private void initToolbar() {
        Toolbar toolbarDetail = findViewById(R.id.toolbar_detail);
        toolbarDetail.setSubtitle(job.getTitle());
        toolbarDetail.setTitle(job.getCompany());
        toolbarDetail.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initUI() {
        ImageView companyLogo = findViewById(R.id.companyLogo);
        Picasso.with(companyLogo.getContext()).load(job.getCompanyLogo()).into(companyLogo);
        TextView title = findViewById(R.id.title);
        title.setText(job.getTitle());
        TextView location = findViewById(R.id.location);
        location.setText(job.getLocation());
        TextView type = findViewById(R.id.type);
        type.setText(job.getType());
        TextView description = findViewById(R.id.description);
        description.setText(Html.fromHtml(job.getDescription()));
        TextView createdAt = findViewById(R.id.created_at);
        //converting date for createdAt
        SimpleDateFormat inputFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.getDefault());
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        Date receivedDate;
        String resultString = null;
        try {
            receivedDate = inputFormat.parse(job.getCreatedAt());
            resultString = outputFormat.format(receivedDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        createdAt.setText("Created at: " + resultString);
        TextView howToApply = findViewById(R.id.how_to_apply);
        howToApply.setText(Html.fromHtml(job.getHowToApply()));
        howToApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickHelper();
            }
        });
        TextView url = findViewById(R.id.url);
        url.setText("See more: " + Html.fromHtml(job.getUrl()));
        url.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickHelper();
            }
        });
        final TextView companyUrl = findViewById(R.id.company_url);
        companyUrl.setText("Website: " + job.getCompanyUrl());
        companyUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickHelper();
            }
        });
    }

    private void onClickHelper() {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(job.getCompanyUrl()));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setPackage(null);
        startActivity(intent);
    }
}
