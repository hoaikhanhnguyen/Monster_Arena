package com.example.monster_arena;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.monster_arena.database.entities.User;

import java.util.List;

public class User_RecyclerViewAdapter extends RecyclerView.Adapter <User_RecyclerViewAdapter.MyViewHolder>{
    Context context;
   List<User> users;

    public User_RecyclerViewAdapter(Context context, List<User> users) {
        this.context = context;
        this.users = users;
    }
    @NonNull
    @Override
    public User_RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate layout
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_row, parent, false);
        return new User_RecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull User_RecyclerViewAdapter.MyViewHolder holder, int position) {
        // assigning values to the views in recycler_view_row layout file based on position
        holder.tvName.setText(users.get(position).getUserName());
        if (users.get(position).isAdmin()) {
            holder.admin.setText(R.string.admin);
        } else {
            holder.admin.setText(R.string.user);
        }
    }

    @Override
    public int getItemCount() {
        // how many items in total
        return users.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // grab all views from recycler_view_row layout file

        ImageView imageView;
        TextView tvName, admin;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            tvName = itemView.findViewById(R.id.textView);
            admin = itemView.findViewById(R.id.textView2);
        }
    }
}
