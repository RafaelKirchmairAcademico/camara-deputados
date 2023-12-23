package br.ifsul.deputados;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import br.ifsul.deputados.utils.Authentication;
import br.ifsul.deputados.utils.BottomNavigationMenu;
import br.ifsul.deputados.utils.Keys;
import br.ifsul.deputados.utils.api.APICaller;
import br.ifsul.deputados.utils.api.CallbackData;
import br.ifsul.deputados.utils.api.domain.Partido;

public class MainActivity extends AppCompatActivity { // Home/Partidos

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(Authentication.checkIfLoggedInAndRedirectToStartIfNot(this)) {
            BottomNavigationView bottomNavigation = findViewById(R.id.bottom_navigation_menu);
            bottomNavigation.setSelectedItemId(R.id.nav_partidos);
            bottomNavigation.setOnItemSelectedListener(item -> BottomNavigationMenu.listener(this, item));

            ListView party = findViewById(R.id.party_list);
            ArrayAdapter<Partido> partyAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1);
            party.setAdapter(partyAdapter);

            party.setOnItemClickListener((adapterView, view, i, l) -> {
                Intent intent = new Intent(getApplicationContext(), PartyProfileActivity.class);
                intent.putExtra(Keys.PARTIDO_ID, partyAdapter.getItem(i).getId());
                startActivity(intent);
                finish();
            });

            APICaller apiCaller = new APICaller();
            apiCaller.getAllPartidos(new CallbackData<>() {
                @Override
                public void onSuccess(List<Partido> data) {
                    partyAdapter.clear();
                    partyAdapter.addAll(data);
                }

                @Override
                public void onUnsuccess(String message) {
                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                }

                @Override
                public void onFailure(String message) {
                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                }
            });
        }

    }
}