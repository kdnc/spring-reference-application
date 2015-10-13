import org.junit.Test;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

public class PasswordHashTest {

	@Test
	public void testMD5Hash() {
		
		String password = "123";
		Md5PasswordEncoder passwordEncoder = new Md5PasswordEncoder();
		String hashedPassword = passwordEncoder.encodePassword(password, null);
		System.out.println(hashedPassword);
	}
}
