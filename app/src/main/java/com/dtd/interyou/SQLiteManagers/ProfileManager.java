package com.dtd.interyou.SQLiteManagers;

/**
 * Created by Egor on 18/08/15.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Egor on 22.05.2015.
 */
public class ProfileManager {



    // the Activity or Application that is creating an object from this class.
    Context context;

    // a reference to the database used by this application/object
    private SQLiteDatabase db;

    // These constants are specific to the database.  They should be
    // changed to suit your needs.
    private final String DB_NAME = "InterYou_database";
    private final int DB_VERSION = 1;

    // These constants are specific to the database table.  They should be
    // changed to suit your needs.
    private final static String TABLE_NAME = "AccountDB";
    private final static String TABLE_ROW_ID = "ID";
    private final static String TABLE_ROW_FIRSTNAME = "FirstName";
    private final static String TABLE_ROW_SURNAME = "SurName";
    private final static String TABLE_ROW_LASTNAME = "LastName";
    private final static String TABLE_ROW_EMAIL = "Email";
    private final static String TABLE_ROW_SEX = "Sex";
    private final static String TABLE_ROW_COUNTRY = "Country";
    private final static String TABLE_ROW_CITY = "City";
    private final static String TABLE_ROW_BIRTHDAY = "Birthday";
    private final static String TABLE_ROW_EDUCATION = "Education";
    private final static String TABLE_ROW_EMPLOYMENT = "Employment";
    private final static String TABLE_ROW_INCOME = "Income";
    private final static String TABLE_ROW_FINANCIALSTATUS = "Financial status";
    private final static String TABLE_ROW_CHILDREN = "Children";
    private final static String TABLE_ROW_FAMILYSTATUS = "Family status";
    private final static String TABLE_ROW_CAR = "Car";
    private final static String TABLE_ROW_MOBILEOPERATOR = "Mobile operator";

    public ProfileManager(Context context)
    {
        this.context = context;
        // create or open the database
        CustomSQLiteOpenHelper helper = new CustomSQLiteOpenHelper(context);
        this.db = helper.getWritableDatabase();
    }

    public void addRow(String rowStringFirstName,
                       String rowStringSurName,
                       String rowStringLastName,
                       String rowStringEmail,
                       String rowStringSex,
                       String rowStringCountry,
                       String rowStringCity,
                       String rowStringBithday,
                       String rowStringEducation,
                       String rowStringEmployment,
                       String rowStringIncome,
                       String rowStringFinancialStatus,
                       String rowStringChildren,
                       String rowStringFamilyStatus,
                       String rowStringCar,
                       String rowStringMobileOperator)
    {
        // this is a key value pair holder used by android's SQLite functions
        ContentValues values = new ContentValues();
        values.put(TABLE_ROW_FIRSTNAME, rowStringFirstName);
        values.put(TABLE_ROW_SURNAME, rowStringSurName);
        values.put(TABLE_ROW_LASTNAME, rowStringLastName);
        values.put(TABLE_ROW_EMAIL, rowStringEmail);
        values.put(TABLE_ROW_SEX ,rowStringSex);
        values.put(TABLE_ROW_COUNTRY ,rowStringCountry);
        values.put(TABLE_ROW_CITY ,rowStringCity);
        values.put(TABLE_ROW_BIRTHDAY ,rowStringBithday);
        values.put(TABLE_ROW_EDUCATION ,rowStringEducation);
        values.put(TABLE_ROW_EMPLOYMENT ,rowStringEmployment);
        values.put(TABLE_ROW_INCOME ,rowStringIncome);
        values.put(TABLE_ROW_FINANCIALSTATUS ,rowStringFinancialStatus);
        values.put(TABLE_ROW_CHILDREN ,rowStringChildren);
        values.put(TABLE_ROW_FAMILYSTATUS ,rowStringFamilyStatus);
        values.put(TABLE_ROW_CAR ,rowStringCar);
        values.put(TABLE_ROW_MOBILEOPERATOR ,rowStringMobileOperator);
        // ask the database object to insert the new data
        try{db.insert(TABLE_NAME, null, values);}
        catch(Exception e)
        {
            Log.e("DB ERROR", e.toString());
            e.printStackTrace();
        }
    }


    public void deleteRow()
    {
        // ask the database manager to delete the row of given id
        try {db.delete(TABLE_NAME, TABLE_ROW_ID + "=1", null);}                                                  /////////////////////////////////
        catch (Exception e)
        {
            Log.e("DB ERROR", e.toString());
            e.printStackTrace();
        }
    }
    /**********************************************************************
     * UPDATING A ROW IN THE DATABASE TABLE
     *
     * This is an example of how to update a row in the database table
     * using this class.  You should edit this method to suit your needs.
     */

    public void updateRow(String rowStringFirstName,
                          String rowStringSurName,
                          String rowStringLastName,
                          String rowStringEmail,
                          String rowStringSex,
                          String rowStringCountry,
                          String rowStringCity,
                          String rowStringBithday,
                          String rowStringEducation,
                          String rowStringEmployment,
                          String rowStringIncome,
                          String rowStringFinancialStatus,
                          String rowStringChildren,
                          String rowStringFamilyStatus,
                          String rowStringCar,
                          String rowStringMobileOperator)
    {
        // this is a key value pair holder used by android's SQLite functions
        ContentValues values = new ContentValues();
        values.put(TABLE_ROW_FIRSTNAME, rowStringFirstName);
        values.put(TABLE_ROW_SURNAME, rowStringSurName);
        values.put(TABLE_ROW_LASTNAME, rowStringLastName);
        values.put(TABLE_ROW_EMAIL, rowStringEmail);
        values.put(TABLE_ROW_SEX ,rowStringSex);
        values.put(TABLE_ROW_COUNTRY ,rowStringCountry);
        values.put(TABLE_ROW_CITY ,rowStringCity);
        values.put(TABLE_ROW_BIRTHDAY ,rowStringBithday);
        values.put(TABLE_ROW_EDUCATION ,rowStringEducation);
        values.put(TABLE_ROW_EMPLOYMENT ,rowStringEmployment);
        values.put(TABLE_ROW_INCOME ,rowStringIncome);
        values.put(TABLE_ROW_FINANCIALSTATUS ,rowStringFinancialStatus);
        values.put(TABLE_ROW_CHILDREN ,rowStringChildren);
        values.put(TABLE_ROW_FAMILYSTATUS ,rowStringFamilyStatus);
        values.put(TABLE_ROW_CAR ,rowStringCar);
        values.put(TABLE_ROW_MOBILEOPERATOR ,rowStringMobileOperator);

        // ask the database object to update the database row of given rowID
        try {db.update(TABLE_NAME, values, TABLE_ROW_ID + "=" + 1, null);}
        catch (Exception e)
        {
            Log.e("DB Error", e.toString());
            e.printStackTrace();
        }
    }

    /**********************************************************************
     * RETRIEVING A ROW FROM THE DATABASE TABLE
     *
     * This is an example of how to retrieve a row from a database table
     * using this class.  You should edit this method to suit your needs.
     *
     * @return an array containing the data from the row
     */

    public ArrayList<Object> getRowAsArray()
    {
        // create an array list to store data from the database row.
        // I would recommend creating a JavaBean compliant object
        // to store this data instead.  That way you can ensure
        // data types are correct.
        ArrayList<Object> rowArray = new ArrayList<Object>();
        Cursor cursor;

        try
        {
            // this is a database call that creates a "cursor" object.
            // the cursor object store the information collected from the
            // database and is used to iterate through the data.
            cursor = db.query
                    (
                            TABLE_NAME,
                            new String[] { TABLE_ROW_ID,
                                    TABLE_ROW_FIRSTNAME,
                                    TABLE_ROW_SURNAME,
                                    TABLE_ROW_LASTNAME,
                                    TABLE_ROW_EMAIL,
                                    TABLE_ROW_SEX,
                                    TABLE_ROW_COUNTRY,
                                    TABLE_ROW_CITY,
                                    TABLE_ROW_BIRTHDAY,
                                    TABLE_ROW_EDUCATION,
                                    TABLE_ROW_EMPLOYMENT,
                                    TABLE_ROW_INCOME,
                                    TABLE_ROW_FINANCIALSTATUS,
                                    TABLE_ROW_CHILDREN,
                                    TABLE_ROW_FAMILYSTATUS,
                                    TABLE_ROW_CAR,
                                    TABLE_ROW_MOBILEOPERATOR },
                            TABLE_ROW_ID + "= 1",
                            null, null, null, null, null
                    );

            // move the pointer to position zero in the cursor.
            cursor.moveToFirst();

            // if there is data available after the cursor's pointer, add
            // it to the ArrayList that will be returned by the method.
            if (!cursor.isAfterLast())
            {
                do
                {
                    rowArray.add(cursor.getLong(0));
                    rowArray.add(cursor.getString(1));
                    rowArray.add(cursor.getString(2));
                    rowArray.add(cursor.getString(3));
                    rowArray.add(cursor.getString(4));
                    rowArray.add(cursor.getString(5));
                    rowArray.add(cursor.getString(6));
                    rowArray.add(cursor.getString(7));
                    rowArray.add(cursor.getString(8));
                    rowArray.add(cursor.getString(9));
                    rowArray.add(cursor.getString(10));
                    rowArray.add(cursor.getString(11));
                    rowArray.add(cursor.getString(12));
                    rowArray.add(cursor.getString(13));
                    rowArray.add(cursor.getString(14));
                    rowArray.add(cursor.getString(15));
                    rowArray.add(cursor.getString(16));
                }
                while (cursor.moveToNext());
            }

            // let java know that you are through with the cursor.
            cursor.close();
        }
        catch (android.database.SQLException e)
        {
            Log.e("DB ERROR", e.toString());
            e.printStackTrace();
        }

        // return the ArrayList containing the given row from the database.
        return rowArray;
    }

    /**********************************************************************
     * RETRIEVING ALL ROWS FROM THE DATABASE TABLE
     *
     * This is an example of how to retrieve all data from a database
     * table using this class.  You should edit this method to suit your
     * needs.
     *
     * the key is automatically assigned by the database
     */

    public boolean fact(){
        Cursor c = db.query(TABLE_NAME, null, null, null, null, null, null);
        if(c.moveToFirst() ){
            return true;
        }else{
            return false;
        }
    }

    public ArrayList<ArrayList<Object>> getAllRowsAsArrays()
    {
        // create an ArrayList that will hold all of the data collected from
        // the database.
        ArrayList<ArrayList<Object>> dataArrays = new ArrayList<ArrayList<Object>>();

        // this is a database call that creates a "cursor" object.
        // the cursor object store the information collected from the
        // database and is used to iterate through the data.
        Cursor cursor;

        try
        {
            // ask the database object to create the cursor.
            cursor = db.query(
                    TABLE_NAME,
                    new String[]{TABLE_ROW_ID,
                            TABLE_ROW_FIRSTNAME,
                            TABLE_ROW_SURNAME,
                            TABLE_ROW_LASTNAME,
                            TABLE_ROW_EMAIL,
                            TABLE_ROW_SEX,
                            TABLE_ROW_COUNTRY,
                            TABLE_ROW_CITY,
                            TABLE_ROW_BIRTHDAY,
                            TABLE_ROW_EDUCATION,
                            TABLE_ROW_EMPLOYMENT,
                            TABLE_ROW_INCOME,
                            TABLE_ROW_FINANCIALSTATUS,
                            TABLE_ROW_CHILDREN,
                            TABLE_ROW_FAMILYSTATUS,
                            TABLE_ROW_CAR,
                            TABLE_ROW_MOBILEOPERATOR},
                    null, null, null, null, null
            );

            // move the cursor's pointer to position zero.
            cursor.moveToFirst();

            // if there is data after the current cursor position, add it
            // to the ArrayList.
            if (!cursor.isAfterLast())
            {
                do
                {
                    ArrayList<Object> dataList = new ArrayList<Object>();

                    dataList.add(cursor.getLong(0));
                    dataList.add(cursor.getString(1));
                    dataList.add(cursor.getString(2));
                    dataList.add(cursor.getString(3));
                    dataList.add(cursor.getString(4));
                    dataList.add(cursor.getString(5));
                    dataList.add(cursor.getString(6));
                    dataList.add(cursor.getString(7));
                    dataList.add(cursor.getString(8));
                    dataList.add(cursor.getString(9));
                    dataList.add(cursor.getString(10));
                    dataList.add(cursor.getString(11));
                    dataList.add(cursor.getString(12));
                    dataList.add(cursor.getString(13));
                    dataList.add(cursor.getString(14));
                    dataList.add(cursor.getString(15));
                    dataList.add(cursor.getString(16));

                    dataArrays.add(dataList);
                }
                // move the cursor's pointer up one position.
                while (cursor.moveToNext());
            }
        }
        catch (android.database.SQLException e)
        {
            Log.e("DB Error", e.toString());
            e.printStackTrace();
        }

        // return the ArrayList that holds the data collected from
        // the database.
        return dataArrays;
    }

    private class CustomSQLiteOpenHelper extends SQLiteOpenHelper
    {
        public CustomSQLiteOpenHelper(Context context)
        {
            super(context, DB_NAME, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db)
        {
            // This string is used to create the database.  It should
            // be changed to suit your needs.
            String newTableQueryString = "create table " +
                    TABLE_NAME +
                    " (" +
                    TABLE_ROW_ID + " integer primary key autoincrement not null," +
                    TABLE_ROW_FIRSTNAME + " text," +
                    TABLE_ROW_SURNAME + " text," +
                    TABLE_ROW_LASTNAME + " text," +
                    TABLE_ROW_EMAIL + " text," +
                    TABLE_ROW_SEX+ " text," +
                    TABLE_ROW_COUNTRY+ " text," +
                    TABLE_ROW_CITY+ " text," +
                    TABLE_ROW_BIRTHDAY+ " text," +
                    TABLE_ROW_EDUCATION+ " text," +
                    TABLE_ROW_EMPLOYMENT+ " text," +
                    TABLE_ROW_INCOME+ " text," +
                    TABLE_ROW_FINANCIALSTATUS+ " text," +
                    TABLE_ROW_CHILDREN+ " text," +
                    TABLE_ROW_FAMILYSTATUS+ " text," +
                    TABLE_ROW_CAR+ " text," +
                    TABLE_ROW_MOBILEOPERATOR+ " text" +
                    ");";
            // execute the query string to the database.
            try {
                db.execSQL(newTableQueryString);
            } catch (Exception e){
                e.printStackTrace();
            }
        }


        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
        {
            db.execSQL("DROP TABLE IF EXISTS " + DB_NAME);
            onCreate(db);

            // NOTHING TO DO HERE. THIS IS THE ORIGINAL DATABASE VERSION.
            // OTHERWISE, YOU WOULD SPECIFY HOW TO UPGRADE THE DATABASE.
        }
    }

}

