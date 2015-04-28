package com.example.billiblahblaa.finalproject;

import android.app.AlertDialog;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.http.ServiceFilterResponse;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;
import com.microsoft.windowsazure.mobileservices.table.TableOperationCallback;

import java.net.MalformedURLException;


public class AdminActivity extends ActionBarActivity {

    private MobileServiceClient mClient;
    private MobileServiceTable<RegUser> mRegUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

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

    }

    public void btClick(View v) {
        EditText etRegName = (EditText)findViewById(R.id.etRegName);
        EditText etRegUsername = (EditText)findViewById(R.id.etRegUsername);
        EditText etRegPasswd = (EditText)findViewById(R.id.etRegPasswd);
        EditText etRegCurriculum = (EditText)findViewById(R.id.etRegCourriculum);
        EditText etRegAcademicYear = (EditText)findViewById(R.id.etRegAcademicYear);

        String sRegName = etRegName.getText().toString();
        String sRegUsername = etRegUsername.getText().toString();
        String sRegPasswd = etRegPasswd.getText().toString();
        String sRegCurriculum = etRegCurriculum.getText().toString();
        String sRegAcademicYear = etRegAcademicYear.getText().toString();
        String sRegInitialCourse = " ";

        final AlertDialog.Builder adb = new AlertDialog.Builder(this);

        RegUser item = new RegUser();
        item.setName(sRegName);
        item.setUsername(sRegUsername);
        item.setPasswd(sRegPasswd);
        item.setCurriculum(sRegCurriculum);
        item.setAcademicYear(sRegAcademicYear);
        item.setCourse1(sRegInitialCourse);
        item.setCourse2(sRegInitialCourse);
        item.setCourse3(sRegInitialCourse);
        item.setCourse4(sRegInitialCourse);
        item.setCourse5(sRegInitialCourse);
        item.setCourse6(sRegInitialCourse);
        item.setCourse7(sRegInitialCourse);
        item.setCourse8(sRegInitialCourse);
        item.setCourse9(sRegInitialCourse);
        item.setCourse10(sRegInitialCourse);

        mRegUser.insert(item, new TableOperationCallback<RegUser>() {

            public void onCompleted(RegUser entity, Exception exception, ServiceFilterResponse response) {

                if (exception == null) {
                    AlertDialog ad = adb.create();
                    ad.setMessage("Register Data Successfully.");
                    ad.show();
                } else {
                    AlertDialog ad = adb.create();
                    ad.setMessage("Error : " + exception.getCause().getMessage());
                    ad.show();
                }

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_admin, menu);
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
