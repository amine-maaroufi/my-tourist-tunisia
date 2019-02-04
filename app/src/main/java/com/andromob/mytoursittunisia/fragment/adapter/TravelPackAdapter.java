package com.andromob.mytoursittunisia.fragment.adapter;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.andromob.mytoursittunisia.R;

public class TravelPackAdapter extends RecyclerView.Adapter<TravelPackAdapter.RecyclerVH>{

    Context c;
    String[] listpacks;

    public TravelPackAdapter(Context c, String[] listpacks) {
            this.c = c;
            this.listpacks = listpacks;
            }

    @Override
    public RecyclerVH onCreateViewHolder(ViewGroup parent, int viewType) {
            View v= LayoutInflater.from(c).inflate(R.layout.cardview_model,parent,false);
            return new RecyclerVH(v);
            }

    @Override
    public void onBindViewHolder(RecyclerVH holder, int position) {
            holder.title_card.setText(listpacks[position]);
            holder.price.setText("70$");
            holder.content_card.setText("An exceptional natural landscape draws its charm from its verdant nature. proximity to the mountains");

            }

    @Override
    public int getItemCount() {
            return listpacks.length;
            }

    /*
    VIEWHOLDER CLASS
     */
    public class RecyclerVH extends RecyclerView.ViewHolder
    {
        TextView title_card,price,content_card;


        public RecyclerVH(View itemView) {
            super(itemView);

            title_card= (TextView) itemView.findViewById(R.id.title_card);
            content_card= (TextView) itemView.findViewById(R.id.card_content);
            price= (TextView) itemView.findViewById(R.id.price);
        }
    }
}
