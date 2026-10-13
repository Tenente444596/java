import java.math.BigInteger;
public class multicaçao { 
    public static void main(String[] args) { 
        BigInteger a = new BigInteger("118877"); 
        BigInteger b = new BigInteger("118893"); 
        BigInteger c = new BigInteger("122289"); 
        BigInteger d = new BigInteger("228877"); 
        BigInteger e = new BigInteger("228893"); 
        BigInteger f = new BigInteger("234192"); 
        BigInteger g = new BigInteger("338877"); 
        BigInteger h = new BigInteger("338893"); 
        BigInteger i = new BigInteger("340871"); 
        BigInteger j = new BigInteger("448877"); 
        BigInteger k = new BigInteger("448893"); 
        BigInteger l = new BigInteger("504840"); 

        // Multiplicação sequencial usando o método .multiply()
        BigInteger produto = a.multiply(b)
                              .multiply(c)
                              .multiply(d)
                              .multiply(e)
                              .multiply(f)
                              .multiply(g)
                              .multiply(h)
                              .multiply(i)
                              .multiply(j)
                              .multiply(k)
                              .multiply(l); 

        System.out.println("A multiplicação é: " + produto); 
    } 
}
