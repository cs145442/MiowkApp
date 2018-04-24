package com.self.khetan.miowkapp;

/**
 * Created by khetan on 11/6/17.
 */

public class Word {

    //Declaring Variables
    private String miowkNum;
    private String engNum;
    private int ic_num;
    private int mSound;

    //Defining Constructors
    /**
     * Create a new Word Object with Image
     *
     * @param m is the word that user is already familiar with
     * @param e is the Miowk word, in translation
     * @param n is the Image resource ID
     */
    Word(String m, String e, int n){
        miowkNum = m;
        engNum = e;
        ic_num = n;
        mSound = 0;
    }

    /**
     * Create a new Word Object without Image
     *
     * @param m is the word that user is already familiar with
     * @param e is the Miowk word, in translation
     */
    Word(String m, String e){
        miowkNum = m;
        engNum = e;
        ic_num = 0;
        mSound = 0;
    }

    /**
     * Create a new Word Object with Image and Audio Prununciation
     *
     * @param m is the word that user is already familiar with
     * @param e is the original word
     * @param n is the ID for the image Source
     * @param sc is the ID for the Sound Clip associated with the object
     */
    Word(String m, String e, int n, int sc){
        miowkNum = m;
        engNum = e;
        ic_num = n;
        mSound = sc;
    }


    /**
     * Create a new Word Object with Audio but without ImageView
     *
     * @param m is the word that user is already familiar with
     * @param e is the original word
     * @param sc is the ID for the Sound Clip associated with the object
     */
    Word(String m, int sc, String e){
        miowkNum = m;
        engNum = e;
        ic_num = 0;
        mSound = sc;
    }


    /**
     * Getting the Parametrs
     * @return is the engNum
     */
    public String getEngNum() {
        return engNum;
    }

    public String getMiowkNum() {
        return miowkNum;
    }

    public int getIc_num() {
        return ic_num;
    }

    public int getmSound() {
        return mSound;
    }
}
