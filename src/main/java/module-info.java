module com.example.Abarrotes {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.Abarrotes.controladores to javafx.fxml;
    opens com.example.Abarrotes.modelo to javafx.fxml;
    opens com.example.Abarrotes.app to javafx.fxml;

    exports com.example.Abarrotes.controladores;
    exports com.example.Abarrotes.modelo;
    exports com.example.Abarrotes.app;
}
