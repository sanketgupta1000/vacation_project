package com.project.readers.readers_community.configs;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

// to get keys from properties file
@ConfigurationProperties(prefix = "rsa")
public record RsaKeyProperties(
        RSAPublicKey publicKey,
        RSAPrivateKey privateKey
){
}
