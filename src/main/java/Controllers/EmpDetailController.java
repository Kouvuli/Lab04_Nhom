package Controllers;

import Entities.Employee;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class EmpDetailController implements Initializable {
    @FXML
    private Text empEmail;

    @FXML
    private Text empId;

    @FXML
    private Text empName;

    @FXML
    private Text empSal;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setValue(Employee employee){
        empId.setText(employee.getId());
        empName.setText(employee.getName());
        empEmail.setText(employee.getEmail());
        empSal.setText(employee.getSalary());
    }
}
