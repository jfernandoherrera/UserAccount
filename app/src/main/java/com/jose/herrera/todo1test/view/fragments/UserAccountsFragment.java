package com.jose.herrera.todo1test.view.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.jose.herrera.todo1test.R;
import com.jose.herrera.todo1test.model.context.weather.remote.WeatherEndpoints;
import com.jose.herrera.todo1test.model.domain.account.Account;
import com.jose.herrera.todo1test.model.domain.weather.Weather;
import com.jose.herrera.todo1test.view.adapters.AccountsAdapter;
import com.jose.herrera.todo1test.view.interfaces.AccountInteraction;
import com.squareup.picasso.Picasso;

import java.util.List;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class UserAccountsFragment extends Fragment {

    private Weather weather;
    private RecyclerView accountsList;
    private AccountsAdapter accounts;
    private Context context;
    private TextView emailView;
    private TextView weatherView;
    private String email;
    private ImageView weatherIcon;

    @Override
    public void onAttach(Context context) {

        super.onAttach(context);

        this.context = context;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,

                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_user_accounts, container, false);

        accountsList = rootView.findViewById(R.id.recyclerViewAccounts);

        emailView = rootView.findViewById(R.id.userEmail);

        weatherView = rootView.findViewById(R.id.userWeather);

        weatherIcon = rootView.findViewById(R.id.iconWeather);

        LinearLayoutManager llm = new LinearLayoutManager(context);

        accountsList.setLayoutManager(llm);

        showAccounts();

        showEmail();

        showWeather();

        return rootView;

    }

    public void setAccounts(List<Account> accounts, AccountInteraction accountInteraction) {

        this.accounts = new AccountsAdapter(accounts, accountInteraction);

        this.showAccounts();

    }

    public void setWeather(Weather weather) {

        this.weather = weather;

        showWeather();

    }

    private void showWeather() {

        if(weather != null && weatherView != null) {

            weatherView.setText(weather.getDescription());

            Picasso.get().load(WeatherEndpoints.ICON_URL + weather.getIcon() + WeatherEndpoints.iCON_FORMAT)

                    .into(weatherIcon);

        }

    }

    public void setEmail(String email) {

        this.email = email;

        showEmail();

    }

    private void showEmail() {

        if(emailView != null && email != null) {

            emailView.setText(email);

        }

    }

    public void showAccounts() {

        if(accountsList != null) {

            accountsList.setAdapter(accounts);

        }

    }

}