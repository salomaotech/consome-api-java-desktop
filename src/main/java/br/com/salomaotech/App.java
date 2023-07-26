package br.com.salomaotech;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.swing.SwingUtilities;
import java.util.concurrent.CompletableFuture;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class App {

    public static void main(String[] args) {

        String apiUrl = "https://viacep.com.br/ws/01001000/json/";
        CompletableFuture<Response> responseFuture = ApiClient.getAsync(apiUrl);

        responseFuture.thenAccept(resposta -> {

            try (ResponseBody responseBody = resposta.body()) {

                if (!resposta.isSuccessful()) {

                    System.out.println("Falha! Sem resposta.");

                }

                String json = responseBody.string();
                ObjectMapper objectMapper = new ObjectMapper();

                /* aqui poderíamos passar outro objeto se fosse o caso */
                Endereco endereco = objectMapper.readValue(json, Endereco.class);

                SwingUtilities.invokeLater(() -> {

                    System.out.println("CEP: " + endereco.getCep());
                    System.out.println("Logradouro: " + endereco.getLogradouro());
                    System.out.println("Complemento: " + endereco.getComplemento());
                    System.out.println("Bairro: " + endereco.getBairro());
                    System.out.println("Localidade: " + endereco.getLocalidade());
                    System.out.println("UF: " + endereco.getUf());
                    System.out.println("IBGE: " + endereco.getIbge());
                    System.out.println("GIA: " + endereco.getGia());
                    System.out.println("DDD: " + endereco.getDdd());
                    System.out.println("SIAFI: " + endereco.getSiafi());

                    /* fecha o sistema */
                    System.exit(0);

                });

            } catch (Exception e) {

                System.out.println("Falha! Impossível converter JSON em um objeto.");

            }

        });

    }

}
