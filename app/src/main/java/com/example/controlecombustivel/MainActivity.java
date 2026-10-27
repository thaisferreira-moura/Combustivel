package com.example.controlecombustivel;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etData, etHodometro, etLitros, etValorLitro;
    private RadioGroup rgCombustivel;
    private EditText etPrecoEtanol, etPrecoGasolina;
    private TextView tvResultadoFlex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etData = findViewById(R.id.etData);
        etHodometro = findViewById(R.id.etHodometro);
        etLitros = findViewById(R.id.etLitros);
        etValorLitro = findViewById(R.id.etValorLitro);
        rgCombustivel = findViewById(R.id.rgCombustivel);

        etPrecoEtanol = findViewById(R.id.etPrecoEtanol);
        etPrecoGasolina = findViewById(R.id.etPrecoGasolina);
        tvResultadoFlex = findViewById(R.id.tvResultadoFlex);

        Button btnSalvar = findViewById(R.id.btnSalvar);
        Button btnVerHistorico = findViewById(R.id.btnVerHistorico);
        Button btnCalcularFlex = findViewById(R.id.btnCalcularFlex);

        btnSalvar.setOnClickListener(v -> salvarAbastecimento());
        btnVerHistorico.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, HistoricoActivity.class);
            startActivity(intent);
        });

        btnCalcularFlex.setOnClickListener(v -> calcularOpcaoVantajosa());
    }

    private void salvarAbastecimento() {
        String data = etData.getText().toString().trim();
        String hodometroStr = etHodometro.getText().toString().trim();
        String litrosStr = etLitros.getText().toString().trim();
        String valorStr = etValorLitro.getText().toString().trim();

        if (data.isEmpty() || hodometroStr.isEmpty() || litrosStr.isEmpty() || valorStr.isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos do abastecimento!", Toast.LENGTH_SHORT).show();
            return;
        }

        double hodometro = Double.parseDouble(hodometroStr);
        double litros = Double.parseDouble(litrosStr);
        double valorLitro = Double.parseDouble(valorStr);

        int selectedId = rgCombustivel.getCheckedRadioButtonId();
        RadioButton rbSelecionado = findViewById(selectedId);
        String tipoCombustivel = rbSelecionado.getText().toString();

        Abastecimento novo = new Abastecimento(data, hodometro, litros, tipoCombustivel, valorLitro);
        FuelRepository.getInstance().adicionar(novo);

        Toast.makeText(this, "Abastecimento registrado com sucesso!", Toast.LENGTH_SHORT).show();

        etHodometro.setText("");
        etLitros.setText("");
        etValorLitro.setText("");
    }

    private void calcularOpcaoVantajosa() {
        String etanolStr = etPrecoEtanol.getText().toString().trim();
        String gasolinaStr = etPrecoGasolina.getText().toString().trim();

        if (etanolStr.isEmpty() || gasolinaStr.isEmpty()) {
            Toast.makeText(this, "Informe os preços do Etanol e da Gasolina!", Toast.LENGTH_SHORT).show();
            return;
        }

        double precoEtanol = Double.parseDouble(etanolStr);
        double precoGasolina = Double.parseDouble(gasolinaStr);

        double raz = precoEtanol / precoGasolina;

        if (raz <= 0.70) {
            tvResultadoFlex.setText(String.format("Abasteça com ETANOL (Relação: %.2f%%)", raz * 100));
        } else {
            tvResultadoFlex.setText(String.format("Abasteça com GASOLINA (Relação: %.2f%%)", raz * 100));
        }
    }
}
