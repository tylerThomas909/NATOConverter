package tyler.thomas.natoconverter;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiresApi(api = Build.VERSION_CODES.N)
public class MainActivity extends AppCompatActivity {
    EditText editText;
    Button button;
    TextView textView;
    View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editTextName);
        textView = findViewById(R.id.textViewOne);
        button = findViewById(R.id.buttonOne);
        view = findViewById(R.id.snackbar_action);
        button.setOnClickListener(v -> {
            if (editText.getText().toString().equals("")){
                Snackbar.make(view, R.string.text_label, Snackbar.LENGTH_SHORT)
                        .show();
                textView.setText("");
            }
            else if(editText.getText().toString().matches(".*[0-9].*")){
                Snackbar.make(view, R.string.text_number, Snackbar.LENGTH_SHORT)
                        .show();
                textView.setText("");
            }
            else {
                Snackbar.make(view, R.string.done, Snackbar.LENGTH_SHORT)
                        .show();
                textView.setText(NATOConverter("" + editText.getText().toString()));
            }
        });
    }
    static final Map<Character, String> WORDS = Stream.of(
            "Alpha", "Bravo", "Charlie", "Delta", "Echo", "Foxtrot", "Golf", "Hotel" ,
            "India", "Juliett", "Kilo", "Lima", "Mike", "November", "Oscar", "Papa",
            "Quebec", "Romeo", "Sierra", "Tango", "Uniform", "Victor", "Whiskey",
            "Xray", "Yankee", "Zulu")
            .collect(Collectors.toMap(word -> word.charAt(0), Function.identity()));

    public static String NATOConverter(String name){
        return name.toUpperCase().chars()
                .mapToObj(c -> WORDS.get((char)c))
                .filter(Objects::nonNull)
                .collect(Collectors.joining(" "));
    }
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Exit Application?")
                .setCancelable(false)
                .setPositiveButton("Yes", (dialog, k) -> MainActivity.super.onBackPressed())
                .setNegativeButton("No", (dialog, which) -> dialog.cancel());
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}


