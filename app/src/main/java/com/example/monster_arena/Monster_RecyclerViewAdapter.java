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
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_monster_view, parent, false);
        return new MyViewHolder(view, monsterRecyclerViewInterface);
    }

    //@Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.monsterName.setText(monsters.get(position).getName());
        Monsters monster = monsters.get(position);
        holder.monsterInfo.setText(monster.toString());
    }

    @Override
    public int getItemCount() {
        return monsters.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView monsterName;
        TextView monsterInfo;
        public MyViewHolder(@NonNull View itemView, Monster_RecyclerViewInterface monsterRecyclerViewInterface) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            monsterName = itemView.findViewById(R.id.textViewTitle); // sets name of monster in card view
            monsterInfo = itemView.findViewById(R.id.textViewMonsterInfo);


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
