import android.os.Bundle;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import java.util.ArrayList;
import android.text.TextWatcher;
import android.text.Editable;  // Import for Editable



public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private EditText editTextNumberFilter;
    private NumberAdapter numberAdapter;
    private List<Integer> numberList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Création d'un LinearLayout programmatically
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));

        // Création de l'EditText programmatically
        editTextNumberFilter = new EditText(this);
        editTextNumberFilter.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        editTextNumberFilter.setHint("Entrer un seuil");
        editTextNumberFilter.setInputType(android.text.InputType.TYPE_CLASS_NUMBER);

        // Création du RecyclerView programmatically
        recyclerView = new RecyclerView(this);
        LayoutParams recyclerViewParams = new LayoutParams(LayoutParams.MATCH_PARENT, 0, 1f);
        recyclerView.setLayoutParams(recyclerViewParams);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Ajout des vues au layout
        layout.addView(editTextNumberFilter);
        layout.addView(recyclerView);

        // Définir le layout comme vue principale de l'activité
        setContentView(layout);

        // Initialisation de la liste des nombres
        numberList = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            numberList.add(i);
        }

        // Configuration du RecyclerView
        numberAdapter = new NumberAdapter(numberList);
        recyclerView.setAdapter(numberAdapter);

        // Écouter les changements dans le champ de texte pour filtrer la liste
        editTextNumberFilter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                filterList(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });
    }

    private void filterList(String filterText) {
        if (filterText.isEmpty()) {
            numberAdapter.updateList(numberList);
        } else {
            try {
                int threshold = Integer.parseInt(filterText);
                List<Integer> filteredList = new ArrayList<>();
                for (int number : numberList) {
                    if (number > threshold) {
                        filteredList.add(number);
                    }
                }
                numberAdapter.updateList(filteredList);
            } catch (NumberFormatException e) {
                numberAdapter.updateList(numberList);
            }
        }
    }
}
