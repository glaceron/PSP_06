import java.awt.*;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class Encriptar {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        int metodo;


        do {

            System.out.println("Introduce la frase que quieras encryptar:");
            String frase = scanner.nextLine();
            System.out.println("Que metodo quieres usar:");
            System.out.println("    1) AES256");
            System.out.println("    2) DES");
            System.out.println("    3) BLOWFISH");
            System.out.println("    4) SALIR");
            try {
                metodo = scanner.nextInt();
            }catch (Exception e){
                System.out.println("Introduce una de las 4 opciones");
                metodo=0;
            }
            if (metodo == 1) {
                String encryptedString = AES256.encrypt(frase);
                System.out.println(encryptedString);
                FileWriter fichero = null;
                PrintWriter pw = null;
                try {
                    fichero = new FileWriter("fichero.cifrado");
                    pw = new PrintWriter(fichero);
                    pw.print(encryptedString);

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (null != fichero)
                            fichero.close();
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            }
            if (metodo == 2) {
                FileWriter fichero = null;
                PrintWriter pw = null;
                try {
                    String key = "squirrel123"; // needs to be at least 8 characters for DES

                    try {
                        fichero = new FileWriter("original.txt");
                        pw = new PrintWriter(fichero);
                        pw.print(frase);

                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            if (null != fichero)
                                fichero.close();
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    }
                    FileInputStream fis = new FileInputStream("original.txt");
                    FileOutputStream fos = new FileOutputStream("fichero.cifrado");
                    DES.encrypt(key, fis, fos);
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            }
            if (metodo == 3) {
                String cifrado = Blowfish.encrypt(frase);
                System.out.println("Blowfish cifrado = " + cifrado);
                System.out.println("Blowfish descrifrado = " + Blowfish.decrypt(cifrado));

                FileWriter fichero = null;
                PrintWriter pw = null;

                try {
                    fichero = new FileWriter("fichero.cifrado");
                    pw = new PrintWriter(fichero);
                    pw.print(cifrado);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (null != fichero)
                            fichero.close();
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            }
        }while (metodo != 4);
        }
    }
