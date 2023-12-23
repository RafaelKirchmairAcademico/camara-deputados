package br.ifsul.deputados;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import br.ifsul.deputados.utils.BottomNavigationMenu;
import br.ifsul.deputados.utils.Keys;
import br.ifsul.deputados.utils.api.APICaller;
import br.ifsul.deputados.utils.api.CallbackData;
import br.ifsul.deputados.utils.api.domain.Deputado;

public class DeputyProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deputy_profile);

        BottomNavigationView bottomNavigation = findViewById(R.id.bottom_navigation_menu);
        bottomNavigation.setOnItemSelectedListener(item -> BottomNavigationMenu.listener(this, item));

        TextView name = findViewById(R.id.deputy_name);
        TextView partyAc = findViewById(R.id.deputy_party_ac);
        TextView ufAc = findViewById(R.id.deputy_uf_ac);
        TextView email = findViewById(R.id.deputy_email);

        final int deputyId = getIntent().getIntExtra(Keys.DEPUTADO_ID, 0);
        Deputado deputy = new Deputado();

        APICaller apiCaller = new APICaller();
        apiCaller.getDeputado(deputyId, new CallbackData<>() {
            @Override
            public void onSuccess(Deputado data) {
                deputy.set(data);
                name.setText("Nome: " + deputy.getUltimoStatus().getNome());
                partyAc.setText("Partido: " + deputy.getUltimoStatus().getSiglaPartido());
                ufAc.setText("UF: " + deputy.getUltimoStatus().getSiglaUf());
                email.setText("E-mail: " + deputy.getUltimoStatus().getEmail());
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