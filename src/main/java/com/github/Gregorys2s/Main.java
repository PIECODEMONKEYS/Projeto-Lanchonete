package com.github.Gregorys2s;

import com.github.Gregorys2s.config.FlyWayConfig;
import com.github.Gregorys2s.config.HibernateConfig;


public class Main {
    public static void main(String[] args) {

        // 1️⃣ Migra o banco
        FlyWayConfig.migrate();

        // 2️⃣ Executa regra de negócio
        EstoqueBD estoque = new EstoqueBD();
        //estoque.cadastrarIngrediente("Presunto", 40);

        // 3️⃣ Encerra recursos
        HibernateConfig.shutdown();

        System.out.println("Aplicação finalizada ✅");


    }
}
