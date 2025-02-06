package frc.robot.autonomous.tasks;

import frc.robot.subsystems.Elevator;

public class ElevatorMoveTask {
    private Elevator mElevator;
    private double targetPosition;
    private double speed;
    private double time;

    // Constructor with three arguments
    public ElevatorMoveTask(double targetPosition, double speed, double time) {
        this.mElevator = Elevator.getInstance();
        this.targetPosition = targetPosition;
        this.speed = speed;
        this.time = time;
    }

    // Start the elevator movement
    public void start() {
        mElevator.setElevatorTarget(targetPosition);
    }

    // Check if the movement is finished
    public boolean isFinished() {
        double currentPosition = mElevator.getElevatorPosition();
        return Math.abs(currentPosition - targetPosition) < 0.1; // Tolerance for completion
    }

    // Stop the elevator movement
    public void stop() {
        mElevator.setElevatorPower(0.0);
    }

    // Method to update the elevator's status periodically
    public void update() {
        if (isFinished()) {
            stop();
        }
    }
}
