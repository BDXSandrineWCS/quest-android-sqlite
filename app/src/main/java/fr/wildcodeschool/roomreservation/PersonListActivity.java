package fr.wildcodeschool.roomreservation;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class PersonListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_list);

        ArrayList<PersonModel> personModels = loadPersonsFromDB();

        PersonAdapter adapter = new PersonAdapter(this, 0, personModels);
        ListView lvListPerson = findViewById(R.id.list_person);
        lvListPerson.setAdapter(adapter);
    }

    private ArrayList<PersonModel> loadPersonsFromDB() {
        ArrayList<PersonModel> personModels = new ArrayList<>();

        DbHelper myHelper = new DbHelper(this);
        SQLiteDatabase myDatabase = myHelper.getReadableDatabase();

        String[] projection = {
                DBContract.PersonEntry._ID,
                DBContract.PersonEntry.COLUMN_NAME_FIRSTNAME,
                DBContract.PersonEntry.COLUMN_NAME_LASTNAME
        };

        Cursor myCursor = myDatabase.query(DBContract.PersonEntry.TABLE_NAME, projection, null, null, null,null, null);

        while (myCursor.moveToNext()) {

            long id = myCursor.getLong(myCursor.getColumnIndexOrThrow(DBContract.PersonEntry._ID));
            String firstname = myCursor.getString(myCursor.getColumnIndexOrThrow(DBContract.PersonEntry.COLUMN_NAME_FIRSTNAME));
            String lastname = myCursor.getString(myCursor.getColumnIndexOrThrow(DBContract.PersonEntry.COLUMN_NAME_LASTNAME));

            PersonModel personModel = new PersonModel(id, firstname, lastname);
            personModels.add(personModel);
        }

        myCursor.close();

        return personModels;
    }
}
