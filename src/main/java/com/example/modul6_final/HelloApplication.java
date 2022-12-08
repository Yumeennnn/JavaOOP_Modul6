package com.example.modul6_final;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;

public class HelloApplication extends Application {

    private final ObservableList<Mahasiswa> data = FXCollections.observableArrayList();
    private final TableView table = new TableView();
    final HBox hBox = new HBox();

    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(new Group());
        stage.setTitle("Data");
        stage.setWidth(1070);
        stage.setHeight(600);

        final Label label = new Label("JavaFx");
        label.setFont(new Font("Arial", 30));

        table.setEditable(true);


        TableColumn<Mahasiswa, String> dosenCol = new TableColumn("Dosen");
        TableColumn<Mahasiswa, String> matkulCol = new TableColumn("Mata Kuliah");
        TableColumn<Mahasiswa, String> ruangCol = new TableColumn("Ruang");
        TableColumn<Mahasiswa, String> waktuCol = new TableColumn("Waktu");
        TableColumn<Mahasiswa, String> gkbCol = new TableColumn("GKB");

        dosenCol.setMinWidth(200);
        dosenCol.setCellValueFactory(new PropertyValueFactory<Mahasiswa,String>("Dosen"));
        dosenCol.setOnEditCommit(
                (CellEditEvent<Mahasiswa, String> t) -> {
                    ((Mahasiswa) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setDosen(t.getNewValue());
                });
        dosenCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Mahasiswa, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Mahasiswa, String>p) {
                return p.getValue().dosenProperty();
            }
        });


        matkulCol.setMinWidth(200);
        matkulCol.setCellValueFactory(new PropertyValueFactory<>("Mata Kuliah"));
        matkulCol.setOnEditCommit(
                (CellEditEvent<Mahasiswa, String> t) -> {
                    ((Mahasiswa) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setDosen(t.getNewValue());
                });
        matkulCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Mahasiswa, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Mahasiswa, String>p) {
                return p.getValue().matkulProperty();
            }
        });

        ruangCol.setMinWidth(200);
        ruangCol.setCellValueFactory(new PropertyValueFactory<>("Ruangan"));
        ruangCol.setOnEditCommit(
                (CellEditEvent<Mahasiswa, String> t) -> {
                    ((Mahasiswa) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setDosen(t.getNewValue());
                });
        ruangCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Mahasiswa, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Mahasiswa, String>p) {
                return p.getValue().ruangProperty();
            }
        });

        waktuCol.setMinWidth(200);
        waktuCol.setCellValueFactory(new PropertyValueFactory<>("Waktu"));
        waktuCol.setOnEditCommit(
                (CellEditEvent<Mahasiswa, String> t) -> {
                    ((Mahasiswa) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setDosen(t.getNewValue());
                });

        gkbCol.setMinWidth(200);
        gkbCol.setCellValueFactory(new PropertyValueFactory<Mahasiswa, String>("GKB"));

        VBox vBox = new VBox();
        final TextField adddosen = new TextField();
        adddosen.setPromptText("Nama Dosen");

        final TextField addmatkul = new TextField();
        addmatkul.setPromptText("Mata Kuliah");

        final TextField addruang = new TextField();
        addruang.setPromptText("Ruang");

        final TextField addwaktu = new TextField();
        addwaktu.setPromptText("Waktu");

        final TextField addgkb = new TextField();
        addgkb.setPromptText("GKB");

        addgkb.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(!newValue.matches("\\d*")){
                    addgkb.setText(newValue.replaceAll("[^\\d]",""));
                }
            }
        });
        final Button addButton = new Button("Add");
        gkbCol.setOnEditCommit(
                (CellEditEvent<Mahasiswa, String> t) -> {
                    ((Mahasiswa) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setGkb(t.getNewValue());


                    adddosen.setPromptText("Nama Dosen");
                    adddosen.setMaxWidth(dosenCol.getPrefWidth());

                    addmatkul.setMaxWidth(matkulCol.getPrefWidth());
                    addmatkul.setPromptText("Mata Kuliah");

                    addruang.setMaxWidth(ruangCol.getPrefWidth());
                    addruang.setPromptText("Ruang");

                    addwaktu.setMaxWidth(waktuCol.getPrefWidth());
                    addwaktu.setPromptText("Waktu");

                    addgkb.setMaxWidth(gkbCol.getPrefWidth());
                    addgkb.setPromptText("Gkb");
                });

        gkbCol.setMaxWidth(200);
        gkbCol.setCellValueFactory(
                new PropertyValueFactory<>("Gkb"));
//        gkbCol.setCellFactory(cellFactory);
        gkbCol.setOnEditCommit(
                (CellEditEvent<Mahasiswa, String> t) -> {
                    ((Mahasiswa) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setGkb(t.getNewValue());
                });


        addButton.setOnAction((ActionEvent e) -> {
            data.add(new Mahasiswa(
                    adddosen.getText(),
                    addmatkul.getText(),
                    addruang.getText(),
                    addwaktu.getText(),
                    addgkb.getText()));
            adddosen.clear();
            addmatkul.clear();
            addruang.clear();
            addwaktu.clear();
            addgkb.clear();
        });

        final Button delButton = new Button("Delete");
        delButton.setTextFill(Color.BLACK);
        delButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Dialog d = new Alert(Alert.AlertType.INFORMATION, "Delete Sucses!");
                d.show();
                table.getItems().removeAll(table.getSelectionModel().getSelectedItems());
            }
        });

        hBox.setSpacing(5);
        hBox.getChildren().addAll(adddosen,addmatkul,addruang,addwaktu,addgkb,addButton,delButton);
        vBox.setSpacing(5);
        vBox.setPadding(new Insets(10, 10, 10, 10));
        vBox.getChildren().addAll(label, table, hBox);

        table.setItems(data);
        table.getColumns().addAll(dosenCol,matkulCol,ruangCol,waktuCol,gkbCol);

        ((Group) scene.getRoot()).getChildren().addAll(vBox);

        stage.setScene(scene);
        stage.show();

    }

    public static class Mahasiswa {
        public String getDosen() {
            return Dosen.get();
        }

        public SimpleStringProperty dosenProperty() {
            return Dosen;
        }

        public void setDosen(String dosen) {
            this.Dosen.set(dosen);
        }

        public String getMatkul() {
            return Matkul.get();
        }

        public SimpleStringProperty matkulProperty() {
            return Matkul;
        }

        public void setMatkul(String matkul) {
            this.Matkul.set(matkul);
        }

        public String getRuang() {
            return Ruang.get();
        }

        public SimpleStringProperty ruangProperty() {
            return Ruang;
        }

        public void setRuang(String ruang) {
            this.Ruang.set(ruang);
        }

        public String getWaktu() {
            return Waktu.get();
        }

        public SimpleStringProperty waktuProperty() {
            return Waktu;
        }

        public void setWaktu(String waktu) {
            this.Waktu.set(waktu);
        }

        public String getGkb() {
            return Gkb.get();
        }

        public SimpleStringProperty gkbProperty() {
            return Gkb;
        }

        public void setGkb(String gkb) {
            this.Gkb.set(gkb);
        }

        private final SimpleStringProperty Dosen;
        private final SimpleStringProperty Matkul;
        private final SimpleStringProperty Ruang;
        private final SimpleStringProperty Waktu;
        private final SimpleStringProperty Gkb;

        private Mahasiswa(String fName,String lName, String Ruang, String Waktu,String Gkb){
            this.Dosen = new SimpleStringProperty(fName);
            this.Matkul = new SimpleStringProperty(lName);
            this.Ruang = new SimpleStringProperty(Ruang);
            this.Waktu = new SimpleStringProperty(Waktu);
            this.Gkb = new SimpleStringProperty(Gkb);
        }



    }


    public static void main(String[] args) {
        launch();
    }
}
