package br.unicamp.ft.ulisses.aula05_drawer2018;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyFirstAdapter extends RecyclerView.Adapter {
    private ArrayList<Aluno> alunos;
    private MyOnItemClickListener myOnItemClickListener;
    private MyOnLongItemClickListener myOnLongItemClickListener;

    public interface MyOnItemClickListener {
        void myOnItemClick(String nome);
    }

    public interface MyOnLongItemClickListener {
        void myOnLongItemClick(int position);
    }

    public MyFirstAdapter(ArrayList alunos){
        this.alunos = alunos;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_layout, parent, false);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (myOnItemClickListener != null) {
                    TextView txt = view.findViewById(R.id.nome);
                    myOnItemClickListener.myOnItemClick(txt.getText().toString());
                }
            }
        });

        final MyFirstViewHolder holder = new MyFirstViewHolder(v);

        v.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (myOnLongItemClickListener != null){
                    myOnLongItemClickListener.myOnLongItemClick(holder.position);
                }
                return false;
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((MyFirstViewHolder)holder).bind(alunos.get(position), position);

    }

    @Override
    public int getItemCount() {
        return alunos.size();
    }

    public void setMyOnItemClickListener(MyOnItemClickListener myOnItemClickListener){
        this.myOnItemClickListener = myOnItemClickListener;
    }

    public void setMyOnLongItemClickListener(MyOnLongItemClickListener myOnLongItemClickListener){
        this.myOnLongItemClickListener = myOnLongItemClickListener;
    }

    class MyFirstViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private TextView  txtNome;
        private TextView  txtRa;
        private TextView  txtDescription;
        private int position;

        public MyFirstViewHolder(final View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            txtNome   = itemView.findViewById(R.id.nome);
            txtRa     = itemView.findViewById(R.id.ra);
            txtDescription = itemView.findViewById(R.id.description);
            position = 0;
        }

        public void bind(final Aluno aluno, int position) {
            imageView.setImageResource(aluno.getFoto());
            txtNome.setText(aluno.getNome());
            txtRa.setText(aluno.getRa());
            txtDescription.setText(Html.fromHtml(aluno.getMiniCV()));
            this.position = position;
        }
    }
}
