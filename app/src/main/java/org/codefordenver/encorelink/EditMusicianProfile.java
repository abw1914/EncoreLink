package org.codefordenver.encorelink;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.r0adkll.slidr.Slidr;

import java.util.ArrayList;

public class EditMusicianProfile extends AppCompatActivity {

    ListView editMusicianrofileListView;
    private ArrayList<String> userInfo = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Slidr.attach(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_musician_profile);

        editMusicianrofileListView = findViewById(R.id.edit_musician_profile);

        userInfo.add(LoginFragment.musician.toString());
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, userInfo);
       editMusicianrofileListView.setAdapter(arrayAdapter);

    }
}
