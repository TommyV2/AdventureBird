package com.adventureBird.flappybirdgame;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Handler;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;

import java.util.Random;


public class GameView extends View {

    Handler handler;
    Runnable runnable;
    final int UPDATE_TIME = 1;
    final int floorLevel; // na oko
    final int ceilingLevel = 0; // na oko
    private int lives;

    Bitmap background;
    Bitmap topColumn, bottomColumn,
            scaledBottomColumn,
            scaledTopColumn,
            topColumnN, bottomColumnN,
            scaledBottomColumnN,
            scaledTopColumnN,
            topColumnC, bottomColumnC,
            scaledBottomColumnC,
            scaledTopColumnC,
            scaledBottomColumnF,
            scaledTopColumnF,
            scaledBottomColumnFu,
            scaledTopColumnFu,
            scaledTopColumnI,
            scaledBottomColumnI,
            croppedBottomColumn,
            backgroundC,
            backgroundN,
            backgroundF,
            backgroundFu,
            backgroundI,
            topColumnF,bottomColumnF,
            topColumnFu,bottomColumnFu,heart,
            topColumnI, bottomColumnI;

    Bitmap zero, one, two, three, four, five, six, seven, eight, nine;
    Bitmap digit;
    Display display;
    Point point;
    int dWidth, dHeight;
    Rect rect;
    Bitmap[] birds;
    View startView;
    Rect bird, top, bottom;

    int birdFrame = 0;
    double velocity, gravity,velocityLevel;
    int birdX, birdY;
    int gameMode;
    int difficulty;
    boolean gameState = false;
    int gap = 400;
    int score, scoreX, scoreY;
    int minColumn, maxColumn;
    int numberOfColumns = 4;
    int distanceBetweenColumns;
    int[] columnX = new int[numberOfColumns];
    int[] topColumnY = new int[numberOfColumns];
    int columnVelocity;
    int bonusSpeed;
    int nextColumnIndex;
    int result;
    String number;
    Context context;
    Intent intent;
    Random random;
    String birdModel;
    String mapModel;

    public GameView(Context context, int difficulty, int gameMode, String birdModel, String mapModel, int currentVolumeIndex) {
        super(context);
        this.context = context;
        intent = new Intent(context, MainActivity.class);
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                invalidate();
            }
        };
        this.gameMode=gameMode;
        this.difficulty=difficulty;
        startView = (View) findViewById(R.id.imageView);
        this.birdModel = birdModel;
        this.mapModel = mapModel;
        initializeBirdModels(birdModel);
        initializeMapModels(mapModel);

        heart = BitmapFactory.decodeResource(getResources(), R.drawable.heart);
        zero = BitmapFactory.decodeResource(getResources(), R.drawable.zero);
        one = BitmapFactory.decodeResource(getResources(), R.drawable.one);
        two = BitmapFactory.decodeResource(getResources(), R.drawable.two);
        three = BitmapFactory.decodeResource(getResources(), R.drawable.three);
        four = BitmapFactory.decodeResource(getResources(), R.drawable.four);
        five = BitmapFactory.decodeResource(getResources(), R.drawable.five);
        six = BitmapFactory.decodeResource(getResources(), R.drawable.six);
        seven = BitmapFactory.decodeResource(getResources(), R.drawable.seven);
        eight = BitmapFactory.decodeResource(getResources(), R.drawable.eight);
        nine = BitmapFactory.decodeResource(getResources(), R.drawable.nine);

        heart = Bitmap.createScaledBitmap(heart, 50, 50, false);
        zero = Bitmap.createScaledBitmap(zero, 80, 110, false);
        one = Bitmap.createScaledBitmap(one, 80, 110, false);
        two = Bitmap.createScaledBitmap(two, 80, 110, false);
        three = Bitmap.createScaledBitmap(three, 80, 110, false);
        four = Bitmap.createScaledBitmap(four, 80, 110, false);
        five = Bitmap.createScaledBitmap(five, 80, 110, false);
        six = Bitmap.createScaledBitmap(six, 80, 110, false);
        seven = Bitmap.createScaledBitmap(seven, 80, 110, false);
        eight = Bitmap.createScaledBitmap(eight, 80, 110, false);
        nine = Bitmap.createScaledBitmap(nine, 80, 110, false);

        MainActivity.birdJumpSound.setVolume((float) currentVolumeIndex*16/100,(float) currentVolumeIndex*16/100);
        MainActivity.pointSound.setVolume((float) currentVolumeIndex*16/100*1/4,(float) currentVolumeIndex*16/100*1/4);
        MainActivity.hitSound.setVolume((float) currentVolumeIndex*16/100,(float) currentVolumeIndex*16/100);
        display = ((Activity) getContext()).getWindowManager().getDefaultDisplay();
        point = new Point();
        display.getSize(point);
        dWidth = point.x;
        dHeight = point.y;
        scoreY = dHeight * 1 / 8;
        gravity = 3; //3
         columnVelocity = 6;
         lives=0;
        if(difficulty==0) {
            lives = 10;
            bonusSpeed = 0;
        }
        else if(difficulty==1) {
            lives = 5;
            bonusSpeed = 1;
        }
        else{
            lives = 1;
            bonusSpeed = 2;
        }
        columnVelocity = 6+bonusSpeed;
        score = 0;
        floorLevel = dHeight * 77 / 100;
        rect = new Rect(0, 0, dWidth, dHeight);

        scaledTopColumn = Bitmap.createScaledBitmap(topColumn
                , dHeight * 4 / 5 * 12 / 100, dHeight * 4 / 5, false);
        scaledBottomColumn = Bitmap.createScaledBitmap(bottomColumn
                , dHeight * 4 / 5 * 12 / 100, dHeight * 4 / 5, false);

        if(gameMode!=0) {
            topColumnN = BitmapFactory.decodeResource(getResources(), R.drawable.pipe_down_night);
            topColumnC=BitmapFactory.decodeResource(getResources(), R.drawable.pipe_down_glass);
            topColumnF=BitmapFactory.decodeResource(getResources(), R.drawable.pipe_down_glass);
            topColumnF = BitmapFactory.decodeResource(getResources(), R.drawable.pipe_down_forest);
            topColumnFu = BitmapFactory.decodeResource(getResources(), R.drawable.pipe_down_future);
            topColumnI = BitmapFactory.decodeResource(getResources(), R.drawable.pipe_down_inferno);

            backgroundN = BitmapFactory.decodeResource(getResources()
                    , R.drawable.background_night);
            backgroundC = BitmapFactory.decodeResource(getResources()
                    , R.drawable.background_city);
            backgroundF = BitmapFactory.decodeResource(getResources()
                    , R.drawable.background_forest);
            backgroundFu= BitmapFactory.decodeResource(getResources()
                    , R.drawable.background_future);
            backgroundI= BitmapFactory.decodeResource(getResources()
                    , R.drawable.background_inferno);
            bottomColumnN = BitmapFactory.decodeResource(getResources(), R.drawable.pipe_up_night);
            bottomColumnC = BitmapFactory.decodeResource(getResources(), R.drawable.pipe_up_glass);
            bottomColumnF = BitmapFactory.decodeResource(getResources(), R.drawable.pipe_up_forest);
            bottomColumnFu = BitmapFactory.decodeResource(getResources(), R.drawable.pipe_up_future);
            bottomColumnI = BitmapFactory.decodeResource(getResources(), R.drawable.pipe_up_inferno);

            scaledTopColumnN = Bitmap.createScaledBitmap(topColumnN
                    , dHeight * 4 / 5 * 12 / 100, dHeight * 4 / 5, false);
            scaledBottomColumnN = Bitmap.createScaledBitmap(bottomColumnN
                    , dHeight * 4 / 5 * 12 / 100, dHeight * 4 / 5, false);
            scaledTopColumnC = Bitmap.createScaledBitmap(topColumnC
                    , dHeight * 4 / 5 * 12 / 100, dHeight * 4 / 5, false);
            scaledBottomColumnC = Bitmap.createScaledBitmap(bottomColumnC
                    , dHeight * 4 / 5 * 12 / 100, dHeight * 4 / 5, false);
            scaledTopColumnF = Bitmap.createScaledBitmap(topColumnF
                    , dHeight * 4 / 5 * 12 / 100, dHeight * 4 / 5, false);
            scaledBottomColumnF = Bitmap.createScaledBitmap(bottomColumnF
                    , dHeight * 4 / 5 * 12 / 100, dHeight * 4 / 5, false);
            scaledTopColumnFu = Bitmap.createScaledBitmap(topColumnFu
                    , dHeight * 4 / 5 * 12 / 100, dHeight * 4 / 5, false);
            scaledBottomColumnFu = Bitmap.createScaledBitmap(bottomColumnFu
                    , dHeight * 4 / 5 * 12 / 100, dHeight * 4 / 5, false);
            scaledTopColumnI = Bitmap.createScaledBitmap(topColumnI
                    , dHeight * 4 / 5 * 12 / 100, dHeight * 4 / 5, false);
            scaledBottomColumnI = Bitmap.createScaledBitmap(bottomColumnI
                    , dHeight * 4 / 5 * 12 / 100, dHeight * 4 / 5, false);

        }
        birdX = dWidth / 2 - birds[0].getWidth() / 2;
        birdY = dHeight / 2 - birds[0].getHeight() / 2;
        distanceBetweenColumns = dWidth *1/2;//dWidth * 3 / 4;
        minColumn = gap / 2;
        maxColumn = floorLevel - gap / 3 - gap;
        random = new Random();
        nextColumnIndex = 0;
        for (int i = 0; i < numberOfColumns; i++) {
            columnX[i] = dWidth + i * distanceBetweenColumns;
            topColumnY[i] = minColumn + random.nextInt(maxColumn - minColumn + 1);

        }


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(gameMode!=0 && score%20 == 0 && score <120){
            switch (score) {
                case 20:
                    topColumn = topColumnN;
                    bottomColumn = bottomColumnN;
                    scaledTopColumn = scaledTopColumnN;
                    scaledBottomColumn = scaledBottomColumnN;
                    background = backgroundN;
                    columnVelocity = 8+bonusSpeed;
                    break;
                case 40:
                    topColumn = topColumnC;
                    bottomColumn = bottomColumnC;
                    scaledTopColumn = scaledTopColumnC;
                    scaledBottomColumn = scaledBottomColumnC;
                    background = backgroundC;
                    columnVelocity = 9+bonusSpeed;
                    break;
                case 60:
                    topColumn = topColumnF;
                    bottomColumn = bottomColumnF;
                    scaledTopColumn = scaledTopColumnF;
                    scaledBottomColumn = scaledBottomColumnF;
                    background = backgroundF;
                    columnVelocity = 10+bonusSpeed;
                    break;
                case 80:
                    topColumn = topColumnFu;
                    bottomColumn = bottomColumnFu;
                    scaledTopColumn = scaledTopColumnFu;
                    scaledBottomColumn = scaledBottomColumnFu;
                    background = backgroundFu;
                    columnVelocity = 11+bonusSpeed;
                    break;
                case 100:
                    topColumn = topColumnI;
                    bottomColumn = bottomColumnI;
                    scaledTopColumn = scaledTopColumnI;
                    scaledBottomColumn = scaledBottomColumnI;
                    background = backgroundI;
                    columnVelocity =12+bonusSpeed;
                    break;
            }

        }
        canvas.drawBitmap(background, null, rect, null);

        if (birdFrame == 0) { //rotowanie obrazków ptaka (animacja lotu)
            birdFrame = 1;
        } else {
            birdFrame = 0;
        }

        if (gameState == true) {
            if (birdY + velocity < floorLevel || velocity < 0) {
                velocity += gravity;
                birdY += velocity;

            } else {

                if(gameMode != 0){
                    lives--;
                    birdY-=200;
                    if(lives<=0)
                        gameOver();
                }
                else {
                    //birdY = dHeight - birds[0].getHeight() - floorLevel;
                    gameOver();
                }
            }

            if (birdX > columnX[nextColumnIndex] + scaledTopColumn.getWidth()) {
                nextColumnIndex++;
                score++;
                MainActivity.pointSound.start();

                if (nextColumnIndex > 3) {
                    nextColumnIndex = 0;
                }
            }
            if(gameMode != 2) { ////////////////////
                if (birdX + birds[0].getWidth() > columnX[nextColumnIndex] - columnVelocity) {
                    result = isCollision(nextColumnIndex);
                    if (result != 0) {
                        if (gameMode == 1) {
                            lives--;
                            if (lives <= 0)
                                gameOver();
                            if (result == 1)
                                birdY += 100;
                            else
                                birdY -= 100;
                        } else {
                            gameOver();
                        }
                    }
                }
            }

            if (birdY + velocity < ceilingLevel) {
                birdY = ceilingLevel + 5;
            }

            for (int i = 0; i < numberOfColumns; i++) {
                columnX[i] -= columnVelocity;
                if (columnX[i] < -scaledTopColumn.getWidth()) {
                    columnX[i] += numberOfColumns * distanceBetweenColumns;
                    topColumnY[i] = minColumn + random.nextInt(maxColumn - minColumn + 1);
                }

                canvas.drawBitmap(scaledTopColumn, columnX[i]
                        , topColumnY[i] - scaledTopColumn.getHeight(), null);
                croppedBottomColumn = cropColumn(topColumnY[i]);
               // croppedBottomColumn = scaledBottomColumn;
                canvas.drawBitmap(croppedBottomColumn, columnX[i]
                        , topColumnY[i] + gap, null);
            }

            drawScore(canvas, score);
            if(gameMode==1)
                drawLives(canvas, lives);

        }

        canvas.drawBitmap(birds[birdFrame], birdX, birdY, null);
        handler.postDelayed(runnable, UPDATE_TIME);


    }

    public Bitmap cropColumn(int Y){
        return Bitmap.createBitmap(scaledBottomColumn, 0, 0, scaledTopColumn.getWidth()
                , floorLevel - (Y + gap));
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int action = event.getAction();
        if (action == MotionEvent.ACTION_DOWN) {
            velocity = -30;  //-28/-30/-33
            gameState = true;
        }
        MainActivity.birdJumpSound.start();

        return true;
    }

    public void gameOver() {
        MainActivity.hitSound.start();
        gameState = false;
        MainActivity.mydata.insertData(score);
        ((Activity) context).startActivity(intent);
    }


    public int isCollision(int nextColumnIndex) {

        bird = new Rect(birdX + 20, birdY + 20
                , birdX + birds[0].getWidth() - 20
                , birdY + birds[0].getHeight() - 20);
        top = new Rect(columnX[nextColumnIndex],
                topColumnY[nextColumnIndex] - scaledTopColumn.getHeight()
                , columnX[nextColumnIndex] + scaledTopColumn.getWidth()
                , topColumnY[nextColumnIndex]);
        bottom = new Rect(columnX[nextColumnIndex],
                topColumnY[nextColumnIndex] + gap
                , columnX[nextColumnIndex] + scaledTopColumn.getWidth()
                , topColumnY[nextColumnIndex] + gap + scaledTopColumn.getHeight());

        if(bird.intersect(top))
            return 1;
        else if (bird.intersect(bottom))
            return -1;
        else
            return 0;

    }

    public void initializeBirdModels(String birdModel){
        birds = new Bitmap[2];
        switch (birdModel){
            case "bird":
                birds[0] = BitmapFactory.decodeResource(getResources(), R.drawable.bird);
                birds[1] = BitmapFactory.decodeResource(getResources(), R.drawable.bird2);
                birds[0] = Bitmap.createScaledBitmap(birds[0]
                        , 138, 96, false);
                birds[1] = Bitmap.createScaledBitmap(birds[1]
                        , 138, 96, false);
                break;
            case "pigeon":
                birds[0] = BitmapFactory.decodeResource(getResources(), R.drawable.pigeon);
                birds[1] = BitmapFactory.decodeResource(getResources(), R.drawable.pigeon2);
                birds[0] = Bitmap.createScaledBitmap(birds[0]
                        , 138, 96, false);
                birds[1] = Bitmap.createScaledBitmap(birds[1]
                        , 138, 96, false);
                break;
            case "valentine":
                birds[0] = BitmapFactory.decodeResource(getResources(), R.drawable.valentine);
                birds[1] = BitmapFactory.decodeResource(getResources(), R.drawable.valentine2);
                birds[0] = Bitmap.createScaledBitmap(birds[0]
                        , 138, 96, false);
                birds[1] = Bitmap.createScaledBitmap(birds[1]
                        , 138, 96, false);
                break;
        }

    }

    public void initializeMapModels(String mapModel){
        switch (mapModel){
            case "classic":
                background = BitmapFactory.decodeResource(getResources()
                        , R.drawable.background_classic);
                topColumn = BitmapFactory.decodeResource(getResources(), R.drawable.pipe_down);
                bottomColumn = BitmapFactory.decodeResource(getResources(), R.drawable.pipe_up);
                break;
            case "night":
                background = BitmapFactory.decodeResource(getResources()
                        , R.drawable.background_night);
                topColumn = BitmapFactory.decodeResource(getResources(), R.drawable.pipe_down_night);
                bottomColumn = BitmapFactory.decodeResource(getResources(), R.drawable.pipe_up_night);
                break;
            case "city":
                background = BitmapFactory.decodeResource(getResources()
                        , R.drawable.background_city);
                topColumn = BitmapFactory.decodeResource(getResources(), R.drawable.pipe_down_glass);
                bottomColumn = BitmapFactory.decodeResource(getResources(), R.drawable.pipe_up_glass);
                break;
            case "forest":
                background = BitmapFactory.decodeResource(getResources()
                        , R.drawable.background_forest);
                topColumn = BitmapFactory.decodeResource(getResources(), R.drawable.pipe_down_forest);
                bottomColumn = BitmapFactory.decodeResource(getResources(), R.drawable.pipe_up_forest);
                break;
            case "future":
                background = BitmapFactory.decodeResource(getResources()
                        , R.drawable.background_future);
                topColumn = BitmapFactory.decodeResource(getResources(), R.drawable.pipe_down_future);
                bottomColumn = BitmapFactory.decodeResource(getResources(), R.drawable.pipe_up_future);
                break;
            case "inferno":
                background = BitmapFactory.decodeResource(getResources()
                        , R.drawable.background_inferno);
                topColumn = BitmapFactory.decodeResource(getResources(), R.drawable.pipe_down_inferno);
                bottomColumn = BitmapFactory.decodeResource(getResources(), R.drawable.pipe_up_inferno);
                break;
        }

    }

    public void drawLives(Canvas canvas, int lives){
        for(int i=0; i<lives;i++){
            canvas.drawBitmap(heart, (int)(dWidth*0.02+5+ i * heart.getWidth()), (int)(dHeight*0.05), null);
        }
    }

    public void drawScore(Canvas canvas, int score) {
        digit = zero;
        number = Integer.toString(score);
        int numberOfDigits = number.length();
        scoreX = dWidth / 2 - numberOfDigits * zero.getWidth() / 2;
        for (int i = 0; i < numberOfDigits; i++) {
            switch (number.charAt(i)) {
                case '0':
                    digit = zero;
                    break;
                case '1':
                    digit = one;
                    break;
                case '2':
                    digit = two;
                    break;
                case '3':
                    digit = three;
                    break;
                case '4':
                    digit = four;
                    break;
                case '5':
                    digit = five;
                    break;
                case '6':
                    digit = six;
                    break;
                case '7':
                    digit = seven;
                    break;
                case '8':
                    digit = eight;
                    break;
                case '9':
                    digit = nine;
                    break;
            }
            canvas.drawBitmap(digit, scoreX + i * zero.getWidth(), scoreY, null);
        }
    }

}
