package Controllers;

import DAO.ClassDAO;
import DAO.EmployeeDAO;
import Entities.Class;
import Entities.Employee;
import Entities.Student;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {


    @FXML
    private ListView<String> classList;

    @FXML
    private ListView<String> empList;

    @FXML
    private ScrollPane detailContent;

    private String username;
    private Employee currEmployee;
    public static String pubKey;
    public static String privateKey;

    public MainViewController() {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        EmployeeDAO dao=new EmployeeDAO();

        Employee employee =dao.getEmployeeById(currEmployee.getId());
        List<String> a=new ArrayList<>();
        List<String> b=new ArrayList<>();
        for (Class aClass : employee.getClassSet()) {
            a.add(aClass.getId());
        }
        for (Employee e:dao.getAll()){
            b.add(e.getId());
        }
        classList.setItems(FXCollections.observableArrayList(a));
        empList.setItems(FXCollections.observableArrayList(b));
        empList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String empId = (String) empList.getSelectionModel().getSelectedItem();
                EmployeeDAO employeeDAO=new EmployeeDAO();
                Employee currEmp=employeeDAO.getEmployeeById(empId);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Layouts/emp-detail.fxml"));
                Parent root = null;
                try {
                    root = (Parent) loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                EmpDetailController controller=loader.getController();
                controller.setValue(currEmp);
                detailContent.setContent(root);

            }
        });
        classList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String classId = (String) classList.getSelectionModel().getSelectedItem();
                ClassDAO classDAO=new ClassDAO();
                Class currClass=classDAO.getClassById(classId);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Layouts/class-detail.fxml"));
                Parent root = null;
                try {
                    root = (Parent) loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ClassDetailController controller=loader.getController();
                controller.setValue(currClass);
                detailContent.setContent(root);

            }
        });
    }

    public void setValue(String username){
        this.username=username;
        EmployeeDAO dao=new EmployeeDAO();
        currEmployee = dao.getEmployeeByUserName(username);
        pubKey=currEmployee.getPubKey();
        privateKey="0x".concat(currEmployee.getPassword());
    }
}
