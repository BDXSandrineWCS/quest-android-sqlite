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

public class RoomCreateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_create);

        Button btCreate = findViewById(R.id.button_create_room);
        btCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText etName = findViewById(R.id.edit_name);

                String name = etName.getText().toString();

                addRoomToDB(name);

                Intent intent = new Intent(RoomCreateActivity.this, MainActivity.class);
                RoomCreateActivity.this.startActivity(intent);
            }
        });
    }

    private void addRoomToDB(String name) {

        DbHelper myDbHelper = new DbHelper(this);
        SQLiteDatabase myDatabase = myDbHelper.getWritableDatabase();

        ContentValues room = new ContentValues();
        room.put(DBContract.RoomEntry.COLUMN_NAME_NAME, name);

        long idRoom= myDatabase.insert(DBContract.RoomEntry.TABLE_NAME,null, room);

        String result = String.format(getString(R.string.text_room_created), String.valueOf(idRoom));

        Toast.makeText(this,result, Toast.LENGTH_SHORT).show();
    }
}
