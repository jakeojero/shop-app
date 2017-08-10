package com.jakeojero.casestudy.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.jakeojero.casestudy.R;
import com.jakeojero.casestudy.models.Product;

import java.text.NumberFormat;
import java.util.List;

/**
 * Created by Jake Ojero on 2017-08-09.
 */

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ViewHolder> {


    // Member variable for products
    private List<Product> mProducts;
    private Context mContext;

    public ProductsAdapter( Context mContext, List<Product> mProducts) {
        this.mProducts = mProducts;
        this.mContext = mContext;
    }

    private Context getContext() {
        return mContext;
    }

    // Usually involves inflating a layout from XML and returning the holder
    @Override
    public ProductsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.product, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(ProductsAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        Product product = mProducts.get(position);

        // Set item views based on your views and data model
        TextView pName = viewHolder.productName;
        pName.setText(product.getmProductName());

        NumberFormat moneyformat = NumberFormat.getCurrencyInstance();

        TextView pPrice = viewHolder.productPrice;
        pPrice.setText(moneyformat.format(product.getmMSRP()));

        Button button = viewHolder.productDetailsButton;
        button.setText("Details");

        String resource = product.getmGraphicName().replace("-", "").toLowerCase();
        ImageView imageView = viewHolder.productImage;
        int resId = getContext().getResources().getIdentifier(resource, "drawable", getContext().getPackageName());
        imageView.setImageResource(resId);
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return mProducts.size();
    }

    public void dataChanged(){
        notifyDataSetChanged();
    }

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView productName;
        public TextView productPrice;
        public Button productDetailsButton;
        public ImageView productImage;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            productName = (TextView) itemView.findViewById(R.id.product_name);
            productPrice = (TextView) itemView.findViewById(R.id.product_price);
            productDetailsButton = (Button) itemView.findViewById(R.id.product_details_button);
            productImage = (ImageView) itemView.findViewById(R.id.product_image);
        }
    }

}
