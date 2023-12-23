package br.ifsul.deputados;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;


import java.util.List;

import br.ifsul.deputados.utils.BottomNavigationMenu;
import br.ifsul.deputados.utils.Keys;
import br.ifsul.deputados.utils.api.APICaller;
import br.ifsul.deputados.utils.api.CallbackData;
import br.ifsul.deputados.utils.api.domain.Deputado;

public class DeputysProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deputys_party);

        BottomNavigationView bottomNavigation = findViewById(R.id.bottom_navigation_menu);
        bottomNavigation.setOnItemSelectedListener(item -> BottomNavigationMenu.listener(this, item));

        ListView deputys = findViewById(R.id.deputys_party_list);
        ArrayAdapter<Deputado> deputysAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1);
        deputys.setAdapter(deputysAdapter);

        final int partyId = getIntent().getIntExtra(Keys.PARTIDO_ID, 0);

        deputys.setOnItemClickListener((adapterView, view, i, l) -> {
            Intent intent = new Intent(getApplicationContext(), DeputyProfileActivity.class);
            intent.putExtra(Keys.DEPUTADO_ID, deputysAdapter.getItem(i).getId());
            intent.putExtra(Keys.PARTIDO_ID, partyId);
            startActivity(intent);
            finish();
        });

        APICaller apiCaller = new APICaller();
        apiCaller.getDeputadosByPartido(partyId, new CallbackData<>() {
            @Override
            public void onSuccess(List<Deputado> data) {
                deputysAdapter.clear();
                deputysAdapter.addAll(data);
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