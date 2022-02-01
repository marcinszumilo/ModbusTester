package com.marsim.advancedmodbustester;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Random;

public class AdvancedModbusTesterApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        ModbusConfig listOfModbusDevices = new ModbusConfig();
        ModbusTester listOfModbusRequests = new ModbusTester();
        // temp set to be deleted

        ModbusConfigStructure device1 = new ModbusConfigStructure("urzadzenie 1","TCP", "SLAVE" , (byte) 5, "127.0.0.1","502");
        ModbusConfigStructure device2 = new ModbusConfigStructure("urzadzenie 2","TCP","SLAVE" , (byte) 6 ,"127.0.0.1","502");
        ModbusConfigStructure device3 = new ModbusConfigStructure("urzadzenie 3","TCP", "SLAVE" , (byte) 7 , "127.0.0.1","502");
        ModbusConfigStructure device4 = new ModbusConfigStructure("urzadzenie 4","TCP","SLAVE" , (byte) 8 ,"127.0.0.1","502");
        listOfModbusDevices.addDevice(device1.getKey(),device1);
        listOfModbusDevices.addDevice(device2.getKey(),device2);
        listOfModbusDevices.addDevice(device3.getKey(),device1);
        listOfModbusDevices.addDevice(device4.getKey(),device2);

        ModbusTesterStructure request1 = new ModbusTesterStructure("request 1" , "urzadzenie 1" , (byte) 1 , (byte) 3 , 1 , (short) 0 ,(short) 500 );
        ModbusTesterStructure request2 = new ModbusTesterStructure("request 2" , "urzadzenie 1" , (byte) 1 , (byte) 3 , 2 , (short) 0 ,(short) 500 );
        ModbusTesterStructure request3 = new ModbusTesterStructure("request 3" , "urzadzenie 1" , (byte) 1 , (byte) 3 , 3 , (short) 0 ,(short) 500 );
        ModbusTesterStructure request4 = new ModbusTesterStructure("request 4" , "urzadzenie 1" , (byte) 1 , (byte) 3 , 4 , (short) 0 ,(short) 500 );

        listOfModbusRequests.addDevice(request1.getKey(),request1);
        listOfModbusRequests.addDevice(request1.getKey(),request2);
        listOfModbusRequests.addDevice(request1.getKey(),request3);
        listOfModbusRequests.addDevice(request1.getKey(),request4);

        // end of temp set

        ModbusBackgroundTask backgroundTask = new ModbusBackgroundTask();

        // prepare main window

        TableView<ModbusTesterStructure> modbusRequestView = new TableView<ModbusTesterStructure>();
        modbusRequestView.setEditable(true);

        TableColumn firstRequestDataColumn = new TableColumn<>("Request Name");
        firstRequestDataColumn.setMinWidth(100);
        firstRequestDataColumn.setCellValueFactory(
                new PropertyValueFactory<ModbusConfigStructure, String>("key"));

        TableColumn secondRequestDataColumn = new TableColumn<>("Device Name");
        secondRequestDataColumn.setMinWidth(100);
        secondRequestDataColumn.setCellValueFactory(
                new PropertyValueFactory<ModbusConfigStructure, String>("device"));

        TableColumn thirdRequestDataColumn = new TableColumn<>("Device Address");
        thirdRequestDataColumn.setMinWidth(100);
        thirdRequestDataColumn.setCellValueFactory(
                new PropertyValueFactory<ModbusConfigStructure, String>("modbusDeviceAddress"));

        TableColumn fourthRequestDataColumn = new TableColumn<>("Request Code");
        fourthRequestDataColumn.setMinWidth(100);
        fourthRequestDataColumn.setCellValueFactory(
                new PropertyValueFactory<ModbusConfigStructure, String>("functionCode"));

        TableColumn fifthRequestDataColumn = new TableColumn<>("Request Address");
        fifthRequestDataColumn.setMinWidth(100);
        fifthRequestDataColumn.setCellValueFactory(
                new PropertyValueFactory<ModbusConfigStructure, String>("modbusAddress"));

        TableColumn sixthRequestDataColumn = new TableColumn<>("Value");
        sixthRequestDataColumn.setMinWidth(100);
        sixthRequestDataColumn.setCellValueFactory(
                new PropertyValueFactory<ModbusConfigStructure, String>("modbusValue"));

        TableColumn seventhRequestDataColumn = new TableColumn<>("Timeout");
        seventhRequestDataColumn.setMinWidth(100);
        seventhRequestDataColumn.setCellValueFactory(
                new PropertyValueFactory<ModbusConfigStructure, String>("modbusTimeout"));

        ObservableList<ModbusTesterStructure> allRequests = FXCollections.observableArrayList(request1,request2,request3,request4);

        modbusRequestView.setItems(allRequests);
        modbusRequestView.getColumns().setAll(firstRequestDataColumn, secondRequestDataColumn, thirdRequestDataColumn , fourthRequestDataColumn , fifthRequestDataColumn , sixthRequestDataColumn , seventhRequestDataColumn);


        primaryStage.setTitle("Advanced Modbus Tester");
        Menu menuFile = new Menu("File");
        MenuItem menuFileOpen = new MenuItem("Open");
        MenuItem menuFileSave = new MenuItem("Save");
        MenuItem menuFileExit = new MenuItem("Exit");
        menuFile.getItems().addAll(menuFileOpen , menuFileSave , menuFileExit);

        Menu menuDevices = new Menu("Devices");
        MenuItem menuDevicesConfiguration = new MenuItem("Configuration");
        menuDevices.getItems().addAll(menuDevicesConfiguration);

        Menu menuModbusTester = new Menu("Modbus");
        MenuItem menuModbusModbusTester = new MenuItem("Modbus Tester");
        MenuItem menuModbusModbusSniffer = new MenuItem("Modbus Sniffer");
        menuModbusTester.getItems().addAll(menuModbusModbusTester , menuModbusModbusSniffer);

        Menu menuHelp = new Menu("Help");

        MenuBar menuBar = new MenuBar();

        menuBar.getMenus().addAll(menuFile , menuDevices , menuModbusTester , menuHelp);

        VBox vBox = new VBox(menuBar);

        Scene scene = new Scene(vBox, 1200, 800);

        primaryStage.setScene(scene);
        primaryStage.show();

// background task

        Thread backgroundThread = new Thread(new Runnable() {
            @Override
            public void run() {
                boolean runTask = true;

                while (runTask) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        runTask = false;
                        e.printStackTrace();
                    }
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            Random ran = new Random();
                            backgroundTask.executeCommunication();
                            if (backgroundTask.execute(request1 , request2 , request3 , request4)) {
                                modbusRequestView.refresh();
                            }
                        }
                    });
                }
            }
        });

// event handlers

// menu/exit application exit

        menuFileExit.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });

// device/configuration new window

        menuDevicesConfiguration.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                TableView<ModbusConfigStructure> devicesTableView = new TableView<ModbusConfigStructure>();
                devicesTableView.setEditable(true);

                devicesTableView.setRowFactory(tv -> {
                    TableRow<ModbusConfigStructure> row = new TableRow<>();
                    row.setOnMouseClicked(eventDoubleClick -> {
                        if (eventDoubleClick.getClickCount() == 2 && (! row.isEmpty()) ) {
                            ModbusConfigStructure rowData = row.getItem();

                            GridPane deviceConfigurationEditLayout = new GridPane();

                            deviceConfigurationEditLayout.setPadding(new Insets(10, 10, 10, 10));
                            deviceConfigurationEditLayout.setVgap(5);
                            deviceConfigurationEditLayout.setHgap(5);
                            final TextField deviceName = new TextField();
                            deviceName.setPrefColumnCount(10);
                            deviceName.getText();
                            GridPane.setConstraints(deviceName, 0, 0);
                            deviceConfigurationEditLayout.getChildren().add(deviceName);

                            Scene deviceConfigurationEditScene = new Scene(deviceConfigurationEditLayout, 1000, 800);
                            Stage deviceConfigurationEditWindow = new Stage();
                            deviceConfigurationEditWindow.setTitle("Devices configuration");
                            deviceConfigurationEditWindow.setScene(deviceConfigurationEditScene);
                            deviceConfigurationEditWindow.initModality(Modality.WINDOW_MODAL);
                            deviceConfigurationEditWindow.initOwner(primaryStage);
                            deviceConfigurationEditWindow.setX(primaryStage.getX() + 50);
                            deviceConfigurationEditWindow.setY(primaryStage.getY() + 50);
                            deviceConfigurationEditWindow.show();

                        }
                    });
                    return row ;
                });

                TableColumn firstDataColumn = new TableColumn<>("Device Name");
                firstDataColumn.setMinWidth(100);
                firstDataColumn.setCellValueFactory(
                        new PropertyValueFactory<ModbusConfigStructure, String>("key"));

                TableColumn secondDataColumn = new TableColumn<>("Layer Type");
                secondDataColumn.setMinWidth(100);
                secondDataColumn.setCellValueFactory(
                        new PropertyValueFactory<ModbusConfigStructure, String>("modbusType"));

                TableColumn thirdDataColumn = new TableColumn<>("IP");
                thirdDataColumn.setMinWidth(100);
                thirdDataColumn.setCellValueFactory(
                        new PropertyValueFactory<ModbusConfigStructure, String>("modbusTCPIP"));

                TableColumn fourthDataColumn = new TableColumn<>("Port");
                fourthDataColumn.setMinWidth(100);
                fourthDataColumn.setCellValueFactory(
                        new PropertyValueFactory<ModbusConfigStructure, String>("modbusTCPIPPort"));

                ObservableList<ModbusConfigStructure> allDatas = FXCollections.observableArrayList(device1,device2,device3,device4);

                devicesTableView.setItems(allDatas);
                devicesTableView.getColumns().setAll(firstDataColumn, secondDataColumn, thirdDataColumn , fourthDataColumn);

                StackPane deviceConfigurationLayout = new StackPane();
                Scene deviceConfigurationScene = new Scene(deviceConfigurationLayout, 1000, 800);
                Stage deviceConfigurationWindow = new Stage();
                deviceConfigurationLayout.getChildren().add(devicesTableView);
                deviceConfigurationWindow.setTitle("Devices configuration");
                deviceConfigurationWindow.setScene(deviceConfigurationScene);
                deviceConfigurationWindow.initModality(Modality.WINDOW_MODAL);
                deviceConfigurationWindow.initOwner(primaryStage);
                deviceConfigurationWindow.setX(primaryStage.getX() + 50);
                deviceConfigurationWindow.setY(primaryStage.getY() + 50);
                deviceConfigurationWindow.show();
            }
        });

        menuModbusModbusTester.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                StackPane modbusRequestsLayout = new StackPane();
                Scene modbusRequestsScene = new Scene(modbusRequestsLayout, 1000, 800);
                Stage modbusRequestsWindow = new Stage();
                modbusRequestsLayout.getChildren().add(modbusRequestView);
                modbusRequestsWindow.setTitle("Modbus Requests List");
                modbusRequestsWindow.setScene(modbusRequestsScene);
                modbusRequestsWindow.initModality(Modality.WINDOW_MODAL);
                modbusRequestsWindow.initOwner(primaryStage);
                modbusRequestsWindow.setX(primaryStage.getX() + 50);
                modbusRequestsWindow.setY(primaryStage.getY() + 50);
                modbusRequestsWindow.show();
            }
        });
        backgroundThread.start();
    }
}