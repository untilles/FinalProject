package com.example.billiblahblaa.finalproject;

import android.app.AlertDialog;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.http.ServiceFilterResponse;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;
import com.microsoft.windowsazure.mobileservices.table.TableOperationCallback;

import java.net.MalformedURLException;


public class Admin2Activity extends ActionBarActivity {

    private MobileServiceClient mClient;
    private MobileServiceTable<RegCourse> mRegCourse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin2);

        try {
            mClient = new MobileServiceClient(
                    "https://android9987.azure-mobile.net/",
                    "xnfnjefZpwZKXJLrnZNAiShFBklndN27",
                    this
            );
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        mRegCourse = mClient.getTable(RegCourse.class);
    }

    public void btClick(View v) {
        EditText etRegCourseCode = (EditText)findViewById(R.id.etRegCourseCode);
        EditText etRegCourseName = (EditText)findViewById(R.id.etRegCourseName);
        EditText etRegCourseCredit = (EditText)findViewById(R.id.etRegCourseCredit);
        EditText etRegCourseSection = (EditText)findViewById(R.id.etRegCourseSection);
        EditText etRegCourseRoom = (EditText)findViewById(R.id.etRegCourseRoom);
        RadioGroup rgDay = (RadioGroup)findViewById(R.id.rgDay);
        RadioGroup rgTime = (RadioGroup)findViewById(R.id.rgTime);

        int rDayID = rgDay.getCheckedRadioButtonId();
        int rTimeID = rgTime.getCheckedRadioButtonId();

        String sRegCourseCode = etRegCourseCode.getText().toString();
        String sRegCourseName = etRegCourseName.getText().toString();
        String sRegCourseCredit = etRegCourseCredit.getText().toString();
        String sRegCourseSection = etRegCourseSection.getText().toString();
        String sRegCourseRoom = etRegCourseRoom.getText().toString();
        String sRegCourseDay = ((RadioButton)findViewById(rDayID)).getText().toString();
        String sRegCourseTime = ((RadioButton)findViewById(rTimeID)).getText().toString();

        final AlertDialog.Builder adb = new AlertDialog.Builder(this);

        RegCourse item = new RegCourse();
        item.setCourseCode(sRegCourseCode);
        item.setCourseName(sRegCourseName);
        item.setCourseCredit(sRegCourseCredit);
        item.setCourseSection(sRegCourseSection);
        item.setCourseRoom(sRegCourseRoom);
        item.setCourseDay(sRegCourseDay);
        item.setCourseTime(sRegCourseTime);

        mRegCourse.insert(item, new TableOperationCallback<RegCourse>() {

            public void onCompleted(RegCourse entity, Exception exception, ServiceFilterResponse response) {

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
        getMenuInflater().inflate(R.menu.menu_admin2, menu);
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
