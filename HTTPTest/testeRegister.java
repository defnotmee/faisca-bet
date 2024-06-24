import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class testeRegister{


    private static String getFormDataAsString(Map<String, String> formData) {
        StringBuilder formBodyBuilder = new StringBuilder();
        for (Map.Entry<String, String> singleEntry : formData.entrySet()) {
            if (formBodyBuilder.length() > 0) {
                formBodyBuilder.append("&");
            }
            formBodyBuilder.append(URLEncoder.encode(singleEntry.getKey(), StandardCharsets.UTF_8));
            formBodyBuilder.append("=");
            formBodyBuilder.append(URLEncoder.encode(singleEntry.getValue(), StandardCharsets.UTF_8));
        }
        return formBodyBuilder.toString();
    }

    public static void main(String[] args) throws IOException, InterruptedException{
        HttpClient client = HttpClient.newHttpClient();

        Map<String,String> mp = new HashMap<>();

        mp.put("nome", "Leo");
        mp.put("email", "douglas@bibibo.com");
        mp.put("senha", "pipippipooo");
        mp.put("cpf", "43759625851");

        String serviceUrl = "http://localhost:8080/faisca-api/register";
            
        HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(serviceUrl))
        .header("Content-Type", "application/x-www-form-urlencoded")
        .POST(HttpRequest.BodyPublishers.ofString(getFormDataAsString(mp)))
        .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.statusCode());
        System.out.println(response.body());
    }
    
}