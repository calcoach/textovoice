package com.example.texttospeech;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TextToSpeech t1;
    Button button;
    ImageButton jokesButton;
    Button clean;
    EditText text;
    int iterator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = (EditText) findViewById(R.id.text);
        button = (Button) findViewById(R.id.button);
        clean = (Button) findViewById(R.id.clean);
        jokesButton = (ImageButton) findViewById(R.id.jokes_button);



        getSupportActionBar().setTitle("");

        t1 = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {

                    t1.setLanguage(Locale.forLanguageTag("es-CO"));
                    String toSpeak = "Bienvenido, escriba en el cuadro lo que quiere que la voz de google diga u oprima contar chiste.";
                    t1.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null, "kl");
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
                t1.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null, "kl");
            }

        });

        clean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                text.setText("");
            }
        });

        final String[] chistes = getResources().getStringArray(R.array.chistes);
        iterator = (int) Math.floor(Math.random() * (100 - 1 + 1) + 1);

        jokesButton.setOnClickListener(clickListener(chistes));
        LinearLayout jokesLayout = (LinearLayout) findViewById(R.id.jokes_layout);
        jokesLayout.setOnClickListener(clickListener(chistes));

    }

    private View.OnClickListener clickListener(final String [] chistes){

       return new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               if (iterator > 99)
                   iterator = 1;

               String toSpeak = chistes[iterator];
               int kl = t1.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null, "kl");
               iterator++;

           }
       };
    }
}
