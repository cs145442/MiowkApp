package com.self.khetan.miowkapp;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    //mSound Variable Declaration
    private MediaPlayer mSound;
    private AudioManager mAudio;

    /**
     * This listener gets called when AudioFocus changes
     *
     */
    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK)
            {
                mSound.pause();
                mSound.seekTo(0);
            }
            else if (focusChange == AudioManager.AUDIOFOCUS_LOSS){
                mSound.release();
            }
            else if (focusChange == AudioManager.AUDIOFOCUS_GAIN){
                mSound.start();
            }

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //To set up the activity
        setContentView(R.layout.activity_numbers);

        //To create and setup the AudioFocus request Audio Focus
        mAudio = (AudioManager) getSystemService(Context.AUDIO_SERVICE);


        //Creating ArrayList of the Numbers
        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("ek", "one", R.drawable.number_one, R.raw.number_one));
        words.add(new Word("do", "two", R.drawable.number_two, R.raw.number_two));
        words.add(new Word("teen", "three", R.drawable.number_three, R.raw.number_three));
        words.add(new Word("char", "four", R.drawable.number_four, R.raw.number_four));
        words.add(new Word("paanch", "five", R.drawable.number_five, R.raw.number_five));
        words.add(new Word("chhe", "six", R.drawable.number_six, R.raw.number_six));
        words.add(new Word("saat", "seven", R.drawable.number_seven, R.raw.number_seven));
        words.add(new Word("aath", "eight", R.drawable.number_eight, R.raw.number_eight));
        words.add(new Word("noo", "nine", R.drawable.number_nine, R.raw.number_nine));
        words.add(new Word("dus", "ten", R.drawable.number_ten, R.raw.number_ten));

        //Array Adapter Concept
        WordAdapter itemsAdapter = new WordAdapter(this, words, R.color.numbersTheme);
        final ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(itemsAdapter);

        //OnClick Listener for every object
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //Getting the word which is clicked
                Word word = words.get(position);

                //Clearing Previously held Resources
                //mSound.release();
                //Preparing AudioFocus for playing sound
                int audioFocusResult = mAudio.requestAudioFocus(mOnAudioFocusChangeListener,
                        //to stream music
                        AudioManager.STREAM_MUSIC,
                        //for short period of time
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                //Check for status of AudioFocus
                if (audioFocusResult == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    Toast.makeText(NumbersActivity.this, "Audio Focus: " + audioFocusResult, Toast.LENGTH_SHORT).show();
                    //Preparing MediaPlayer resource for the selected Activity
                    mSound = MediaPlayer.create(NumbersActivity.this, word.getmSound());
                    //To start playing the sound
                    mSound.start();
                }
                else {
                    //Toast a message that AudioFocus is not present
                    Toast.makeText(NumbersActivity.this, "No Audio Focus", Toast.LENGTH_SHORT);
                }
            }
        });

    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }


    /**
     * To release all the resources used by the media Players
     */
    private void releaseMediaPlayer(){
        mSound = null;
        mAudio.abandonAudioFocus(mOnAudioFocusChangeListener);
    }
}
