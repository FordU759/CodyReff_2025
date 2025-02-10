package frc.robot.autonomous.modes;

import frc.robot.autonomous.tasks.ElevatorMoveTask;
import frc.robot.subsystems.Elevator;

public class ElevatorMoveMode extends AutoModeBase {

    @Override
    public void queueTasks() {
        // Define the target elevator state
        Elevator.ElevatorState targetState = Elevator.ElevatorState.L3; // Example: Move to L3

        // Queue the ElevatorMoveTask (instead of manually starting it)
        queueTask(new ElevatorMoveTask(0, 0, targetState)); // Distance and speed are irrelevant here
    }
}
