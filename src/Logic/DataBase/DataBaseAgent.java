package Logic.DataBase;

import Logic.Game.Ball.Ball;
import Logic.Game.Bricks.Blinking;
import Logic.Game.Bricks.Brick;
import Logic.Game.Bricks.Invisible;
import Logic.Game.Bricks.Wooden;
import Logic.Game.GameState;
import Logic.Game.Prizes.Prize;
import Logic.Game.Vector.Vector;
import org.jetbrains.annotations.Nullable;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class DataBaseAgent {

    private String username;
    private final File users;
    private final File top20File;
    private File userDirectory;
    private File gamesDirectory;
    private int highestScore;
    private final TreeMap<String, Integer> top20;

    public DataBaseAgent(){
        users = new File("DataBase\\Users");
        if (!users.exists()) {
            users.mkdirs();
        }
        top20File = new File("DataBase\\top20");
        if(!top20File.exists()){
            try {
                top20File.createNewFile();
            }
            catch (IOException e){
                System.out.println("Failed To Build Top20 File.");
            }
        }
        top20 = new TreeMap<>();
        loadTop20();
    }

    public void setUsername(String username) {
        this.username = username;
        loadUserInfo();
    }

    private void loadUserInfo() {
        userDirectory = new File(users.getPath()+"\\"+username);
        if(userDirectory.exists()){
            try {
                File highestScoreFile = new File(userDirectory.getPath()+"\\HighestScore");
                Scanner scanner = new Scanner(highestScoreFile);
                highestScore = scanner.nextInt();
                scanner.close();
            } catch (FileNotFoundException e) {
                System.out.println("Can Not Find User Highest Score File");
            }
        } else {
            try {
                userDirectory.mkdir();

                File highestScoreFile = new File(userDirectory.getPath()+"\\HighestScore");
                highestScoreFile.createNewFile();
                FileOutputStream fileOutputStream = new FileOutputStream(highestScoreFile);
                PrintStream printStream = new PrintStream(fileOutputStream);
                printStream.println(0);
                printStream.flush();
                printStream.close();

                gamesDirectory = new File(userDirectory.getPath() + "\\Games");
                gamesDirectory.mkdir();
            } catch (IOException e) {
                System.out.println("Can Not Build Highest Score File");
            }
        }
    }

    public  String getUsername() {
        return username;
    }

    public  int getHighestScore() {
        return highestScore;
    }

    public  void saveScore(int score) {
        if(score > highestScore){
            try {
                highestScore = score;
                File highestScoreFile = new File(userDirectory.getPath()+"\\HighestScore");
                highestScoreFile.createNewFile();
                FileOutputStream fileOutputStream = new FileOutputStream(highestScoreFile, false);
                PrintStream printStream = new PrintStream(fileOutputStream);
                printStream.println(score);
                printStream.flush();
                printStream.close();
            } catch (IOException e) {
                System.out.println("Unable To Save Score");
            }
            updateTop20();
        }
    }

    private void updateTop20() {
        if (top20.containsKey(username)) {
            top20.put(username, highestScore);
        } else {
            if(top20.size() < 20){
                top20.put(username, highestScore);
            } else {
                int min = (int) top20.values().toArray()[0];
                String minOwner = null;
                for (String key :
                        top20.keySet()) {
                    if(top20.get(key) <= min){
                        min = top20.get(key);
                        minOwner = key;
                    }
                }
                if(min < highestScore) {
                    assert minOwner != null;
                    top20.remove(minOwner);
                    top20.put(username, highestScore);
                }
            }
        }
        rewriteTop20();
    }

    private void loadTop20() {
        try {
            Scanner scanner = new Scanner(top20File);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                top20.put(line.split(" ")[0], Integer.parseInt(line.split(" ")[1]));
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Top20File Not Found.");
        }
    }

    private void rewriteTop20() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(top20File, false);
            PrintStream printStream = new PrintStream(fileOutputStream);
            for (String name :
                    top20.keySet()) {
                printStream.println(name + " " + top20.get(name));
            }
            printStream.flush();
            printStream.close();
        } catch (FileNotFoundException e) {
            System.out.println("Could Not Rewrite Top20 File.");
        }
    }

    public String getTop20AsText() {
        StringBuilder stringBuilder = new StringBuilder();
        String[] names = new String[top20.size()];
        int[] scores = new int[top20.size()];
        int i = 0;
        for (String key :
                top20.keySet()) {
            names[i] = key;
            scores[i] = top20.get(key);
            i++;
        }
        for (int j = 0; j < top20.size() - 1; j++) {
            for (int k = 0; k < top20.size() - 1; k++) {
                if(scores[k] < scores[k+1]){
                    int tempInt;
                    tempInt = scores[k+1];
                    scores[k+1] = scores[k];
                    scores[k] = tempInt;
                    String tempString;
                    tempString = names[k+1];
                    names[k+1] = names[k];
                    names[k] = tempString;
                }
            }
        }
        stringBuilder.append("<html>");
        for (int j = 0; j < top20.size(); j++) {
            stringBuilder.append(names[j]).append(" ").append(scores[j]).append("<br/>");
        }
        stringBuilder.append("<html>");
        return stringBuilder.toString();
    }

    public boolean gameExists(String name) {
        File gameFile = new File(userDirectory + "\\" + "Games\\" + name);
        return gameFile.exists();
    }

    public  void saveGame(GameState gameState, String name) {
        File gameFile = new File(userDirectory + "\\" + "Games\\" + name);
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(gameFile);
        } catch (FileNotFoundException e) {
            System.out.println("Unable To Build Game FileOutputStream");
            return;
        }
        PrintStream printStream = new PrintStream(fileOutputStream);
        printStream.println(gameState.getTimerCounter());
        printStream.println(gameState.getScore());
        printStream.println(gameState.getLife());
        HashSet<Ball> activeBalls = new HashSet<>();
        for (Ball ball :
                gameState.getBalls()) {
            if (ball.isActive()) {
                activeBalls.add(ball);
            }
        }
        printStream.println(activeBalls.size());
        for (Ball ball :
                activeBalls) {
            printStream.println(ball.getCenter().getX());
            printStream.println(ball.getCenter().getY());
            printStream.println(ball.getSpeed().getX());
            printStream.println(ball.getSpeed().getY());
            printStream.println(ball.isOnFire());
            printStream.println(ball.isSpeedUp());
            printStream.println(ball.isSpeedDown());
        }
        HashSet<Brick> activeBricks = new HashSet<>();
        for (Brick brick :
                gameState.getBricks()) {
            if (brick.isActive()) {
                activeBricks.add(brick);
            }
        }
        printStream.println(activeBricks.size());
        for (Brick brick :
                activeBricks) {
            printStream.println(brick.getClass().toString().split(" ")[1]);
            printStream.println(brick.getX());
            printStream.println(brick.getY());
            printStream.println(brick.getLife());
            if (!Objects.isNull(brick.getPrize())) {
                printStream.println(brick.getPrize().getClass().toString().split(" ")[1]);
            } else {
                printStream.println("null");
            }
            if (brick instanceof Blinking) {
                printStream.println(((Blinking) brick).isVisible());
            } else {
                printStream.println(false);
            }
        }
        printStream.println(gameState.getPrizes().size());
        for (Prize prize :
                gameState.getPrizes()) {
            printStream.println(prize.getClass().toString().split(" ")[1]);
            printStream.println(prize.getX());
            printStream.println(prize.getY());
        }
        printStream.println(gameState.getOnGoingPrize().size());
        for (Prize prize :
                gameState.getOnGoingPrize()) {
            printStream.println(prize.getClass().toString().split(" ")[1]);
            printStream.println(prize.getTimerPassedSinceActivated());
        }
        printStream.flush();
        printStream.close();
    }

    interface inter {
        int nextInt();
        boolean nextBoolean();
        String nextString();
    }

    public GameState loadGame(String name) {
        File gameFile = new File(userDirectory + "\\" + "Games\\" + name);
        Scanner scanner = null;
        try {
            scanner = new Scanner(gameFile);
        } catch (FileNotFoundException e) {
            System.out.println("Unable To Scan GameFile");
        }
        final Scanner finalScanner = scanner;
        inter get = new inter() {
            @Override
            public int nextInt() {
                return Integer.parseInt(finalScanner.nextLine());
            }

            @Override
            public boolean nextBoolean() {
                return Boolean.parseBoolean(finalScanner.nextLine());
            }

            @Override
            public String nextString() {
                return finalScanner.nextLine();
            }
        };
        int timerCounter = get.nextInt();
        int score = get.nextInt();
        int life = get.nextInt();
        int ballsCount = get.nextInt();
        Ball[] balls = new Ball[ballsCount];
        for (int i = 0; i < ballsCount; i++) {
            Ball ball = new Ball();
            int centerX = get.nextInt();
            int centerY = get.nextInt();
            Vector center = new Vector(centerX, centerY);
            ball.setCenter(center);
            int speedX = get.nextInt();
            int speedY = get.nextInt();
            Vector speed = new Vector(speedX, speedY);
            ball.setSpeed(speed);
            ball.setOnFire(get.nextBoolean());
            ball.setSpeedUp(get.nextBoolean());
            ball.setSpeedDown(get.nextBoolean());
            balls[i] = ball;
        }
        int bricksCount = get.nextInt();
        Brick[] bricks = new Brick[bricksCount];
        for (int i = 0; i < bricksCount; i++) {
            try {
                Brick brick = buildBrick(get.nextString());
                brick.setX(get.nextInt());
                brick.setY(get.nextInt());
                brick.setLife(get.nextInt());
                brick.setPrize(buildPrize(get.nextString(), brick));
                boolean b = get.nextBoolean();
                if(brick instanceof Blinking) ((Blinking) brick).setVisible(b);
                bricks[i] = brick;
            } catch (Exception e) {
                System.out.println("Loading Brick Failed.");
                e.printStackTrace();
            }
        }
        int prizesCount = get.nextInt();
        Prize[] prizes =  new Prize[prizesCount];
        for (int i = 0; i < prizesCount; i++) {
            try {
                Prize prize = buildPrize(get.nextString(), null);
                assert prize != null;
                prize.setX(get.nextInt());
                prize.setY(get.nextInt());
                prizes[i] = prize;
            } catch (Exception e) {
                System.out.println("Loading Prize Failed.");
                e.printStackTrace();
            }
        }
        int onGoingPrizesCount = get.nextInt();
        Prize[] onGoingPrizes = new Prize[onGoingPrizesCount];
        for (int i = 0; i < onGoingPrizesCount; i++) {
            try {
                Prize prize = buildPrize(get.nextString(), null);
                assert prize != null;
                prize.setTimerPassedSinceActivated(get.nextInt());
                onGoingPrizes[i] = prize;
            } catch (Exception e) {
                System.out.println("Loading Prize Failed.");
                e.printStackTrace();
            }
        }
        scanner.close();
        finalScanner.close();
        return new GameState(timerCounter, score, life, balls, bricks, prizes, onGoingPrizes);
    }

    private Prize buildPrize(String nextString, Brick brick) throws Exception {
        if(nextString.equals("null")) return null;
        Class<?> clazz = Class.forName(nextString);
        Constructor<?> constructor = clazz.getConstructor(Brick.class);
        return (Prize) constructor.newInstance(brick);
    }

    private Brick buildBrick(String nextString) throws Exception {
        Class<?> clazz = Class.forName(nextString);
        Constructor<?> constructor = clazz.getConstructor(int.class, int.class);
        return (Brick) constructor.newInstance(0,0);
    }

    public File getTop20File() {
        return top20File;
    }
}
