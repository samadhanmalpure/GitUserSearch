package autocomplete.samadhan.com.myapplication.adapter;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import autocomplete.samadhan.com.myapplication.R;


/**
 * Created by Samadhan.Malpure
 */

public class RepositoryListAdapterViewHolder extends RecyclerView.ViewHolder {

    public AppCompatTextView txtRepositoryName;
    public AppCompatTextView txtRepositoryDescription;

    public RepositoryListAdapterViewHolder(View itemView) {
        super(itemView);
        txtRepositoryName = itemView.findViewById(R.id.repositoryName);
        txtRepositoryDescription = itemView.findViewById(R.id.repositoryDescription);
    }

    public void setView(String repositoryName, String repositoryDescription) {
        txtRepositoryName.setText(repositoryName);
        txtRepositoryDescription.setText(repositoryDescription);
    }
}
