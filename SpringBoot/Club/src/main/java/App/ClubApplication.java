package App;

import App.controller.ControllerInterface;
import App.controller.LoginController;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Setter
@SpringBootApplication
public class ClubApplication implements CommandLineRunner {

    @Autowired
    LoginController controller;

    public static void main(String[] args) {
        SpringApplication.run(ClubApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        try {
            controller.session();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            for (StackTraceElement el : e.getStackTrace()){
            
                System.out.println(el.toString());}
        }
    }

}
