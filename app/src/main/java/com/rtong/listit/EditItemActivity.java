package com.rtong.listit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class EditItemActivity extends AppCompatActivity {

    EditText etEditedText;
    int position;
    private Spinner spnPriority;
    String[] priorityList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);
        String content = getIntent().getStringExtra("content");
        String priority = getIntent().getStringExtra("priority");
        position = getIntent().getIntExtra("position", 0);

        etEditedText = (EditText) findViewById(R.id.etAddedText);
        spnPriority = (Spinner) findViewById(R.id.spnPriority);


        etEditedText.setText(content);
        etEditedText.setSelection(content.length());

        priorityList = getResources().getStringArray(R.array.priority_list);

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, priorityList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnPriority.setAdapter(dataAdapter);

        int spinnerPosition = dataAdapter.getPosition(priority);
        spnPriority.setSelection(spinnerPosition);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_item, menu);
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
        }

        return super.onOptionsItemSelected(item);
    }

    public void onSave(View view) {

        EditText etName = (EditText) findViewById(R.id.etAddedText);
        Spinner spnPriority = (Spinner) findViewById(R.id.spnPriority);
        Intent data = new Intent();
        data.putExtra("content", etName.getText().toString());
        data.putExtra("position", position);
        data.putExtra("priority", spnPriority.getSelectedItem().toString());
        data.putExtra("code", 200);
        setResult(RESULT_OK, data);
        finish();
    }
}

