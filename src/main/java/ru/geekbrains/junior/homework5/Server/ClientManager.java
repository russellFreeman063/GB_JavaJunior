package ru.geekbrains.junior.homework5.Server;

import java.io.*;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ClientManager implements Runnable {

    private final Socket socket;
    private String name;
    private BufferedWriter bufferedWriter;
    private BufferedReader bufferedReader;
    public static ArrayList<ClientManager> clients = new ArrayList<>();

    public ClientManager(Socket socket) {
        this.socket = socket;
        try {
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            name = bufferedReader.readLine();
            clients.add(this);
            System.out.println(name + " подключился к чату.");
            broadcastMessage("Server: " + name + " подключился к чату.");
        } catch (IOException e) {
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    private void removeClient() {
        clients.remove(this);
        System.out.println(name + " покинул чат.");
        broadcastMessage("Server: " + name + " покинул чат.");
    }

    private void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
        removeClient();
        try {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Отправка сообщения всем слушателям
     *
     * @param message сообщение
     */
    private void broadcastMessage(String message) {
        String[] elements = message.split(" ", 3);

        // Проверка является ли сообщение личным
        if (elements[1].startsWith("@")) {
            String sendTo = elements[1].replaceFirst("@", "");
            message = elements[2];
            for (ClientManager client : clients) {
                try {
                    if (client.name.equals(sendTo) && message != null) {
                        client.bufferedWriter.write("(Private) " + getTime() + " " + name + ": " + message);
                        client.bufferedWriter.newLine();
                        client.bufferedWriter.flush();
                        return;
                    }
                } catch (IOException e) {
                    closeEverything(socket, bufferedReader, bufferedWriter);
                }
            }

            // Если клиент не найден
            for (ClientManager client : clients) {
                try {
                    if (client.name.equals(name)) {
                        client.bufferedWriter.write("(Server) " + sendTo + " не в сети");
                        client.bufferedWriter.newLine();
                        client.bufferedWriter.flush();
                        return;
                    }
                } catch (IOException e) {
                    closeEverything(socket, bufferedReader, bufferedWriter);
                }
            }
        }
        for (ClientManager client : clients) {
            try {
                if (!client.name.equals(name) && message != null) {
                    client.bufferedWriter.write( getTime() + " " + message);
                    client.bufferedWriter.newLine();
                    client.bufferedWriter.flush();
                }
            } catch (IOException e) {
                closeEverything(socket, bufferedReader, bufferedWriter);
            }
        }
    }

    /**
     * Добавление времени сообщения
     */
    private String getTime() {
        SimpleDateFormat date = new SimpleDateFormat("HH:mm");
        return date.format(new Date());
    }

    /**
    * Чтение сообщений ОТ клиента
    */
    @Override
    public void run() {
        String messageFromClient;
        while (socket.isConnected()) {
            try {
                messageFromClient = bufferedReader.readLine();
                broadcastMessage(messageFromClient);
            }
            catch (IOException e){
                closeEverything(socket, bufferedReader, bufferedWriter);
                break;
            }
        }
    }
}

