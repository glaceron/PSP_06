import javax.crypto.Cipher;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Scanner;

public class Encriptar
{
    private static Cipher rsa;

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        // Se salva y recupera de fichero la clave publica
        RSAAsymetricCrypto.saveKey(publicKey, "publickey.dat");
        publicKey = RSAAsymetricCrypto.loadPublicKey("publickey.dat");

        // Se salva y recupera de fichero la clave privada
        RSAAsymetricCrypto.saveKey(privateKey, "privatekey.dat");

        // Obtener la clase para encriptar/desencriptar
        rsa = Cipher.getInstance("RSA/ECB/PKCS1Padding");

        System.out.println("Introduce el texto que quieras encriptar:");

        // Texto a encriptar
        String text = scanner.nextLine();

        // Se encripta
        rsa.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encriptado = rsa.doFinal(text.getBytes());

        String pathName = "fichero.cifrado";
        RSAAsymetricCrypto.ToFile(encriptado,pathName);

        System.out.println("Mensaje cifrado y guardado correctamente, ejecute el programa 'Desencriptar' para poder ver descifrar el archivo");
    }

}
