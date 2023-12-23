package br.ifsul.deputados;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import br.ifsul.deputados.utils.BottomNavigationMenu;
import br.ifsul.deputados.utils.Keys;
import br.ifsul.deputados.utils.api.APICaller;
import br.ifsul.deputados.utils.api.CallbackData;
import br.ifsul.deputados.utils.api.domain.Partido;

public class PartyProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_party_profile);

        BottomNavigationView bottomNavigation = findViewById(R.id.bottom_navigation_menu);
        bottomNavigation.setOnItemSelectedListener(item -> BottomNavigationMenu.listener(this, item));

        TextView name = findViewById(R.id.party_name);
        TextView partyAc = findViewById(R.id.party_ac);

        final int partyId = getIntent().getIntExtra(Keys.PARTIDO_ID, 0);
        Partido party = new Partido();

        APICaller apiCaller = new APICaller();
        apiCaller.getPartido(partyId, new CallbackData<>() {
            @Override
            public void onSuccess(Partido data) {
                party.set(data);
                name.setText("Nome: " + party.getNome());
                partyAc.setText("Sigla: " + party.getSigla());
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

        Button deputados = findViewById(R.id.see_deputys_button);
        deputados.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), DeputysProfileActivity.class);
            intent.putExtra(Keys.PARTIDO_ID, partyId);
            intent.putExtra(Keys.PARTIDO_SIGLA, party.getSigla());
            startActivity(intent);
            finish();
        });

    }
}