package com.dima.jobs.ui.screens.job;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.dima.jobs.R;
import com.dima.jobs.data.App;
import com.dima.jobs.data.Job;
import com.dima.jobs.data.JobFavoritesDao;
import com.dima.jobs.data.JobsDatabase;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class JobActivity extends AppCompatActivity {

    private Job job;

    private ImageView companyLogo;
    private TextView title;
    private TextView location;
    private TextView type;
    private TextView description;
    private TextView createdAt;

    JobsDatabase db = App.getInstance().getDatabase();
    JobFavoritesDao jobFavoritesDao = db.jobFavoritesDao();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        job = (Job) getIntent().getExtras().getSerializable("job");
        setContentView(R.layout.activity_job);
        initToolbar();
        initUI();
        fillUpUI();
    }

    private void initToolbar() {
        final Toolbar toolbar = findViewById(R.id.tbJob);
        toolbar.setSubtitle(job.getTitle());
        toolbar.setTitle(job.getCompany());
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        toolbar.inflateMenu(R.menu.detail_toolbar_menu);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (!job.isFavorite()) {
                    job.setFavorite(true);
                    jobFavoritesDao.addFavorite(job);
                    toolbar.getMenu().getItem(0)
                            .setIcon(getResources().getDrawable(R.drawable.ic_baseline_star_24));
                    showToast("Added to favorites");
                } else {
                    job.setFavorite(false);
                    jobFavoritesDao.deleteFavorite(job);
                    toolbar.getMenu().getItem(0)
                            .setIcon(getResources().getDrawable(R.drawable.ic_baseline_star_border_24));
                    showToast("Removed from favorites");
                }
                return true;
            }
        });

        if (job.isFavorite()) {
            toolbar.getMenu().getItem(0)
                    .setIcon(getResources().getDrawable(R.drawable.ic_baseline_star_24));
        } else {
            toolbar.getMenu().getItem(0)
                    .setIcon(getResources().getDrawable(R.drawable.ic_baseline_star_border_24));
        }
    }

    private void initUI() {
        companyLogo = findViewById(R.id.ivCompanyLogo);
        title = findViewById(R.id.tvVacancyTitle);
        location = findViewById(R.id.tvLocation);
        type = findViewById(R.id.tvTtype);
        description = findViewById(R.id.tvDescription);
        createdAt = findViewById(R.id.tvCreatedAt);
    }

    private void fillUpUI() {
        Picasso.with(companyLogo.getContext())
                .load(job.getCompanyLogo())
                .placeholder(R.drawable.ic_baseline_hourglass_empty_24)
                .error(R.drawable.ic_baseline_error_outline_24)
                .into(companyLogo);

        title.setText(job.getTitle());
        location.setText(job.getLocation());
        type.setText(job.getType());
        description.setText(Html.fromHtml(job.getDescription()));
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
            resultString = "Long time ago";
        }
        createdAt.setText("Created at: " + resultString);
        TextView howToApply = findViewById(R.id.tvHowToApply);
        howToApply.setText(Html.fromHtml(job.getHowToApply()));
        howToApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickHelper();
            }
        });
        TextView url = findViewById(R.id.tvUrl);
        url.setText("See more: " + Html.fromHtml(job.getUrl()));
        url.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickHelper();
            }
        });
        final TextView companyUrl = findViewById(R.id.tvCompanyUrl);
        companyUrl.setText("Website: " + job.getCompanyUrl());
        companyUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickHelper();
            }
        });
    }

    private void onClickHelper() {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(job.getCompanyUrl())));
    }

    private void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }
}
