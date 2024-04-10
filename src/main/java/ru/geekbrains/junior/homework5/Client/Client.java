package ru.geekbrains.junior.homework5.Client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private final Socket socket;
    private final String name;
    private BufferedWriter bufferedWriter;
    private BufferedReader bufferedReader;
    private boolean active;

    public Client(Socket socket, String userName){
        this.socket = socket;
        name = userName;
        try {
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }
        catch (IOException e){
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    /**
     * Отправить сообщение
     */
    public void sendMessage() {
        try {
            bufferedWriter.write(name);
            bufferedWriter.newLine();
            bufferedWriter.flush();
            Scanner scanner = new Scanner(System.in);
            while (socket.isConnected() && active){
                String message = scanner.nextLine();
                bufferedWriter.write(name + ": " + message);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        }
        catch (IOException e){
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    /**
     * Слушатель для входящих сообщений
     */
    public void listenForMessage(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                String message;
                while (socket.isConnected()){
                    try {
                        message = bufferedReader.readLine();
                        System.out.println(message);
                    }
                    catch (IOException e){
                        closeEverything(socket, bufferedReader, bufferedWriter);
                        System.out.println("Завершение работы клиента");
                        active = false;
                        break;
                    }
                }
            }
        }).start();
    }

    /**
     * Завершение работы всех потоков, закрытие клиентского сокета
     * @param socket клиентский сокет
     * @param bufferedReader буфер для чтения данных
     * @param bufferedWriter буфер для отправки данных
     */
    private void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
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
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

}
