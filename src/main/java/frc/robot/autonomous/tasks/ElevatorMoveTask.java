package frc.robot.autonomous.tasks;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.RobotTelemetry;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Elevator;


public class ElevatorMoveTask extends Task {
    private Drivetrain m_drive;
    private Elevator m_elevator;
    private double m_targetDistance;
    private double m_speed;
    private Pose2d m_startPose;
    private Elevator.ElevatorState m_targetElevatorState; 
    private Timer m_elevatorTimer = new Timer(); 
    private boolean m_elevatorMoving = false; 


    private Timer m_runningTimer = new Timer();
    private double m_lastTime = 0;
    private boolean m_driveFinished = false;


    public ElevatorMoveTask(double distance, double speed, Elevator.ElevatorState targetElevatorState) {
        m_drive = Drivetrain.getInstance();
        m_elevator = Elevator.getInstance();
        m_targetDistance = distance;
        m_speed = speed;
        m_targetElevatorState = targetElevatorState;
    }

    @Override
    public void start() {
        m_runningTimer.reset();
        m_runningTimer.start();
        m_startPose = m_drive.getPose();
        m_driveFinished = false;
        m_elevatorMoving = false;
    }

    @Override
    public void update() {
        if (!m_driveFinished) {
            m_drive.drive(m_speed, 0);
            if (isDriveFinished()) {
                m_driveFinished = true;
                startElevatorMovement(); 
            }
        } else if (m_elevatorMoving){
            //Do nothing while elevator is moving.  isFinished() will handle checking for completion.
        }
    }

    @Override
    public void updateSim() {
        if (!RobotBase.isReal()) {
            Pose2d currentPose = m_drive.getPose();
            double newX = currentPose.getX() - m_speed * (m_runningTimer.get() - m_lastTime) * Math.cos(currentPose.getRotation().getRadians());
            double newY = currentPose.getY() - m_speed * (m_runningTimer.get() - m_lastTime) * Math.sin(currentPose.getRotation().getRadians());
            Pose2d newPose = new Pose2d(newX, newY, currentPose.getRotation());
            m_drive.setPose(newPose);
            m_lastTime = m_runningTimer.get();
        }
    }

    @Override
    public boolean isFinished() {
        return m_driveFinished && !m_elevatorMoving; 
    }

    private boolean isDriveFinished() {
        Pose2d relativePose = m_startPose.relativeTo(m_drive.getPose());
        return Math.hypot(relativePose.getX(), relativePose.getY()) >= m_targetDistance;
    }

    private void startElevatorMovement(){
        m_elevatorMoving = true;
        m_elevatorTimer.reset();
        m_elevatorTimer.start();
        switch (m_targetElevatorState) {
            case STOW: m_elevator.goToElevatorStow(); break;
            case L2: m_elevator.goToElevatorL2(); break;
            case L3: m_elevator.goToElevatorL3(); break;
            case L4: m_elevator.goToElevatorL4(); break;
            case A1: m_elevator.goToAlgaeLow(); break;
            case A2: m_elevator.goToAlgaeHigh(); break;
            default: m_elevator.stop(); break;
        }
    }

    @Override
    public void done() {
        RobotTelemetry.print("Auto driving and elevating done");
        m_drive.drive(0, 0);
        m_elevator.stop();
    }
}
