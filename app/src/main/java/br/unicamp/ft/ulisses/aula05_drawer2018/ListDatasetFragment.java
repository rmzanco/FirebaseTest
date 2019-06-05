package br.unicamp.ft.ulisses.aula05_drawer2018;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListDatasetFragment extends Fragment {

    public static class RespostaViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView answer;
        TextView chosen;

        public RespostaViewHolder(@NonNull View v) {
            super(v);
            name    = (TextView) itemView.findViewById(R.id.txtName);
            answer  = (TextView) itemView.findViewById(R.id.txtAnswer);
            chosen  = (TextView) itemView.findViewById(R.id.txtChosen);
        }
    }

    private DatabaseReference mFirebaseDatabaseReference;
    private FirebaseRecyclerAdapter<Resposta, RespostaViewHolder> mFirebaseAdapter;

    public ListDatasetFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View lview = inflater.inflate(R.layout.fragment_list_dataset, container, false);


        return lview;
    }

}
