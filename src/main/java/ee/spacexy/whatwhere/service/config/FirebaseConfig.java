package ee.spacexy.whatwhere.service.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;

@Configuration
@Slf4j
public class FirebaseConfig {
    @Primary
    @Bean
    public void firebaseInit() {
        InputStream inputStream = null;
        try {
            inputStream = new ClassPathResource("firebase_config.json").getInputStream();
        } catch (IOException e3) {
            log.error(e3.getMessage(), e3);
        }
        try {

            FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(inputStream))
                .build();

            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
            }
            log.info("Firebase Initialize");

        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }
}
