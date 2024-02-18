package org.selenide.examples;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

import com.codeborne.selenide.SelenideElement;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;



public class AppTest 
{
    @Test
    public void userCanAccessDouglasWebsite() {
        open("https://www.douglas.pl/pl");

        SelenideElement cookies = $(By.cssSelector(".uc-overlay .uc-list-button__accept-all")).shouldBe(visible);
        if (cookies.exists()){
            cookies.click();
        }
    }
    @Test
    public void mainPageElementsAreVisible() {
        open("https://www.douglas.pl/pl");

        SelenideElement cookies = $(By.cssSelector(".uc-overlay .uc-list-button__accept-all")).shouldBe(visible);
        if (cookies.exists()){
            cookies.click();
        }

        $(By.className("logo")).shouldBe(visible);
        $(By.id("typeAhead-input")).shouldBe(visible);
        $(By.cssSelector(".douglas-swiper-carousel--horizontal .swiper-arrow.swiper-button-next")).click();
        $(By.cssSelector(".douglas-swiper-carousel--horizontal .swiper-arrow.swiper-button-prev")).click();
        $(By.className("promotion-bar__paragraph-text")).shouldBe(interactable);
    }

    @Test
    public void userCanSearchSomeItemsAtDouglasWebsite() {
        open("https://www.douglas.pl/pl");

        SelenideElement cookies = $(By.cssSelector(".uc-overlay .uc-list-button__accept-all")).shouldBe(visible);
        if (cookies.exists()){
            cookies.click();
        }

        $(By.id("typeAhead-input")).setValue("MAC Lustreglass").pressEnter();
        $(By.className("product-tile__main-link")).shouldBe(visible);
        $(By.className("product-tile__main-link")).click();
        $(By.className("headline-bold")).shouldBe(visible).shouldHave(text("Lustreglass"));
    }


    @Test
    public void userCanNavigateToDifferentCategories() {
        open("https://www.douglas.pl/pl");

        SelenideElement cookies = $(By.cssSelector(".uc-overlay .uc-list-button__accept-all")).shouldBe(visible);
        if (cookies.exists()){
            cookies.click();
        }

        $(By.linkText("MARKI")).click();
        $(By.linkText("MARKI")).shouldHave(cssClass("active"));

        $(By.linkText("PERFUMY")).click();
        $(By.linkText("PERFUMY")).shouldHave(cssClass("active"));

        $(By.linkText("MAKIJAŻ")).click();
        $(By.linkText("MAKIJAŻ")).shouldHave(cssClass("active"));

        $(By.linkText("TWARZ")).click();
        $(By.linkText("TWARZ")).shouldHave(cssClass("active"));

        $(By.linkText("CIAŁO")).click();
        $(By.linkText("CIAŁO")).shouldHave(cssClass("active"));

        $(By.linkText("WŁOSY")).click();
        $(By.linkText("WŁOSY")).shouldHave(cssClass("active"));
    }

    @Test
    public void userCanAddItemsToCart() {
        open("https://www.douglas.pl/pl");

        SelenideElement cookies = $(By.cssSelector(".uc-overlay .uc-list-button__accept-all")).shouldBe(visible);
        if (cookies.exists()){
            cookies.click();
        }

        $(By.linkText("MAKIJAŻ")).click();
        $(By.className("product-tile__main-link")).shouldBe(visible).click();
        $(By.className("button__primary")).shouldBe(visible).click();
        $(By.cssSelector(".add-cart-modal__button.button")).should(appear).click();
        $(By.className("mini-cart__icon")).shouldBe(visible).click();
    }

    @Test
    public void priceOfItemInCartIsRight() {
        open("https://www.douglas.pl/pl");

        SelenideElement cookies = $(By.cssSelector(".uc-overlay .uc-list-button__accept-all")).shouldBe(visible);
        if (cookies.exists()){
            cookies.click();
        }

        $(By.linkText("MAKIJAŻ")).click();
        $(By.className("product-tile__main-link")).shouldBe(visible).click();
        String priceText = $(By.cssSelector(".product-detail__variant-row .product-price")).getText().replaceAll("[^0-9,]+", "").replaceFirst(",", ".");
        double totalPrice = Double.parseDouble(priceText);
        $(By.className("button__primary")).shouldBe(visible).click();
        $(By.className("add-cart-modal__button--secondary")).should(appear).click();

        $(By.className("mini-cart__icon")).shouldBe(visible).click();

        String totalPriceText = $(By.className("cart-calculations__item--pushed")).shouldBe(visible).getText().replaceAll("[^0-9,]+", "").replaceFirst(",", ".");
        double displayedTotalPrice = Double.parseDouble(totalPriceText);

        Assert.assertEquals(totalPrice, displayedTotalPrice, 0.01);
    }

    @Test
    public void userCanFilterPerfumesByProductCategories() {
        open("https://www.douglas.pl/pl");

        SelenideElement cookies = $(By.cssSelector(".uc-overlay .uc-list-button__accept-all")).shouldBe(visible);
        if (cookies.exists()){
            cookies.click();
        }

        $(By.className("logo")).click();
        $(By.linkText("PERFUMY")).click();
        $(By.className("facet__title")).scrollTo().shouldBe(visible).click();
        $(By.cssSelector(".facet-search .input__input")).setValue("Woda perfumowana");
        $(By.className("facet-option")).shouldBe(visible).click();

        $(By.cssSelector(".link.link--no-decoration.product-tile__main-link")).click();
        $(By.className("third-line")).shouldHave(text("Woda perfumowana"));
        back();
    }

}
