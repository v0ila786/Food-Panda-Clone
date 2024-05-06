module com.example.notfoodpandatest {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.media;
    requires javafx.swing;
    requires javafx.web;
    requires miglayout;
    requires TimingFramework;
    requires java.logging;
    requires AbsoluteLayout.SNAPSHOT;


    opens com.example.notfoodpandatest to javafx.fxml;
    exports com.example.notfoodpandatest;
}