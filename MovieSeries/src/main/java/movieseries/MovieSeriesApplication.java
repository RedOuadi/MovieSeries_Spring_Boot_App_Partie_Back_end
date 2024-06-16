package movieseries;





import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "model")
public class MovieSeriesApplication {

    public static void main(String[] args) {
        SpringApplication.run(MovieSeriesApplication.class, args);
    }

}
