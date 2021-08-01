package Certification.app.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("Certification.app") // コンポーネントスキャンするパッケージ指定
@EntityScan("Certification.app")
@EnableJpaRepositories("Certification.app")
public class CertificationProApplication {

	public static void main(String[] args) {
		SpringApplication.run(CertificationProApplication.class, args);
	}

}