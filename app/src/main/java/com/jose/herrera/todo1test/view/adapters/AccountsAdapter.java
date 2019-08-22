package com.jose.herrera.todo1test.view.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.jose.herrera.todo1test.R;
import com.jose.herrera.todo1test.model.domain.Account;
import com.jose.herrera.todo1test.view.views.AppTitleValueTextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AccountsAdapter extends RecyclerView.Adapter {

    private List<Account> accountList;

    public AccountsAdapter(List<Account> accountList) {

        this.accountList = accountList;

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

        Account account = accountList.get(position);

        AccountHolder accountHolder = (AccountHolder) holder;

        accountHolder.id.setText(account.getId());

        accountHolder.balance.setText(String.valueOf(account.getBalance()));

    }

    @Override
    public int getItemCount() {

        return accountList.size();

    }


    private class AccountHolder extends RecyclerView.ViewHolder {

        private TextView id;
        private AppTitleValueTextView balance;

        private AccountHolder(@NonNull View itemView) {

            super(itemView);

            id = itemView.findViewById(R.id.accountId);

            balance = itemView.findViewById(R.id.balance);

            balance.setTitle(itemView.getContext().getString(R.string.balance));

        }

    }

}
