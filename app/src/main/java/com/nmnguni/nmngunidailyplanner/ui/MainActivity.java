package com.nmnguni.nmngunidailyplanner.ui;

import static com.nmnguni.nmngunidailyplanner.data.DataBaseHelper.APPOINTMENTS_TABLE;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.nmnguni.nmngunidailyplanner.R;
import com.nmnguni.nmngunidailyplanner.data.AppointmentsEntity;
import com.nmnguni.nmngunidailyplanner.data.DataBaseHelper;

import java.util.List;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // MainActivity Class Members.
    // Definitions of references to buttons, and other controls on the main
    // activity layout.
    Button btn_appointmentView, btn_appointmentInsert;
    Button rdo_optionGroceries, rdo_optionGym;
    EditText et_appointmentDate, et_appointmentMustDo, et_appointmentContent;
    ListView lv_appointmentList;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Assign values to class member references to buttons, and other
        // controls on the main activity layout.
        btn_appointmentView = findViewById(R.id.btn_appointmentsViewAll);
        btn_appointmentInsert = findViewById(R.id.btn_appointmentInsert);
        rdo_optionGroceries = findViewById(R.id.rdo_btnAppointmentGroceries);
        rdo_optionGym = findViewById(R.id.rdo_btnAppointmentGym);
        et_appointmentDate = findViewById(R.id.et_appointmentDate);
        et_appointmentMustDo = findViewById(R.id.et_appointmentMustdoContent);
        et_appointmentContent = findViewById(R.id.et_appointmentContent);
        lv_appointmentList = findViewById(R.id.lv_appointmentsList);

        // Button listeners for the add view all buttons.
        btn_appointmentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(MainActivity.this, "Button Appointment View clicked", Toast.LENGTH_SHORT).show();
                DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this);
                List<AppointmentsEntity> appointmentsList = dataBaseHelper.appointmentsEntityGetAll();

                Toast.makeText(MainActivity.this, appointmentsList.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        btn_appointmentInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a new AppointmentEntity object (newAppointment).
                AppointmentsEntity newAppointment;

                // Check for input errors when trying to instantiate / assign
                // the newAppointment object.
                try {
                    // Instantiate / assign the newAppointment object.
                    newAppointment = new AppointmentsEntity(
                        -1,
                        java.sql.Date.valueOf(et_appointmentDate.getText().toString()),
                        et_appointmentMustDo.getText().toString(),
                        et_appointmentContent.getText().toString(),
                        rdo_optionGroceries.isSelected(),
                        rdo_optionGym.isSelected()
                    );

                    Toast.makeText(MainActivity.this, "New appointment inserted.", Toast.LENGTH_SHORT).show();
                    // Toast.makeText(MainActivity.this, newAppointment.toString(), Toast.LENGTH_SHORT).show();
                }
                catch (Exception exc) {
                    Toast.makeText(MainActivity.this, "Error: Inserting an appointment.\n\n" +
                        exc.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    AppointmentsEntity appointmentsEntity = new AppointmentsEntity(
            -1,
                        java.sql.Date.valueOf("2023-01-01"),
  "must do test",
      "appointment test",
                       rdo_optionGroceries.isSelected(),
                       rdo_optionGym.isSelected()
                    );
                }

                // Create database helper variable.
                DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this);

                boolean success = dataBaseHelper.appointmentsEntityInsertOne();
                // Toast.makeText(MainActivity.this, "Error: Inserting an appointment.", Toast.LENGTH_SHORT).show();
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
    }
}