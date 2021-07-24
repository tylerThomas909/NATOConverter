package tyler.thomas.natoconverter;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
    static TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editTextName);
        textView = findViewById(R.id.textViewOne);
        button = findViewById(R.id.buttonOne);
        button.setOnClickListener(v -> textView.setText(NATOConverter(""+ editText.getText().toString())));

    }
    static final Map<Character, String> WORDS = Stream.of(
            "Alpha", "Bravo", "Charlie", "Delta", "Echo", "Foxtrot", "Golf", "Hotel" ,
            "India", "Juliett", "Kilo", "Lima", "Mike", "November", "Oscar", "Papa",
            "Quebec", "Romeo", "Sierra", "Tango", "Uniform", "Victor", "Whiskey",
            "Xray", "Yankee", "Zulu")
            .collect(Collectors.toMap(word -> word.charAt(0), Function.identity()));

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static String NATOConverter(String name){
        return name.toUpperCase().chars()
                .mapToObj(c -> WORDS.get((char)c))
                .filter(Objects::nonNull)
                .collect(Collectors.joining(" "));
    }
}


