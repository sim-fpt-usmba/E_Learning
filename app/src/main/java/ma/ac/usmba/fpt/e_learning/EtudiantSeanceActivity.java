package ma.ac.usmba.fpt.e_learning;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

public class EtudiantSeanceActivity extends AppCompatActivity {

    //Fragment page adapter
    EtudiantSeanceFPA etudiantSeanceFPA;

    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etudiant_seance);

        etudiantSeanceFPA = new EtudiantSeanceFPA(getSupportFragmentManager());

        viewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(viewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

    }

    public void setupViewPager(ViewPager viewPager) {
        EtudiantSeanceFPA adapter = new EtudiantSeanceFPA(getSupportFragmentManager());
        adapter.addFragment(new EtudiantSeanceContenuFrag(), "Contenu");
        adapter.addFragment(new EtudiantSeanceDiscussionFrag(), "Discussion");
        adapter.addFragment(new EtudiantSeanceQuizFrag(), "Quiz");
        viewPager.setAdapter(adapter);

    }

    public void goback(View view) {
    }
}
