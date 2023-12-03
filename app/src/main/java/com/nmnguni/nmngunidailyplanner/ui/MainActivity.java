package com.nmnguni.nmngunidailyplanner.ui;

import static com.nmnguni.nmngunidailyplanner.data.DatabaseHelperSQLite.APPOINTMENTS_TABLE;

import static java.time.LocalDate.now;
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
import com.nmnguni.nmngunidailyplanner.data.DatabaseHelperSQLite;

import java.sql.Date;
import java.text.DateFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

/**
 * {@code public} {@code class} {@link MainActivity}, {@code extends}
 * {@code public} {@code class} {@link AppCompatActivity}.
 *
 * <p><br>The {@code MainActivityOld} {@code class} is the main activity UI
 * component.
 */
public class MainActivity extends AppCompatActivity
{
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
	DatabaseHelperSQLite dbHelper;
    Adapter appointmentsListAdapter;
    List<AppointmentsEntity> appointmentsList;

    /**
     * The {@link #onCreate(Bundle savedInstanceState)} method of the
     * {@link MainActivity} {@code  class}.
     *
     * @param savedInstanceState The saved instance state passed to the super
     *                          class.
     */
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
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
    }

    private void ShowAppointmentsListOnListView(DatabaseHelperSQLite dbHelperSQLite)
    {
    }
}
