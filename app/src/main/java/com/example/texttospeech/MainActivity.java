package com.example.texttospeech;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TextToSpeech t1 ;
    Button button ;
    Button clean;
    EditText text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = (EditText) findViewById(R.id.text);
        button  = (Button) findViewById(R.id.button);
        clean = (Button) findViewById(R.id.clean);

        t1 = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR){

                    t1.setLanguage(Locale.forLanguageTag("es-CO"));
                }
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String toSpeak = text.getText().toString();
                /*Toast.makeText(getApplicationContext(), toSpeak,Toast.LENGTH_SHORT).show();
                AudioManager am = (AudioManager)getSystemService(getApplicationContext().AUDIO_SERVICE);
                int amStreamMusicMaxVol = am.getStreamMaxVolume(am.STREAM_MUSIC);
                am.setStreamVolume(am.STREAM_MUSIC, amStreamMusicMaxVol, 0);*/
                t1.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null,"kl");
            }

        });

        clean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                text.setText("");
            }
        });
    }

    @Override
    public void onPause(){

        if(t1 !=null){
            t1.stop();
            t1.shutdown();
        }
        super.onPause();
    }
}
