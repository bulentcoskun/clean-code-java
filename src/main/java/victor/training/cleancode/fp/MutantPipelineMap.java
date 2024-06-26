package victor.training.cleancode.fp;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class MutantPipelineMap {
  private final PaymentCardRepository paymentCardRepository;
  private final PaymentCardMapper paymentCardMapper;

  public PaymentCardResponseDto setUserAliasCard(final long ssoId, final long paymentCardId, final String alias) {
    return paymentCardRepository.findById(paymentCardId)
        .filter(card -> card.getId() == ssoId)
        .map(card -> {
          card.setUserAlias(alias);
          return paymentCardMapper.toDto(paymentCardRepository.save(card));
        })
        .orElseThrow(() -> new IllegalArgumentException("Card " + paymentCardId + " with sso " + ssoId + " cannot be found"));
  }

  interface PaymentCardRepository {
    Optional<PaymentCard> findById(long paymentCardId);

    PaymentCard save(PaymentCard card);
  }

  interface PaymentCardMapper {
    PaymentCardResponseDto toDto(PaymentCard card);
  }

  record PaymentCardResponseDto(long id, String alias) {
  }

  @Data
  static class PaymentCard {
    long id;
    String userAlias;
  }
}
