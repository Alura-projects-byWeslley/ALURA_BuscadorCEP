package main;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Digite o CEP que voce quer procurar:");
        var scan = new Scanner(System.in);
        String cep = scan.nextLine();

        String url = "https://viacep.com.br/ws/" + cep + "/json";
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = response.body();
            System.out.println(json);
            var escritor = new FileWriter("endereco.json");
            escritor.write(json);
            escritor.close();
        } catch (IOException e) {
            e.getMessage();
        }
        // scan.close();

    }
}

// api: viacep
// menu para interacao com o usuario
// geracao de um json com os dados do endereço