package ch.epfl.cs107.icmon.gamelogic.actions;

public class LogAction implements Action {

    private final String message;

    /**
     * Default Constructor for LogAction
     * @param message message that we want to Log
     */
    public LogAction(String message){
        this.message = message;
    }

    @Override
    public void perform() {
        System.out.println(message);
    }
}
