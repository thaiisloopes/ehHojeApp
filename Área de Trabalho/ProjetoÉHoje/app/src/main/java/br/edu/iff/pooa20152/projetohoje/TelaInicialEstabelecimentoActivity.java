package br.edu.iff.pooa20152.projetohoje;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class TelaInicialEstabelecimentoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial_estabelecimento);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button btCadastrarEvento = (Button) findViewById(R.id.button14);

        btCadastrarEvento.setOnClickListener(onClickCadastrarEvento());

        Button btCadastrarRepresentantes = (Button) findViewById(R.id.button15);

        btCadastrarRepresentantes.setOnClickListener(onClickCadastrarRepresentantes());

        Button btRemoverRepresentantes = (Button) findViewById(R.id.button16);

        btRemoverRepresentantes.setOnClickListener(onClickRemoverRepresentantes());

        Button btSair = (Button) findViewById(R.id.button18);

        btSair.setOnClickListener(onClickSair());
    }

    private View.OnClickListener onClickCadastrarEvento() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TelaInicialEstabelecimentoActivity.this,CadastrarEventosActivity.class);
                startActivity(intent);
            }
        };
    }

    private View.OnClickListener onClickCadastrarRepresentantes() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TelaInicialEstabelecimentoActivity.this,CadastrarRepresentantesActivity.class);
                startActivity(intent);
            }
        };
    }

    private View.OnClickListener onClickRemoverRepresentantes() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TelaInicialEstabelecimentoActivity.this,RemoverRepresentantesActivity.class);
                startActivity(intent);
            }
        };
    }

    private View.OnClickListener onClickSair() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TelaInicialEstabelecimentoActivity.this,MainEstabelecimentoActivity.class);
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
