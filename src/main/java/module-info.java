module ni.edu.uam.serviciosweb {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires com.fasterxml.jackson.databind;


    opens ni.edu.uam.serviciosweb to javafx.fxml;
    opens ni.edu.uam.serviciosweb.controller to javafx.fxml;
    opens ni.edu.uam.serviciosweb.model to javafx.fxml;
    opens ni.edu.uam.serviciosweb.service to javafx.fxml;

    exports ni.edu.uam.serviciosweb.service;
    exports ni.edu.uam.serviciosweb.model;
    exports ni.edu.uam.serviciosweb;
    exports ni.edu.uam.serviciosweb.controller;
}