package br.edu.pomodjj;


import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Chronometer;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

import br.edu.pomodjj.model.Ciclo;

public class Pomodoro_Tarefa extends AppCompatActivity {

    private Ciclo ciclo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pomodoro__tarefa);

        if(getIntent().getExtras() != null){
            ciclo = (Ciclo) getIntent().getExtras().getSerializable("ciclo");

            TextView txtTitulo = findViewById(R.id.txtTitulo);

            txtTitulo.setText(ciclo.getTitulo());

            final TextView txtCronometro = findViewById(R.id.txtCronometro);
            new CountDownTimer(ciclo.getTempoTrabalho() * 60000, 1000) {

                public void onTick(long millisUntilFinished) {
                    String tempo = String.format("%02d:%02d",
                            TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                            TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                                    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))
                    );

                    txtCronometro.setText(tempo);
                }

                public void onFinish() {
                    txtCronometro.setText("done!");

                    Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        v.vibrate(VibrationEffect.createOneShot(2000, VibrationEffect.DEFAULT_AMPLITUDE));
                    } else {
                        v.vibrate(2000);
                    }

                    new CountDownTimer(ciclo.getTempoDescanso() * 60000, 1000) {

                        public void onTick(long millisUntilFinished) {
                            String tempo = String.format("%02d:%02d",
                                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                                    TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))
                            );

                            txtCronometro.setText(tempo);
                        }

                        public void onFinish() {
                            txtCronometro.setText("done!");

                            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                v.vibrate(VibrationEffect.createOneShot(2000, VibrationEffect.DEFAULT_AMPLITUDE));
                            } else {
                                v.vibrate(2000);
                            }
                        }

                    }.start();
                }

            }.start();

        }

    }

    public void Parar(View view){
        Intent it = new Intent(getApplicationContext(), ListarPomo.class);
        startActivity(it);
    }

}
