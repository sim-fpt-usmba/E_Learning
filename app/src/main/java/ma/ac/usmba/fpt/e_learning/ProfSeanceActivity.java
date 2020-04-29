package ma.ac.usmba.fpt.e_learning;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

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
        androidx.appcompat.widget.Toolbar toolbar= findViewById(R.id.constraintLayout4);
        setSupportActionBar(toolbar);
        ImageView img=findViewById(R.id.GoBackIcon);
        img.setImageResource(R.drawable.trace_52);
        TextView txt=findViewById(R.id.textView);
        txt.setText("Espace Enseignant");
        toolbar.setBackgroundColor(Color.parseColor("#E31731"));
        getSupportActionBar().setDisplayShowTitleEnabled(false);

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
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;

    }
}
