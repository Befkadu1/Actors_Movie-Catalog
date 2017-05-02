/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artist_moviecatalog;

import static artist_moviecatalog.Movie_ArtistMenuController.movieNameObservableList;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Befkadu Degefa
 */
public class Logic
{
    private static List<ArtistClass> artistClassArrayList;
    private static List<MovieClass> movieClassArrayList;
    //public static ObservableList<ArtistClass> artistClassObservableList;
    private static Logic instance; //Step 2 declare the instance variabel
    private Logic() //Step 1 declare the constructor and change it to private
    {
        artistClassArrayList = new ArrayList<>();
        movieClassArrayList = new ArrayList<>();
        //artistClassObservableList = FXCollections.observableArrayList();
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
        ArtistClass artist3 = new ArtistClass(3, "Anne", "Heche", 48);
        artist3.getMovieArrayList().add(movie4);
        artist3.getMovieArrayList().add(movie6);

        ArtistClass artist4 = new ArtistClass(4, "Denzel", "Washington", 62);
        artist4.getMovieArrayList().add(movie6);
        artist4.getMovieArrayList().add(movie7);
        artist4.getMovieArrayList().add(movie8);

        movie1.getActorArrayList().add(artist1);//Indiana Jones has Harrison
        movie1.getActorArrayList().add(artist2);//Indiana Jones has Sean
        movie2.getActorArrayList().add(artist1);//Sabrina has Harrison
        movie3.getActorArrayList().add(artist1);
        movie4.getActorArrayList().add(artist1);
        movie4.getActorArrayList().add(artist3);
        movie5.getActorArrayList().add(artist2);
        movie6.getActorArrayList().add(artist3);
        movie6.getActorArrayList().add(artist4);
        movie7.getActorArrayList().add(artist4);
        movie8.getActorArrayList().add(artist4);
        artistClassArrayList.add(artist1);
        artistClassArrayList.add(artist2);
        artistClassArrayList.add(artist3);
        artistClassArrayList.add(artist4);
        

        movieClassArrayList.add(movie1);
        movieClassArrayList.add(movie2);
        movieClassArrayList.add(movie3);
        movieClassArrayList.add(movie4);
        movieClassArrayList.add(movie5);
        movieClassArrayList.add(movie6);
        movieClassArrayList.add(movie7);
        movieClassArrayList.add(movie8);
    }
    
   public static Logic getInstance() //Step 3 write getInstance method
    {
        if (instance == null) {
            instance = new Logic();
        }
        return instance;
    }
   

   
   public List<MovieClass> getAllMovieArrayList() {
        return movieClassArrayList;
    }
   
  /* public ObservableList <ArtistClass> getObsAllMovieArrayList() {
       artistClassObservableList.addAll(getAllArtistsArrayList());
       
        return artistClassObservableList;
    }*/
   
 public List<ArtistClass> getAllArtistsArrayList() {

        return artistClassArrayList;
    }

   public void addAllActors(ArtistClass addArtist) {
               //hard coded objects of MovieClass

        
        artistClassArrayList.add(addArtist);
    }
   
   public void addAllMovies(MovieClass addMovies) {

        movieClassArrayList.add(addMovies);
    }
   
   public boolean removeMovie(int movieId, int actorId, MovieClass toBeRemovedMovie, String firstName,String lastName){
            boolean removeMovie = false;
            for (int i = 0; i < getAllArtistsArrayList().size(); i++)
            {
                if(actorId ==  getAllArtistsArrayList().get(i).getartistId()){ //to remove for the selected actor
                for (int j = 0; j < getAllArtistsArrayList().get(i).getMovieArrayList().size(); j++)
                {
                    if(movieId== getAllArtistsArrayList().get(i).getMovieArrayList().get(j).getmovieID()){
                        getAllArtistsArrayList().get(i).getMovieArrayList().remove(toBeRemovedMovie);   
                        //getAllArtistsArrayList().get(i).remove(toBeRemovedMovie);
                        removeMovie = true;
                    }
                }
                }
            }

            //Removing the actor from the deleted movie arrayList        

          for (int i = 0; i<getAllMovieArrayList().size(); i++)
                {
                    if(movieId== getAllMovieArrayList().get(i).getmovieID()){
                        for( int j= 0; j < getAllMovieArrayList().get(i).getActorArrayList().size(); j++){
                            if(actorId == getAllMovieArrayList().get(i).getActorArrayList().get(j).getartistId()){
                                getAllMovieArrayList().get(i).getActorArrayList().remove( getAllMovieArrayList().get(i).getActorArrayList().get(j));
                            }
                        }
                    }
                    
                }
        
            
       return removeMovie;        
   }
   
   public List<MovieClass> checkIfTheMovieExists(String movieName, String publishDate, String [] nameOfArtist, int movieId, int actorId, int actorAge){
       boolean notExisted = true;
       MovieClass mc = new MovieClass();
       List<MovieClass> addMusic = new ArrayList<>();
        for (int i = 0; i < getAllArtistsArrayList().size(); i++)
           {
               //to check the added movie in the selected person
               if(getAllArtistsArrayList().get(i).getfirstName().equalsIgnoreCase(nameOfArtist[0])
                       && getAllArtistsArrayList().get(i).getlastName().equalsIgnoreCase(nameOfArtist[1])){
                   
                   for(int j = 0 ; j < getAllArtistsArrayList().get(i).getMovieArrayList().size(); j++){
                       //check if the movie does exist in the selected actor
                       if(movieName.equalsIgnoreCase(getAllArtistsArrayList().get(i).getMovieArrayList().get(j).getmovieName())
                               && publishDate.equalsIgnoreCase(getAllArtistsArrayList().get(i).getMovieArrayList().get(j).getpublishDate())){
                           addMusic.addAll(getAllArtistsArrayList().get(i).getMovieArrayList());
                           notExisted = false;
                       }
                   }
                  
               }

               }
        
                       
               //if the movie doesn't exist in the selected actor it will go to other actors
               //but if it does exist in other actor's movieArrayList
               if(notExisted)
               { System.out.println("if  " + notExisted);
                   mc = new MovieClass(movieId, movieName, publishDate);
                   for (int i = 0; i < getAllArtistsArrayList().size(); i++){
               if(getAllArtistsArrayList().get(i).getfirstName().equalsIgnoreCase(nameOfArtist[0])
                       && getAllArtistsArrayList().get(i).getlastName().equalsIgnoreCase(nameOfArtist[1])){
                   
                           getAllArtistsArrayList().get(i).getMovieArrayList().add(mc);
                               addMusic.addAll(getAllArtistsArrayList().get(i).getMovieArrayList());
                               
                               
                               break;
                       } 
                     
                   }
                   
                   //Adding the person in to the new movie arrayList
                   for(int i = 0; i< getAllMovieArrayList().size();i++){
                       if(getAllMovieArrayList().get(i).getmovieID()==movieId){
                           getAllMovieArrayList().get(i).getActorArrayList().add(new ArtistClass(actorId, nameOfArtist[0], nameOfArtist[1], actorAge));
                       }
                   }
                       
                }

       return addMusic;
           }

   /***********************************************************************************/
   public List<ArtistClass> checkIfTheActorExists(String firstName, String lastName, String nameOfMovie, int age , int artistId){
       boolean notExisted = true;
       ArtistClass ac = new ArtistClass();
       boolean check=false;
       int movieId = 0;
       String publishDate = null;
       List<ArtistClass> addActor = new ArrayList<>();
        for (int i = 0; i < getAllMovieArrayList().size(); i++)
           {
               //to check the added actor is already in the selected movieArrayList
               if(getAllMovieArrayList().get(i).getmovieName().equals(nameOfMovie)){
                   movieId = getAllMovieArrayList().get(i).getmovieID();
                   publishDate = getAllMovieArrayList().get(i).getpublishDate();
                    for (int j = 0; j < getAllMovieArrayList().get(i).getActorArrayList().size(); j++){
                        
                       //check if the movie does exist in the selected actor
                       if(firstName.equalsIgnoreCase(getAllMovieArrayList().get(i).getActorArrayList().get(j).getfirstName())
                               && lastName.equalsIgnoreCase(getAllMovieArrayList().get(i).getActorArrayList().get(j).getlastName())
                               && age== (getAllMovieArrayList().get(i).getActorArrayList().get(j).getage())){
                           addActor.addAll(getAllMovieArrayList().get(i).getActorArrayList());
                           notExisted = false;
                       }
                   }
 
                  
               }

               }
   
        //if the person doesn't exist in the selected movie it will search his ID in other moviesArrayList.
        //Because the ID should be the same
               if(notExisted)
               { System.out.println("if  " + notExisted);
                   ac = new ArtistClass(artistId, firstName, lastName, age);
                   for (int i = 0; i < getAllMovieArrayList().size(); i++){
                       //check if the movie does exist in the selected actor
                         if(getAllMovieArrayList().get(i).getmovieName().equals(nameOfMovie))
                      {
                   
                           getAllMovieArrayList().get(i).getActorArrayList().add(ac);
                           //getAllMovieArrayList().get(i).getActorArrayList().add(addActor.get(i));
                               addActor.addAll(getAllMovieArrayList().get(i).getActorArrayList());
                               break;
                     
                     
                   }
                   }
                                      //Adding the person in to the new movie arrayList
                   for(int i = 0; i< getAllArtistsArrayList().size();i++){
                       if(getAllArtistsArrayList().get(i).getartistId()==artistId){
                           getAllArtistsArrayList().get(i).getMovieArrayList().add(new MovieClass(movieId,nameOfMovie , publishDate));
                       }
                   }
                }                       


        return addActor;
   }
   
public void deleteActorFromMovieArrayList(int movieId, int actorId){
    
     for (int i = 0; i < getAllMovieArrayList().size(); i++)
            {
                if(movieId== getAllMovieArrayList().get(i).getmovieID()){
                for (int j = 0; j < getAllMovieArrayList().get(i).getActorArrayList().size(); j++)
                {
                   if (actorId == getAllMovieArrayList().get(i).getActorArrayList().get(j).getartistId())
                        {
                            //warningMessageLabel.setText(" ");
                            getAllMovieArrayList().get(i).getActorArrayList().remove(getAllMovieArrayList().get(i).getActorArrayList().get(j));
                            //logic.getAllArtistsArrayList().remove(actorSelection);
                            //artistObservableList.remove(actorSelection);
                            
                            
                        }

                    }
            }


            }
            for(int i= 0; i< getAllArtistsArrayList().size(); i++){
                if(actorId == getAllArtistsArrayList().get(i).getartistId()){
                    for(int j = 0 ; j < getAllArtistsArrayList().get(i).getMovieArrayList().size(); j++){
                        if(movieId==getAllArtistsArrayList().get(i).getMovieArrayList().get(j).getmovieID() ){
                            
                           getAllArtistsArrayList().get(i).getMovieArrayList().remove(getAllArtistsArrayList().get(i).getMovieArrayList().get(j));
                        }
                    }
                }    
           }
    
}
}
