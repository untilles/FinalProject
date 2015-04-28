package com.example.billiblahblaa.finalproject;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.MobileServiceException;
import com.microsoft.windowsazure.mobileservices.http.ServiceFilterResponse;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;
import com.microsoft.windowsazure.mobileservices.table.TableOperationCallback;
import com.microsoft.windowsazure.mobileservices.table.TableQueryCallback;

import java.net.MalformedURLException;
import java.util.List;


public class SelectActivity extends ActionBarActivity {

    private MobileServiceClient mClient;
    private MobileServiceTable<RegUser> mRegUser;
    private MobileServiceTable<RegCourse> mRegCourse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

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

        Intent intent = getIntent();
        final String id = intent.getExtras().getString("sid");
        final String id2 = intent.getExtras().getString("ssid");

        refreshItemsFromTable(id);

        Button btSave = (Button) findViewById(R.id.btSave);
        btSave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                RadioGroup rgSelect = (RadioGroup)findViewById(R.id.rgSelect);
                int rSID = rgSelect.getCheckedRadioButtonId();
                final String slot = ((RadioButton)findViewById(rSID)).getText().toString();

                mRegUser.where().field("id").eq(id)
                        .execute(new TableQueryCallback<RegUser>() {

                            public void onCompleted(List<RegUser> result, int count, Exception exception,
                                                    ServiceFilterResponse response) {

                                if (exception == null) {
                                    RegUser item = result.get(0);

                                    if(slot.equals("Slot1")) {
                                        item.setCourse1(id2);
                                    } else if(slot.equals("Slot2")) {
                                        item.setCourse2(id2);
                                    } else if(slot.equals("Slot3")) {
                                        item.setCourse3(id2);
                                    } else if(slot.equals("Slot4")) {
                                        item.setCourse4(id2);
                                    } else if(slot.equals("Slot5")) {
                                        item.setCourse5(id2);
                                    } else if(slot.equals("Slot6")) {
                                        item.setCourse6(id2);
                                    } else if(slot.equals("Slot7")) {
                                        item.setCourse7(id2);
                                    } else if(slot.equals("Slot8")) {
                                        item.setCourse8(id2);
                                    } else if(slot.equals("Slot9")) {
                                        item.setCourse9(id2);
                                    } else if(slot.equals("Slot10")) {
                                        item.setCourse10(id2);
                                    }

                                    mRegUser.update(item, new TableOperationCallback<RegUser>() {

                                        public void onCompleted(RegUser entity, Exception exception, ServiceFilterResponse response) {
                                            if (exception == null) {
                                                Toast.makeText(SelectActivity.this, "Update Data Successfully", Toast.LENGTH_SHORT).show();
                                            } else {
                                                Toast.makeText(SelectActivity.this, "Update Failed!", Toast.LENGTH_SHORT).show();
                                                Log.d("Error : ", exception.getMessage());
                                            }
                                        }

                                    });
                                } else {
                                    Log.d("Error","Error Load Data from Mobile Service");
                                }
                            }
                        });

                Intent i = new Intent(SelectActivity.this,UserActivity.class);
                i.putExtra("sid", id);
                startActivity(i);
            }
        });

    }

    private void refreshItemsFromTable(final String id) {

        final AlertDialog.Builder adb = new AlertDialog.Builder(this);

        if (mClient == null) {
            AlertDialog ad = adb.create();
            ad.setMessage("Cannot connect to Windows Azure Mobile Service!");
            ad.show();
        } else {

            mRegUser.where().field("id").eq(id).execute(new TableQueryCallback<RegUser>() {
                public void onCompleted(List<RegUser> result, int count, Exception exception,
                                        ServiceFilterResponse response) {

                    if (exception == null) {
                        if(result.size() > 0)
                        {

                            RegUser item = result.get(0);
                            String course1 = item.getCourse1();
                            String course2 = item.getCourse2();
                            String course3 = item.getCourse3();
                            String course4 = item.getCourse4();
                            String course5 = item.getCourse5();
                            String course6 = item.getCourse6();
                            String course7 = item.getCourse7();
                            String course8 = item.getCourse8();
                            String course9 = item.getCourse9();
                            String course10 = item.getCourse10();

                            TextView tvCourse1 = (TextView)findViewById(R.id.tvCourse1);
                            TextView tvCourse2 = (TextView)findViewById(R.id.tvCourse2);
                            TextView tvCourse3 = (TextView)findViewById(R.id.tvCourse3);
                            TextView tvCourse4 = (TextView)findViewById(R.id.tvCourse4);
                            TextView tvCourse5 = (TextView)findViewById(R.id.tvCourse5);
                            TextView tvCourse6 = (TextView)findViewById(R.id.tvCourse6);
                            TextView tvCourse7 = (TextView)findViewById(R.id.tvCourse7);
                            TextView tvCourse8 = (TextView)findViewById(R.id.tvCourse8);
                            TextView tvCourse9 = (TextView)findViewById(R.id.tvCourse9);
                            TextView tvCourse10 = (TextView)findViewById(R.id.tvCourse10);

                            tvCourse1.setText(course1);
                            tvCourse2.setText(course2);
                            tvCourse3.setText(course3);
                            tvCourse4.setText(course4);
                            tvCourse5.setText(course5);
                            tvCourse6.setText(course6);
                            tvCourse7.setText(course7);
                            tvCourse8.setText(course8);
                            tvCourse9.setText(course9);
                            tvCourse10.setText(course10);

                        }

                    } else {
                        AlertDialog ad = adb.create();
                        ad.setMessage("Error : " + exception.getCause().getMessage());
                        ad.show();
                    }
                }
            });

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_select, menu);
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
