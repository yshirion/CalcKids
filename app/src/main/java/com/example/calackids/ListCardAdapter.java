package com.example.calackids;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;

//Adapter of RecyclerView for the lists of messages and actions.
public class ListCardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_LIST = 1;
    private ArrayList<ListCard> mRecyclerList;
    public boolean isMessage;
    public boolean isInvest;
    public boolean isLoan;

    public ListCardAdapter(ArrayList<ListCard> exampleList) {
        mRecyclerList = exampleList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        if (viewType == TYPE_HEADER){
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.head_layout, parent, false);
            return new HeaderHolder(v);
        }
        else if (viewType == TYPE_LIST) {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_layout, parent, false);
            return new RecyclerViewHolder(v);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ListCard currentItem;

        if (holder instanceof HeaderHolder){
            holder.itemView.setBackgroundResource(R.drawable.rounded_white);
            HeaderHolder headHolder = (HeaderHolder) holder;
            headHolder.date_title.setText("Date");
            if (isMessage) {
                headHolder.amount_from_title.setText("From");
                headHolder.subject_type_title.setText("Subject");
            }
            else {
                headHolder.amount_from_title.setText("Amount");
                if (!isInvest && ! isLoan)
                    headHolder.subject_type_title.setText("Type");
                else {
                    headHolder.subject_type_title.setText("Interest");
                    if (isInvest) headHolder.end_title.setText("End");
                }
            }
        }

        if (holder instanceof RecyclerViewHolder) {
            RecyclerViewHolder listHolder = (RecyclerViewHolder) holder;
            currentItem = mRecyclerList.get(position - 1);
            paintCard(currentItem, listHolder);
            listHolder.date_text.setText(currentItem.getDate());
            listHolder.amount_text.setText(currentItem.getAmount_from());
            listHolder.type_text.setText(currentItem.getSubject_type());
            listHolder.end_text.setText(currentItem.getEnd_read());
            listHolder.itemView.setTag(currentItem);

        }
    }

    private void paintCard(ListCard currentItem, RecyclerViewHolder holder){
        // For loans, investments, and read messages.
        holder.itemView.setBackgroundResource(R.drawable.rounded_white);
        //if we in balance (list of actions), paint the positive with green and negative with red.
        if (currentItem.isBalance){
            if (currentItem.isPositive)
                holder.itemView.setBackgroundResource(R.drawable.rounded_green);
            else
                holder.itemView.setBackgroundResource(R.drawable.rounded_red);
        }
        //for not read message.
        else if (currentItem.isMessage){
            if (!currentItem.isReaded) holder.itemView.setBackgroundResource(R.drawable.rounded_read);
        }
    }

    @Override
    public int getItemCount() {
        return mRecyclerList.size()+1;
    }

    public ListCard remove(int position){
        ListCard item = mRecyclerList.get(position);
        mRecyclerList.remove(position);
        return item;
    }

    public void readd(ListCard item, int position){
        mRecyclerList.add(position, item);
    }

    @Override
    public int getItemViewType(int position) {

        if (position == 0)
            return TYPE_HEADER;
        return TYPE_LIST;
    }

    //Inner class for the objects in the Recyclerview.
    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {
        int viewType;
        TextView date_text, amount_text, type_text, end_text;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            date_text = (TextView) itemView.findViewById(R.id.date_view);
            amount_text = (TextView) itemView.findViewById(R.id.amount_from);
            type_text = (TextView) itemView.findViewById(R.id.subject_type);
            end_text = (TextView) itemView.findViewById(R.id.end_read);
        }
    }
    private class HeaderHolder extends RecyclerView.ViewHolder{
        TextView date_title, amount_from_title, subject_type_title, end_title;

        public HeaderHolder(@NonNull View itemView) {
            super(itemView);
            date_title = (TextView) itemView.findViewById(R.id.date_view_title);
            amount_from_title = (TextView) itemView.findViewById(R.id.amount_from_title);
            subject_type_title = (TextView) itemView.findViewById(R.id.subject_type_title);
            end_title = (TextView) itemView.findViewById(R.id.end_read_title);

        }
    }
}
