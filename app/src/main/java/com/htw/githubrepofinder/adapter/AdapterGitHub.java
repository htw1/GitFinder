package com.htw.githubrepofinder.adapter;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.htw.githubrepofinder.R;
import com.htw.githubrepofinder.callback.RecyclerOnClickListener;
import com.htw.githubrepofinder.databinding.UiAdapterItem;
import com.htw.githubrepofinder.model.Items;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AdapterGitHub extends RecyclerView.Adapter<AdapterGitHub.GitHubViewHolder> {

    private LayoutInflater layoutInflater;
    RecyclerOnClickListener onClickListener;
    @Nullable
    private List<Items> repoList = new ArrayList<>();

    public AdapterGitHub(Context context, RecyclerOnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public void  setAdapterList (List<Items> newList){
        repoList.clear();
        Collections.sort(newList, new SortByFavorites());
        repoList.addAll(newList);
        notifyDataSetChanged();
    }
    public class SortByFavorites implements Comparator<Items> {
        @Override
        public int compare(Items o1, Items o2) {
            return o2.getmStargazersCount().compareTo(o1.getmStargazersCount());
        }
    }

    // GitHubViewHolder
    public class GitHubViewHolder extends RecyclerView.ViewHolder {

        private final UiAdapterItem binding;

        public GitHubViewHolder(@NonNull UiAdapterItem binding) {
            super(binding.getRoot());
            this.binding = binding;

            itemView.setOnClickListener(view -> {
                try {
                    onClickListener.recyclerViewListClicked(view,getLayoutPosition());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            });

        }
    }

    @NonNull
    @Override
    public GitHubViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {

        if (layoutInflater == null) layoutInflater = LayoutInflater.from(viewGroup.getContext());
        UiAdapterItem binding = DataBindingUtil.inflate(layoutInflater, R.layout.recyclerview_adapter_item,viewGroup,false);

        return new GitHubViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull GitHubViewHolder holder, int possition) {

        holder.binding.repoName.setText(repoList.get(possition).getmName());
        Glide.with(holder.itemView.getContext())
                .load(repoList.get(possition).getmOwner().getmAvatarUrl())
                .apply(RequestOptions.circleCropTransform())
                .into(holder.binding.avatarIcon);
        holder.binding.stars.setText(formatValue(repoList.get(possition).getmStargazersCount()));
        holder.binding.watchers.setText(repoList.get(possition).getmWatchers().toString());
        holder.binding.language.setText(TextUtils.isEmpty(repoList.get(possition).getmLanguage()) ? "n/a" : repoList.get(possition).getmLanguage());
    }
    public static String formatValue(double value) {
        int power;
        String suffix = " kmbt";
        String formattedNumber = "";

        NumberFormat formatter = new DecimalFormat("#,###.#");
        power = (int)StrictMath.log10(value);
        value = value/(Math.pow(10,(power/3)*3));
        formattedNumber=formatter.format(value);
        formattedNumber = formattedNumber + suffix.charAt(power/3);
        return formattedNumber.length()>4 ?  formattedNumber.replaceAll("\\.[0-9]+", "") : formattedNumber;
    }


    @Override
    public int getItemCount() {
        return repoList.size() ;
    }
}
