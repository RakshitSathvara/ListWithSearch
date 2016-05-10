package in.vaksys.listwithsearch;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private Button showDialog;

    // Declare Variables
    ListView list;
    ListViewAdapter adapter;
    EditText editsearch;
    String[] code;
    String[] countryName;
    ArrayList<Coutrycode> arraylist = new ArrayList<Coutrycode>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showDialog = (Button) findViewById(R.id.show_dialog);

        code = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};

        countryName = new String[]{"China", "India", "United States",
                "Indonesia", "Brazil", "Pakistan", "Nigeria", "Bangladesh",
                "Russia", "Japan"};

        showDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(MainActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.country_code);

                list = (ListView) dialog.findViewById(R.id.listView);

                for (int i = 0; i < countryName.length; i++) {
                    Coutrycode wp = new Coutrycode(code[i], countryName[i]);
                    // Binds all strings into an array
                    arraylist.add(wp);
                }

                // Pass results to ListViewAdapter Class
                adapter = new ListViewAdapter(MainActivity.this, arraylist);

                // Binds the Adapter to the ListView
                list.setAdapter(adapter);

                // Locate the EditText in listview_main.xml
                editsearch = (EditText) dialog.findViewById(R.id.code);

                // Capture Text in EditText
                editsearch.addTextChangedListener(new TextWatcher() {

                    @Override
                    public void afterTextChanged(Editable arg0) {
                        // TODO Auto-generated method stub
                        String text = editsearch.getText().toString().toLowerCase(Locale.getDefault());
                        adapter.filter(text);
                    }

                    @Override
                    public void beforeTextChanged(CharSequence arg0, int arg1,
                                                  int arg2, int arg3) {
                        // TODO Auto-generated method stub
                    }

                    @Override
                    public void onTextChanged(CharSequence arg0, int arg1, int arg2,
                                              int arg3) {
                        // TODO Auto-generated method stub
                    }
                });

                dialog.show();
            }
        });
    }
}
