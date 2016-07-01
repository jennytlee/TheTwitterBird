package com.codepath.apps.restclienttemplate.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.codepath.apps.restclienttemplate.R;

/**
 * Created by jennytlee on 6/30/16.
 */
public class ComposeTweetDialogFragment extends DialogFragment implements TextView.OnEditorActionListener {

    private EditText etBody;

    public ComposeTweetDialogFragment() {
    }
    public interface ComposeTweetDialogListener {
        void onFinishEditDialog(String inputText);
    }

    /*public static ComposeTweetDialogFragment newInstance(String tweet) {
        ComposeTweetDialogFragment frag = new ComposeTweetDialogFragment();
        Bundle args = new Bundle();
        args.putString("body", tweet);
        frag.setArguments(args);
        return frag;
    }
    */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_compose, container);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Get field from view
        etBody = (EditText) view.findViewById(R.id.etBody);
        // Fetch arguments from bundle and set title
        getDialog().setTitle("Compose tweet");
        // Show soft keyboard automatically and request focus to field
            etBody.requestFocus();
        getDialog().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

        Button btnSend = (Button) view.findViewById(R.id.btnSend);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ComposeTweetDialogListener listener = (ComposeTweetDialogListener) getActivity();
                listener.onFinishEditDialog(etBody.getText().toString());
                dismiss();
            }
        });


    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        Button btnSend = (Button) v.findViewById(R.id.btnSend);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ComposeTweetDialogListener listener = (ComposeTweetDialogListener) getActivity();
                listener.onFinishEditDialog(etBody.getText().toString());
                dismiss();
            }
        });
        return true;
    }


    /*@Override
    public void onResume() {
        // Get existing layout params for the window
        ViewGroup.LayoutParams params = getDialog().getWindow().getAttributes();
        // Assign window properties to fill the parent
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = 600;
        getDialog().getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);
        // Call super onResume after sizing
        super.onResume();
    }
*/

}
