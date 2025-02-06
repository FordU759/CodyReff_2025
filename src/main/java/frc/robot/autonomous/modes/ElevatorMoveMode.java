package frc.robot.autonomous.modes;

import frc.robot.autonomous.tasks.ElevatorMoveTask;
import frc.robot.Constants;

public class ElevatorMoveMode {

    public void run() {
        ElevatorMoveTask moveToL3 = new ElevatorMoveTask(Constants.Elevator.kL3Height, 0.5, 5.0);
        moveToL3.start();
        while (!moveToL3.isFinished()) {
            moveToL3.update();
        }

        moveToL3.stop();
    }
}
