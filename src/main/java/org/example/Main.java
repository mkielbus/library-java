package org.example;

public class Main {
    public static void main(String[] args) {
        LoginPage loginpage = new LoginPage(600, 500);
        while (!loginpage.isLoggedIn()){
            loginpage.setVisible(true);
        }
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            //tu powinien być log jakiś ale idk jak to zrobić lol
        }
        loginpage.setVisible(false);
        WelcomePage welcomepage = new WelcomePage(600, 500);
        welcomepage.setVisible(true);
    }
}