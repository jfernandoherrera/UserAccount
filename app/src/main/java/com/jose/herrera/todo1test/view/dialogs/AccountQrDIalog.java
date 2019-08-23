package com.jose.herrera.todo1test.view.dialogs;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jose.herrera.todo1test.R;

import androidx.fragment.app.DialogFragment;

public class AccountQrDIalog extends DialogFragment {

    private Bitmap qrCode;
    private ImageView qrCodeImage;

    @Override
    public void onAttach(Context context) {

        setStyle(DialogFragment.STYLE_NO_TITLE, 0);

        super.onAttach(context);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.dialog_account_qr, container, false);

        qrCodeImage = rootView.findViewById(R.id.qrImage);

        showQrImage();

        return rootView;

    }

    public void setQrCode(Bitmap qrCode) {

        this.qrCode = qrCode;

        showQrImage();

    }

    private void showQrImage(){

        if(qrCodeImage != null && qrCode != null) {

            qrCodeImage.setImageBitmap(qrCode);

        }

    }

}
