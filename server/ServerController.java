package Messenger.server;

import Messenger.client.ClientController;
import Messenger.client.ClientGUI;

import javax.swing.*;
import java.io.FileWriter;
import java.util.List;

public class ServerController {
    ServerWindow serverWindow;
    ClientController clientController;
    FileStorage fileStorage;
    private boolean connectUser;
    private boolean work;
    List<ClientGUI> clientGUIList;
    ClientGUI clientGUI;
    JTextArea log = new JTextArea();
    public static final String LOG_PATH = "C:\\'Коммерческий Директор это я'\\'Рабочая тетрадь'\\" +
            "java\\JDK_Sem2_HW2_V1\\src\\main\\java\\Log.txt";
    private ServerView serverView;

    public void setServerView(ServerView serverView){
        this.serverView = serverView;
    }

    public boolean connectUser(){
        if (!work){
            return false;
        }
        clientGUIList.add(clientGUI);
        return true;
    }

    public String getHistory(){
        return fileStorage.readText();
    }

    public void disconnectUser(ClientGUI clientGUI){
        clientGUIList.remove(clientGUI);
        if (clientGUI != null){
            clientGUI.disconnectedFromServer();
        }
    }
    private void answerAll(String text){
        for (ClientGUI clientGUI: clientGUIList){
            clientGUI.showMessage(text);
        }
    }

    void appendLog(String text){
        log.append(text + "\n");
    }

    private void saveInLog(String text){
        try (FileWriter writer = new FileWriter(LOG_PATH, true)){
            writer.write(text);
            writer.write("\n");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void message(String text){
        if (!work){
            return;
        }
        appendLog(text);
        answerAll(text);
        saveInLog(text);
    }

}
