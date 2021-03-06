package hu.petrik.etlap;



import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.SQLException;


public class HozzaadController extends Controller {
    @FXML
    private TextField inputNev;
    @FXML
    private TextField inputKategoria;
    @FXML
    private Spinner<Integer> inputAr;

    private EtlapDb db;


    public void initialize() throws SQLException {
        db = new EtlapDb();
    }


    @FXML
    public void onHozzaadButtonClick(ActionEvent actionEvent) {
        String nev = inputNev.getText().trim();
        String kategoria = inputKategoria.getText().trim();
        int ar = 0;

        if (nev.isEmpty()) {
            alert("Név megadása kötelező");
            return;
        }
        if (kategoria.isEmpty()) {
            alert("Kategória megadása kötelező");
            return;
        }
        try {
            ar = inputAr.getValue();
        } catch (NullPointerException ex) {
            alert("Az ár megadása kötelező");
            return;
        }
        if (ar == 0 || ar < 1) {
            alert("Az ár nem lehet nulla vagy annál kisebb");
            return;
        }


        try {
            EtlapDb db = new EtlapDb();
            int siker = db.etelHozzaadasa(nev, kategoria, ar);
            if (siker == 1) {
                alert("Étel hozzáadása sikeres");
            } else {
                alert("Étel hozzáadása sikeretelen");
            }
        } catch (Exception e) {
            hibaKiir(e);
        }

    }
}