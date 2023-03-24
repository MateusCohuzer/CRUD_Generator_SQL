package tools;

import java.security.*;

public class Criptografar {

    private String bytesToHex(byte[] b) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < b.length; ++i) {
            sb.append((Integer.toHexString((b[i] & 0xFF) | 0x100)).substring(1, 3));
        }
        return sb.toString();
    }

    public String toPassword(String data) throws NoSuchAlgorithmException {
        byte[] mybytes = data.getBytes();
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] md5digest = md5.digest(mybytes);
        return bytesToHex(md5digest);
    }

    public boolean checkSenha(String a, String b) {
        try {
            a = toPassword(a);
            b = toPassword(b);
            return a.equals(b);
        } catch (Exception ex) {
            return false;
        }
    }

    public static void main(String[] args) {
        Criptografar c = new Criptografar();
        try {
            String pw1 = c.toPassword("123"); // a senha cadastrada           
            String pw2 = c.toPassword("123"); // usuário digitou errado            

            System.out.println("senha armazenada " + c.toPassword(pw1));

            if (c.checkSenha(pw1, pw2)) {
                System.out.println("senhas corretas");
            } else {
                System.out.println("nao são iguais");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
