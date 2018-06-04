package com.software.sulthan.kasirtoko;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CustomerBr extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private JSONObject jObject;
    private String xResult ="";
    //Seusuaikan url dengan nama domain yang anda gunakan
    //private String url = "https://mysentra.000webhostapp.com/customerview.php";
    //private String url = "http://mobapp.sentrasolusi.net/customer/customerview.php";
    private String url;
    public Button btn1;
    public TextView txt1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customerbrfr);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //        .setAction("Action", null).show();
                startActivity(new Intent(CustomerBr.this, CustomerIU2.class));

            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Mulai


    }


    private void parse(TextView txtResult) throws Exception {
        jObject = new JSONObject(xResult);
        JSONArray menuitemArray = jObject.getJSONArray("dataku");
        String sret="";
        for (int i = 0; i < menuitemArray.length(); i++) {
            sret +=menuitemArray.getJSONObject(i).getString("kodecust").toString()+" : ";
            System.out.println(menuitemArray.getJSONObject(i).getString("kodecust").toString());
            System.out.println(menuitemArray.getJSONObject(i).getString("namacust").toString());
            sret +=menuitemArray.getJSONObject(i).getString("namacust").toString()+"\n";
        }
        txtResult.setText(sret);
    }
    /**
     * Method untuk Mengirimkan data kes erver
     * event by button login diklik
     */
    public String getRequest(String Url){
        String sret="";
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(Url);
        try{
            HttpResponse response = client.execute(request);
            sret =request(response);
        }
        catch(Exception ex){
            Toast.makeText(this,"Gagal "+ex, Toast.LENGTH_SHORT).show();
        }
        return sret;
    }
    /**
     * Method untuk Menenrima data dari server
     * @param response
     * @return
     */
    public static String request(HttpResponse response){
        String result = "";
        try{
            InputStream in = response.getEntity().getContent();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder str = new StringBuilder();
            String line = null;
            while((line = reader.readLine()) != null){
                str.append(line + "\n");
            }
            in.close();
            result = str.toString();
        }catch(Exception ex){
            result = "Error";
        }
        return result;
    }









    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.customer_br, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
            Intent intents = new Intent(this, CustomerIU.class);
            startActivity(intents);
        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_insertcustomer) {
            Intent intents = new Intent(this, CustomerIU2.class);
            startActivity(intents);
        } else if (id == R.id.nav_listcustomer) {
            Intent intents = new Intent(this, CustomerBr.class);
            startActivity(intents);

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void btnSearchClick (View v) {
        SettingGlobalData a = new SettingGlobalData();
        url = a.UrlMaster + "/customer/customerview.php";

        TextView txtResult = (TextView)findViewById(R.id.textView4);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        xResult = getRequest(url);
        try {
            parse(txtResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //btn1= (Button)findViewById(R.id.button) ;
    }

    public void btnAddClick (View v) {
        Toast.makeText(this,"Click tambah ya.. ", Toast.LENGTH_SHORT).show();
    }

}
