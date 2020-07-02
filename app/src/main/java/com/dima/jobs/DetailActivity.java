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
import java.util.Objects;

@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public class DetailActivity extends AppCompatActivity {
    Job job = (Job) Objects.requireNonNull(getIntent().getExtras()).getSerializable("job");    //Getting job data from parcel
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        // ---------------------- companyLogo
        ImageView companyLogo = findViewById(R.id.companyLogo);
        Picasso.with(companyLogo.getContext()).load(job.getCompanyLogo()).into(companyLogo);
        // ---------------------- title
        TextView title = findViewById(R.id.title);
        title.setText(job.getTitle());
        // ---------------------- location
        TextView location = findViewById(R.id.location);
        location.setText(job.getLocation());
        //----------------------type
        TextView type = findViewById(R.id.type);
        type.setText(job.getType());
        //----------------------description
        TextView description = findViewById(R.id.description);
        description.setText(Html.fromHtml(job.getDescription()));
        //---------------------- createdAt
        TextView createdAt = findViewById(R.id.created_at);
        String inputPattern = "EEE MMM dd HH:mm:ss z yyyy"; //Wed Jun 03 19:36:58 UTC 2020
        String outputPattern = "dd.MM.yyyy";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern, Locale.getDefault());
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern, Locale.getDefault());
        Date date;
        String str = null;
        try {
            date = inputFormat.parse(job.getCreatedAt());
            str = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        createdAt.setText("Created at: " + str);
        //----------------------howtoApply
        TextView howToApply = findViewById(R.id.how_to_apply);
        howToApply.setText(Html.fromHtml(job.getHowToApply()));
        howToApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(job.getCompanyUrl()));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setPackage(null);
                startActivity(intent);
            }
        });
        //----------------------url
        TextView url = findViewById(R.id.url);
        url.setText("See more: " + Html.fromHtml(job.getUrl()));
        url.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(job.getCompanyUrl()));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setPackage(null);
                startActivity(intent);
            }
        });
        //----------------------companyUrl
        final TextView companyUrl = findViewById(R.id.company_url);
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
    }
}
