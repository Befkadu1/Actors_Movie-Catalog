/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artist_moviecatalog;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Befkadu Degefa
 */
public class Logic
{
    private static List<ArtistClass> artistClassArrayList;
    private static List<MovieClass> movieClassArrayList;
    private static Logic instance; //Step 2 declare the instance variabel
    private Logic() //Step 1 declare the constructor and change it to private
    {
        artistClassArrayList = new ArrayList<>();
    }
    
   public static Logic getInstance() //Step 3 write getInstance method
    {
        if (instance == null) {
            instance = new Logic();
        }
        return instance;
    }
   
   public static List<ArtistClass> getAllArtistsArrayList() {
        return artistClassArrayList;
    }
   
   public static List<MovieClass> getAllMovieArrayList() {
        return movieClassArrayList;
    }
   
  /* public static ArrayList<MovieClass> getAllMoviesArrayList(int actorsID) {
       return getAllAccountArrayList(actorsID);
    }
  */ 
   public List<ArtistClass> removeArtist(int id) {
        ArrayList<ArtistClass> removedCustomerList = new ArrayList<>();
 /*       for (int i = 0; i < getAllArtistsArrayList().size(); i++) {
            if (getAllArtistsArrayList().get(i).getartistId() == id) {

                for (int k = 0; k < getAllAccountsArrayList(pNr).size(); k++) {
                    removedCustomerList.add(getAllAccountsArrayList(pNr).get(k).toStringClose());
                }
                if (bankLogicRepository.removeCustomer(pNr)) { // Kalla på repository metoden och kolla så den är true(tagit bort kund)

                    break;
                }
            }
        }*/
        return removedCustomerList;
    }
   
   
}
