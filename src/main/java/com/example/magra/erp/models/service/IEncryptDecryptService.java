package com.example.magra.erp.models.service;

public interface IEncryptDecryptService {

    public String encryptKey(String plainKey);
     
    public String decryptKey(String encryptedKey);
}
