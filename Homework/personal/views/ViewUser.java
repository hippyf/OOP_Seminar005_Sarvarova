package Homework.personal.views;

import Homework.personal.controllers.UserController;
import Homework.personal.exception.CommandException;
import Homework.personal.model.User;

import java.util.Scanner;

public class ViewUser {

    private UserController userController;

    public ViewUser(UserController userController) {
        this.userController = userController;
    }

    public void run(){
        Commands com = Commands.NONE();

        while (true) {
            try {
                String command = prompt("Введите команду: ");
                com = Commands.valueOf(command.toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Unknown command");
                continue;
            }
            if (com == Commands.EXIT) return;
            switch (com) {
                case CREATE:
                    try {
                        String firstName = prompt("Имя: ");
                        String lastName = prompt("Фамилия: ");
                        String phone = prompt("Номер телефона: ");
                        userController.saveUser(new User(firstName, lastName, phone));
                    } catch (IllegalStateException e) {
                        System.out.println(e.getMessage());
                        continue;
                    }
                    break;
                case READ:
                    String id = prompt("Идентификатор пользователя: ");
                    try {
                        User user = userController.readUser(id);
                        System.out.println(user);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case LIST:
                    userController.readUsers().forEach(System.out::println);
                    break;
                case UPDATE:
                    String firstName = prompt("Имя: ");
                    String lastName = prompt("Фамилия: ");
                    String phone = prompt("Номер телефона: ");
                    String userID = prompt("Идентификатор пользователя: ");
                    userController.editUser(new User(userID, firstName, lastName, phone));
                case DELETE:
                    String deleteUserID = prompt("Идентификатор пользователя: ");
                    userController.deleteUser(deleteUserID);
            }
        }
    }

    private String prompt(String message) {
        Scanner in = new Scanner(System.in);
        System.out.print(message);
        return in.nextLine();
    }
}
