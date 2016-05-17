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


public class MainClienteActivity extends AppCompatActivity {

    private Button btEntrar, btCadastrar, btAcesso;
    private EditText ET_Login, ET_Senha;
    private TextView Alerta;
    private String texto;
    String durl = "http://ehhoje-app-pooa-20152.herokuapp.com/clientes.json";
    JSONArray retorno;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_cliente);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        retorno=null;

        btEntrar = (Button) findViewById(R.id.button);
        btCadastrar = (Button) findViewById(R.id.button2);
        btAcesso = (Button) findViewById(R.id.button13);
        ET_Login = (EditText) findViewById(R.id.editText);
        ET_Senha = (EditText) findViewById(R.id.editText2);
        //Alerta = (TextView) findViewById(R.id.TX_Alert);

        btEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClienteTask login = new ClienteTask(durl, RestFullHelper.GET, null);
                login.execute();
            }
        });

        //Criando botão btCadastrar que chamará tela de cadastro Cliente
        Button btCadastrar = (Button) findViewById(R.id.button2);

        //Chamando tela Cadastrar Cliente
        btCadastrar.setOnClickListener(onClickCadastrarCliente());

        //Criando botão btEntrar que chamará tela Selecionar Localidade
        Button btEntrar = (Button) findViewById(R.id.button);

        //Chamando tela Selecionar Localidade
        btEntrar.setOnClickListener(onClickEntrar());

        //Criando botão btAcesso que chamará tela Main do Estabelecimento
        Button btAcesso = (Button) findViewById(R.id.button13);

        //Chamando tela Main do Estabelecimento
        btAcesso.setOnClickListener(onClickAcesso());
    }

    //método para chamar cadastrar Cliente
    private View.OnClickListener onClickCadastrarCliente() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainClienteActivity.this,CadastroActivity.class);
                startActivity(intent);
            }
        };
    }

    //método para chamar 1ª tela de Cliente após login
    private View.OnClickListener onClickEntrar(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainClienteActivity.this,SelecionarLocalidadeActivity.class);
                startActivity(intent);
            }
        };
    }

    //método para chamar main Estabelecimento
    private View.OnClickListener onClickAcesso(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainClienteActivity.this,MainEstabelecimentoActivity.class);
                startActivity(intent);
            }
        };
    }

    //seta para voltar a tela anterior
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == android.R.id.home) {

            finish();

            return true;

        }

        return super.onOptionsItemSelected(item);

    }

    public class ClienteTask extends AsyncTask<String, String, JSONArray> {
        String url = null;
        String method = null;
        JSONObject parametros = null;

        ProgressDialog dialog;

        public ClienteTask(String url, String method, JSONObject parametros) {
            this.url = url;
            this.method = method;
            this.parametros = parametros;

        }


        @Override
        protected JSONArray doInBackground(String... params) {
            RestFullHelper http = new RestFullHelper();

            if (retorno == null) {
                try {
                    retorno = http.getJSON(url, method, parametros).getJSONArray("cliente");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return retorno;

        }

        @Override
        protected void onPreExecute(){
            dialog = new ProgressDialog(MainClienteActivity.this);
            dialog.setTitle("Carregando");
            dialog.show();
        }

        @Override
        protected void onPostExecute(JSONArray cliente) {

            int i;
            Boolean BCliente = false;
            JSONObject pessoa = null;

            for (i = 0; i < retorno.length(); i++) {
                try {
                    JSONObject obj = retorno.getJSONObject(i);
                    if (ET_Login.getText().toString().equals(obj.getString("login"))){
                        if (ET_Senha.getText().toString().equals(obj.getString("senha"))){
                            BCliente = true;
                            pessoa = obj;
                            break;
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            dialog.dismiss();

            if (BCliente == true) {
                Intent tela = new Intent(MainClienteActivity.this, SelecionarLocalidadeActivity.class);
                //Bundle materia = new Bundle();
                //materia.putString("aluno", pessoa.toString());
                //tela.putExtras(materia);
                startActivity(tela);
                finish();
            } else {
                    texto = "Login ou senha incorretos!";
                    Alerta.setText(texto);
              }
            }

    }
}
