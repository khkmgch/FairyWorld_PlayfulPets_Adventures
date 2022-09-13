import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Date;
import java.text.SimpleDateFormat;

class RandomWrapper{
    public static double getRanDouble(double min, double max){
        Random r = new Random();
        return r.nextDouble() * (max - min) + min;
    }
    public static boolean ranBoolean(){
        return new Random().nextBoolean();
    }
}
class Name{
    private String firstName;
    private String lastName;

    public Name(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }
    public String toString(){
        return this.firstName + " " + this.lastName;
    }
}
class BMI{
    private double heightM;
    private double weightKg;

    public BMI(double heightM, double weightKg){
        this.heightM = heightM;
        this.weightKg = weightKg;
    }
    public double getHeightM(){
        return this.heightM;
    }
    public double getValue(){
        return this.weightKg / (this.heightM * this.heightM);
    }
    public String toString(){
        return "heightM: " + this.getHeightM() + ", weightKg: " + this.weightKg + ", BMI: " + this.getValue();
    }
}
class Animal{
    protected String species;
    protected BMI bmi;
    protected double lifeSpanDays;
    protected String biologicalSex;
    protected Date spawnTime;
    protected Date deathTime;
    protected int hungerPercent = 100;
    protected int sleepPercent = 100;

    public Animal(String species, double heightM, double weightKg, double lifeSpanDays, String biologicalSex){
        this.species = species;
        this.bmi = new BMI(heightM, weightKg);
        this.lifeSpanDays = lifeSpanDays;
        this.biologicalSex = biologicalSex;
        this.spawnTime = new java.util.Date();
    }

    public void eat(){
        if(!this.isAlive())return;
        this.hungerPercent = 0;
    }
    public void setAsHungry(){
        if(!this.isAlive())return;
        this.hungerPercent = 100;
    }
    public boolean isHungry(){
        return this.hungerPercent >= 70;
    }
    public void sleep(){
        if(!this.isAlive())return;
        this.sleepPercent = 0;
    }
    public void setAsSleepy(){
        if(!this.isAlive())return;
        this.sleepPercent = 100;
    }
    public boolean isSleepy(){
        return this.sleepPercent >= 70;
    }
    public void die(){
        this.hungerPercent = 0;
        this.sleepPercent = 0;
        this.deathTime = new java.util.Date();
    }
    public boolean isAlive(){
        return this.deathTime == null;
    }
    public String toString(){
        return this.species + " " + this.bmi + " lives " + this.lifeSpanDays + " days/" + "gender:" + this.biologicalSex + "." + this.status();
    }
    public String status(){
        return this.species + " status:" + " Hunger - " + this.hungerPercent + "%, " + "sleepiness:"+this.sleepPercent + "%" + ", Alive - " + this.isAlive() + ". First created at " + this.dateCreated();
    }
    public String dateCreated(){
        return new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(this.spawnTime);
    }
}
class Mammal extends Animal{
    private double bodyTemperatureC;
    private double avgBodyTemperatureC;

    public Mammal(String species, double heightM, double weightKg, double lifeSpanDays, String biologicalSex, double avgBodyTemperatureC){
        super(species, heightM, weightKg, lifeSpanDays, biologicalSex);

        this.avgBodyTemperatureC = avgBodyTemperatureC;
        this.bodyTemperatureC = this.avgBodyTemperatureC;
    }
    public void eat(){
        super.eat();
        System.out.println("this " + this.species + " is eating with its single lower jaw");
    }

    public String toString(){
        return super.toString() + " " + this.mammalInformation();
    }

    public void increaseBodyHeat(double celcius){
        this.bodyTemperatureC+=celcius;
    }

    public void decreaseBodyHeat(double celcius){
        this.bodyTemperatureC-=celcius;
    }

    public void adjustBodyHeat(){
        this.bodyTemperatureC = this.avgBodyTemperatureC;
    }

    public String mammalInformation(){
        return "This is a mammal with a temperature of: "+this.bodyTemperatureC;
    }
}
class Person extends Mammal{
    public static final String SPECIES = "Human";
    public static final double LIFE_EXPECTANCY = 30000;
    public static final double BODY_TEMPERATURE = 36;

    private Name name;
    private int age;

    public Person(String firstName, String lastName, int age, double heightM, double weightKg, String biologicalSex){
        super(Person.SPECIES, heightM, weightKg, Person.LIFE_EXPECTANCY, biologicalSex, Person.BODY_TEMPERATURE);
        this.name = new Name(firstName, lastName);
        this.age = age;
    }
    
    public String getName(){
        return this.name.toString();
    }
    public String toString(){
        return super.toString() + ". The name of this Person is " + this.getName();
    }
}
interface PlayfulPet{
    abstract public String play();
    abstract public String playWithPerson(Person person);
    abstract public String playNoise();
    abstract public String getPetName();
    abstract public double getRentalCosts();
    abstract public boolean likesActivity(String activity);
    abstract public boolean dislikesActivity(String activity);
    abstract public String doActivity(String activity);
}
class Cat extends Mammal implements PlayfulPet{
    public static final String SPECIES = "Cat";
    public static final double LIFE_EXPECTANCY = 5500;
    public static final double BODY_TEMPERATURE = 37;

    private static final double PLAYFUL_HOURLY_COSTS = 50;
    private static final String[] LIKED_ACTIVITIES = {"eat","nap","groom","drink","crawl","explore","pet"};
    private static final String[] DISLIKED_ACTIVITIES = {"bath"};

    public Cat(double heightM, double weightKg, String biologicalSex){
        super(Cat.SPECIES, heightM, weightKg, Cat.LIFE_EXPECTANCY, biologicalSex, Cat.BODY_TEMPERATURE);
    }

    public String toString(){
        return super.toString() + " this is a cat";
    }
    public void meow(){
        System.out.println("Meooow");
    }
    public String getPetName(){
        return this.species;
    }
    public String play(){
        return "This cat starts rolling on the floor, and pretends to play predator";
    }
    public String playWithPerson(Person person){
        String s = "The cat stares at " + person.getName();
        s+= ". After taking kin to " + person.getName() + ", " + person.getName() + " throws a mouse toy at this cat and the cat starts chasing the mouse toy";
        return s;
    }
    public String playNoise(){
        return "Meow";
    }
    public double getRentalCosts(){
        return this.PLAYFUL_HOURLY_COSTS;
    }

    public boolean likesActivity(String activity){
        return Arrays.asList(this.LIKED_ACTIVITIES).contains(activity);
    };

    public boolean dislikesActivity(String activity){
        return Arrays.asList(this.DISLIKED_ACTIVITIES).contains(activity);
    };

    public String doActivity(String activity){
        if(activity == "eat"){
            this.eat();
            return "The cat enjoyed eating food.";
        }
        else if(activity == "nap"){
            this.sleep();
            return "The cat took a good nap.";
        }
        else if(this.likesActivity(activity)) return "Meow. The cat really enjoyed the " + activity + " activity.";
        else if(this.likesActivity(activity)) return "The cat really hated the " + activity + " activity.";
        return "The cat felt indiferent about the " + activity + " activity.";
    };
}
abstract class PlayfulPetAssistant{
    protected static final double DEFAULT_RENT_TIME = 1.0;
    protected static final String DEFAULT_TOUR = "all-rounder pack";

    protected Person currentPerson;
    protected double currentRentTime = PlayfulPetAssistant.DEFAULT_RENT_TIME;
    protected static String[] availableActivities = {"eat","walk","drink","nap","pet","run","explore"};
    protected static String[] availableTours = {"all-rounder pack", "deluxe rounder pack"};

    public String[] getActivities(){
        return this.availableActivities;
    }

    public String[] getAvailableTours(){
        return this.availableTours;
    }

    public boolean isValidTour(String tour){
        return Arrays.asList(this.getAvailableTours()).contains(tour);
    }

    protected String getRandomActivity(){
        List<String> activities = Arrays.asList(this.getActivities());
        int ran = new Random().nextInt(activities.size());
        return activities.get(ran);
    }

    public void setPerson(Person person){
        this.currentPerson = person;
    }

    public void setHours(double hours){
        this.currentRentTime = hours;
    }

    public double getCurrentRentTime(){
        return this.currentRentTime;
    }

    public void reset(){
        this.currentPerson = null;
        this.currentRentTime = this.DEFAULT_RENT_TIME;
    }

    public double runAssistanceTour(Person person){
        return this.runAssistanceTour(person, this.DEFAULT_TOUR);
    }

    public double runAssistanceTour(Person person, String tour){
        return this.runAssistanceTour(person, tour, 1);
    }
    public double runAssistanceTour(Person person, String tour, int amount){
        if(!this.isValidTour(tour)) System.out.println("The tour guide does not accept the " + tour + " tour.");
        
        ArrayList<PlayfulPet> playfulPets = this.createPlayfulPets(amount);

        double rentalCosts = 0;
        for(int i = 0; i < playfulPets.size(); i++){
            PlayfulPet playfulPet = playfulPets.get(i);

            System.out.println("");
            System.out.println("Booting up... Playful Pet Assistance robot at your service.");
            System.out.println("Printing information about the Person to service..." + person);
            System.out.println("");
            System.out.println("Printing information about the Playful Pet - " + playfulPet.getPetName() + " to service..." + playfulPet);

            if(tour == "all-rounder pack" || tour == "deluxe rounder pack"){
                int count = tour == "all-rounder pack" ? 1 : 3;
                this.genericRounderTour(count, person, playfulPet);
                rentalCosts += playfulPet.getRentalCosts() * this.getCurrentRentTime();
            }
            else{
                System.out.println("The tour assistant robot for " + playfulPet.getPetName() + " and " + person.getName() + " did nothing.");
            }
        }

        this.reset();

        return rentalCosts;
    }

    private void genericRounderTour(int activityCount, Person person, PlayfulPet pet){
        String newLine = System.lineSeparator();
        System.out.println(newLine + "Now starting the generic rounder tour with " + activityCount + " activity(s)");
        System.out.println(person.getName() + " greets " + pet.getPetName() + newLine);
        System.out.println(pet.play() + newLine);
        System.out.println(pet.playNoise() + newLine);
        System.out.println(pet.playWithPerson(person) + newLine);
        for(int i = 0; i < activityCount; i++){
            String activity = this.getRandomActivity();
            System.out.println(pet.getPetName() + " will now " + activity);
            System.out.println("--------");
            System.out.println(pet.doActivity(activity));
            System.out.println("--------" + newLine);
        }
    }

    public abstract PlayfulPet createPlayfulPet();
    public abstract ArrayList<PlayfulPet> createPlayfulPets(int amount);
}
class PlayfulCatAssistant extends PlayfulPetAssistant{
    public PlayfulPet createPlayfulPet(){
        return new Cat(RandomWrapper.getRanDouble(0.15,0.3), RandomWrapper.getRanDouble(2.0,4.9), RandomWrapper.ranBoolean() ? "male" : "female");
    }
    public ArrayList<PlayfulPet> createPlayfulPets(int amount){
        ArrayList<PlayfulPet> list = new ArrayList<>();
        for(int i = 0; i < amount; i++){
            list.add(new Cat(RandomWrapper.getRanDouble(0.15,0.3), RandomWrapper.getRanDouble(2.0,4.9), RandomWrapper.ranBoolean() ? "male" : "female"));
        }
        return list;
    }
}
class Invoice{
    public String title;
    public String description;
    public double costs;
    public Person person;
    public String petKey;
    public int amount;

    public Invoice(String title, String description, double costs, Person person, String petKey, int amount){
        this.title = title;
        this.description = description;
        this.costs = costs;
        this.person = person;
        this.petKey = petKey;
        this.amount = amount;
    }
    
    public String toString(){
        String newLine = System.lineSeparator();
        return "Title: " + this.title + newLine + 
                "Description: " + this.description + newLine + 
                "Cost: " + this.costs + newLine + 
                "Person: " + this.person + newLine + 
                "Pet: " + this.petKey + newLine + 
                "Amount: " + this.amount;
    }
}
class FairyWorld{
    private HashMap<String, PlayfulPetAssistant> assistantMap = new HashMap<>();
    private ArrayList<Invoice> invoiceList = new ArrayList<>();

    public void rentPet(PlayfulPetAssistant assistant, Person person){
        System.out.println("Thank you for your pet rental!");
        double costs = assistant.runAssistanceTour(person);
        System.out.println(costs + " dollars were charged to " + person.getName() + "'s credit card.");
        System.out.println("xxxxxxxxxxxxxxxxxxxxxxx" + System.lineSeparator());
    }
    public void rentPet(String petKey, Person person, int amount, String tour){
        System.out.println("Thank you for your pet rental!");

        PlayfulPetAssistant assistant = this.assistantMap.get(petKey);
        double costs = assistant.runAssistanceTour(person, tour, amount);

        System.out.println(costs + " dollars were charged to " + person.getName() + "'s credit card.");
        System.out.println("xxxxxxxxxxxxxxxxxxxxxxx" + System.lineSeparator());

        String title = person.getName() + " rent " + petKey;
        String description = "You rent " + amount + " " + petKey + "at " + tour;
        Invoice invoice = new Invoice(title, description, costs, person, petKey, amount);
        this.addInvoice(invoice);
    }
    private void addInvoice(Invoice invoice){
        this.invoiceList.add(invoice);
    }
    public void addPlayfulPetAssistant(String petKey, PlayfulPetAssistant assistant){
        if(!this.assistantMap.containsKey(petKey)){
            assistantMap.put(petKey, assistant);
        }
    }
    public ArrayList<Invoice> getRentedPetsInvoice(){
        return this.invoiceList;
    }
}
class Main{
    public static void main(String[] args){

    }
}
