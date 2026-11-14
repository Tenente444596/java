// O arquivo principal deve se chamar Programa.java
public class continencia {
    public static void main(String[] args) {
        Pessoa pessoa = new Pessoa("Supermilitar");
        pessoa.fazerContinencia();
    }
}

interface AlunoEEAR {
    default void fazerContinencia() {
        System.out.println("Bom dia Comandante!");
    }
}

class Pessoa implements AlunoEEAR {
    private String nome;

    public Pessoa(String nome) {
        this.nome = nome;
    }

    // Sobrescreve o método default da interface
    @Override
    public void fazerContinencia() {
        System.out.println("Bom dia Comandante! Sou o " + this.nome);
    }
}
