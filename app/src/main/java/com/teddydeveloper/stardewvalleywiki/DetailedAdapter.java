package com.teddydeveloper.stardewvalleywiki;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


public class DetailedAdapter extends RecyclerView.Adapter<DetailedAdapter.DataViewHolder>{

    private static final String LOG_TAG = DataAdapter.class.getSimpleName();
    List<Data> data;
    Context context;
    private DetailedAdapterClickListener mDetailedAdapterClickListener;

    public void setmDetailedAdapterClickListener(DetailedAdapterClickListener detailedAdapterClickListener) {
        this.mDetailedAdapterClickListener = detailedAdapterClickListener;
    }


    DetailedAdapter(List<Data> data){
        this.data = data;
    }

    @Override
    public DataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View v = LayoutInflater.from(context).inflate(R.layout.content_detailed, parent, false);
        DataViewHolder pvh = new DataViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(DataViewHolder holder, int position) {
        holder.title.setText(data.get(position).getTitle());
        holder.image.setImageResource(data.get(position).photoId);
        holder.description.setText(Html.fromHtml(data.get(position).getDetailedText()));
    }

    public interface DetailedAdapterClickListener {
        public void itemClicked(View view, int position);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class DataViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        CardView cardView;
        TextView title;
        ImageView image;
        TextView description;


        DataViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView)itemView.findViewById(R.id.cardview_main);
            title = (TextView)itemView.findViewById(R.id.title);
            image = (ImageView)itemView.findViewById(R.id.image);
            description = (TextView)itemView.findViewById(R.id.description);
            cardView.setClickable(true);
            cardView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            if(mDetailedAdapterClickListener!=null){
          //      Toast.makeText(context, "onClickListener", Toast.LENGTH_LONG).show();
                Log.d("detailedAdapter",LOG_TAG);
                mDetailedAdapterClickListener.itemClicked(v,getAdapterPosition());
            }
        }
    }

}