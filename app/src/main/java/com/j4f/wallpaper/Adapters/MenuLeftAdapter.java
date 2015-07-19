package com.j4f.wallpaper.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.j4f.wallpaper.R;

import java.util.ArrayList;

/**
 * Created by pham on 7/16/2015.
 */
public class MenuLeftAdapter extends RecyclerView.Adapter<MenuLeftAdapter.ViewHolder>{
    private ArrayList<String> data= new ArrayList<>();
    private LayoutInflater inflater;
    private Context context;
    private int ICONS[] = {R.drawable.ic_star, R.drawable.ic_list, R.drawable.ic_download,R.drawable.ic_information,R.drawable.ic_share};
    public MenuLeftAdapter(Context context, ArrayList<String> data){
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= inflater.inflate(R.layout.item_row,parent,false);
        ViewHolder viewHolder= new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.title.setText(data.get(position).toString());
        holder.imageView.setImageResource(ICONS[position]);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView imageView;
        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            imageView=(ImageView)itemView.findViewById(R.id.icon);
        }
    }
}
