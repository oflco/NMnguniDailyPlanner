package com.nmnguni.nmngunidailyplanner.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// Prepare to create the application database file for SQLite.
public class DataBaseHelper extends SQLiteOpenHelper {
    // Public static constant variables.
    public static final String APPOINTMENTS_TABLE = "APPOINTMENTS";
    public static final String COLUMN_APPOINTMENT_ID = "APPOINTMENT_ID";
    public static final String COLUMN_APPOINTMENT_DATE = "APPOINTMENT_DATE";
    public static final String COLUMN_APPOINTMENT_MUST_DO_CONTENT = "APPOINTMENT_MUST_DO_CONTENT";
    public static final String COLUMN_APPOINTMENT_CONTENT = "APPOINTMENT_CONTENT";
    public static final String COLUMN_APPOINTMENT_OPTION_GROCERIES = "APPOINTMENT_OPTION_GROCERIES";
    public static final String COLUMN_APPOINTMENT_OPTION_GYM = "APPOINTMENT_OPTION_GYM";

    public static final String NOTES_TABLE = "NOTES";
    public static final String COLUMN_NOTE_ID = "NOTE_ID";
    public static final String COLUMN_NOTE_DATE = "NOTE_DATE";
    public static final String COLUMN_NOTE_TITLE = "NOTE_TITLE";
    public static final String COLUMN_NOTE_CONTENT = "NOTE_CONTENT";

    public DataBaseHelper(@Nullable Context context) {
        super(context, "nmngunidailyplannerdb.db", null, 1);
    }

    // This method is called the first time a database is accessed.
    // There should be code in here to create a new database.
    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL create table statement: Appointments table.
        String createTableAppointments = "CREATE TABLE " + APPOINTMENTS_TABLE +
            "(" +
                COLUMN_APPOINTMENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_APPOINTMENT_DATE + " TEXT, " +
                COLUMN_APPOINTMENT_MUST_DO_CONTENT + " TEXT, " +
                COLUMN_APPOINTMENT_CONTENT + " TEXT, " +
                COLUMN_APPOINTMENT_OPTION_GROCERIES + " BOOL, " +
                COLUMN_APPOINTMENT_OPTION_GYM + " BOOL" +
            ")";
        db.execSQL(createTableAppointments);

        // Create table statement: Notes table.
        String createTableNotes = "CREATE TABLE " + NOTES_TABLE +
            "(" +
                COLUMN_NOTE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NOTE_DATE + " TEXT, " +
                COLUMN_NOTE_TITLE + " TEXT, " +
                COLUMN_NOTE_CONTENT + " TEXT" +
            ")";
        db.execSQL(createTableNotes);
    }

    // This method is called if the database version number changes.
    // It prevents previous user apps from breaking when you a database
    // design change occurs.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    // AppointmentsEntity insert one record method.
    public boolean appointmentsEntityInsertOne(AppointmentsEntity appointmentsEntity) {
        // SQLite getWritableDatabase method only used when you plan to
        // insert, update or delete records. The getWritableDatabase method
        // locks the SQLite data file so other processes may not access it.
        SQLiteDatabase db = this.getWritableDatabase();
        // ContentValues class that is used to store data sets in (key, value) pairs.
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_APPOINTMENT_DATE, appointmentsEntity.getAppointmentDate().getTime());
        contentValues.put(COLUMN_APPOINTMENT_MUST_DO_CONTENT, appointmentsEntity.getAppointmentMustDoContent());
        contentValues.put(COLUMN_APPOINTMENT_CONTENT, appointmentsEntity.getAppointmentsContent());
        contentValues.put(COLUMN_APPOINTMENT_OPTION_GROCERIES, appointmentsEntity.getAppointmentOptionGroceries());
        contentValues.put(COLUMN_APPOINTMENT_OPTION_GYM, appointmentsEntity.getAppointmentOptionGym());

        long insertedRowId = db.insert(APPOINTMENTS_TABLE, null, contentValues);

        // Check if the newly inserted row ID was a success row, or if an error occurred (-1).
        if (insertedRowId == -1) {
            return false;
        }
        else {
            return true;
        }
    }

    public boolean notesEntityInsertOne(NotesEntity notesEntity) {
        List<NotesEntity> recordsList = new ArrayList<>();
        String queryString = "SELECT * FROM " + NOTES_TABLE;

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_NOTE_DATE, notesEntity.getNoteDate().getTime());
        contentValues.put(COLUMN_NOTE_TITLE, notesEntity.getNoteTitle());
        contentValues.put(COLUMN_NOTE_CONTENT, notesEntity.getNote_Content());

        long insertedRowId = db.insert(APPOINTMENTS_TABLE, null, contentValues);

        if (insertedRowId == -1) {
            return false;
        }
        else {
            return true;
        }
    }

    // AppointmentsEntity get all records method.
    public List<AppointmentsEntity> appointmentsEntityGetAll() {
        // Create an empty records list variable of AppointmentsEntity type.
        List<AppointmentsEntity> recordsList = new ArrayList<>();

        // Fill the records list with data from the database SELECT query.
        String queryString = "SELECT * FROM " + APPOINTMENTS_TABLE;

        // SQLite getReadableDatabase method only used when you plan to
        // select records. The getReadableDatabase method does not lock
        // the SQLite data file. Other processes may access the data file.
        SQLiteDatabase db = this.getReadableDatabase();

        // Execute the actual database SQL statement (SELECT query).
        // The results returned after executing the query are assigned to a
        // cursor (the result set from a SQL statement).
        Cursor cursor = db.rawQuery(queryString, null);

        // Check if the database results that were assigned to the cursor were
        // successful, or they failed.
        if (cursor.moveToFirst()) {
            // loop through the cursor (result set) and create new
            // Appointments objects. Put them into the results list.
            do {
                int appointmentId = cursor.getInt(0);

                // Convert text/string date value to java SQL Date type.
                Date appointmentDate = ((java.sql.Date.valueOf(cursor.getString(1))));
                String appointmentMustDoContent = cursor.getString(2);
                String appointmentsContent = cursor.getString(3);

                // Ternary operator used to convert integer/number values
                // (1 and 0) to boolean values (true and false) inclusively.
                boolean appointmentOptionGroceries = cursor.getInt(4) == 1 ? true : false;
                boolean appointmentOptionGym = cursor.getInt(5) == 1 ? true : false;

                // Create a new AppointmentEntity (newAppointment) object and
                // parse it the cursor database result values.
                AppointmentsEntity newAppointment = new AppointmentsEntity(
                    appointmentId,
                    appointmentDate,
                    appointmentMustDoContent,
                    appointmentsContent,
                    appointmentOptionGroceries,
                    appointmentOptionGym
                );

                // Add the newAppointment object into the recordsList object.
                recordsList.add(newAppointment);
            } while (cursor.moveToNext());
        } else {
            // If there are no results from the database table (Appointments),
            // then no need to add any records to the recordsList.

        }

        // Close both the cursor and the db connections once done.
        cursor.close();
        db.close();

        // Return the records list from the method (data returned to the MainActivity).
        return recordsList;
    }

    public List<NotesEntity> notesEntityGetAll() {
        List<NotesEntity> recordsList = new ArrayList<>();

        String queryString = "SELECT * FROM " + NOTES_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            do {
                int noteId = cursor.getInt(0);
                Date noteDate = ((java.sql.Date.valueOf(cursor.getString(1))));
                String noteTitle = cursor.getString(2);
                String noteContent = cursor.getString(3);

                NotesEntity newNote = new NotesEntity(
                        noteId,
                        noteDate,
                        noteTitle,
                        noteContent
                );

                recordsList.add(newNote);
            } while (cursor.moveToNext());
        } else {
            // If there are no results from the database table (Notes),
            // then no need to add any records to the recordsList.

        }

        // Close both the cursor and the db connections once done.
        cursor.close();
        db.close();

        // Return the records list from the method (data returned to the MainActivity).
        return recordsList;
    }
    public boolean notesEntityDeleteOne(NotesEntity notesEntity) {
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + NOTES_TABLE + " WHERE " + COLUMN_NOTE_ID
            + " = " + notesEntity.getNoteId();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToNext()) {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean notesEntityDeleteOne(AppointmentsEntity notesEntity) {
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + NOTES_TABLE + " WHERE " + COLUMN_NOTE_ID
                + " = " + notesEntity.getNoteId();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToNext()) {
            return true;
        }
        else {
            return false;
        }
    }
}
