package com.jose.herrera.todo1test.view.dialogs;

import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.jose.herrera.todo1test.R;
import com.jose.herrera.todo1test.utils.Utils;
import com.jose.herrera.todo1test.view.interfaces.AccountInteraction;
import com.jose.herrera.todo1test.view.views.AppEditText;
import com.jose.herrera.todo1test.view.views.AppTitleValueTextView;
import androidx.fragment.app.DialogFragment;

public class TransferDialog extends DialogFragment {

    private AppTitleValueTextView from;
    private AppTitleValueTextView to;
    private String fromAccount;
    private String toAccount;
    private AppEditText amount;

    @Override
    public void onAttach(Context context) {

        setStyle(DialogFragment.STYLE_NO_TITLE, 0);

        super.onAttach(context);

    }

    @Override
    public void onResume() {

        super.onResume();

        Point size = Utils.getDisplayRealSize(getActivity());

        getDialog().getWindow().setLayout((int) (size.x * 0.85), ViewGroup.LayoutParams.WRAP_CONTENT);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.dialog_transfer, container, false);

        from = rootView.findViewById(R.id.from);

        to = rootView.findViewById(R.id.to);

        amount = rootView.findViewById(R.id.field_amount);

        showAccounts();

        return rootView;

    }

    public void setAccounts(String from, String to) {

        fromAccount = from;

        toAccount = to;

        showAccounts();

    }

    private void showAccounts() {

        if(fromAccount != null && toAccount != null &&

        from != null && to != null) {

            from.setTitle(getString(R.string.from));

            to.setTitle(getString(R.string.to));

            from.setText(fromAccount);

            to.setText(toAccount);

        }

    }

    public String getFromAccount() {

        return fromAccount;

    }

    public String getToAccount() {

        return toAccount;

    }

    public int getAmount() throws NumberFormatException {

        return Integer.parseInt(amount.getText().toString());

    }

}
