package com.example.liftlog;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.LinkedList;

public class workout_view extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_view);
        LinearLayout scroll = (LinearLayout) findViewById(R.id.linearInScroll);

        Workout testW = new Workout(1, "myWorkout", "get big or die trying");
        ExerciseStats bench = new ExerciseStats(0, 150, 5, 1);
        ExerciseStats squat = new ExerciseStats(1, 225, 5, 1);
        ExerciseStats deadlift = new ExerciseStats(2, 300, 5, 1);
        ExerciseStats overheadpress = new ExerciseStats(3, 100, 5, 1);
        squat.trigger_max_change = true;


        LinkedList<ExerciseStats> exerciseArray = testW.statsList;
        exerciseArray.add(bench);
        exerciseArray.add(squat);
        exerciseArray.add(deadlift);
        exerciseArray.add(overheadpress);


        View.OnClickListener listener = new View.OnClickListener() {
            public void onClick(View view) {
                int viewID = (int) view.getTag();
                for (int i = 0; i < scroll.getChildCount(); i++) {

                    View v = scroll.getChildAt(i);
                    int compareID = (int) v.getTag();

                    TextView exerciseName = (TextView) v.findViewById(R.id.exerciseName);
                    boolean tempTrigger = (boolean) exerciseName.getTag();

                    if(compareID == viewID){

                        if(tempTrigger){
                            TextView tempReps = (TextView) v.findViewById(R.id.exerciseReps);
                            String maxRepsString = tempReps.getText().toString().trim();
                            if (!TextUtils.isEmpty(maxRepsString)) {
                                int maxReps = Integer.parseInt(maxRepsString);
                                Log.d("Dam boi you flexing.. ", maxRepsString + " reps ?!?");
                            }
                            else{
                                Log.d("Where the reps at??", "enter some reps");
                            }
                        }

                        else {
                            scroll.removeView(v);
                        }

                    }
                }

            }
        };

        LayoutInflater inflater = getLayoutInflater();
        for(ExerciseStats currentExercise : exerciseArray) {
            int exID = currentExercise.exercise;
            int exWeight = currentExercise.weight;
            int exReps = currentExercise.reps;
            int exSets = currentExercise.sets;
            boolean exTrigger = currentExercise.trigger_max_change;

            //KEY:
            // newLayout -> ID
            // exerciseName -> trigger_max_change
            // btn -> ID


            ConstraintLayout newLayout = (ConstraintLayout) inflater.inflate(R.layout.workout_template, scroll,false);
            newLayout.setTag(exID);

            TextView exerciseName = (TextView) newLayout.findViewById(R.id.exerciseName);
            exerciseName.setText("ID: " + exID);
            exerciseName.setTag(exTrigger);

            TextView exerciseWeight = (TextView) newLayout.findViewById(R.id.exerciseWeight);
            exerciseWeight.setText(String.valueOf(exWeight));

            TextView exerciseReps = (TextView) newLayout.findViewById(R.id.exerciseReps);
            if(!exTrigger) exerciseReps.setText(String.valueOf(exReps));

            TextView exerciseSets = (TextView) newLayout.findViewById(R.id.exerciseSets);
            exerciseSets.setText(String.valueOf(exSets));

            Button btnDone = (Button) newLayout.findViewById(R.id.btnDone);
            btnDone.setTag(exID);
            btnDone.setOnClickListener(listener);

            // Set push exercise stats
            if(exTrigger){
                btnDone.setBackgroundTintList(MyApplication.getContext().getResources().getColorStateList(R.color.btn_yellow));
                btnDone.setTextColor(getResources().getColor(R.color.black));
                exerciseName.setTextColor(getResources().getColor(R.color.btn_yellow));
                exerciseReps.setHint("∞");
                exerciseReps.setClickable(true);
                exerciseReps.setFocusable(true);
                exerciseReps.setFocusableInTouchMode(true);
                exerciseReps.setCursorVisible(true);
            }

            scroll.addView(newLayout);
        }




    }
}