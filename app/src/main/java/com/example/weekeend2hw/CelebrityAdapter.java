package com.example.weekeend2hw;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CelebrityAdapter extends RecyclerView.Adapter<CelebrityAdapter.CelebrityHolder> {
    private List<Celebrity> celebrity = new ArrayList<>();
    private OnitemClickListener listener;

    class CelebrityHolder extends RecyclerView.ViewHolder {
        private TextView tvCelebrityName;
        private TextView tvDescription;
        private TextView tvFameLevel;
        private ImageButton img_btnDelete;
        private ImageButton img_btnUpdate;


        public CelebrityHolder(@NonNull View itemView) {
            super(itemView);
            tvCelebrityName = itemView.findViewById(R.id.tvCelebrityName);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvFameLevel = itemView.findViewById(R.id.tvFameLevel);
            img_btnDelete = itemView.findViewById(R.id.img_btnDelete);
            img_btnUpdate = itemView.findViewById(R.id.img_btnUpdate);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(celebrity.get(position));
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public CelebrityHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.customer_item, parent, false);
        return new CelebrityHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final CelebrityHolder holder, final int position) {
        final Celebrity currentCelebrity = celebrity.get(position);
        holder.tvCelebrityName.setText(currentCelebrity.getCelebrityName());
        holder.tvDescription.setText(currentCelebrity.getProfession());
        holder.tvFameLevel.setText(String.valueOf(currentCelebrity.getFameLevel()));

        holder.img_btnDelete.setOnClickListener(new View.OnClickListener() {  //on click listener for
            @Override
            public void onClick(View v) {

                String item = currentCelebrity.getCelebrityName();
                //int newPosition = holder.getAdapterPosition();
                celebrity.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, celebrity.size());

                // Toast.makeText(MainActivity.this,"Removed : "+item ,Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {

        return celebrity.size();
    }

    public void setCelebrity(List<Celebrity> celebrities) {
        this.celebrity = celebrities;
        notifyDataSetChanged();
    }

    public Celebrity getCelebrityAt(int position) {
        return celebrity.get(position);
    }

    public interface OnitemClickListener { //for updating click listener
        void onItemClick(Celebrity celebrity);
    }

    public void setOnItemClickListener(OnitemClickListener listener) {
        this.listener = listener;
    }


}
