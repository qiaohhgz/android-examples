package com.jim.example.database;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.AdapterView.OnItemClickListener;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 14-2-21
 * Time: 下午6:40
 * To change this template use File | Settings | File Templates.
 */
public class QueryActivity extends ListActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("浏览收藏信息");
        final DBHelper helpter = new DBHelper(this);
        Cursor c = helpter.query();
        String[] from = {"_id", "name", "url", "desc"};
        int[] to = {R.id.textView, R.id.textView1, R.id.textView2, R.id.textView3};
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                R.layout.row, c, from, to);
        ListView listView = getListView();
        listView.setAdapter(adapter);


        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                final long temp = arg3;
                builder.setMessage("真的要删除该记录吗？").setPositiveButton("是",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                helpter.del((int) temp);
                                Cursor c = helpter.query();
                                String[] from = {"_id", "name", "url", "desc"};
                                int[] to = {R.id.textView, R.id.textView1, R.id.textView2, R.id.textView3};
                                SimpleCursorAdapter adapter = new SimpleCursorAdapter(getApplicationContext(),
                                        R.layout.row, c, from, to);
                                ListView listView = getListView();
                                listView.setAdapter(adapter);
                            }
                        }).setNegativeButton("否",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int which) {

                            }
                        });
                AlertDialog ad = builder.create();
                ad.show();
            }
        });
        helpter.close();
    }
}
