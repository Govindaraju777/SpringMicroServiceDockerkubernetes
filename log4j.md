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
