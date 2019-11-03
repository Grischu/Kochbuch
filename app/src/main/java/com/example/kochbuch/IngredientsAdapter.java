package com.example.kochbuch;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.IngredientsHolder> {
    private List<Ingredients> name;
    private OnItemClickListener listener;
    private Context context;

    public IngredientsAdapter(List<Ingredients> dataSet) {
        name = dataSet;
    }

    @NonNull
    @Override
    public IngredientsHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflater = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.ingredients_layout, viewGroup, false);
        return new IngredientsHolder(inflater);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientsHolder recipeHolder, int i) {
        //Recipe recipe = recipeList.get(i);
        Ingredients ingredients = name.get(i);


        recipeHolder.textViewName.setText(ingredients.getName());
        recipeHolder.textViewAmount.setText(String.valueOf(ingredients.getAmount()));
        recipeHolder.textViewUnit.setText(ingredients.getUnit());
        //recipeHolder.imageView.setImageResource(recipe.getImage());
    }

    @Override
    public int getItemCount() {
        return name.size();
    }

    /*public void setRecipe(List<Recipe> recipe) {
        this.recipeList = recipe;
        notifyDataSetChanged(); //TODO Maybe replace with more efficent
    }*/


    class IngredientsHolder extends RecyclerView.ViewHolder {
        private TextView textViewName;
        private TextView textViewAmount;
        private TextView textViewUnit;
        //private ImageView imageView;

        public IngredientsHolder(View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.name);
            textViewAmount = itemView.findViewById(R.id.amount);
            textViewUnit = itemView.findViewById(R.id.unit);

            //imageView = itemView.findViewById(R.id.image);

            /*itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(recipeList.get(position));
                    }
                }
            });*/
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Recipe recipe);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.listener = onItemClickListener;
    }

}
