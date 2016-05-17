package br.edu.iff.pooa20152.projetohoje;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SelecionarLocalidadeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selecionar_localidade);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button btProximo = (Button) findViewById(R.id.button10);

        btProximo.setOnClickListener(onClickProximo());
    }


    private View.OnClickListener onClickProximo() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelecionarLocalidadeActivity.this,TelaInicialClienteActivity.class);
                startActivity(intent);
            }
        };
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == android.R.id.home) {

            finish();

            return true;

        }

        return super.onOptionsItemSelected(item);

    }

}
