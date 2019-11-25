package br.edu.pomodjj;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import br.edu.pomodjj.model.Ciclo;

public class CadastrarCiclo extends AppCompatActivity {

    private Ciclo ciclo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_ciclo);

        if(getIntent().getExtras() != null){
            ciclo = (Ciclo) getIntent().getExtras().getSerializable("ciclo");


        }
    }

    public void onClick (View view){
        Intent it = new Intent(getApplicationContext(),ListarPomo.class);
        startActivity(it);
    }

    public void salvar(View view){
        TextView txtTitulo = findViewById(R.id.txtTitulo);
        TextView txtQuantPomo = findViewById(R.id.txtQuantPomo);
        TextView txtTempoTrabalho = findViewById(R.id.txtTempoTrabalho);
        TextView txtTempoDescanso = findViewById(R.id.txtTempoDescanso);

        if(ciclo == null){
            ciclo = new Ciclo();
        }

        ciclo.setTitulo(txtTitulo.getText().toString());
        ciclo.setQuantidade(Integer.parseInt(txtQuantPomo.getText().toString()));
        ciclo.setTempoTrabalho(Integer.parseInt(txtTempoTrabalho.getText().toString()));
        ciclo.setTempoDescanso(Integer.parseInt(txtTempoDescanso.getText().toString()));

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("ciclos");

        myRef.push().setValue(ciclo);

        onClick(view);
    }
}
