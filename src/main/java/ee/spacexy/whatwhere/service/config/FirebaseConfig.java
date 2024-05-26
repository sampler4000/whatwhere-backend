package ee.spacexy.whatwhere.service.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

@Configuration
@Slf4j
public class FirebaseConfig {

    @Autowired
    private FirebaseConfigProperties firebaseProperties;

    @PostConstruct
    public void init() throws IOException {
        String firebaseConfig = firebaseProperties.getConfig();
        if (firebaseConfig != null) {
            try (FileOutputStream fos = new FileOutputStream("firebase-config.json")) {
                fos.write(Base64.getDecoder().decode(firebaseConfig));
            }
        }
    }
    @Primary
    @Bean
    public FirebaseApp initializeFirebase() throws IOException {
        FileInputStream serviceAccount =
            new FileInputStream("firebase-config.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
            .setCredentials(GoogleCredentials.fromStream(serviceAccount))
            .build();

        return FirebaseApp.initializeApp(options);
    }
}
