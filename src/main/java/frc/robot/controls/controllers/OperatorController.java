package frc.robot.controls.controllers;

public class OperatorController extends FilteredController {

  private boolean manualControlMode = false; // Variable to track manual control mode

  public OperatorController(int port) {
    super(port, false, false);
  }

  public OperatorController(int port, boolean useDeadband, boolean useSquaredInput) {
    super(port, useDeadband, useSquaredInput);
  }

  // Axis
  private final double k_triggerActivationThreshold = 0.5;

  public double getElevatorAxis() {
    return -this.getFilteredAxis(1); // Joystick axis for manual control
  }

  // Manual Control Toggle Button (Button 5 is used in this example)
  public boolean wantsManualControlToggle() {
    return this.getRawButton(5); // Toggle manual control with Button 5
  }

  // Elevator Control Logic
  public boolean getWantsElevatorReset() {
    return this.getRawButton(7);
  }

  public boolean getWantsElevatorStow() {
    return this.getHatDown();
  }

  public boolean getWantsElevatorL2() {
    return this.getHatLeft();
  }

  public boolean getWantsElevatorL3() {
    return this.getHatRight();
  }

  public boolean getWantsElevatorL4() {
    return this.getHatUp();
  }

  // Check if manual control is active, and adjust the elevator if it is
  public double getElevatorManualControl() {
    if (wantsManualControlToggle()) {
      manualControlMode = !manualControlMode; // Toggle manual control on/off
    }

    if (manualControlMode) {
      return getElevatorAxis(); // Use the joystick for manual movement
    }

    // If not in manual mode, return 0 (no movement)
    return 0;
  }
}
