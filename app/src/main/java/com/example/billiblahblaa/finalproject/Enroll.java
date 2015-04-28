package com.example.billiblahblaa.finalproject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.MobileServiceException;
import com.microsoft.windowsazure.mobileservices.http.ServiceFilterResponse;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;
import com.microsoft.windowsazure.mobileservices.table.TableQueryCallback;

import java.net.MalformedURLException;
import java.util.List;


public class Enroll extends ActionBarActivity {

    private MobileServiceClient mClient;
    private MobileServiceTable<RegUser> mRegUser;
    private MobileServiceTable<RegCourse> mRegCourse;
    private EnrollAdapter mAdapter;

    String[] Cmd = {"Select"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enroll);

        try {
            mClient = new MobileServiceClient(
                    "https://android9987.azure-mobile.net/",
                    "xnfnjefZpwZKXJLrnZNAiShFBklndN27",
                    this
            );
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        mRegUser = mClient.getTable(RegUser.class);
        mRegCourse = mClient.getTable(RegCourse.class);

        final ListView lvEnroll = (ListView)findViewById(R.id.lvEnroll);
        mAdapter = new EnrollAdapter(this,R.layout.activity_column);
        lvEnroll.setAdapter(mAdapter);
        registerForContextMenu(lvEnroll);

        try {
            refreshItemsFromTable();
        } catch (MobileServiceException e) {
            e.printStackTrace();
        }

    }

    private void refreshItemsFromTable() throws MobileServiceException {

        mRegCourse.execute(new TableQueryCallback<RegCourse>() {
            public void onCompleted(List<RegCourse> result, int count, Exception exception, ServiceFilterResponse response) {

                if (exception == null) {
                    mAdapter.clear();
                    for (RegCourse item : result) {
                        mAdapter.add(item);
                    }
                } else {
                    Log.d("Error", "Error Load Data from Mobile Service");
                }
            }
        });
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        menu.setHeaderIcon(android.R.drawable.btn_star_big_on);
        menu.setHeaderTitle("Command");
        String[] menuItems = Cmd;
        for (int i = 0; i<menuItems.length; i++) {
            menu.add(Menu.NONE, i, i, menuItems[i]);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        int menuItemIndex = item.getItemId();
        String[] menuItems = Cmd;
        String CmdName = menuItems[menuItemIndex];

        Intent intent = getIntent();
        String id = intent.getExtras().getString("sid");

        // Check Event Command
        if ("Select".equals(CmdName)) {
            String s_id = mAdapter.getItem(info.position).getId().toString();
            //String s_name = mAdapter.getItem(info.position).getId().toString();
            //String s_username = mAdapter.getItem(info.position).getUsername().toString();

            Toast.makeText(Enroll.this,"Your Selected Update ",Toast.LENGTH_LONG).show();

            Intent i = new Intent(Enroll.this,SelectActivity.class);
            i.putExtra("sid", id);
            i.putExtra("ssid", s_id);
            startActivity(i);
        }
        return true;
    }

    public class EnrollAdapter extends ArrayAdapter<RegCourse> {
        Context mContext;
        int mLayoutResourceId;

        public EnrollAdapter(Context context, int layoutResourceId) {
            super(context, layoutResourceId);

            mContext = context;
            mLayoutResourceId = layoutResourceId;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View row = convertView;

            final RegCourse currentItem = getItem(position);

            if (row == null) {
                LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
                row = inflater.inflate(mLayoutResourceId, parent, false);
            }

            row.setTag(currentItem);

            // Column id
            final TextView colCode = (TextView) row.findViewById(R.id.colCode);
            colCode.setText(String.valueOf(currentItem.getCourseCode()));

            // Column name
            final TextView colName = (TextView) row.findViewById(R.id.colName);
            colName.setText(currentItem.getCourseName());

            final TextView colSection = (TextView) row.findViewById(R.id.colSection);
            colSection.setText(currentItem.getCourseSection());

            String daytime = currentItem.getCourseDay() + "\n" + currentItem.getCourseTime();

            // Column email
            final TextView colTime = (TextView) row.findViewById(R.id.colTime);
            colTime.setText(daytime);

            return row;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_enroll, menu);
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
}
