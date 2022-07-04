package com.example.modul6_final;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.control.TableView;
//import javafx.scene.control.TreeTableColumn.CellEditEvent;
import javafx.util.Callback;


public class CRUDFinal extends Application{
    private final TableView<Mahasiswa> table = new TableView<>();
    private final ObservableList<Mahasiswa> data = FXCollections.observableArrayList();
    final HBox hBox = new HBox();

    public static void main(String[] args) {
        launch(args);
    }

    public void start (Stage stage){
        Scene scene = new Scene(new Group());
        stage.setTitle("Mini CRUD App By Yummm292");
        scene.setFill(Color.DARKGREY);
        stage.setWidth(1080);
        stage.setHeight(680);

        final Label label = new Label("JavaFX CRUD");
        label.setFont(Font.font("Poppins", FontWeight.BOLD,50));


        table.setEditable(true);

        TableColumn NameCol = new TableColumn("NamaDosen");
        NameCol.setMinWidth(200);
        NameCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Mahasiswa,String >, ObservableValue>() {
            @Override
            public ObservableValue call(TableColumn.CellDataFeatures<Mahasiswa,String >p) {
                return p.getValue().nameDosenProperty();
            }
        });

        TableColumn MatkulCol = new TableColumn("NamaMatkul");
        MatkulCol.setMinWidth(200);
        MatkulCol.setCellValueFactory(new PropertyValueFactory<Mahasiswa,String >("NamaMatkul"));
        MatkulCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Mahasiswa,String >, ObservableValue>() {
            @Override
            public ObservableValue call(TableColumn.CellDataFeatures<Mahasiswa,String >p) {
                return p.getValue().nameMatkulProperty();
            }
        });

        TableColumn WaktuCol = new TableColumn("Waktu");
        WaktuCol.setMinWidth(200);
        WaktuCol.setCellValueFactory(new PropertyValueFactory<Mahasiswa,String >("Waktu"));
        WaktuCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Mahasiswa,String >, ObservableValue>() {
            @Override
            public ObservableValue call(TableColumn.CellDataFeatures<Mahasiswa,String >p) {
                return p.getValue().timeProperty();
            }
        });

        TableColumn GKBCol = new TableColumn("GKB");
        GKBCol.setMinWidth(200);
        GKBCol.setCellValueFactory(new PropertyValueFactory<Mahasiswa,String>("GKB"));
        GKBCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Mahasiswa,String >, ObservableValue>() {
            @Override
            public ObservableValue call(TableColumn.CellDataFeatures<Mahasiswa,String >p) {
                return p.getValue().gkbProperty();
            }
        });

        TableColumn RuanganCol = new TableColumn("Ruangan");
        RuanganCol.setMinWidth(200);
        RuanganCol.setCellValueFactory(new PropertyValueFactory<Mahasiswa,String>("Ruangan"));
        RuanganCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Mahasiswa,String >, ObservableValue>() {
            @Override
            public ObservableValue call(TableColumn.CellDataFeatures<Mahasiswa,String >p) {
                return p.getValue().ruanganProperty();
            }
        });

        table.getColumns().add(NameCol);
        table.getColumns().add(MatkulCol);
        table.getColumns().add(WaktuCol);
        table.getColumns().add(GKBCol);
        table.getColumns().add(RuanganCol);
        table.setItems(data);

        final TextField addName = new TextField();
        addName.setPromptText("Nama Dosen");
        addName.setMaxWidth(NameCol.getPrefWidth());

        final TextField addMatkul = new TextField();
        addMatkul.setPromptText("Nama Matkul");
        addMatkul.setMaxWidth(MatkulCol.getPrefWidth());

        final TextField addWaktu = new TextField();
        addWaktu.setPromptText("Waktu");
        addWaktu.setMaxWidth(WaktuCol.getPrefWidth());

        final TextField addGKB = new TextField();
        addGKB.setPromptText("GKB");
        addGKB.setMaxWidth(GKBCol.getPrefWidth());
        addGKB.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    addGKB.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        final TextField addRuangan = new TextField();
        addRuangan.setPromptText("Ruangan");
        addRuangan.setMaxWidth(RuanganCol.getPrefWidth());

        final Button addButton = new Button("Add");
        addButton.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        addButton.setTextFill(Color.BLACK);
        addButton.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        addButton.setOnAction((ActionEvent e) ->{
            String dosen = addName.getText();
            String matkul = addMatkul.getText();
            String waktu = addWaktu.getText();
            String gkb = addGKB.getText();
            String ruangan = addRuangan.getText();

            if(dosen.isEmpty() || matkul.isEmpty() || waktu.isEmpty() || gkb.isEmpty() || ruangan.isEmpty()){
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Data Yang Di Input Harus Tidak Ada Yang Boleh Kosong");
                a.setTitle("Alert");
                a.showAndWait();
            }else {
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Data Telah Di Tambahkan");
                a.setTitle("Success");
                a.showAndWait();
                data.add(new Mahasiswa(
                        addName.getText(),
                        addMatkul.getText(),
                        addWaktu.getText(),
                        addGKB.getText(),
                        addRuangan.getText()));
                addName.clear();
                addMatkul.clear();
                addWaktu.clear();
                addGKB.clear();
                addRuangan.clear();
            }

        });

        Button updtButton = new Button("Update");
        updtButton.setBackground(new Background(new BackgroundFill(Color.YELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
        updtButton.setTextFill(Color.BLACK);
        updtButton.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        updtButton.setOnAction((ActionEvent e) -> {
            Dialog d=new Alert(Alert.AlertType.INFORMATION,"Update Sucess!!!");
            d.show();
            table.getItems().remove(table.getSelectionModel().getSelectedIndex());
            data.add(new Mahasiswa(addName.getText(),addMatkul.getText(),addWaktu.getText(),addGKB.getText(),addRuangan.getText()));
            addName.clear();
            addMatkul.clear();
            addWaktu.clear();
            addGKB.clear();
            addRuangan.clear();

        });

        final Button delButton = new Button("Delete");
        delButton.setBackground(new Background(new BackgroundFill(Color.CADETBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        delButton.setTextFill(Color.BLACK);
        delButton.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        delButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Dialog d = new Alert(Alert.AlertType.INFORMATION,"Delete Success!!!");
                d.show();
                table.getItems().removeAll(table.getSelectionModel().getSelectedItem());
            }
        });

        final Button exitButton = new Button("Exit");
        exitButton.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
        exitButton.setTextFill(Color.BLACK);
        exitButton.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        exitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Stage stage1 = (Stage) exitButton.getScene().getWindow();
                stage1.close();
            }
        });

        hBox.getChildren().addAll(addName,addMatkul,addWaktu,addGKB,addRuangan,addButton,updtButton,delButton,exitButton);
        hBox.setSpacing(10);

        final VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(18);
        vBox.setPadding(new Insets(15,15,15,15));
        vBox.getChildren().addAll(label,table,hBox);

        ((Group) scene.getRoot()).getChildren().addAll(vBox);

        stage.setScene(scene);
        stage.show();
    }


    public static class Mahasiswa{


        public String getNameDosen() {
            return nameDosen.get();
        }

        public SimpleStringProperty nameDosenProperty() {
            if (nameDosen == null) nameDosen = new SimpleStringProperty(this, "NamaDosen");
            return nameDosen;
        }

        public void setNameDosen(String nameDosen) {
            this.nameDosen.set(nameDosen);
        }

        public String getNameMatkul() {
            return nameMatkul.get();
        }

        public SimpleStringProperty nameMatkulProperty() {
            return nameMatkul;
        }

        public void setNameMatkul(String nameMatkul) {
            this.nameMatkul.set(nameMatkul);
        }

        public String getTime() {
            return time.get();
        }

        public SimpleStringProperty timeProperty() {
            return time;
        }

        public void setTime(String time) {
            this.time.set(time);
        }

        public String getGkb() {
            return gkb.get();
        }

        public SimpleStringProperty gkbProperty() {
            return gkb;
        }

        public void setGkb(String gkb) {
            this.gkb.set(gkb);
        }

        public String getRuangan() {
            return ruangan.get();
        }

        public SimpleStringProperty ruanganProperty() {
            return ruangan;
        }

        public void setRuangan(String ruangan) {
            this.ruangan.set(ruangan);
        }


        private SimpleStringProperty nameDosen;
        private SimpleStringProperty nameMatkul;
        private SimpleStringProperty time;
        private SimpleStringProperty gkb;

        private SimpleStringProperty ruangan;

        private Mahasiswa(String nameDos, String nameMat, String timee, String gkbb, String ruangann){
            this.nameDosen = new SimpleStringProperty(nameDos);
            this.nameMatkul = new SimpleStringProperty(nameMat);
            this.time = new SimpleStringProperty(timee);
            this.gkb = new SimpleStringProperty(gkbb);
            this.ruangan = new SimpleStringProperty(ruangann);
        }
    }
}
