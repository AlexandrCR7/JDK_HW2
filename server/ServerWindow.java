package Messenger.server;


import Messenger.client.ClientGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

//класс требуется разделить на GUI, controller и repository (смотри схему проекта)
public class ServerWindow extends JFrame  implements ServerView{
    public static final int WIDTH = 400;
    public static final int HEIGHT = 300;
    public static final String LOG_PATH = "C:\\'Коммерческий Директор это я'\\'Рабочая тетрадь'\\" +
            "java\\JDK_Sem2_HW2_V1\\src\\main\\java\\Log.txt";

    List<ClientGUI> clientGUIList;

    JButton btnStart, btnStop;
    JTextArea log;
    boolean work;
    ServerController serverController;
    FileStorage fileStorage;



    public ServerWindow(){

        clientGUIList = new ArrayList<>();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setTitle("Chat server");
        setLocationRelativeTo(null);

        createPanel();

        setVisible(true);
    }

    public String getLog() {
        return fileStorage.readText();
    }

    private void createPanel() {
        log = new JTextArea();
        add(log);
        add(createButtons(), BorderLayout.SOUTH);
    }

    public void setServerController(ServerController serverController){
        this.serverController = serverController;
    }

    private Component createButtons() {
        JPanel panel = new JPanel(new GridLayout(1, 2));
        btnStart = new JButton("Start");
        btnStop = new JButton("Stop");

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (work){
                    serverController.appendLog("Сервер уже был запущен");
                } else {
                    work = true;
                    serverController.appendLog("Сервер запущен!");
                }
            }
        });

        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!work){
                    serverController.appendLog("Сервер уже был остановлен");
                } else {
                    work = false;
                    while (!clientGUIList.isEmpty()){
                        serverController.disconnectUser(clientGUIList.get(clientGUIList.size()-1));
                    }
                    serverController.appendLog("Сервер остановлен!");
                }
            }
        });

        panel.add(btnStart);
        panel.add(btnStop);
        return panel;
    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void connect() {

    }

    @Override
    public void disconnect() {

    }
}

//    public boolean connectUser(ClientGUI clientGUI){
//        if (!work){
//            return false;
//        }
//        clientGUIList.add(clientGUI);
//        return true;
//    }



//    public void disconnectUser(ClientGUI clientGUI){
//        clientGUIList.remove(clientGUI);
//        if (clientGUI != null){
//            clientGUI.disconnectedFromServer();
//        }
//    }

//    public void message(String text){
//        if (!work){
//            return;
//        }
//        appendLog(text);
//        answerAll(text);
//        saveInLog(text);
//    }

//    private void answerAll(String text){
//        for (ClientGUI clientGUI: clientGUIList){
//            clientGUI.showMessage(text);
//        }
//    }
//
//    private void saveInLog(String text){
//        try (FileWriter writer = new FileWriter(LOG_PATH, true)){
//            writer.write(text);
//            writer.write("\n");
//        } catch (Exception e){
//            e.printStackTrace();
//        }
//    }


//    String readLog(){
//        StringBuilder stringBuilder = new StringBuilder();
//        try (FileReader reader = new FileReader(LOG_PATH)){
//            int c;
//            while ((c = reader.read()) != -1){
//                stringBuilder.append((char) c);
//            }
//            stringBuilder.delete(stringBuilder.length()-1, stringBuilder.length());
//            return stringBuilder.toString();
//        } catch (Exception e){
//            e.printStackTrace();
//            return null;
//        }
//    }

//    private void appendLog(String text){
//        log.append(text + "\n");
//    }
