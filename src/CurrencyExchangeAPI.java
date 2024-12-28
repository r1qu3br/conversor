import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CurrencyExchangeAPI {
    public static void main(String[] args) {
        try {
            // Definindo a URL da API
            String url = "https://v6.exchangerate-api.com/v6/00586f14dfc7eb48038fdeeb/latest/USD";

            // Criando o cliente HTTP
            HttpClient client = HttpClient.newHttpClient();

            // Criando a requisição HTTP
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(url))  // URL da API
                    .build();

            // Enviando a requisição e recebendo a resposta
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());


            // Exibindo o status da resposta
            System.out.println("Status: " + response.statusCode());

            // Exibindo o corpo da resposta
            System.out.println("Resposta: " + response.body());

        } catch (Exception e) {
            e.printStackTrace();  // Exibindo erro se ocorrer algum problema
        }
    }
}
