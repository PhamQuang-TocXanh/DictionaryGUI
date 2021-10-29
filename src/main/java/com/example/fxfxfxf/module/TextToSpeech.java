package com.example.fxfxfxf.module;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class TextToSpeech {
    /**
     * Text to speech
     */
    public static void textToSpeech(String text) {
        if(text == "") return;
        //set Kevin Directory.
        System.setProperty( "freetts.voices", "com.sun.speech.freetts.en.us"
                + ".cmu_us_kal.KevinVoiceDirectory");
        Voice voice;//Creating object of Voice class
        voice = VoiceManager.getInstance().getVoice("kevin16");//Getting voice
        try {
            voice.allocate();//Allocating Voice
            voice.speak(text);//Calling speak() method
            voice.deallocate();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
