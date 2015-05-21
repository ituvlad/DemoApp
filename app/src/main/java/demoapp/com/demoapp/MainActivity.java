package demoapp.com.demoapp;

import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Set;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createList();
    }

    private String[] getActivitiesInPackage() {
        String[] activities = {};

        try {
            PackageInfo pkgInfo = getPackageManager().getPackageInfo("demoapp.com.demoapp", PackageManager.GET_ACTIVITIES);
            ActivityInfo[] activityInfos = pkgInfo.activities;
            activities = new String[activityInfos.length];
            for(int i = 0; i < activityInfos.length; i++) {
                activities[i] = activityInfos[i].name;
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return activities;
    }

    private void createList() {
        ListView lvActivities = (ListView) findViewById(R.id.lvActivities);

        String[] values = getActivitiesInPackage();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, values);

        lvActivities.setAdapter(adapter);

        lvActivities.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
