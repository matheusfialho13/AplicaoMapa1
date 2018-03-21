package com.example.matheus.aplicaomapa;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Esta pegando o objeto "private FragmentManager fragmentManager;" criado lá em cima
        fragmentManager = getSupportFragmentManager();

        /*Responsável por iniciar a transação para depois poder adicionar um fragmento
        *Sempre que precisar fazer alguma ação como add, remover, modificar ....
        *É necessário realizar esse processo
        */
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        //Adicionando fragmento ao Activity
        transaction.add(R.id.container, new MapsFragment(), "MapsFragment");

        //Confirmar se a alteração foi feita
        transaction.commitAllowingStateLoss();
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
        getMenuInflater().inflate(R.menu.main, menu);
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

    private void showFragment(Fragment fragment, String name){

        /*Responsável por iniciar a transação para depois poder adicionar um fragmento
        *Sempre que precisar fazer alguma ação como add, remover, modificar ....
        *É necessário realizar esse processo
        */
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        //Substituindo fragmento
        transaction.replace(R.id.container, fragment, name);

        //Confirmar se a alteração foi feita
        transaction.commit();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id){
            case R.id.nav_exemplobasico:

                showFragment(new MapsFragment(), "MapsFragment");

                break;

            case R.id.nav_exemploproviderv1:

                showFragment(new ExemploProviderFragmentV1(), "ExemploProviderFragmentV1");

                break;
        }

        if (id == R.id.nav_exemplobasico) {
            // Handle the camera action
        } else if (id == R.id.nav_exemploproviderv1) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
