import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import java.util.Base64;

public class CriptografiaAES {

    private static final String ALGORITMO = "AES/GCM/NoPadding";
    private static final int TAG_LENGTH_BIT = 128;

    public static void main(String[] args) throws Exception {
        // 1. Gerar uma chave secreta AES de 256 bits
        SecretKey chave = gerarChave();
        
        String textoOriginal = "Supermilitar, Superheroina, Kryptoniano, Alureano";
        
        System.out.println("Texto original: " + textoOriginal);
    
        // 2. Criptografar
        String textoCriptografado = criptografar(textoOriginal, chave);
        System.out.println("Criptografado: " + textoCriptografado);

    }

    public static SecretKey gerarChave() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(256);
        return keyGen.generateKey();
    }

    public static String criptografar(String texto, SecretKey chave) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITMO);
        cipher.init(Cipher.ENCRYPT_MODE, chave);
        byte[] iv = cipher.getIV();
        byte[] textoBytes = texto.getBytes("UTF-8");
        byte[] cipherBytes = cipher.doFinal(textoBytes);

        // Combinar IV + Dados Cifrados para salvar em um único lugar
        byte[] combinado = new byte[iv.length + cipherBytes.length];
        System.arraycopy(iv, 0, combinado, 0, iv.length);
        System.arraycopy(cipherBytes, 0, combinado, iv.length, cipherBytes.length);

        return Base64.getEncoder().encodeToString(combinado);
    }

    public static String descriptografar(String textoCifradoBase64, SecretKey chave) throws Exception {
        byte[] combinado = Base64.getDecoder().decode(textoCifradoBase64);

        // Extrair o IV (primeiros 12 bytes para GCM)
        byte[] iv = new byte[12];
        System.arraycopy(combinado, 0, iv, 0, 12);

        // Extrair o texto cifrado real
        byte[] cipherBytes = new byte[combinado.length - 12];
        System.arraycopy(combinado, 12, cipherBytes, 0, cipherBytes.length);

        Cipher cipher = Cipher.getInstance(ALGORITMO);
        GCMParameterSpec spec = new GCMParameterSpec(TAG_LENGTH_BIT, iv);
        cipher.init(Cipher.DECRYPT_MODE, chave, spec);

        byte[] originalBytes = cipher.doFinal(cipherBytes);
        return new String(originalBytes, "UTF-8");
    }
}