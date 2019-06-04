package com.mohammed.translator;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class AddWord extends AppCompatActivity {

    private HashMap<String, String[]> ForeignLanguage;

    private EditText foreign;
    private String lang;
    private EditText arabic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_wrod);

        foreign = findViewById(R.id.englishWord);
        arabic = findViewById(R.id.arabicWord);

        Button submit = findViewById(R.id.submitAdd);
        //ايضا هذا غيره للفايربيس
        SharedPreferences savedData = getSharedPreferences(TheBag.SHARE_KEY, MODE_PRIVATE);

        TheBag bag = new TheBag();
        lang = bag.Dialog(this);
        //معلومات الجسون من الهاتف غيره فايربيس
        String json = savedData.getString(lang, null);

        if (json == null) {
            Toast.makeText(this, "We dont have any saved data", Toast.LENGTH_SHORT).show();
            ForeignLanguage = new HashMap<>();
        } else {
            ForeignLanguage = TheBag.gson.fromJson(json, TheBag.typeToken);
        }
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String ForeignWord = foreign.getText().toString();
                String arabicWord = arabic.getText().toString();

                if (ForeignWord.trim().isEmpty() || arabicWord.trim().isEmpty()) {
                    Toast.makeText(AddWord.this, "This is not valid input you entered space", Toast.LENGTH_SHORT).show();
                } else {
                    ForeignWord = ForeignWord.toLowerCase();
                    ForeignLanguage.put(ForeignWord, arabicWord.split("_"));
                    String toJson = TheBag.gson.toJson(ForeignLanguage, TheBag.typeToken);
                    //حفظت البيانات هنا بالخزن الداخلي انت احفظها بالفايربيس
                    SharedPreferences preferences = getSharedPreferences(TheBag.SHARE_KEY, MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString(lang, toJson);
                    editor.apply();
                    Toast.makeText(AddWord.this, "Saved!", Toast.LENGTH_SHORT).show();
                    foreign.setText("");
                    arabic.setText("");
                }

            }
        });
    }
}
