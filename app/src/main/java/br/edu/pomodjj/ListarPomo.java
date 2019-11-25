package br.edu.pomodjj;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.google.firebase.database.FirebaseDatabase;

public class ListarPomo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_pomo);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView recyclerView = findViewById(R.id.listPomodoro);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        ListPomoAdapter listPomoAdapter = new ListPomoAdapter();
        listPomoAdapter.setActivity(this);

        FirebaseHelper firebase = new FirebaseHelper(FirebaseDatabase.getInstance().getReference(),listPomoAdapter);
        listPomoAdapter.setCiclos(firebase.listar());

        recyclerView.setAdapter(listPomoAdapter);

        FloatingActionButton fab = findViewById(R.id.btnAdicionar);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getApplicationContext(),CadastrarCiclo.class);
                startActivity(it);
            }
        });
    }

}