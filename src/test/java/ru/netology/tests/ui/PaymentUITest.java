package ru.netology.tests.ui;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import ru.netology.data.DataHelper;
import ru.netology.data.DataBaseHelper;
import ru.netology.pages.DashboardPage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaymentUITest {

    DashboardPage page = open("http://localhost:8080/", DashboardPage.class);

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }


    @Test
    @DisplayName("Card with APPROVED status")
    void shouldSuccessPaymentByCard() {
        var paymentPage = page.paymentButtonClick();
        var cardInfo = DataHelper.getValidDataWithApprovedCard();
        paymentPage.inputData(cardInfo);
        paymentPage.getSuccessNotification();
        var actual = DataBaseHelper.getStatusPaymentRequest();
        assertEquals("APPROVED", actual);
    }

    @Test
    @DisplayName("Card with DECLINED status")
    void shouldDeclinePaymentByCard() {
        var paymentPage = page.paymentButtonClick();
        var cardInfo = DataHelper.getValidDataWithDeclinedCard();
        paymentPage.inputData(cardInfo);
        paymentPage.getErrorNotification();
        var actual = DataBaseHelper.getStatusPaymentRequest();
        assertEquals("DECLINED", actual);
    }

    //Без заполнения полей формы
    @Test
    @DisplayName("Card with empty fields")
    void shouldFailValidationCardWithEmptyFields() {
        var paymentPage = page.paymentButtonClick();
        var cardInfo = DataHelper.getCardInfoWithEmptyFields();
        paymentPage.inputData(cardInfo);
        paymentPage.checkingEmptyField();
    }

    //Картой с невалидным номером длиной 16 цифр
    @Test
    @DisplayName("Card with random number")
    void shouldDeclineTransactionCardWithRandomNumber() {
        var paymentPage = page.paymentButtonClick();
        var cardInfo = DataHelper.getCardInfoWithRandomNumber();
        paymentPage.inputData(cardInfo);
        paymentPage.getErrorNotification();
    }

    //С пустым полем Номер карты
    @Test
    @DisplayName("Card with empty number")
    void shouldFailValidationWithEmptyCardNumber() {
        var paymentPage = page.paymentButtonClick();
        var cardInfo = DataHelper.getCardInfoWithEmptyNumber();
        paymentPage.inputData(cardInfo);
        paymentPage.checkingEmptyField();
    }

    //Картой с невалидным номером длиной 15 цифр
    @Test
    @DisplayName("Card with 15 numbers")
    void shouldFailValidationWithCard15Numbers() {
        var paymentPage = page.paymentButtonClick();
        var cardInfo = DataHelper.getCardInfoWith15();
        paymentPage.inputData(cardInfo);
        paymentPage.checkingWrongFormat();
    }

    //Картой с номером длиной 17 цифр
    @Test
    @DisplayName("Card with 17 numbers")
    void shouldFailValidationWithCard17Numbers() {
        var paymentPage = page.paymentButtonClick();
        var cardInfo = DataHelper.getCardInfoWith17();
        paymentPage.inputData(cardInfo);
        paymentPage.checkingWrongFormat();
    }

    //Картой с номером, состоящим из спецсимволов
    @Test
    @DisplayName("Card with with special characters")
    void shouldFailValidationCardWithSpecialCharacters() {
        var paymentPage = page.paymentButtonClick();
        var cardInfo = DataHelper.getCardInfoWithSpecialCharacters();
        paymentPage.inputData(cardInfo);
        paymentPage.checkingWrongFormat();
    }

    //C пустым полем Месяц
    @Test
    @DisplayName("Card with empty month")
    void shouldFailValidationCardWithEmptyMonth() {
        var paymentPage = page.paymentButtonClick();
        var cardInfo = DataHelper.getCardInfoWithNullMonth();
        paymentPage.inputData(cardInfo);
        paymentPage.checkingEmptyField();
    }

    //Cо значением 00 в поле Месяц
    @Test
    @DisplayName("Card with zero in month")
    void shouldFailValidationCardWithZeroInMonth() {
        var paymentPage = page.paymentButtonClick();
        var cardInfo = DataHelper.getCardInfoWithMonthWithTwoZero();
        paymentPage.inputData(cardInfo);
        paymentPage.checkingWrongDateFormat();
    }

    //Cо значением 13 в поле Месяц getCardInfoWith13Month
    @Test
    @DisplayName("Card with month number 13")
    void shouldFailValidationWithMonthAbove12() {
        var paymentPage = page.paymentButtonClick();
        var cardInfo = DataHelper.getCardInfoWith13Month();
        paymentPage.inputData(cardInfo);
        paymentPage.checkingWrongDateFormat();
    }

    //Cо значением, состоящим из одной цифры в поле Месяц
    @Test
    @DisplayName("Card with month with one number")
    void shouldFailValidationCardWith1NumberMonth() {
        var paymentPage = page.paymentButtonClick();
        var cardInfo = DataHelper.getCardInfoWithMonthWith1Number();
        paymentPage.inputData(cardInfo);
        paymentPage.checkingWrongFormat();
    }

    //Cо значением, состоящим из спецсимвола в поле Месяц
    @Test
    @DisplayName("Card with month with special characters")
    void shouldFailValidationCardWithMonthSpecialCharacters() {
        var paymentPage = page.paymentButtonClick();
        var cardInfo = DataHelper.getCardInfoWithMonthWithSpecialCharacters();
        paymentPage.inputData(cardInfo);
        paymentPage.checkingWrongFormat();
    }

    //С пустым полем Год
    @Test
    @DisplayName("Card with empty year")
    void shouldFailValidationCardWithEmptyYear() {
        var paymentPage = page.paymentButtonClick();
        var cardInfo = DataHelper.getCardInfoWithNullYear();
        paymentPage.inputData(cardInfo);
        paymentPage.checkingEmptyField();
    }

    //Со значением в поле Год, ранее текущего года
    @Test
    @DisplayName("Card with last year")
    void shouldFailValidationCardWithLastYear() {
        var paymentPage = page.paymentButtonClick();
        var cardInfo = DataHelper.getCardInfoWithLastYear();
        paymentPage.inputData(cardInfo);
        paymentPage.checkingCardEnded();
    }

    //Со спецсимволами в поле Год
    @Test
    @DisplayName("Card with year with special characters")
    void shouldFailValidationCardWithSpecialCharactersYear() {
        var paymentPage = page.paymentButtonClick();
        var cardInfo = DataHelper.getCardInfoWithSpecialCharactersYear();
        paymentPage.inputData(cardInfo);
        paymentPage.checkingWrongFormat();
    }

    //С одной цифрой в поле Год
    @Test
    @DisplayName("Card with year with one number")
    void shouldFailValidationCardWith1NumberYear() {
        var paymentPage = page.paymentButtonClick();
        var cardInfo = DataHelper.getCardInfoWith1NumberYear();
        paymentPage.inputData(cardInfo);
        paymentPage.checkingWrongFormat();
    }

    //С пустым полем Владелец
    @Test
    @DisplayName("Card with empty owner")
    void shouldFailValidationCardWithEmptyOwner() {
        var paymentPage = page.paymentButtonClick();
        var cardInfo = DataHelper.getCardInfoWithNullOwner();
        paymentPage.inputData(cardInfo);
        paymentPage.checkingEmptyField();
    }

    //С цифрами в поле Владелец
    @Test
    @DisplayName("Card with owner with name of numbers")
    void shouldFailValidationCardWithOwnerWithNumbers() {
        var paymentPage = page.paymentButtonClick();
        var cardInfo = DataHelper.getCardInfoWithOwnerWithNumbers();
        paymentPage.inputData(cardInfo);
        paymentPage.checkingWrongFormat();
    }

    //Со спецсимволами в поле Владелец
    @Test
    @DisplayName("Card with owner with name with special characters")
    void shouldFailValidationCardWithNameSpecialCharacters() {
        var paymentPage = page.paymentButtonClick();
        var cardInfo = DataHelper.getCardInfoWithOwnerSpecialCharacters();
        paymentPage.inputData(cardInfo);
        paymentPage.checkingWrongFormat();
    }

    //Со значением в поле Владелец, набранным кириллицей
    @Test
    @DisplayName("Card with owner with name with cyrillic")
    void shouldFailValidationCardWithNameWithCyrillic() {
        var paymentPage = page.paymentButtonClick();
        var cardInfo = DataHelper.getCardInfoWithOwnerCyrillic();
        paymentPage.inputData(cardInfo);
        paymentPage.checkingWrongFormat();
    }

    //С пустым полем CVC/CVV
    @Test
    @DisplayName("Card with empty CVC")
    void shouldFailValidationCardWithEmptyCVC() {
        var paymentPage = page.paymentButtonClick();
        var cardInfo = DataHelper.getCardInfoWithEmptyCVC();
        paymentPage.inputData(cardInfo);
        paymentPage.checkingEmptyField();
    }

    //С двумя цифрами в поле CVC/CVV
    @Test
    @DisplayName("Card with 2 numbers in CVC")
    void shouldFailValidationCardWith2NumbersInCVC() {
        var paymentPage = page.paymentButtonClick();
        var cardInfo = DataHelper.getCardInfoWithCVC2Numbers();
        paymentPage.inputData(cardInfo);
        paymentPage.checkingWrongFormat();
    }

    //С буквами в поле CVC/CVV
    @Test
    @DisplayName("Card with special characters in CVC")
    void shouldFailValidationCardWithWithCVCSpecialCharacters() {
        var paymentPage = page.paymentButtonClick();
        var cardInfo = DataHelper.getCardInfoWithCVCSpecialCharacters();
        paymentPage.inputData(cardInfo);
        paymentPage.checkingWrongFormat();
    }

    //Со спецсимволами в поле CVC/CVV
    @Test
    @DisplayName("Card with letters in CVC")
    void shouldFailValidationCardWithCVCLetter() {
        var paymentPage = page.paymentButtonClick();
        var cardInfo = DataHelper.getCardInfoWithCVCLetter();
        paymentPage.inputData(cardInfo);
        paymentPage.checkingWrongFormat();
    }
}
