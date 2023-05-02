package data;

import client.ClientDriver;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class StudentsDaoUser implements StudentDao {
    ClientDriver clientDriver;
    public StudentsDaoUser() {
        clientDriver = ClientDriver.getInstance();
    }
    private Student convertToStudent(JSONObject studentObject){
        Student student = new Student();
        if(!studentObject.isEmpty()){
            student.setStudentID((String) studentObject.get("studentID"));
            student.setFirstName((String) studentObject.get("firstName"));
            student.setEnrolled((boolean) studentObject.get("isEnrolled"));
            student.setGPA((double) studentObject.get("GPA"));
        }
        return student;
    }
    private JSONObject convertToJSON(Student student){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("firstName" , student.getFirstName());
        jsonObject.put("isEnrolled" , student.isEnrolled());
        jsonObject.put("GPA" , student.getGPA());
        jsonObject.put("studentID" , student.getStudentID());
        return jsonObject;
    }

    @Override
    public boolean createDatabase(String databaseName) {
        return clientDriver.createDatabase(databaseName);
    }

    @Override
    public boolean createCollection(String databaseName, String collectionName, JSONObject schema) {
        return clientDriver.createCollection(databaseName ,collectionName , schema);
    }

    @Override
    public Student getStudentByIndex(String databaseName, String collectionName, int index) {
       JSONObject studentObject = clientDriver.readObjectByIndex(databaseName ,collectionName , index);
        return convertToStudent(studentObject);
    }

    @Override
    public List<Student> findAll(String databaseName, String collectionName) {
        JSONArray studentsArray = clientDriver.readCollection(databaseName , collectionName);
        List<Student> students = new ArrayList<>();
        for(Object o : studentsArray){
            JSONObject studentObject = (JSONObject) o;
            Student current = convertToStudent(studentObject);
            students.add(current);
        }
        return students;
    }


    @Override
    public boolean insertStudent(String databaseName , String collectionName,Student student) {
        return clientDriver.writeObject(databaseName , collectionName , convertToJSON(student));
    }

    @Override
    public boolean updateStudent(String databaseName, String collectionName, int index, Student student) {
        return clientDriver.updateObjectOnIndex(databaseName ,collectionName , index , convertToJSON(student));
    }

    @Override
    public boolean deleteCollection(String databaseName, String collectionName) {
        return clientDriver.deleteCollection(databaseName , collectionName);
    }

    @Override
    public boolean deleteDatabase(String databaseName) {
        return clientDriver.deleteDatabase(databaseName);
    }

    @Override
    public boolean createJSONPropertyIndexing(String databaseName, String collectionName, String propertyName) {
        return clientDriver.createIndexOnAJSONProperty(databaseName ,collectionName , propertyName);
    }

    @Override
    public List<Long> getJSONPropertyIndexes(String databaseName, String collectionName, String propertyName, String propertyValue) {
        return clientDriver.getJSONPropertyIndexes(databaseName ,collectionName,propertyName , propertyValue);
    }

}
