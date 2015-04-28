package com.example.billiblahblaa.finalproject;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.http.ServiceFilterResponse;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;
import com.microsoft.windowsazure.mobileservices.table.TableQueryCallback;

import java.net.MalformedURLException;
import java.util.List;


public class UserActivity extends ActionBarActivity {

    private MobileServiceClient mClient;
    private MobileServiceTable<RegUser> mRegUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

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

        Intent intent = getIntent();
        final String id = intent.getExtras().getString("sid");

        refreshItemsFromTable(id);

        Button btEnroll = (Button) findViewById(R.id.btEnroll);
        // Perform action on click
        btEnroll.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(UserActivity.this, Enroll.class);
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

                            String name = null;
                            String username = null;
                            String curriculum = null;
                            String academicyear = null;

                            RegUser item = result.get(0);
                            name = item.getName();
                            username = item.getUsername();
                            curriculum = item.getCurriculum();
                            academicyear = item.getAcademicYear();

                            TextView tvName = (TextView)findViewById(R.id.tvName);
                            TextView tvUsername = (TextView)findViewById(R.id.tvUsername);
                            TextView tvCurriculum = (TextView)findViewById(R.id.tvCurriculum);
                            TextView tvAcademicYear = (TextView)findViewById(R.id.tvAcademicYear);

                            tvName.setText(name);
                            tvUsername.setText(username);
                            tvCurriculum.setText(curriculum);
                            tvAcademicYear.setText(academicyear);

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
        getMenuInflater().inflate(R.menu.menu_user, menu);
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
