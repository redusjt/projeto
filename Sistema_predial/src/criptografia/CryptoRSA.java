package criptografia;

import java.io.*;
import javax.crypto.*;
import java.security.*;
import java.security.spec.*;
import java.security.cert.*;

/*
   Classe exclusivamente com a função de cifrar e decifrar um texto passado como vetor de bytes e 
   retornar tanto o texto cifrado como o decifrado também como vetores de byte.
   Esta classe também gera um par de chaves pública e privada. 
*/

public class CryptoRSA
{ 
   private byte[] textoCifrado;
   private byte[] textoDecifrado;
   public CryptoRSA()
   { 
      textoCifrado = null;
      textoDecifrado = null;
   }
   
   public byte[] getTextoCifrado() throws Exception
   { 
      return textoCifrado;
   }
   public byte[] getTextoDecifrado() throws Exception
   { 
      return textoDecifrado;
   }
   
   //Gera as chaves pública e privada e as grava em um formato serializado
   public void geraParDeChaves(File fChavePublica, File fChavePrivada)
   throws IOException, NoSuchAlgorithmException,
   InvalidAlgorithmParameterException,
   CertificateException, KeyStoreException
   { 
      final int RSAKEYSIZE = 1024;
      KeyPairGenerator kpg = KeyPairGenerator.getInstance ("RSA");
      kpg.initialize (new RSAKeyGenParameterSpec(RSAKEYSIZE, RSAKeyGenParameterSpec.F4));
      KeyPair kpr = kpg.generateKeyPair();
      PrivateKey oPriv = kpr.getPrivate();
      PublicKey oPub = kpr.getPublic();
   //-- Gravando a chave publica em formato serializado
      ObjectOutputStream oos = new ObjectOutputStream (new FileOutputStream (fChavePublica));
      oos.writeObject (oPub);
      oos.close();
   //-- Gravando a chave privada em formato serializado
      oos = new ObjectOutputStream (new FileOutputStream (fChavePrivada));
      oos.writeObject (oPriv);
      oos.close();
   }
   
   
   public void geraCifra(byte[] texto, File fChavePublica)
   throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
   IllegalBlockSizeException, BadPaddingException,
   InvalidAlgorithmParameterException,
   IOException, ClassNotFoundException
   { 
      ObjectInputStream ois = new ObjectInputStream (new FileInputStream (fChavePublica));
      PublicKey iPub = (PublicKey) ois.readObject();
      ois.close();
      Cipher rsacf = Cipher.getInstance ("RSA");
      rsacf.init (Cipher.ENCRYPT_MODE, iPub);
      textoCifrado = rsacf.doFinal (texto);
   }
   
   public void geraDecifra(byte[] texto, File fChavePrivada)
   throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
   IllegalBlockSizeException, BadPaddingException,
   InvalidAlgorithmParameterException,
   IOException, ClassNotFoundException
   { ObjectInputStream ois = new ObjectInputStream (new FileInputStream (fChavePrivada));
      PrivateKey iPrv = (PrivateKey) ois.readObject();
      ois.close();
      Cipher rsacf = Cipher.getInstance ("RSA");
      rsacf.init (Cipher.DECRYPT_MODE, iPrv);
      textoDecifrado = rsacf.doFinal (texto);
   }
}