
import java.util.ArrayList;
import java.util.Comparator;
import javax.swing.JOptionPane;
import jdk.nashorn.internal.objects.NativeArray;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Le_Juston
 */
public class CompassionCompanion {
    public static int date = 180415;    //YYMMDD
    public static void main(String[] args) {
        JOptionPane.showMessageDialog(null, "This main method shows how the logic of our matching algorithm works by calculating the score between a volunteer and an event.");
        String volunteerName;
        int volunteerAge;
        int volunteerGender;
        int maxDistance;
        String volunteerLocation;
        volunteerName = JOptionPane.showInputDialog("What is the volunteer's name?");
        volunteerAge = Integer.parseInt(JOptionPane.showInputDialog("What is the volunteer's age?"));
        volunteerGender = Integer.parseInt(JOptionPane.showInputDialog("What is the volunteer's gender? 1 for male, 2 for female, 3 for trans-male, and 4 for trans-female."));
        volunteerLocation = JOptionPane.showInputDialog("What is the longitude and latitude of the volunteer? Separate the two values with a space.");
        maxDistance = Integer.parseInt(JOptionPane.showInputDialog("What is the maximum distance allowed to travel?"));
        double volunteerLongitude = Double.parseDouble(volunteerLocation.substring(0, volunteerLocation.indexOf(' ')));
        double volunteerLatitude = Double.parseDouble(volunteerLocation.substring(volunteerLocation.indexOf(' ')+1));
        while (true){
            String eventName;
            String eventDay;           //YYMMDD
            String eventLocation;
            String eventAgeRange;
            String requestedGender;
            String reputation;
            eventName = JOptionPane.showInputDialog("What is the name of your event?");
            eventDay = JOptionPane.showInputDialog("What day is the event? (Use format YYMMDD)");
            eventLocation = JOptionPane.showInputDialog("What is the longitude and latidue of the event. Separate the two values with a space.");
            eventAgeRange = JOptionPane.showInputDialog("What is the minimum and maximum ages allowed to volunteer at the event? Separate the two values with a space.");
            requestedGender = JOptionPane.showInputDialog("What gender do you want volunteering? Input 0 if all genders are allowed, 1 if males, 2 if females, 3 if trans-males, 4 if trans-females.");
            reputation = JOptionPane.showInputDialog("What is the reputation value of the organization running the event? Enter a value between 0 and 100.");
            int eventDate = Integer.parseInt(eventDay);
            double eventLongitude = Double.parseDouble(eventLocation.substring(0, eventLocation.indexOf(' ')));
            double eventLatitude = Double.parseDouble(eventLocation.substring(eventLocation.indexOf(' ')+1));
            int eventMinAge = Integer.parseInt(eventAgeRange.substring(0, eventLocation.indexOf(' ')));
            int eventMaxAge = Integer.parseInt(eventAgeRange.substring(eventLocation.indexOf(' ')+1));
            int eventGender = Integer.parseInt(requestedGender);
            int rep = Integer.parseInt(reputation);
            double distanceScore = distance(eventLongitude, eventLatitude, volunteerLongitude, volunteerLatitude, maxDistance);
            double dateScore = date(eventDate);
            double repScore = rating(rep);
            double ageScore = age(eventMinAge, eventMaxAge, volunteerAge);
            double genderScore = gender(eventGender, volunteerGender);
            double score = distanceScore*10+dateScore*15+repScore*20+ageScore*10+genderScore*20;
            JOptionPane.showMessageDialog(null, "The score matching " + volunteerName + " to " + eventName + " is: " + score);
            boolean exit = JOptionPane.showInputDialog("If you would like to stop, type \"EXIT\"").equals("EXIT");
        }
    }
    private static double distance(double eventLongitude, double eventLatitude, double userLongitude, double userLatitude, int maxDistance) {
        double distance = Math.pow(userLatitude-eventLatitude, 2)+Math.pow(userLongitude, eventLongitude);
        return (1-distance/maxDistance);
    }
    private static double date(int eventDate){
        int today = CompassionCompanion.date;
        int dateDifference = eventDate-today;
        if (dateDifference < 0){
            return -200;
        }
        else if(dateDifference >= 0 && dateDifference < 4){
            double partialScore = Math.pow(dateDifference, 2)-2*dateDifference-15;
            partialScore = partialScore/(-16);
            return partialScore;
        }
        else{
            double partialScore = Math.pow(dateDifference, 2)-24*dateDifference/7;
            partialScore = 1/partialScore;
            return partialScore;
        }
    }
    private static double rating(int reputation){
        return ((double)reputation)/100;
    }
    private static double age(int minimumAge, int maximumAge, int userAge){
        if (userAge >= minimumAge && userAge <= maximumAge){
            return 1;
        }
        else if (userAge < minimumAge){
            return -Math.pow(userAge-minimumAge, 2);
        }
        else {
            return -Math.pow(userAge-maximumAge, 2);
        }
    }
    private static double gender(int requiredGender, int actualGender){
        if (requiredGender==0||requiredGender==actualGender){
            return 0;
        }
        else return -1;
    }
            
}
class Volunteer{
    String name;
    int maxDistance;
    int gender;     //1 is male, 2 is female, 3 is trans-male, 4 is trans-female
    int birthday;
    int age;
    String description;
    ArrayList <String> preferences;
    ArrayList <PersonalEvent> recommendedEvents;
    int latitude;
    int longitude;
    private Comparator<? super PersonalEvent> cmprtr;
    public void getScores(){
        for (int i = 0; i < recommendedEvents.size(); i++) {
            int currentScore = 0;
            double distanceScore = distance(recommendedEvents.get(i));
            double dateScore = date(recommendedEvents.get(i));
            double ratingScore = rating(recommendedEvents.get(i));
            double ageScore = age(recommendedEvents.get(i));
            double genderScore = gender(recommendedEvents.get(i));
            double totalScore = distanceScore*10+dateScore*15+ratingScore*20+ageScore*10+genderScore*20;
        }
    }
    public void sortEvents(){
        recommendedEvents.sort(cmprtr);
    }

    private double distance(PersonalEvent myEvent) {
        int eventLatitude = accessEvent(myEvent.eventID).latitude;
        int eventLongitude = accessEvent(myEvent.eventID).longitude;
        int userLatitude = this.latitude;
        int userLongitude = this.longitude;
        double distance = Math.pow(userLatitude-eventLatitude, 2)+Math.pow(userLongitude, eventLongitude);
        return (1-distance/maxDistance);
    }
    private double date(PersonalEvent myEvent){
        int eventDate = accessEvent(myEvent.eventID).date;
        int today = CompassionCompanion.date;
        int dateDifference = eventDate-today;
        if (dateDifference < 0){
            return -200;
        }
        else if(dateDifference >= 0 && dateDifference < 4){
            double partialScore = Math.pow(dateDifference, 2)-2*dateDifference-15;
            partialScore = partialScore/(-16);
            return partialScore;
        }
        else{
            double partialScore = Math.pow(dateDifference, 2)-24*dateDifference/7;
            partialScore = 1/partialScore;
            return partialScore;
        }
    }
    private double rating(PersonalEvent myEvent){
        return accessOrg(accessEvent(myEvent.eventID).orgID).reputation/100;
    }
    private double age(PersonalEvent myEvent){
        int userAge = age;
        int minimumAge = accessEvent(myEvent.eventID).minAge;
        int maximumAge = accessEvent(myEvent.eventID).maxAge;
        if (userAge >= minimumAge && userAge <= maximumAge){
            return 1;
        }
        else if (userAge < minimumAge){
            return -Math.pow(userAge-minimumAge, 2);
        }
        else {
            return -Math.pow(userAge-maximumAge, 2);
        }
    }
    private double gender(PersonalEvent myEvent){
        int requiredGender = accessEvent(myEvent.eventID).requestedGender;    //0 is unspecified, 1 is male, 2 is female, 3 is trans-male, 4 is trans-female;
        if (requiredGender==0||requiredGender==gender){
            return 0;
        }
        else return -1;
    }
    private Event accessEvent(int eventID){
        return new Event();         //stand in method for accessing the Event from the database.
    }
    private Organization accessOrg (int orgID){
        return new Organization();  //stand in method for accessing the Org from the database.
    }
    
}

class Organization{
    int orgID, eventID;
    String name;
    ArrayList <Integer> eventIDs;
    int reputation;
    ArrayList <String> orgValues;
}
class Event{
    String name;
    int eventID, orgID;
    int date;           //YYMMDD
    int latitude;
    int longitude;
    int minAge;
    int maxAge;
    int requestedGender;
}
class PersonalEvent{
    int eventID;
    int eventScore;
}