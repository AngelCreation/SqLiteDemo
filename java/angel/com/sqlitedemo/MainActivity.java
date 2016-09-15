package angel.com.sqlitedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener
{
    Button updateButton = null;
    Button insertButton = null;
    Button deleteButton = null;
    Button selectButton = null;
    Button viewAllDataButton = null;
    EditText nameEditText = null;
    EditText numberEditText = null;
    EditText rowIdEditText = null;
    Data data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        updateButton = (Button) findViewById(R.id.updateButton);
        insertButton = (Button) findViewById(R.id.insertButton);
        deleteButton = (Button) findViewById(R.id.deleteButton);
        selectButton = (Button) findViewById(R.id.selectButton);
        viewAllDataButton = (Button) findViewById(R.id.viewAllDataButton);

        nameEditText = (EditText) findViewById(R.id.nameEditText);
        numberEditText = (EditText) findViewById(R.id.numberEditText);
        rowIdEditText = (EditText) findViewById(R.id.rowIdEditText);

        updateButton.setOnClickListener(this);
        insertButton.setOnClickListener(this);
        deleteButton.setOnClickListener(this);
        selectButton.setOnClickListener(this);
        viewAllDataButton.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
            switch (v.getId()){
                case R.id.insertButton:
                    DAL d = new DAL(MainActivity.this);
                    d.openDB();
                    data = new Data();
                    data.setName(nameEditText.getText().toString());
                    data.setSalary(Double.parseDouble(numberEditText.getText().toString()));
                    d.inserData(data);
                    d.closeDB();
                    break;
                case R.id.updateButton:
                    DAL d1 = new DAL(MainActivity.this);
                    d1.openDB();
                    data.setName(nameEditText.getText().toString());
                    data.setSalary(Double.parseDouble(numberEditText.getText().toString()));
                    try{
                        data.setId(Integer.parseInt(rowIdEditText.getText().toString()));
                    }
                    catch(Exception e){
                            e.printStackTrace();
                    }
                    d1.updateData(data);
                    d1.closeDB();
                    break;
                case R.id.deleteButton:
                    DAL d2 = new DAL(MainActivity.this);
                    d2.openDB();
                    data = new Data();
                    try{
                        data.setId(Integer.parseInt(rowIdEditText.getText().toString()));
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }
                    d2.Delete(data);
                    d2.closeDB();
                    break;
                case R.id.viewAllDataButton:
                    DAL d3 = new DAL(MainActivity.this);
                    d3.openDB();
                    Log.e("all Data","" + d3.selectData());
                    d3.closeDB();
                    break;
            }
    }
}
