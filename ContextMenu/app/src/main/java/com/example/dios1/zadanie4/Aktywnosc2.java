package com.example.dios1.zadanie4;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Aktywnosc2 extends AppCompatActivity {

    TextView textView;
    Button bt1;
    Button contextMenuButton3;
    int item_selection = 0;
    private ActionMode mActionMode;
    TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aktywnosc2);
        textView = findViewById(R.id.ele5);
        bt1 = findViewById(R.id.ele6);
        Button contextMenuButton = findViewById(R.id.cont5);

        registerForContextMenu(contextMenuButton);

        Button contextMenuButton2 = findViewById(R.id.cont6);

        registerForContextMenu(contextMenuButton2);

        contextMenuButton3 = findViewById(R.id.ele7);

        registerForContextMenu(contextMenuButton3);

        textView2 = findViewById(R.id.ele8);
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
            menu.add(5, 20, 1, "RozmiarCzcionki").setIcon(R.drawable.iconsize2);
            menu.add(5, 21, 2, "KolorCzcionki").setIcon(R.drawable.iconcolor5);
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
                case 20:
                    textView2.setTextSize(32);
                    return true;
                case 21:
                    textView2.setTextColor(Color.parseColor("#880E4F"));
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
        menu.add(0, 1, 1, "Opcja1");
        menu.add(0, 2, 2, "Opcja2");
        menu.add(0, 3, 3, "Opcja3");
        SubMenu sm = menu.addSubMenu(1, 5, 4, "SubMenu");
        sm.add(1, 6, 1, "Opcja4a").setIcon(R.drawable.iconcolor4);;
        sm.add(1, 7, 2, "Opcja4b").setIcon(R.drawable.iconsize);;
        sm.add(1, 8, 3, "Opcja4c").setIcon(R.drawable.iconsubmenu);;
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case 1:
                textView.setTextColor(Color.parseColor("#6D4C41"));
                bt1.setTextColor(Color.parseColor("#6D4C41"));
                return true;
            case 2:
                textView.setTextColor(Color.parseColor("#4CAF50"));
                bt1.setTextColor(Color.parseColor("#4CAF50"));
                return true;
            case 3:
                textView.setTextColor(Color.parseColor("#00BCD4"));
                bt1.setTextColor(Color.parseColor("#00BCD4"));
                return true;
            case 6:
                textView.setTextSize(20);
                bt1.setTextSize(20);
                return true;
            case 7:
                textView.setTextSize(30);
                bt1.setTextSize(30);
                return true;
            case 8:
                textView.setTextSize(40);
                bt1.setTextSize(40);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, v, menuInfo);

        if(v.getId() == R.id.cont5)
        {
            menu.add(2, 10, 1, "Opcja1");
            menu.add(2, 11, 2, "Opcja2");
            menu.add(2, 12, 3, "Opcja3");

        }
        else if(v.getId() == R.id.cont6)
        {
            menu.add(3, 13, 1, "Akcja1");
            menu.add(3, 14, 2, "Akcja2");
            menu.add(3, 15, 3, "Akcja3");
        }
        else if(v.getId() == R.id.ele7)
        {
            menu.add(4, 16, 1, "KolorCzcionki").setCheckable(true).setChecked(false);
            menu.add(4, 17, 2, "RozmiarCzcionki").setCheckable(true).setChecked(false);
            menu.add(4, 18, 3, "KolorTla").setCheckable(true).setChecked(false);
            MenuItem item1 = menu.findItem(16);
            MenuItem item2 = menu.findItem(17);
            MenuItem item3 = menu.findItem(18);

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
            case 10:
                textView.setText(R.string.text34);
                return true;
            case 11:
                textView.setText(R.string.text35);
                return true;
            case 12:
                textView.setText(R.string.text36);
                return true;
            case 13:
                bt1.setBackgroundColor(Color.parseColor("#AFB42B"));
                return true;
            case 14:
                bt1.setBackgroundColor(Color.parseColor("#FF6F00"));
                return true;
            case 15:
                bt1.setBackgroundColor(Color.parseColor("#FF9E80"));
                return true;
            case 16:


                contextMenuButton3.setTextColor(Color.parseColor("#009688"));
                contextMenuButton3.setTextSize(10);
                contextMenuButton3.setBackgroundColor(Color.parseColor("#455A64"));
                item.setChecked(true);
                item_selection  = 1;

                return true;
            case 17:


                contextMenuButton3.setTextSize(50);
                contextMenuButton3.setTextColor(Color.parseColor("#000000"));
                contextMenuButton3.setBackgroundColor(Color.parseColor("#455A64"));
                item.setChecked(true);
                item_selection  = 2;
                return true;

            case 18:



                contextMenuButton3.setBackgroundColor(Color.parseColor("#880E4F"));
                contextMenuButton3.setTextSize(10);
                contextMenuButton3.setTextColor(Color.parseColor("#000000"));
                item.setChecked(true);
                item_selection  = 3;

                return true;

        }
        return false;

    }

}
