package br.edu.iff.pooa20152.projetohoje;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class TelaInicialClienteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial_cliente);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button btAvaliar = (Button) findViewById(R.id.button8);

        btAvaliar.setOnClickListener(onClickAvaliar());

        Button btContato = (Button) findViewById(R.id.button9);

        btContato.setOnClickListener(onClickContato());

        Button btComprarIngresso = (Button) findViewById(R.id.button12);

        btComprarIngresso.setOnClickListener(onClickComprarIngresso());

        Button btSair = (Button) findViewById(R.id.button2);

        btSair.setOnClickListener(onClickSair());

        Button btLista = (Button) findViewById(R.id.button4);

        btLista.setOnClickListener(onClickLista());

        Button btInformacao01 = (Button) findViewById(R.id.button5);

        btInformacao01.setOnClickListener(onClickInformacao01());
    }

    private View.OnClickListener onClickAvaliar() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TelaInicialClienteActivity.this,AvaliacaoActivity.class);
                startActivity(intent);
            }
        };
    }

    private View.OnClickListener onClickContato() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TelaInicialClienteActivity.this,ContatoActivity.class);
                startActivity(intent);
            }
        };
    }

    private View.OnClickListener onClickComprarIngresso() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TelaInicialClienteActivity.this,ComprarIngressoActivity.class);
                startActivity(intent);
            }
        };
    }

    private View.OnClickListener onClickInformacao01() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TelaInicialClienteActivity.this,InformacoesEvento01Activity.class);
                startActivity(intent);
            }
        };
    }

    private View.OnClickListener onClickSair() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TelaInicialClienteActivity.this,MainClienteActivity.class);
                startActivity(intent);
            }
        };
    }

    private View.OnClickListener onClickLista() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context contexto = getApplicationContext();
                String texto = "Nome incluído na lista VIP.";
                int duracao = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(contexto, texto, duracao);
                toast.show();
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



