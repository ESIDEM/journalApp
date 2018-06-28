package ng.com.techdepo.journalapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ng.com.techdepo.journalapp.R;
import ng.com.techdepo.journalapp.pojo.Journal;

public class JournalAdapter extends RecyclerView.Adapter<JournalAdapter.JournalViewHolder> {

    // Member variable to handle item clicks
    final private ItemClickListener mItemClickListener;
    private List<Journal> mJournalEntries;
    private Context mContext;

    public JournalAdapter(ItemClickListener mItemClickListener, Context mContext) {
        this.mItemClickListener = mItemClickListener;
        this.mContext = mContext;
    }


    @NonNull
    @Override
    public JournalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.memory_item, parent, false);

        return new JournalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JournalViewHolder holder, int position) {

        // Determine the values of the wanted data
        Journal journalEntry = mJournalEntries.get(position);
        String title = journalEntry.getTitle();
        String body = journalEntry.getBody();
        String timeAdded = journalEntry.getTimeStamp();

        //Set values
        holder.memory_title.setText(title);
        holder.memory_body.setText(body);
        holder.time_added.setText(String.valueOf(timeAdded));


    }

    @Override
    public int getItemCount() {
        if (mJournalEntries == null) {
            return 0;
        }
        return mJournalEntries.size();
    }

    public List<Journal> getJournals() {
        return mJournalEntries;
    }

    public void setTasks(List<Journal> journalsEntries) {
        mJournalEntries = journalsEntries;
        notifyDataSetChanged();
    }

    public interface ItemClickListener {
        void onItemClickListener(int itemId);
    }


    // Inner class for creating ViewHolders
    class JournalViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        // Class variables for the task description and priority TextViews
        TextView memory_title;
        TextView memory_body;
        TextView time_added;


        /**
         * Constructor for the TaskViewHolders.
         *
         * @param itemView The view inflated in onCreateViewHolder
         */
        public JournalViewHolder(View itemView) {
            super(itemView);

            memory_title = itemView.findViewById(R.id.memory_title);
            memory_body = itemView.findViewById(R.id.memory_body);
            time_added = itemView.findViewById(R.id.time_added);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            int elementId = mJournalEntries.get(getAdapterPosition()).getId();
            mItemClickListener.onItemClickListener(elementId);
        }
    }
}
