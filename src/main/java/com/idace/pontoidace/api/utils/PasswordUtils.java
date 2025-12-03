package com.idace.pontoidace.api.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtils {

    private static final Logger log = LoggerFactory.getLogger(PasswordUtils.class);

    public PasswordUtils() {
    }

    /**
     * Gera um hash utilizando o BCrypt.
     *
     * @param senha
     * @return String
     */
    public static String gerarBCrypt(String senha) {
        if (senha == null) {
            return senha;
        }

        log.info("Gerando hash com o BCrypt.");
        BCryptPasswordEncoder bCryptEncoder = new BCryptPasswordEncoder();
        return bCryptEncoder.encode(senha);
    }

    public static boolean senhaValida(String senha, String senhaEncoded) {
        BCryptPasswordEncoder bCryptEncoder = new BCryptPasswordEncoder();
        if (senha == null || senha.isEmpty() || senhaEncoded == null || senhaEncoded.isEmpty()) {
            return false;
        }
        return bCryptEncoder.matches(senha, senhaEncoded);
    }
}
