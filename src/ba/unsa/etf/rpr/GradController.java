package ba.unsa.etf.rpr;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;

public class GradController {
    public TextField fieldNaziv;
    public TextField fieldBrojStanovnika;
    public ChoiceBox<Drzava> choiceDrzava;
    public ObservableList<Drzava> listDrzave;
    public ChoiceBox<String> choiceTipGrada;
    public ObservableList<String> tip;
    private Grad grad;

    public GradController(Grad grad, ArrayList<Drzava> drzave) {
        this.grad = grad;
        listDrzave = FXCollections.observableArrayList(drzave);
    }

    @FXML
    public void initialize() {
        ArrayList<String> tipovi=new ArrayList<>();
        tipovi.add("Razvijen");
        tipovi.add("Srednje razvijen");
        tipovi.add("Nerazvijen");
        tip =FXCollections.observableArrayList(tipovi);
        choiceTipGrada.setItems(tip);
        choiceDrzava.setItems(listDrzave);

        if (grad != null) {
            fieldNaziv.setText(grad.getNaziv());
            fieldBrojStanovnika.setText(Integer.toString(grad.getBrojStanovnika()));
            // choiceDrzava.getSelectionModel().select(grad.getDrzava());
            // ovo ne radi jer grad.getDrzava() nije identički jednak objekat kao član listDrzave
            for (Drzava drzava : listDrzave)
                if (drzava.getId() == grad.getDrzava().getId())
                    choiceDrzava.getSelectionModel().select(drzava);
             if(grad instanceof RazvijeniGrad) choiceTipGrada.getSelectionModel().select("Razvijen");
            else if(grad instanceof SrednjeRazvijeniGrad) choiceTipGrada.getSelectionModel().select("Srednje razvijen");
            else if(grad instanceof NerazvijeniGrad) choiceTipGrada.getSelectionModel().select("Nerazvijen");
        } else {
            choiceDrzava.getSelectionModel().selectFirst();
            choiceTipGrada.setValue("Razvijen");
        }
    }

    public Grad getGrad() {
        return grad;
    }

    public void clickCancel(ActionEvent actionEvent) {
        grad = null;
        Stage stage = (Stage) fieldNaziv.getScene().getWindow();
        stage.close();
    }

    public void clickOk(ActionEvent actionEvent) {
        boolean sveOk = true;

        if (fieldNaziv.getText().trim().isEmpty()) {
            fieldNaziv.getStyleClass().removeAll("poljeIspravno");
            fieldNaziv.getStyleClass().add("poljeNijeIspravno");
            sveOk = false;
        } else {
            fieldNaziv.getStyleClass().removeAll("poljeNijeIspravno");
            fieldNaziv.getStyleClass().add("poljeIspravno");
        }


        int brojStanovnika = 0;
        try {
            brojStanovnika = Integer.parseInt(fieldBrojStanovnika.getText());
        } catch (NumberFormatException e) {
            // ...
        }
        if (brojStanovnika <= 0) {
            fieldBrojStanovnika.getStyleClass().removeAll("poljeIspravno");
            fieldBrojStanovnika.getStyleClass().add("poljeNijeIspravno");
            sveOk = false;
        } else {
            fieldBrojStanovnika.getStyleClass().removeAll("poljeNijeIspravno");
            fieldBrojStanovnika.getStyleClass().add("poljeIspravno");
        }

        if (!sveOk) return;

        int stariId=0;
        if (grad != null) stariId=grad.getId();


            if(choiceTipGrada.getValue().equals("Razvijen")){
                grad = new RazvijeniGrad();
            }else if(choiceTipGrada.getValue().equals("Srednje razvijen")){
                grad = new SrednjeRazvijeniGrad();
            }else if(choiceTipGrada.getValue().equals("Nerazvijen")){
                grad = new NerazvijeniGrad();
            }

        grad.setId(stariId);
        grad.setNaziv(fieldNaziv.getText());
        grad.setBrojStanovnika(Integer.parseInt(fieldBrojStanovnika.getText()));
        grad.setDrzava(choiceDrzava.getValue());
        Stage stage = (Stage) fieldNaziv.getScene().getWindow();
        stage.close();
    }
}
