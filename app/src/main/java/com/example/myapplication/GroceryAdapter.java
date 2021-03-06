package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class GroceryAdapter extends RecyclerView.Adapter<GroceryAdapter.GroceryViewHolder> {

    private Context context;
    private Cursor cursor;

    public GroceryAdapter(Context context, Cursor cursor) {
        this.context = context;
        this.cursor = cursor;
    }

    public class GroceryViewHolder extends RecyclerView.ViewHolder {

        public TextView nameText;
        public TextView countText;

        public GroceryViewHolder(@NonNull View itemView) {
            super(itemView);
            nameText = itemView.findViewById(R.id.text_item_name);
            countText = itemView.findViewById(R.id.text_item_amount);
        }
    }

    @NonNull
    @Override
    public GroceryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        return new GroceryViewHolder(inflater.inflate(R.layout.grocery_item, parent, false));
    }

    @SuppressLint("Range")
    @Override
    public void onBindViewHolder(@NonNull GroceryViewHolder holder, int position) {
        if (!cursor.moveToPosition(position)) {
            return;
        }
        String name = cursor.getString(cursor.getColumnIndex(GroceryContract.GroceryEntry.COLUMN_NAME));
        int amount = cursor.getInt(cursor.getColumnIndex(GroceryContract.GroceryEntry.COLUMN_AMOUNT));

        holder.nameText.setText(name);
        holder.countText.setText(String.valueOf(amount));
    }

    @Override
    public int getItemCount() {
        return cursor.getCount();
    }

    public void swapCursor(Cursor newCursor){
        if(cursor!=null){
            cursor.close();
        }
        cursor = newCursor;
        if(newCursor!=null){
            notifyDataSetChanged();
        }
    }
}
