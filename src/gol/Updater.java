package gol;

import gol.gui.Case;
import gol.gui.Field;
import javafx.application.Platform;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class Updater extends Service<Void> {
    private Field field;
    private boolean[][] cache;
    private LongProperty iterations;

    private boolean running;

    public Updater(Field field){
        super();
        this.field = field;
        cache = new boolean[field.getCases().length][field.getCases()[0].length];

        running = false;
        iterations = new SimpleLongProperty(0);
    }

    public int getSurroundAlive(int x, int y){
        int count = 0;
        for (int ix = x - 1; ix <= x + 1; ix++) {
            if(ix < 0 || ix >= field.getCases().length){
                if(Main.getParam().isSideAlive()){
                    count++;
                }
                continue;
            }

            for (int iy = y - 1; iy <= y + 1; iy++) {
                if(iy < 0 || iy >= field.getCases()[x].length){
                    if(Main.getParam().isSideAlive()){
                        count++;
                    }
                    continue;
                }

                if(ix == x && iy == y){
                    continue;
                }

                if(field.getCases()[ix][iy].isAlive()){
                    count++;
                }
            }
        }

        return count;
    }

    public void setIterations(long iterations) {
        this.iterations.set(iterations);
    }
    public long getIterations() {
        return iterations.get();
    }
    public LongProperty iterationsProperty() {
        return iterations;
    }

    @Override
    protected Task<Void> createTask() {
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                while(!isCancelled()){
                    if(!running){
                        try{
                            Thread.sleep(2*Main.getParam().getSpeed());
                        }
                        catch(InterruptedException ignore){}

                        continue;
                    }

                    Platform.runLater(() -> iterations.set(iterations.get() + 1));

                    try{
                        for (int x = 0; x < field.getCases().length; x++) {
                            for (int y = 0; y < field.getCases()[x].length; y++) {
                                Case c = field.getCases()[x][y];
                                int count = getSurroundAlive(x, y);

                                if(c.isAlive()){
                                    if(count < 2){
                                        cache[x][y] = false;
                                    }
                                    else if(count > 3){
                                        cache[x][y] = false;
                                    }
                                    else {
                                        cache[x][y] = c.isAlive();
                                    }
                                }
                                else {
                                    if(count == 3){
                                        cache[x][y] = true;
                                    }
                                    else {
                                        cache[x][y] = c.isAlive();
                                    }
                                }
                            }
                        }

                        for (int x = 0; x < field.getCases().length; x++) {
                            for (int y = 0; y < field.getCases()[x].length; y++) {
                                field.getCases()[x][y].setAlive(cache[x][y]);
                            }
                        }

                        Thread.sleep(Main.getParam().getSpeed());
                    }
                    catch(InterruptedException ignore){}
                    catch(Exception exc){
                        exc.printStackTrace();
                    }

                }

                return null;
            }
        };
        return task;
    }

    @Override
    public void start() {
        running = true;
        if(!isRunning()){
            super.start();
        }
    }

    @Override
    public boolean cancel() {
        running = false;
        return running;
    }

    public boolean isStarted() {
        return running;
    }

    public void quit(){
        cancel();
        super.cancel();
    }
}
