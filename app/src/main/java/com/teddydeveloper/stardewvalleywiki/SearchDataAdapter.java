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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Matu on 2.03.2016.
 */
public class SearchDataAdapter extends RecyclerView.Adapter<SearchDataAdapter.DataViewHolder>{

    private static final String LOG_TAG = SearchDataAdapter.class.getSimpleName();

    public List<Data> getData() {
        return data;
    }

    List<Data> data;
    Context context;
    private  SearchDataAdapterClickListener mSearchDataAdapterClickListener;

    public void setmDataAdapterClickListener(SearchDataAdapterClickListener searchDataAdapterClickListener) {
        this.mSearchDataAdapterClickListener = searchDataAdapterClickListener;
    }


    public SearchDataAdapter(List<Data> data){
        this.data = new ArrayList<>(data);
    }

    @Override
    public DataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View v = LayoutInflater.from(context).inflate(R.layout.main_row, parent, false);
        DataViewHolder pvh = new DataViewHolder(v);
        return pvh;
    }

    public Data removeItem(int position) {
        final Data model = data.remove(position);
        notifyItemRemoved(position);
        return model;
    }

    public void addItem(int position, Data model) {
        data.add(position, model);
        notifyItemInserted(position);
    }

    public void moveItem(int fromPosition, int toPosition) {
        final Data model = data.remove(fromPosition);
        data.add(toPosition, model);
        notifyItemMoved(fromPosition, toPosition);
    }

 /** animate search **/

    public void animateTo(List<Data> data) {
        applyAndAnimateRemovals(data);
        applyAndAnimateAdditions(data);
        applyAndAnimateMovedItems(data);
    }

    private void applyAndAnimateRemovals(List<Data> newModels) {
        for (int i = data.size() - 1; i >= 0; i--) {
            final Data model = data.get(i);
            if (!newModels.contains(model)) {
                removeItem(i);
            }
        }
    }

    private void applyAndAnimateAdditions(List<Data> newModels) {
        for (int i = 0, count = newModels.size(); i < count; i++) {
            final Data model = newModels.get(i);
            if (!data.contains(model)) {
                addItem(i, model);
            }
        }
    }

    private void applyAndAnimateMovedItems(List<Data> newModels) {
        for (int toPosition = newModels.size() - 1; toPosition >= 0; toPosition--) {
            final Data model = newModels.get(toPosition);
            final int fromPosition = data.indexOf(model);
            if (fromPosition >= 0 && fromPosition != toPosition) {
                moveItem(fromPosition, toPosition);
            }
        }
    }



    public interface SearchDataAdapterClickListener {
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
            if(mSearchDataAdapterClickListener!=null){
              //  Toast.makeText(context, "onClickListener", Toast.LENGTH_LONG).show();
                Log.d("DataAdapter YAYAY",LOG_TAG);
                mSearchDataAdapterClickListener.itemClicked(v,getAdapterPosition());
            }
        }
    }

}