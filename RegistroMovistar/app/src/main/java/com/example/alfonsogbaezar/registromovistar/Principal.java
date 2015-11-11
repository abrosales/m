package com.example.alfonsogbaezar.registromovistar;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Principal extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private View asit;
    View notifi;
    View vent;
    View estad;
    private LocationManager locManager;
    private LocationListener locListener;
    EditText loca;
    AlertDialog alert = null;
    TextView maschips;
    TextView maspalnes;
    TextView masportas;
    TextView masequipos;
    TextView menoschips;
    TextView menosplanes;
    TextView menosportas;
    TextView menosequipos;
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
        comenzarLocalizacion();

    }
    private void comenzarLocalizacion()
    {
        //Obtenemos una referencia al LocationManager
        locManager =(LocationManager)getSystemService(LOCATION_SERVICE);

        //Obtenemos la última posición conocida
        Location location =locManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        Toast.makeText(this, (CharSequence) location, Toast.LENGTH_SHORT).show();
        //Mostramos la última posición conocida
        mostrarPosicion(location);

        //Nos registramos para recibir actualizaciones de la posición
        locListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                mostrarPosicion(location);
            }
            public void onProviderDisabled(String provider){


            }
            public void onProviderEnabled(String provider){

            }
            public void onStatusChanged(String provider, int status, Bundle extras){

            }
        };

        locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 30000, 0, locListener);
    }



    private void mostrarPosicion(Location loc) {
        loca= (EditText) findViewById(R.id.txt_localizacion);
        if(loc != null)
        {
            Double x=loc.getLatitude();
            Double y=loc.getLongitude();
            String posi= Double.toString(x)+","+Double.toString(y);

            Toast.makeText(this, posi, Toast.LENGTH_SHORT).show();
            loca.setText(posi);


            Log.i("", String.valueOf(loc.getLatitude() + " - " + String.valueOf(loc.getLongitude())));
        }
        else
        {
            Toast.makeText(this, "vacio", Toast.LENGTH_SHORT).show();

        }
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
    public void onclickcontrolesventa(View view){
        maschips= (TextView) findViewById(R.id.txt_numchips);
        maspalnes=(TextView) findViewById(R.id.txt_numplanes);
        masportas=(TextView)  findViewById(R.id.txt_numporta);
        masequipos=(TextView) findViewById(R.id.txt_numequiposnuevos);

        int id = view.getId();
        int chip=Integer.parseInt(maschips.getText().toString());
        int plan=Integer.parseInt(maspalnes.getText().toString());
        int porta=Integer.parseInt(masportas.getText().toString());
        int eqnu=Integer.parseInt(masequipos.getText().toString());
        if (id== R.id.btnmas_chips){
            chip=chip+1;
            maschips.setText(Integer.toString(chip));

        }else if(id== R.id.btnmas_planes){
            plan=plan+1;
            maspalnes.setText(Integer.toString(plan));

        } else if(id== R.id.btnmas_portas){
            porta=porta+1;
            masportas.setText(Integer.toString(porta));

        }else if(id== R.id.btnmas_equipos){

            eqnu=eqnu+1;
            masequipos.setText(Integer.toString(eqnu));
        }else if(id== R.id.btnmenos_chips){
            if(chip>0){
                chip=chip-1;
                maschips.setText(Integer.toString(chip));
            }

        }else if(id== R.id.btnmenos_planes){
            if(plan>0){
                plan=plan-1;
                maspalnes.setText(Integer.toString(plan));
            }

        }else if(id== R.id.btnmenos_portas){
            if(porta>0){
                porta=chip-1;
                masportas.setText(Integer.toString(porta));
            }

        }else if(id== R.id.btnmenos_equipos){
            if(eqnu>0){
                eqnu=eqnu-1;
                masequipos.setText(Integer.toString(eqnu));
            }

        }



    }
}
