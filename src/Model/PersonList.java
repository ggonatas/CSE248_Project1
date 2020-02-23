package Model;

import java.io.*;
import java.util.HashMap;

public class PersonList implements Serializable {
	private HashMap<String, Person> personlist;
    public static final Administrator ADMIN = new Administrator("admin", "adminPassword123");
    public static final GuestUser GUEST = new GuestUser("guest", "guestPassword123");

    public PersonList() {
        personlist =  new HashMap<String, Person>();
    }
    
    //Save personlist to file personlist.txt
    public void saveToFile(){
        try {
            FileOutputStream fOutStream = new FileOutputStream("personlist.txt");
            ObjectOutputStream oOutStream = new ObjectOutputStream(fOutStream);
            oOutStream.writeObject(personlist);
            oOutStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    //Load personlist from file personlist.txt
    public static PersonList loadFromFile(){
        PersonList newpersonlist = new PersonList();
        try {
            System.out.println(new File(".").getAbsoluteFile());
            FileInputStream fInStream = new FileInputStream("personlist.txt");
            ObjectInputStream oInStream = new ObjectInputStream(fInStream);
            newpersonlist.personlist = (HashMap<String, Person>) oInStream.readObject();
            oInStream.close();
        } catch (IOException | ClassNotFoundException e) {
            return new PersonList();
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

    //Check if person is in list based on UserID
    public boolean personInList(String userID){
        return personlist.containsKey(userID);
    }
    //Check if user is administrator
    public static boolean isAdmin(String userID, String password){
        return ADMIN.equals(userID) && ADMIN.verifyPassword(password);
    }
}
