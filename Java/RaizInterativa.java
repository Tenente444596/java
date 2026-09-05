import java.util.Scanner;

public class RaizInterativa {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Digite um número para calcular a raiz: ");
        double numero = scanner.nextDouble();
        
        // Validação básica para evitar erros matemáticos
        if (numero < 0) {
            System.out.println("Não existe raiz quadrada real para números negativos.");
        } else {
            double raiz = Math.sqrt(numero);
            System.out.println("A raiz quadrada de " + numero + " é: " + raiz);
        }
        
        scanner.close();
    }
}
