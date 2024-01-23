package ru.netology.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class DashboardPage {

    private final SelenideElement header = $(byText("Путешествие дня"));
    private final SelenideElement paymentButton = $(byText("Купить"));
    private final SelenideElement creditButton = $(byText("Купить в кредит"));
    private final SelenideElement formPay = $("form");
    private final SelenideElement successNotification = $(".notification_status_ok");
    private final SelenideElement errorNotification = $(".notification_status_error");


    public DashboardPage() {
        header.shouldBe(visible);
        paymentButton.shouldBe(visible);
        creditButton.shouldBe(visible);
        formPay.shouldBe(hidden);
        successNotification.shouldBe(hidden);
        errorNotification.shouldBe(hidden);
    }

    public PaymentPage paymentButtonClick() {
        paymentButton.click();
        formPay.shouldBe(visible);
        return new PaymentPage();
    }

    public CreditPage creditButtonClick() {
        creditButton.click();
        formPay.shouldBe(visible);
        return new CreditPage();
    }
}
