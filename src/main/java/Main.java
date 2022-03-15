import model.Model;

import javax.swing.*;

import model.ModelImpl;
import controller.Controller;

public class Main {
    public static void main(String[] args) {
        startGame();
    }

    public static void startGame(){
        Model model = new ModelImpl();
        Controller controller = new Controller(model);
        JFrame game = new JFrame();
        game.setTitle("2048");
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.setSize(450, 500);
        game.setResizable(false);
        game.add(controller.getView());
        game.setLocationRelativeTo(null);
        game.setVisible(true);
    }
}
