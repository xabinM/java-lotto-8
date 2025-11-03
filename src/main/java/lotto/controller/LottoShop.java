package lotto.controller;

import lotto.model.*;
import lotto.view.LottoView;

import java.util.List;

public class LottoShop {
    private final LottoMachine lottoMachine;

    public LottoShop(LottoMachine lottoMachine) {
        this.lottoMachine = lottoMachine;
    }

    // 구매 금액 처리
    public LottoAmount processPurchaseAmount() {
        while (true) {
            try {
                return new LottoAmount(LottoView.requestPurchasePrice());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    // 로또 구매 처리
    public List<Lotto> processBuyLotto(int amount) {
        List<Lotto> lottos = lottoMachine.buyLottos(amount);
        LottoView.printLottos(lottos);
        return lottos;
    }

    // 당첨 로또 처리
    public WinningLotto processWinningLotto() {
        List<Integer> winningNumbers = drawWinningNumbers();
        int bonusNumber = drawBonusNumber(winningNumbers);
        return new WinningLotto(winningNumbers, bonusNumber);
    }

    private List<Integer> drawWinningNumbers() {
        while (true) {
            try {
                return LottoView.requestWinningNumbers();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private int drawBonusNumber(List<Integer> winningNumbers) {
        while (true) {
            try {
                return LottoView.requestBonusNumber(winningNumbers);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    // 통계 처리
    public void processWinningStatistics(LottoAmount lottoAmount, List<Lotto> lottos, WinningLotto winningLotto) {
        List<LottoResult> lottoResults = StatisticsCalculator.checkLotto(lottos, winningLotto);
        RankStorage rankStorage = new RankStorage();
        StatisticsCalculator.calculateRankCounts(lottoResults, rankStorage);
        LottoView.printWinningStatistics(lottoAmount, rankStorage);
    }
}
