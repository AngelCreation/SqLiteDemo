package angel.com.sqlitedemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by SVF 15213 on 01-07-2016.
 */
public class DAL extends SQLiteOpenHelper {

    private final static String PRACTICEDB = "practiceDB";
    private Context context = null;
    private SQLiteDatabase sqlDB;

    public DAL(Context context) {
        super(context, PRACTICEDB, null, 1  );
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "create table practice ( _id integer primary key autoincrement, namme text, salary real)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void openDB() {
        sqlDB = getWritableDatabase();
    }

    public void closeDB() {
        if (sqlDB != null && sqlDB.isOpen()) {
            sqlDB.close();
        }
    }

    public void inserData(Data d){

        ContentValues cv = new ContentValues();
        cv.put("namme",d.getName());
        cv.put("salary",d.getSalary());
        sqlDB.insert("practice",null,cv);
    }

    public String selectData() {
        Cursor cursor = sqlDB.query("practice", null, null, null, null, null, null, null);
        String data = "";
        while (cursor.moveToNext()) {
            int _id = cursor.getInt(cursor.getColumnIndex("_id"));
            String namme = cursor.getString(cursor.getColumnIndex("namme"));
            double salary = cursor.getDouble(cursor.getColumnIndex("salary"));
            data += _id + "\t" + namme + "\t" + salary + "\n\n";
        }
        cursor.close();
        return data;
    }


    public void updateData(Data d){
        // String query1 = "UPDATE my_table set name = name1, salary = Salary1 where _id = i   ";
        Log.e("inside"," Update data");
        ContentValues cv = new ContentValues();
        cv.put("namme",d.getName());
        cv.put("salary",d.getSalary());
        sqlDB.update("practice",cv,"_id = " + d.getId() ,null);
    }

    public void Delete(Data d){
        Log.e("inside","Delete Data");
        sqlDB.delete("practice","_id = "+ d.getId(),null);
    }
}
