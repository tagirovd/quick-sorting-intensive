package stage1.graduation.enums;

public enum MailDomains {
    GMAIL_COM("gmail.com"),
    YAHOO_COM("yahoo.com"),
    MAIL_RU("mail.ru"),
    RAMBLER_COM("rambler.com"),
    PROTON_ME("proton.me");

    private final String domain;

    MailDomains(String domain) {
        this.domain = domain;
    }

    public String getDomain() {
        return domain;
    }

    public static MailDomains getRandom() {
        MailDomains[] values = values();
        int length = values.length;
        int randomIndex = (int) (Math.random() * length);
        return values[randomIndex];
    }
}
