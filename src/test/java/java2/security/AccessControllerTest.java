package java2.security;

import org.junit.jupiter.api.Test;

import javax.sound.sampled.AudioPermission;
import java.security.AccessController;

class AccessControllerTest {

    @Test
    void checkPermission() {
        AccessController.checkPermission(new AudioPermission("listen rap music"));
    }

    @Test
    void name() {
    }
}
