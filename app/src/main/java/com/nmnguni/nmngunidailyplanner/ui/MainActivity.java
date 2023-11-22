package com.nmnguni.nmngunidailyplanner.ui;

import static com.nmnguni.nmngunidailyplanner.data.DatabaseHelper.APPOINTMENTS_TABLE;

import static java.util.Calendar.getInstance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.nmnguni.nmngunidailyplanner.R;
import com.nmnguni.nmngunidailyplanner.data.AppointmentsEntity;
import com.nmnguni.nmngunidailyplanner.data.DatabaseHelper;

import java.sql.Date;
import java.util.List;
import java.util.ArrayList;

/**
 * {@link MainActivity} class that extends the {@link AppCompatActivity} class.
 */
public class MainActivity extends AppCompatActivity {

    /**
     * Fields: UI controls on the layout.
     */
    Button btn_appointmentsView;
    Button btn_appointmentsInsert;
    Button rdo_btnAppointmentGroceries;
    Button rdo_btnAppointmentGym;

    EditText et_appointmentDate;
    EditText et_appointmentMustdoContent;
    EditText et_appointmentContent;
    RecyclerView rv_appointmentsList;
    DatabaseHelper databaseHelper;
    Adapter appointmentsAdapter;
    List<AppointmentsEntity> appointmentsList;

    /**
     * {@link MainActivity} class {@link onCreate} method that extends the
     * {@link AppCompatActivity} class.
     */
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_appointmentsView = findViewById(R.id.btn_appointmentsView);
        btn_appointmentsInsert = findViewById(R.id.btn_appointmentsInsert);
        rdo_btnAppointmentGroceries = findViewById(R.id.rdo_btnAppointmentGroceries);
        rdo_btnAppointmentGym = findViewById(R.id.rdo_btnAppointmentGym);

        et_appointmentDate = findViewById(R.id.et_appointmentDate);
        et_appointmentMustdoContent = findViewById(R.id.et_appointmentMustdoContent);
        et_appointmentContent = findViewById(R.id.et_appointmentContent);
        rv_appointmentsList = findViewById(R.id.rv_appointmentsList);

        // Create an Android adapter for rv_appointmentsList.
        appointmentsListAdapter = new Adapter<AppointmentsEntity>(
            MainActivity.this, android.R.layout.simple_list_item_1, appointmentsList
        );

        // Link the appointmentsArrayAdapter with the rv_appointmentsList.
        rv_appointmentsList.setAdapter(appointmentsListAdapter);

        /**
         * Button listener for btn_appointmentsView.
         */
        btn_appointmentsView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper databaseHelper = new DatabaseHelper(MainActivity.this);
                appointmentsList = databaseHelper.appointmentsEntityGetAll();

                // Create an Android adapter for rv_appointmentsList.
                appointmentsArrayAdapter = new Adapter<AppointmentsEntity>(
                    MainActivity.this,
                    android.R.layout.simple_list_item_1,
                    appointmentsList
                );

                // Link appointmentsListAdapter to rv_appointmentsList control.
                rv_appointmentsList.setAdapter(appointmentsListAdapter);
            }
        });

        /**
         * Button listener for btn_appointmentsInsert.
         */
        btn_appointmentsInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppointmentsEntity appointmentsEntity;

                // Try to create an appointment object and catch input errors.
                try {
                    appointmentsEntity = new AppointmentsEntity(
                        -1,
                        java.sql.Date.valueOf(et_appointmentDate.getText().toString()),
                        et_appointmentMustdoContent.getText().toString(),
                        et_appointmentContent.getText().toString(),
                        rdo_btnAppointmentGroceries.isSelected(),
                        rdo_btnAppointmentGym.isSelected()
                    );
                    Toast.makeText(MainActivity.this, appointmentsEntity.toString(),
                        Toast.LENGTH_SHORT).show();
                }
                catch (Exception exc) {
                    Toast.makeText(MainActivity.this, "Error creating an appointment.\n\n" +
                        exc.getMessage().toString(), Toast.LENGTH_SHORT).show();

                    appointmentsEntity = new AppointmentsEntity(
                        -1,
                        // java.sql.Date.valueOf(et_appointmentDate.getText().toString()),
                        new Date(getInstance()),
                        et_appointmentMustdoContent.getText().toString(),
                        et_appointmentContent.getText().toString(),
                        rdo_btnAppointmentGroceries.isSelected(),
                        rdo_btnAppointmentGym.isSelected()
                    );
                }

                // Create database helper variable.
                DatabaseHelper databaseHelper = new DatabaseHelper(MainActivity.this);
            }

            // Create an empty list to be filled with the db query recordsList
            // from an appointment entry insert, and returned to the main activity.
            public List<AppointmentsEntity> appointmentEntry() {
                List<AppointmentsEntity> recordEntry = new ArrayList();

                // Get data from the database.
                String queryyString = "SELECT * FROM " + APPOINTMENTS_TABLE;

                SQLiteDatabase db = this.getReadableDatabase();

                Cursor cursor = db.rawQuery(queryString);

                if (
                        // loop through the cursor (result set) and create new appointment
                )

            return recordEntry;
            }
        });

        // Appointments list view delete one appointment, item click listener.
        rv_appointmentsList.setOnClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        }); {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AppointmentsEntity clickedAppointment = (AppointmentsEntity) parent.getItemAtPosition(position);
                databaseHelper.appointmentsEntityDeleteOne(clickedAppointment);
                ShowAppointmentsListOnListView(databaseHelper);
                Toast.makeText(MainActivity.this, "Deleted " + clickedAppointment.toString(), Toast.LENGTH_SHORT);
            }
        };
    }

    private void ShowAppointmentsListOnListView(DatabaseHelper databaseHelper) {
    }
}
