/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artist_moviecatalog;

import java.util.ArrayList;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Befkadu Degefa
 */
public class ArtistClass
{
    private  IntegerProperty artistId;
    private  StringProperty firstName;
    private  StringProperty lastName;   
    private  IntegerProperty age;
    //private final IntegerProperty movieId;
    private ArrayList<MovieClass> movieArrayList;

      /**
     * Default constructor.
     */
    public ArtistClass(){
       this(0,null,null, 0);
    }
    
    //constructor
    public ArtistClass( int artistId, String firstName, String lastName, int age){
       this.firstName =  new SimpleStringProperty(firstName);
       this.lastName = new SimpleStringProperty(lastName);
       this.artistId = new SimpleIntegerProperty(artistId);
       this.age = new SimpleIntegerProperty(age);
      // this.movieId = new SimpleIntegerProperty(movieID);
      movieArrayList = new ArrayList<>();
    }
    

    
    //getters and setters
    public int getartistId(){
        return artistId.get();
    }
    
    public void setartistId(int id){
        artistId.set(id);
    }
    

    public String getfirstName()
    {
        return firstName.get();
    }
    
    public void setfirstName(String firstname){
        firstName.set(firstname);
    }
    
    public String getlastName()
    {
        return lastName.get();
    }
    public void setlastName(String lastname){
        lastName.set(lastname);
    }
     public int getage(){
        return age.get();
    }
     
     public void setAge(int actorAge){
         age.set(actorAge);
     }
    
 /*   public int getmovieId(){
        return movieId.get();
    }
    
    public void setmovieId(int movie_id){
        movieId.set(movie_id);
    }
  */  
    //property values
    public IntegerProperty artistIdProperty(){
        return artistId;
    }
    
    public StringProperty firstNameProperty(){
        if (firstName == null) {
            firstName = new SimpleStringProperty();
        }       
        return firstName;
    }
    
    public StringProperty lastNameProperty(){
        return lastName;
    }
    
    public IntegerProperty  ageProperty(){
        return age;
    }
    
   /* public IntegerProperty movieIdProperty(){
        return movieId;
    }
   */ 

    public ArrayList<MovieClass> getMovieArrayList()
    {
        return movieArrayList;
    }

    public void setMovieArrayList(int n, String name, String date)
    {
       movieArrayList.add(new MovieClass(n, name, date)) ;
    }

    @Override
    public String toString()
    {
        return "ID " + artistId;
    }
    
    
}
