package com.example.alfonsogbaezar.registromovistar;

import android.content.Intent;
import android.os.Bundle;
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

public class Principal extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    View asit;
    View notifi;
    View vent;
    View estad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        asit=(View) findViewById(R.id.activity_asistencias);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        super.setTitle("Asistencia");
        asit.setVisibility(View.VISIBLE);
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
        getMenuInflater().inflate(R.menu.principal, menu);
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
            Intent i=new Intent(this,MainActivity.class);
            startActivity(i);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        asit=(View) findViewById(R.id.activity_asistencias);
        notifi=(View) findViewById(R.id.activity_notificaciones);
        vent=(View) findViewById(R.id.activity_ventas);
        estad=(View) findViewById(R.id.activity_notificaciones);
        int id = item.getItemId();

        if (id == R.id.Control_asistencia) {
            super.setTitle("Asistencia");
            asit.setVisibility(View.VISIBLE);
            notifi.setVisibility(View.INVISIBLE);
            vent.setVisibility(View.INVISIBLE);
            estad.setVisibility(View.INVISIBLE);
        } else if (id == R.id.Notificaciones) {
            super.setTitle("Notificaciones");
            asit.setVisibility(View.INVISIBLE);
            notifi.setVisibility(View.VISIBLE);
            vent.setVisibility(View.INVISIBLE);
            estad.setVisibility(View.INVISIBLE);
        } else if (id == R.id.Ventas) {
            super.setTitle("Ventas");
            asit.setVisibility(View.INVISIBLE);
            notifi.setVisibility(View.INVISIBLE);
            vent.setVisibility(View.VISIBLE);
            estad.setVisibility(View.INVISIBLE);
        } else if (id == R.id.Estadistica) {
            super.setTitle("Estadistica");
            asit.setVisibility(View.INVISIBLE);
            notifi.setVisibility(View.INVISIBLE);
            vent.setVisibility(View.INVISIBLE);
            estad.setVisibility(View.VISIBLE);
        } else if (id == R.id.Salir) {
            Intent i=new Intent(this,MainActivity.class);
            startActivity(i);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
