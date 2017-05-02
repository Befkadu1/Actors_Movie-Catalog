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
    private TableView<ArtistClass> actorTableView;

    @FXML
    private TableColumn<ArtistClass, Integer> actorIdTableColumn;

    @FXML
    private TableColumn<ArtistClass, String> actorFirstNameTableColumn;

    @FXML
    private TableColumn<ArtistClass, String> actorLastNameTableColumn;

    @FXML
    private TableColumn<ArtistClass, Integer> actorAgeTableColumn;

    @FXML
    private TextField searchActor;
    
    @FXML
    private TextField searchMovie;

    @FXML
    private Button backButton;

    @FXML
    private TextField firstNameTextField;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private TextField ageTextField;

    private String selectMovieListView;

    private Logic logic = Logic.getInstance(); // singleton

    @FXML
    private Label warningMessageLabel;

    public static ArtistClass actorSelection;

    private int actorId = 0;
    private String publishDate = null;
    private int movieId = 0;
    private int actorAge=0;
    /**
     * Initializes the controller class.
     */
    //ObservableList to save only the names of movies
    public static ObservableList<String> movieNameObservableList;
    public static ObservableList<ArtistClass> artistObservableList;

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        movieNameObservableList = FXCollections.observableArrayList();
        artistObservableList = FXCollections.observableArrayList();
        //Saving all the movies name into the movieNameObservableList
        for (int i = 0; i < logic.getAllMovieArrayList().size(); i++)
        {
            movieNameObservableList.add(logic.getAllMovieArrayList().get(i).getmovieName());
        }
        movieListView.setItems(movieNameObservableList);

        //Associate the data with the table columns
        actorIdTableColumn.setCellValueFactory(new PropertyValueFactory<>("artistId"));
        actorFirstNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        actorLastNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        actorAgeTableColumn.setCellValueFactory(new PropertyValueFactory<>("age"));

        for (int i = 0; i < logic.getAllArtistsArrayList().size(); i++)
        {
            artistObservableList.add(logic.getAllArtistsArrayList().get(i));
        }
        /* for(ArtistClass artist : logic.getAllArtistsArrayList()){
            artistObservableList.add(artist);
        }*/
        //to show the artshowProductTableViewist table
        actorTableView.setItems(artistObservableList);
        //To make the table column contents editable
        actorFirstNameTableColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        actorLastNameTableColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        searchActor.textProperty().addListener((observable, oldValue, newValue) -> 
                {
                    //clearing the movieObservableList
                    artistObservableList.clear();
                    //         artistArrayList.stream()
                    logic.getAllArtistsArrayList().stream()
                            .filter(t -> t.getfirstName().toLowerCase().startsWith(searchActor.getText().toLowerCase()))
                            .forEach(j -> artistObservableList.add(j));
                    
                    
        });
        
        searchMovie.textProperty().addListener((observable, oldValue, newValue) -> 
                {
                    movieNameObservableList.clear();
                    logic.getAllMovieArrayList().stream()
                            .filter(t -> t.getmovieName().toLowerCase().startsWith(searchMovie.getText().toLowerCase()))
                            .forEach(j -> movieNameObservableList.add(j.getmovieName()));
                            movieListView.setItems(movieNameObservableList);
                    
               });

    }

    //Edit the name column in the table
    @FXML
    public void handleActorFirstNameAction(TableColumn.CellEditEvent<ArtistClass, String> t)
    {
        ((ArtistClass) t.getTableView().getItems().get(t.getTablePosition().getRow())).setfirstName(t.getNewValue());

        //Testing if the info is updated
        System.out.println("test " + FXMLDocumentController.artistObservableList.get(0).getfirstName());

    }
    
    //Edit the last name column in the table
    @FXML
    public void handleActorLastNameAction(TableColumn.CellEditEvent<ArtistClass, String> t)
    {
        ((ArtistClass) t.getTableView().getItems().get(t.getTablePosition().getRow())).setlastName(t.getNewValue());

    }


    @FXML
    public void backButtonAction(ActionEvent event) throws IOException
    {
        Parent root2 = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene scene = new Scene(root2);
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.setScene(scene);
    }

    @FXML
    private void movieSelectionAction(MouseEvent event)
    {
        warningMessageLabel.setText(" ");
        selectMovieListView = (String) movieListView.getSelectionModel().getSelectedItem();
        artistObservableList.clear();
        // FXMLDocumentController.artistObservableList.clear();
        //FXMLDocumentController.movieArrayList.stream()
        logic.getAllMovieArrayList().stream()
                .filter(i -> i.getmovieName().equals(selectMovieListView))
                .forEach(j -> artistObservableList.addAll(j.getActorArrayList()));
        
        //<to get to know the selected movie id
        for(int i = 0; i< logic.getAllMovieArrayList().size(); i++){
            if(logic.getAllMovieArrayList().get(i).getmovieName().equals(selectMovieListView)){
                movieId = logic.getAllMovieArrayList().get(i).getmovieID();
                publishDate = logic.getAllMovieArrayList().get(i).getpublishDate();
                System.out.println("movieId selection method " + movieId);
                System.out.println("publishDate sel method " + publishDate);
            }
        }

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
        if (actorSelection == null){
           warningMessageLabel.setText("Select the actor and movie"); 
        }
        else if (selectMovieListView != null)
        {
            warningMessageLabel.setText("");
         logic.deleteActorFromMovieArrayList(movieId,actorSelection.getartistId());
         artistObservableList.remove(actorSelection);

        } else
        {
            warningMessageLabel.setText("Select the movie first");
        }
    }

    @FXML
    private void seeAllActorsButton(ActionEvent event)
    {

        //clearing the selected items from table view and list view
        actorTableView.getItems().clear();

        artistObservableList.clear();
        logic.getAllArtistsArrayList().stream()
                .forEach(s -> artistObservableList.add(s));
        
        movieNameObservableList.clear();
        logic.getAllMovieArrayList().stream()
                .forEach(i-> movieNameObservableList.add(i.getmovieName()));

    }

    public void insertArtist(ActionEvent event)
    {

        warningMessageLabel.setText(" ");
        selectMovieListView = (String) movieListView.getSelectionModel().getSelectedItem();

        if (selectMovieListView == null)
        {
            warningMessageLabel.setText("Select the movie first");
        } else
        {
            ArtistClass ac = new ArtistClass();//initializing
            boolean check = false;
            if (firstNameTextField.getText().isEmpty() || lastNameTextField.getText().isEmpty() || ageTextField.getText().isEmpty())
            {
                warningMessageLabel.setText("Write full name and age");
            }
            else if (!firstNameTextField.getText().matches("^[A-zåäöÅÄÖ-]+$") || 
                    !lastNameTextField.getText().matches("^[A-zåäöÅÄÖ-]+$")){
                 warningMessageLabel.setText("Write only letters");
            }
            else
            {
                try
            {
                actorAge = Integer.parseInt(ageTextField.getText());
             
                //checking if the movie to be added has already existed in the movieArrayList
                for (int j = 0; j < logic.getAllArtistsArrayList().size(); j++)
                {

                    if (logic.getAllArtistsArrayList().get(j).getfirstName().equalsIgnoreCase(firstNameTextField.getText())
                            && logic.getAllArtistsArrayList().get(j).getlastName().equalsIgnoreCase(lastNameTextField.getText()))
                    {
                        warningMessageLabel.setText(" ");
                        actorId = logic.getAllArtistsArrayList().get(j).getartistId();
                        check = true;

                    }
                }

                //If the actor is already exist, which means he/she already has an ID, no need of assigning another ID
                if (check)
                {
                    warningMessageLabel.setText("");
                    artistObservableList.clear();
                    artistObservableList.addAll(logic.checkIfTheActorExists(firstNameTextField.getText(), lastNameTextField.getText(), selectMovieListView, actorAge, actorId));
                } else
                {
                    //When a new movie to be added 
                    warningMessageLabel.setText("");
                    artistObservableList.clear();
                    int newArtisId = (logic.getAllArtistsArrayList().size() + 1);
                    ac = new ArtistClass(newArtisId, firstNameTextField.getText(), lastNameTextField.getText(), actorAge);
                    System.out.println("ac " + ac);
                    for (int i = 0; i < logic.getAllMovieArrayList().size(); i++)
                    {
                        //to check the added movie in the selected person
               if(logic.getAllMovieArrayList().get(i).getmovieName().equals(selectMovieListView)){
                   logic.getAllMovieArrayList().get(i).getActorArrayList().add(ac);
                   artistObservableList.addAll(logic.getAllMovieArrayList().get(i).getActorArrayList());
                   logic.addAllActors(ac);
                   break;
               }

                    }
                    System.out.println("movieId insert " + movieId);
                System.out.println("publishDate insert " + publishDate);
                                       //Adding the movie in to the new actor's arrayList
                   for(int i = 0; i< logic.getAllArtistsArrayList().size();i++){
                       if(logic.getAllArtistsArrayList().get(i).getartistId()==newArtisId){
                           logic.getAllArtistsArrayList().get(i).getMovieArrayList().add(new MovieClass(movieId, selectMovieListView, publishDate));
                       }
                   }
                }
                }catch (Exception e)
            {
                warningMessageLabel.setText("Fill a number in the age box");
            }
            }
        }
    }
}
