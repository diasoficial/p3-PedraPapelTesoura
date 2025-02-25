package com.dias.aulapedrapapeltesoura;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void selecionarPedra(View view) {
        verificarGanhador("pedra");
    }
    public void selecionarPapel(View view) {
        verificarGanhador("papel");
    }
    public void selecionarTesoura(View view) {
        verificarGanhador("tesoura");
    }
    private String gerarEscolhaAleatoriaApp() {
        /*
        Forma 1
        Random random = new Random();
        random.nextInt();
        */
        String[] opcoes = {"pedra", "papel", "tesoura"};
        int numeroAleatorio = new Random().nextInt(3); // 0 1 2

        ImageView imagemApp = findViewById(R.id.image_app);
        String escolhaApp = opcoes[numeroAleatorio];
        switch (escolhaApp) {
            case "pedra" :
                imagemApp.setImageResource(R.drawable.pedra); //setImageResource configura uma imagem de recurso
                break;
            case "papel" :
                imagemApp.setImageResource(R.drawable.papel);
                break;
            case "tesoura" :
                imagemApp.setImageResource(R.drawable.tesoura);
                break;
        }

        return escolhaApp;
        //return opcoes[numeroAleatorio];
    }

    private void verificarGanhador(String escolhaUsuario) {

        String escolhaApp = gerarEscolhaAleatoriaApp();
        //System.out.println("Item clicado: " + escolhaUsuario);

        TextView textoResultado = findViewById(R.id.text_resultado); // Exibe para o usuario

        if( // App é ganhador
                (escolhaApp == "pedra" && escolhaUsuario == "tesoura") ||
                (escolhaApp == "papel" && escolhaUsuario == "pedra") ||
                (escolhaApp == "tesoura" && escolhaUsuario == "papel")
        ) {
            textoResultado.setText("Você perdeu :(");
        } else if( // Usuário é ganhador
                (escolhaUsuario == "pedra" && escolhaApp == "tesoura") ||
                (escolhaUsuario == "papel" && escolhaApp == "pedra") ||
                (escolhaUsuario == "tesoura" && escolhaApp == "papel")
        ) {
            textoResultado.setText("Você ganhou!! :)");
        } else {// Empatou
            textoResultado.setText("Empatamos ;)");
        }
    }

    public void somarGanhos(String escolhaUsuario) {
        int qtdPerdas = 0;
        int qtdGanhos = 0;

        String escolhaApp = gerarEscolhaAleatoriaApp();

        TextView textoPerdas = findViewById(R.id.qtdPerdas); // Exibe para o usuario
        TextView textoGanhos = findViewById(R.id.qtdGanhos); // Exibe para o usuario

        if( // App é ganhador
                (escolhaApp == "pedra" && escolhaUsuario == "tesoura") ||
                (escolhaApp == "papel" && escolhaUsuario == "pedra") ||
                (escolhaApp == "tesoura" && escolhaUsuario == "papel")
        ) {
            qtdPerdas = qtdPerdas++;
            textoPerdas.setText(qtdPerdas);
        } else {
            qtdGanhos = qtdGanhos++;
            textoGanhos.setText(qtdGanhos);
        }
    }
}