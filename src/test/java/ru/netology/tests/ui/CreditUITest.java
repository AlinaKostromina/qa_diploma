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

public class CreditUITest {

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
        var creditPage = page.creditButtonClick();
        var cardInfo = DataHelper.getValidDataWithApprovedCard();
        creditPage.inputData(cardInfo);
        creditPage.getSuccessNotification();
        var actual = DataBaseHelper.getStatusCreditRequest();
        assertEquals("APPROVED", actual);
    }

    @Test
    @DisplayName("Card with DECLINED status")
    void shouldDeclinePaymentByCard() {
        var creditPage = page.creditButtonClick();
        var cardInfo = DataHelper.getValidDataWithDeclinedCard();
        creditPage.inputData(cardInfo);
        creditPage.getErrorNotification();
        var actual = DataBaseHelper.getStatusCreditRequest();
        assertEquals("DECLINED", actual);
    }

    //Без заполнения полей формы
    @Test
    @DisplayName("Card with empty fields")
    void shouldFailValidationCardWithEmptyFields() {
        var creditPage = page.creditButtonClick();
        var cardInfo = DataHelper.getCardInfoWithEmptyFields();
        creditPage.inputData(cardInfo);
        creditPage.checkingEmptyField();
    }

    //Картой с невалидным номером длиной 16 цифр
    @Test
    @DisplayName("Card with random number")
    void shouldDeclineTransactionCardWithRandomNumber() {
        var creditPage = page.creditButtonClick();
        var cardInfo = DataHelper.getCardInfoWithRandomNumber();
        creditPage.inputData(cardInfo);
        creditPage.getErrorNotification();
    }

    //С пустым полем Номер карты
    @Test
    @DisplayName("Card with empty number")
    void shouldFailValidationWithEmptyCardNumber() {
        var creditPage = page.creditButtonClick();
        var cardInfo = DataHelper.getCardInfoWithEmptyNumber();
        creditPage.inputData(cardInfo);
        creditPage.checkingEmptyField();
    }

    //Картой с невалидным номером длиной 15 цифр
    @Test
    @DisplayName("Card with 15 numbers")
    void shouldFailValidationWithCard15Numbers() {
        var creditPage = page.creditButtonClick();
        var cardInfo = DataHelper.getCardInfoWith15();
        creditPage.inputData(cardInfo);
        creditPage.checkingWrongFormat();
    }

    //Картой с номером длиной 17 цифр
    @Test
    @DisplayName("Card with 17 numbers")
    void shouldFailValidationWithCard17Numbers() {
        var creditPage = page.creditButtonClick();
        var cardInfo = DataHelper.getCardInfoWith17();
        creditPage.inputData(cardInfo);
        creditPage.checkingWrongFormat();
    }

    //Картой с номером, состоящим из спецсимволов
    @Test
    @DisplayName("Card with with special characters")
    void shouldFailValidationCardWithSpecialCharacters() {
        var creditPage = page.creditButtonClick();
        var cardInfo = DataHelper.getCardInfoWithSpecialCharacters();
        creditPage.inputData(cardInfo);
        creditPage.checkingWrongFormat();
    }

    //C пустым полем Месяц
    @Test
    @DisplayName("Card with empty month")
    void shouldFailValidationCardWithEmptyMonth() {
        var creditPage = page.creditButtonClick();
        var cardInfo = DataHelper.getCardInfoWithNullMonth();
        creditPage.inputData(cardInfo);
        creditPage.checkingEmptyField();
    }

    //Cо значением 00 в поле Месяц
    @Test
    @DisplayName("Card with zero in month")
    void shouldFailValidationCardWithZeroInMonth() {
        var creditPage = page.creditButtonClick();
        var cardInfo = DataHelper.getCardInfoWithMonthWithTwoZero();
        creditPage.inputData(cardInfo);
        creditPage.checkingWrongDateFormat();
    }

    //Cо значением 13 в поле Месяц getCardInfoWith13Month
    @Test
    @DisplayName("Card with month number 13")
    void shouldFailValidationWithMonthAbove12() {
        var creditPage = page.creditButtonClick();
        var cardInfo = DataHelper.getCardInfoWith13Month();
        creditPage.inputData(cardInfo);
        creditPage.checkingWrongDateFormat();
    }

    //Cо значением, состоящим из одной цифры в поле Месяц
    @Test
    @DisplayName("Card with month with one number")
    void shouldFailValidationCardWith1NumberMonth() {
        var creditPage = page.creditButtonClick();
        var cardInfo = DataHelper.getCardInfoWithMonthWith1Number();
        creditPage.inputData(cardInfo);
        creditPage.checkingWrongFormat();
    }

    //Cо значением, состоящим из спецсимвола в поле Месяц
    @Test
    @DisplayName("Card with month with special characters")
    void shouldFailValidationCardWithMonthSpecialCharacters() {
        var creditPage = page.creditButtonClick();
        var cardInfo = DataHelper.getCardInfoWithMonthWithSpecialCharacters();
        creditPage.inputData(cardInfo);
        creditPage.checkingWrongFormat();
    }

    //С пустым полем Год
    @Test
    @DisplayName("Card with empty year")
    void shouldFailValidationCardWithEmptyYear() {
        var creditPage = page.creditButtonClick();
        var cardInfo = DataHelper.getCardInfoWithNullYear();
        creditPage.inputData(cardInfo);
        creditPage.checkingEmptyField();
    }

    //Со значением в поле Год, ранее текущего года
    @Test
    @DisplayName("Card with last year")
    void shouldFailValidationCardWithLastYear() {
        var creditPage = page.creditButtonClick();
        var cardInfo = DataHelper.getCardInfoWithLastYear();
        creditPage.inputData(cardInfo);
        creditPage.checkingCardEnded();
    }

    //Со спецсимволами в поле Год
    @Test
    @DisplayName("Card with year with special characters")
    void shouldFailValidationCardWithSpecialCharactersYear() {
        var creditPage = page.creditButtonClick();
        var cardInfo = DataHelper.getCardInfoWithSpecialCharactersYear();
        creditPage.inputData(cardInfo);
        creditPage.checkingWrongFormat();
    }

    //С одной цифрой в поле Год
    @Test
    @DisplayName("Card with year with one number")
    void shouldFailValidationCardWith1NumberYear() {
        var creditPage = page.creditButtonClick();
        var cardInfo = DataHelper.getCardInfoWith1NumberYear();
        creditPage.inputData(cardInfo);
        creditPage.checkingWrongFormat();
    }

    //С пустым полем Владелец
    @Test
    @DisplayName("Card with empty owner")
    void shouldFailValidationCardWithEmptyOwner() {
        var creditPage = page.creditButtonClick();
        var cardInfo = DataHelper.getCardInfoWithNullOwner();
        creditPage.inputData(cardInfo);
        creditPage.checkingEmptyField();
    }

    //С цифрами в поле Владелец
    @Test
    @DisplayName("Card with owner with name of numbers")
    void shouldFailValidationCardWithOwnerWithNumbers() {
        var creditPage = page.creditButtonClick();
        var cardInfo = DataHelper.getCardInfoWithOwnerWithNumbers();
        creditPage.inputData(cardInfo);
        creditPage.checkingWrongFormat();
    }

    //Со спецсимволами в поле Владелец
    @Test
    @DisplayName("Card with owner with name with special characters")
    void shouldFailValidationCardWithNameSpecialCharacters() {
        var creditPage = page.creditButtonClick();
        var cardInfo = DataHelper.getCardInfoWithOwnerSpecialCharacters();
        creditPage.inputData(cardInfo);
        creditPage.checkingWrongFormat();
    }

    //Со значением в поле Владелец, набранным кириллицей
    @Test
    @DisplayName("Card with owner with name with cyrillic")
    void shouldFailValidationCardWithNameWithCyrillic() {
        var creditPage = page.creditButtonClick();
        var cardInfo = DataHelper.getCardInfoWithOwnerCyrillic();
        creditPage.inputData(cardInfo);
        creditPage.checkingWrongFormat();
    }

    //С пустым полем CVC/CVV
    @Test
    @DisplayName("Card with empty CVC")
    void shouldFailValidationCardWithEmptyCVC() {
        var creditPage = page.creditButtonClick();
        var cardInfo = DataHelper.getCardInfoWithEmptyCVC();
        creditPage.inputData(cardInfo);
        creditPage.checkingEmptyField();
    }

    //С двумя цифрами в поле CVC/CVV
    @Test
    @DisplayName("Card with 2 numbers in CVC")
    void shouldFailValidationCardWith2NumbersInCVC() {
        var creditPage = page.creditButtonClick();
        var cardInfo = DataHelper.getCardInfoWithCVC2Numbers();
        creditPage.inputData(cardInfo);
        creditPage.checkingWrongFormat();
    }

    //С буквами в поле CVC/CVV
    @Test
    @DisplayName("Card with special characters in CVC")
    void shouldFailValidationCardWithWithCVCSpecialCharacters() {
        var creditPage = page.creditButtonClick();
        var cardInfo = DataHelper.getCardInfoWithCVCSpecialCharacters();
        creditPage.inputData(cardInfo);
        creditPage.checkingWrongFormat();
    }

    //Со спецсимволами в поле CVC/CVV
    @Test
    @DisplayName("Card with letters in CVC")
    void shouldFailValidationCardWithCVCLetter() {
        var creditPage = page.creditButtonClick();
        var cardInfo = DataHelper.getCardInfoWithCVCLetter();
        creditPage.inputData(cardInfo);
        creditPage.checkingWrongFormat();
    }
}
