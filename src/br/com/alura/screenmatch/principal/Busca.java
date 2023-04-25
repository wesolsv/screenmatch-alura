package br.com.alura.screenmatch.principal;

import br.com.alura.screenmatch.excecao.ErroAnoException;
import br.com.alura.screenmatch.modelos.Titulo;
import br.com.alura.screenmatch.modelos.TituloOmdb;
import br.com.alura.screenmatch.requisicao.Requisicao;
import br.com.alura.screenmatch.util.CriarArquivoJson;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Busca {
    public static void main(String[] args) throws IOException, InterruptedException {

        Scanner leitura = new Scanner(System.in);
        var busca = "";
        List<Titulo> titulos = new ArrayList<>();

        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .setPrettyPrinting()
                .create();

        while (true) {
            System.out.println("Digite um filme para busca");

            busca = leitura.nextLine();

            if(busca.equalsIgnoreCase("sair")){break;}

            String endereco = "http://www.omdbapi.com/?t=" + busca.replaceAll(" ", "+") + "&apikey=";

            String json = Requisicao.requisicao(endereco);

            System.out.println(json);

            TituloOmdb tituloOmdb = gson.fromJson(json, TituloOmdb.class);

            System.out.println(tituloOmdb);

            try {
                Titulo titulo = new Titulo(tituloOmdb);

                titulos.add(titulo);

            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            } catch (ErroAnoException e) {
                System.out.println(e.getMessage());
            }
        }

        CriarArquivoJson.criarArquivo(gson.toJson(titulos));

        System.out.println("finalizou corretamente");
    }
}
