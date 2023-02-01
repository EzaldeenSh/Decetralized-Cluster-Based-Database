package data;

import org.json.simple.JSONObject;

import java.util.List;

public interface StudentDao {
    boolean createDatabase(String databaseName);

    boolean createCollection(String databaseName, String collectionName, JSONObject schema);
    Student getStudentByIndex(String databaseName, String collectionName, int index);
    List<Student> findAll(String databaseName, String collectionName);

    boolean insertStudent(String databaseName, String collectionName,Student student);
    boolean updateStudent(String databaseName, String collectionName, int index, Student student);
    boolean deleteCollection(String databaseName, String collectionName);
    boolean deleteDatabase(String databaseName);
    boolean createJSONPropertyIndexing(String databaseName, String collectionName, String propertyName);
     List<Long> getJSONPropertyIndexes(String databaseName, String collectionName, String propertyName, String propertyValue);




}