package co.edu.poli.Examen2.controlador;

import co.edu.poli.Examen2.modelo.*;
import co.edu.poli.Examen2.servicios.ImplementacionOperacionCRUD;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class PrimaryController {

	@FXML
	private TextField txtCodigoExamen;
	@FXML
	private TextField txtNombrePaciente;
	@FXML
	private TextField txtCosto;
	@FXML
	private ComboBox<String> cmbTipoSangre;
	@FXML
	private RadioButton rbPositivo;
	@FXML
	private RadioButton rbNegativo;
	@FXML
	private ToggleGroup tgFactorRH;
	@FXML
	private TextArea txtAreaResultados;

	@FXML
	public void initialize() {
		
	}

	@FXML
	private void handleGuardar() {
	}

	@FXML
	private void handleLimpiar() {
	}

	@FXML
	private void handleSerializar() {
	}

	@FXML
	private void handleDeserializar() {
	}

	@FXML
	private void handleListar() {
	}
}
