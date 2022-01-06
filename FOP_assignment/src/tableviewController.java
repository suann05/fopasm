
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ngsua
 */
public class tableviewController {
    
    @FXML
    private TableView<Movie> tableView;
    @FXML
    private TableColumn<Movie, String> nameCol;
    @FXML
    private TableColumn<Movie, String> dateCol;
    @FXML
    private TableColumn<Movie, String> timeCol;
    
    String query = null;
    Connection connection = null ;
    PreparedStatement preparedStatement = null ;
    ResultSet resultSet = null ;
    Movie movie = null ;
    
    ObservableList<Movie>  movieList = FXCollections.observableArrayList();
    
    public void refreshTable(){
         try {
            movieList.clear();
            
            query = "SELECT * FROM movie";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()){
                movieList.add(new Movie(
                        
                        resultSet.getString("name"),
                        resultSet.getString("date"),
                        resultSet.getString("time")));
                tableView.setItems(movieList);
                
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(tableviewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void loadDate(){
        
        connection = OnAction.getConnect();
        refreshTable();
        
        
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        timeCol.setCellValueFactory(new PropertyValueFactory<>("time"));
        
    }
}
