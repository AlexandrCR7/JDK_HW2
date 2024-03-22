package Messenger.server;

public interface Repository {
    void saveText(String text);
    String readText();

}
