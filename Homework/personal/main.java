package Homework.personal;
import Homework.personal.controllers.UserController;
import Homework.personal.model.FileOperation;
import Homework.personal.model.FileOperationImpl;
import Homework.personal.model.Repository;
import Homework.personal.model.RepositoryFile;
import Homework.personal.views.Commands;
import Homework.personal.views.ViewUser;

public class main {
    FileOperation fileOperation = new FileOperationImpl("users.txt");
    Repository repository = new RepositoryFile(fileOperation);
    UserController controller = new UserController(repository);
    ViewUser view = new ViewUser(controller);
    view.run();
}
