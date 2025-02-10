package frc.robot;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.util.Units;

public class Constants {
  public static class Robot {
    public static final double k_width = 26; // Inches
    public static final double k_length = 28; // Inches
  }

  public static class Elevator {
    public static final int kElevatorLeftMotorId = 9; //This is the CAN ID for the left elevator motor
    public static final int kElevatorRightMotorId = 10; //This is the CAN ID for the right elevator motor

    public static final double kP = 0.15; //This is the proportional gain, helps with error correction
    public static final double kI = 0; //This is the integral gain, helps with steady state error
    public static final double kD = 0.0; //This is the derivative gain, Damps oscillations
    public static final double kIZone = 5.0; //This is the integral zone if greater than 5 it will diable
    public static final double kG = 0.5; //This is the gravity constant

    public static final double kMaxVelocity = 65; //This is the maximum velocity of the elevator
    public static final double kMaxAcceleration = 200; //This is the maximum acceleration of the elevator

    public static final int kMaxCurrent = 40;
    public static final double kMaxPowerUp = -0.1; //This is the maximum power that the elevator can go up
    public static final double kMaxPowerDown = 0.1; //This is the maximum power that the elevator can go down

    public static final double kStowHeight = 0.0; //This is the height of the elevator when it is stowed
    public static final double kL2Height = 9.0; //This is the height of the elevator when it is at level 2
    public static final double kL3Height = 25.14; //This is the height of the elevator when it is at level 3
    public static final double kL4Height = 52.0; // This is the height of the elevator when it is at level 4
    public static final double kMaxHeight = 56.2; //This is the maximum height of the elevator
    public static final double kGroundAlgaeHeight = 0.0;
    public static final double kScoreAlgaeHeight = 0.0;
    public static final double kLowAlgaeHeight = 24.8;
    public static final double kHighAlgaeHeight = 42.5;
  }

  public static class Coral {
    public static final int kLeftMotorId = 11; //This is the CAN ID for the left coral motor
    public static final int kRightMotorId = 12; //This is the CAN ID for the right coral motor

    public static final int kLaserId = 0; //This is the DIO ID for the laser sensor, which is used to detect the coral
    public static final int kColorId = 16; //This is the I2C ID for the color sensor, which is used to detect the color of the coral

    public static final double kMaxCurrent = 20;

    public static final double kP = 0.0;
    public static final double kI = 0.0;
    public static final double kD = 0.0;
    public static final double kIZone = 0;

    public static final double kIntakeSpeed = 0.3;
    public static final double kReverseSpeed = -0.3;
    public static final double kL1Speed = 0.4;
    public static final double kL24Speed = 0.4;
    public static final double kIndexSpeed = 0.1;
    public static final double kSpeedDifference = kL1Speed * 0.5;
  }

  public static class Algae {
    // WRIST
    public static final int kWristMotorId = 13;
    public static final int kIntakeMotorId = 14;

    public static final int kWristEncoderId = 9;

    public static final int kMaxWristCurrent = 10;

    public static final double kWristP = 0.01;
    public static final double kWristI = 0.0;
    public static final double kWristD = 0.0;

    public static final double kWristKS = 0.0;
    public static final double kWristKG = 0.0;
    public static final double kWristKV = 0.100;
    public static final double kWristKA = 0.0;

    public static final double kWristOffset = 141.0;

    public static final double kWristMaxVelocity = 690.0;
    public static final double kWristMaxAcceleration = 1380.0;

    public static final double kStowAngle = 233.0;
    public static final double kDeAlgaeAngle = 215.0;
    public static final double kGroundIntakeAngle = 162.0;

    // INTAKE
    public static final int kMaxIntakeCurrent = 20;

    public static final double kIntakeSpeed = 0.6;
    public static final double kEjectSpeed = -0.3;
    public static final double kGroundIntakeSpeed = -0.3;
  }

  public static class Intake {
    // Motors
    public static final int kIntakeMotorId = 9;
    public static final int kPivotMotorId = 10;

    // DIO
    public static final int k_pivotEncoderId = 0;
    public static final int k_intakeLimitSwitchId = 2;

    // Absolute encoder offset
    public static final double k_pivotEncoderOffset = 0.166842; // Straight up, sketchy to reset to "up"

    // Pivot set point angles
    public static final double k_pivotAngleGround = 60;
    public static final double k_pivotAngleSource = 190;
    public static final double k_pivotAngleAmp = k_pivotAngleSource;
    public static final double k_pivotAngleStow = 275;

    // Intake speeds
    public static final double k_intakeSpeed = 0.7;
    public static final double k_ejectSpeed = -0.45;
    public static final double k_feedShooterSpeed = -0.5;
  }

  // PCM
  public static final int kPCMId = 0;
  public static final int kIntakeSolenoidForwardId = 2;

  // DIO

  // Shooter
  public static final int kShooterLeftMotorId = 12;
  public static final int kShooterRightMotorId = 13;

  public static final double kShooterP = 0.00005;
  public static final double kShooterI = 0.0;
  public static final double kShooterD = 0.0;
  public static final double kShooterFF = 0.0002;

  public static final double kShooterMinOutput = 0;
  public static final double kShooterMaxOutput = 1;

  // Climber
  public static final int kClimberLeftMotorId = 14;
  public static final int kClimberRightMotorId = 15;
  public static final double kClimberClimbSpeed = 600.0; // RPM
  public static final double kClimberReleaseSpeed = -600.0; // RPM

  public static final double kClimberGearRatio = 1.0 / 12.0;

  public static final double kClimberP = 0.001;
  public static final double kClimberI = 0.0;
  public static final double kClimberD = 0.0;
  public static final double kClimberMinOutput = -0.5;

  public static final double kClimberMaxOutput = 0.5;

  // Drivetrain
  public static class Drive {
    public static final double kP = 0.0; // 0.00085;
    public static final double kI = 0.0;
    public static final double kD = 0.0;

    public static final double kS = 0.1695;// 0.01;
    public static final double kV = 2.8559;// 2.6;
    public static final double kA = 0.4864;

    public static final int kFLMotorId = 8;
    public static final int kBLMotorId = 7;
    public static final int kFRMotorId = 6;
    public static final int kBRMotorId = 5;
  }

  public static class Field {
    public static final double k_width = Units.feetToMeters(54.0);
    public static final double k_length = Units.feetToMeters(27.0);

    // TODO: Add left and right subwoofer starting poses
    public static final Pose2d redCenterPose2d = new Pose2d(15.19, 5.50, new Rotation2d(Units.degreesToRadians(180.0)));
    public static final Pose2d blueCenterPose2d = new Pose2d(1.27, 5.50, new Rotation2d(0));
  }

  public static class LEDs {
    public static final int k_PWMId = 9;
    public static final int k_totalLength = 300;
  }
}
