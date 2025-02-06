package frc.robot.autonomous.modes;

import frc.robot.autonomous.tasks.ElevatorMoveTask;
import frc.robot.Constants;
import frc.robot.subsystems.Elevator;


public class ElevatorMoveMode {

    public void run() {
        // Choose the target elevator state
        Elevator.ElevatorState targetState = Elevator.ElevatorState.L3; // Example: Go to L3

        // Create and run the ElevatorMoveTask
        ElevatorMoveTask elevatorTask = new ElevatorMoveTask(0, 0, targetState); //Distance and speed are irrelevant here.
        elevatorTask.start();

        while (!elevatorTask.isFinished()) {
            elevatorTask.update();
        }
        //The done() method is called automatically in the ElevatorMoveTask when finished.
        //elevatorTask.done(); //This is not needed as it's handled in the task itself.
    }
}
