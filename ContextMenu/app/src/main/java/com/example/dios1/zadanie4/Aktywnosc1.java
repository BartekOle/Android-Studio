package com.example.dios1.zadanie4;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Aktywnosc1 extends AppCompatActivity {

    TextView textView;
    Button bt1;
    private ActionMode mActionMode;
    TextView textView2;
    Button contextMenuButton3;
    int item_selection = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aktywnosc1);
        textView = findViewById(R.id.ele1);
        bt1 = findViewById(R.id.ele2);

        Button contextMenuButton = findViewById(R.id.cont1);

        registerForContextMenu(contextMenuButton);

        Button contextMenuButton2 = findViewById(R.id.cont2);

        registerForContextMenu(contextMenuButton2);

       contextMenuButton3 = findViewById(R.id.ele4);

        registerForContextMenu(contextMenuButton3);


        textView2 = findViewById(R.id.ele3);
        textView2.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                if (mActionMode != null) {
                    return false;
                }

                mActionMode = startActionMode(mActionModeCallback);
                return true;
            }
        });
    }

    private ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            mode.getMenuInflater().inflate(R.menu.menu4, menu);
            mode.setTitle("Wybierz opcje");
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()) {
                case R.id.action10:
                    textView2.setTextSize(40);
                    return true;
                case R.id.action11:
                    textView2.setTextColor(Color.parseColor("#1A237E"));
                    return true;
                default:
                    return false;
            }

        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {

            mActionMode = null;

        }
    };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu1, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.action1:
                textView.setTextColor(Color.parseColor("#D50000"));
                bt1.setTextColor(Color.parseColor("#D50000"));
                return true;
            case R.id.action2:
                textView.setTextColor(Color.parseColor("#6200EA"));
                bt1.setTextColor(Color.parseColor("#6200EA"));
                return true;
            case R.id.action3:
                textView.setTextColor(Color.parseColor("#FF6D00"));
                bt1.setTextColor(Color.parseColor("#FF6D00"));
                return true;
            case R.id.submenu3a:
               textView.setTextSize(40);
                bt1.setTextSize(40);
                return true;
            case R.id.submenu3b:
                textView.setTextSize(5);
                bt1.setTextSize(5);
                return true;
            case R.id.submenu3c:
                textView.setTextSize(15);
                bt1.setTextSize(15);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, v, menuInfo);

        if(v.getId() == R.id.cont1)
            getMenuInflater().inflate(R.menu.menu2,menu);
        else if(v.getId() == R.id.cont2)
            getMenuInflater().inflate(R.menu.menu3,menu);
        else if(v.getId() == R.id.ele4)
        {
            getMenuInflater().inflate(R.menu.menu5,menu);
            MenuItem item1 = menu.findItem(R.id.option7);
            MenuItem item2 = menu.findItem(R.id.option8);
            MenuItem item3 = menu.findItem(R.id.option9);

            if(item_selection == 1)
            {
                item1.setChecked(true);
            }
            else if(item_selection == 2)
            {
                item2.setChecked(true);
            }
            else if(item_selection == 3)
            {
                item3.setChecked(true);
            }
        }




    }

    @Override
    public boolean onContextItemSelected(MenuItem item){



        switch(item.getItemId()){
            case R.id.option1:
                textView.setText(R.string.text18);
                return true;
            case R.id.option2:
                textView.setText(R.string.text19);
                return true;
            case R.id.option3:
                textView.setText(R.string.text20);
                return true;
            case R.id.option4:
               bt1.setBackgroundColor(Color.parseColor("#33691E"));
                return true;
            case R.id.option5:
                bt1.setBackgroundColor(Color.parseColor("#00B8D4"));
                return true;
            case R.id.option6:
                bt1.setBackgroundColor(Color.parseColor("#C2185B"));
                return true;
            case R.id.option7:


                        contextMenuButton3.setTextColor(Color.parseColor("#BF360C"));
                        contextMenuButton3.setTextSize(20);
                        contextMenuButton3.setBackgroundColor(Color.parseColor("#455A64"));
                item.setChecked(true);
                item_selection  = 1;

                return true;
            case R.id.option8:


                contextMenuButton3.setTextSize(45);
                contextMenuButton3.setTextColor(Color.parseColor("#000000"));
                contextMenuButton3.setBackgroundColor(Color.parseColor("#455A64"));
                item.setChecked(true);
                item_selection  = 2;
                return true;

            case R.id.option9:



                    contextMenuButton3.setBackgroundColor(Color.parseColor("#827717"));
                    contextMenuButton3.setTextSize(20);
                    contextMenuButton3.setTextColor(Color.parseColor("#000000"));
                    item.setChecked(true);
                item_selection  = 3;

                return true;

        }
        return false;

    }
}
