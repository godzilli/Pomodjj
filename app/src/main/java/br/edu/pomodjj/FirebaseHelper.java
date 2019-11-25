package br.edu.pomodjj;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import java.util.ArrayList;
import br.edu.pomodjj.model.Ciclo;

public class FirebaseHelper {

    DatabaseReference db;
    ArrayList<Ciclo> ciclos=new ArrayList<>();
    ListPomoAdapter adapter;

    public FirebaseHelper(DatabaseReference db, ListPomoAdapter l) {

        this.db = db;
        this.adapter = l;
    }

    //SAVE
    public Ciclo salvar(Ciclo ciclo)
    {
        db.child("ciclos").push().setValue(ciclo);


        return ciclo;
    }

    //READ
    public ArrayList<Ciclo> listar()
    {
        db.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                carregarCiclo(dataSnapshot);
            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                carregarCiclo(dataSnapshot);
            }
            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return ciclos;
    }

    private void carregarCiclo(DataSnapshot dataSnapshot)
    {
        ciclos.clear();
        for (DataSnapshot ds : dataSnapshot.getChildren())
        {
            Ciclo ciclo = new Ciclo();
            if (ds.child("titulo").getValue() != null) {
                ciclo.setTitulo(ds.child("titulo").getValue().toString());
            }
            if(ds.child("tempoDescanso").getValue() != null){
                ciclo.setTempoDescanso(Long.valueOf(ds.child("tempoDescanso").getValue().toString()).intValue());
            }
            if(ds.child("tempoTrabalho").getValue() != null){
                ciclo.setTempoTrabalho(Long.valueOf(ds.child("tempoTrabalho").getValue().toString()).intValue());
            }
            if(ds.child("quantidade").getValue() != null){
                ciclo.setQuantidade(Long.valueOf(ds.child("quantidade").getValue().toString()).intValue());
            }
            ciclos.add(ciclo);
        }
        adapter.notifyDataSetChanged();
    }

}
