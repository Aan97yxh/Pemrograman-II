package Controller;

import Model.Mahasiswa;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class Controller {

    @FXML
    private TableView<Mahasiswa> tabelMahasiswa;

    @FXML
    private TableColumn<Mahasiswa, String> colNIM;

    @FXML
    private TableColumn<Mahasiswa, String> colNama;

    private ObservableList<Mahasiswa> data =
            FXCollections.observableArrayList(
                new Mahasiswa(1, "Raka", "241081711"),
                new Mahasiswa(2, "Dina", "241081711"),
                new Mahasiswa(3, "Sore", "241081711"),
                new Mahasiswa(4, "Laila", "241081711"),
                new Mahasiswa(5, "Fajar", "241081721"),
                new Mahasiswa(6, "Bunga", "241081721"),
                new Mahasiswa(7, "Fauzi", "241081721"),
                new Mahasiswa(8, "Silvi", "241081731"),
                new Mahasiswa(9, "Farid", "241081731"),
                new Mahasiswa(10, "Lia", "241081731")
            );

    @FXML
    public void initialize() {
        colNIM.setCellValueFactory(new PropertyValueFactory<>("nim"));
        colNama.setCellValueFactory(new PropertyValueFactory<>("nama"));

        tabelMahasiswa.setItems(data);
    }
}