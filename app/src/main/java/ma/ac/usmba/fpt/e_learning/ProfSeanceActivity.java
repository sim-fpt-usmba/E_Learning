package ma.ac.usmba.fpt.e_learning;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class ProfSeanceActivity extends AppCompatActivity {

    //Fragment page adapter
    ProfSeanceFPA profSeanceFPA;

    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prof_seance);

        profSeanceFPA = new ProfSeanceFPA(getSupportFragmentManager());

        viewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(viewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

    }

    public void setupViewPager(ViewPager viewPager) {
        ProfSeanceFPA adapter = new ProfSeanceFPA(getSupportFragmentManager());
        adapter.addFragment(new ProfSeanceContenuFrag(), "Contenu");
        adapter.addFragment(new SeanceDiscussionFrag(), "Discussion");
        adapter.addFragment(new ProfSeanceQuizFrag(), "Quiz");
        viewPager.setAdapter(adapter);

    }

    public void goback(View view) {
        finish();
    }
}
