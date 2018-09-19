package hva.higher_lower;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private TextView score;
    private TextView highScore;
    private ImageView diceImage;
    private FloatingActionButton lower;
    private FloatingActionButton higher;
    private ListView listView;
    private int[] diceImageNames;
    private int points = 0;
    private int highScorePoints = 0;
    private ArrayAdapter adapter;
    private List<Dice> diceThrows;
    private int currentNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        score = findViewById(R.id.score);
        highScore = findViewById(R.id.highscore);
        diceImage = findViewById(R.id.dice);
        lower = findViewById(R.id.lower);
        higher = findViewById(R.id.higher);
        listView = findViewById(R.id.listview);
        diceImageNames = new int[]{R.drawable.d1, R.drawable.d2, R.drawable.d3, R.drawable.d4, R.drawable.d5, R.drawable.d6};
        currentNumber = getRandom();
        diceImage.setImageResource(diceImageNames[currentNumber]);
        score.setText("Score: " + (String.valueOf(points)));
        highScore.setText("Highscore: " + (String.valueOf(points)));
        diceThrows = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, diceThrows);
        listView.setAdapter(adapter);

        lower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rollDice(false, v);
            }
        });
        higher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rollDice(true, v);
            }
        });
    }

    private int getRandom() {
        int randomElement = new Random().nextInt(6);
        return randomElement;
    }

    private void rollDice(boolean isItHigher, View v) {
        Dice dice = new Dice(getRandom());
        diceImage.setImageResource(diceImageNames[dice.getDiceFaceNumber()]);
        diceThrows.add(dice);
        adapter.notifyDataSetChanged();

        if (currentNumber <= dice.getDiceFaceNumber() && isItHigher) {
            points++;
            Snackbar.make(v, "You win", Snackbar.LENGTH_SHORT).show();
        } else if (currentNumber >= dice.getDiceFaceNumber() && !isItHigher) {
            points++;
            Snackbar.make(v, "You win", Snackbar.LENGTH_SHORT).show();
        } else {
            points = 0;
            Snackbar.make(v, "You lose", Snackbar.LENGTH_SHORT).show();
        }

        currentNumber = dice.getDiceFaceNumber();
        score.setText("Score: " + (String.valueOf(points)));

        if (points > highScorePoints) {
            highScore.setText("Highscore: " + (String.valueOf(points)));
        }
    }
}
