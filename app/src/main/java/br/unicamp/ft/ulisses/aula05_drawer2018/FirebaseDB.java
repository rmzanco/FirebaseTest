package br.unicamp.ft.ulisses.aula05_drawer2018;


import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

import br.unicamp.ft.ulisses.aula05_drawer2018.interfaces.OnBiografiaRequest;

import static android.support.constraint.Constraints.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class FirebaseDB extends Fragment {

    private View lview;

    private Random random = new Random();
    private int idadeCorreta;
    private int positionAluno;
    private int numTentativas;

    private String name;
    private String answer;
    private String guess;

    private ImageView imageView;
    private TextView  txtNome;
    private TextView  txtTentativas;
    private TextView  txtFeedback;

    private OnBiografiaRequest onBiografiaRequest;

    private DatabaseReference mFirebaseDatabaseReference;

    public void setOnBiografiaRequest(OnBiografiaRequest onBiografiaRequest) {
        this.onBiografiaRequest = onBiografiaRequest;
    }

    public FirebaseDB() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (lview == null) {
            lview = inflater.inflate(R.layout.fragment_idade, container, false);
        }

        imageView     = lview.findViewById(R.id.imageFoto);
        txtNome       = lview.findViewById(R.id.txtNome);
        txtTentativas = lview.findViewById(R.id.txtTentativas);
        txtFeedback   = lview.findViewById(R.id.txtFeedback);
        mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();

        startGame();

        View.OnClickListener guessButtonListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int idadeEscolhida = Integer.parseInt( ((Button)v).getText().toString() );
                if (idadeEscolhida == idadeCorreta){
                    txtFeedback.setText("Correto!!");
                    new Handler().postDelayed(
                            new Runnable()
                            {
                                @Override
                                public void run()
                                {
                                    startGame();
                                }
                            }, 2000);
                } else {
                    txtFeedback.setText("Incorreto!!");
                    numTentativas--;
                    txtTentativas.setText("Tentativas: "+numTentativas);

                    if (numTentativas == 0){
                        txtFeedback.setText("Você Perdeu!!");

                        new Handler().postDelayed(
                                new Runnable()
                                {
                                    @Override
                                    public void run()
                                    {
                                        if (onBiografiaRequest != null){
                                            onBiografiaRequest.onRequest(positionAluno);
                                        }
                                    }
                                }, 2000);

                    }

                }

                name = txtNome.getText().toString();
                answer = String.valueOf(idadeCorreta);
                guess = String.valueOf(idadeEscolhida);

                Resposta resposta = new Resposta(name,answer,guess);
                mFirebaseDatabaseReference.child("respostas").push().setValue(resposta);

                mFirebaseDatabaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot remoteRespostas : dataSnapshot.getChildren()){
                            for (DataSnapshot remorteResposta : remoteRespostas.getChildren()){
                                Resposta resposta = remorteResposta.getValue(Resposta.class);
                                Log.v("DATASET",resposta.getAnswer()+"-"+resposta.getChosen());
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.w(TAG, "Failed to read value.", error.toException());
                    }
                });

                mFirebaseDatabaseReference.limitToLast(12);

            }
        };

        lview.findViewById(R.id.button1).setOnClickListener(guessButtonListener);
        lview.findViewById(R.id.button2).setOnClickListener(guessButtonListener);
        lview.findViewById(R.id.button3).setOnClickListener(guessButtonListener);
        lview.findViewById(R.id.button4).setOnClickListener(guessButtonListener);
        lview.findViewById(R.id.button5).setOnClickListener(guessButtonListener);
        lview.findViewById(R.id.button6).setOnClickListener(guessButtonListener);
        lview.findViewById(R.id.button7).setOnClickListener(guessButtonListener);
        lview.findViewById(R.id.button8).setOnClickListener(guessButtonListener);
        lview.findViewById(R.id.button9).setOnClickListener(guessButtonListener);

        return lview;
    }

    private void startGame(){
        int guess = random.nextInt(Alunos.alunos.length);
        positionAluno = guess;
        Aluno aluno = Alunos.alunos[guess];
        idadeCorreta = aluno.getIdade();
        imageView.setImageResource(aluno.getFoto());
        txtNome.setText(aluno.getNome());
        numTentativas = 3;
        txtTentativas.setText("Tentativas: "+numTentativas);
        txtFeedback.setText("");

    }

}
