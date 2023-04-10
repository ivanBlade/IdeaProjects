import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {


        List<String> rutaUrls = new ArrayList<>();
        long anio;
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingresa un año: ");
        anio = sc.nextLong();

        boolean resultado = esBisiesto(anio);

        if (resultado) {
            System.out.println("Si es bisiesto");
        } else {
            System.out.println("No es bisiesto");
        }



        for (anio = 1900; anio < 2500; anio++) {
            if (esBisiesto(anio)) {
                System.out.printf("%d, ", anio);
            }
        }
        long tiempoInicio = System.nanoTime();
        rutaUrls.stream().forEach(s -> Tiempo(s));
        long tiempoFin = (System.nanoTime());
        System.out.println();
        System.out.print("Diferencia de tiempo sin programacion paralela " + ((tiempoFin - tiempoInicio) / 1_000_000_000));

        tiempoInicio = System.nanoTime();
        rutaUrls.stream().parallel().forEach(s -> Tiempo(s));
        tiempoFin = (System.nanoTime());
        System.out.println();
        System.out.print("Diferencia de tiempo con programacion paralela " + ((tiempoFin - tiempoInicio) / 1_000_000_000));
    }

    public static boolean esBisiesto(long anio) {

        return anio % 4 == 0 && (anio % 100 != 0 || anio % 400 == 0);

    }

    private static String Tiempo(String rutaUrl) {
        try {
            URL url = new URL(rutaUrl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            Stream<String> lineas = new BufferedReader(new InputStreamReader(inputStream)).lines();
            return lineas.collect(Collectors.joining());

            //.collect(Collectors.joining())
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }
        return "";
    }

}