package br.unicamp.ft.ulisses.aula05_drawer2018;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

import br.unicamp.ft.ulisses.aula05_drawer2018.interfaces.OnBiografiaRequest;


/**
 * A simple {@link Fragment} subclass.
 */
public class AlunosFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private MyFirstAdapter mAdapter;

    private OnBiografiaRequest onBiografiaRequest;

    public void setOnBiografiaRequest(OnBiografiaRequest onBiografiaRequest) {
        this.onBiografiaRequest = onBiografiaRequest;
    }

    public AlunosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View lview = inflater.inflate(R.layout.fragment_alunos, container, false);

        mRecyclerView = (RecyclerView) lview.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mAdapter = new MyFirstAdapter(new ArrayList(Arrays.asList(Alunos.alunos)));
        mRecyclerView.setAdapter(mAdapter);

        /*
        Adicionando o Listener
         */
        mAdapter.setMyOnItemClickListener(new MyFirstAdapter.MyOnItemClickListener() {
            @Override
            public void myOnItemClick(String nome) {
                Toast.makeText(getContext(),nome,Toast.LENGTH_SHORT).show();
            }
        });

        mAdapter.setMyOnLongItemClickListener(new MyFirstAdapter.MyOnLongItemClickListener() {
            @Override
            public void myOnLongItemClick(int position) {
                if (onBiografiaRequest != null){
                    onBiografiaRequest.onRequest(position);
                }
            }
        });


        return lview;
    }

}
