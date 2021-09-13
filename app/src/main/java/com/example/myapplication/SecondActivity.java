package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class SecondActivity extends AppCompatActivity {

    TextView text;
    Button moveToFirst;
    Button rollDice;
    ImageView dice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);
        text = (TextView) findViewById(R.id.text);
        moveToFirst = (Button) findViewById(R.id.button_move_first);
        rollDice = (Button) findViewById(R.id.button_roll);
        dice = (ImageView) findViewById(R.id.image_dice);
        moveToFirst.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
        rollDice.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int[] dices = {R.drawable.dice1, R.drawable.dice2, R.drawable.dice3,
                        R.drawable.dice4, R.drawable.dice5, R.drawable.dice6};
                Random rnd = new Random();
                dice.setImageResource(dices[rnd.nextInt(dices.length)]);
            }
        });
    }
}