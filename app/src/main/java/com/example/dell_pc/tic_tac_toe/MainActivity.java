package com.example.dell_pc.tic_tac_toe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // playingPlayer is the current player and "0" means x is playing, 1 means "0" is playing

    int playingPlayer = 0;

    boolean isActive = true;

    // 2 means that the current index's position is unplayed

    int[] keepAWatch = {2,2,2,2,2,2,2,2,2};

    int[][] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    public void dropIn (View view) {

        ImageView counter = (ImageView) view;

        int tag = Integer.parseInt(counter.getTag().toString());

        if (keepAWatch[tag] == 2 && isActive) {

            counter.setTranslationY(-1000f);

            if (playingPlayer == 0) {

                keepAWatch[tag] = 0;

                counter.setImageResource(R.drawable.x);

                playingPlayer = 1;

            }
            else {

                keepAWatch[tag] = 1;

                counter.setImageResource(R.drawable.zero3);

                playingPlayer = 0;


            }

            counter.animate().translationYBy(1000f).rotation(360).setDuration(300);

            for(int[] winningPosition : winningPositions){

                if((keepAWatch[winningPosition[0]] == keepAWatch[winningPosition[1]])
                        && (keepAWatch[winningPosition[1]] == keepAWatch[winningPosition[2]])
                            &&(keepAWatch[winningPosition[0]] != 2)) {
                    String winner = "X";

                    isActive = false;

                    if (keepAWatch[winningPosition[0]] == 1)

                        winner = "0";

                    TextView winnerM = (TextView) findViewById(R.id.winnerMessage);

                    winnerM.setText( winner + " has won!!!");


                    LinearLayout linear = (LinearLayout) findViewById(R.id.playAgainLayout);

                    linear.animate().translationXBy(10f).rotation(360).setDuration(300);

                    linear.setVisibility(view.VISIBLE);


                }
                else{
                    boolean game = true;

                    for(int watch : keepAWatch){
                        if(watch == 2 )
                            game = false;

                    }
                    if(game){
                        TextView winnerM = (TextView) findViewById(R.id.winnerMessage);

                        winnerM.setText( "Its a draw");


                        LinearLayout linear = (LinearLayout) findViewById(R.id.playAgainLayout);

                        linear.animate().translationXBy(10f).rotation(360).setDuration(300);

                        linear.setVisibility(view.VISIBLE);
                    }

                }

            }

        }
    }
    public void playAgain(View view){

        isActive = true;

        LinearLayout linear = (LinearLayout) findViewById(R.id.playAgainLayout);

        linear.setVisibility(view.INVISIBLE);

        playingPlayer = 0;

        for(int i=0; i<keepAWatch.length;i++){

            keepAWatch[i] = 2;
        }
        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);

        for(int i=0; i<gridLayout.getChildCount(); i++){

            ((ImageView)gridLayout.getChildAt(i)).setImageResource(0);


        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
