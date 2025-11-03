package lotto.controller;

import lotto.view.LottoView;
import lotto.model.LottoMachine;
import lotto.model.LottoNumberGenerator;

public class Application {
    public static void main(String[] args) {
        LottoShop lottoMachine = new LottoShop(new LottoMachine(new LottoNumberGenerator()));
    }
}