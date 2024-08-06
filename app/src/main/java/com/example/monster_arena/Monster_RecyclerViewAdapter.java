package com.example.monster_arena;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.monster_arena.database.entities.Monsters;
import java.util.List;

public class Monster_RecyclerViewAdapter extends RecyclerView.Adapter<Monster_RecyclerViewAdapter.MyViewHolder> {
    private final Monster_RecyclerViewInterface monsterRecyclerViewInterface;

    Context context;
    List<Monsters> monsters;

    public Monster_RecyclerViewAdapter(Context context, List<Monsters> monsters, Monster_RecyclerViewInterface monsterRecyclerViewInterface) {
        this.context = context;
        this.monsters = monsters;
        this.monsterRecyclerViewInterface = monsterRecyclerViewInterface;
    }

    @NonNull
    @Override
    public Monster_RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_row, parent, false);
        return new Monster_RecyclerViewAdapter.MyViewHolder(view, monsterRecyclerViewInterface);
    }

    //@Override
    public void onBindViewHolder(@NonNull Monster_RecyclerViewAdapter.MyViewHolder holder, int position) {
        holder.tvName.setText(monsters.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return monsters.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView tvName, admin;
        public MyViewHolder(@NonNull View itemView, Monster_RecyclerViewInterface monsterRecyclerViewInterface) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            tvName = itemView.findViewById(R.id.textView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (monsterRecyclerViewInterface != null) {
                        int pos = getAdapterPosition();

                        if (pos != RecyclerView.NO_POSITION) {
                            monsterRecyclerViewInterface.onItemClick(pos);
                        }
                    }
                }
            });
        }
    }



}
