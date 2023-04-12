module com.github.javaniw.convexhullproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.testng;


    opens com.github.javaniw.convexhullproject to javafx.fxml;
    exports com.github.javaniw.convexhullproject;
    exports com.github.javaniw.convexhullproject.ConvexHull;
    opens com.github.javaniw.convexhullproject.ConvexHull to javafx.fxml;
}