package client;

import data.Credentials;
import data.Student;
import data.StudentsDaoUser;
import data.User;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SuppressWarnings("ALL")
public class Client {
    private final String DATABASE_NAME = "students";
    private final String COLLECTION_NAME = "studentsInfo";
    private final String CREDENTIALS_PATH = "C:\\Users\\User\\Desktop\\credentials.txt";
    private StudentsDaoUser studentsDaoUser;
    private ClientDriver clientDriver;
    private void initiate(){
        this.studentsDaoUser = new StudentsDaoUser();
        this.clientDriver = ClientDriver.getInstance();
    }
    private JSONObject getSchema(){
        try {
            String SCHEMA_STRING = "{\"firstName\":\"String\",\"studentID\":\"String\",\"isEnrolled\":\"boolean\",\"GPA\":\"double\"}";
            return (JSONObject) new JSONParser().parse(SCHEMA_STRING);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
    private String getCredentialsString(Credentials credentials){
        return credentials.getUser().getUsername() + "\n"+credentials.getUser().getPassword()+"\n"+credentials.getPortNumber();
    }
    private void saveCredentials(){
        File credentialsFile = new File(CREDENTIALS_PATH);
        try {
            credentialsFile.createNewFile();
            FileWriter fileWriter = new FileWriter(credentialsFile);
            Credentials myCredentials = clientDriver.getCredentials();
            String credentialsString = getCredentialsString(myCredentials);
            fileWriter.write(credentialsString);
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private Credentials readCredentials(){
        File credentialsFile = new File(CREDENTIALS_PATH);
        Credentials credentials;
        try {
            Scanner scanner = new Scanner(credentialsFile);
            User myUser = new User(scanner.nextLine(), scanner.nextLine());
            int assignedPort = scanner.nextInt();
            credentials = new Credentials(myUser , assignedPort);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return credentials;
    }
    private boolean login(){
        if(credentialsExists()) {
            Credentials credentials = readCredentials();
            clientDriver.setCredentials(credentials);
        }else {
            clientDriver.createCredentials();
            saveCredentials();
        }return clientDriver.login();

    }
    private boolean credentialsExists(){
        return new File(CREDENTIALS_PATH).exists();
    }
    public void start(){
        initiate();

        if(login()){
            System.out.println("logged in");


            studentsDaoUser.createDatabase(DATABASE_NAME);
            System.out.println("Database Created");


            studentsDaoUser.createCollection(DATABASE_NAME , COLLECTION_NAME , getSchema());
            System.out.println("Collection Created");

            writeStudents();

            List<Student> studentList = studentsDaoUser.findAll(DATABASE_NAME , COLLECTION_NAME);
            System.out.println("All students are: ");
            for(Student student : studentList){
                System.out.println(student.toString());
            }
            studentsDaoUser.updateStudent(DATABASE_NAME , COLLECTION_NAME , 5 ,
                    new Student("Ezaldeen"  , true , 3.12 , "student15"));

            studentsDaoUser.createJSONPropertyIndexing(DATABASE_NAME  , COLLECTION_NAME , "isEnrolled");
            System.out.println("Enrolled students are: ");

            List<Long> enrolledStudentsIndexes = studentsDaoUser.getJSONPropertyIndexes(
                    DATABASE_NAME , COLLECTION_NAME , "isEnrolled" , "true");


            for(Long index : enrolledStudentsIndexes){
                int indexInt = Math.toIntExact(index);
                System.out.println(index + "- " + studentsDaoUser.getStudentByIndex(DATABASE_NAME , COLLECTION_NAME , indexInt) );
            }
            clientDriver.logout();

        } else{
            System.out.println("Connection Problem");
        }


    }

    private void writeStudents() {
        List<Student> students = new ArrayList<>();
        students.add(new Student("Ahmad" , true , 3.2 , "Student1"));
        students.add(new Student("Sarah" ,  true , 3.1 , "Student2"));
        students.add(new Student("Khaled" ,  false , 2.9 , "Student3"));
        students.add(new Student("Sarah" ,  true , 3.4 , "Student4"));
        students.add(new Student("Mahmoud" ,  true , 2.8 , "Student5"));
        students.add(new Student("Dina" ,  true , 3.8 , "Student6"));
        students.add(new Student("Lubna" ,  false , 2.5 , "Student7"));
        students.add(new Student("Ahmad" ,  false , 2.2 , "Student8"));
        students.add(new Student("Sarah" ,  false , 3.1 , "Student9"));
        students.add( new Student("Khaled" ,  false , 2.9 , "Student10"));
        students.add( new Student("Sarah" ,  true , 3.4 , "Student11"));
        students.add( new Student("Firas" ,  false , 4.0 , "Student12"));
        students.add( new Student("Ahmad" ,  true , 2.3 , "Student13"));
        students.add( new Student("Lubna"  , false , 2.7 , "Student14"));
        System.out.println("Writing students to Database");
        for (Student student :students) {
            studentsDaoUser.insertStudent(DATABASE_NAME , COLLECTION_NAME , student);
        }
        System.out.println("Students written!");
    }
}