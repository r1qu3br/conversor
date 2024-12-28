import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JsonResponseAnalyzer {
    public static void main(String[] args) {
        // Exemplo da resposta
        String jsonResponse = "{\n" +
                " \"result\":\"success\",\n" +
                " \"documentation\":\"https://www.exchangerate-api.com/docs\",\n" +
                " \"terms_of_use\":\"https://www.exchangerate-api.com/terms\",\n" +
                " \"time_last_update_unix\":1735344002,\n" +
                " \"time_last_update_utc\":\"Sat, 28 Dec 2024 00:00:02 +0000\",\n" +
                " \"base_code\":\"USD\",\n" +
                " \"conversion_rates\":{\n" +
                " \"USD\":1,\n" +
                " \"EUR\":0.9597\n" +
                " }\n" +
                "}";

        // Usando Gson para analises
        JsonObject jsonObject = JsonParser.parseString(jsonResponse).getAsJsonObject();

        // Extrair o valor
        String baseCurrency = jsonObject.get("base_code").getAsString();
        System.out.println("Base currency: " + baseCurrency);

        // Extrair a txa de câmbio para Euro
        JsonObject conversionRates = jsonObject.getAsJsonObject("conversion_rates");
        double eurRate = conversionRates.get("EUR").getAsDouble();
        System.out.println("EUR rate: " + eurRate);
    }
}
