package com.mohammed.translator;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class TranslatePage extends AppCompatActivity {

    private Button submitTranslate;
    private HashMap<String, String[]> foreignWord;
    private TextView arabic;
    private SharedPreferences getData;
    private EditText foreign;
    private String lang;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translate_page);

        submitTranslate = findViewById(R.id.submitTranslate);
        arabic = findViewById(R.id.arabicTranslated);
        foreign = findViewById(R.id.foreignWord);


        TheBag bag = new TheBag();
        //غير هذا الي الفاربيس
        getData = getSharedPreferences(TheBag.SHARE_KEY, MODE_PRIVATE);

        lang = bag.Dialog(this);
        //هنا اخذت كود الجسون من الحفظ الداخلي غيره الى فاربيس
        String json = getData.getString(lang, null);

        if (json == null) {
            Toast.makeText(this, "We dont have Data to this language", Toast.LENGTH_SHORT).show();
            submitTranslate.setEnabled(false);
        } else {
            foreignWord = TheBag.gson.fromJson(json, TheBag.typeToken);
        }
        submitTranslate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arabic.setText("");
                String[] words = foreignWord.get((foreign.getText().toString().toLowerCase()));
                if (words == null) {
                    Toast.makeText(TranslatePage.this, "We cant find this word", Toast.LENGTH_SHORT).show();
                } else {
                    for (String word : words) {
                        arabic.append(word + "\n");
                    }
                }
            }
        });

    }

}
