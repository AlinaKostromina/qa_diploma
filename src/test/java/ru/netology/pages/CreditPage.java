package ru.netology.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;
import java.time.Duration;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CreditPage {

    private final SelenideElement header = $(byText("Кредит по данным карты"));
    private final SelenideElement number = $("[placeholder='0000 0000 0000 0000']");
    private final SelenideElement month = $("[placeholder='08']");
    private final SelenideElement year = $("[placeholder='22']");
    private final SelenideElement holder = $(byText("Владелец")).parent().$(".input__control");
    private final SelenideElement cvc = $("[placeholder='999']");
    private final SelenideElement successNotification = $(".notification_status_ok");
    private final SelenideElement errorNotification = $(".notification_status_error");
    private final SelenideElement continueButton = $$("button").find(exactText("Продолжить"));
    private final SelenideElement invalidValueFormat = $(byText("Неверный формат"));
    private final SelenideElement invalidOwner = $(byText("Поле обязательно для заполнения"));
    private final SelenideElement invalidDateFormat = $(byText("Неверно указан срок действия карты"));
    private final SelenideElement cardEndedError = $(byText("Истёк срок действия карты"));


    public CreditPage() {
        header.shouldBe(visible);
    }

    public void getSuccessNotification() {
        successNotification.shouldBe(visible, Duration.ofSeconds(15));
        successNotification.$("[class=notification__title]").should(text("Успешно"));
        successNotification.$("[class=notification__content]").should(text("Операция одобрена Банком."));
    }

    public void getErrorNotification() {
        errorNotification.shouldBe(visible, Duration.ofSeconds(15));
        errorNotification.$("[class=notification__title]").should(text("Ошибка"));
        errorNotification.$("[class=notification__content]").should(text("Ошибка! Банк отказал в проведении операции."));
    }

    public void inputData(DataHelper.CardInfo card) {
        number.setValue(card.getNumber());
        month.setValue(card.getMonth());
        year.setValue(card.getYear());
        holder.setValue(card.getHolder());
        cvc.setValue(card.getCvc());
        continueButton.click();
    }

    public void checkingEmptyField() {
        invalidOwner.shouldBe(Condition.visible);
    }

    public void checkingWrongFormat() {
        invalidValueFormat.shouldBe(Condition.visible);
    }

    public void checkingWrongDateFormat() {
        invalidDateFormat.shouldBe(Condition.visible);
    }

    public void checkingCardEnded() {
        cardEndedError.shouldBe(Condition.visible);
    }
}


