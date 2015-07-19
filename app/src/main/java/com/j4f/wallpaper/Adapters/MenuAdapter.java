package com.j4f.wallpaper.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.j4f.wallpaper.Model.BaseModel;
import com.j4f.wallpaper.R;

import java.util.ArrayList;

/**
 * Created by pham on 7/10/2015.
 */
public class MenuAdapter extends ArrayAdapter<BaseModel> {
    private Context context;
    private ArrayList<BaseModel> lstMenu;
    private TextView menuTitle;
    private ImageView menuIcon;
    private int ICONS[] = {R.drawable.ic_star, R.drawable.ic_list, R.drawable.ic_download, R.drawable.ic_information, R.drawable.ic_share};
    private View view;
    private LayoutInflater inflater;

    public MenuAdapter(Context context, int resource, ArrayList<BaseModel> lstMenu) {
        super(context, 0, lstMenu);
        this.context = context;
        this.lstMenu = lstMenu;
        if (inflater == null) {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        view = inflater.inflate(R.layout.item_row, parent, false);
        menuTitle = (TextView) view.findViewById(R.id.menuTitle);
        menuIcon = (ImageView) view.findViewById(R.id.menuIcon);
       if (position==lstMenu.size()-2) {
            View div = (View) view.findViewById(R.id.divider);
            div.setVisibility(View.VISIBLE);
        }
        menuTitle.setText(lstMenu.get(position).getName().toString());
        menuIcon.setImageResource(ICONS[position]);
        return view;
    }
}
