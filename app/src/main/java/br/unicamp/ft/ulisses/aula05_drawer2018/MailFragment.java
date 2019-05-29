package br.unicamp.ft.ulisses.aula05_drawer2018;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class MailFragment extends Fragment {
    View lview;
    EditText edtEmail;
    EditText edtBody;

    public MailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        if (lview == null){
            System.out.println("lview destroyed");
            lview = inflater.inflate(R.layout.fragment_mail,
                    container, false);
        } else {
            System.out.println("lview mantida");
        }
        edtEmail = (EditText)lview.findViewById(R.id.setEmail);
        edtBody = (EditText)lview.findViewById(R.id.setBody);

        lview.findViewById(R.id.btn_enviar).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getActivity(),
                                edtEmail.getText().toString()+
                                        " : " +
                                        edtBody.getText().toString(),
                                Toast.LENGTH_SHORT
                                ).show();
                    }
                }

        );

        return lview;
    }
}
