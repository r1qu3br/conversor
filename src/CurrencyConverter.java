import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CurrencyConverter {

    public static void main(String[] args) {
        try {
            // URL com a chave de API correta
            String apiUrl = "https://v6.exchangerate-api.com/v6/00586f14dfc7eb48038fdeeb/latest/USD";

            // Criação de URL e conexão
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Obter resposta da API
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Processar resposta JSON com Gson
            JsonObject myResponse = JsonParser.parseString(response.toString()).getAsJsonObject();

            // Exibir dados
            System.out.println("Status: " + myResponse.get("result").getAsString());
            System.out.println("Base: " + myResponse.get("base_code").getAsString());
            System.out.println("Taxa de câmbio para EUR: " + myResponse.getAsJsonObject("conversion_rates").get("EUR").getAsDouble());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
