package com.self.khetan.miowkapp;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class PhrasesActivity extends AppCompatActivity {

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

    //To make a global sound variable
    MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //To show the xml layout
        setContentView(R.layout.activity_phrases);


        //To create and setup the AudioFocus request Audio Focus
        mAudio = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        //To set up the toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //Creating ArrayList for the phrases
        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("Where are you going?", R.raw.phrase_where_are_you_going,"minto wuksus"
                ));
        words.add(new Word("What is your name?", "tinnә oyaase'nә",
                R.raw.phrase_what_is_your_name));
        words.add(new Word("My name is...", R.raw.phrase_my_name_is, "oyaaset..."));
        words.add(new Word("How are you feeling?", R.raw.phrase_how_are_you_feeling, "michәksәs?"));
        words.add(new Word("I’m feeling good.", R.raw.phrase_im_feeling_good, "kuchi achit"));
        words.add(new Word("Are you coming?", R.raw.phrase_are_you_coming, "әәnәs'aa?"));
        words.add(new Word("Yes, I’m coming.", R.raw.phrase_yes_im_coming, "hәә’ әәnәm"));
        words.add(new Word("I’m coming.", R.raw.phrase_im_coming, "әәnәm"));
        words.add(new Word("Let’s go.", R.raw.phrase_lets_go, "yoowutis"));
        words.add(new Word("Come here.", R.raw.phrase_come_here, "әnni'nem"));

        //Setting up the adapter to control the flow of data
        WordAdapter phrasesAdapter = new WordAdapter(this, words, R.color.phrasesTheme);
        ListView phrasesLV = (ListView) findViewById(R.id.phrasesList);
        phrasesLV.setAdapter(phrasesAdapter);

        //OnClick Listener for every object
        phrasesLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
                    //Preparing MediaPlayer resource for the selected Activity
                    mSound = MediaPlayer.create(PhrasesActivity.this, word.getmSound());
                    //To start playing the sound
                    mSound.start();
                }
                else {
                    //Toast a message that AudioFocus is not present
                    Toast.makeText(PhrasesActivity.this, "No Audio Focus", Toast.LENGTH_SHORT);
                }
            }
        });

        //To set up the floating button in the right-bottom corner
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "These are the phrases", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
