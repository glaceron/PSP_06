import javax.crypto.Cipher;
import java.security.PrivateKey;


public class Desencriptar
{
    private static Cipher rsa;

    public static void main(String[] args) throws Exception
    {
        PrivateKey privateKey;
        privateKey = RSAAsymetricCrypto.loadPrivateKey("privatekey.dat");

        byte[] leido = RSAAsymetricCrypto.ToBytes("fichero.cifrado");

        rsa = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        rsa.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] bytesDesencriptados = rsa.doFinal(leido);
        String textoDesencripado = new String(bytesDesencriptados);

        // Se escribe el texto desencriptado
        System.out.println("Texto leido: " + textoDesencripado);
    }
}
