# Application logging :

    https://docs.microsoft.com/en-us/azure/architecture/microservices/logging-monitoring

### 9 Logging Sins in Your Java Applications
    https://stackify.com/9-logging-sins-java/
 
 
 
### Anonymizing logs for GDPR ( General Data Protection Regulation) using Logback pattern layout
    https://www.schibsted.pl/blog/logback-pattern-gdpr/

  Anonymize/mask sensitive data
  If you find yourself in a situation in which you already log something that may contain private data, you should consider implementing anonymization mechanisms. Solutions may differ depending on your needs – encryption, masking or complete removal are some of the possible options.


    Insights on Software Development, Cloud, DevOps, Consulting, and More…

    Containerized Microservice Log Aggregation and Visualization using ELK Stack and Logspout

    https://programmaticponderings.com/tag/log4j/
 
 
## AES Encryption 

    AES (Advanced Encryption Standard) is a strong symmetric encryption algorithm. AES supports key lengths of 128, 192 and 256 bit.In this article, we will learn AES 256 Encryption and Decryption.
    The Advanced Encryption Standard (AES) is a standard for encryption and decryption that has been approved by the U.S. NIST (National Institute of Standards and Technology) in 2001. It is more secure than the previous encryption standard DES (Data Encryption Standard) and 3DES (Triple-DES). You should be using AES for all symmetric encryption needs in preference to DES and 3DES (which are now deprecated).
    AES uses the same secret key is used for the both encryption and decryption. Unlike AES 128 bit encryption and decryption, if we need a stronger AES 256 bit key, we need to have Java cryptography extension (JCE) unlimited strength jurisdiction policy files.
    Symmetric Encryption refers to algorithms that use the same key for encryption as well as decryption. As such, the key should be kept secret and must be exchanged between the encryptor and decryptor using a secure channel. 

    The core java libraries provide good support for all aspects of encryption and decryption using AES so no external libraries are required.

    https://javainterviewpoint.com/aes-256-encryption-and-decryption/
    https://www.novixys.com/blog/java-aes-example/
    

        When using AES with a mode known as CBC (Cipher Block Chaining), you need to generate an initialization vector (IV). In the CBC mode, each plaintext block is XORed with the previous ciphertext block before being encrypted. So you need an initialization vector for the first block.
        
        To produce different ciphertext with each run of the encryption (even with the same plaintext and key), we use a random initialization vector.
        To generate the IV, we use the SecureRandom class. The block size required depends on the AES encryption block size. For the default block size of 128 bits, we need an initialization vector of 16 bytes.
        
        From the initialization vector, we create an IvParameterSpec which is required when creating the Cipher.
        
        byte[] iv = new byte[128/8];
        srandom.nextBytes(iv);
        IvParameterSpec ivspec = new IvParameterSpec(iv);



## AES256Demo.java
    
        package com.example.demo;

        import javax.crypto.Cipher;
        import javax.crypto.KeyGenerator;
        import javax.crypto.SecretKey;
        import javax.crypto.spec.IvParameterSpec;
        import javax.crypto.spec.SecretKeySpec;
        import java.security.SecureRandom;
        import java.util.Base64;

        public class AES256Demo {
            static String plainText = "This is a plain text which need to be encrypted by Java AES 256 Algorithm in CBC Mode";

            public static void main(String[] args) throws Exception {
                KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
                keyGenerator.init(256);

                // Generate Key. // generate random enc key.
                //SecretKey key = keyGenerator.generateKey();
                byte[] key = "aesEncryptionKey".getBytes("UTF-8");

                // Generating IV.
                byte[] IV = new byte[16];
                SecureRandom random = new SecureRandom();
                random.nextBytes(IV);

                System.out.println("Original Text  : " + plainText);

                byte[] cipherText = encrypt(plainText.getBytes(), key, IV);
                System.out.println("Encrypted Text : " + Base64.getEncoder().encodeToString(cipherText));

                String decryptedText = decrypt(cipherText, key, IV);
                System.out.println("DeCrypted Text : " + decryptedText);

            }

            //public static byte[] encrypt(byte[] plaintext, SecretKey key, byte[] IV) throws Exception {
            public static byte[] encrypt(byte[] plaintext, byte[] key, byte[] IV) throws Exception {
                //Get Cipher Instance
                Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

                //Create SecretKeySpec
                SecretKeySpec keySpec = new SecretKeySpec(key, "AES");

                //Create IvParameterSpec
                IvParameterSpec ivSpec = new IvParameterSpec(IV);

                //Initialize Cipher for ENCRYPT_MODE
                cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);

                //Perform Encryption
                byte[] cipherText = cipher.doFinal(plaintext);

                return cipherText;
            }

            //public static String decrypt(byte[] cipherText, SecretKey key, byte[] IV) throws Exception {
            public static String decrypt(byte[] cipherText, byte[] key, byte[] IV) throws Exception {

                //Get Cipher Instance
                Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

                //Create SecretKeySpec
                //SecretKeySpec keySpec = new SecretKeySpec(key.getEncoded(), "AES");
                SecretKeySpec keySpec = new SecretKeySpec(key, "AES");


                //Create IvParameterSpec
                IvParameterSpec ivSpec = new IvParameterSpec(IV);

                //Initialize Cipher for DECRYPT_MODE
                cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);

                //Perform Decryption
                byte[] decryptedText = cipher.doFinal(cipherText);

                return new String(decryptedText);
            }
        }



