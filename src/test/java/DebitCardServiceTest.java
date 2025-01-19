import onlinebank.dao.DebitCardDAO;
import onlinebank.dao.UserDAO;
import onlinebank.models.DebitCard;
import onlinebank.models.DepositRequest;
import onlinebank.models.TransferRequest;
import onlinebank.services.DebitCardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class DebitCardServiceTest {

    @Mock
    private DebitCardDAO debitCardDAO;

    @Mock
    private UserDAO userDAO;

    @InjectMocks
    private DebitCardService debitCardService;

    private DebitCard debitCard;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        debitCard = new DebitCard("1234567812345678", LocalDate.now(), LocalDate.now().plusYears(3), 123, 500.0, 12345);
    }

    @Test
    void testGetAllDebitcards() {
        when(debitCardDAO.getAllDebitcards()).thenReturn(List.of(debitCard));

        var result = debitCardService.getAllDebitcards();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(debitCardDAO, times(1)).getAllDebitcards();
    }

    @Test
    void testSaveDebitCard_UserNotFound() {
        when(userDAO.existsByPassportNumber(debitCard.getPassportNumber())).thenReturn(false);

        try {
            debitCardService.save(debitCard);
        } catch (IllegalArgumentException e) {
            assertEquals("No user found with passport number 12345", e.getMessage());
        }

        verify(userDAO, times(1)).existsByPassportNumber(debitCard.getPassportNumber());
        verify(debitCardDAO, times(0)).save(any());
    }

    @Test
    void testSaveDebitCard_UserFound() {
        when(userDAO.existsByPassportNumber(debitCard.getPassportNumber())).thenReturn(true);

        debitCardService.save(debitCard);

        verify(debitCardDAO, times(1)).save(debitCard);
    }

    @Test
    void testUpdateDebitCard() {
        when(debitCardDAO.findByCardNumber(debitCard.getCardNumber())).thenReturn(debitCard);

        DebitCard updatedDebitCard = new DebitCard("1234567812345678", LocalDate.now(), LocalDate.now().plusYears(3), 123, 1000.0, 12345);
        debitCardService.update(debitCard.getCardNumber(), updatedDebitCard);

        verify(debitCardDAO, times(1)).update(debitCard.getCardNumber(), updatedDebitCard);
    }

    @Test
    void testDeleteDebitCard() {
        when(debitCardDAO.findByCardNumber(debitCard.getCardNumber())).thenReturn(debitCard);

        debitCardService.delete(debitCard.getCardNumber(), debitCard.getPassportNumber());

        verify(debitCardDAO, times(1)).delete(debitCard.getCardNumber(), debitCard.getPassportNumber());
    }

    @Test
    void testTransferMoney_Success() {
        TransferRequest transferRequest = new TransferRequest("1234567812345678", "8765432187654321", 100.0);

        DebitCard sender = new DebitCard("1234567812345678", LocalDate.now(), LocalDate.now().plusYears(3), 123, 500.0, 12345);
        DebitCard receiver = new DebitCard("8765432187654321", LocalDate.now(), LocalDate.now().plusYears(3), 123, 200.0, 12346);

        when(debitCardDAO.findByCardNumber(transferRequest.getSenderCardNumber())).thenReturn(sender);
        when(debitCardDAO.findByCardNumber(transferRequest.getReceiverCardNumber())).thenReturn(receiver);

        debitCardService.transferMoney(transferRequest);

        // Use delta to handle floating-point comparison
        assertEquals(400.0, sender.getCardBalance(), 0.0001);  // delta of 0.0001
        assertEquals(300.0, receiver.getCardBalance(), 0.0001); // delta of 0.0001

        verify(debitCardDAO, times(1)).update(sender.getCardNumber(), sender);
        verify(debitCardDAO, times(1)).update(receiver.getCardNumber(), receiver);
    }

    @Test
    void testTransferMoney_InsufficientBalance() {
        TransferRequest transferRequest = new TransferRequest("1234567812345678", "8765432187654321", 600.0);

        DebitCard sender = new DebitCard("1234567812345678", LocalDate.now(), LocalDate.now().plusYears(3), 123, 500.0, 12345);
        DebitCard receiver = new DebitCard("8765432187654321", LocalDate.now(), LocalDate.now().plusYears(3), 123, 200.0, 12346);

        when(debitCardDAO.findByCardNumber(transferRequest.getSenderCardNumber())).thenReturn(sender);
        when(debitCardDAO.findByCardNumber(transferRequest.getReceiverCardNumber())).thenReturn(receiver);

        try {
            debitCardService.transferMoney(transferRequest);
        } catch (IllegalArgumentException e) {
            assertEquals("There is not enough money on the card.", e.getMessage());
        }

        verify(debitCardDAO, times(0)).update(any(), any());
    }

    @Test
    void testDepositMoney_Success() {
        DepositRequest depositRequest = new DepositRequest("1234567812345678", 100.0);

        DebitCard receiver = new DebitCard("1234567812345678", LocalDate.now(), LocalDate.now().plusYears(3), 123, 500.0, 12345);

        when(debitCardDAO.findByCardNumber(depositRequest.getCardNumber())).thenReturn(receiver);

        debitCardService.depositMoney(depositRequest);

        // Use delta to handle floating-point comparison
        assertEquals(600.0, receiver.getCardBalance(), 0.0001); // delta of 0.0001
        verify(debitCardDAO, times(1)).update(receiver.getCardNumber(), receiver);
    }


    @Test
    void testDepositMoney_InvalidCardNumber() {
        DepositRequest depositRequest = new DepositRequest("1234567812345678", 100.0);

        when(debitCardDAO.findByCardNumber(depositRequest.getCardNumber())).thenReturn(null);

        try {
            debitCardService.depositMoney(depositRequest);
        } catch (IllegalArgumentException e) {
            assertEquals("The receiver card number is invalid.", e.getMessage());
        }

        verify(debitCardDAO, times(0)).update(any(), any());
    }

    @Test
    void testDepositMoney_InvalidAmount() {
        DepositRequest depositRequest = new DepositRequest("1234567812345678", -100.0);

        DebitCard receiver = new DebitCard("1234567812345678", LocalDate.now(), LocalDate.now().plusYears(3), 123, 500.0, 12345);

        when(debitCardDAO.findByCardNumber(depositRequest.getCardNumber())).thenReturn(receiver);

        try {
            debitCardService.depositMoney(depositRequest);
        } catch (IllegalArgumentException e) {
            assertEquals("Deposit amount must be greater than zero.", e.getMessage());
        }

        verify(debitCardDAO, times(0)).update(any(), any());
    }
}