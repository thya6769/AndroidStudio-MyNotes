package com.caqmei.mynotes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.EditText;

import java.util.HashSet;
import java.util.Set;

public class EditActivity extends AppCompatActivity {

    private EditText myNote;
    private Intent i;
    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        myNote = (EditText) findViewById(R.id.myNote);

        i = getIntent();
        index = i.getIntExtra("notesInfo", -1);
        Log.i("NOTES INDEX: ", Integer.toString(index));
        if(index != -1) {
            myNote.setText(MainActivity.myNotes.get(index));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()) {
            case android.R.id.home:

                Set<String> set = new HashSet<>();
                if(index != -1){
                    set = MainActivity.sharedPreferences.getStringSet("notes", null);
                    set.remove(index);
                    MainActivity.myNotes.set(index, myNote.getText().toString());
                } else {
                    MainActivity.myNotes.add(myNote.getText().toString());
                    set.addAll(MainActivity.myNotes);
                }
                MainActivity.sharedPreferences.edit().putStringSet("notes", set).apply();
                MainActivity.arrayAdapter.notifyDataSetChanged();


                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
