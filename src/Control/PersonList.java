package Control;

import java.io.*;
import java.util.Collection;
import java.util.HashMap;
import Model.Person;
import Model.Name;

public class PersonList implements Serializable {
	private HashMap<String, Person> personlist;

    public PersonList(){
        personlist =  new HashMap<String, Person>();
    }
    
    //Save personlist to file personlist.sav
    public void saveToFile(){
        try {
            FileOutputStream fOutStream = new FileOutputStream("personlist.sav");
            ObjectOutputStream oOutStream = new ObjectOutputStream(fOutStream);
            oOutStream.writeObject(personlist);
            oOutStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    //Load personlist from file personlist.sav
    public static PersonList loadFromFile(){
        PersonList newpersonlist = new PersonList();
        try {
            FileInputStream fInStream = new FileInputStream("personlist.sav");
            ObjectInputStream oInStream = new ObjectInputStream(fInStream);
            newpersonlist.personlist = (HashMap<String, Person>) oInStream.readObject();
            oInStream.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return newpersonlist;
    }
    
    //Add person to personlist
    //Returns false if userID already in personlist
    //Otherwise puts person in hashmap with userID as the key and returns true
    public boolean addToPersonList(Person person){
        if(personlist.containsKey(person.getUserID())){
            return false;
        }
        personlist.put(person.getUserID(), person);
        return true;
    }
    
    //Delete person with specified userID from personlist
    public boolean removeFromPersonList(String userID){
        Person person;
        person = personlist.get(userID);
        if(person.getUserID().equals(userID)) {
            personlist.remove(userID, person); 
            return true;
        }
        return false;
    }
    //Update person
    public boolean updatePerson(Person updatedperson){
        String userID = updatedperson.getUserID();
        Person person = personlist.get(userID);
        return personlist.replace(userID, person, updatedperson);
    }
    //Get a person in personlist by userID
    public Person getPerson(String userID){
        return personlist.get(userID);
    }

}
