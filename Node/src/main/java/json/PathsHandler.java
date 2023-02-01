package json;

public class PathsHandler implements IPathsHandler{
    private final String basePath = "C:\\Users\\User\\Desktop\\Outputs1";
    @Override
    public String getDatabasePath(String databaseName){

        return basePath +"\\"+ databaseName;
    }
    @Override
    public String getCollectionPath(String databaseName, String collectionName){
        return getDatabasePath(databaseName + "\\"+ collectionName);
    }
    public String getIndexFilePath(String databaseName, String collectionName){
        return getCollectionPath(databaseName , collectionName ) + "\\index.txt";
    }
    public String getIdFilePath(String databaseName, String collectionName){
        return getCollectionPath(databaseName , collectionName ) + "\\idFile.json";
    }
    public String  getDataFilePath(String databaseName, String collectionName){
        return getCollectionPath(databaseName , collectionName ) + "\\data.json";
    }
    public String getSchemaFilePath(String databaseName , String collectionName){
        return getCollectionPath(databaseName , collectionName ) + "\\schema.json";
    }
    public String getAffinityPath(String databaseName, String collectionName){
        return getCollectionPath(databaseName , collectionName) + "\\affinity.txt";
    }
    public String getSingleJSONPropertyPath(String databaseName, String collectionName, String property){
        return getCollectionPath(databaseName , collectionName) + "\\"+ property+".json";

    }

    public String getBasePath() {
        return basePath;
    }
}
