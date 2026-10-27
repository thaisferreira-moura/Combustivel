package com.example.controlecombustivel;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class HistoricoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        ListView listView = new ListView(this);
        setContentView(listView);

        FuelRepository repo = FuelRepository.getInstance();
        List<Abastecimento> lista = repo.getLista();
        List<String> exibicao = new ArrayList<>();

        for (int i = 0; i < lista.size(); i++) {
            Abastecimento item = lista.get(i);
            StringBuilder sb = new StringBuilder();

            sb.append("Data: ").append(item.getData()).append("
")
              .append("Hodômetro: ").append(item.getHodometro()).append(" km
")
              .append("Litros: ").append(item.getLitros()).append(" L | ")
              .append("Tipo: ").append(item.getTipoCombustivel()).append("
")
              .append("Valor/L: R$ ").append(String.format(Locale.getDefault(), "%.2f", item.getValorPorLitro())).append("
");

            if (i == 0) {
                sb.append("
[Primeiro registro - Aguardando próximo abastecimento para métricas]");
            } else {
                double consumoMedio = repo.calcularConsumoMedio(item);
                double custoKm = repo.calcularCustoPorKm(item);
                double kmDia = repo.calcularKmPorDia(item);

                sb.append("
--- Análises desta viagem ---")
                  .append(String.format(Locale.getDefault(), "
- Consumo médio: %.2f km/l", consumoMedio))
                  .append(String.format(Locale.getDefault(), "
- Custo efetivo: R$ %.2f / km", custoKm))
                  .append(String.format(Locale.getDefault(), "
- Média diária: %.2f km/dia", kmDia));
            }

            exibicao.add(sb.toString());
        }

        if (exibicao.isEmpty()) {
            exibicao.add("Nenhum abastecimento cadastrado.");
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                exibicao
        );
        listView.setAdapter(adapter);
    }
}
