package com.example.controlecombustivel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class FuelRepository {
    private static FuelRepository instance;
    private final List<Abastecimento> listaAbastecimentos;

    private FuelRepository() {
        listaAbastecimentos = new ArrayList<>();
    }

    public static synchronized FuelRepository getInstance() {
        if (instance == null) {
            instance = new FuelRepository();
        }
        return instance;
    }

    public void adicionar(Abastecimento a) {
        listaAbastecimentos.add(a);
        Collections.sort(listaAbastecimentos, Comparator.comparingDouble(Abastecimento::getHodometro));
    }

    public List<Abastecimento> getLista() {
        return listaAbastecimentos;
    }

    public Abastecimento getAnterior(Abastecimento atual) {
        int index = listaAbastecimentos.indexOf(atual);
        if (index > 0) {
            return listaAbastecimentos.get(index - 1);
        }
        return null;
    }

    public double calcularConsumoMedio(Abastecimento atual) {
        Abastecimento anterior = getAnterior(atual);
        if (anterior == null || atual.getLitros() <= 0) return 0.0;
        double kmPercorridos = atual.getHodometro() - anterior.getHodometro();
        return kmPercorridos / atual.getLitros();
    }

    public double calcularCustoPorKm(Abastecimento atual) {
        Abastecimento anterior = getAnterior(atual);
        if (anterior == null) return 0.0;
        double kmPercorridos = atual.getHodometro() - anterior.getHodometro();
        if (kmPercorridos <= 0) return 0.0;
        
        double valorTotalGasto = atual.getLitros() * atual.getValorPorLitro();
        return valorTotalGasto / kmPercorridos;
    }

    public double calcularKmPorDia(Abastecimento atual) {
        Abastecimento anterior = getAnterior(atual);
        if (anterior == null) return 0.0;

        double kmPercorridos = atual.getHodometro() - anterior.getHodometro();
        long diffMillis = Math.abs(atual.getDataAsObject().getTime() - anterior.getDataAsObject().getTime());
        long dias = TimeUnit.DAYS.convert(diffMillis, TimeUnit.MILLISECONDS);

        if (dias == 0) dias = 1;
        return kmPercorridos / (double) dias;
    }
}
