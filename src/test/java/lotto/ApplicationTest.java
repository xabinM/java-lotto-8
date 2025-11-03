package lotto;

import lotto.controller.LottoShop;
import lotto.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ApplicationTest {

    private LottoShop lottoShop;

    @BeforeEach
    void setUp() {
        lottoShop = new LottoShop(new LottoMachine(new LottoNumberGenerator()));
    }

    @Test
    void 기능_테스트() {
        LottoAmount amount = new LottoAmount(8000);

        List<Lotto> lottos = lottoShop.processBuyLotto(amount.getAmount());

        WinningLotto winningLotto = new WinningLotto(
                List.of(1, 2, 3, 4, 5, 6),
                7
        );

        lottoShop.processWinningStatistics(amount, lottos, winningLotto);

        assertThat(lottos).hasSize(8);

        List<Integer> firstLottoNumbers = lottos.get(0).getNumbers();
        assertThat(firstLottoNumbers).hasSize(6);
    }

    @Test
    void 예외_테스트() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new LottoAmount(1000 + 1)
        );

        assertThat(exception.getMessage()).contains("[ERROR]");
    }
}
