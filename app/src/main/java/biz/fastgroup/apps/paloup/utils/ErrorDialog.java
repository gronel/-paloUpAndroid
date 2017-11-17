package biz.fastgroup.apps.paloup.utils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

/**
 * Created by RJG-PC on 10/2/2014.
 */
public class ErrorDialog extends DialogFragment {

    public ErrorDialog() {
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Bundle args = getArguments();
        String title = args.getString("title", "");
        String message = args.getString("message", "");

        return new AlertDialog.Builder(getActivity())
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, null);
                        dialog.dismiss();
                    }
                })
                .create();
    }
}
