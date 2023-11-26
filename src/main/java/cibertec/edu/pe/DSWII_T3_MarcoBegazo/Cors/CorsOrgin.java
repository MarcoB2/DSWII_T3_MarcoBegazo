package cibertec.edu.pe.DSWII_T3_MarcoBegazo.Cors;

import org.springframework.web.bind.annotation.CrossOrigin;
        import org.springframework.web.bind.annotation.RestController;
        import org.springframework.boot.SpringApplication;
        import org.springframework.boot.autoconfigure.SpringBootApplication;

@CrossOrigin(origins = "https://www.cibertec.edu.pe")
@SpringBootApplication
public class CorsOrgin {

    public static void main(String[] args) {
        SpringApplication.run(CorsOrgin.class, args);
    }
}
