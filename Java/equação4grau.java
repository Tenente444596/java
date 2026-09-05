import java.util.Scanner;

public class equação4grau {

    // Define a função polinomial: f(x) = ax^4 + bx^3 + cx^2 + dx + e
    private static double f(double x, double a, double b, double c, double d, double e) {
        return a * Math.pow(x, 4) + b * Math.pow(x, 3) + c * Math.pow(x, 2) + d * x + e;
    }

    // Define a derivada da função: f'(x) = 4ax^3 + 3bx^2 + 2cx + d
    private static double fDerivada(double x, double a, double b, double c, double d) {
        return 4 * a * Math.pow(x, 3) + 3 * b * Math.pow(x, 2) + 2 * c * x + d;
    }

    // Método de Newton-Raphson para aproximar uma raiz real inicial
    private static double encontrarRaizNewton(double palpiteInicial, double a, double b, double c, double d, double e) {
        double x = palpiteInicial;
        double tolerancia = 1e-7;
        int maxIteracoes = 1000;

        for (int i = 0; i < maxIteracoes; i++) {
            double y = f(x, a, b, c, d, e);
            double yDerivada = fDerivada(x, a, b, c, d);

            if (Math.abs(yDerivada) < 1e-12) {
                break; // Evita divisão por zero
            }

            double proximoX = x - y / yDerivada;

            if (Math.abs(proximoX - x) < tolerancia) {
                return proximoX;
            }
            x = proximoX;
        }
        return x;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- Resolução de Equação de 4º Grau: ax^4 + bx^3 + cx^2 + dx + e = 0 ---");
        System.out.print("Digite o coeficiente a: ");
        double a = scanner.nextDouble();
        
        if (a == 0) { 
    System.out.println("O coeficiente a não pode ser zero em uma equação de 4º grau."); 
    return; 
}


        System.out.print("Digite o coeficiente b: ");
        double b = scanner.nextDouble();
        System.out.print("Digite o coeficiente c: ");
        double c = scanner.nextDouble();
        System.out.print("Digite o coeficiente d: ");
        double d = scanner.nextDouble();
        System.out.print("Digite o coeficiente e: ");
        double e = scanner.nextDouble();

        System.out.println("\nBuscando raízes reais...");

        // Testa diferentes palpites iniciais para varrer o polinômio e encontrar raízes distintas
        double[] palpites = {-100, -10, -1, 0, 1, 10, 100};
        double[] raizesEncontradas = new double[4];
        int numRaizes = 0;
        double toleranciaDuplicada = 1e-4;

        for (double palpite : palpites) {
            double raiz = encontrarRaizNewton(palpite, a, b, c, d, e);
            
            // Verifica se a aproximação realmente zerou a função com sucesso
            if (Math.abs(f(raiz, a, b, c, d, e)) < 1e-3) {
                boolean jaExiste = false;
                
                // Evita salvar raízes repetidas devido a palpites diferentes convergirem ao mesmo ponto
                for (int i = 0; i < numRaizes; i++) {
                    if (Math.abs(raizesEncontradas[i] - raiz) < toleranciaDuplicada) {
                        jaExiste = true;
                        break;
                    }
                }
                
                if (!jaExiste && numRaizes < 4) {
                    raizesEncontradas[numRaizes] = raiz;
                    numRaizes++;
                }
            }
        }

        // Exibe os resultados na tela
        if (numRaizes == 0) {
            System.out.println("Nenhuma raiz real foi encontrada (as raízes podem ser complexas/imaginárias).");
        } else {
            System.out.println("Raízes reais encontradas:");
            for (int i = 0; i < numRaizes; i++) {
                System.out.printf("x%d = %.4f\n", (i + 1), raizesEncontradas[i]);
            }
        }
        
        scanner.close();
    }
}
