package fr.wildcodeschool.roomreservation;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class RoomListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_list);

        ArrayList<RoomModel> roomModels = loadRoomsFromDB();

        RoomAdapter adapter = new RoomAdapter(this, 0, roomModels);
        ListView lvListRoom = findViewById(R.id.list_room);
        lvListRoom.setAdapter(adapter);
    }

    private ArrayList<RoomModel> loadRoomsFromDB() {
        ArrayList<RoomModel> roomModels = new ArrayList<>();

        DbHelper myHelper = new DbHelper(this);
        SQLiteDatabase myDatabase = myHelper.getReadableDatabase();

        String[] projection = {
                DBContract.RoomEntry._ID,
                DBContract.RoomEntry.COLUMN_NAME_NAME
        };

        Cursor myCursor = myDatabase.query(DBContract.RoomEntry.TABLE_NAME, projection, null, null, null,null, null);

        while (myCursor.moveToNext()) {

            long id = myCursor.getLong(myCursor.getColumnIndexOrThrow(DBContract.RoomEntry._ID));
            String name = myCursor.getString(myCursor.getColumnIndexOrThrow(DBContract.RoomEntry.COLUMN_NAME_NAME));

            RoomModel room = new RoomModel(id,name);
            roomModels.add(room);
        }

        myCursor.close();


        return roomModels;
    }
}
