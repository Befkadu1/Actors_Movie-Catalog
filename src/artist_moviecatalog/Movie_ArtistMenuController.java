/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artist_moviecatalog;

import static artist_moviecatalog.FXMLDocumentController.artistArrayList;
import static artist_moviecatalog.FXMLDocumentController.movieArrayList;
import static artist_moviecatalog.FXMLDocumentController.movieObservableList;
import static artist_moviecatalog.FXMLDocumentController.movieSelection;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author Befkadu Degefa
 */
public class Movie_ArtistMenuController implements Initializable
{

    @FXML
    private ListView movieListView;
    
    @FXML
    private TableView <ArtistClass> actorTableView;
    
    @FXML
    private TableColumn <ArtistClass, Integer>actorIdTableColumn;
    
    @FXML
    private TableColumn <ArtistClass, String>actorFirstNameTableColumn;
    
    @FXML
    private TableColumn <ArtistClass, String> actorLastNameTableColumn;
    
    @FXML
    private TableColumn  <ArtistClass, Integer> actorAgeTableColumn;
    
    @FXML
    private TextField searchActor;
    
    @FXML
    private Button backButton;
    
    private String selectMovieListView;
    
    @FXML
    private Label warningMessageLabel;
    
    public static ArtistClass actorSelection;
    /**
     * Initializes the controller class.
     */
    //ObservableList to save only the names of movies
    public static ObservableList<String> movieNameObservableList;
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        movieNameObservableList = FXCollections.observableArrayList();
       //Saving all the movies name into the movieNameObservableList
        for (int i = 0;i < movieArrayList.size(); i++)
        {
            movieNameObservableList.add(movieArrayList.get(i).getmovieName());
        }
       movieListView.setItems(movieNameObservableList);
        
       //Associate the data with the table columns
         actorIdTableColumn.setCellValueFactory( new PropertyValueFactory<>("artistId"));
        actorFirstNameTableColumn.setCellValueFactory( new PropertyValueFactory<>("firstName"));
        actorLastNameTableColumn.setCellValueFactory( new PropertyValueFactory<>("lastName"));
        actorAgeTableColumn.setCellValueFactory( new PropertyValueFactory<>("age"));
        
        //to show the artshowProductTableViewist table
        actorTableView.setItems(FXMLDocumentController.artistObservableList);
        //To make the table column contents editable
        actorFirstNameTableColumn.setCellFactory(TextFieldTableCell.forTableColumn());       
        
       
       searchActor.textProperty().addListener(( observable,  oldValue,  newValue)-> {
              //clearing the movieObservableList
                    FXMLDocumentController.artistObservableList.clear();
              artistArrayList.stream()
                      .filter(t->  t.getfirstName().toLowerCase().startsWith(searchActor.getText().toLowerCase()))
                              .forEach(j-> FXMLDocumentController.artistObservableList.add(j));
               

            });

    }    
    
     //Edit the name column in the table
    @FXML
    public void handleActorFirstNameAction (TableColumn.CellEditEvent<ArtistClass, String>t){
        System.out.println("t.getOldValue() " + t.getOldValue()+ " getNewValue() " + t.getNewValue());
       ((ArtistClass) t.getTableView().getItems().get( t.getTablePosition().getRow()) ).setfirstName(t.getNewValue());
       
       //Testing if the info is updated
        System.out.println("test " + FXMLDocumentController.artistObservableList.get(0).getfirstName());       
 
    }
    
    @FXML
    public void backButtonAction(ActionEvent event) throws IOException{
            Parent root2 = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
            Scene scene = new Scene(root2);
            Window stage = actorTableView.getScene().getWindow();
            //stage.setScene(scene);
    }
    
    @FXML
    private void movieSelectionAction(MouseEvent event){
        warningMessageLabel.setText(" ");
      selectMovieListView = (String) movieListView.getSelectionModel().getSelectedItem();
      FXMLDocumentController.artistObservableList.clear();
      FXMLDocumentController.movieArrayList.stream()
              .filter(i-> i.getmovieName().equals(selectMovieListView))
              .forEach(j-> FXMLDocumentController.artistObservableList.addAll(j.getActorArrayList()));

        
    }
    
    @FXML
    public void deleteMovie()
    {
         actorSelection = actorTableView.getSelectionModel().getSelectedItem();
          selectMovieListView = (String) movieListView.getSelectionModel().getSelectedItem();
          
        /*  if (selectMovieListView != null)             
        {          
         
          FXMLDocumentController.movieArrayList.stream()
                  .filter(i-> i.getmovieName().equals(selectMovieListView))
                  .forEach(s-> movieNameObservableList.remove(s.getmovieName()));
         
        }
           if (actorSelection != null)
        {
          FXMLDocumentController.artistArrayList.stream()
                  .filter(i-> i.getartistId()== actorSelection.getartistId())
                  .forEach(s-> FXMLDocumentController.artistObservableList.remove(actorSelection));
          
        }*/
 if (selectMovieListView != null )             
        {

         
        for (int i = 0; i < movieArrayList.size(); i++)
        {  
            for (int j = 0; j < movieArrayList.get(i).getActorArrayList().size(); j++)
            {
                if(actorSelection != null){
                if (actorSelection.getartistId() == movieArrayList.get(i).getActorArrayList().get(j).getartistId())
                {
                    warningMessageLabel.setText(" ");
                    movieArrayList.get(i).getActorArrayList().remove(actorSelection);
                    artistArrayList.remove(actorSelection);
                    FXMLDocumentController.artistObservableList.remove(actorSelection);
                }
               
                
                }
                
                 else{
                    warningMessageLabel.setText("Select the actor first");
                }

        }
        
        }
        }
 else{
      warningMessageLabel.setText("Select the movie first");
 }
    }
    
     @FXML
      private void seeAllActorsButton(ActionEvent event){
          
          //clearing the selected items from table view and list view
          actorTableView.getItems().clear();
          
          
          FXMLDocumentController.artistObservableList.clear();
          FXMLDocumentController.artistArrayList.stream()
                  .forEach(s-> FXMLDocumentController.artistObservableList.add(s));

      }
}
