package com.example.kochbuch;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

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
                .inflate(R.layout.ingredients_layout_edit, viewGroup, false);
        if(viewGroup.getContext() instanceof RecipeViewActivity) {
            return new IngredientsHolder(inflater, true);
        }
        return new IngredientsHolder(inflater, false);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientsHolder recipeHolder, int i) {
        Ingredients ingredients = name.get(i);

        recipeHolder.editTextName.setText(ingredients.getName());
        recipeHolder.editTextAmount.setText(String.valueOf(ingredients.getAmount()));
        recipeHolder.editTextUnit.setText(ingredients.getUnit());
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
        private EditText editTextName;
        private EditText editTextAmount;
        private EditText editTextUnit;
        private ImageView imageViewRemove;
        //private ImageView imageView;

        public IngredientsHolder(View itemView, boolean disabled) {
            super(itemView);

            editTextName = (EditText) itemView.findViewById(R.id.name);
            editTextAmount = (EditText) itemView.findViewById(R.id.amount);
            editTextUnit = (EditText) itemView.findViewById(R.id.unit);
            imageViewRemove = (ImageView) itemView.findViewById(R.id.remove);

            editTextName.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    name.get(getAdapterPosition()).setName(editTextName.getText().toString());
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });

            editTextAmount.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if(!editTextAmount.getText().toString().equals("")) {
                        name.get(getAdapterPosition()).setAmount(Double.parseDouble(editTextAmount.getText().toString()));
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });

            editTextUnit.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    name.get(getAdapterPosition()).setUnit(editTextUnit.getText().toString());
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });

            imageViewRemove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    removeAt(getAdapterPosition());
                }
            });


            if(disabled) {
                editTextName.setEnabled(false);
                editTextAmount.setEnabled(false);
                editTextUnit.setEnabled(false);
                imageViewRemove.setVisibility(View.INVISIBLE);
            } else {
                editTextName.setEnabled(true);
                editTextAmount.setEnabled(true);
                editTextUnit.setEnabled(true);
                imageViewRemove.setVisibility(View.VISIBLE);
            }

        }
    }

    public void addData(){
        name.add(new Ingredients());
        notifyDataSetChanged();
    }

    public void removeAt(int position) {
        name.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, name.size());
    }


    public interface OnItemClickListener {
        void onItemClick(Recipe recipe);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.listener = onItemClickListener;
    }

    public List<Ingredients> getName() {
        return name;
    }

    public void setName(List<Ingredients> name) {
        this.name = name;
    }

    //TODO irgendwas, dass nach der mengenänderung und dann beim edit klick die werte nicht übernommen werden
    /*public void setAmount(int amount) {
        RecipeHolder.editTextAmount.setText(String.valueOf(ingredients.getAmount()));
    }*/

}
