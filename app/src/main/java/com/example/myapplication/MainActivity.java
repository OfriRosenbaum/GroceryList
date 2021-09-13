package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase db;
    GroceryAdapter adapter;
    TextView textAmount;
    RecyclerView list;
    EditText editText;
    //    Button moveToSecond;
    int amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GroceryDBHelper dbHelper = new GroceryDBHelper(this);
        db = dbHelper.getWritableDatabase();
        textAmount = (TextView) findViewById(R.id.text_amount);
        Button increase = (Button) findViewById(R.id.button_increase);
        Button decrease = (Button) findViewById(R.id.button_decrease);
        Button addItem = (Button) findViewById(R.id.button_add);
        editText = (EditText) findViewById(R.id.edittext);
        list = (RecyclerView) findViewById(R.id.recyclerview_items);
        list.setLayoutManager(new LinearLayoutManager(this));
        adapter = new GroceryAdapter(this, getAllItems());
        list.setAdapter(adapter);
//        moveToSecond = (Button) findViewById(R.id.button_move_second);
        amount = 0;
        increase.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                amount++;
                textAmount.setText(String.valueOf(amount));
            }
        });
        decrease.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (amount > 0) {
                    amount--;
                    textAmount.setText(String.valueOf(amount));
                }
            }
        });
        addItem.setOnClickListener((new View.OnClickListener() {
            public void onClick(View view) {
                if (editText.getText().toString().trim().length() == 0 || amount == 0) {
                    return;
                }
                String name = editText.getText().toString();
                ContentValues cv = new ContentValues();
                cv.put(GroceryContract.GroceryEntry.COLUMN_NAME, name);
                cv.put(GroceryContract.GroceryEntry.COLUMN_AMOUNT, amount);
                db.insert(GroceryContract.GroceryEntry.TABLE_NAME, null, cv);
                adapter.swapCursor(getAllItems());
                editText.getText().clear();
            }
        }));
//        moveToSecond.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
//                startActivity(intent);
//            }
//        });
    }

    private Cursor getAllItems() {
        return db.query(GroceryContract.GroceryEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                GroceryContract.GroceryEntry.COLUMN_TIMESTAMP + " DESC");
    }
}