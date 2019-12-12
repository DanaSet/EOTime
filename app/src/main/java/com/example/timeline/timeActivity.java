package com.example.timeline;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.timeline.helper.DataHelper;

import java.util.Calendar;

public class timeActivity extends AppCompatActivity {

    Cursor cursor;
    DataHelper dbEvent;
    Button btnAdd, btnSelesai, btnKembali;
    EditText editNomor, editEvent,editTanggal, editKeterangan, editWaktuMulai, editWaktuAkhir;
    DatePicker picker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);

        dbEvent = new DataHelper(this);
        editNomor = findViewById(R.id.editNomor);
        editEvent = findViewById(R.id.editEvent);
        editTanggal = findViewById(R.id.editTanggal);
        editKeterangan = findViewById(R.id.editKeterangan);
        editWaktuMulai = findViewById(R.id.editWaktuMulai);
        editWaktuAkhir = findViewById(R.id.editWaktuAkhir);

        editWaktuMulai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar mCurrentTime = Calendar.getInstance();
                int hours = mCurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mCurrentTime.get(Calendar.MINUTE);

                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(timeActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        editWaktuMulai.setText(String.format("%02d:%02d", selectedHour, selectedMinute));
                    }
                },hours,minute,true
                        );
                mTimePicker.setTitle("Pilih Waktu Mulai");
                mTimePicker.show();
            }
        });

        editWaktuAkhir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar mCurrentTime = Calendar.getInstance();
                int hours = mCurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mCurrentTime.get(Calendar.MINUTE);

                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(timeActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        editWaktuAkhir.setText(String.format("%02d:%02d", selectedHour, selectedMinute));
                    }
                },hours,minute,true
                );
                mTimePicker.setTitle("Pilih Waktu Berakhir");
                mTimePicker.show();
            }
        });


        editTanggal.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Calendar mCurrentDate = Calendar.getInstance();
                int day = mCurrentDate.get(Calendar.DAY_OF_MONTH);
                int month = mCurrentDate.get(Calendar.MONTH);
                int year = mCurrentDate.get(Calendar.YEAR);

                DatePickerDialog mDatePicker;
                mDatePicker = new DatePickerDialog(timeActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int selectedDay, int selectedMonth, int selectedYear) {
                        editTanggal.setText(String.format("%02d-%02d-%d", selectedDay, selectedMonth, selectedYear));
                    }
                },day,month,year
                        );
                mDatePicker.setTitle("Pilih Tanggal");
                mDatePicker.show();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = dbEvent.getWritableDatabase();
                db.execSQL("INSERT INTO kegiatan (nomor, event, tanggal, keterangan, waktu_mulai, waktu_akhir) VALUES ('" + editNomor.getText().toString() + "', '" + editEvent.getText().toString() + "', '" + editTanggal.getText().toString() + "', '" + editKeterangan.getText().toString() + "', '" + editWaktuMulai.getText().toString() + "', '" + editWaktuAkhir.getText().toString() + "')");
                Toast.makeText(getApplicationContext(), "Berhasil Menambah Kegiatan", Toast.LENGTH_LONG).show();
                MainActivity.ma.RefreshList();
                finish();
            }
        });

        btnSelesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbEvent.getWritableDatabase();
                db.execSQL("DELETE FROM kegiatan WHERE event = '" + getIntent().getStringExtra("event") + "'");
                Toast.makeText(getApplicationContext(), "Kegiatan Telah Diselesaikan", Toast.LENGTH_LONG).show();
                MainActivity.ma.RefreshList();
                finish();
            }
        });

        btnKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
