package autocomplete.samadhan.com.myapplication.util;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import autocomplete.samadhan.com.myapplication.R;
import autocomplete.samadhan.com.myapplication.model.UserRepos;

public class UserRepodetailsBottomSheetDialog extends BottomSheetDialog {

    private UserRepos userRepos;
    private AppCompatTextView txtLastUpdated;
    private AppCompatTextView txtStars;
    private AppCompatTextView txtForks;

    public UserRepodetailsBottomSheetDialog(@NonNull Context context, UserRepos userRepos) {
        super(context);
        this.userRepos = userRepos;
        create();
    }

    public void create() {
        View bottomSheetView = getLayoutInflater().inflate(R.layout.dialog_view, null);
        setContentView(bottomSheetView);

        txtLastUpdated = bottomSheetView.findViewById(R.id.txtLastUpdated);
        txtStars = bottomSheetView.findViewById(R.id.txtStars);
        txtForks = bottomSheetView.findViewById(R.id.txtForks);

        txtLastUpdated.setText(userRepos.getUpdatyedAt());
        txtStars.setText(userRepos.getStarCount());
        txtForks.setText(userRepos.getForks());
    }
}
