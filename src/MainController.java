import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Window;

import java.io.File;
import java.util.ArrayList;

public class MainController {
    private static int MAX_NUM_OF_LINES = 50;

    private File mainDirectory;
    private File saveDirectory;
    private ArrayList<String> textAreaOutputLines;

    @FXML
    protected VBox vBoxMenu;

    @FXML
    protected Button btnOpenFolder;
    @FXML
    protected TextField textFieldOpenFolder;

    @FXML
    protected Button btnSaveFolder;
    @FXML
    protected TextField textFieldSaveFolder;

    @FXML
    protected ComboBox<String> comboBoxMode;
    @FXML
    protected CheckBox keepFolderStructureCheckbox;

    @FXML
    protected Button btnNextImage;
    @FXML
    protected Button btnSkipImage;
    @FXML
    protected Button btnNextFolder;
    @FXML
    protected Button btnSkipFolder;

    @FXML
    protected TextArea textAreaOutput;

    @FXML
    protected ProgressBar progressBar;
    @FXML
    protected Label progressBarText;

    public void initialize(){
        textAreaOutputLines = new ArrayList<>();

        textAreaOutput.prefHeightProperty().bind(vBoxMenu.heightProperty());

        comboBoxMode.setOnAction(event -> {
            switch (comboBoxMode.getValue()){
                case "Cropped images":
                    keepFolderStructureCheckbox.setDisable(false);
                    break;
                case "Coords. single file":
                    keepFolderStructureCheckbox.setDisable(true);
                    break;
                case "Coords. multiple files":
                    keepFolderStructureCheckbox.setDisable(false);
                    break;
                default:
                    System.out.println("Nothing was selected!");
                    break;
            }
            comboBoxMode.setStyle("-fx-border-style: none;");
        });

        btnNextImage.setOnAction(event -> {
            if(textFieldOpenFolder.getText().isEmpty()){
                textFieldOpenFolder.setStyle("-fx-border-color: red;");
            }
            if(textFieldSaveFolder.getText().isEmpty()){
                textFieldSaveFolder.setStyle("-fx-border-color: red;");
            }
            if(comboBoxMode.getValue() == null){
                comboBoxMode.setStyle("-fx-border-color: red;");
            }
        });

        btnSkipImage.setOnAction(event -> textAreaOutputHandler("Image skipped!"));
    }

    public void btnOpenFolderHandler(){
        Window owner = btnOpenFolder.getScene().getWindow();
        DirectoryChooser dc = new DirectoryChooser();
        File directory = dc.showDialog(owner);
        if(directory != null){
            textFieldOpenFolder.setText(directory.getPath());
            mainDirectory = directory;
            textAreaOutputHandler("Main directory: " + mainDirectory.getPath());
            textFieldOpenFolder.setStyle("-fx-border-style: none;");
        }
    }

    public void btnSaveFolderHandler(){
        Window owner = btnSaveFolder.getScene().getWindow();
        DirectoryChooser dc = new DirectoryChooser();
        File directory = dc.showDialog(owner);
        if(directory != null){
            textFieldSaveFolder.setText(directory.getPath());
            saveDirectory = directory;
            textAreaOutputHandler("Save directory: " + saveDirectory.getPath());
            textFieldSaveFolder.setStyle("-fx-border-style: none;");
        }
    }

    private void textAreaOutputHandler(String str) {
        if(textAreaOutputLines.size() < MAX_NUM_OF_LINES){
            textAreaOutputLines.add(str);
            printToOutput(str);
        } else {
            textAreaOutputLines.add(str);
            textAreaOutputLines.remove(0);
            textAreaOutput.setText("");
            for (String s : textAreaOutputLines) {
                printToOutput(s);
            }
            textAreaOutput.setScrollTop(Double.MAX_VALUE);
        }
    }

    private void printToOutput(String s){
        textAreaOutput.appendText(s + '\n');
    }
}
