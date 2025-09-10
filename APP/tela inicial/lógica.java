package com.web.web.nanoprata;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private WebView webView;
    private EditText addressBar;
    private ImageButton goButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Conecta as variáveis Java com os elementos do layout
        webView = findViewById(R.id.webview);
        addressBar = findViewById(R.id.address_bar);
        goButton = findViewById(R.id.go_button);

        // Configura a WebView para exibir páginas no seu aplicativo
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());

        // Carrega uma página inicial
        webView.loadUrl("https://www.google.com");

        // Lógica para o botão de "Ir"
        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = addressBar.getText().toString();
                if (!url.startsWith("http://") && !url.startsWith("https://")) {
                    url = "https://" + url;
                }
                webView.loadUrl(url);
            }
        });

        // Lógica para a tecla "Enter" no teclado
        addressBar.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_GO) {
                    goButton.performClick();
                    return true;
                }
                return false;
            }
        });
    }

    // Lógica para o botão "Voltar" do celular
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}