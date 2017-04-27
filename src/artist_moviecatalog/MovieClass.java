/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artist_moviecatalog;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Befkadu Degefa
 */
public class MovieClass
{
    private IntegerProperty movieID;
    private StringProperty movieName;
    private StringProperty publishDate;
    
    public MovieClass(int id, String name, String date){
        this.movieID = new SimpleIntegerProperty(id);
        this.movieName = new SimpleStringProperty(name);
        this.publishDate = new SimpleStringProperty(date);
        
    }
    
    //getters and setters
    public int getmovieID(){
        return movieID.get();
    }
    
    public void setmovieID(int id){
        movieID.set(id);
    }
    
    public String getmovieName(){
        return movieName.get();
    }
    
    public void setmovieName(String name){
        movieName.set(name);
    }
    
    public String getpublishDate(){
        return publishDate.get();
    }
    
    public void setpublishDate(String date){
        publishDate.set(date);
    }
    
    //property values
    
    public IntegerProperty movieIDProperty(){
        return movieID;
    }
    
    public StringProperty movieNameProperty(){
        return movieName;
    }

    public StringProperty publishDateProperty()
    {
        return publishDate;
    }
    
    
}
