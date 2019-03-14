package java2.nio.file.attribute;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.security.auth.Subject;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.attribute.UserPrincipal;
import java.nio.file.attribute.UserPrincipalLookupService;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class UserPrincipalLookupServiceTest {

    private UserPrincipalLookupService userPrincipalLookupService;

    @BeforeEach
    void setUp() {
        userPrincipalLookupService = FileSystems.getDefault().getUserPrincipalLookupService();
    }

    @Test
    void lookup_principal_by_name() throws IOException {
        UserPrincipal userPrincipal = userPrincipalLookupService.lookupPrincipalByName("maksim");
        assertNotNull(userPrincipal);
        System.out.println(userPrincipal.getName());

        Subject subject = new Subject();

        System.out.println(userPrincipal.implies(subject));
    }
}
