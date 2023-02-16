import java.util.Scanner;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        int choice;
        String fromCurr = "", toCurr = "";
        do {
            System.out.println("What currency do you want to enter first? ");
            System.out.println("1.PLN\n2.EUR\n3.USD\n4.JPY\n5.Exit");
            choice = keyboard.nextInt();
            switch (choice) {
                case 1:
                    fromCurr = "pln";
                    break;
                case 2:
                    fromCurr = "eur";
                    break;
                case 3:
                    fromCurr = "usd";
                    break;
                case 4:
                    fromCurr = "jpy";
                    break;
                default:
                    break;
            }
            System.out.println("What currency do you want to econvert into? ");
            System.out.println("1.PLN\n2.EUR\n3.USD\n4.JPY\n");
            choice = keyboard.nextInt();
            switch (choice) {
                case 1:
                    toCurr = "pln";
                    break;
                case 2:
                    toCurr = "eur";
                    break;
                case 3:
                    toCurr = "usd";
                    break;
                case 4:
                    toCurr = "jpy";
                    break;
                default:
                    break;
            }
            System.out.println("How much money do you want to convert?: ");
            float amount = keyboard.nextFloat();
            System.out.println(calculate(fromCurr, toCurr, amount));

        } while (choice != 5);
    }

    public static float calculate(String currency1, String currency2, float quantity) {
        try {
            String uri = "https://cdn.jsdelivr.net/gh/fawazahmed0/currency-api@1/latest/currencies/" + currency1 + "/" + currency2 + ".json";
            URL url = new URL(uri);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            String newResponse = response.substring(37, response.length());
            float v = (Float.parseFloat(newResponse.substring(0, newResponse.length() - 1))) * quantity;
            return v;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return Float.NaN;
        }
    }
}
