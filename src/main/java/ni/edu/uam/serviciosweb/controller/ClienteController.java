
package ni.edu.uam.serviciosweb.controller;

import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import ni.edu.uam.serviciosweb.model.Cliente;
import ni.edu.uam.serviciosweb.service.ClienteService;

import java.util.List;

public class ClienteController {
    @FXML
    private TableView<Cliente> tblClientes;
    @FXML
    private TableColumn<Cliente, Integer> colId;
    @FXML
    private TableColumn<Cliente, String> colNombre;
    @FXML
    private TableColumn<Cliente, String> colApellido;
    @FXML
    private TableColumn<Cliente, String> colCorreo;
    @FXML
    private TableColumn<Cliente, String> colTelefono;
    @FXML
    private TableColumn<Cliente, String> colNacimiento;
    @FXML
    private TableColumn<Cliente, String> colPais;
    @FXML
    private TableColumn<Cliente, String> colCiudad;
    @FXML
    private TableColumn<Cliente, String> colDireccion;
    @FXML
    private TableColumn<Cliente, Double> colIngreso;
    @FXML
    private TableColumn<Cliente, Integer> colPuntaje;
    @FXML
    private TableColumn<Cliente, Boolean> colDeudaPendiente;
    @FXML
    private TableColumn<Cliente, Double> colMontoPendiente;
    @FXML
    private TableColumn<Cliente, String> colEstado;

    @FXML
    private Button btnConsultarClientes;
    @FXML
    private Label lblEstado;
    private final ClienteService service = new ClienteService();

    @FXML
    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colApellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        colCorreo.setCellValueFactory(new PropertyValueFactory<>("email"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        colNacimiento.setCellValueFactory(new PropertyValueFactory<>("fechaNacimiento"));
        colPais.setCellValueFactory(new PropertyValueFactory<>("pais"));
        colCiudad.setCellValueFactory(new PropertyValueFactory<>("ciudad"));
        colDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        colIngreso.setCellValueFactory(new PropertyValueFactory<>("ingresoAnual"));
        colPuntaje.setCellValueFactory(new PropertyValueFactory<>("puntajeCrediticio"));
        colDeudaPendiente.setCellValueFactory(new PropertyValueFactory<>("tieneDeudaPendiente"));
        colMontoPendiente.setCellValueFactory(new PropertyValueFactory<>("montoDeudaPendiente"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
    }

    @FXML
    private void consultarClientes() {
        Task<List<Cliente>> task = new Task<List<Cliente>>() {
            @Override
            protected List<Cliente> call() throws Exception {
                return service.obtenerClientes();
            }
        };

        btnConsultarClientes.disableProperty().bind(task.runningProperty());
        lblEstado.setText("Consultando API ..........");
        task.setOnSucceeded(e -> {
            List<Cliente> clientes = task.getValue();
            tblClientes.setItems(FXCollections.observableArrayList(clientes));
            lblEstado.setText("Clientes encontrados: " + clientes.size());
        });

        task.setOnFailed(e -> {
            Throwable error = task.getException();
            lblEstado.setText(error.getMessage());
        });
        Thread hilo = new Thread(task);
        hilo.setDaemon(true);
        hilo.start();
    }
}
