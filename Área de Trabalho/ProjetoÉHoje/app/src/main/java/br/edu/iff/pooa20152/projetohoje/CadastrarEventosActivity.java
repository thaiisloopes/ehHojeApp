package br.edu.iff.pooa20152.projetohoje;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class CadastrarEventosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_eventos);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button btCadastrar = (Button) findViewById(R.id.buttonCadastrar);
        btCadastrar.setOnClickListener(onClickCadastrar());
    }

    private View.OnClickListener onClickCadastrar() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CadastrarEventosActivity.this,TelaInicialEstabelecimentoActivity.class);
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
