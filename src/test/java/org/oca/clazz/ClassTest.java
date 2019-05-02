package org.oca.clazz;

import org.junit.jupiter.api.Test;
import org.oca.clazz.subpackage.A;

class ClassTest {

    @Test
    void revealTheSecret() {
        SecretHolder secretHolder = new SecretHolder("How kill the Kennedy?");
        SecretHolder filth = new SecretHolder("filth");
        filth.revealTheSecret(secretHolder);
        SecretHolder.show(filth);
    }

    @Test
    void revealProtectedSecret() {
        SubSecretHolder subSecretHolder = new SubSecretHolder("Money in a jar", "Money in a Bank");
        subSecretHolder.revealProectedSecret(subSecretHolder);
    }

    @Test
    void name() {

        A a = new B();
        B b = new B();
        b.process(a);
        System.out.println(a.getI());

    }
}

class SecretHolder {

    private final String secret;

    protected String protectedSecret;

    public SecretHolder(String secret) {
        this(secret, null);
    }

    public SecretHolder(String secret, String protectedSecret) {
        this.secret = secret;
        this.protectedSecret = protectedSecret;
    }

    public void revealTheSecret(SecretHolder holder) {
        System.out.println(holder.secret);
    }

    static void show(SecretHolder secretHolder) {
        System.out.println(secretHolder.secret);
    }
}

class SubSecretHolder extends SecretHolder {

    public SubSecretHolder(String secret, String protectedSecret) {
        super(secret, protectedSecret);
    }

    public void revealProectedSecret(SubSecretHolder subSecretHolder) {
        System.out.println(subSecretHolder.protectedSecret);
    }
}

class B extends A {
    public void process(A a) {

        if (a instanceof B) {
            B b = (B)a;
            b.i = b.i * 2;
        }

    }
}