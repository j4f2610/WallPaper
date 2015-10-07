package com.j4f.wallpaper.Adapters;

import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.j4f.wallpaper.Model.Album;
import com.j4f.wallpaper.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by pham on 7/22/2015.
 */
public class ListAlbumAdapter extends RecyclerView.Adapter<ListAlbumAdapter.ListWallPaperHolder> {
    private ArrayList<Album> wallPapers;
    public ListAlbumAdapter(ArrayList<Album> wallPapers) {
        this.wallPapers = wallPapers;
    }
    public ListAlbumAdapter() {

    }
    @Override
    public ListWallPaperHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_wallpaper_list, parent, false);
        ListWallPaperHolder listWallPaperHolder = new ListWallPaperHolder(view);
        return listWallPaperHolder;
    }

    @Override
    public void onBindViewHolder(ListWallPaperHolder holder, int position) {
        if (wallPapers.get(position).getUrl() != null) {
            Uri uri = Uri.parse(wallPapers.get(position).getUrl());
            Picasso.with(holder.imageWallpaper.getContext()).load(uri).placeholder(R.drawable.image).into(holder.imageWallpaper);
        }

    }

    @Override
    public int getItemCount() {
        return wallPapers.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class ListWallPaperHolder extends RecyclerView.ViewHolder {
        private CardView cv;
        private TextView personName;
        private ImageView imageWallpaper;

        ListWallPaperHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.card_view);
            imageWallpaper = (ImageView) itemView.findViewById(R.id.imgWallPaper);
            personName = (TextView) itemView.findViewById(R.id.info_text);
        }
    }
}
