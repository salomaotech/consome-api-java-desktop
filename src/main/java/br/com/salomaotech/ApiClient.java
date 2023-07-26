package br.com.salomaotech;

import okhttp3.*;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

public class ApiClient {

    private static final OkHttpClient httpClient = new OkHttpClient();

    public static CompletableFuture<Response> getAsync(String url) {

        Request requisicao = new Request.Builder().url(url).build();
        CompletableFuture<Response> responseFuture = new CompletableFuture<>();

        httpClient.newCall(requisicao).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {

                responseFuture.completeExceptionally(e);

            }

            @Override
            public void onResponse(Call call, Response resposta) {

                responseFuture.complete(resposta);

            }

        });

        return responseFuture;

    }

}
