package com.jose.herrera.todo1test.view.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.jose.herrera.todo1test.R;
import com.jose.herrera.todo1test.model.domain.account.Account;
import com.jose.herrera.todo1test.view.interfaces.AccountInteraction;
import com.jose.herrera.todo1test.view.views.AppTitleValueTextView;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AccountsAdapter extends RecyclerView.Adapter {

    private List<Account> accountList;
    private AccountInteraction accountInteraction;

    public AccountsAdapter(List<Account> accountList, AccountInteraction accountInteraction) {

        this.accountList = accountList;
        this.accountInteraction = accountInteraction;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_account, parent, false);

        AccountHolder accountHolder = new AccountHolder(v);

        return accountHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        final Account account = accountList.get(position);

        AccountHolder accountHolder = (AccountHolder) holder;

        accountHolder.id.setText(account.getId());

        accountHolder.balance.setText(String.valueOf(account.getBalance()));

        accountHolder.generateQr.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                accountInteraction.onGenerateQr(account.getId());

            }

        });

        accountHolder.transfer.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                accountInteraction.onTransfer(account.getId());

            }

        });

    }

    @Override
    public int getItemCount() {

        return accountList.size();

    }


    private class AccountHolder extends RecyclerView.ViewHolder {

        private TextView id;
        private AppTitleValueTextView balance;
        private Button generateQr;
        private Button transfer;

        private AccountHolder(@NonNull View itemView) {

            super(itemView);

            id = itemView.findViewById(R.id.accountId);

            balance = itemView.findViewById(R.id.balance);

            balance.setTitle(itemView.getContext().getString(R.string.balance));

            generateQr = itemView.findViewById(R.id.generateQrButton);

            transfer = itemView.findViewById(R.id.transferButton);

        }

    }

}
