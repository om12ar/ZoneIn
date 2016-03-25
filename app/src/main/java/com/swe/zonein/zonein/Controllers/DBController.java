package com.swe.zonein.zonein.Controllers;

import android.app.Notification;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import  com.swe.zonein.zonein.Models.*;
import java.util.ArrayList;


public class DBController extends SQLiteOpenHelper {

    private static final String TAG = DBController.class.getSimpleName();

    /*
    private final Context context;
    private final String AssetPath ;
    private final String dbPath;
     */
    private final String dbName= "db.db";

    public DBController(Context context) {
        super(context, "db.db", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE \"USER\" (\"USR_ID\" INTEGER PRIMARY KEY  AUTOINCREMENT ,\"USR_NAME\" VARCHAR(50)  NOT NULL  COLLATE NOCASE,\"USR_EMAIL\" VARCHAR(50)  NOT NULL  COLLATE NOCASE UNIQUE,\"USR_PASSWORD\" VARCHAR(50)  NOT NULL  COLLATE NOCASE,\"USR_TYPE\" VARCHAR(50)  NOT NULL  COLLATE NOCASE);");
        db.execSQL("CREATE TABLE \"place\" (\"PLC_ID\" INTEGER PRIMARY KEY  AUTOINCREMENT ,\"PLC_NAME\" VARCHAR(50)  NOT NULL  COLLATE NOCASE,\"PLC_RATING\" FLOAT(2,2)  NOT NULL  ,\"PLC_DISC\" TEXT  NOT NULL  COLLATE NOCASE,\"PLC_LONG\" TEXT  NOT NULL  COLLATE NOCASE,\"PLC_LAT\" VARCHAR(50)  NOT NULL  COLLATE NOCASE,\"numOfCheckin\" INTEGER  NULL DEFAULT NULL COLLATE NOCASE);");
        db.execSQL("CREATE TABLE \"taste\" (\"taste_ID\" INTEGER PRIMARY KEY  AUTOINCREMENT ,\"TASTE\" VARCHAR(50)  NOT NULL  COLLATE NOCASE);");
        db.execSQL("CREATE TABLE \"usr_in_chat\" (\"CHAT_ID\" INTEGER  NOT NULL  ,\"USR_ID\" INTEGER  NOT NULL  ,PRIMARY KEY (\"CHAT_ID\",\"USR_ID\"),CONSTRAINT `FK_USR_IN_CHAT` FOREIGN KEY (`CHAT_ID`) REFERENCES CHAT (`CHAT_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,CONSTRAINT `FK_USR_IN_CHAT2` FOREIGN KEY (`USR_ID`) REFERENCES user (`USR_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT);");
        db.execSQL("CREATE TABLE \"usr_has\" (\"TASTE_ID\" INTEGER  NOT NULL  ,\"USR_ID\" INTEGER  NOT NULL  ,PRIMARY KEY (\"TASTE_ID\",\"USR_ID\"),CONSTRAINT `FK_USR_HAS` FOREIGN KEY (`TASTE_ID`) REFERENCES taste (`TASTE_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,CONSTRAINT `FK_USR_HAS2` FOREIGN KEY (`USR_ID`) REFERENCES user (`USR_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT);");
        db.execSQL("CREATE TABLE \"user_saves_place\" (\"USER_USR_ID\" INTEGER  NOT NULL  ,\"PLACE_PLC_ID\" INTEGER  NOT NULL  ,PRIMARY KEY (\"USER_USR_ID\",\"PLACE_PLC_ID\"),CONSTRAINT `fk_USER_has_PLACE_USER1` FOREIGN KEY (`USER_USR_ID`) REFERENCES user (`USR_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,CONSTRAINT `fk_USER_has_PLACE_PLACE1` FOREIGN KEY (`PLACE_PLC_ID`) REFERENCES place (`PLC_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION);");
        db.execSQL("CREATE TABLE \"post\" (\"POST_ID\" INTEGER PRIMARY KEY   AUTOINCREMENT ,\"PLC_ID\" INTEGER  NULL DEFAULT NULL ,\"USR_ID\" INTEGER  NULL DEFAULT NULL ,\"POSTTEXT\" VARCHAR(200)  NOT NULL  COLLATE NOCASE,\"POSTTIME\" DATETIME  NOT NULL  ,\"POST_LIKES\" INTEGER  NULL DEFAULT NULL ,\"POSTERID\" INTEGER  NOT NULL  ,CONSTRAINT `FK_RELATIONSHIP_12` FOREIGN KEY (`PLC_ID`) REFERENCES place (`PLC_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,CONSTRAINT `FK_WRITES` FOREIGN KEY (`USR_ID`) REFERENCES user (`USR_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT);");
        db.execSQL("CREATE TABLE \"plc_has\" (\"TASTE_ID\" INTEGER  NOT NULL  ,\"PLC_ID\" INTEGER  NOT NULL  ,PRIMARY KEY (\"TASTE_ID\",\"PLC_ID\"),CONSTRAINT `FK_PLC_HAS` FOREIGN KEY (`TASTE_ID`) REFERENCES taste (`TASTE_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,CONSTRAINT `FK_PLC_HAS2` FOREIGN KEY (`PLC_ID`) REFERENCES place (`PLC_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT);");
        db.execSQL("CREATE TABLE \"notification\" (\"NOTI_ID\" INTEGER PRIMARY KEY AUTOINCREMENT  , \"NOTI_TXT\" INTEGER  NOT NULL ,\"USR_ID\" INTEGER  NOT NULL  ,\"SourceID\" VARCHAR(45)  NOT NULL  COLLATE NOCASE,CONSTRAINT `FK_USR_GETS` FOREIGN KEY (`USR_ID`) REFERENCES user (`USR_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT);");
        db.execSQL("CREATE TABLE \"message\" (\"CHAT_ID\" INTEGER  NOT NULL  ,\"USR_ID\" INTEGER  NOT NULL  ,\"MESSAGE\" TEXT  NULL  COLLATE NOCASE,\"MESSAGE_ID\" INTEGER PRIMARY KEY AUTOINCREMENT  COLLATE NOCASE,CONSTRAINT `FK_MESSAGE` FOREIGN KEY (`CHAT_ID`) REFERENCES CHAT (`CHAT_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,CONSTRAINT `FK_MESSAGE2` FOREIGN KEY (`USR_ID`) REFERENCES user (`USR_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT);");
        //db.execSQL("CREATE TABLE \"android_metadata\" (\"locale\" TEXT DEFAULT 'en_US');");
        db.execSQL("CREATE TABLE \"FRIEND_REQUEST\" (\"USR_ID\" INTEGER  NOT NULL  ,\"USE_USR_ID\" INTEGER  NOT NULL  ,PRIMARY KEY (\"USR_ID\",\"USE_USR_ID\"),CONSTRAINT `FK_FRIEND_REQUEST` FOREIGN KEY (`USR_ID`) REFERENCES user (`USR_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,CONSTRAINT `FK_FRIEND_REQUEST2` FOREIGN KEY (`USE_USR_ID`) REFERENCES user (`USR_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT);");
        db.execSQL("CREATE TABLE \"FRIENDS\" (\"USE_USR_ID\" INTEGER  NOT NULL  ,\"USR_ID\" INTEGER  NOT NULL  ,PRIMARY KEY (\"USE_USR_ID\",\"USR_ID\"),CONSTRAINT `FK_FRIENDS` FOREIGN KEY (`USR_ID`) REFERENCES user (`USR_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,CONSTRAINT `FK_FRIENDS2` FOREIGN KEY (`USE_USR_ID`) REFERENCES user (`USR_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT);");
        db.execSQL("CREATE TABLE \"FOLLOW\" (\"USR_ID\" INTEGER  NOT NULL  ,\"BRAND_ID\" VARCHAR(50)  NOT NULL  COLLATE NOCASE,PRIMARY KEY (\"USR_ID\",\"BRAND_ID\"),CONSTRAINT `FK_FOLLOW` FOREIGN KEY (`USR_ID`) REFERENCES user (`USR_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,CONSTRAINT `FK_FOLLOW2` FOREIGN KEY (`BRAND_ID`) REFERENCES BRAND (`BRAND_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT);");
        db.execSQL("CREATE TABLE \"COMMENT\" (\"POS_POST_ID\" INTEGER  NOT NULL  ,\"POST_ID\" INTEGER  NOT NULL  ,PRIMARY KEY (\"POS_POST_ID\",\"POST_ID\"),CONSTRAINT `FK_PARENTPOST` FOREIGN KEY (`POST_ID`) REFERENCES post (`POST_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,CONSTRAINT `FK_PARENTPOST2` FOREIGN KEY (`POS_POST_ID`) REFERENCES post (`POST_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT);");
        db.execSQL("CREATE TABLE \"CHECKIN\" (\"POST_ID\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"PLC_ID\" INTEGER  NULL DEFAULT NULL ,\"POSTTEXT\" VARCHAR(200)  NOT NULL  COLLATE NOCASE,\"POSTTIME\" VARCHAR(50)  NOT NULL  ,\"POST_LIKES\" INTEGER  NULL DEFAULT NULL ,\"POSTERID\" INTEGER  NOT NULL  ,\"RATE\" FLOAT(2,2)  NULL DEFAULT NULL ,CONSTRAINT `FK_INHERITANCE_1` FOREIGN KEY (`POST_ID`) REFERENCES post (`POST_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT);");
        db.execSQL("CREATE TABLE \"CHAT\" (`CHAT_ID` INTEGER PRIMARY KEY AUTOINCREMENT );");
        db.execSQL("CREATE TABLE \"BRAND_HAS_PLC\" (\"PLC_ID\" INTEGER  NOT NULL  ,\"BRAND_ID\" VARCHAR(50)  NOT NULL  COLLATE NOCASE,PRIMARY KEY (\"PLC_ID\",\"BRAND_ID\"),CONSTRAINT `FK_BRAND_HAS_PLC` FOREIGN KEY (`PLC_ID`) REFERENCES place (`PLC_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,CONSTRAINT `FK_BRAND_HAS_PLC2` FOREIGN KEY (`BRAND_ID`) REFERENCES BRAND (`BRAND_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT);");
        db.execSQL("CREATE TABLE `BRANDTIPS` (`TIP_ID`\tINTEGER PRIMARY KEY AUTOINCREMENT ,`BRAND_ID`\tVARCHAR(50) NOT NULL,`TIP_TXT`\tVARCHAR(50) DEFAULT NULL);");
        db.execSQL("CREATE TABLE `BRAND` (`BRAND_ID` INTEGER  PRIMARY KEY AUTOINCREMENT ,`USR_ID`\tINTEGER DEFAULT NULL,`BRAND_NAME`\tVARCHAR(50) DEFAULT NULL,FOREIGN KEY(`USR_ID`) REFERENCES user ( `USR_ID` ) ON DELETE RESTRICT ON UPDATE RESTRICT);");
        db.execSQL("CREATE INDEX 'fk_USER_has_PLACE_USER1_idx' ON 'user_saves_place' (`USER_USR_ID` DESC);");
        db.execSQL("CREATE INDEX 'fk_USER_has_PLACE_PLACE1_idx' ON 'user_saves_place' (`PLACE_PLC_ID` DESC);");
        db.execSQL("CREATE INDEX 'FK_WRITES' ON 'post' (`USR_ID` DESC);");
        db.execSQL("CREATE INDEX 'FK_USR_IN_CHAT2' ON 'usr_in_chat' (`USR_ID` DESC);");
        db.execSQL("CREATE INDEX 'FK_USR_HAS2' ON 'usr_has' (`USR_ID` DESC);");
        db.execSQL("CREATE INDEX 'FK_USR_GETS' ON 'notification' (`USR_ID` DESC);");
        db.execSQL("CREATE INDEX 'FK_RELATIONSHIP_12' ON 'post' (`PLC_ID` DESC);");
        db.execSQL("CREATE INDEX 'FK_PLC_HAS2' ON 'plc_has' (`PLC_ID` DESC);");
        db.execSQL("CREATE INDEX 'FK_PARENTPOST' ON 'COMMENT' (`POST_ID` DESC);");
        db.execSQL("CREATE INDEX 'FK_MESSAGE2' ON 'message' (`USR_ID` DESC);");
        db.execSQL("CREATE INDEX 'FK_MESSAGE' ON 'message' (`CHAT_ID` DESC);");
        db.execSQL("CREATE INDEX 'FK_FRIEND_REQUEST2' ON 'FRIEND_REQUEST' (`USE_USR_ID` DESC);");
        db.execSQL("CREATE INDEX 'FK_FRIENDS' ON 'FRIENDS' (`USR_ID` DESC);");
        db.execSQL("CREATE INDEX 'FK_FOLLOW2' ON 'FOLLOW' (`BRAND_ID` DESC);");
        db.execSQL("CREATE INDEX 'FK_BRAND_HAS_PLC2' ON 'BRAND_HAS_PLC' (`BRAND_ID` DESC);");


        // INITALIZE PLACES
        db.execSQL("INSERT INTO PLACE (PLC_NAME, PLC_RATING, PLC_DISC,PLC_LONG ,PLC_LAT , numOfCheckin) VALUES ('Cairo University' , 3.5 , 'This is Cairo University' , '31.20681509999997' ,'30.0223946' , 0)");
        db.execSQL("INSERT INTO PLACE (PLC_NAME, PLC_RATING, PLC_DISC,PLC_LONG ,PLC_LAT , numOfCheckin) VALUES ('FCI_CU' , 0.0 , 'faculty of computers and information cairo university' , '31.210334' ,'30.030795' , 0)");
        db.execSQL("INSERT INTO PLACE (PLC_NAME, PLC_RATING, PLC_DISC,PLC_LONG ,PLC_LAT , numOfCheckin) VALUES ('Booklet' , 5.0 , 'This is Booklet' , '31.21338129' ,'30.03094396' , 100)");
        db.execSQL("INSERT INTO PLACE (PLC_NAME, PLC_RATING, PLC_DISC,PLC_LONG ,PLC_LAT , numOfCheckin) VALUES ('shawerma el reem' , 1.0 , 'This is shawerma el reem' , '31.21338129' ,'30.03094396' , 100)");
        //INITALIZE TASTES
        db.execSQL("INSERT INTO TASTE (TASTE) VALUES ('education')");
        db.execSQL("INSERT INTO TASTE (TASTE) VALUES ('fastfood')");
        db.execSQL("INSERT INTO TASTE (TASTE) VALUES ('workspace')");
        db.execSQL("INSERT INTO TASTE (TASTE) VALUES ('outdoors')");
        db.execSQL("INSERT INTO TASTE (TASTE) VALUES ('chinese')");
        Log.i(TAG, "DB is created");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS USER");
        onCreate(db);
    }
    public boolean addUser(String name , String email , String pass){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contantValues = new ContentValues();
      try{

          pass = new StringBuilder(pass).reverse().toString() ;
          contantValues.put("USR_NAME", name );
          contantValues.put("USR_EMAIL", email);
          Log.e(TAG ,"saved mail = " +email +" "+ DatabaseUtils.sqlEscapeString(email)  );
          contantValues.put("USR_PASSWORD", pass );
          contantValues.put("USR_TYPE", "N");
          long isInserted = db.insertOrThrow("USER", null, contantValues);
          if(isInserted==-1){
              return false;
          }
          return true;
      }
      catch (Exception e){
          return false;
      }
    }





    public int login(String email , String pass){

        SQLiteDatabase db = this.getWritableDatabase();
        pass = new StringBuilder(pass).reverse().toString() ;
        Cursor res = db.rawQuery("SELECT USR_ID FROM USER WHERE USR_EMAIL = '" + email + "' AND USR_PASSWORD = '" + pass +"'", null);
        //Cursor res = db.rawQuery("SELECT USR_ID FROM USER ", null);
        if(res== null || res.getCount() ==0 ){
            Log.i(TAG, "SELECT USR_ID FROM USER WHERE USR_EMAIL = '" + email + "' AND USR_PASSWORD = '" + pass +"'");
            return -1;
        }
        else{
            res.moveToNext();
            StringBuffer buffer = new StringBuffer();
            Log.e(TAG, res.getString(0));
            buffer.append(res.getString(0));
            Log.e(TAG, buffer.toString());
            Integer id = Integer.parseInt(buffer.toString());
            Log.e(TAG+TAG, id.toString());
            return id;
        }

    }

    /**
     * Return user object needed to be retrieved from the database iff found.
     * Otherwise return null.
     * @param ID unique integer used to search for specific user in database.
     * @return User. the required user object to be retrieved from database.
     */
    public User getUser(Integer ID){


        SQLiteDatabase db = this.getWritableDatabase();
        User user = new User();
        //////GET USER DATA/////
        Cursor res = db.rawQuery("SELECT USR_NAME , USR_TYPE FROM USER WHERE USR_ID = "+ID, null);
        if(res== null || res.getCount() ==0 ){
            return null;
        }
        res.moveToNext();
        Log.e(TAG+"3", res.getString(0));
        user.setID(ID);
        user.setName(res.getString(0));
        Log.e(TAG+"3", res.getString(1));
        user.setUserType(res.getString(1));
        res.close();

        //////GET USER DATA/////
       //////GET USER tastes/////
        ArrayList<Taste> tastes = new ArrayList<>();
        Cursor res1 = db.rawQuery("SELECT * FROM usr_has WHERE USR_ID = "+ID, null);
        if(!(res1== null || res1.getCount() ==0)){
            ArrayList<Integer> tasteIDs = new ArrayList<>();
            while (res1.moveToNext()){
                tasteIDs.add(res1.getInt(0));
            }
            res1.close();
            for(int i =0 ; i < tasteIDs.size() ;i++){
                tastes.add(new Taste(getTaste(tasteIDs.get(i))));
            }
        }
        user.setUserTastes(tastes);

        //////GET USER tastes/////
        //////GET USER Places/////
        ArrayList<Place> places = new ArrayList<>();
        Cursor res2 = db.rawQuery("SELECT * FROM user_saves_place WHERE USER_USR_ID = "+ID, null);
        if(!(res2== null || res2.getCount() ==0)){
            ArrayList<Integer> plcIDs = new ArrayList<>();
            while (res2.moveToNext()){
                plcIDs.add(res2.getInt(1));
            }
            res2.close();
            for(int i =0 ; i <plcIDs.size() ;i++){
                places.add(new Place(getPlace(plcIDs.get(i))));
            }
        }
        user.setPlaces(places);

        //////GET USER Places/////



        //////GET USER Notifications/////
        ArrayList<NotificationModel> notifications = new ArrayList<>();
        Cursor res3 = db.rawQuery("SELECT * FROM NOTIFICATION WHERE USR_ID = "+ID, null);
        if(!(res3== null || res3.getCount() ==0)){
            while (res3.moveToNext()){
                NotificationModel notification = new NotificationModel();
                notification.setID(res3.getInt(0));
                notification.setNotification(res3.getString(1));
                notification.setUserID(res3.getInt(2));
                notification.setContentID(res3.getInt(3));
                notifications.add(new NotificationModel(notification));
            }
        }
        res3.close();
        user.setNotifications(notifications);
        //////GET USER notifications/////
        // TODO TODO TODO
//        ////db.close();
        Log.e(TAG + "R ", "\"SELECT USR_NAME , USR_TYPE FROM USER WHERE USR_ID = " + ID);
        return user;
    }

    public Taste getTaste(int ID){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT TASTE FROM TASTE WHERE taste_ID = " + ID, null);
        if(res== null || res.getCount() ==0 ){
            return null;
        }
        res.moveToNext();
        Taste taste = new Taste();
        taste.setID(ID);
        taste.setName(res.getString(0));
        ////db.close();
        res.close();
        return taste;
    }


    public int savePlaceToUser(int userID , int plc_ID){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contantValues = new ContentValues();
        //user_saves_place" ("USER_USR_ID" INTEGER  NOT NULL  ,"PLACE_PLC_ID
        try{
            contantValues.put("USER_USR_ID", userID);
            contantValues.put("PLACE_PLC_ID", plc_ID);
            Log.e(TAG, "saved place to user  = " + userID +"  " + plc_ID);
            long isInserted = db.insertOrThrow("user_saves_place", null, contantValues);
            if(isInserted==-1){
                return -1;
            }
            return (int) isInserted;
        }
        catch (Exception e){
            return -1;
        }
    }
    public ArrayList<Place> getUserPlaces(int ID){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<Place> places = new ArrayList<>();
        Cursor res2 = db.rawQuery("SELECT * FROM user_saves_place WHERE USER_USR_ID = "+ID, null);
        if(!(res2== null || res2.getCount() ==0)){
            ArrayList<Integer> plcIDs = new ArrayList<>();
            while (res2.moveToNext()){
                Log.e(TAG+"get user toNExt"  , res2.getInt(1)+"" );
                plcIDs.add(res2.getInt(1));
            }
            res2.close();
            for(int i =0 ; i <plcIDs.size() ;i++){
                places.add(new Place(getPlace(plcIDs.get(i))));
            }
            for(int i =0 ;i < places.size() ;i++){
                Log.e(TAG+"get user places"  , places.get(i).getName());
            }
            return places;
        }
        return null ;
    }

    public  Place getPlace(int ID)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM PLACE WHERE PLC_ID = " + ID, null);
        Log.e(TAG+"getplaces" , "SELECT * FROM PLACE WHERE PLC_ID = " + ID);
        if(res== null || res.getCount() ==0 ){
            return null;
        }
        res.moveToNext();
        Place place = new Place();
        place.setID(res.getInt(0));
        place.setName(res.getString(1));
        place.setRating(res.getFloat(2));
        place.setDescription(res.getString(3));
        place.setLng(res.getString(4));
        place.setLat(res.getString(5));
        place.setNumberOfCheckIn(res.getInt(6));
        ////db.close();
        res.close();
        return place;
    }

    public ArrayList<Place> getAllPlaces(){
        ArrayList<Place> places = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT PLC_ID FROM PLACE", null);
        if(res== null || res.getCount() ==0 ){
            return null;
        }
        ArrayList<Integer> plcID = new ArrayList<>();

        while (res.moveToNext()){
            Log.e(TAG + "plcID", "" + res.getInt(0) + " " + res.getCount() + " " + res.getPosition());
            plcID.add(res.getInt(0));


        }

        res.close();
        for(int i =0 ;i < plcID.size();i++){
            places.add(getPlace(plcID.get(i)));
        }
        ////db.close();
        res.close();
        return places;
    }
    public ArrayList<Taste> getAllTastes() {
        ArrayList<Taste> tastes = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM TASTE", null);
        if(res== null || res.getCount() ==0 ){
            return null;
        }
        while (res.moveToNext()){
            Log.e(TAG + "tasteID", "" + res.getInt(0) + " " + res.getString(1) + " " + res.getPosition());
            Taste taste = new Taste();
            taste.setID(res.getInt(0));
            taste.setName(res.getString(1));
            tastes.add(taste);
        }


        ////db.close();
        res.close();
        return tastes;
    }
    public ArrayList<NotificationModel> getUserNotifications (int ID){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<NotificationModel> notifications = new ArrayList<>();
        Cursor res3 = db.rawQuery("SELECT * FROM NOTIFICATION WHERE USR_ID = "+ID, null);
        if(!(res3== null || res3.getCount() ==0)){
            while (res3.moveToNext()){
                NotificationModel notification = new NotificationModel();
                notification.setID(res3.getInt(0));
                notification.setNotification(res3.getString(1));
                notification.setUserID(res3.getInt(2));
                notification.setContentID(res3.getInt(3));
                notifications.add(new NotificationModel(notification));
            }
        }
        res3.close();
        return notifications;
    }
    public String getPassByMail(String mail){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT USR_PASSWORD FROM USER WHERE USR_EMAIL = '"+mail+"'",null);
        res.moveToNext();
        if(res== null || res.getCount() ==0 ){
            return "";
        }
        else{
            String pass =res.getString(0);
            pass = new StringBuilder(pass).reverse().toString() ;
            return pass;
        }


    }
    /**
     * Return a boolean value indicate whether the operation
     * added to the database successfully or not.
     * @param ID , unique number indicate the user that used the request,
     * @param friendID , unique number indicate the user who friend request sent to.
     * @return operationDoneSuccessfully.if ,
     * operation one successfully return true
     * otherwise return false
     */

    public boolean sendFriendRequest(int ID, int friendID)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contantValues = new ContentValues();
        try{

            contantValues.put("USR_ID", ID );
            contantValues.put("USE_USR_ID", friendID );
            long isInserted = db.insertOrThrow("FRIEND_REQUEST", null, contantValues);
            if(isInserted==-1){
                ////db.close();
                return false;
            }

            ////db.close();
            return true;
        }
        catch (Exception e){
            return false;
        }

    }


    public boolean addTasteToUser(int userID ,int tasteID){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contantValues = new ContentValues();
        try{
            contantValues.put("USR_ID", userID );
            contantValues.put("TASTE_ID", tasteID );
            long isInserted = db.insertOrThrow("usr_has", null, contantValues);
            Log.e(TAG+" addTaste" , "" +tasteID+" " +userID);
            if(isInserted==-1){
                ////db.close();
                return false;
            }
            ////db.close();
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
    public ArrayList<Taste> getUserTastes(int userID ){
        ArrayList<Taste> tastes = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM usr_has WHERE USR_ID = "+userID, null);
        if(res== null || res.getCount() ==0 ){
            return null;
        }
        while (res.moveToNext()){
            tastes.add(getTaste(res.getInt(0)));
        }
        ////db.close();
        return tastes;


    }

    /**
     * Return boolean that indicate whether the operation done successfully.
     * This function is add check in element in table CHECKIN in database.
     * It takes parameter CHECKIN.
     * @param checkIn
     * @return boolean indicate  if the operation done successfully
     */


    public boolean checkIn(CheckIn checkIn)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contantValues = new ContentValues();
        try{
            contantValues.put("PLC_ID", checkIn.getUserID() );
            contantValues.put("POSTTEXT", checkIn.getText() );
            contantValues.put("POSTTIME", checkIn.getTime() );
            contantValues.put("POST_LIKES", 0 );
            contantValues.put("POSTERID" , checkIn.getUserID());
            contantValues.put("RATE", checkIn.getRate());
            long isInserted = db.insertOrThrow("CHECKIN", null, contantValues);
            if(isInserted==-1){
                ////db.close();
                return false;
            }
            /*ContentValues contantValues2 = new ContentValues();
            contantValues2.put("numOfCheckin" , );*/
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    /**
     * Return a CheckIn object that have the value that required to retrieve from the database.
     * The function search in the database for the required check in,
     *  by its id and return its value iff it found.
     * otherwise it return null.
     * @param checkInID . integer used to search in database.
     * @return returned . CheckIn object asked to retrieved from the database.
     */
    public static CheckIn getCheckIn(int checkInID)
    {
        // Search for Check in id idCHECK_IN
        //CheckIn returned=new CheckIn(); // put the parameterize data in constructor
        //return returned;
        return  null;
    }

    /**
     * Returned boolean indicate whether the add operation is done successfully or not.
     * Returned True. indicate that the operation is done successfully.
     * otherwise returned false
     * @param user User object to add in the Database table.
     * @return operationDoneSuccessfully. it indicate whether the operation is done successfully .
     * or any error happen in saving in database.
     */
    public static boolean saveUser(User user)
    {
        boolean operationDoneSucessfully=false;
        // add User in Table User
        // if any exception happen
        operationDoneSucessfully=false;
        // else
        operationDoneSucessfully=true;
        return true;
    }





    /**
     *
     * @param place
     * @return
     */
    public static boolean savePlace(Place place)
    {
        boolean operationDoneSuccessfully=false;
        // add the element in Table place;
        // no exception happen
        operationDoneSuccessfully=true;
        // exception happen
        operationDoneSuccessfully=false;
        return operationDoneSuccessfully;
    }

    public static boolean saveBrand(Brand brand)
    {
        boolean operationDoneSuccessfully=false;
        // add brand to table BRAND
        operationDoneSuccessfully=true;
        // if any exception happen
        operationDoneSuccessfully=false;
        return operationDoneSuccessfully;
    }
    public static Brand getBrand(int brandId)
    {
        // search for brand id in table BRAND
        Brand brand=new Brand(); // paramertize the constructor
        return brand;
        // else not found return null
    }
    public static boolean new_post(Post post)
    {
        boolean operationDoneSucessfully=false;
        // save the Post to table POST
        operationDoneSucessfully=true;
        // if any exception
        operationDoneSucessfully=false;
        return operationDoneSucessfully;
    }
    public static ArrayList<Place> searchPlace(String placeName)
    {
        ArrayList<Place> places=new ArrayList<Place>();
        // search in PLACE for string name
        return places;
        // if not found return null
    }
    public static ArrayList<User> searchUser(String userName)
    {
        // search in User
        ArrayList<User> users=new ArrayList<User>();
        return users;
        // if not found any return null
    }
    public static boolean AddPlaceToBrand(Place place,int brandId)
    {
        boolean operationDoneSuccessfully=false;
        // search for the brand in TABLE BRAND and get id
        // search for the place id TABLE PLACE and get id
        // ADD to the BRAND_HAS_PLC the both ids
        // if no exception happened in database
        operationDoneSuccessfully=true;
        //otherwise
        operationDoneSuccessfully=false;
        return operationDoneSuccessfully;

    }
    public static ArrayList<Place> getAllPlaceRelatedToBrand(int brandId)
    {
        ArrayList<Place> place=new ArrayList<>();
        //search in DB for brand id
        // if found any add to Array
        // otherwise return null
        return place;
    }
    /**
     * Return the userID that have the same in email .
     * Return only one user if found.
     * otherwise return -1.
     * @param e_mail Unique string used to search for a user.
     * @return userID. Unique integer.
     */
    public static int getUserByEmail(String e_mail)
    {
        int userID=-1;
        // search e_mail in DB
        // if found return userID
        //else rtuen -1
        return userID;
    }


    public static boolean acceptFriendRequest(boolean accept, int notifyNumber)
    {
        boolean operationDoneSucessfully=false;
        if(accept==false)
        {
            // search in NOTIFICATION table
            // delete from the NOTIFICATION TABLE
            // then delete from the FRIEND database. !
            // if any error happen in database
            operationDoneSucessfully=false;
            // otherwise
            operationDoneSucessfully=true;
        }
        else
        {
            operationDoneSucessfully=true;
        }
        return operationDoneSucessfully;
    }

    public static boolean removeUser(User user) {
        // search for user id in database and delete it
        boolean operationDoneSucessfully=false;
        // if anyerror happen in database
        operationDoneSucessfully=false;
        // otherwise
        operationDoneSucessfully=true;
        return operationDoneSucessfully;
    }
    /**
     * Return place ID iff the place lng and lat are found.
     * otherwise return -1
     * @param lng String indicate the position of place according to longitude.
     * @param lat String indicate the position of place according to the laitude.
     * @return placeID. Unique value used to point to only this place.
     */
    public static int searchPlaceUsingLngLat(String lng, String lat)
    {
        int placeID=-1;
        // placeID select PLC_ID
        // From TABLE PLACE
        // WHERE PLC_LNG=lng and PLC_LAT=lat
        return placeID;
    }

    public static boolean addUserinChat(int userID, int chatID)
    {
        boolean operationDoneSuccessfully=false;
        return  operationDoneSuccessfully;
    }

    public static  boolean addMessageinChat(int ChatID,int messageID)
    {
        boolean operationDoneSuccessfully=false;
        return  operationDoneSuccessfully;
    }

    public static boolean addChat(int ChatID)
    {
        boolean operationDoneSuccessfully=false;
        return  operationDoneSuccessfully;
    }

    public static boolean saveNotification(Notification notification)
    {
        boolean operationDoneSuccessfully=false;
        return operationDoneSuccessfully;
    }

    public static boolean addFollow(int brandID, int userID)
    {
        boolean operationDoneSuccessfully=false;
        return operationDoneSuccessfully;
    }



}






