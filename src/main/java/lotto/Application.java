package lotto;

import lotto.controller.LottoShop;
import lotto.model.Lotto;
import lotto.model.LottoMachine;
import lotto.model.LottoNumberGenerator;
import lotto.model.LottoAmount;
import lotto.model.WinningLotto;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        LottoShop lottoShop = new LottoShop(new LottoMachine(new LottoNumberGenerator()));

        LottoAmount lottoAmount = lottoShop.processPurchaseAmount();
        List<Lotto> lottos = lottoShop.processBuyLotto(lottoAmount.getAmount());
        WinningLotto winningLotto = lottoShop.processWinningLotto();
        lottoShop.processWinningStatistics(lottoAmount, lottos, winningLotto);
    }
}
