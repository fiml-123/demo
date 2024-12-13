package com.devops.demo.util;

import com.devops.demo.model.Player;

import java.io.*;

public class SerializationUtil {

    public static void savePlayer(Player player, String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(player);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Player loadPlayer(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (Player) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
