package nl.hu.bep.tom.helloku;

import java.util.TimerTask;

public class ClearTask extends TimerTask {

    private SynchronizedString string;

    public ClearTask(SynchronizedString string){
        this.string = string;
    }

    @Override
    public void run() {
        this.string.clear();
    }
}
