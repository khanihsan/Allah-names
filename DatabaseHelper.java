package com.logicmaster.a99nameofallah;


    import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Locale;

    public class DatabaseHelper extends SQLiteOpenHelper {

        private static final String DATABASE_PATH = "/data/data/com.logicmaster.a99nameofallah/databases/";
        private static final String DATABASE_NAME = "allahnames.sqlite";
        private static final int SCHEMA_VERSION = 1;

        // wrapperClass wp=new wrapperClass();
 /*
  * private static final String TABLE_NAME="Topics";private static final
  * String COLUMN_ID="chapID";
  */

        public SQLiteDatabase dbSqlite;
        private final Context myContext;

        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, SCHEMA_VERSION);
            this.myContext = context;
            // TODO Auto-generated constructor stub
        }

        @Override
        public void onCreate(SQLiteDatabase arg0) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }

        public void createDatabse() {
            creatDB();

        }

        private void creatDB() {
            boolean dbExist = DBExists();
            if (!dbExist) {
                this.getReadableDatabase();
                copyDBFromResource();
            }

        }

        private boolean DBExists() {
            SQLiteDatabase db = null;
            try {
                String databasePath = DATABASE_PATH + DATABASE_NAME;
                db = SQLiteDatabase.openDatabase(databasePath, null,
                        SQLiteDatabase.OPEN_READWRITE);
                db.setLocale(Locale.getDefault());
                db.setLockingEnabled(true);
                db.setVersion(1);
            } catch (SQLiteException e) {
                Log.e("SqlHelper", "database not found");
            }
            if (db != null) {
                db.close();
            }
            return db != null ? true : false;
        }

        private void copyDBFromResource() {
            InputStream inputStream = null;
            OutputStream outStream = null;
            String dbFilePath = DATABASE_PATH + DATABASE_NAME;
            try {
                inputStream = myContext.getAssets().open(DATABASE_NAME);
                outStream = new FileOutputStream(dbFilePath);
                byte[] buffer = new byte[1024];
                int length;
                while ((length = inputStream.read(buffer)) > 0) {
                    outStream.write(buffer, 0, length);

                }
                outStream.flush();
                outStream.close();
                inputStream.close();

            } catch (IOException e) {
                throw new Error("Error copying database from resource");

            }
        }
        ///Main Activity



        public ArrayList<String> listAlphabet(){

            String header="";
            header = "name";
            String tab_name="";
            tab_name="names";
            ArrayList<String> arrayList_alphabet = new ArrayList<String>();
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor result = db.rawQuery("SELECT * from "+tab_name+"  ", null);
            result.moveToFirst();
            while(result.isAfterLast() == false){
                arrayList_alphabet.add(result.getString(result.getColumnIndex(header)));
                // arrayList_alphabet.add(result.getString(result.getColumnIndex("id")));
                result.moveToNext();
            }
            return arrayList_alphabet;
        }

        public ArrayList<String> getAlphabet(int id){

            String header="";
            header = "name";
            String tab_name="";
            tab_name="names";
            ArrayList<String> arrayList_alphabet = new ArrayList<String>();
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor result = db.rawQuery("SELECT * from "+tab_name+" where id = "+id+"  ", null);
            result.moveToFirst();
            while(result.isAfterLast() == false){
                arrayList_alphabet.add(result.getString(result.getColumnIndex(header)));
                // arrayList_alphabet.add(result.getString(result.getColumnIndex("id")));
                result.moveToNext();
            }
            return arrayList_alphabet;
        }


        public ArrayList<String> getword(int id){

            String header="";
            header = "name";
            String tab_name="";
            tab_name="names";
            ArrayList<String> arrayList_word = new ArrayList<String>();
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor result = db.rawQuery("SELECT * from "+tab_name+" where id = "+id+"  ", null);
            result.moveToFirst();
            while(result.isAfterLast() == false){

                arrayList_word.add(result.getString(result.getColumnIndex("meaning")));

                // arrayList_alphabet.add(result.getString(result.getColumnIndex("id")));
                result.moveToNext();
            }
            return arrayList_word;
        }

        public ArrayList<String> benifit(int id){

            String header="";
            header = "name";
            String tab_name="";
            tab_name="names";
            ArrayList<String> arrayList_word = new ArrayList<String>();
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor result = db.rawQuery("SELECT * from "+tab_name+" where id = "+id+"  ", null);
            result.moveToFirst();
            while(result.isAfterLast() == false){

                arrayList_word.add(result.getString(result.getColumnIndex("benifit")));

                // arrayList_alphabet.add(result.getString(result.getColumnIndex("id")));
                result.moveToNext();
            }
            return arrayList_word;
        }





    }

