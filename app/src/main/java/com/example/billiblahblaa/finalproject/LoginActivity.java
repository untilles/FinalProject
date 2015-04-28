package com.example.billiblahblaa.finalproject;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.http.ServiceFilterResponse;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;
import com.microsoft.windowsazure.mobileservices.table.TableQueryCallback;

import java.net.MalformedURLException;
import java.util.List;


public class LoginActivity extends ActionBarActivity {

    private MobileServiceClient mClient;
    private MobileServiceTable<RegUser> mRegUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

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
        EditText etUsername = (EditText)findViewById(R.id.etUsername);
        EditText etPasswd = (EditText)findViewById(R.id.etPasswd);

        String sUsername = etUsername.getText().toString();
        String sPasswd = etPasswd.getText().toString();

        final AlertDialog.Builder adb = new AlertDialog.Builder(this);

        mRegUser.where().field("username").eq(sUsername).and().field("passwd").eq(sPasswd).execute(new TableQueryCallback<RegUser>() {
            public void onCompleted(List<RegUser> result, int count, Exception exception, ServiceFilterResponse response) {
                if (exception == null) {
                    if (result.size() > 0) {

                        String id;
                        RegUser item = result.get(0);
                        id = item.getId();

                        Intent i = new Intent(LoginActivity.this, UserActivity.class);
                        i.putExtra("sid", id);
                        startActivity(i);

                    } else {
                        AlertDialog ad = adb.create();
                        ad.setMessage("Incorrect Username or Password");
                        ad.show();
                    }
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
        getMenuInflater().inflate(R.menu.menu_login, menu);
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