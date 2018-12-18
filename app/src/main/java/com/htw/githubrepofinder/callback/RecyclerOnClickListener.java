package com.htw.githubrepofinder.callback;
import android.view.View;
import java.text.ParseException;

public interface RecyclerOnClickListener {
    public void recyclerViewListClicked(View v, int position) throws ParseException;
}