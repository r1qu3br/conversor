import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpHeaders;
import java.io.IOException;
import java.util.Map;
import java.util.List;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class HttpResponserProcessor {

    public static void main(String[] args) {
        // URL da API de taxas de câmbio
        String apiUrl = "https://v6.exchangerate-api.com/v6/00586f14dfc7eb48038fdeeb/latest/USD";

        // Cria o cliente HTTP
        HttpClient client = HttpClient.newHttpClient();

        // Cria a requisição HTTP
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .build();

        try {
            // Envia a requisição e obtém a resposta
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Exibe o status da resposta
            int statusCode = response.statusCode();
            System.out.println("Status Code: " + statusCode);

            // Exibe os cabeçalhos da resposta
            HttpHeaders headers = response.headers();
            Map<String, List<String>> headerMap = headers.map();
            System.out.println("Headers: " + headerMap);

            // Exibe o corpo da resposta (geralmente JSON)
            String responseBody = response.body();
            System.out.println("Response Body: " + responseBody);

            // Processar o corpo da resposta (JSON) com Gson
            JsonObject jsonResponse = JsonParser.parseString(responseBody).getAsJsonObject();

            // Acessando a base de câmbio
            if (jsonResponse.has("base_code")) {
                String base = jsonResponse.get("base_code").getAsString();
                System.out.println("Base currency: " + base);
            } else {
                System.out.println("Base currency not found in the response.");
            }

            // Acessar as taxas de câmbio
            if (jsonResponse.has("conversion_rates")) {
                JsonObject conversionRates = jsonResponse.getAsJsonObject("conversion_rates");
                if (conversionRates.has("EUR")) {
                    System.out.println("EUR rate: " + conversionRates.get("EUR").getAsDouble());
                } else {
                    System.out.println("EUR rate not found in the response.");
                }
            } else {
                System.out.println("Conversion rates not found in the response.");
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
