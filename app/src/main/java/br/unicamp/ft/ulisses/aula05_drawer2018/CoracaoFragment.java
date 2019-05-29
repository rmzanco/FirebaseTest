package br.unicamp.ft.ulisses.aula05_drawer2018;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class CoracaoFragment extends Fragment {
    View lview;
    ImageView imgPessoa;
    ImageView imgCoracao;
    TextView txtFeedback;
    MyImageRotater rotatePessoa;
    MyImageRotater rotateTimes;
    int[] idCoracoes;

    public CoracaoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        if (lview == null) {
            lview = inflater.inflate(R.layout.fragment_coracao, container, false);
        }

        imgPessoa  = lview.findViewById(R.id.pessoa);
        imgCoracao = lview.findViewById(R.id.coracao);
        txtFeedback = lview.findViewById(R.id.txtFeedback);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkThreads();
            }
        };
        imgCoracao.setOnClickListener(
                onClickListener
        );


    //        int[] imageCoraco  = new int[];


        return lview;
    }

    public void checkThreads(){
        if (rotateTimes != null && rotatePessoa != null){
            rotatePessoa.pauseRunning();
            rotateTimes.pauseRunning();
            Aluno aluno = Alunos.alunos[rotatePessoa.getLocalRotation()];
            int rightTime  = aluno.getTime();
            int chosenTime = idCoracoes[rotateTimes.getLocalRotation()];
            if (rightTime == chosenTime){
                txtFeedback.setText("Correto");
            } else {
                txtFeedback.setText("Incorreto");
            }
        }
    }

    @Override
    public void onStart(){
        super.onStart();

        int[] idFotos = new int[Alunos.alunos.length];
        for (int i = 0; i < Alunos.alunos.length; i++){
            idFotos[i] = Alunos.alunos[i].getFoto();
        }

        idCoracoes = new int[]{
                R.drawable.celtic_fc,
                R.drawable.corinthians,
                R.drawable.fla,
                R.drawable.guarani,
                R.drawable.pontepreta,
                R.drawable.palmeiras,
                R.drawable.paysandu_pa,
                R.drawable.riobranco_ac,
                R.drawable.santos,
                R.drawable.saopaulo,
                R.drawable.undefined
        };


        rotatePessoa = new MyImageRotater(imgPessoa, idFotos, 6000);
        rotatePessoa.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        rotateTimes = new MyImageRotater(imgCoracao, idCoracoes, 800);
        rotateTimes.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

    }

    @Override
    public void onStop(){
        super.onStop();
        rotatePessoa.stopRunning();
        rotateTimes.stopRunning();
    }



    class MyImageRotater extends AsyncTask<String, Integer, Boolean> {

        private ImageView imageView;
        private int[] images;
        private int delay;
        private Boolean keepRunning;
        private Boolean pauseRunning;
        private int   count;


        public MyImageRotater(ImageView imageView, int[] images, int delay) {
            this.imageView = imageView;
            this.images = images;
            this.delay = delay;
            keepRunning = true;
            pauseRunning = false;
        }

        public void stopRunning() {
            keepRunning = false;
        }

        public void pauseRunning(){
            pauseRunning = true;
        }

        @Override
        protected void onPreExecute() {
        }

        public int getLocalRotation(){
            return count % images.length;
        }

        @Override
        protected Boolean doInBackground(String... args) {
            count = 0;
            while (keepRunning) {
                try {
                    Thread.sleep(delay);
                    if (pauseRunning){
                        Thread.sleep(3000);
                        pauseRunning = false;
                    }
                } catch (InterruptedException e) {
                    System.out.println("InterruptedException");
                }
                count++;
                publishProgress(count % images.length);
            }
            return true;
        }

        @Override
        protected void onProgressUpdate(Integer... args) {

            if (args[0] != null) {
                imageView.setImageResource(images[args[0]]);
            }
        }

        @Override
        protected void onPostExecute(Boolean args) {

        }
    }
}
