import java.util.Scanner;

public class ConversorKwParaCv {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o valor em kW: ");
        double kw = scanner.nextDouble();

        // Fator de conversão: 1 kW = 1,35962 CV (ou aprox. 1,36)
        double cv = kw * 1.35962;

        System.out.printf("Valor em CV: %.2f CV\n", cv);

        scanner.close();
    }
}
