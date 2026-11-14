import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class Polinomiosub {
    // Armazena <Expoente, Coeficiente> ordenados do maior expoente para o menor
    private final Map<Integer, Double> termos;

    public Polinomiosub() {
        this.termos = new TreeMap<>(Collections.reverseOrder());
    }

    // Adiciona ou atualiza um termo no polinômio
    public void adicionarTermo(double coeficiente, int expoente) {
        if (expoente < 0) {
            throw new IllegalArgumentException("O expoente não pode ser negativo.");
        }
        if (coeficiente == 0) {
            return; // Ignora termos nulos
        }

        double novoCoeficiente = this.termos.getOrDefault(expoente, 0.0) + coeficiente;
        
        if (novoCoeficiente == 0) {
            this.termos.remove(expoente); // Remove o termo se a soma zerar
        } else {
            this.termos.put(expoente, novoCoeficiente);
        }
    }

    // Soma o polinômio atual com outro e retorna um novo Polinomio
    public Polinomiosub somar(Polinomiosub outro) {
        Polinomiosub resultado = new Polinomiosub();

        for (Map.Entry<Integer, Double> entry : this.termos.entrySet()) {
            resultado.adicionarTermo(entry.getValue(), entry.getKey());
        }

        for (Map.Entry<Integer, Double> entry : outro.termos.entrySet()) {
            resultado.adicionarTermo(entry.getValue(), entry.getKey());
        }

        return resultado;
    }

    // Subtrai outro polinômio do polinômio atual e retorna um novo Polinomio
    public Polinomiosub subtrair(Polinomiosub outro) {
        Polinomiosub resultado = new Polinomiosub();

        // Copia todos os termos do primeiro polinômio
        for (Map.Entry<Integer, Double> entry : this.termos.entrySet()) {
            resultado.adicionarTermo(entry.getValue(), entry.getKey());
        }

        // Subtrai os termos do segundo polinômio (invertendo o sinal)
        for (Map.Entry<Integer, Double> entry : outro.termos.entrySet()) {
            resultado.adicionarTermo(-entry.getValue(), entry.getKey());
        }

        return resultado;
    }

    // Formata o polinômio para exibição legível (ex: 3x^2 - 2x + 5)
    @Override
    public String toString() {
        if (termos.isEmpty()) {
            return "0";
        }

        StringBuilder sb = new StringBuilder();
        boolean primeiroTermo = true;

        for (Map.Entry<Integer, Double> entry : termos.entrySet()) {
            int exp = entry.getKey();
            double coef = entry.getValue();

            // Formatação do sinal (+ ou -)
            if (!primeiroTermo) {
                sb.append(coef > 0 ? " + " : " - ");
            } else if (coef < 0) {
                sb.append("-");
            }

            double absCoef = Math.abs(coef);
            
            // Formata o número (remove ".0" se for inteiro)
            String coefStr = (absCoef == (long) absCoef) 
                ? String.format("%d", (long) absCoef) 
                : String.valueOf(absCoef);

            // Omite o coeficiente '1' se houver variável x (ex: 1x^2 vira x^2)
            if (absCoef != 1 || exp == 0) {
                sb.append(coefStr);
            }

            // Formatação da variável x e do expoente
            if (exp > 0) {
                sb.append("x");
                if (exp > 1) {
                    sb.append("^").append(exp);
                }
            }

            primeiroTermo = false;
        }

        return sb.toString();
    }

    // Método principal para teste
    public static void main(String[] args) {
        // P1(x) = 3x^3 + 2x^2 - 5x + 1
        Polinomiosub p1 = new Polinomiosub();
        p1.adicionarTermo(3, 3);
        p1.adicionarTermo(2, 2);
        p1.adicionarTermo(-5, 1);
        p1.adicionarTermo(1, 0);

        // P2(x) = -3x^3 + 4x^2 + 2x - 7
        Polinomiosub p2 = new Polinomiosub();
        p2.adicionarTermo(-3, 3);
        p2.adicionarTermo(4, 2);
        p2.adicionarTermo(2, 1);
        p2.adicionarTermo(-7, 0);

        // P3(x) = x^2 + 3x + 4
        Polinomiosub p3 = new Polinomiosub();
        p3.adicionarTermo(1, 2);
        p3.adicionarTermo(3, 1);
        p3.adicionarTermo(4, 0);

        // P4(x) = 2x^3 - x^2 + 2
        Polinomiosub p4 = new Polinomiosub();
        p4.adicionarTermo(2, 3);
        p4.adicionarTermo(-1, 2);
        p4.adicionarTermo(2, 0);

        // Subtraindo os quatro polinômios: P1 - P2 - P3 - P4
        Polinomiosub subtracao = p1.subtrair(p2).subtrair(p3).subtrair(p4);

        System.out.println("P1(x)     = " + p1);
        System.out.println("P2(x)     = " + p2);
        System.out.println("P3(x)     = " + p3);
        System.out.println("P4(x)     = " + p4);
        System.out.println("Subtração = " + subtracao);
    }
}
