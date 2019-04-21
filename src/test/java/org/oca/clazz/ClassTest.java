package org.oca.clazz;

import org.junit.jupiter.api.Test;

class ClassTest {



    @Test
    void revealTheSecret() {
        SecretHolder secretHolder = new SecretHolder("How kill the Kennedy?");
        SecretHolder filth = new SecretHolder("filth");
        filth.revealTheSecret(secretHolder);
        SecretHolder.show(filth);
    }
}

class SecretHolder {

    private final String secret;

    public SecretHolder(String secret) {
        this.secret = secret;
    }

    public void revealTheSecret(SecretHolder holder) {
        System.out.println(holder.secret);
    }

    static void show(SecretHolder secretHolder) {
        System.out.println(secretHolder.secret);
    }
}