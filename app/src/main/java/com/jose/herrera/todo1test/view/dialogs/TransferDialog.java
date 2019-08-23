package com.jose.herrera.todo1test.view.dialogs;

import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.jose.herrera.todo1test.R;
import com.jose.herrera.todo1test.utils.Utils;
import com.jose.herrera.todo1test.view.views.AppEditText;
import com.jose.herrera.todo1test.view.views.AppTitleValueTextView;
import androidx.fragment.app.DialogFragment;

public class TransferDialog extends DialogFragment {

    private AppTitleValueTextView from;
    private AppTitleValueTextView to;
    private String fromAccount;
    private String toAccount;
    private AppEditText amount;
    private boolean isUSD;
    private Button usd;
    private Button cop;

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

        usd = rootView.findViewById(R.id.usd);

        cop = rootView.findViewById(R.id.cop);

        showAccounts();

        showCurrency();

        return rootView;

    }

    public void setUSD(boolean USD) {

        isUSD = USD;

        showCurrency();

    }

    public boolean isUSD() {

        return isUSD;

    }

    public void setAccounts(String from, String to) {

        fromAccount = from;

        toAccount = to;

        showAccounts();

    }

    private void showCurrency(){

        if(usd != null && cop != null) {

            if(isUSD) {

                usd.setBackgroundColor(getResources().getColor(R.color.colorAccent));

                cop.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

            }else{

                cop.setBackgroundColor(getResources().getColor(R.color.colorAccent));

                usd.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

            }
        }

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
