package e.nick.caps;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class caps_activity extends AppCompatActivity {

    private Game game;
    private String prevQuestion = "";
    private String answer = "";
    private String log = "";
    private int score = 0;
    private int qNum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.caps_layout);
        ask();
    }

    //Ask the player a new question
    private void ask(){
        game = new Game();
        String question = game.qa();
        prevQuestion = question;

        ((TextView) findViewById(R.id.questionBox)).setText(question);
        qNum++;
        String questionNumber = "Q#" + qNum;
        ((TextView) findViewById(R.id.questionNum)).setText(questionNumber);
    }

    /* Checks if the given answer matches the answer key
     * If answer is correct, the score is incremented
     * If the game is not over, ask the player a new question, else call the finish method to end
     */
    public void onDone(View v){
        String playerAnswer = ((EditText) findViewById(R.id.answerField)).getText().toString().toUpperCase();
        answer = game.getAnswer();
        log = "\nQ#" + " " + qNum + prevQuestion + "\n" + "Your Answer: " + playerAnswer
                + "\n" +  "Correct Answer: " +answer + "\n" + log + "\n";
        ((TextView) findViewById(R.id.previousAnswer)).setText(log);
        final int MAX_NUM_QUESTIONS = 9;
        if(qNum < MAX_NUM_QUESTIONS) {

            if (playerAnswer.equals(game.getAnswer().toUpperCase())) {
                score++;
                ((TextView) findViewById(R.id.score)).setText("Score = " + score);
            }
            ask();

        } else {

            if (playerAnswer.equals(game.getAnswer().toUpperCase())) {
                score++;
                ((TextView) findViewById(R.id.score)).setText("Score = " + score);
            }
            finish();
        }
    }

    //Self-explanatory, ends the game and prevents further action from the player
    public void finish(){
        ((TextView) findViewById(R.id.questionBox)).setText("Game Over");
        ((Button) findViewById(R.id.doneButton)).setOnClickListener(null);
    }
}
