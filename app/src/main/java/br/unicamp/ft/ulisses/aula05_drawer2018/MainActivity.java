package br.unicamp.ft.ulisses.aula05_drawer2018;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import br.unicamp.ft.ulisses.aula05_drawer2018.interfaces.OnBiografiaRequest;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FragmentManager fragmentManager;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        fragmentManager = getSupportFragmentManager();
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();



    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            return true;
        } else if (id == R.id.action_mail) {
            Fragment mailFragment =
                    fragmentManager.findFragmentByTag("mail");
            if (mailFragment == null){
                mailFragment = new MailFragment();
            }
            replaceFragment(mailFragment, "mail");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_alunos) {
            // Handle the camera action
            AlunosFragment alunosFragment =
                    (AlunosFragment) fragmentManager.findFragmentByTag("alunos");
            if (alunosFragment == null){
                alunosFragment = new AlunosFragment();
                alunosFragment.setOnBiografiaRequest(
                        new OnBiografiaRequest() {
                            @Override
                            public void onRequest(int position) {
                                showBiografia(position);
                            }
                        }
                );
            }
            replaceFragment(alunosFragment, "alunos");

        } else if (id == R.id.nav_biografia) {
            showBiografia(-1);
        } else if (id == R.id.nav_idade) {
            IdadeFragment idadeFragment =
                    (IdadeFragment) fragmentManager.findFragmentByTag("idade");
            if (idadeFragment == null){
                idadeFragment = new IdadeFragment();
                idadeFragment.setOnBiografiaRequest(
                        new OnBiografiaRequest() {
                            @Override
                            public void onRequest(int position) {
                                showBiografia(position);
                            }
                        }
                );
            }
            replaceFragment(idadeFragment, "idade");
        } else if (id == R.id.nav_altura) {
            FirebaseDB firebaseDB = (FirebaseDB) fragmentManager.findFragmentByTag("firebase");
            if (firebaseDB == null){
                firebaseDB = new FirebaseDB();
            }
            replaceFragment(firebaseDB, "firebase");
        } else if (id == R.id.nav_coracao_1) {
            CoracaoFragment coracaoFragment =
                    (CoracaoFragment) fragmentManager.findFragmentByTag("coracao");
            if (coracaoFragment == null){
                coracaoFragment = new CoracaoFragment();

            }
            replaceFragment(coracaoFragment, "coracao");
        } else if (id == R.id.nav_coracao_2) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void replaceFragment(Fragment fragment, String tag){
        FragmentTransaction fragmentTransaction =
                fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment, tag);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        fragmentManager.executePendingTransactions();
    }

    private void showBiografia(int position){
        BiografiasFragment biografiasFragment =
                (BiografiasFragment) fragmentManager.findFragmentByTag("biografia");
        if (biografiasFragment == null){
            biografiasFragment = new BiografiasFragment();
        }
        if (position != -1) {
            biografiasFragment.setArguments(position);
        }
        replaceFragment(biografiasFragment, "biografia");
    }
}
