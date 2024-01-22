package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataHelper {

    private static Faker fakerEn = new Faker(new Locale("en"));
    private static Faker fakerRu = new Faker(new Locale("ru"));

    private DataHelper() {
    }

    public static String getLastYear(int minusOneYear) {
        return LocalDate.now().minusYears(minusOneYear).format(DateTimeFormatter.ofPattern("yy"));
    }

    //ДОРАБОТКА
    public static String generateHolderName() {
        return fakerEn.name().lastName() + " " + fakerEn.name().firstName();
    }

    public static String generateMonth(int month) {
        return LocalDate.now().plusMonths(month).format(DateTimeFormatter.ofPattern("MM"));
    }

    public static String generateYear(int year) {
        return LocalDate.now().plusYears(year).format(DateTimeFormatter.ofPattern("yy"));
    }


    @Value
    public static class CardInfo {
        String number;
        String month;
        String year;
        String holder;
        String cvc;

        public CardInfo(Object o, Object o1, Object o2, Object o3, Object o4) {
        }
    }

    // ДАННЫЕ ДЛЯ ПОЗИТИВНЫХ СЦЕНАРИЕВ
    public static String getApprovedCardNumber() {
        return ("4444 4444 4444 4441");
    }

    public static String getDeclinedCardNumber() {
        return ("4444 4444 4444 4442");
    }

    public static CardInfo getValidDataWithApprovedCard() {
        return new CardInfo(getApprovedCardNumber(),
                generateMonth(0),
                generateYear(0),
                generateHolderName(),
                fakerEn.numerify("###"));
    }

    public static CardInfo getValidDataWithDeclinedCard() {
        return new CardInfo(getDeclinedCardNumber(),
                generateMonth(0),
                generateYear(0),
                generateHolderName(),
                fakerEn.numerify("###"));
    }

    // ДАННЫЕ ДЛЯ НЕГАТИВНЫХ СЦЕНАРИЕВ

    //Без заполнения полей формы
    public static CardInfo getCardInfoWithEmptyFields() {
        return new CardInfo(
                null,
                null,
                null,
                null,
                null);
    }

    //С пустым полем Номер карты
    public static CardInfo getCardInfoWithEmptyNumber() {
        return new CardInfo(null,
                generateMonth(0),
                generateYear(0),
                generateHolderName(),
                fakerEn.numerify("###"));
    }

    //Картой с невалидным номером длиной 16 цифр
    public static CardInfo getCardInfoWithRandomNumber() {
        return new CardInfo(fakerEn.numerify("#### #### #### ####"),
                generateMonth(0),
                generateYear(0),
                generateHolderName(),
                fakerEn.numerify("###"));
    }

    //Картой с номером длиной 17 цифр
    public static CardInfo getCardInfoWith17() {
        return new CardInfo(fakerEn.numerify("#### #### #### #### #"),
                generateMonth(0),
                generateYear(0),
                generateHolderName(),
                fakerEn.numerify("###"));
    }

    //Картой с номером длиной 15 цифр
    public static CardInfo getCardInfoWith15() {
        return new CardInfo(fakerEn.numerify("#### #### #### ###"),
                generateMonth(0),
                generateYear(0),
                generateHolderName(),
                fakerEn.numerify("###"));
    }

    //Картой с номером, состоящим из спецсимволов
    public static CardInfo getCardInfoWithSpecialCharacters() {
        return new CardInfo(fakerEn.numerify("@#$% !&*( ^&*( )(*&"),
                generateMonth(0),
                generateYear(0),
                generateHolderName(),
                fakerEn.numerify("###"));
    }

    //C пустым полем Месяц
    public static CardInfo getCardInfoWithNullMonth() {
        return new CardInfo(getApprovedCardNumber(),
                null,
                generateYear(0),
                generateHolderName(),
                fakerEn.numerify("###"));
    }

    //Cо значением 00 в поле Месяц
    public static CardInfo getCardInfoWithMonthWithTwoZero() {
        return new CardInfo(getApprovedCardNumber(),
                LocalDate.now().format(DateTimeFormatter.ofPattern("00")),
                generateYear(0),
                generateHolderName(),
                fakerEn.numerify("###"));
    }

    //Cо значением 13 в поле Месяц
    public static CardInfo getCardInfoWith13Month() {
        return new CardInfo(getApprovedCardNumber(),
                "13",
                generateYear(0),
                generateHolderName(),
                fakerEn.numerify("###"));
    }

    //Cо значением, состоящим из одной цифры в поле Месяц
    public static CardInfo getCardInfoWithMonthWith1Number() {
        return new CardInfo(getApprovedCardNumber(),
                fakerEn.numerify("#"),
                generateYear(0),
                generateHolderName(),
                fakerEn.numerify("###"));
    }

    //Cо значением, состоящим из спецсимвола в поле Месяц
    public static CardInfo getCardInfoWithMonthWithSpecialCharacters() {
        return new CardInfo(getApprovedCardNumber(),
                fakerEn.numerify("&$"),
                generateYear(0),
                generateHolderName(),
                fakerEn.numerify("###"));
    }

    //С пустым полем Год
    public static CardInfo getCardInfoWithNullYear() {
        return new CardInfo(getDeclinedCardNumber(),
                generateMonth(0),
                null,
                generateHolderName(),
                fakerEn.numerify("###"));
    }

    //Со значением в поле Год, ранее текущего года
    public static CardInfo getCardInfoWithLastYear() {
        return new CardInfo(getApprovedCardNumber(),
                generateMonth(0),
                getLastYear(1),
                generateHolderName(),
                fakerEn.numerify("###"));
    }

    //Со спецсимволами в поле Год
    public static CardInfo getCardInfoWithSpecialCharactersYear() {
        return new CardInfo(getApprovedCardNumber(),
                generateMonth(0),
                fakerEn.numerify("&$"),
                generateHolderName(),
                fakerEn.numerify("###"));
    }

    //С одной цифрой в поле Год
    public static CardInfo getCardInfoWith1NumberYear() {
        return new CardInfo(getApprovedCardNumber(),
                generateMonth(0),
                fakerEn.numerify("#"),
                generateHolderName(),
                fakerEn.numerify("###"));
    }

    //С пустым полем Владелец
    public static CardInfo getCardInfoWithNullOwner() {
        return new CardInfo(getApprovedCardNumber(),
                generateMonth(0),
                generateYear(0),
                null,
                fakerEn.numerify("###"));
    }

    //С цифрами в поле Владелец
    public static CardInfo getCardInfoWithOwnerWithNumbers() {
        return new CardInfo(getApprovedCardNumber(),
                generateMonth(0),
                generateYear(0),
                fakerEn.numerify("####### ######"),
                fakerEn.numerify("###"));
    }

    //Со спецсимволами в поле Владелец
    public static CardInfo getCardInfoWithOwnerSpecialCharacters() {
        return new CardInfo(getApprovedCardNumber(),
                generateMonth(0),
                generateYear(0),
                ")(*&^%$#@!@",
                fakerEn.numerify("###"));
    }

    //Со значением в поле Владелец, набранным кириллицей
    public static CardInfo getCardInfoWithOwnerCyrillic() {
        return new CardInfo(getApprovedCardNumber(),
                generateMonth(0),
                generateYear(0),
                fakerRu.name().firstName() + " " + fakerRu.name().lastName(),
                fakerEn.numerify("###"));
    }

    //С пустым полем CVC/CVV
    public static CardInfo getCardInfoWithEmptyCVC() {
        return new CardInfo(getApprovedCardNumber(),
                generateMonth(0),
                generateYear(0),
                generateHolderName(),
                null);
    }

    //С двумя цифрами в поле CVC/CVV
    public static CardInfo getCardInfoWithCVC2Numbers() {
        return new CardInfo(getApprovedCardNumber(),
                generateMonth(0),
                generateYear(0),
                generateHolderName(),
                fakerEn.numerify("##"));
    }

    //С буквами в поле CVC/CVV
    public static CardInfo getCardInfoWithCVCSpecialCharacters() {
        return new CardInfo(getApprovedCardNumber(),
                generateMonth(0),
                generateYear(0),
                generateHolderName(),
                fakerEn.numerify("*&*"));
    }

    //Со спецсимволами в поле CVC/CVV
    public static CardInfo getCardInfoWithCVCLetter() {
        return new CardInfo(getApprovedCardNumber(),
                generateMonth(0),
                generateYear(0),
                generateHolderName(),
                "CVC");
    }
}
