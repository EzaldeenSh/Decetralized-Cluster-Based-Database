package client;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Client1 {
    public static void main(String[] args){

        ClientDriver clientDriver = ClientDriver.getInstance();
        clientDriver.createCredentials();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ignored){
        }
        boolean validation = clientDriver.login();
        if(!validation){
            System.out.println("Client NOT Validated!");
            return;
        }
        try  {
            Scanner sc = new Scanner(System.in);
            System.out.println("Please select an operation: \n" +
                    "1-Create Database.\n" +
                    "2-Create Collection.\n" +
                    "3-Read Object by Index.\n" +
                    "4-Read Collection.\n" +
                    "5-Write Collection.\n" +
                    "6-Update Object By Index. \n" +
                    "7-Delete Collection.\n" +
                    "8-Delete Database.\n" +
                    "9-Create Index on a JSON Property\n"+
                    "10-Get Single Property Indexing\n"+
                    "0-Exit.");
            int selection = sc.nextInt();

            while(clientDriver.isConnected()){
                switch (selection){
                      case 1:{

                        System.out.println("Please enter the name of your database: ");

                        String databaseName = sc.next();
                        boolean result = clientDriver.createDatabase(databaseName);

                        if(result){
                            System.out.println("Database Created!");
                        } else
                            System.out.println("Database not Created!");
                          System.out.println("Please select an operation: \n" +
                                  "1-Create Database.\n" +
                                  "2-Create Collection.\n" +
                                  "3-Read Object by Index.\n" +
                                  "4-Read Collection.\n" +
                                  "5-Write Collection.\n" +
                                  "6-Update Object By Index. \n" +
                                  "7-Delete Collection.\n" +
                                  "8-Delete Database.\n" +
                                  "9-Create Index on a JSON Property\n"+
                                  "10-Get Single Property Indexing\n"+
                                  "0-Exit.");

                        selection = Integer.parseInt(sc.next());
                         break;
                 }
                 case 2:{
                     System.out.println("Please enter the name of the database: ");
                     String databaseName = sc.next();
                     System.out.println("Please enter the name of the collection: ");
                     String collectionName = sc.next();
                     System.out.println("Please enter your json schema: ");
                     String schema = sc.next();
                     JSONParser jsonParser = new JSONParser();

                     JSONObject jsonSchema = (JSONObject) jsonParser.parse(schema);

                     boolean result = clientDriver.createCollection(databaseName , collectionName ,jsonSchema);
                     if(result)
                         System.out.println("Collection created!");
                     else
                         System.out.println("Collection not created!");

                     System.out.println("Please select an operation: \n" +
                             "1-Create Database.\n" +
                             "2-Create Collection.\n" +
                             "3-Read Object by Index.\n" +
                             "4-Read Collection.\n" +
                             "5-Write Collection.\n" +
                             "6-Update Object By Index. \n" +
                             "7-Delete Collection.\n" +
                             "8-Delete Database.\n" +
                             "9-Create Index on a JSON Property\n"+
                             "10-Get Single Property Indexing\n"+
                             "0-Exit.");
                     selection = Integer.parseInt(sc.next());

                     break;
                 }
                 case 3:{

                     System.out.println("Please enter the name of the database: ");
                     String databaseName = sc.next();
                     System.out.println("Please enter the name of the collection: ");
                     String collectionName = sc.next();
                     System.out.println("Please enter the index of the wanted object");
                     int index = Integer.parseInt(sc.next());


                     JSONObject jsonObject = clientDriver.readObjectByIndex(databaseName , collectionName , index);
                     System.out.println(jsonObject+"\n");

                     System.out.println("Please select an operation: \n" +
                             "1-Create Database.\n" +
                             "2-Create Collection.\n" +
                             "3-Read Object by Index.\n" +
                             "4-Read Collection.\n" +
                             "5-Write Collection.\n" +
                             "6-Update Object By Index. \n" +
                             "7-Delete Collection.\n" +
                             "8-Delete Database.\n" +
                             "9-Create Index on a JSON Property\n"+
                             "10-Get Single Property Indexing\n"+
                             "0-Exit.");

                     selection = Integer.parseInt(sc.next());
                     break;
                 }
                 case 4:{

                     System.out.println("Please enter the name of the database: ");
                     String databaseName = sc.next();
                     System.out.println("Please enter the name of the collection: ");
                     String collectionName = sc.next();

                     JSONArray jsonArray = clientDriver.readCollection(databaseName , collectionName);

                     for(Object o : jsonArray){
                         System.out.println(o);
                     }

                     System.out.println("Please select an operation: \n" +
                             "1-Create Database.\n" +
                             "2-Create Collection.\n" +
                             "3-Read Object by Index.\n" +
                             "4-Read Collection.\n" +
                             "5-Write Collection.\n" +
                             "6-Update Object By Index. \n" +
                             "7-Delete Collection.\n" +
                             "8-Delete Database.\n" +
                             "9-Create Index on a JSON Property\n"+
                             "10-Get Single Property Indexing\n"+
                             "0-Exit.");

                     selection = Integer.parseInt(sc.next());
                     break;
                 }
                 case 5:{
                     System.out.println("Please enter the name of the database: ");
                     String databaseName = sc.next();
                     System.out.println("Please enter the name of the collection: ");
                     String collectionName = sc.next();
                     JSONObject jsonObject = new JSONObject();
                     jsonObject.put("username","user2");
                     jsonObject.put("password","p1");

                     boolean result = clientDriver.writeObject(databaseName ,collectionName , jsonObject);
                     if(result)
                         System.out.println("Object Written");
                     else
                         System.out.println("Object not Written!");
                     System.out.println("Please select an operation: \n" +
                             "1-Create Database.\n" +
                             "2-Create Collection.\n" +
                             "3-Read Object by Index.\n" +
                             "4-Read Collection.\n" +
                             "5-Write Collection.\n" +
                             "6-Update Object By Index. \n" +
                             "7-Delete Collection.\n" +
                             "8-Delete Database.\n" +
                             "9-Create Index on a JSON Property\n"+
                             "10-Get Single Property Indexing\n"+
                             "0-Exit.");


                     selection = Integer.parseInt(sc.next());
                     break;
                 }
                 case 6:{
                     System.out.println("Please enter the name of the database: ");
                     String databaseName = sc.next();
                     System.out.println("Please enter the name of the collection: ");
                     String collectionName = sc.next();
                     System.out.println("Please enter the index");
                     int index = sc.nextInt();

                     JSONObject jsonObject = new JSONObject();
                     jsonObject.put("username" , "myUser" );
                     jsonObject.put("password" , "myPassword");

                     boolean result = clientDriver.updateObjectOnIndex(databaseName ,collectionName , index , jsonObject);
                     if(result)
                         System.out.println("Object Updated");
                     else
                         System.out.println("Object not Updated!");
                     System.out.println("Please select an operation: \n" +
                             "1-Create Database.\n" +
                             "2-Create Collection.\n" +
                             "3-Read Object by Index.\n" +
                             "4-Read Collection.\n" +
                             "5-Write Collection.\n" +
                             "6-Update Object By Index. \n" +
                             "7-Delete Collection.\n" +
                             "8-Delete Database.\n" +
                             "9-Create Index on a JSON Property\n"+
                             "10-Get Single Property Indexing\n"+
                             "0-Exit.");


                     selection = Integer.parseInt(sc.next());
                     break;
                 }
                 case 7:{
                     System.out.println("Please enter the name of the database: ");
                     String databaseName = sc.next();
                     System.out.println("Please enter the name of the collection: ");
                     String collectionName = sc.next();


                     boolean result = clientDriver.deleteCollection(databaseName , collectionName);
                     if(result) System.out.println("Collection Deleted!");
                     else System.out.println("Collection not Deleted!");


                     System.out.println("Please select an operation: \n" +
                             "1-Create Database.\n" +
                             "2-Create Collection.\n" +
                             "3-Read Object by Index.\n" +
                             "4-Read Collection.\n" +
                             "5-Write Collection.\n" +
                             "6-Update Object By Index. \n" +
                             "7-Delete Collection.\n" +
                             "8-Delete Database.\n" +
                             "9-Create Index on a JSON Property\n"+
                             "10-Get Single Property Indexing\n"+
                             "0-Exit.");


                     selection = Integer.parseInt(sc.next());
                     break;
                 }
                 case 8:{
                     System.out.println("Please enter the name of your database: ");

                     String databaseName = sc.next();


                     boolean result = clientDriver.deleteDatabase(databaseName);
                     if(result){
                         System.out.println("Database Deleted!");
                     } else
                         System.out.println("Database not Deleted!");
                     System.out.println("Please select an operation: \n" +
                             "1-Create Database.\n" +
                             "2-Create Collection.\n" +
                             "3-Read Object by Index.\n" +
                             "4-Read Collection.\n" +
                             "5-Write Collection.\n" +
                             "6-Update Object By Index. \n" +
                             "7-Delete Collection.\n" +
                             "8-Delete Database.\n" +
                             "9-Create Index on a JSON Property\n"+
                             "10-Get Single Property Indexing\n"+
                             "0-Exit.");

                     selection = Integer.parseInt(sc.next());
                     break;
                 }
                    case 9:{
                        System.out.println("Please enter the name of your Database: ");
                        String databaseName = sc.next();
                        System.out.println("Please enter the name of your Collection: ");
                        String collectionName = sc.next();
                        System.out.println("Please enter the name of your property: ");
                        String propertyName = sc.next();

                        boolean result = clientDriver.createIndexOnAJSONProperty(databaseName ,collectionName , propertyName);
                        if(result){
                            System.out.println("Indexing Done");
                        } else
                            System.out.println("Indexing not Done");

                        System.out.println("Please select an operation: \n" +
                                "1-Create Database.\n" +
                                "2-Create Collection.\n" +
                                "3-Read Object by Index.\n" +
                                "4-Read Collection.\n" +
                                "5-Write Collection.\n" +
                                "6-Update Object By Index. \n" +
                                "7-Delete Collection.\n" +
                                "8-Delete Database.\n" +
                                "9-Create Index on a JSON Property\n"+
                                "10-Get Single Property Indexing\n"+
                                "0-Exit.");
                        selection = Integer.parseInt(sc.next());
                        break;
                    }
                    case 10:{
                        System.out.println("Please enter the name of your Database: ");
                        String databaseName = sc.next();
                        System.out.println("Please enter the name of your Collection: ");
                        String collectionName = sc.next();
                        System.out.println("Please enter the name of your property: ");
                        String propertyName = sc.next();
                        System.out.println("Please enter the name of your property value");
                        String propertyValue = sc.next();


                        ArrayList<Long> indexes = clientDriver.getJSONPropertyIndexes(databaseName ,collectionName , propertyName , propertyValue);
                        for(Long l : indexes){
                            System.out.println(l);
                        }
                        System.out.println("Please select an operation: \n" +
                                "1-Create Database.\n" +
                                "2-Create Collection.\n" +
                                "3-Read Object by Index.\n" +
                                "4-Read Collection.\n" +
                                "5-Write Collection.\n" +
                                "6-Update Object By Index. \n" +
                                "7-Delete Collection.\n" +
                                "8-Delete Database.\n" +
                                "9-Create Index on a JSON Property\n"+
                                "10-Get Single Property Indexing\n"+
                                "0-Exit.");
                        selection = Integer.parseInt(sc.next());

                        break;
                    }

                     default:{
                         System.out.println("Logging out");
                         selection = 0;
                         clientDriver.logout();
                         break;
                     }

                }
            }
            Thread.sleep(100);
            System.out.println("Client service is over");


        } catch (InterruptedException | ParseException e) {
            throw new RuntimeException(e);
        }


    }

}
