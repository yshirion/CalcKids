package com.example.calackids;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;

public class ListCardAdapter extends RecyclerView.Adapter<ListCardAdapter.ExampleViewHolder>{
    private ArrayList<ListCard> mExampleList;

    public static class ExampleViewHolder extends RecyclerView.ViewHolder {
        public TextView date_text;
        public TextView amount_text;
        public TextView type_text;
        public TextView end_text;
        int count;

        public ExampleViewHolder(View itemView) {
            super(itemView);
            count = 0;
            date_text = (TextView) itemView.findViewById(R.id.date_view);
            amount_text = (TextView) itemView.findViewById(R.id.amount_from);
            type_text = (TextView) itemView.findViewById(R.id.subject_type);
            end_text = (TextView) itemView.findViewById(R.id.end_read);
        }

    }

    public ListCardAdapter(ArrayList<ListCard> exampleList) {
        mExampleList = exampleList;
    }

    @Override
    public ListCardAdapter.ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_layout, parent, false);
        ListCardAdapter.ExampleViewHolder evh = new ListCardAdapter.ExampleViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {
        ListCard currentItem = mExampleList.get(position);
        if (position == 0) holder.itemView.setLayoutParams(
                new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,100));
        paintCard(currentItem, holder);

        holder.date_text.setText(currentItem.getDate());
        holder.amount_text.setText(currentItem.getAmount_from());
        holder.type_text.setText(currentItem.getSubject_type());
        holder.end_text.setText(currentItem.getEnd_read());
        holder.itemView.setTag(currentItem);

    }

    private void paintCard(ListCard currentItem, ExampleViewHolder holder) {
        holder.itemView.setBackgroundResource(R.drawable.rounded_white);
        if (currentItem.isBalance){
            if (currentItem.isPositive)
                holder.itemView.setBackgroundResource(R.drawable.rounded_green);
            else
                holder.itemView.setBackgroundResource(R.drawable.rounded_red);
        }
        else if (currentItem.isMessage){
            if (!currentItem.isReaded) holder.itemView.setBackgroundResource(R.drawable.rounded_read);
        }


    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }
}
