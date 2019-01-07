package fr.wildcodeschool.roomreservation;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PersonCreateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_create);

        Button btCreate = findViewById(R.id.button_create_person);
        btCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText etFirstname = findViewById(R.id.edit_firstname);
                EditText etLastname = findViewById(R.id.edit_lastname);

                String firstname = etFirstname.getText().toString();
                String lastname = etLastname.getText().toString();

                addPersonToDB(firstname, lastname);

                Intent intent = new Intent(PersonCreateActivity.this, MainActivity.class);
                PersonCreateActivity.this.startActivity(intent);
            }
        });
    }

    private void addPersonToDB(String firstname, String lastname) {

        DbHelper myDbHelper = new DbHelper(this);
        SQLiteDatabase myDatabase = myDbHelper.getWritableDatabase();

        ContentValues person = new ContentValues();
        person.put(DBContract.PersonEntry.COLUMN_NAME_FIRSTNAME, firstname);
        person.put(DBContract.PersonEntry.COLUMN_NAME_LASTNAME, lastname);

        long idPerson= myDatabase.insert(DBContract.PersonEntry.TABLE_NAME,null, person);

        Toast.makeText(this, String.valueOf(idPerson), Toast.LENGTH_SHORT).show();

    }
}

