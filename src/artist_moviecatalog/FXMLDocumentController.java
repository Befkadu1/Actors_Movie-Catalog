/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artist_moviecatalog;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.control.cell.TextFieldTreeCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 *
 * @author Befkadu Degefa
 */
public class FXMLDocumentController implements Initializable
{

    @FXML
    private Label showName;

    @FXML
    private Label showActorNameText;

    @FXML
    private Label showActorAgeText;

    @FXML
    private Label warningMessage;

    @FXML
    private Label showAge;

    @FXML
    private TextField insertFullName;

    @FXML
    private TextField insertAge;

    @FXML
    private TextField searchMovie;

    @FXML
    private Button deleteMovie;

    @FXML
    private Button insertMovieButton;

    @FXML
    private TreeView treeView;


    public final String name = " ";

    private TreeItem<String> node;

    @FXML
    private TableView<MovieClass> movieTableView;

    @FXML
    private TableColumn<MovieClass, Integer> movieIDcolumn;

    @FXML
    private TableColumn<MovieClass, String> movieNameColumn;

    @FXML
    private TableColumn<MovieClass, String> moviePublishDateColumn;

    @FXML
    private ImageView imageView;

    @FXML
    private ImageView imageViewLogo;

    @FXML
    private TextField insertMovieName;

    @FXML
    private TextField insertMoviePublishDate;
    private int insertMoviePublishDateInt = 0;

    private Logic logic = Logic.getInstance(); // singleton

    //ArrayList to save all artists
    public static ArrayList<ArtistClass> artistArrayList = new ArrayList<>();

    //ArrayList to save all movies
    public static ArrayList<MovieClass> movieArrayList = new ArrayList<>();

    //ObservableList to save artists
    public static ObservableList<ArtistClass> artistObservableList;

    //ObservableList to save movies
    public static ObservableList<MovieClass> movieObservableList;

    public static MovieClass movieSelection; //A string to show when the user select the artist table,artistTableView
    private int actorAge;
    private int actorId;
    private String firstName = null;
    private String lastName = null;

    //Shows the movies of the selected artist
    @FXML
    private void getOnMouseClickedOnArtistView(MouseEvent event)
    { 
        //lambda way
        treeView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue)
                -> 
                {
                    TreeItem<String> selectedItem = (TreeItem<String>) treeView.getSelectionModel().getSelectedItem();
                    
                    //if the person don't select the actor,or the title of the tree view
                    if (selectedItem == null || selectedItem == node.getParent())
                    {
                        System.out.println(" ");
                    } else
                    {

                        showActorNameText.setText("Name ");
                        showActorAgeText.setText("Age ");
                        
                        //Spliting first name and last name
                        String[] parts = selectedItem.getValue().split(" ");

                        firstName = parts[0];
                        lastName = parts[1];
                        
                        //Show the actor's full name
                        showName.setText(parts[0] + " " + parts[1]);

                        //clearing the movieObservableList
                        movieObservableList.clear();

                        for (int j = 0; j < logic.getAllArtistsArrayList().size(); j++)
                        {

                            //filtering the selected actor
                            if (logic.getAllArtistsArrayList().get(j).getfirstName().equalsIgnoreCase(parts[0])
                                    && logic.getAllArtistsArrayList().get(j).getlastName().equalsIgnoreCase(parts[1]))
                            {
                                actorAge = logic.getAllArtistsArrayList().get(j).getage();
                                showAge.setText(Integer.toString(logic.getAllArtistsArrayList().get(j).getage()));
                                actorId = logic.getAllArtistsArrayList().get(j).getartistId();
                                //loading the image for the specific actor
                                switch (actorId)
                                {
                                    case 1:
                                        loadImage("harison.jpg", " ");
                                        break;
                                    case 2:
                                        loadImage("sean.jpg", " ");
                                        break;
                                    case 3:
                                        loadImage("anne.jpg", " ");
                                        break;
                                    case 4:
                                        loadImage("denzel.jpg", " ");
                                        break;
                                    default:
                                        System.out.println("hi");

                                }

                                for (int i = 0; i < logic.getAllArtistsArrayList().get(j).getMovieArrayList().size(); i++)
                                {
                                    movieObservableList.add(logic.getAllArtistsArrayList().get(j).getMovieArrayList().get(i));

                                }
                                

                            }
                        }
                    }
                    //lambda way
                    /*       artistArrayList.stream()
                        .filter(i->i.getfirstName().equalsIgnoreCase(parts[0]) && i.getlastName().equalsIgnoreCase(parts[1]))
                        .forEach(j-> movieObservableList.addAll(j.getMovieArrayList()));
                showName.setText(parts[0] + " " + parts[1]);
                
                artistArrayList.stream()
                        .filter(i->i.getfirstName().equalsIgnoreCase(parts[0]) && i.getlastName().equalsIgnoreCase(parts[1]))
                        .forEach(j-> showAge.setText(Integer.toString(j.getage())));
                
                     */

        });
    }

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        //initialisation of the observableLists
        artistObservableList = FXCollections.observableArrayList();
        movieObservableList = FXCollections.observableArrayList();
        
        //loading the logo image
        loadImage("imdb.png", "logo");

        //loading the artist list in the artist observable list
        logic.getAllArtistsArrayList().stream()
                .forEach(i-> artistObservableList.addAll(logic.getAllArtistsArrayList()));

       
        //loading all movies to the movie observable list, foreach way        
        for(MovieClass movie:  logic.getAllMovieArrayList()){
            movieObservableList.add(movie);
        }
     
     

        //to show the tree items,from the method
        showTreeItems();

        //Associate the data with the table column
        movieIDcolumn.setCellValueFactory(cellData -> cellData.getValue().movieIDProperty().asObject());
        movieNameColumn.setCellValueFactory(cellData -> cellData.getValue().movieNameProperty());
        moviePublishDateColumn.setCellValueFactory(cellData -> cellData.getValue().publishDateProperty());

        //displaying the movies on the movieTableView
        movieTableView.setItems(movieObservableList);

        //initializing for editing the columns
        //initializing
        movieNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        moviePublishDateColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        treeView.setCellFactory(TextFieldTreeCell.forTreeView());// Set a cell factory to use TextFieldTreeCell       

        //addListener for the search box
        searchMovie.textProperty().addListener((observable, oldValue, newValue) ->  {
                    //clearing the movieObservableList
                    movieObservableList.clear();
                    logic.getAllMovieArrayList().stream()
                            .filter(t -> t.getmovieName().toLowerCase().startsWith(searchMovie.getText().toLowerCase()))
                            .forEach(j -> movieObservableList.add(j));

        });

    }

    //Show tree items
    private void showTreeItems()
    {
        //Tree items
        TreeItem<String> root = new TreeItem<>("Actors");
        for (ArtistClass artistObservableList1 : logic.getAllArtistsArrayList())
        {
            node = new TreeItem<>(artistObservableList1.getfirstName() + " " + artistObservableList1.getlastName());
            root.getChildren().addAll(node);
        }
        treeView.setRoot(root);
    }

    //loads the image
    private void loadImage(String pic, String img)
    {
        Image image = new Image(getClass().getResourceAsStream("images/" + pic));
        if (img.equals("logo"))
        {
            imageViewLogo.setImage(image);
        } else
        {
            imageView.setImage(image);
        }

    }

    //Edit the movie name column in the table
    @FXML
    public void handleEditMovieNameAction(TableColumn.CellEditEvent<MovieClass, String> t)
    {
        ((MovieClass) t.getTableView().getItems().get(t.getTablePosition().getRow())).setmovieName(t.getNewValue());

    }

    //Edit the movie's publish year column in the table
    @FXML
    public void handleEditMoviePublishYearAction(TableColumn.CellEditEvent<MovieClass, String> t)
    {
        ((MovieClass) t.getTableView().getItems().get(t.getTablePosition().getRow())).setpublishDate(t.getNewValue());

    }

    //Deleting the movie from the selected actor
    @FXML
    public void deleteMovieFromArtistArrayList(ActionEvent event)
    {
        TreeItem<String> selectedItem = (TreeItem<String>) treeView.getSelectionModel().getSelectedItem();
        movieSelection = movieTableView.getSelectionModel().getSelectedItem();

        
        //message if the actor not selected
        if (selectedItem == null || movieSelection == null)
        {
            warningMessage.setText("Select the actor first");
        } else
        {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete confirmation");
            alert.setHeaderText("You can not undo this!");
//        alert.setGraphic(new ImageView(this.getClass().getResource("warning.png").toString()));
            alert.setContentText("Are you sure you want to delete movie" + " " + movieSelection.getmovieName() + "?");
            Optional<ButtonType> action = alert.showAndWait();
            if (action.get() == ButtonType.OK)
            {
                movieSelection = movieTableView.getSelectionModel().getSelectedItem();
                warningMessage.setText(" "); //clearing the warning message
                if (logic.removeMovie(movieSelection.getmovieID(), actorId, movieSelection, firstName, lastName))
                {
                    movieObservableList.remove(movieSelection);
                }
                warningMessage.setText(movieSelection.getmovieName() + " " + "has been deleted. ");
                //   if(selectArtist.getName()==null){
                //       statusLabel.setText(" ");
                //     }
            }


            
        }
    }

    //Inserting a movie for the selected actor
    public void insertMovie(ActionEvent event)
    {

        TreeItem<String> selectedItem = (TreeItem<String>) treeView.getSelectionModel().getSelectedItem();
        TreeItem<ArtistClass> selectedItemObject = (TreeItem<ArtistClass>) treeView.getSelectionModel().getSelectedItem();
        
        //Message if the actor not selected
        if (selectedItem == null)
        {
            warningMessage.setText("Select the actor first");
        } else
        {
            MovieClass mc = new MovieClass();//initializing
            String[] parts = selectedItem.getValue().split(" ");
            int movieId = 0;
            boolean check = false;

            //Message if the movie name box and publish date empty 
            if (insertMovieName.getText().isEmpty() || insertMoviePublishDate.getText().isEmpty())
            {
                warningMessage.setText("Write both movie name and publish date");
            } 
            //message if the user enters the non letters
            else if (!insertMovieName.getText().matches("^[A-zåäöÅÄÖ-]+$") || (!insertMoviePublishDate.getText().matches("^[0-9]*$")))
            {
                warningMessage.setText("Write only letters & number");
            } else
            {

                try
                {
                    insertMoviePublishDateInt = Integer.parseInt(insertMoviePublishDate.getText());

                    //checking if the movie to be added has already existed in the movieArrayList
                    for (int j = 0; j < logic.getAllMovieArrayList().size(); j++)
                    {

                        if (logic.getAllMovieArrayList().get(j).getmovieName().equalsIgnoreCase(insertMovieName.getText())
                                && logic.getAllMovieArrayList().get(j).getpublishDate().equals(insertMoviePublishDate.getText()))
                        {
                            warningMessage.setText(" ");
                            movieId = logic.getAllMovieArrayList().get(j).getmovieID();
                            check = true;

                        }
                    }
                    if (check)
                    {
                        warningMessage.setText("");
                        movieObservableList.clear();
                        // movieArrayList.addAll(logic.checkIfTheMovieExists(insertMovieName.getText(), insertMoviePublishDate.getText(), parts, movieId));
                        movieObservableList.addAll(logic.checkIfTheMovieExists(insertMovieName.getText(), insertMoviePublishDate.getText(), parts, movieId, actorId, actorAge));

                    } else
                    {
                        //When a new music to be added 
                        warningMessage.setText("");
                        movieObservableList.clear();
                        int newMovieId = (logic.getAllMovieArrayList().size() + 1);
                        mc = new MovieClass(newMovieId, insertMovieName.getText(), insertMoviePublishDate.getText());
                        for (int i = 0; i < logic.getAllArtistsArrayList().size(); i++)
                        {

                            //to check the added movie in the selected person
                            if (logic.getAllArtistsArrayList().get(i).getfirstName().equalsIgnoreCase(parts[0])
                                    && logic.getAllArtistsArrayList().get(i).getlastName().equalsIgnoreCase(parts[1]))
                            {
                                //logic.getAllArtistsArrayList().get(i).getMovieArrayList().add(mc);
                                logic.addAllMovies(mc);

                                logic.getAllArtistsArrayList().get(i).setMovieArrayList(mc);
                                //logic.addArtistInMusicArray(actorId, parts[0], parts[1], actorAge);
                                //movieArrayList.addAll(logic.getAllArtistsArrayList().get(i).getMovieArrayList());
                                movieObservableList.addAll(logic.getAllArtistsArrayList().get(i).getMovieArrayList());
                            }

                        }

                        //Adding the person in to the new movie arrayList
                        for (int i = 0; i < logic.getAllMovieArrayList().size(); i++)
                        {
                            if (logic.getAllMovieArrayList().get(i).getmovieID() == newMovieId)
                            {
                                logic.getAllMovieArrayList().get(i).getActorArrayList().add(new ArtistClass(actorId, parts[0], parts[1], actorAge));
                            }
                        }

                    }
                } catch (Exception e)
                {
                    warningMessage.setText("Fill a number in the publish date box");
                }
            }

        }

        //  System.out.println("************** "+ movieObservableList.get(movieArrayList.size()-1).getmovieID());
        /*   artistObservableList.stream()
                .filter(m-> m.getfirstName().equals("Harrison"))
                .map(t-> t.getMovieArrayList().add(mc))
                .forEach(i-> System.out.println("movie added  " + i.toString()));

         */
    }

    public void editTreeViewArtistName()
    {
        TreeItem<String> selectedItem = (TreeItem<String>) treeView.getSelectionModel().getSelectedItems();
        String[] parts = selectedItem.getValue().split(" ");
        /*  Stream<ArtistClass> filter = artistObservableList.stream()
                .filter(i->i.firstNameProperty().equals(parts[0])
                );
         System.out.println("nnnnn " + filter.toArray().toString());*/
        (artistObservableList.get(0).firstNameProperty()).bindBidirectional(selectedItem.valueProperty());

    }

 

    /*private String treeSelection(){

         // TreeItem<String> selectedItem =  (TreeItem<String>) treeView.getSelectionModel().getSelectedItem();
          //System.out.println("selectedItem " + selectedItem.toString().isEmpty());
          

         return selectedItem.getValue();
      }*/
    
    //To go to another page
    @FXML
    private void showMoviesMenu(ActionEvent event) throws IOException
    {
        //forwarding the scene to "DriverTaxiCheck.fxml" if all the boxes are filled
        Parent root2 = FXMLLoader.load(getClass().getResource("Movie_ArtistMenu.fxml"));
        Scene scene = new Scene(root2, 700, 500);
        Stage stage = (Stage) treeView.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Movie Page");
    }

    //A button to show all movies in the movie arrayList
    @FXML
    private void seeAllMoviesButton(ActionEvent event)
    {
        movieObservableList.clear();
        logic.getAllMovieArrayList().stream()
                .forEach(s -> movieObservableList.add(s));
    }

}
