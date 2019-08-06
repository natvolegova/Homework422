package com.example.homework422;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private ListView main_content;
    private FloatingActionButton fab;
    private LinearLayout add_layout;
    private EditText input_title;
    private EditText input_subtitle;
    private Spinner list_task;
    private Button add_task;
    private ItemListAdapter myListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        myListAdapter = new ItemListAdapter(this, null);
        main_content.setAdapter(myListAdapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onClick(View view) {
                add_layout.setVisibility(View.VISIBLE);
                fab.setVisibility(view.GONE);
                toolbar.setTitle(getString(R.string.new_task));
            }
        });

        add_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add_task(view);
            }
        });

        main_content.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                showItemData(i);
                return false;
            }
        });
    }

    @SuppressLint("RestrictedApi")
    private void add_task(View view) {
        String str_title = input_title.getText().toString();
        String str_subtitle = input_subtitle.getText().toString();
        int task_value = (int) list_task.getSelectedItemId();

        if (!str_title.equals("") && !str_subtitle.equals("")) {
            myListAdapter.add_item(new ItemData(str_title, str_subtitle, task_value));
            input_title.setText("");
            input_subtitle.setText("");
            list_task.setSelection(0);
            add_layout.setVisibility(View.GONE);
            fab.setVisibility(view.VISIBLE);
            toolbar.setTitle(getString(R.string.app_name));
        } else {
            Toast.makeText(MainActivity.this, getString(R.string.msg_error_add), Toast.LENGTH_LONG).show();
        }
    }

    private void showItemData(int position) {
        ItemData item = myListAdapter.getItem(position);
        String result = getResources().getString(R.string.msg_task_info);
        result = String.format(result, Integer.toString(position), item.getTitle(), item.getSubtitle());
        Toast.makeText(MainActivity.this, result, Toast.LENGTH_LONG).show();
    }

    private void initView() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        main_content = findViewById(R.id.main_content);
        main_content.setItemsCanFocus(false);
        fab = findViewById(R.id.fab);
        add_layout = findViewById(R.id.add_layout);
        input_title = findViewById(R.id.input_title);
        input_subtitle = findViewById(R.id.input_subtitle);
        list_task = findViewById(R.id.list_task);
        add_task = findViewById(R.id.add_task);
    }
}
