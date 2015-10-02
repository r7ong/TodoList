package com.rtong.listit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private final int EDIT_REQUEST_CODE = 20;
    private final int ADD_REQUEST_CODE = 40;

    ArrayList<Item> todoItems = new ArrayList<Item>();
    ItemAdapter itemAdapter;
    ListView lvItems;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DBHelper(this, null, null, 1);
        System.out.println("in-- main before read");
        todoItems = dbHelper.readItems();
        for(Item i:todoItems){
            System.out.println(i.getContent());
        }


        itemAdapter = new ItemAdapter(this, todoItems);
        lvItems = (ListView) findViewById(R.id.lvItems);
        lvItems.setAdapter(itemAdapter);

        lvItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                todoItems.remove(position);
                itemAdapter.notifyDataSetChanged();
                dbHelper.writeItems(todoItems);
                return true;
            }
        });
        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(MainActivity.this, EditItemActivity.class);
                i.putExtra("position", position);
                i.putExtra("content", todoItems.get(position).getContent());
                i.putExtra("priority", todoItems.get(position).getPriority());
                startActivityForResult(i, EDIT_REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK && requestCode == EDIT_REQUEST_CODE) {
            // Extract name value from result extras
            String content = data.getExtras().getString("content");
            String priority = data.getExtras().getString("priority");
            int position = data.getExtras().getInt("position", 0);
            int code = data.getExtras().getInt("code", 0);
            // save edited item
            todoItems.set(position, new Item(content, priority));
            itemAdapter.notifyDataSetChanged();
            dbHelper.writeItems(todoItems);
            Toast.makeText(this, "Item Saved", Toast.LENGTH_LONG).show();
        } else if (resultCode == RESULT_OK && requestCode == ADD_REQUEST_CODE) {
            String content = data.getExtras().getString("content");
            String priority = data.getExtras().getString("priority");
            int code = data.getExtras().getInt("code", 0);
            todoItems.add(new Item(content, priority));
            itemAdapter.notifyDataSetChanged();
            dbHelper.writeItems(todoItems);
            Toast.makeText(this, "Item Added", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void onAddItem(View view) {
        Intent i = new Intent(MainActivity.this, AddItemActivity.class);
        startActivityForResult(i, ADD_REQUEST_CODE);
    }
}
