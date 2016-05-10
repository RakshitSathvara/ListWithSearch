package in.vaksys.listwithsearch;

/**
 * Created by dell980 on 5/10/2016.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class ListViewAdapter extends BaseAdapter {

    // Declare Variables
    Context mContext;
    LayoutInflater inflater;
    private List<Coutrycode> worldpopulationlist = null;
    private ArrayList<Coutrycode> arraylist;

    public ListViewAdapter(Context context,
                           List<Coutrycode> worldpopulationlist) {
        mContext = context;
        this.worldpopulationlist = worldpopulationlist;
        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<Coutrycode>();
        this.arraylist.addAll(worldpopulationlist);
    }

    public class ViewHolder {
        TextView code;
        TextView countryName;
    }

    @Override
    public int getCount() {
        return worldpopulationlist.size();
    }

    @Override
    public Coutrycode getItem(int position) {
        return worldpopulationlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.list_row, null);
            // Locate the TextViews in listview_item.xml
            holder.code = (TextView) view.findViewById(R.id.code);
            holder.countryName = (TextView) view.findViewById(R.id.countryName);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Set the results into TextViews
        holder.code.setText(worldpopulationlist.get(position).getCode());
        holder.countryName.setText(worldpopulationlist.get(position).getCountryName());
        // Listen for ListView Item Click
        view.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                /*// Send single item click data to SingleItemView Class
                Intent intent = new Intent(mContext, SingleItemView.class);
                // Pass all data rank
                intent.putExtra("rank",
                        (worldpopulationlist.get(position).getRank()));
                // Pass all data country
                intent.putExtra("country",
                        (worldpopulationlist.get(position).getCountry()));
                // Pass all data population
                intent.putExtra("population",
                        (worldpopulationlist.get(position).getPopulation()));
                // Pass all data flag
                intent.putExtra("flag",
                        (worldpopulationlist.get(position).getFlag()));
                // Start SingleItemView Class
                mContext.startActivity(intent);*/
                Toast.makeText(mContext, worldpopulationlist.get(position).getCode(), Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        worldpopulationlist.clear();
        if (charText.length() == 0) {
            worldpopulationlist.addAll(arraylist);
        } else {
            for (Coutrycode wp : arraylist) {
                if (wp.getCountryName().toLowerCase(Locale.getDefault())
                        .contains(charText)) {
                    worldpopulationlist.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }

}