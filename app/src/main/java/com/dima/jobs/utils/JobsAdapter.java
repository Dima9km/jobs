package com.dima.jobs.utils;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dima.jobs.R;
import com.dima.jobs.data.App;
import com.dima.jobs.data.Job;
import com.dima.jobs.data.JobFavoritesDao;
import com.dima.jobs.data.JobsDatabase;
import com.dima.jobs.ui.screens.job.JobActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class JobsAdapter extends RecyclerView.Adapter<JobsAdapter.ViewHolder> {

    List<Job> jobs;

    public JobsAdapter(List<Job> jobs) {
        this.jobs = jobs;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_job, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(jobs.get(position));
    }

    @Override
    public int getItemCount() {
        return jobs.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView companyLogo;
        private TextView company;
        private TextView title;
        private TextView location;
        private TextView type;
        private ImageView favoriteStar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            companyLogo = itemView.findViewById(R.id.ivCompanyLogo);
            company = itemView.findViewById(R.id.company);
            title = itemView.findViewById(R.id.tvVacancyTitle);
            location = itemView.findViewById(R.id.tvLocation);
            type = itemView.findViewById(R.id.tvTtype);
            favoriteStar = itemView.findViewById(R.id.ivFavorite);
        }

        void bind(final Job job) {
            Picasso.with(companyLogo.getContext())
                    .load(job.getCompanyLogo())
                    .placeholder(R.drawable.ic_baseline_hourglass_empty_24)
                    .error(R.drawable.ic_baseline_error_outline_24)
                    .into(companyLogo);

            if (job.isFavorite()) {
                favoriteStar.setImageResource(R.drawable.ic_baseline_star_24);
            } else {
                favoriteStar.setImageResource(R.drawable.ic_baseline_star_border_24);
            }

            company.setText(job.getCompany());
            title.setText(job.getTitle());
            location.setText(job.getLocation());
            type.setText(job.getType());

            favoriteStar.setOnClickListener(new View.OnClickListener() {
                JobsDatabase db = App.getInstance().getDatabase();
                JobFavoritesDao jobFavoritesDao = db.jobFavoritesDao();

                @Override
                public void onClick(View v) {
                    if (!job.isFavorite()) {
                        job.setFavorite(true);
                        jobFavoritesDao.addFavorite(job);
                        favoriteStar.setImageResource(R.drawable.ic_baseline_star_24);
                        showToast("Added to favorites");
                    } else {
                        job.setFavorite(false);
                        jobFavoritesDao.deleteFavorite(job);
                        favoriteStar.setImageResource(R.drawable.ic_baseline_star_border_24);
                        showToast("Removed from favorites");
                    }
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    v.getContext().startActivity(new Intent(v.getContext(), JobActivity.class).putExtra("job", job));
                }
            });
        }

        private void showToast(String text) {
            Toast.makeText(itemView.getContext(), text, Toast.LENGTH_LONG).show();
        }
    }
}
