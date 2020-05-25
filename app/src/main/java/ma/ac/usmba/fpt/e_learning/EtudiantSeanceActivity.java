package ma.ac.usmba.fpt.e_learning;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Consumer;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import ma.ac.usmba.fpt.e_learning.Controller.EtudiantAPI;
import ma.ac.usmba.fpt.e_learning.Model.Etudiant;
import ma.ac.usmba.fpt.e_learning.Model.Seance;

public class EtudiantSeanceActivity extends AppCompatActivity {
    private SharedPreferences sessionData;
    private EtudiantAPI API;
    public Etudiant etudiant;
    public Seance seance;

    //Fragment page adapter
    EtudiantSeanceFPA etudiantSeanceFPA;

    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etudiant_seance);

        etudiantSeanceFPA = new EtudiantSeanceFPA(getSupportFragmentManager());

        viewPager = (ViewPager) findViewById(R.id.container);


        //Intent intent = getIntent();
        int seance_id = 1;//Integer.parseInt( intent.getStringExtra("seance_id") );
        API = new EtudiantAPI();

        sessionData = getSharedPreferences("user_details",MODE_PRIVATE);

        if( sessionData.contains("Etudiant") ){
            etudiant = API.Object_from_Json(
                    sessionData.getString("Etudiant", null )
            );

            // get seance

            API.seance(
                    etudiant.build_access_token(),
                    seance_id,
                    new Consumer<Seance>() {
                        @Override
                        public void accept(Seance s) {
                            seance = s;
                            Toast.makeText(EtudiantSeanceActivity.this, seance.getTitre(), Toast.LENGTH_SHORT).show();
                            setupViewPager(viewPager);

                            TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
                            tabLayout.setupWithViewPager(viewPager);
                        }
                    },
                    new Consumer<String>() {
                        @Override
                        public void accept(String error) {
                            // oubtou_log : seance not found.
                            Toast.makeText(EtudiantSeanceActivity.this, error, Toast.LENGTH_SHORT).show();
                        }
                    }
            );

            /*

            // oubtou_log : get messages

            API.messages(
                    etudiant.build_access_token(),
                    seance.getId(),
                    0, // oubtou_log : start from ID - for pagination give the last message id to load new messages if exist.
                    new Consumer<ArrayList<Message>>() {
                        @Override
                        public void accept(ArrayList<Message> messages_) {
                            Toast.makeText(EtudiantSeanceActivity.this, ""+messages_.size(), Toast.LENGTH_SHORT).show();
                        }
                    },
                    new Consumer<String>() {
                        @Override
                        public void accept(String error) {
                            // oubtou_log : messages not found.
                            Toast.makeText(EtudiantSeanceActivity.this, error, Toast.LENGTH_SHORT).show();
                        }
                    }
            );

            // oubtou_log : send message

            API.send_message(
                    etudiant.build_access_token(),
                    seance.getId(),
                    "your message : this is a simple message sent from mobile app",
                    new Consumer<Boolean>() {
                        @Override
                        public void accept(Boolean b) {
                            //seance = s;
                            Log.d("oubtou__", b.toString());
                            //Toast.makeText(EtudiantSeanceActivity.this, seance.getTitre(), Toast.LENGTH_SHORT).show();
                        }
                    },
                    new Consumer<String>() {
                        @Override
                        public void accept(String error) {
                            // oubtou_log : seance not found.
                            //Toast.makeText(EtudiantSeanceActivity.this, error, Toast.LENGTH_SHORT).show();
                        }
                    }
            );
            */
        }else{
            // user not exit you need to redirect user to login form
        }

    }

    public void setupViewPager(ViewPager viewPager) {
        EtudiantSeanceFPA adapter = new EtudiantSeanceFPA(getSupportFragmentManager());

        /*
            oubtou_log : this just a simple solution to use seance variable
                         but you need to structer your architecture before
                         we have a Seance Model than for what we need a ContenuModel ???

        EtudiantSeanceContenuFrag ESCF = new EtudiantSeanceContenuFrag();
        ContenuModel cm  = ESCF.contenu;
        cm.setDescription( seance.getContenu() );
        ESCF.contenu = cm;
        adapter.addFragment(ESCF, "Contenu");
        */

        adapter.addFragment(new EtudiantSeanceContenuFrag(), "Contenu");
        adapter.addFragment(new SeanceDiscussionFrag(), "Discussion");
        adapter.addFragment(new EtudiantSeanceQuizFrag(), "Quiz");
        viewPager.setAdapter(adapter);
    }

    public void goback(View view) {
        finish();
    }
}
