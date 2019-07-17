package com.example.android.justjava;

import android.app.LoaderManager;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.justjava.db.NotesContract;
import com.example.android.justjava.ui.NotesAdapter;

/**
 * Activity для создания новой заметки
 */
public class CreateNoteActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    public static final String EXTRA_NOTE_ID = "note_id";

    private Toast toast;

    private static int countEmtyTextNameDevice = 0;
    private static int countEmtyTextNameDriver = 0;
    private static int countEmtyTextNumberPhone = 0;
    private EditText nameDeviceList;
    private EditText nameDriver;
    private EditText telephoneDriver;

    private ImageView addButtonDevice;

    private long noteId;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_create_note);

        nameDeviceList = findViewById(R.id.nameDeviceList);
        nameDriver = findViewById(R.id.nameDriver);
        telephoneDriver = findViewById(R.id.telephoneDriver);
        addButtonDevice = findViewById(R.id.addButtonDevice);

        noteId = getIntent().getLongExtra(EXTRA_NOTE_ID, -1);
        if (noteId != -1) {
            getLoaderManager().initLoader(
                    0, // Идентификатор загрузчика
                    null, // Аргументы
                    this // Callback для событий загрузчика
            );
        }

        toast = Toast.makeText(getApplicationContext(),
                "Все поля должны быть заполнены",
                Toast.LENGTH_SHORT);
        TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
        v.setTextSize(20);
        v.setTextColor(Color.BLUE);
        v.setBackgroundColor(Color.GREEN);

        nameDeviceList.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().trim().length() > 0) {
                    countEmtyTextNameDevice=1;
                } else {
                    countEmtyTextNameDevice=0;
                }
            }
        });
        nameDriver.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().trim().length() > 0) {
                    countEmtyTextNameDriver=1;
                } else {
                    countEmtyTextNameDriver=0;
                }
            }
        });
        telephoneDriver.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().trim().length() > 0) {
                    countEmtyTextNumberPhone=1;
                } else {
                    countEmtyTextNumberPhone=0;
                }
            }
        });

        addButtonDevice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addButtonDevice.setImageResource(R.drawable.button_create_push);
                if (countEmtyTextNumberPhone*countEmtyTextNameDriver*countEmtyTextNameDevice > 0) {
                    countEmtyTextNumberPhone=0;
                    countEmtyTextNameDriver=0;
                    countEmtyTextNameDevice=0;
                    saveNote();
                    finish();
                }
                else {
                    addButtonDevice.setImageResource(R.drawable.button);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
            }
        });
    }

    /**
     * Метод для сохранения заметок
     */
    private void saveNote() {
        String title = nameDriver.getText().toString().trim();
        String axis1 = new LoginActivity().getString().toString().trim();
        String axis2 = new LoginActivity().getString().toString().trim();
        String axis3 = new LoginActivity().getString().toString().trim();
        String axis4 = new LoginActivity().getString().toString().trim();
        String text = nameDeviceList.getText().toString().trim();
        String number_phone = telephoneDriver.getText().toString().trim();


        boolean isCorrect = true;

        if (isCorrect) {
            long currentTime = System.currentTimeMillis();

            ContentValues contentValues = new ContentValues();
            contentValues.put(NotesContract.Notes.COLUMN_TITLE, title);

            contentValues.put(NotesContract.Notes.COLUMN_AXIS1, axis1);
            contentValues.put(NotesContract.Notes.COLUMN_AXIS2, axis2);
            contentValues.put(NotesContract.Notes.COLUMN_AXIS3, axis3);
            contentValues.put(NotesContract.Notes.COLUMN_AXIS4, axis4);
            contentValues.put(NotesContract.Notes.COLUMN_NUMBER_PHONE, number_phone);

            contentValues.put(NotesContract.Notes.COLUMN_NOTE, text);

            if (noteId == -1) {
                contentValues.put(NotesContract.Notes.COLUMN_CREATED_TS, currentTime);
            }

            contentValues.put(NotesContract.Notes.COLUMN_UPDATED_TS, currentTime);
            if (noteId == -1) {
                getContentResolver().insert(NotesContract.Notes.URI, contentValues);
            } else {
                getContentResolver().update(ContentUris.withAppendedId(NotesContract.Notes.URI, noteId),
                        contentValues,
                        null,
                        null);
            }

            finish();
        }
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(
                this,  // Контекст
                ContentUris.withAppendedId(NotesContract.Notes.URI, noteId), // URI
                NotesContract.Notes.LIST_PROJECTION, // Столбцы
                null, // Параметры выборки
                null, // Аргументы выборки
                null // Сортировка по умолчанию
        );
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {

        cursor.setNotificationUri(getContentResolver(), NotesContract.Notes.URI);

        displayNote(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    /**
     * Отображаем данные из курсора
     */
    private void displayNote(Cursor cursor) {
        if (!cursor.moveToFirst()) {
            // Если не получилось перейти к первой строке — завершаем Activity

            finish();
        }

        String title = cursor.getString(cursor.getColumnIndexOrThrow(NotesContract.Notes.COLUMN_TITLE));
        String noteText = cursor.getString(cursor.getColumnIndexOrThrow(NotesContract.Notes.COLUMN_NOTE));

        nameDriver.setText(title);
        nameDeviceList.setText(noteText);
    }
}
