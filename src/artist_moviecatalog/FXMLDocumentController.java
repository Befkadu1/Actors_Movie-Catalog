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
import java.util.stream.Stream;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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
import javafx.scene.control.TreeItem.TreeModificationEvent;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.PropertyValueFactory;
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

    @FXML
    private MenuItem showAllMusics;

    public final String name = " ";

    private TreeItem<String> node;

    /* @FXML
    private TableView<ArtistClass> artistTableView;
    
    @FXML
    private TableColumn<ArtistClass, Integer> artistIDcolumn;
    
    @FXML
    private TableColumn<ArtistClass, String> firstNameColumn;
    
    @FXML
    private TableColumn<ArtistClass, String> lastNameColumn;
     */
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
    private TextField insertMovieName;

    @FXML
    private TextField insertMoviePublishDate;
    private int insertMoviePublishDateInt= 0;

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

    @FXML
    private void getOnMouseClickedOnArtistView(MouseEvent event)
    {
        /* treeView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue)
            {
              TreeItem<String> selectedItem = (TreeItem<String>) newValue;
                System.out.println("Selected Text : " + selectedItem.getValue());
            }
  
    });*/
        //lambda way
        treeView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue)
                -> 
                {
                    TreeItem<String> selectedItem = (TreeItem<String>) treeView.getSelectionModel().getSelectedItem();
                      if (selectedItem == null || selectedItem == node.getParent() )
        {
            System.out.println(" ");
        }
                      
                else{
                          
                      
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
                                    loadImage("harison.jpg");
                                    break;
                                case 2:
                                    loadImage("sean.jpg");
                                    break;
                                case 3:
                                    loadImage("anne.jpg");
                                    break;
                                case 4:
                                    loadImage("denzel.jpg");
                                    break;
                                default:
                                    System.out.println("hi");

                            }

                            System.out.println("selected artist's movieArrayList line 146 " + logic.getAllArtistsArrayList().get(j).getMovieArrayList().size());
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
        
        
        //hard coded objects of MovieClass
      /*  MovieClass movie1 = new MovieClass(1, "Indiana Jones ", "1989");
        MovieClass movie2 = new MovieClass(2, "Sabrina", "1995");
        MovieClass movie3 = new MovieClass(3, "Firewall", "2006");
        MovieClass movie4 = new MovieClass(4, "Six Days, Seven Nights", "1998");
        MovieClass movie5 = new MovieClass(5, "Finding Forrester", "2000");
        MovieClass movie6 = new MovieClass(6, "John Q", "2002");
        MovieClass movie7 = new MovieClass(7, "Flight", "2012");
        MovieClass movie8 = new MovieClass(8, "Fences", "2016");

        //hard coded objects of ArtistClass
        ArtistClass artist1 = new ArtistClass(1, "Harrison", "Ford", 74);
        artist1.getMovieArrayList().add(movie1);
        artist1.getMovieArrayList().add(movie2);
        artist1.getMovieArrayList().add(movie3);
        artist1.getMovieArrayList().add(movie4);
        ArtistClass artist2 = new ArtistClass(2, "Sean", "Connery", 86);
        artist2.getMovieArrayList().add(movie1);
        artist2.getMovieArrayList().add(movie5);
        ArtistClass artist3 = new ArtistClass(3, "Anne", "Heche", 48);
        artist3.getMovieArrayList().add(movie4);
        artist3.getMovieArrayList().add(movie6);

        ArtistClass artist4 = new ArtistClass(4, "Denzel", "Washington", 62);
        artist4.getMovieArrayList().add(movie6);
        artist4.getMovieArrayList().add(movie7);
        artist4.getMovieArrayList().add(movie8);

        movie1.getActorArrayList().add(artist1);
        movie2.getActorArrayList().add(artist1);
        movie2.getActorArrayList().add(artist2);
        movie3.getActorArrayList().add(artist1);
        movie4.getActorArrayList().add(artist1);
        movie4.getActorArrayList().add(artist3);
        movie5.getActorArrayList().add(artist2);
        movie6.getActorArrayList().add(artist3);
        movie6.getActorArrayList().add(artist4);
        movie7.getActorArrayList().add(artist4);
        movie8.getActorArrayList().add(artist4);
        artistArrayList.add(artist1);
        artistArrayList.add(artist2);
        artistArrayList.add(artist3);
        artistArrayList.add(artist4);*/
System.out.println("bbbbbbb" + logic.getAllArtistsArrayList().size());
for (int i = 0; i < logic.getAllArtistsArrayList().size();i++)
        {
            //logic.addAllActors(artist);
            artistObservableList.add(logic.getAllArtistsArrayList().get(i));
            
        }
       
        //saving all the movie objects in the movieArrayList
     /*   movieArrayList.add(movie1);
        movieArrayList.add(movie2);
        movieArrayList.add(movie3);
        movieArrayList.add(movie4);
        movieArrayList.add(movie5);
        movieArrayList.add(movie6);
        movieArrayList.add(movie7);
        movieArrayList.add(movie8);
*/
        //Saving all the movieArrayList into the movieObservableList
/*        for( MovieClass movie: logic.getAllMovieArrayList()){
            logic.addAllMovies(movie);
            movieObservableList.add(movie);
        }*/

         for(int i = 0; i < logic.getAllMovieArrayList().size(); i++){
             movieObservableList.add(logic.getAllMovieArrayList().get(i));
       
         }

        showTreeItems();
     

        /*
        //These codes are changed
        TreeItem<String> node2 = new TreeItem<>("Sean Connery");
        TreeItem<String> node3 = new TreeItem<>("Anne Heche");
        TreeItem<String> node4 = new TreeItem<>("Denzel Washington");
        root.getChildren().addAll(node1, node2, node3, node4);
        treeView.setRoot(root);
         */
        //Associate the data with the table columns
        // artistIDcolumn.setCellValueFactory(cellData -> cellData.getValue().artistIdProperty().asObject());
/*        artistIDcolumn.setCellValueFactory( new PropertyValueFactory<>("artistId"));
        firstNameColumn.setCellValueFactory( new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory( new PropertyValueFactory<>("lastName"));
        
        //to show the artist table
        artistTableView.setItems(artistObservableList);
         */
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
        searchMovie.textProperty().addListener((observable, oldValue, newValue) -> 
                {
                    //clearing the movieObservableList
                    movieObservableList.clear();
                    logic.getAllMovieArrayList().stream()
                            .filter(t -> t.getmovieName().toLowerCase().startsWith(searchMovie.getText().toLowerCase()))
                            .forEach(j -> movieObservableList.add(j));


        });

    }

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

    private void loadImage(String pic)
    {
        Image image = new Image(getClass().getResourceAsStream("images/" + pic));
        imageView.setImage(image);
    }

    //Edit the name column in the table
    @FXML
    public void handleEditMovieNameAction(TableColumn.CellEditEvent<MovieClass, String> t)
    {
        System.out.println("t.getOldValue() " + t.getOldValue() + " getNewValue() " + t.getNewValue());
        ((MovieClass) t.getTableView().getItems().get(t.getTablePosition().getRow())).setmovieName(t.getNewValue());

    }

    @FXML
    public void handleEditMoviePublishYearAction(TableColumn.CellEditEvent<MovieClass, String> t)
    {
        System.out.println("t.getOldValue() " + t.getOldValue() + " getNewValue() " + t.getNewValue());
        ((MovieClass) t.getTableView().getItems().get(t.getTablePosition().getRow())).setpublishDate(t.getNewValue());

    }


  /*  @FXML
    public void getOnMouseClickedOnMovieTable(ActionEvent event)
    {
        movieSelection = movieTableView.getSelectionModel().getSelectedItem();
        System.out.println("test " + event.getSource().toString().substring(10, 21));
        System.out.println("movieSelection " + movieSelection.getmovieName());

        //clearing the movieObservableList
        movieObservableList.clear();

        //if deleteMovie button clicked
        for (int j = 0; j < artistArrayList.size(); j++)
        {

            if (event.getSource().toString().substring(10, 21).equals("deleteMovie"))
            {
                for (int i = 0; i < artistArrayList.get(j).getMovieArrayList().size(); i++)
                {

                    if (movieSelection.getmovieID() == artistArrayList.get(j).getMovieArrayList().get(i).getmovieID())
                    {
                        System.out.println("before**" + artistArrayList.get(j).getMovieArrayList().get(i).getmovieID());
                        artistArrayList.get(j).getMovieArrayList().remove(movieSelection);
                        movieArrayList.remove(movieSelection);
                        //System.out.println("after******" + movieObservableList.get(0).getmovieName());
                        movieObservableList.add(artistArrayList.get(j).getMovieArrayList().get(i));
                    }

                }

            }

        }

        //displaying the movies on the movieTableView
        movieTableView.setItems(movieObservableList);
    }*/

    @FXML
    public void deleteMovieFromArtistArrayList(ActionEvent event)
    {
        TreeItem<String> selectedItem = (TreeItem<String>) treeView.getSelectionModel().getSelectedItem();
        movieSelection = movieTableView.getSelectionModel().getSelectedItem();
        
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete confirmation");
        alert.setHeaderText("You can not undo this!");
//        alert.setGraphic(new ImageView(this.getClass().getResource("warning.png").toString()));
        alert.setContentText("Are you sure you want to delete movie" + " " + movieSelection.getmovieName() + "?");
        Optional<ButtonType> action = alert.showAndWait();
        if(action.get()==ButtonType.OK){
            movieSelection = movieTableView.getSelectionModel().getSelectedItem();
        movieObservableList.remove(movieSelection);
     //   statusLabel.setText(selectArtist.getName() + " " + "has been deleted. ");
     //   if(selectArtist.getName()==null){
     //       statusLabel.setText(" ");
   //     }
        }
        
        //String actorSelection = movieTableView.getSelectionModel().getSelectedItem();
        System.out.println("event " + event.getSource().toString());
        if (selectedItem == null ||  movieSelection == null)
        {
            warningMessage.setText("Select the actor first");
        } else
        {
            warningMessage.setText(" "); //clearing the warning message
            System.out.println("first name" + firstName);
             if(logic.removeMovie(movieSelection.getmovieID(),actorId, movieSelection, firstName, lastName)){
                            movieObservableList.remove(movieSelection);
                        }
//            for (int i = 0; i < logic.getAllArtistsArrayList().size(); i++)
//            {
//
//                for (int j = 0; j < logic.getAllArtistsArrayList().get(i).getMovieArrayList().size(); j++)
//                {
//                    if (movieSelection.getmovieID() == artistArrayList.get(i).getMovieArrayList().get(j).getmovieID())
//                    {
                        //The movie should be deleted from the artisArrayList, movieArrayList 
                        //and also from the movieObservableList(This is to synchronize the view in the table
                        
//                        if(logic.removeMovie(movieSelection.getmovieID(), movieSelection)){
//                            movieObservableList.remove(movieSelection);
//                        }
                        
           //             artistArrayList.get(i).getMovieArrayList().remove(movieSelection);
           //             movieArrayList.remove(movieSelection);
            //            movieObservableList.remove(movieSelection);
//                    }
//
//                }
//
//            }

        }
    }

    public void insertMovie(ActionEvent event)
    {

        TreeItem<String> selectedItem = (TreeItem<String>) treeView.getSelectionModel().getSelectedItem();
        TreeItem <ArtistClass> selectedItemObject = (TreeItem<ArtistClass>)  treeView.getSelectionModel().getSelectedItem();
        if (selectedItem == null)
        {
            warningMessage.setText("Select the actor first");
        } else
        {
            MovieClass mc = new MovieClass();//initializing
            String[] parts = selectedItem.getValue().split(" ");
            int movieId = 0;
            boolean check = false;
            
            if(insertMovieName.getText().isEmpty()|| insertMoviePublishDate.getText().isEmpty()){
                warningMessage.setText("Write both movie name and publish date");
            }
            else if(!insertMovieName.getText().matches("^[A-zåäöÅÄÖ-]+$") && (!insertMoviePublishDate.getText().matches("^[0-9]*$"))){
                 warningMessage.setText("Write only letters");
            }
            
            else{
   
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
            if(check){
             warningMessage.setText("");
            movieObservableList.clear();
           // movieArrayList.addAll(logic.checkIfTheMovieExists(insertMovieName.getText(), insertMoviePublishDate.getText(), parts, movieId));
            movieObservableList.addAll(logic.checkIfTheMovieExists(insertMovieName.getText(), insertMoviePublishDate.getText(), parts, movieId, actorId, actorAge));
            
            }
            else{
                //When a new music to be added 
                   warningMessage.setText("");
                   movieObservableList.clear();
                   int newMovieId = (logic.getAllMovieArrayList().size()+1);
                   mc = new MovieClass(newMovieId, insertMovieName.getText(), insertMoviePublishDate.getText());
                   for (int i = 0; i < logic.getAllArtistsArrayList().size(); i++){
                       
               //to check the added movie in the selected person
               if(logic.getAllArtistsArrayList().get(i).getfirstName().equalsIgnoreCase(parts[0])
                       && logic.getAllArtistsArrayList().get(i).getlastName().equalsIgnoreCase(parts[1])){
                           //logic.getAllArtistsArrayList().get(i).getMovieArrayList().add(mc);
                           logic.addAllMovies(mc);

                           logic.getAllArtistsArrayList().get(i).setMovieArrayList(mc);
                           //logic.addArtistInMusicArray(actorId, parts[0], parts[1], actorAge);
                           //movieArrayList.addAll(logic.getAllArtistsArrayList().get(i).getMovieArrayList());
                           movieObservableList.addAll(logic.getAllArtistsArrayList().get(i).getMovieArrayList());
                       } 
                     
                   }
                   
                   //Adding the person in to the new movie arrayList
                   for(int i = 0; i< logic.getAllMovieArrayList().size();i++){
                       if(logic.getAllMovieArrayList().get(i).getmovieID()==newMovieId){
                           logic.getAllMovieArrayList().get(i).getActorArrayList().add(new ArtistClass(actorId, parts[0], parts[1], actorAge));
                       }
                   }

                }
             }catch (Exception e)
            {
                warningMessage.setText("Fill a number in the publish date box");
            }
            }
            /* if (check)
            {
                movieObservableList.clear();//Clearing the movieObservableList 
                mc = new MovieClass(id, insertMovieName.getText(), insertMoviePublishDate.getText());
                for (int i = 0; i < artistArrayList.size(); i++)
                {

                    //If the movie is already exist, no need of setting a new ID
                    if (artistArrayList.get(i).getfirstName().equals(parts[0])
                            && artistArrayList.get(i).getlastName().equals(parts[1]))
                    {

                        //If the movie is already exist in other actor's arrayList
                        artistArrayList.get(i).setMovieArrayList(mc);
                        for (int j = 0; j < artistArrayList.get(i).getMovieArrayList().size(); j++)
                        {
                            movieObservableList.add(artistArrayList.get(i).getMovieArrayList().get(j)); //To show on the display table, sync
                        }

                    }
                }
            } else
            {

                for (int i = 0; i < artistObservableList.size(); i++)
                {
                    if (artistObservableList.get(i).getfirstName().equals(parts[0])
                            && artistObservableList.get(i).getlastName().equals(parts[1]))
                    {
                        mc = new MovieClass((movieArrayList.size() + 1), insertMovieName.getText(), insertMoviePublishDate.getText());
                        //artistObservableList.get(i).setMovieArrayList((movieArrayList.size()+1), insertMovieName.getText(),  insertMoviePublishDate.getText());
                        movieArrayList.add(mc); //Adding to the movieArrayList
                        movieObservableList.add(movieArrayList.get(movieArrayList.size() - 1)); //To show on the display table, sync

                    }
                }

            }*/

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
        System.out.println("---------" + selectedItem.getValue());
        /*  Stream<ArtistClass> filter = artistObservableList.stream()
                .filter(i->i.firstNameProperty().equals(parts[0])
                );
         System.out.println("nnnnn " + filter.toArray().toString());*/
        (artistObservableList.get(0).firstNameProperty()).bindBidirectional(selectedItem.valueProperty());

    }

    private TreeItem<ArtistClass> createTreeItem(ArtistClass person)
    {
        TreeItem<ArtistClass> treeItem = new TreeItem<>(person);
        ChangeListener<String> nameListener = (obs, oldName, newName) -> 
                {
                    TreeModificationEvent<ArtistClass> event = new TreeModificationEvent<>(TreeItem.valueChangedEvent(), treeItem);
                    Event.fireEvent(treeItem, event);
        };
        person.firstNameProperty().addListener(nameListener);
        treeItem.valueProperty().addListener((obs, oldValue, newValue) -> 
                {
                    if (oldValue != null)
                    {
                        oldValue.firstNameProperty().removeListener(nameListener);
                    }
                    if (newValue != null)
                    {
                        newValue.firstNameProperty().addListener(nameListener);
                    }
        });
        return treeItem;
    }

    /*private String treeSelection(){

         // TreeItem<String> selectedItem =  (TreeItem<String>) treeView.getSelectionModel().getSelectedItem();
          //System.out.println("selectedItem " + selectedItem.toString().isEmpty());
          

         return selectedItem.getValue();
      }*/
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

    @FXML
    private void seeAllMoviesButton(ActionEvent event)
    {
        movieObservableList.clear();
        logic.getAllMovieArrayList().stream()
                .forEach(s -> movieObservableList.add(s));
    }

}
