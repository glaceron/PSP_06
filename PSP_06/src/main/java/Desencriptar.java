import java.io.*;
import java.util.Scanner;

public class Desencriptar
{
    public static void main(String[] args) throws IOException
    {
        Scanner scanner = new Scanner(System.in);
        int metodo;

        do {

            System.out.println("Que metodo quieres usar para desencriptar?");
            System.out.println("    1) AES256");
            System.out.println("    2) DES");
            System.out.println("    3) BLOWFISH");
            System.out.println("    4) SALIR");
            try
            {
                metodo = scanner.nextInt();
            }
            catch (Exception e)
            {
                System.out.println("Introduce una opcion de la 1 a la 4");
                metodo=0;
            }
            if (metodo == 1) {
                File archivo = null;
                FileReader fr = null;
                BufferedReader br = null;
                String desencriptado;
                String decryptedString = "";

                try {
                    // Apertura del fichero y creacion de BufferedReader para poder
                    // hacer una lectura comoda (disponer del metodo readLine()).
                    archivo = new File("fichero.cifrado");
                    fr = new FileReader(archivo);
                    br = new BufferedReader(fr);

                    // Lectura del fichero
                    String linea;
                    while ((linea = br.readLine()) != null) {
                        linea = AES256.decrypt(linea);
                        System.out.println(linea);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    // En el finally cerramos el fichero, para asegurarnos
                    // que se cierra tanto si todo va bien como si salta
                    // una excepcion.
                    try {
                        if (null != fr) {
                            fr.close();
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
                metodo = 4;
            }

            if (metodo == 2) {
                File archivo = null;
                FileReader fr = null;
                BufferedReader br = null;
                String desencriptado;
                String decryptedString = "";
                try {
                    String key = "squirrel123"; // needs to be at least 8 characters for DES

                    FileInputStream fis2 = new FileInputStream("fichero.cifrado");
                    FileOutputStream fos2 = new FileOutputStream("decrypted.txt");
                    DES.decrypt(key, fis2, fos2);

                    try {
                        // Apertura del fichero y creacion de BufferedReader para poder
                        // hacer una lectura comoda (disponer del metodo readLine()).
                        archivo = new File("decrypted.txt");
                        fr = new FileReader(archivo);
                        br = new BufferedReader(fr);

                        // Lectura del fichero
                        String linea;

                        while ((linea = br.readLine()) != null) {
                            System.out.println(linea);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        // En el finally cerramos el fichero, para asegurarnos
                        // que se cierra tanto si todo va bien como si salta
                        // una excepcion.
                        try {
                            if (null != fr) {
                                fr.close();
                            }
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    }

                } catch (Throwable e) {
                    e.printStackTrace();
                }
                metodo = 4;
            }

            if (metodo == 3) {
                File archivo = null;
                FileReader fr = null;
                BufferedReader br = null;
                String desencriptado = null;

                try {
                    // Apertura del fichero y creacion de BufferedReader para poder
                    // hacer una lectura comoda (disponer del metodo readLine()).
                    archivo = new File("fichero.cifrado");
                    fr = new FileReader(archivo);
                    br = new BufferedReader(fr);

                    // Lectura del fichero
                    String linea;
                    while ((linea = br.readLine()) != null) {
                        System.out.println("Frase desencriptada :" + Blowfish.decrypt(linea));
                    }
                    desencriptado = linea;
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    // En el finally cerramos el fichero, para asegurarnos
                    // que se cierra tanto si todo va bien como si salta
                    // una excepcion.
                    try {
                        if (null != fr) {
                            fr.close();
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
                metodo = 4;
            }
        }while (metodo != 4);
    }
}

