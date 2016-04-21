package com.teddydeveloper.stardewvalleywiki;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Matu on 2.03.2016.
 */
public class DataAdapter extends RecyclerView.Adapter<DataAdapter.DataViewHolder>{

    private static final String LOG_TAG = DataAdapter.class.getSimpleName();
    List<Data> data;
    Context context;
    private DataAdapterClickListener mDataAdapterClickListener;

    public void setmDataAdapterClickListener(DataAdapterClickListener DataAdapterClickListener) {
        this.mDataAdapterClickListener = DataAdapterClickListener;
    }


    DataAdapter(List<Data> data){
        this.data = data;
    }

    @Override
    public DataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View v = LayoutInflater.from(context).inflate(R.layout.main_row, parent, false);
        DataViewHolder pvh = new DataViewHolder(v);
        return pvh;
    }



    public interface DataAdapterClickListener {
        public void itemClicked(View view, int position);
    }

    @Override
    public void onBindViewHolder(DataViewHolder holder, int pos) {

        holder.title.setText(data.get(pos).getTitle());
        holder.image.setImageResource(data.get(pos).photoId);
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


        DataViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView)itemView.findViewById(R.id.cardview_row);
            title = (TextView)itemView.findViewById(R.id.title);
            image = (ImageView)itemView.findViewById(R.id.image);
            cardView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            if(mDataAdapterClickListener!=null){
              //  Toast.makeText(context, "onClickListener", Toast.LENGTH_LONG).show();
                Log.d("DataAdapter YAYAY",LOG_TAG);
                mDataAdapterClickListener.itemClicked(v,getAdapterPosition());
            }
        }
    }

}