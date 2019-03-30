package autocomplete.samadhan.com.myapplication.adapter;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import autocomplete.samadhan.com.myapplication.R;
import autocomplete.samadhan.com.myapplication.model.UserDetailsWithRepositories;
import autocomplete.samadhan.com.myapplication.model.UserRepos;

public class RepositoryListAdapter extends RecyclerView.Adapter {

    private UserDetailsWithRepositories userDetailsWithRepositories;
    private DisplayDialogInterface dialogInterface;

    public RepositoryListAdapter(UserDetailsWithRepositories userDetailsWithRepositories, DisplayDialogInterface dialogInterface) {
        this.userDetailsWithRepositories = userDetailsWithRepositories;
        this.dialogInterface = dialogInterface;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.repository_row_item, parent, false);
        return new RepositoryListAdapterViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {

        final UserRepos userRepos = userDetailsWithRepositories.getUserReposList().get(position);
        RepositoryListAdapterViewHolder repositoryListAdapterViewHolder = (RepositoryListAdapterViewHolder) viewHolder;

        if(userRepos != null && !TextUtils.isEmpty(userRepos.getName())) {
            repositoryListAdapterViewHolder.setView(userRepos.getName(), userRepos.getDescription());
        }

        repositoryListAdapterViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogInterface.displayDialog(userRepos);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (userDetailsWithRepositories.getUserReposList() != null) ? userDetailsWithRepositories.getUserReposList().size() : 0;
    }

    public interface DisplayDialogInterface {
        void displayDialog(UserRepos userRepos);
    }
}
