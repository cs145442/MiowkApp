package com.self.khetan.miowkapp;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_colors);


        //To create and setup the AudioFocus request Audio Focus
        mAudio = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        // Create a list of words
        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("red", "weṭeṭṭi", R.drawable.color_red, R.raw.color_red));
        words.add(new Word("mustard yellow", "chiwiiṭә", R.drawable.color_mustard_yellow,
                R.raw.color_mustard_yellow));
        words.add(new Word("dusty yellow", "ṭopiisә", R.drawable.color_dusty_yellow,
                R.raw.color_dusty_yellow));
        words.add(new Word("green", "chokokki", R.drawable.color_green, R.raw.color_green));
        words.add(new Word("brown", "ṭakaakki", R.drawable.color_brown, R.raw.color_brown));
        words.add(new Word("gray", "ṭopoppi", R.drawable.color_gray, R.raw.color_gray));
        words.add(new Word("black", "kululli", R.drawable.color_black, R.raw.color_black));
        words.add(new Word("white", "kelelli", R.drawable.color_white, R.raw.color_white));

        // Create an {@link WordAdapter}, whose data source is a list of {@link Word}s. The
        // adapter knows how to create list items for each item in the list.
        WordAdapter adapter = new WordAdapter(this, words, R.color.colorsTheme);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // word_list.xml layout file.
        ListView listView = (ListView) findViewById(R.id.colorsList);

        // Make the {@link ListView} use the {@link WordAdapter} we created above, so that the
        // {@link ListView} will display list items for each {@link Word} in the list.
        listView.setAdapter(adapter);

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
                    //Preparing MediaPlayer resource for the selected Activity
                    mSound = MediaPlayer.create(ColorsActivity.this, word.getmSound());
                    //To start playing the sound
                    mSound.start();
                }
                else {
                    //Toast a message that AudioFocus is not present
                    Toast.makeText(ColorsActivity.this, "No Audio Focus", Toast.LENGTH_SHORT);
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
