package br.edu.iff.pooa20152.projetohoje;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainEstabelecimentoActivity extends AppCompatActivity {
    private Button btEntrar, btCadastrar;
    private EditText ET_ID, ET_Senha;
    private TextView Alerta;
    private String texto;
    String durl = "http://ehhoje-app-pooa-20152.herokuapp.com/estabelecimentos.json";
    JSONArray retorno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_estabelecimento);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        retorno = null;

        btEntrar = (Button) findViewById(R.id.button);
        btCadastrar = (Button) findViewById(R.id.button19);
        ET_ID = (EditText) findViewById(R.id.editText);
        ET_Senha = (EditText) findViewById(R.id.editText2);
        //Alerta = (TextView) findViewById(R.id.TX_Alert);

        //Criando botão para chamar Tela Inicial Estabelecimento
        Button btEntrar = (Button) findViewById(R.id.button);

        //Chamando Tela Inicial Estabelecimento
        btEntrar.setOnClickListener(onClickEntrar());

        //Criando botão para chamar Tela Cadastro Estabelecimento
        Button btCadastrar = (Button) findViewById(R.id.button19);

        //Chamando Tela Cadastro Estabelecimento
        btEntrar.setOnClickListener(onClickCadastrar());

        btEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EstabelecimentoTask login = new EstabelecimentoTask(durl, RestFullHelper.GET, null);
                login.execute();
            }
        });

    }

    //método para chamar Tela Inicial Estabelecimento
    private View.OnClickListener onClickEntrar(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainEstabelecimentoActivity.this,TelaInicialEstabelecimentoActivity.class);
                startActivity(intent);
            }
        };
    }



    //método para chamar Tela de Cadastro de Estabelecimento
    private View.OnClickListener onClickCadastrar(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainEstabelecimentoActivity.this,CadastrarEstabelecimentoActivity.class);
                startActivity(intent);
            }
        };
    }

    //método para implementar seta para voltar para tela anterior
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == android.R.id.home) {

            finish();

            return true;

        }

        return super.onOptionsItemSelected(item);

    }

    public class EstabelecimentoTask extends AsyncTask<String, String, JSONArray> {
        String url = null;
        String method = null;
        JSONObject parametros = null;

        ProgressDialog dialog;

        public EstabelecimentoTask(String url, String method, JSONObject parametros) {
            this.url = url;
            this.method = method;
            this.parametros = parametros;

        }


        @Override
        protected JSONArray doInBackground(String... params) {
            RestFullHelper http = new RestFullHelper();

            if (retorno == null) {
                try {
                    retorno = http.getJSON(url, method, parametros).getJSONArray("estabelecimento");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return retorno;

        }

        @Override
        protected void onPreExecute(){
            dialog = new ProgressDialog(MainEstabelecimentoActivity.this);
            dialog.setTitle("Carregando");
            dialog.show();
        }

        @Override
        protected void onPostExecute(JSONArray estabelecimento) {

            int i;
            Boolean BEstabelecimento = false;
            JSONObject pessoa = null;

            for (i = 0; i < retorno.length(); i++) {
                try {
                    JSONObject obj = retorno.getJSONObject(i);
                    if (ET_ID.getText().toString().equals(obj.getString("ID"))){
                        if (ET_Senha.getText().toString().equals(obj.getString("senha"))){
                            BEstabelecimento = true;
                            pessoa = obj;
                            break;
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            dialog.dismiss();

            if (BEstabelecimento == true) {
                Intent tela = new Intent(MainEstabelecimentoActivity.this, TelaInicialEstabelecimentoActivity.class);
                //Bundle materia = new Bundle();
                //materia.putString("aluno", pessoa.toString());
                //tela.putExtras(materia);
                startActivity(tela);
                finish();
            } else {
                texto = "A matrícula ou senha está incorreta!";
                Alerta.setText(texto);
            }

        }
    }
}
