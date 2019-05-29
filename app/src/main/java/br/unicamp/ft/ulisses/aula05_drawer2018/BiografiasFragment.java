package br.unicamp.ft.ulisses.aula05_drawer2018;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class BiografiasFragment extends Fragment {
    private int biografiaCount = 0;
    private View lview;

    private ImageView imageView;
    private TextView txtNome;
    private TextView  txtRa;
    private TextView  txtDescription;

    public BiografiasFragment() {
        // Required empty public constructor
    }

    public void setArguments(int position){
        biografiaCount = position;
    }

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        if (lview == null){
            lview = inflater.inflate(R.layout.fragment_biografias, container, false);
        }


        imageView = lview.findViewById(R.id.imageView);
        txtNome   = lview.findViewById(R.id.nome);
        txtRa     = lview.findViewById(R.id.ra);
        txtDescription = lview.findViewById(R.id.description);

        Button btn = lview.findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                biografiaCount++;
                mostrarBiografia();
            }
        });

        return lview;
    }

    private void mostrarBiografia(){
        Aluno aluno = Alunos.alunos[biografiaCount % Alunos.alunos.length];
        imageView.setImageResource(aluno.getFoto());
        txtNome.setText(aluno.getNome());
        txtRa.setText(aluno.getRa());
        txtDescription.setText(Html.fromHtml(aluno.getMiniCV()));
    }

    @Override
    public void onStart(){
        super.onStart();
        mostrarBiografia();
    }

}
