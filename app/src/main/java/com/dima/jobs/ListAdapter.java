package com.dima.jobs;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private JobsDataCreator jobsDataCreator = new JobsDataCreator();
    ArrayList<Job> jobs = jobsDataCreator.getVacanciesList();

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final Context context = parent.getContext();
        int layoutIdOfListItem = R.layout.list_item;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(layoutIdOfListItem, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(jobs.get(position));


    }

    @Override
    public int getItemCount() {
        return jobs.size();
    }



    class ViewHolder extends RecyclerView.ViewHolder  {

        private ImageView companyLogo;
        private TextView company;
        private TextView title;
        private TextView location;
        private TextView type;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            companyLogo = itemView.findViewById(R.id.companyLogo);
            company = itemView.findViewById(R.id.company);
            title = itemView.findViewById(R.id.title);
            location = itemView.findViewById(R.id.location);
            type = itemView.findViewById(R.id.type);
        }

        void bind(final Job job) {
            Picasso.with(companyLogo.getContext()).load(job.getCompanyLogo()).into(companyLogo);
            company.setText(job.getCompany());
            title.setText(job.getTitle());
            location.setText(job.getLocation());
            type.setText(job.getType());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), DetailActivity.class);
                    intent.putExtra("job", (Serializable) job);
                    v.getContext().startActivity(intent);

                }
            });
        }
    }
}
