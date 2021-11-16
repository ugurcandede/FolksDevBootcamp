package dede.ugurcan.bootcampblog;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BootcampBlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootcampBlogApplication.class, args);
    }

    @Bean
    public OpenAPI customAPI(
            @Value("FolksDev Blog API") String title,
            @Value("1.0") String version,
            @Value("FolksDev SpringBoot Bootcamp süresince geliştirilen blog projesi") String description
    ) {
        return new OpenAPI().info(new Info()
                .title(title)
                .version(version)
                .description(description)
                .contact(new Contact()
                        .name("Ugurcan Dede")
                        .url("https://github.com/ugurcandede/FolksDevBootcamp")
                        .email("ugurcan.dede@outlook.com.tr"))
                .license(new License().name("GNU GPLv3").url("https://github.com/ugurcandede/FolksDevBootcamp/blob/master/LICENCE")));
    }
}
