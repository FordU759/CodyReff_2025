package frc.robot.subsystems;

import com.revrobotics.spark.SparkBase;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.wpilibj.Timer;
import frc.robot.Constants;
import frc.robot.simulation.SimulatableCANSparkMax;

public class Elevator extends Subsystem {

    /*-------------------------------- Private instance variables ---------------------------------*/
    private static Elevator mInstance;
    private PeriodicIO mPeriodicIO;

    public static Elevator getInstance() {
        if (mInstance == null) {
            mInstance = new Elevator();
        }
        return mInstance;
    }

    private SimulatableCANSparkMax mLeftMotor;
    private SimulatableCANSparkMax mRightMotor;

    private double currentHeight = 0; // Track the assumed height; this will be inaccurate

    private Elevator() {
        super("Elevator");

        mPeriodicIO = new PeriodicIO();

        SparkMaxConfig elevatorConfig = new SparkMaxConfig();

        elevatorConfig.closedLoop
                .pid(Constants.Elevator.kP, Constants.Elevator.kI, Constants.Elevator.kD)
                .iZone(Constants.Elevator.kIZone)
                .minOutput(Constants.Elevator.kMaxPowerDown)
                .maxOutput(Constants.Elevator.kMaxPowerUp);

        elevatorConfig.smartCurrentLimit(Constants.Elevator.kMaxCurrent);

        elevatorConfig.idleMode(IdleMode.kBrake);

        // LEFT ELEVATOR MOTOR
        mLeftMotor = new SimulatableCANSparkMax(Constants.Elevator.kElevatorLeftMotorId, MotorType.kBrushless);
        mLeftMotor.configure(
                elevatorConfig,
                ResetMode.kResetSafeParameters,
                PersistMode.kPersistParameters);

        // RIGHT ELEVATOR MOTOR
        mRightMotor = new SimulatableCANSparkMax(Constants.Elevator.kElevatorRightMotorId, MotorType.kBrushless);
        mRightMotor.configure(
                elevatorConfig.follow(mLeftMotor),
                ResetMode.kResetSafeParameters,
                PersistMode.kPersistParameters);
    }

    public static enum ElevatorState { // Made public and static
        NONE,
        STOW,
        L2,
        L3,
        L4,
        A1,
        A2
    }

    private static class PeriodicIO {
        double elevator_power = 0.0;
        ElevatorState state = ElevatorState.STOW;
    }

    /*-------------------------------- Generic Subsystem Functions --------------------------------*/

    @Override
    public void periodic() {
        // No periodic actions needed for this open-loop control
    }

    @Override
    public void writePeriodicOutputs() {
        mLeftMotor.set(mPeriodicIO.elevator_power); // Direct power control
    }

    @Override
    public void stop() {
        mPeriodicIO.elevator_power = 0.0;
        mLeftMotor.set(0.0);
    }

    @Override
    public void outputTelemetry() {
        putNumber("Height/Current (Estimated)", currentHeight); // Indicate estimated height
        putNumber("Power", mPeriodicIO.elevator_power);
        putNumber("Current/Left", mLeftMotor.getOutputCurrent());
        putNumber("Current/Right", mRightMotor.getOutputCurrent());
        putNumber("State", mPeriodicIO.state); // Accessing ElevatorState directly
    }

    @Override
    public void reset() {
        currentHeight = 0.0; // Reset the estimated height
    }

    /*---------------------------------- Custom Public Functions ----------------------------------*/

    public ElevatorState getState() {
        return mPeriodicIO.state;
    }

    public void setElevatorPower(double power) {
        mPeriodicIO.elevator_power = power;
    }

    // Manual height control functions - these are estimations only!  Requires careful calibration!
    public void goToElevatorStow() {
        setElevatorPower(0.5); // Example power for going up.  Adjust as needed!
        Timer.delay(1); // Example time to reach the stow position.  Adjust as needed!
        setElevatorPower(0);
        currentHeight = Constants.Elevator.kStowHeight;
        mPeriodicIO.state = ElevatorState.STOW;
    }

    public void goToElevatorL2() {
        setElevatorPower(0.5); // Example power. Adjust as needed!
        Timer.delay(2); // Example time. Adjust as needed!
        setElevatorPower(0);
        currentHeight = Constants.Elevator.kL2Height;
        mPeriodicIO.state = ElevatorState.L2;
    }

    public void goToElevatorL3() {
        setElevatorPower(0.5); // Example power. Adjust as needed!
        Timer.delay(3); // Example time. Adjust as needed!
        setElevatorPower(0);
        currentHeight = Constants.Elevator.kL3Height;
        mPeriodicIO.state = ElevatorState.L3;
    }

    public void goToElevatorL4() {
        setElevatorPower(0.5); // Example power. Adjust as needed!
        Timer.delay(4); // Example time. Adjust as needed!
        setElevatorPower(0);
        currentHeight = Constants.Elevator.kL4Height;
        mPeriodicIO.state = ElevatorState.L4;
    }

    public void goToAlgaeLow() {
        setElevatorPower(0.5); // Example power. Adjust as needed!
        Timer.delay(2.5); // Example time. Adjust as needed!
        setElevatorPower(0);
        currentHeight = Constants.Elevator.kLowAlgaeHeight;
        mPeriodicIO.state = ElevatorState.A1;
    }

    public void goToAlgaeHigh() {
        setElevatorPower(0.5); // Example power. Adjust as needed!
        Timer.delay(3.5); // Example time. Adjust as needed!
        setElevatorPower(0);
        currentHeight = Constants.Elevator.kHighAlgaeHeight;
        mPeriodicIO.state = ElevatorState.A2;
    }
}