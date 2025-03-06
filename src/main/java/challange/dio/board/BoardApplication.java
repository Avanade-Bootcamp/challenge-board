package challange.dio.board;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import challange.dio.board.ui.MainMenu;

@SpringBootApplication
public class BoardApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(BoardApplication.class, args);
        MainMenu mainMenu = context.getBean(MainMenu.class);
        mainMenu.execute();
    }
}