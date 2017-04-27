/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artist_moviecatalog;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author Befkadu Degefa
 */
public class FXMLDocumentController implements Initializable
{
    
    @FXML
    private Label showName;
    
    @FXML
    private Label showAge;
    
    @FXML
    private Button deleteMovie;
    
    @FXML
    private TreeView treeView;
    
    @FXML
    private MenuItem showAllMusics;
    
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
    
    //ArrayList to save all artists
    public static ArrayList<ArtistClass> artistArrayList = new ArrayList<>();
    
    //ArrayList to save all movies
    public static ArrayList<MovieClass> movieArrayList = new ArrayList<>();
    
    //ObservableList to save artists
    public static ObservableList<ArtistClass> artistObservableList ;
    
    //ObservableList to save movies
    public static ObservableList<MovieClass> movieObservableList;
    
    public static MovieClass movieSelection; //A string to show when the user select the artist table,artistTableView
    
    
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
        treeView.getSelectionModel().selectedItemProperty().addListener(( observable,  oldValue,  newValue)-> {
              TreeItem<String> selectedItem = (TreeItem<String>) treeView.getSelectionModel().getSelectedItem();
              System.out.println("Selected Text : " + selectedItem.getValue());
              
              System.out.println("Movie array size in tree " + movieArrayList.size());
              
              //Spliting first name and last name
              String[] parts = selectedItem.getValue().split(" ");
              
              //Show the actor's full name
              showName.setText(parts[0] + " " + parts[1]);
              
              //clearing the movieObservableList
                movieObservableList.clear();

                for(int j = 0; j< artistArrayList.size(); j++){  
                    
                    
                   
                    //filtering the selected actor
                   if(artistArrayList.get(j).getfirstName().equalsIgnoreCase(parts[0])
                           && artistArrayList.get(j).getlastName().equalsIgnoreCase(parts[1])){
                       showAge.setText(Integer.toString(artistArrayList.get(j).getage()));
                     int id =  artistArrayList.get(j).getartistId();
                        //loading the image for the specific actor
                    switch(id){
                        case 1: loadImage("harison.jpg");
                        break;
                        case 2 : loadImage("sean.jpg");
                        break;
                        case 3 : loadImage("anne.jpg");
                        break;
                        case 4 : loadImage("denzel.jpg");
                        break;
                        default: System.out.println("hi");
                        
                    }
                       System.out.println("sizeeee line 146 " + artistArrayList.get(j).getMovieArrayList().size()); 
                       for(int i = 0; i < artistArrayList.get(j).getMovieArrayList().size(); i++){
                           movieObservableList.add(artistArrayList.get(j).getMovieArrayList().get(i));
                           
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
        
        //Tree items
        TreeItem<String> root = new TreeItem<>("Actors");
        TreeItem<String> node1 = new TreeItem<>("Harrison Ford");
        TreeItem<String> node2 = new TreeItem<>("Sean Connery");
        TreeItem<String> node3 = new TreeItem<>("Anne Heche");
        TreeItem<String> node4 = new TreeItem<>("Denzel Washington");
        root.getChildren().addAll(node1, node2, node3, node4);
        treeView.setRoot(root);
        
        //hard coded objects of MovieClass
        MovieClass movie1 = new MovieClass(1, "Indiana Jones ", "1989");
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
        ArtistClass artist3 = new ArtistClass(3, "Anne", "Heche",48);
        artist3.getMovieArrayList().add(movie4);
        artist3.getMovieArrayList().add(movie6);

        ArtistClass artist4 = new ArtistClass(4, "Denzel", "Washington",62);
        artist4.getMovieArrayList().add(movie6);
        artist4.getMovieArrayList().add(movie7);
        artist4.getMovieArrayList().add(movie8);
        artistArrayList.add(artist1);
        artistArrayList.add(artist2);
        artistArrayList.add(artist3);
        artistArrayList.add(artist4);
        

        
        for(ArtistClass artist : artistArrayList){
            artistObservableList.add(artist);
        }
          
        

        
        //saving all the movie objects in the movieArrayList
        movieArrayList.add(movie1);
        movieArrayList.add(movie2);
        movieArrayList.add(movie3);
        movieArrayList.add(movie4);
        movieArrayList.add(movie5);
        movieArrayList.add(movie6);
        movieArrayList.add(movie7);
        movieArrayList.add(movie8);
        
        //Saving all the movieArrayList into the movieObservableList
        for(MovieClass movie : movieArrayList){
            movieObservableList.add(movie);
        }
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
    }    
    
  
    private void loadImage(String pic){
        Image image = new Image(getClass().getResourceAsStream("images/" + pic));
        imageView.setImage(image); 
    }
    
     //Edit the name column in the table
    @FXML
    public void handleEditMovieNameAction (TableColumn.CellEditEvent<MovieClass, String>t){
        System.out.println("t.getOldValue() " + t.getOldValue()+ " getNewValue() " + t.getNewValue());
       ((MovieClass) t.getTableView().getItems().get( t.getTablePosition().getRow()) ).setmovieName(t.getNewValue());       

    }
    
    @FXML
    public void handleEditMoviePublishYearAction (TableColumn.CellEditEvent<MovieClass, String>t){
        System.out.println("t.getOldValue() " + t.getOldValue()+ " getNewValue() " + t.getNewValue());
       ((MovieClass) t.getTableView().getItems().get( t.getTablePosition().getRow()) ).setpublishDate(t.getNewValue());       

    }
    //9-20
        @FXML
    public void getOnMouseClickedOnMovieTable(ActionEvent event)
    {
        movieSelection = movieTableView.getSelectionModel().getSelectedItem();
        System.out.println("test " + event.getSource().toString().substring(10, 21));
        System.out.println("movieSelection " + movieSelection.getmovieName());
        
                        //clearing the movieObservableList
                movieObservableList.clear();

        //if deleteMovie button clicked
        for(int j = 0; j< artistArrayList.size();j++){
            
       
        if(event.getSource().toString().substring(10, 21).equals("deleteMovie")){
            for(int i = 0; i< artistArrayList.get(j).getMovieArrayList().size(); i++){
                
                
                        if(movieSelection.getmovieID()==artistArrayList.get(j).getMovieArrayList().get(i).getmovieID()){
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
    }
    
}
