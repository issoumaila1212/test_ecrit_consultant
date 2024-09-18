import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NumberAdapter extends RecyclerView.Adapter<NumberAdapter.NumberViewHolder> {

    private List<Integer> numberList;

    // Constructeur pour initialiser la liste des nombres
    public NumberAdapter(List<Integer> numberList) {
        this.numberList = numberList;
    }

    @NonNull
    @Override
    public NumberViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate (charger) le layout pour chaque item de la liste
        View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        return new NumberViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NumberViewHolder holder, int position) {
        // Lier les données à chaque TextView dans la vue
        int number = numberList.get(position);
        holder.numberTextView.setText(String.valueOf(number));
    }

    @Override
    public int getItemCount() {
        // Retourne la taille de la liste de nombres
        return numberList.size();
    }

    // Permet de mettre à jour la liste et de notifier le RecyclerView
    public void updateList(List<Integer> newList) {
        numberList = newList;
        notifyDataSetChanged();
    }

    // Classe interne ViewHolder
    static class NumberViewHolder extends RecyclerView.ViewHolder {
        TextView numberTextView;

        public NumberViewHolder(@NonNull View itemView) {
            super(itemView);
            // Initialiser la TextView à partir de l'item layout
            numberTextView = itemView.findViewById(android.R.id.text1);
        }
    }
}
