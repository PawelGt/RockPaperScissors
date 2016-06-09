package tests;

import app.HighScoreManager;
import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.util.Scanner;

import static org.junit.Assert.*;

/**
 * Created by Pawel on 2016-06-09.
 */
public class HighScoreManagerTest {
    private String path = "src\\tests\\";
    private String fileName = "SaveData";
    @Test
    public void newHighScoreTest() {
        HighScoreManager highScoreManager = new HighScoreManager(path);

        String expectedUserName = "User";
        int expectedHighScore = 10;

        highScoreManager.setNewHighScore("User", 10);

        try {
            Scanner sc = new Scanner(new File(path + "\\" + fileName));
            String line = sc.nextLine();
            String userName = line.split(" ")[0];
            int highScore = Integer.parseInt(line.split(" ")[1]);

            Assert.assertEquals(expectedUserName, userName);
            Assert.assertEquals(expectedHighScore, highScore);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getHighScoreTest() {
        File f = new File(path + "\\" + fileName);
        FileWriter output = null;

        String expectedUserName = "User1";
        int expectedHighScore = 11;

        try {
            output = new FileWriter(f);
            BufferedWriter writer = new BufferedWriter(output);
            writer.write(expectedUserName + " " + expectedHighScore);
            writer.close();

            HighScoreManager highScoreManager = new HighScoreManager(path);

            Assert.assertEquals(expectedUserName, highScoreManager.getHighScoreName());
            Assert.assertEquals(expectedHighScore, highScoreManager.getHighScore());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}